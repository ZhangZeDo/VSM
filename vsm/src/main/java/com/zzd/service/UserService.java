package com.zzd.service;


import com.zzd.model.TUser;

import java.util.List;

//用户接口服务
public interface UserService {
    /**
     * 用户登录检查是否输入正确
     * @param user
     * @return
     */
    boolean userLogin(TUser user);

    /**
     * 添加用户
     * @param user
     * @return
     */
    int addUser(TUser user);

    /**
     * 删除用户，即修改用户状态
     * @param user
     * @return
     */
    int updateUser(TUser user, String loginName);

    /**
     * 根据唯一性名称查询对应用户
     * @param userName
     * @return
     */
    TUser queryUsersByName(String userName);

    List<TUser> listUsers();

}
