package com.sdut.cloud_library.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sdut.cloud_library.common.R;
import com.sdut.cloud_library.entity.Book;
import com.sdut.cloud_library.entity.Record;
import com.sdut.cloud_library.service.RecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private RecordService recordService;

    /**
     * 查询记录
     *
     * @param record
     * @return
     */
    @PostMapping("/recordSelect")
    public R<List<Record>> recordSelect(@RequestBody Record record) {

        String recordBookname = record.getRecordBookname();
        String recordBorrower= record.getRecordBorrower();

        LambdaQueryWrapper<Record> qw = new LambdaQueryWrapper<>();
        qw.like(recordBookname != null, Record::getRecordBookname, recordBookname);
        qw.like(recordBorrower != null, Record::getRecordBorrower, recordBorrower);
        qw.orderByDesc(Record::getRecordRemandtime);
        List<Record> list = recordService.list(qw);
        return R.success(list);
    }
}
