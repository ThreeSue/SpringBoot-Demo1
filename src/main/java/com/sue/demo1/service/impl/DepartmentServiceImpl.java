package com.sue.demo1.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sue.demo1.mapper.DepartmentMapper;
import com.sue.demo1.model.DepartmentModel;
import com.sue.demo1.service.DepartmentService;
import org.springframework.stereotype.Service;

/**
 * @Author: 蘇世睿
 * @Class: 移動互聯2412
 * @SeatNo: 21
 * @Description : [一句话描述这个类的作用]
 * @Version : [v1.1]
 * @Date: 2026/3/25
 * @Time: 11:27
 */

@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, DepartmentModel>
        implements DepartmentService {
}
