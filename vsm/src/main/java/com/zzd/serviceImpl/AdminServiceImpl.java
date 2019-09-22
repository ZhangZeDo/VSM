package com.zzd.serviceImpl;

import com.xiaoleilu.hutool.util.StrUtil;
import com.zzd.dao.TAdminMapper;
import com.zzd.model.TAdmin;
import com.zzd.model.TAdminExample;
import com.zzd.service.AdminService;
import com.zzd.utils.UniqueIdUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service("adminService")
public class AdminServiceImpl implements AdminService {
    @Resource
    private TAdminMapper adminMapper;

    @Override
    public boolean adminLogin(TAdmin admin) {
       TAdmin tAdmin = queryAdminByName(admin.getAdminName());
       if (StrUtil.equals(tAdmin.getAdminPassword(),admin.getAdminPassword())){
           return true;
       }
       return false;
    }

    @Override
    public int addAdmin(TAdmin admin) {
        admin.setId(UniqueIdUtil.buildId("A"));
        admin.setStatus((byte)1);
        setAdminInfo(admin,"system");
        int result = adminMapper.insertSelective(admin);
        return result;
    }

    @Override
    public int updateAdmin(TAdmin admin, String loginName) {
        setAdminInfo(admin,loginName);
        TAdminExample example = new TAdminExample();
        example.createCriteria().andAdminNameEqualTo(admin.getAdminName());
        int result = adminMapper.updateByExample(admin,example);
        return result;
    }

    @Override
    public TAdmin queryAdminByName(String name) {
        TAdminExample example = new TAdminExample();
        example.createCriteria().andAdminNameEqualTo(name);
        List<TAdmin> adminList = adminMapper.selectByExample(example);
        if (adminList==null || adminList.size()==0){
            return null;
        }
        return adminList.get(0);
    }

    @Override
    public List<TAdmin> listAdmins() {
        TAdminExample example = new TAdminExample();
        example.createCriteria();
        List<TAdmin> admins = adminMapper.selectByExample(example);
        return admins;
    }

    private void setAdminInfo(TAdmin admin , String loginName){
        if (admin.getCreateBy()==null){
            admin.setCreateBy(loginName);
        }
        admin.setCreatedDatetime(new Date());
        admin.setUpdateBy(loginName);
        admin.setUpdatedDatetime(new Date());
    }
}
