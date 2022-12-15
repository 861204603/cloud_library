package com.sdut.cloud_library.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Integer bookId;        //图书编号
    private String bookName;       //图书名称
    private String bookIsbn;       //图书标准ISBN编号
    private String bookPress;      //图书出版社
    private String bookAuthor;     //图书作者
    private Integer bookPagination;//图书页数
    private Double bookPrice;      //图书价格
    private String bookUploadtime; //图书上架时间
    private String bookStatus;     //图书状态
    private String bookBorrower;   //图书借阅人
    private String bookBorrowtime; //图书借阅时间
    private String bookReturntime; //图书预计归还时间

}
