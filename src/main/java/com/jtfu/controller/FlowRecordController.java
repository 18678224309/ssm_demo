package com.jtfu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jtfu.entity.FlowRecord;
import com.jtfu.entity.LayUiObj;
import com.jtfu.entity.User;
import com.jtfu.service.IFlowRecordService;
import com.jtfu.service.IFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
@RequestMapping("/flow-record")
public class FlowRecordController {

    @Autowired
    IFlowRecordService recordService;

    @Autowired
    IFlowService flowService;



    @GetMapping("/flowAdmin.html")
    public String flowAdminHtml(Model model,HttpServletRequest request){
        model.addAttribute("userModel",request.getSession().getAttribute("user"));
        model.addAttribute("flows",flowService.list());
        return "flowAdmin";
    }



    @GetMapping("/getFlowRecordList")
    @ResponseBody
    public LayUiObj getFlowRecordList(@RequestParam("page") int current, @RequestParam("limit") int limit, @RequestParam String type, HttpServletRequest request){
        LayUiObj layUiObj=new LayUiObj();
        QueryWrapper queryWrapper=new QueryWrapper();
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");
        if(user!=null){
            if(type!=null){
                if(type.equals("my")){
                    queryWrapper.eq("initiator",user.getId());
                }else{
                    queryWrapper.eq("curr_ex",user.getId());
                }
                List<FlowRecord> lists=recordService.list();
                Page page=new Page(current,limit);
                IPage<FlowRecord> Ipage=recordService.page(page,queryWrapper);
                layUiObj.setCount(lists.size());
                layUiObj.setData(Ipage);
                layUiObj.setCode(0);
                layUiObj.setMsg("查询成功");
            }
        }else{
            layUiObj.setCode(0);
            layUiObj.setMsg("");
            layUiObj.setCount(0);
            layUiObj.setData(new Page());
            return layUiObj;
        }
        return  layUiObj;
    }

}
