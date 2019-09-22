package com.zzd.controller;

import com.zzd.model.TApplyRecord;
import com.zzd.model.TRewardRecord;
import com.zzd.service.RewardRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class RewardRecordController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private RewardRecordService rewardRecordService;

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
}
