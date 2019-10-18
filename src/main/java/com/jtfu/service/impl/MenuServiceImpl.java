package com.jtfu.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jtfu.entity.Menu;
import com.jtfu.mapper.MenuMapper;
import com.jtfu.service.IMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jtfu
 * @since 2019-10-17
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    public List<Menu> getMenuAll() {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("parent_id",0);
        List<Menu> menus=list(queryWrapper);
        recursionMenus(menus);
        //System.out.println(menus);
        return menus;
    }

    //递归调用查询子菜单
    public void recursionMenus(List<Menu> menus){
        for(int i=0;i<menus.size();i++){
            Menu menu=menus.get(i);
            QueryWrapper queryWrapper=new QueryWrapper();
            queryWrapper.eq("parent_id",menu.getId());
            List<Menu> chirMenus=list(queryWrapper);
            if(chirMenus!=null&&chirMenus.size()!=0){
                menu.setChildren(chirMenus);
                recursionMenus(chirMenus);
            }
        }
    }
}
