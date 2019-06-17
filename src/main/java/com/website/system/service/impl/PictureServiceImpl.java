package com.website.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.website.system.dao.PictureMapper;
import com.website.system.model.Picture;
import com.website.system.service.PictureService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 暂时针对首页的轮播图和无线滚动图 服务实现类
 *
 * @author yanqb
 * @since 2018-11-23
 */
@Service
public class PictureServiceImpl extends ServiceImpl<PictureMapper, Picture> implements PictureService {

    @Resource
    PictureMapper mapper;

    @Override
    public String getSortArray(Integer type) {
        return mapper.getSortArray(type);
    }

    @Override
    public void updateStateById(Integer id, Integer stateValue){
       mapper.updateStateById(id, stateValue);
    }
    
}
