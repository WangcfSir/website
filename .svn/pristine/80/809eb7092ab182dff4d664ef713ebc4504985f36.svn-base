package com.website.common.factory;

import com.website.core.util.SpringContextHolder;
import com.website.system.dao.UserMapper;
import com.website.system.model.User;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * 常量的生产工厂
 *
 * @author yanqb
 * @date 2018-12-25 20:42
 */
@Component
@DependsOn("springContextHolder")
public class ConstantFactory implements IConstantFactory {

    private UserMapper userMapper = SpringContextHolder.getBean(UserMapper.class);

    public static IConstantFactory me() {
        return SpringContextHolder.getBean("constantFactory");
    }

    /**
     * 根据用户id获取用户名称
     *
     * @author stylefeng
     * @Date 2017/5/9 23:41
     */
    @Override
    public String getUserNameById(Integer userId) {
        User user = userMapper.selectById(userId);
        if (user != null) {
            return user.getName();
        }
        return "--";
    }


    /**
     * 获取性别名称
     */
    @Override
    public String getSexName(Integer sex) {
        if (sex != null) {
            return sex == 1?"男":"女";
        }
        return "--";
    }

}
