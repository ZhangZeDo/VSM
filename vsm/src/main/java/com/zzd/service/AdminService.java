package com.zzd.service;

import com.zzd.model.TAdmin;

import java.util.List;

//管理员接口服务
public interface AdminService {
    boolean adminLogin(TAdmin admin);

    int addAdmin(TAdmin admin);

    int updateAdmin(TAdmin admin, String loginName);

    TAdmin queryAdminByName(String name);

    List<TAdmin> listAdmins();

}
