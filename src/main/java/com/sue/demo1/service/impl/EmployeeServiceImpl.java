package com.sue.demo1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sue.demo1.mapper.EmployeeMapper;
import com.sue.demo1.model.EmployeeModel;
import com.sue.demo1.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, EmployeeModel>
        implements EmployeeService {


}
