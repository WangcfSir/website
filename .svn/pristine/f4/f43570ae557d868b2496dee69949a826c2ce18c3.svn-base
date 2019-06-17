package com.website.system.dao;

import com.website.system.model.Picture;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 暂时针对首页的轮播图和无线滚动图 Mapper 接口
 *
 * @author yanqb
 * @since 2018-11-23
 */
public interface PictureMapper extends BaseMapper<Picture> {

    String getSortArray(Integer type);

    void updateStateById(@Param("id")Integer id, @Param("stateValue")Integer stateValue);
}