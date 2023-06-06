package com.hancoder.community.service;

import com.hancoder.community.dao.UserMapper;
import com.hancoder.community.entity.User;
import com.hancoder.community.util.CommunityUtil;
import com.hancoder.community.util.MailClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${community.path.domain}")
    private String domain;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    public User findUserById(int id){
        return userMapper.selectById(id);
    }

    public Map<String, Object> register(User user){
        //对user对象的空值进行判断处理
        Map<String, Object> map = new HashMap<>();

        if (user == null)
            throw new IllegalArgumentException("参数不能为空");
        else  if (StringUtils.isBlank(user.getUsername())){
            map.put("usernameMsg", "账号不能为空");
            return map;
        }else  if (StringUtils.isBlank(user.getPassword())){
            map.put("passwordMsg", "密码不能为空");
            return map;
        }else  if (StringUtils.isBlank(user.getEmail())){
            map.put("emailMsg", "邮箱不能为空");
            return map;
        }

        // 验证账号

        if (userMapper.selectByName(user.getUsername()) != null){
            map.put("usernameMsg", "该账户名已经存在");
            return map;
        }

        //验证邮箱
        if (userMapper.selectByEmail(user.getEmail()) != null){
            map.put("emailMsg", "该邮箱已经存在");
            return map;
        }

        //注册用户
        user.setSalt(CommunityUtil.generateUUID().substring(0, 5));
        user.setPassword(CommunityUtil.md5(user.getPassword() + user.getSalt()) );
        user.setType(0); // 注册的用户默认为普通用户
        user.setStatus(0);  //没有激活
        user.setActivationCode(CommunityUtil.generateUUID());
        user.setHeaderUrl("https://img.moegirl.org.cn/common/e/ee/%E6%B3%89%E6%AD%A4%E6%96%B9a.jpeg");
        user.setCreateTime(new Date());
        userMapper.insertUser(user);

        //发送html激活邮件
        Context context = new Context();
        context.setVariable("email", user.getEmail());

        // http://localhost:5555/community/activation/101/code
        //  context.setVariable("url", );
        String url = domain + contextPath + "/activation/" + user.getId() + "/" + user.getActivationCode();
        context.setVariable("url", url);
        String content = templateEngine.process("/mail/activation", context);
        mailClient.sendMail(user.getEmail(), "激活账户", content);

        return map;
    }






}

