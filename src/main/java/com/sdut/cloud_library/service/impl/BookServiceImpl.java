package com.sdut.cloud_library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdut.cloud_library.entity.Book;
import com.sdut.cloud_library.mapper.BookMapper;
import com.sdut.cloud_library.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {
}
