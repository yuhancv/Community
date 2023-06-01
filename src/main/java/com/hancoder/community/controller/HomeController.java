package com.hancoder.community.controller;

import com.hancoder.community.entity.DiscussPost;
import com.hancoder.community.entity.Page;
import com.hancoder.community.service.DiscussPostService;
import com.hancoder.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

@Controller
public class HomeController {
    @Autowired
    private DiscussPostService discussPostService;
    @Autowired
    private UserService userService;

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page){
        //方法调用之前 spring mvc自动实例化 model 和 page， page自动注入model
        //所以在thymeleaf模板中可以直接访问page中的数据

        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/index");

        List<DiscussPost> list = discussPostService.findDiscussPosts(0, page.getOffSet(), page.getLimit());
        List<Map<String, Object>> listOfPosts = new ArrayList<>();

        if (!list.isEmpty()){
            for (DiscussPost post: list){
                Map<String, Object> map = new HashMap<>();
                map.put("post", post);
                map.put("user", userService.findUserById(post.getUserId()));
                listOfPosts.add(map);
            }
        }

        model.addAttribute("discussPosts", listOfPosts);
        return "/index";
    }

}


