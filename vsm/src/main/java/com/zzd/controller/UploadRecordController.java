package com.zzd.controller;

import com.zzd.dto.VideoDTO;
import com.zzd.model.TAdmin;
import com.zzd.model.TUploadRecord;
import com.zzd.model.TUser;
import com.zzd.service.UploadRecordService;
import com.zzd.service.VideoService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

@Controller
public class UploadRecordController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private UploadRecordService uploadRecordService;
    @Resource
    private VideoService videoService;

    @RequestMapping(value = "/listVideoUpload",method = RequestMethod.GET)
    public String listVideoUpload(HttpServletRequest request){
        try{
            List<TUploadRecord> uploadRecords = uploadRecordService.listUploads();
            request.setAttribute("uploadRecords",uploadRecords);
            return "listVideoUpload";
        }catch (Exception e){
            logger.error("获取上传视频列表失败，原因：{}",e);
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/agreeUpload")
    public String agreeUpload(HttpServletRequest request){
        try{
            HttpSession session = request.getSession();
            String id = request.getParameter("id");
            TUploadRecord uploadRecord = uploadRecordService.queryUploadById(id);
            uploadRecord.setStatus((byte)2);
            uploadRecordService.approvalUpload(uploadRecord,((TAdmin)session.getAttribute("Admin")).getAdminName());

            List<TUploadRecord> uploadRecords = uploadRecordService.listUploads();
            request.setAttribute("uploadRecords",uploadRecords);
            return "listVideoUpload";
        }catch (Exception e){
            logger.error("审批视频上传同意失败，原因：{}",e);
            return e.getMessage();
        }
    }
    @RequestMapping(value = "/rejectUpload")
    public String rejectUpload(HttpServletRequest request){
        try{
            HttpSession session = request.getSession();
            String id = request.getParameter("id");
            TUploadRecord uploadRecord = uploadRecordService.queryUploadById(id);
            uploadRecord.setStatus((byte)0);
            uploadRecordService.approvalUpload(uploadRecord,((TAdmin)session.getAttribute("Admin")).getAdminName());

            List<TUploadRecord> uploadRecords = uploadRecordService.listUploads();
            request.setAttribute("uploadRecords",uploadRecords);
            return "listVideoUpload";
        }catch (Exception e){
            logger.error("审批文件上传拒绝失败，原因：{}",e);
            return e.getMessage();
        }
    }
    @RequestMapping(value = "/detailUpload")
    public String detailUpload(HttpServletRequest request){
        try{
            String id = request.getParameter("id");
            TUploadRecord uploadRecord = uploadRecordService.queryUploadById(id);
            request.setAttribute("uploadRecord",uploadRecord);
            return "detailUpload";
        }catch (Exception e){
            logger.error("获取上传记录详情失败，原因：{}",e);
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/uploadVideo" ,method = RequestMethod.POST)
    public String uploadVideo(@RequestParam("videoCover") MultipartFile videoCover, @RequestParam("videoUrl") MultipartFile videoUrl, HttpServletRequest request){
        try{
            HttpSession session = request.getSession();
            TUser user = (TUser) session.getAttribute("User");

            String videoType = request.getParameter("videoType");
            String videoTitle = request.getParameter("videoTitle");
            String videoDescription = request.getParameter("videoDescription");
            //File videoCover = (File)request.getRequestDispatcher("videoCover");
            //File videoUrl = (File)request.getParameter("videoUrl");

            //保存文件到服务器
            saveCover(videoCover);
            saveVideo(videoUrl);

            //添加上传记录到数据库
            TUploadRecord uploadRecord = new TUploadRecord();
            uploadRecord.setUserId(user.getId());
            uploadRecord.setUploadType(videoType);
            uploadRecord.setUploadTitle(videoTitle);
            uploadRecord.setUploadDescription(videoDescription);
            uploadRecord.setCoverUrl("../cover/"+videoCover.getOriginalFilename());
            uploadRecord.setUploadUrl("../video/"+videoUrl.getOriginalFilename());
            uploadRecordService.addUploadRecord(uploadRecord);

            List<VideoDTO> myVideos = videoService.listMyVideos(user.getId());
            request.setAttribute("myVideos",myVideos);
            return "myVideo";
        }catch (Exception e){
            logger.error("用户上传视频失败，原因：{}",e);
            return e.getMessage();
        }
    }

    private void saveCover(MultipartFile file){
        try {
            String filePath = "D:\\ideaProject\\vsm\\src\\main\\webapp\\cover\\"+file.getOriginalFilename();
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(filePath));
        }catch (Exception e){
            logger.error("上传视频封面出错，原因{}",e);
        }
    }

    private void saveVideo(MultipartFile file){
        try {
            String filePath = "D:\\ideaProject\\vsm\\src\\main\\webapp\\video\\"+file.getOriginalFilename();
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(filePath));
        }catch (Exception e){
            logger.error("上传视频封面出错，原因{}",e);
        }
    }
}
