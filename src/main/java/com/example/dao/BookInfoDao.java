package com.example.dao;

import com.example.entity.AdminInfo;
import com.example.entity.BookInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface BookInfoDao extends Mapper<BookInfo> {
    @Select("select * from book_info where name = #{name} and password = #{password}")
    BookInfo findByNameAndPass(@Param("name") String name, @Param("password") String password);

    @Select("select *  from book_info where name=#{name} ")
    BookInfo findByName(@Param("name") String name);

    @Select("select * from book_info where name like concat('%',#{name},'%')")
    List<BookInfo> findByNamePage(@Param("name") String name);
}