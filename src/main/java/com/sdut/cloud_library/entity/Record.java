package com.sdut.cloud_library.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
@Data
public class Record implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer recordId;        //图书借阅id
    private String recordBookname;   //借阅的图书名称
    private String recordBookisbn;   //借阅的图书的ISBN编号
    private String recordBorrower;   //图书借阅人
    private String recordBorrowtime; //图书借阅时间
    private String recordRemandtime; //图书归还时间
}
