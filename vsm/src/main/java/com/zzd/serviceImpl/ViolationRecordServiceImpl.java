package com.zzd.serviceImpl;

import com.zzd.dao.TViolationRecordMapper;
import com.zzd.model.TViolationRecord;
import com.zzd.model.TViolationRecordExample;
import com.zzd.service.ViolationRecordService;
import com.zzd.utils.UniqueIdUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("violationRecordService")
public class ViolationRecordServiceImpl implements ViolationRecordService {
    @Resource
    private TViolationRecordMapper violationRecordMapper;

    @Override
    public List<TViolationRecord> listViolations() {
        TViolationRecordExample example = new TViolationRecordExample();
        example.createCriteria();
        List<TViolationRecord> violationRecords = violationRecordMapper.selectByExample(example);
        return violationRecords;
    }

    @Override
    public TViolationRecord queryViolationById(String id) {
        TViolationRecord violationRecord = violationRecordMapper.selectByPrimaryKey(id);
        return violationRecord;
    }

    @Override
    public int addViolationRecord(TViolationRecord violationRecord, String loginName) {
        violationRecord.setId(UniqueIdUtil.buildId("V"));
        violationRecord.setStatus((byte)1);
        setViolationRecordInfo(violationRecord,loginName);
        int result = violationRecordMapper.insertSelective(violationRecord);
        return result;
    }

    @Override
    public int closeViolationRecord(TViolationRecord violationRecord, String loginName) {
        violationRecord.setStatus((byte)0);
        setViolationRecordInfo(violationRecord,loginName);
        TViolationRecordExample example = new TViolationRecordExample();
        example.createCriteria().andIdEqualTo(violationRecord.getId());
        int result = violationRecordMapper.updateByExample(violationRecord,example);
        return result;
    }

    private void setViolationRecordInfo(TViolationRecord violationRecord,String loginName){
        if (violationRecord.getCreateBy()==null){
            violationRecord.setCreateBy(loginName);
        }
        violationRecord.setCreatedDatetime(new Date());
        violationRecord.setUpdateBy(loginName);
        violationRecord.setUpdatedDatetime(new Date());
    }
}
