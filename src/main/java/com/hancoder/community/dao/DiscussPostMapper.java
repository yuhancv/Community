package com.hancoder.community.dao;

import com.hancoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.*;

@Mapper
public interface DiscussPostMapper {

    //offset 行号 limit 最多显示多少条数据
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    //@Param注解用于给参数取别名，
    // 如果只有一个参数，并且在<if>里使用，必须加别名
    int selectDiscussPostRows(@Param("userId") int userId);
}


