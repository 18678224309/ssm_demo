package com.jtfu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jtfu.entity.Branch;
import com.jtfu.entity.LayUiObj;
import com.jtfu.entity.User;
import com.jtfu.eunm.RoleEnum;
import com.jtfu.eunm.SexEnum;
import com.jtfu.eunm.StatusEnum;
import com.jtfu.mapper.UserMapper;
import com.jtfu.service.IBranchService;
import com.jtfu.service.IUserService;
import com.jtfu.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.Socket;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jtfu
 * @since 2019-10-16
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @Autowired
    IBranchService branchService;


    @GetMapping("/userList.html")
    public String userListHtml(Model model){
        model.addAttribute("branchs",branchService.list());
        return "userList";
    }

    @GetMapping("/getUserList")
    @ResponseBody
    public LayUiObj userList(@RequestParam("page") int current,@RequestParam("limit") int limit){
        LayUiObj layUiObj=new LayUiObj();
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("del_flag",0);
        queryWrapper.notIn("status",4);
        List<Object> users=userService.list(queryWrapper);
        Page page=new Page(current,limit);
        IPage<User> userIPage=userService.page(page,queryWrapper);
        //System.out.println(userIPage);
        layUiObj.setCode(0);
        layUiObj.setData(userIPage);
        layUiObj.setMsg("查询成功");
        layUiObj.setCount(users.size());
        return layUiObj;
    }

    @PostMapping("/updateUser")
    @ResponseBody
    public R updateUser(@RequestParam Integer id,@RequestParam String username, @RequestParam String name, @RequestParam int age, @RequestParam int sex, @RequestParam int roleId,
        @RequestParam int branchId, @RequestParam double pay){
        if(id!=null){
            User user =userService.getById(id);
            if(user!=null){
                user.setUsername(username);
                user.setName(name);
                user.setAge(age);
                SexEnum sexEnum=SexEnum.getLabelByValue(sex);
                user.setSex(sexEnum);
                user.setRoleId(RoleEnum.getLabelByValue(roleId));
                Branch branch= branchService.getById(branchId);
                user.setBranchId(branch.getId());
                user.setBranchName(branch.getName());
                user.setPay(pay);
                userService.updateById(user);
            }
        }else{
            User user1=new User();
            user1.setName(name);
            user1.setUsername(username);
            user1.setAge(age);
            SexEnum sexEnum=SexEnum.getLabelByValue(sex);
            user1.setSex(sexEnum);
            user1.setPassword("123456");
            user1.setRoleId(RoleEnum.getLabelByValue(roleId));
            Branch branch= branchService.getById(branchId);
            user1.setBranchId(branch.getId());
            user1.setBranchName(branch.getName());
            user1.setPay(pay);
            user1.setStatus(StatusEnum.SXQ);
            userService.save(user1);
        }
        return R.success();
    }

    @PostMapping("/deleteUser")
    @ResponseBody
    public R deleteUser(@RequestParam Integer id){
        if(id!=null){
            User user=userService.getById(id);
            if(user!=null){
                //user.setDelFlag(1);
                userService.removeById(id);
            }
        }
        return R.success();
    }
}
