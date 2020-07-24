package com.rlis.web.controller.system;

import com.rlis.common.config.Global;
import com.rlis.common.core.controller.BaseController;
import com.rlis.core.util.ShiroUtils;
import com.rlis.system.domain.RlSysMenu;
import com.rlis.system.domain.RlSysUser;
import com.rlis.system.service.IRlSysConfigService;
import com.rlis.system.service.IRlSysMenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: SysIndexController
 * @Description: 首页 业务处理
 * @Author tangxiaohui
 * @Copyright: Copyright (c) 2020
 * @Company: 成都信通网易医疗科技发展有限公司
 * @DateTime 2020/7/10 15:20
 */
@Controller
public class SysIndexController extends BaseController
{
    @Resource
    private IRlSysMenuService menuService;

    @Resource
    private IRlSysConfigService configService;

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        RlSysUser user = ShiroUtils.getSysUser();
        // 根据用户id取出菜单
        List<RlSysMenu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("sideTheme", configService.selectConfigByKey("sys.index.sideTheme"));
        mmap.put("skinName", configService.selectConfigByKey("sys.index.skinName"));
        mmap.put("copyrightYear", Global.getCopyrightYear());
        mmap.put("demoEnabled", Global.isDemoEnabled());
        return "index";
    }

    // 切换主题
    @GetMapping("/system/switchSkin")
    public String switchSkin(ModelMap mmap)
    {
        return "skin";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap)
    {
        mmap.put("version", Global.getVersion());
        return "main_v2";
    }
}
