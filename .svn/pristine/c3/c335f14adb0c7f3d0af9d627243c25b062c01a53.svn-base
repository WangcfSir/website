package com.website.system.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.website.system.model.Download;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 下载中心 Mapper 接口
 *
 * @author yanqb
 * @since 2018-12-04
 */
public interface DownloadMapper extends BaseMapper<Download> {

    void updateStateById(@Param("id")Integer id, @Param("stateValue")Integer stateValue);

    List<Download> getRankingFour(@Param("rowNumber")Integer rowNumber);
}