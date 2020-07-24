package com.rlis.web.controller.inspection;

import com.rlis.common.annotation.Log;
import com.rlis.common.config.Global;
import com.rlis.common.core.controller.BaseController;
import com.rlis.common.core.domain.AjaxResult;
import com.rlis.common.core.page.TableDataInfo;
import com.rlis.common.enums.BusinessType;
import com.rlis.common.utils.DateUtils;
import com.rlis.common.utils.poi.ExcelUtil;
import com.rlis.core.util.ShiroUtils;
import com.rlis.inspection.domain.RlInspecItem;
import com.rlis.inspection.service.IRlInspecItemService;
import com.rlis.system.domain.RlSysUser;
import com.rlis.system.service.IRlSysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * 检验项目Controller
 *
 * @author tangxiaohui
 * @date 2020-07-14
 */
@Controller
@RequestMapping("/inspection/item")
public class RlInspecItemController extends BaseController
{
    private String prefix = "inspection/item";

    @Resource
    private IRlInspecItemService rlInspecItemService;

    @Resource
    private IRlSysUserService rlSysUserService;

    @RequiresPermissions("inspection:item:view")
    @GetMapping()
    public String item()
    {
        return prefix + "/item";
    }

    /**
     * 查询检验项目列表
     */
    @RequiresPermissions("inspection:item:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(RlInspecItem rlInspecItem)
    {
        startPage();
        RlSysUser user = rlSysUserService.selectUserByLoginName(ShiroUtils.getLoginName());
        rlInspecItem.setOrgId(user.getOrgId());
        List<RlInspecItem> list = rlInspecItemService.selectRlInspecItemList(rlInspecItem);
        return getDataTable(list);
    }

    /**
     * 导出检验项目列表
     */
    @RequiresPermissions("inspection:item:export")
    @Log(title = "项目管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(RlInspecItem rlInspecItem)
    {
        RlSysUser user = rlSysUserService.selectUserByLoginName(ShiroUtils.getLoginName());
        rlInspecItem.setOrgId(user.getOrgId());
        List<RlInspecItem> list = rlInspecItemService.selectRlInspecItemList(rlInspecItem);
        ExcelUtil<RlInspecItem> util = new ExcelUtil<>(RlInspecItem.class);
        return util.exportExcel(list, "item");
    }

    /**
     * 新增检验项目
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存检验项目
     */
    @RequiresPermissions("inspection:item:add")
    @Log(title = "项目管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(RlInspecItem rlInspecItem)
    {
        rlInspecItem.setCreateTime(DateUtils.getNowDate());
        rlInspecItem.setItemId(UUID.randomUUID().toString().replace("-", ""));
        rlInspecItem.setCreateBy(ShiroUtils.getLoginName());
        RlSysUser user = rlSysUserService.selectUserByLoginName(ShiroUtils.getLoginName());
        rlInspecItem.setOrgId(user.getOrgId());
        return toAjax(rlInspecItemService.insertRlInspecItem(rlInspecItem));
    }

    /**
     * 项目状态修改
     */
    @Log(title = "项目管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("inspection:item:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(RlInspecItem rlInspecItem)
    {
        rlInspecItem.setUpdateTime(DateUtils.getNowDate());
        rlInspecItem.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(rlInspecItemService.changeStatus(rlInspecItem));
    }

    /**
     * 修改检验项目
     */
    @GetMapping("/edit/{itemId}")
    public String edit(@PathVariable("itemId") String itemId, ModelMap mmap)
    {
        RlInspecItem rlInspecItem = rlInspecItemService.selectRlInspecItemById(itemId);
        mmap.put("rlInspecItem", rlInspecItem);
        return prefix + "/edit";
    }

    /**
     * 修改保存检验项目
     */
    @RequiresPermissions("inspection:item:edit")
    @Log(title = "项目管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(RlInspecItem rlInspecItem)
    {
        rlInspecItem.setUpdateTime(DateUtils.getNowDate());
        rlInspecItem.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(rlInspecItemService.updateRlInspecItem(rlInspecItem));
    }

    /**
     * 删除检验项目
     */
    @RequiresPermissions("inspection:item:remove")
    @Log(title = "项目管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(rlInspecItemService.deleteRlInspecItemByIds(ids));
    }

    /**
     * 检验项目对码
     */
    @GetMapping("/fixcode/{itemId}")
    public String fixcode(@PathVariable("itemId") String itemId, ModelMap mmap)
    {
        mmap.put("itemfixed", rlInspecItemService.selectRlInspecItemById(itemId));
        /*检验中心的标准项目*/
        RlInspecItem item = new RlInspecItem();
        item.setOrgId(Global.getOrgCenterId());
        mmap.put("standarditems", rlInspecItemService.selectRlInspecItemList(item));
        return prefix + "/fixcode";
    }

    /**
     * 获取检验中心检验项目数据
     */
    @GetMapping("/fixItemModel")
    @ResponseBody
    public AjaxResult fixItemModel()
    {
        AjaxResult ajax = new AjaxResult();
        /*检验中心的标准项目*/
        RlInspecItem item = new RlInspecItem();
        item.setOrgId(Global.getOrgCenterId());
        ajax.put("code", 200);
        ajax.put("value", rlInspecItemService.selectRlInspecItemList(item));
        return ajax;
    }
}
