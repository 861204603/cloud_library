package com.sdut.cloud_library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdut.cloud_library.entity.Record;
import com.sdut.cloud_library.mapper.RecordMapper;
import com.sdut.cloud_library.service.RecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {
}
