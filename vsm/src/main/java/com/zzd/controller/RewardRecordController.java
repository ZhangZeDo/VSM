package com.zzd.controller;

import com.zzd.dto.VideoDTO;
import com.zzd.model.TApplyRecord;
import com.zzd.model.TRewardRecord;
import com.zzd.model.TUser;
import com.zzd.service.RewardRecordService;
import com.zzd.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class RewardRecordController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private RewardRecordService rewardRecordService;
    @Resource
    private VideoService videoService;

    @RequestMapping(value = "listReward" ,method = RequestMethod.GET)
    public String listReward(HttpServletRequest request){
        try {
            List<TRewardRecord> rewardRecords = rewardRecordService.listReward();
            request.setAttribute("rewardRecords",rewardRecords);
            return "listReward";
        }catch (Exception e){
            logger.error("获取打赏记录列表失败，原因{}",e);
            return e.getMessage();
        }
    }

    @RequestMapping(value = "addReward",method = RequestMethod.POST)
    public String addReward(HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            TUser user = (TUser)session.getAttribute("User");

            String rewardNum = request.getParameter("rewardNum");
            String remark = request.getParameter("remark");
            String videoId = request.getParameter("videoId");
            String rewardId = request.getParameter("rewardId");

            TRewardRecord rewardRecord = new TRewardRecord();
            rewardRecord.setRewardNum(Integer.valueOf(rewardNum));
            rewardRecord.setRemark(remark);
            rewardRecord.setVideoId(videoId);
            rewardRecord.setRewardId(rewardId);
            rewardRecord.setUserId(user.getId());
            rewardRecordService.addRewardRecord(rewardRecord);

            VideoDTO videoDTO = videoService.queryVideoById(videoId);
            request.setAttribute("video",videoDTO);
            return "videoDetail";
        }catch (Exception e){
            logger.error("用户添加打赏失败，原因{}",e);
            return e.getMessage();
        }
    }
}
