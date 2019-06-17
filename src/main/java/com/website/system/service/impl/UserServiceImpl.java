package com.website.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.website.system.dao.UserMapper;
import com.website.system.model.User;
import com.website.system.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *  服管理员 务实现类
 *
 * @author yanqb
 * @since 2018-11-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    UserMapper mapper;

    @Override
    public void updateStateById(Integer id, Integer stateValue){
        mapper.updateStateById(id, stateValue);
    }

    @Override
    public void changePwd(Integer userId, String password) {
        mapper.changePwd(userId, password);
    }

    @Override
    public String getByAccount(String account, String phone) {
        return mapper.getByAccount(account, phone);
    }

}
