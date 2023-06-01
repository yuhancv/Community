package com.hancoder.community.controller;

import com.hancoder.community.dao.DiscussPostMapper;
import com.hancoder.community.entity.DiscussPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller

public class HelloController {
    @Autowired
    private DiscussPostMapper discussPostMapper;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello";
    }
}
