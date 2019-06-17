package com.website.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.website.system.model.User;
import org.apache.ibatis.annotations.Param;

/**
 *  管理员 服务类
 *
 * @author yanqb
 * @since 2018-11-18
 */
public interface UserService extends IService<User> {

    /**
     * 修改用户状态(激活与不激活)
     */
    void updateStateById(@Param("id")Integer id, @Param("stateValue")Integer stateValue);

    /**
     * 修改密码
     */
    void changePwd(@Param("userId") Integer userId, @Param("pwd") String pwd);

    /**
     * 根据账号或手机号查询用户（用于判断是否存在）
     */
    String getByAccount(@Param("account") String account, @Param("phone") String phone);

}
