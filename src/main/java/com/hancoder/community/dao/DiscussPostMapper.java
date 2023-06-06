package com.hancoder.community.dao;

import com.hancoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.*;

//为了实现查询功能，需要实现查询方法

@Mapper
public interface DiscussPostMapper {

    //offset 行号 limit 最多显示多少条数据
    //首页是直接陈列出 所有人的所有帖子
    //userId 用于显示指定的用户发布的帖子
    //userId等于0的时候，不需要拼接SQL
    //offset这一页 起始行的行号 limit表示这一页 最多显示多少条数据
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);


    //查询表里一共有多少条数据 也就是帖子的行数
    //首页的UserId为0 在个人主页 -> 我的帖子的时候就会拼接这个条件
    //@Param注解用于给参数取别名，该别名应用于Mybatis的SQL中简化名称
    //sql中动态地拼接条件，
    // 如果只有一个参数，并且在<if>里使用，必须加别名
    int selectDiscussPostRows(@Param("userId") int userId);
}



