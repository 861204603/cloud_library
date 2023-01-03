package com.sdut.cloud_library.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sdut.cloud_library.entity.Book;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;

@Mapper
public interface BookMapper extends BaseMapper<Book> {


    @Override
    @Select("select *")
    Book selectById(Serializable id);
}
