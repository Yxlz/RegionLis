package com.rlis.web.controller.inspection;

import com.rlis.common.annotation.Log;
import com.rlis.common.core.controller.BaseController;
import com.rlis.common.core.domain.AjaxResult;
import com.rlis.common.core.page.TableDataInfo;
import com.rlis.common.enums.BusinessType;
import com.rlis.common.utils.DateUtils;
import com.rlis.common.utils.poi.ExcelUtil;
import com.rlis.core.util.ShiroUtils;
import com.rlis.inspection.domain.RlInspecRequisition;
import com.rlis.inspection.service.IRlInspecRequisitionService;
import com.rlis.system.domain.RlSysUser;
import com.rlis.system.service.IRlSysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * 检验申请Controller
 * 
 * @author tangxiaohui
 * @date 2020-07-23
 */
@Controller
@RequestMapping("/inspection/requisition")
public class RlInspecRequisitionController extends BaseController
{
    private String prefix = "inspection/requisition";

    @Resource
    private IRlInspecRequisitionService rlInspecRequisitionService;

    @Resource
    private IRlSysUserService rlSysUserService;

    @RequiresPermissions("inspection:requisition:view")
    @GetMapping()
    public String requisition()
    {
        return prefix + "/requisition";
    }

    /**
     * 查询检验申请列表
     */
    @RequiresPermissions("inspection:requisition:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(RlInspecRequisition rlInspecRequisition)
    {
        startPage();
        List<RlInspecRequisition> list = rlInspecRequisitionService.selectRlInspecRequisitionList(rlInspecRequisition);
        return getDataTable(list);
    }

    /**
     * 导出检验申请列表
     */
    @RequiresPermissions("inspection:requisition:export")
    @Log(title = "检验申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(RlInspecRequisition rlInspecRequisition)
    {
        List<RlInspecRequisition> list = rlInspecRequisitionService.selectRlInspecRequisitionList(rlInspecRequisition);
        ExcelUtil<RlInspecRequisition> util = new ExcelUtil<RlInspecRequisition>(RlInspecRequisition.class);
        return util.exportExcel(list, "requisition");
    }

    /**
     * 新增检验申请
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存检验申请
     */
    @RequiresPermissions("inspection:requisition:add")
    @Log(title = "检验申请", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(RlInspecRequisition rlInspecRequisition)
    {   rlInspecRequisition.setCreateTime(DateUtils.getNowDate());
        rlInspecRequisition.setId(UUID.randomUUID().toString().replace("-",""));
        rlInspecRequisition.setCreateBy(ShiroUtils.getLoginName());
        RlSysUser user = rlSysUserService.selectUserByLoginName(ShiroUtils.getLoginName());
        rlInspecRequisition.setAppDocCode(user.getLoginName());
        rlInspecRequisition.setAppDocName(user.getUserName());
        rlInspecRequisition.setAppOrgCode(user.getOrg().getOrgId()+"");
        rlInspecRequisition.setAppOrgName(user.getOrg().getOrgName());
        rlInspecRequisition.setAppOrgPhone(user.getOrg().getPhone());
        rlInspecRequisition.setAppType("REGIONLIS");
        rlInspecRequisition.setAppTime(DateUtils.getTime());
        rlInspecRequisition.setAppNo(DateUtils.dateTimeNow()+new Random().nextInt(9999));
        return toAjax(rlInspecRequisitionService.insertRlInspecRequisition(rlInspecRequisition));
    }

    /**
     * 修改检验申请
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        RlInspecRequisition rlInspecRequisition = rlInspecRequisitionService.selectRlInspecRequisitionById(id);
        mmap.put("rlInspecRequisition", rlInspecRequisition);
        return prefix + "/edit";
    }

    /**
     * 修改保存检验申请
     */
    @RequiresPermissions("inspection:requisition:edit")
    @Log(title = "检验申请", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(RlInspecRequisition rlInspecRequisition)
    {
        return toAjax(rlInspecRequisitionService.updateRlInspecRequisition(rlInspecRequisition));
    }

    /**
     * 删除检验申请
     */
    @RequiresPermissions("inspection:requisition:remove")
    @Log(title = "检验申请", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(rlInspecRequisitionService.deleteRlInspecRequisitionByIds(ids));
    }
}
