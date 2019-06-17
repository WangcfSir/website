package com.website.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.website.system.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 管理员DAO
 *
 * @author yanqb
 * @date 2018-11-12 下午8:43:52
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 用户登录验证
     */
    User loginValid(@Param("username") String username, @Param("password") String password);

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
