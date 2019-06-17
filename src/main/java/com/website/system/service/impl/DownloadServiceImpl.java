package com.website.system.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.website.system.dao.DownloadMapper;
import com.website.system.model.Download;
import com.website.system.service.DownloadService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 下载中心 服务实现类
 *
 * @author yanqb
 * @since 2018-12-04
 */
@Service
public class DownloadServiceImpl extends ServiceImpl<DownloadMapper, Download> implements DownloadService {

    @Resource
    DownloadMapper mapper;

    @Override
    public void updateStateById(Integer id, Integer stateValue){
        mapper.updateStateById(id, stateValue);
    }

    @Override
    public List<Download> getRankingFour(Integer rowNumber) {
        return mapper.getRankingFour(rowNumber);
    }
}
