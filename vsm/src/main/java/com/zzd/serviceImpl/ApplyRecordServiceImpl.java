package com.zzd.serviceImpl;

import com.zzd.dao.TApplyRecordMapper;
import com.zzd.model.TApplyRecord;
import com.zzd.model.TApplyRecordExample;
import com.zzd.model.TUser;
import com.zzd.service.ApplyRecordService;
import com.zzd.service.UserService;
import com.zzd.utils.UniqueIdUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("applyRecordService")
public class ApplyRecordServiceImpl implements ApplyRecordService {
    @Resource
    private TApplyRecordMapper applyRecordMapper;

    @Resource
    private UserService userService;


    @Override
    public TApplyRecord queryApplyRecordById(String id) {
        TApplyRecord applyRecord = applyRecordMapper.selectByPrimaryKey(id);
        return applyRecord;
    }

    @Override
    public int addApplyRecord(TApplyRecord applyRecord) {
        applyRecord.setId(UniqueIdUtil.buildId("A"));
        applyRecord.setStatus((byte)1);
        setApplyRecordInfo(applyRecord,"system");
        int result = applyRecordMapper.insertSelective(applyRecord);
        return result;
    }

    @Override
    public int handleApplyRecord(TApplyRecord applyRecord, String loginName) {
        setApplyRecordInfo(applyRecord,loginName);
        TApplyRecordExample example = new TApplyRecordExample();
        example.createCriteria().andIdEqualTo(applyRecord.getId());
        int result = applyRecordMapper.updateByExample(applyRecord,example);

        //如果同意申请的话
        if (applyRecord.getStatus() == (byte)2){
            //修改用户状态为1
            TUser user = userService.queryUsersByName(applyRecord.getUserName());
            user.setStatus((byte)1);
            result = userService.updateUser(user,loginName);
        }
        return result;
    }

    @Override
    public List<TApplyRecord> listApplyRecords() {
        TApplyRecordExample example = new TApplyRecordExample();
        example.createCriteria();
        List<TApplyRecord> applyRecords = applyRecordMapper.selectByExample(example);
        return applyRecords;
    }

    private void setApplyRecordInfo(TApplyRecord applyRecord,String loginName){
        if (applyRecord.getCreateBy()==null){
            applyRecord.setCreateBy(loginName);
        }
        applyRecord.setCreatedDatetime(new Date());
        applyRecord.setUpdateBy(loginName);
        applyRecord.setUpdatedDatetime(new Date());
    }
}
