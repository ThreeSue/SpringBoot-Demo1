package com.sue.demo1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sue.demo1.model.EmployeeModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<EmployeeModel> {

}
