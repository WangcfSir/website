package com.website.system.service;


import com.baomidou.mybatisplus.service.IService;
import com.website.system.model.Picture;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 暂时针对首页的轮播图和无线滚动图 服务类
 *
 * @author yanqb
 * @since 2018-11-23
 */
public interface PictureService extends IService<Picture> {

    /**
     * 获取已存在的排序（根据模块类型）
     */
    String getSortArray(@Param("type") Integer type);

    /**
     * 修改状态(显示不显示)
     */
    void updateStateById(@Param("id")Integer id, @Param("stateValue")Integer stateValue);
}
