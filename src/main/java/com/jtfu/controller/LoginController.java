package com.jtfu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jtfu.entity.User;
import com.jtfu.mapper.UserMapper;
import com.jtfu.service.IMenuService;
import com.jtfu.service.impl.MenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    UserMapper userMapper;
    @Autowired
    IMenuService menuService;

    @GetMapping("/login.html")
    public String home(){
        return "login";
    }


    @PostMapping("/login")
    @ResponseBody
    public Map login(@RequestParam String username, @RequestParam String password, HttpServletRequest request){
        /*System.out.println(username+"=-=====================");*/
        QueryWrapper queryWrapper=new QueryWrapper();
        Map map=new HashMap();
        queryWrapper.eq("username",username);
        queryWrapper.eq("password",password);
        User user=userMapper.selectOne(queryWrapper);
        if(user!=null){
            map.put("msg","success");
            request.getSession().setAttribute("user",user);
        }else{
            map.put("msg","用户不存在或密码错误!");
        }
        return map;
    }

    @GetMapping("/home.html")
    public String index(Model model){
       /* List<Map<String,Object>> list=new ArrayList<Map<String, Object>>();
        Map map=new HashMap();
        map.put("1","菜单1");
        map.put("2","菜单2");
        map.put("3","菜单3");
        map.put("4","菜单4");
        list.add(map);
        model.addAttribute("menus",map);*/
        model.addAttribute("menus",menuService.getMenuAll());
        return "/home";
    }
}
