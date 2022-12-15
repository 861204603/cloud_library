package com.sdut.cloud_library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdut.cloud_library.entity.User;
import com.sdut.cloud_library.mapper.UserMapper;
import com.sdut.cloud_library.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
