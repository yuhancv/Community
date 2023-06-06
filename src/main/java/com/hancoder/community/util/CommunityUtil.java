package com.hancoder.community.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.liquibase.DataSourceClosingSpringLiquibase;
import org.springframework.util.DigestUtils;

import javax.swing.*;
import java.util.UUID;

public class CommunityUtil {
    // 生成一个随机字符串
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    //MD5加密算法对密码进行加密  md5 加密背后的逻辑
    // hello -> abc123455
    // hello + salt -> abc12355

    public static String md5(String key){
        if (StringUtils.isBlank(key)){
            return null;
        }

        return DigestUtils.md5DigestAsHex(key.getBytes());
    }

}
