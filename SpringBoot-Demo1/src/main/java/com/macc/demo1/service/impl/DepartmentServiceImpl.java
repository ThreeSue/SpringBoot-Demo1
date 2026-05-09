package com.macc.demo1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.macc.demo1.mapper.DepartmentMapper;
import com.macc.demo1.model.DepartmentModel;
import com.macc.demo1.service.DepartmentService;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, DepartmentModel> implements DepartmentService {
}
