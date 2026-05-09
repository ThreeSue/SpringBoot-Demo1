package com.macc.demo1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.macc.demo1.mapper.EmployeeMapper;
import com.macc.demo1.model.EmployeeModel;
import com.macc.demo1.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, EmployeeModel>
        implements EmployeeService {
}
