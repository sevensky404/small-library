package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.ResultCode;
import com.example.dao.AdminInfoDao;
import com.example.dao.BookInfoDao;
import com.example.entity.Account;
import com.example.entity.AdminInfo;
import com.example.entity.BookInfo;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookInfoService {
    @Resource
    private BookInfoDao bookInfoDao;


    public BookInfo findById(Long id){
        return bookInfoDao.selectByPrimaryKey(id);
    }

    public void update(BookInfo bookInfo){
        bookInfoDao.updateByPrimaryKeySelective(bookInfo);
    }


    public  void add(BookInfo bookInfo){
        BookInfo info=bookInfoDao.findByName(bookInfo.getName());
        if(ObjectUtil.isNotEmpty(info)){
            throw new CustomException(ResultCode.USER_EXIST_ERROR);
        }


        bookInfoDao.insertSelective(bookInfo);
    }
    public List<BookInfo> findAll(){

        return bookInfoDao.selectAll();
    }
    public void deleteById(Long id){
        bookInfoDao.deleteByPrimaryKey(id);
    }

    public PageInfo<BookInfo> findPage(Integer pageNum, Integer pageSize) {
        //开启自动分页
        PageHelper.startPage(pageNum,pageSize);
        List<BookInfo> infos=bookInfoDao.selectAll();
        return PageInfo.of(infos);
    }

    public PageInfo<BookInfo> findPageName(Integer pageNum, Integer pageSize, String name) {
        //开启自动分页
        PageHelper.startPage(pageNum,pageSize);
        List<BookInfo> infos = bookInfoDao.findByNamePage(name);
        return PageInfo.of(infos);
    }
}