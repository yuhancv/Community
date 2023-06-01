package com.hancoder.community;

import com.hancoder.community.dao.DiscussPostMapper;
import com.hancoder.community.dao.UserMapper;
import com.hancoder.community.entity.DiscussPost;
import com.hancoder.community.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;
import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class TestMapper {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Test
    public void Test(){
        /*
        List<DiscussPost> list = discussPostMapper.selectDiscussPosts(149, 0, 10);

        for (DiscussPost discussPost : list)
            System.out.println(discussPost);

       int rows = discussPostMapper.selectDiscussPostRows(149);
       */

       System.out.println(discussPostMapper.selectDiscussPostRows(0));
    }

}
