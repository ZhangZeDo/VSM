package com.zzd.serviceImpl;


import com.xiaoleilu.hutool.util.StrUtil;
import com.zzd.dao.TUserMapper;
import com.zzd.model.TUser;
import com.zzd.model.TUserExample;
import com.zzd.service.UserService;
import com.zzd.utils.UniqueIdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private TUserMapper userMapper;

    @Override
    public boolean userLogin(TUser tUser) {
        TUser user = queryUsersByName(tUser.getUserName());
        if (StrUtil.equals(user.getUserPassword(),tUser.getUserPassword())){
            return true;
        }
        return false;
    }

    @Override
    public int addUser(TUser user) {
        user.setId(UniqueIdUtil.buildId("U"));
        user.setStatus((byte)1);
        setUserInfo(user,"system");
        int result = userMapper.insertSelective(user);
        return result;
    }

    @Override
    public int updateUser(TUser user ,String loginName){
        setUserInfo(user,loginName);
        TUserExample example = new TUserExample();
        example.createCriteria().andUserNameEqualTo(user.getUserName());
        int result = userMapper.updateByExample(user,example);
        return result;
    }

    public TUser queryUsersByName(String userName){
        TUserExample example = new TUserExample();
        example.createCriteria().andUserNameEqualTo(userName);
        List<TUser> users = userMapper.selectByExample(example);
        if (users==null || users.size()==0){
            return null;
        }
        return users.get(0);
    }

    @Override
    public List<TUser> listUsers() {
        TUserExample example = new TUserExample();
        example.createCriteria();
        List<TUser> users = userMapper.selectByExample(example);
        return users;
    }

    private void setUserInfo(TUser user , String loginName){
        if (user.getCreateBy()==null){
            user.setCreateBy(loginName);
        }
        user.setCreatedDatetime(new Date());
        user.setUpdateBy(loginName);
        user.setUpdatedDatetime(new Date());
    }

}
