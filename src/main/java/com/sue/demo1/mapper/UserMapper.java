package com.sue.demo1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sue.demo1.model.UserModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: 蘇世睿
 * @Class: 移動互聯2412
 * @SeatNo: 21
 * @Description : [一句话描述这个类的作用]
 * @Version : [v1.1]
 * @Date: 2026/5/13
 * @Time: 11:52
 */

@Mapper
public interface UserMapper extends BaseMapper<UserModel> {
}
