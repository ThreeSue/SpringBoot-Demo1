package com.macc.demo1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.macc.demo1.model.DepartmentModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentMapper extends BaseMapper<DepartmentModel> {
}
