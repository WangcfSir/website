package com.website.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.website.system.dao.DoctorMapper;
import com.website.system.model.Doctor;
import com.website.system.service.DoctorService;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl extends ServiceImpl<DoctorMapper, Doctor> implements DoctorService {
}
