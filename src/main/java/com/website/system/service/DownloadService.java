package com.website.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.website.system.model.Download;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 下载中心 服务类
 *
 * @author yanqb
 * @since 2018-12-04
 */
public interface DownloadService extends IService<Download> {

    /**
     * 修改状态(显示不显示)
     */
    void updateStateById(@Param("id")Integer id, @Param("stateValue")Integer stateValue);

    /**
     * 获取下载中心前四条（根据类型，供网站主页动态显示最新信息）
     */
    List<Download> getRankingFour(@Param("rowNumber")Integer rowNumber);
}
