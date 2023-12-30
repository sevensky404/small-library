package com.example.controller;

import com.example.common.Result;
import com.example.entity.BookInfo;
import com.example.service.BookInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/bookInfo")
public class BookInfoController {
    @Resource
    private BookInfoService bookInfoService;

    @PostMapping
    public Result add(@RequestBody BookInfo bookInfo){
        bookInfoService.add(bookInfo);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody BookInfo bookInfo){
        bookInfoService.update(bookInfo);
        return Result.success();
    }
    @GetMapping
    public Result findAll(){
        List<BookInfo> list=bookInfoService.findAll();
        return Result.success(list);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id){
        bookInfoService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        PageInfo<BookInfo> info = bookInfoService.findPage(pageNum,pageSize);
        return Result.success(info);
    }

    @GetMapping("/page/{name}")
    public Result findPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize,@PathVariable String name){
        PageInfo<BookInfo> info = bookInfoService.findPageName(pageNum,pageSize,name);
        return Result.success(info);
    }

}