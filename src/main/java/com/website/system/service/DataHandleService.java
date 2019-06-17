package com.website.system.service;


import com.baomidou.mybatisplus.service.IService;
import com.website.system.model.Picture;
import org.apache.ibatis.annotations.Param;

/**
 * 数据处理（设置默认删除6个月之前的） 服务类
 *
 * @author yanqb
 * @since 2018-11-23
 */
public interface DataHandleService extends IService<Picture> {

    /**
     * 用户登录验证
     */
    String getSortArray(@Param("type") Integer type);
}
