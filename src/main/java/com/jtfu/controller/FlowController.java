package com.jtfu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jtfu.entity.Branch;
import com.jtfu.entity.Flow;
import com.jtfu.entity.LayUiObj;
import com.jtfu.entity.User;
import com.jtfu.eunm.FlowType;
import com.jtfu.eunm.RoleEnum;
import com.jtfu.eunm.SexEnum;
import com.jtfu.eunm.StatusEnum;
import com.jtfu.service.IFlowService;
import com.jtfu.service.IUserService;
import com.jtfu.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jtfu
 * @since 2019-10-18
 */
@Controller
@RequestMapping("/flow")
public class FlowController {

    @Autowired
    IFlowService flowService;

    @Autowired
    IUserService userService;


    @GetMapping("/flowList.html")
    public String flowListHtml(Model model){
        model.addAttribute("users",userService.list());
        return "flowList";
    }



    @ResponseBody
    @GetMapping("/getFlowList")
    public LayUiObj getFlowList(@RequestParam("page") int current, @RequestParam("limit") int limit){
        LayUiObj layUiObj=new LayUiObj();
        QueryWrapper queryWrapper=new QueryWrapper();
        List<Object> flows=flowService.list(queryWrapper);
        Page page=new Page(current,limit);
        IPage<Flow> flowIPage=flowService.page(page,queryWrapper);
        //System.out.println(userIPage);
        layUiObj.setCode(0);
        layUiObj.setData(flowIPage);
        layUiObj.setMsg("查询成功");
        layUiObj.setCount(flows.size());
        return layUiObj;
    }

    @ResponseBody
    @GetMapping("/selectFlowById")
    public R selectFlowById(@RequestParam int id){
        R r=R.success();
        r.set("flow",flowService.getById(id));
        return r;
    }

    @PostMapping("/updateFlow")
    @ResponseBody
    public R updateUser(@RequestParam Integer id,@RequestParam String name, @RequestParam String userName, @RequestParam int userId,
                         @RequestParam int type){
        if(id!=null){
            Flow flow =flowService.getById(id);
            if(flow!=null){
               flow.setName(name);
               flow.setUserId(userId);
               flow.setUserName(userName);
               flow.setType(FlowType.getLabelByValue(type));
               flowService.updateById(flow);
            }
        }else{
            Flow flow=new Flow();
            flow.setName(name);
            flow.setUserId(userId);
            flow.setUserName(userName);
            flow.setType(FlowType.getLabelByValue(type));
            flowService.save(flow);
        }
        return R.success();
    }

    @PostMapping("/deleteFlow")
    @ResponseBody
    public R deleteFlow(@RequestParam Integer id){
        if(id!=null){
            Flow flow =flowService.getById(id);
            if(flow!=null){
                //user.setDelFlag(1);
                flowService.removeById(id);
            }
        }
        return R.success();
    }
}
