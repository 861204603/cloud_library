package com.sdut.cloud_library.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sdut.cloud_library.common.R;
import com.sdut.cloud_library.entity.Book;
import com.sdut.cloud_library.entity.Record;
import com.sdut.cloud_library.service.BookService;
import com.sdut.cloud_library.service.RecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private RecordService recordService;

    /**
     * 根据上架日期查询推荐图书
     * @return
     */
    @Transactional
    @PostMapping
    public R<List<Book>> bookRecommend(){
        log.info("查询");
        //查询图书
        LambdaQueryWrapper<Book> qw = new LambdaQueryWrapper<>();
        qw.orderByDesc(Book::getBookUploadtime);
        qw.last("limit 5");
        List<Book> list=bookService.list(qw);
        return R.success(list);
    }

    /**
     * 根据id查询书籍
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<Book> getById(@PathVariable Integer id){
        LambdaQueryWrapper<Book> qw = new LambdaQueryWrapper<>();
        qw.eq(Book::getBookId, id);
        Book book = bookService.getOne(qw);
        return R.success(book);
    }

    /**
     * 借阅图书
     * @param book
     * @return
     */
    @Transactional
    @PostMapping("/borrow")
    public R<String> borrow(@RequestBody Book book){
        log.info(book.getBookReturntime());
        book.setBookBorrowtime(String.valueOf(LocalDateTime.now()).substring(0,10));
        bookService.updateById(book);

        //保存记录
        Record record=new Record();
        record.setRecordBorrower(book.getBookBorrower());
        record.setRecordBookisbn(book.getBookIsbn());
        record.setRecordBookname(book.getBookName());
        record.setRecordBorrowtime(String.valueOf(LocalDateTime.now()).substring(0,10));
        record.setRecordRemandtime(book.getBookReturntime());
        recordService.save(record);
        return R.success( "借阅成功");
    }

    /**
     * 根据书名和作者名模糊查询需要的书籍
     * @param book
     * @return
     */
    @PostMapping("/bookSelect")
    public R<List> bookSelect(@RequestBody Book book ){
        String bookName=book.getBookName();
        String bookAuthor=book.getBookAuthor();
        log.info(bookAuthor,bookName);
        LambdaQueryWrapper<Book> qw = new LambdaQueryWrapper<>();
        qw.like(bookName!=null,Book::getBookName,bookName);
        qw.like(bookAuthor!=null,Book::getBookAuthor,bookAuthor);
        List<Book> list = bookService.list(qw);
        return R.success(list);
    }

    /**
     * 根据借阅者姓和书名条件模糊查询正在借阅中的书
     * @param book
     * @return
     */
    @PostMapping("/borrowSelect")
    public R<List<Book>> borrowSelect(@RequestBody Book book){

        String bookName=book.getBookName();
        String bookBorrower=book.getBookBorrower();


        //查询所有借阅中的书
        LambdaQueryWrapper<Book> qw = new LambdaQueryWrapper<>();
        qw.eq(Book::getBookStatus, "1");
        qw.like(bookName!=null,Book::getBookName,bookName);
        qw.like(bookBorrower!=null,Book::getBookBorrower,bookBorrower);
        List<Book> list = bookService.list(qw);
        return R.success(list);
    }

    /**
     * 还书
     * @param book
     * @return
     */
    @Transactional
    @PostMapping("/returnBook")
    public R<String> returnBook(@RequestBody Book book){
        //根据书名和借阅者和借阅时间查询记录
        String recordBookName=book.getBookName();
        String recordBorrower=book.getBookBorrower();
        String recordBorrowtime=book.getBookBorrowtime();
        LambdaQueryWrapper<Record> qw = new LambdaQueryWrapper<>();
        qw.eq(Record::getRecordBookname,recordBookName);
        qw.eq(Record::getRecordBorrower,recordBorrower);
        qw.eq(Record::getRecordBorrowtime,recordBorrowtime);
        qw.orderByDesc(Record::getRecordId );
        qw.last("limit 1");
        Record record=recordService.getOne(qw);
        //更新记录记录中的还书时间
        String returnTime=String.valueOf(LocalDateTime.now()).substring(0,10);
        record.setRecordRemandtime(returnTime);
        recordService.updateById(record);


        //还书
        book.setBookStatus("0");
        book.setBookBorrower("");
        book.setBookBorrowtime("");
        book.setBookReturntime("");
        bookService.updateById(book);

        return R.success("还书成功");
    }

    /**
     * 根据ID删除图书
     * @param id
     * @return
     */
    @Transactional
    @PostMapping("/delete/{id}")
    public R<String> deleteBook(@PathVariable Integer id){
        bookService.removeById(id);
        return R.success("删除成功");
    }

    /**
     * 添加一本新的图书
     * @param book
     * @return
     */
    @Transactional
    @PostMapping("/add")
    public R<String> addBook(@RequestBody Book book){
        String uploadTime=String.valueOf(LocalDateTime.now()).substring(0,10);
        book.setBookUploadtime(uploadTime);
        bookService.save(book);
        return R.success("添加成功");
    }
}
