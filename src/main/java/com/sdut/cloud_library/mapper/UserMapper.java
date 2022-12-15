package com.sdut.cloud_library.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sdut.cloud_library.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
