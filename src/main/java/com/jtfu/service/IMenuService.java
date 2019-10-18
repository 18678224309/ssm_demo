package com.jtfu.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.jtfu.entity.Menu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jtfu
 * @since 2019-10-17
 */
public interface IMenuService extends IService<Menu> {

        public List<Menu> getMenuAll();
}
