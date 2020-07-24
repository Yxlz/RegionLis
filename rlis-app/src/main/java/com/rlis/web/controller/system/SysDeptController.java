package com.rlis.web.controller.system;

import com.rlis.common.annotation.Log;
import com.rlis.common.constant.UserConstants;
import com.rlis.common.core.controller.BaseController;
import com.rlis.common.core.domain.AjaxResult;
import com.rlis.common.core.domain.Ztree;
import com.rlis.common.enums.BusinessType;
import com.rlis.common.utils.StringUtils;
import com.rlis.core.util.ShiroUtils;
import com.rlis.system.domain.RlSysOrg;
import com.rlis.system.domain.RlSysRole;
import com.rlis.system.service.IRlSysOrgService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: SysDeptController
 * @Description: 机构信息
 * @Author tangxiaohui
 * @Copyright: Copyright (c) 2020
 * @Company: 成都信通网易医疗科技发展有限公司
 * @DateTime 2020/7/10 15:27
 */
@Controller
@RequestMapping("/system/dept")
public class SysDeptController extends BaseController
{
    private String prefix = "system/dept";

    @Autowired
    private IRlSysOrgService deptService;

    @RequiresPermissions("system:dept:view")
    @GetMapping()
    public String dept()
    {
        return prefix + "/dept";
    }

    @RequiresPermissions("system:dept:list")
    @PostMapping("/list")
    @ResponseBody
    public List<RlSysOrg> list(RlSysOrg dept)
    {
        List<RlSysOrg> deptList = deptService.selectOrgList(dept);
        return deptList;
    }

    /**
     * 新增机构
     */
    @GetMapping("/add/{parentId}")
    public String add(@PathVariable("parentId") Long parentId, ModelMap mmap)
    {
        mmap.put("dept", deptService.selectOrgById(parentId));
        return prefix + "/add";
    }

    /**
     * 新增保存机构
     */
    @Log(title = "机构管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:dept:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated RlSysOrg dept)
    {
        if (UserConstants.DEPT_NAME_NOT_UNIQUE.equals(deptService.checkOrgNameUnique(dept)))
        {
            return error("新增机构'" + dept.getOrgName() + "'失败，机构名称已存在");
        }
        dept.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(deptService.insertOrg(dept));
    }

    /**
     * 修改
     */
    @GetMapping("/edit/{deptId}")
    public String edit(@PathVariable("deptId") Long deptId, ModelMap mmap)
    {
        RlSysOrg dept = deptService.selectOrgById(deptId);
        if (StringUtils.isNotNull(dept) && 100L == deptId)
        {
            dept.setParentName("无");
        }
        mmap.put("dept", dept);
        return prefix + "/edit";
    }

    /**
     * 保存
     */
    @Log(title = "机构管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:dept:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated RlSysOrg dept)
    {
        if (UserConstants.DEPT_NAME_NOT_UNIQUE.equals(deptService.checkOrgNameUnique(dept)))
        {
            return error("修改机构'" + dept.getOrgName() + "'失败，机构名称已存在");
        }
        else if (dept.getParentId().equals(dept.getOrgId()))
        {
            return error("修改机构'" + dept.getOrgName() + "'失败，上级机构不能是自己");
        }
        else if (StringUtils.equals(UserConstants.DEPT_DISABLE, dept.getStatus())
                && deptService.selectNormalChildrenOrgById(dept.getOrgId()) > 0)
        {
            return AjaxResult.error("该机构包含未停用的子机构！");
        }
        dept.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(deptService.updateOrg(dept));
    }

    /**
     * 删除
     */
    @Log(title = "机构管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:dept:remove")
    @GetMapping("/remove/{deptId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("deptId") Long deptId)
    {
        if (deptService.selectOrgCount(deptId) > 0)
        {
            return AjaxResult.warn("存在下级机构,不允许删除");
        }
        if (deptService.checkOrgExistUser(deptId))
        {
            return AjaxResult.warn("机构存在用户,不允许删除");
        }
        return toAjax(deptService.deleteOrgById(deptId));
    }

    /**
     * 校验机构名称
     */
    @PostMapping("/checkDeptNameUnique")
    @ResponseBody
    public String checkDeptNameUnique(RlSysOrg dept)
    {
        return deptService.checkOrgNameUnique(dept);
    }

    /**
     * 选择机构树
     * 
     * @param deptId 机构ID
     * @param excludeId 排除ID
     */
    @GetMapping(value = { "/selectDeptTree/{deptId}", "/selectDeptTree/{deptId}/{excludeId}" })
    public String selectDeptTree(@PathVariable("deptId") Long deptId,
            @PathVariable(value = "excludeId", required = false) String excludeId, ModelMap mmap)
    {
        mmap.put("dept", deptService.selectOrgById(deptId));
        mmap.put("excludeId", excludeId);
        return prefix + "/tree";
    }

    /**
     * 加载机构列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = deptService.selectOrgTree(new RlSysOrg());
        return ztrees;
    }

    /**
     * 加载机构列表树（排除下级）
     */
    @GetMapping("/treeData/{excludeId}")
    @ResponseBody
    public List<Ztree> treeDataExcludeChild(@PathVariable(value = "excludeId", required = false) Long excludeId)
    {
        RlSysOrg dept = new RlSysOrg();
        dept.setOrgId(excludeId);
        List<Ztree> ztrees = deptService.selectOrgTreeExcludeChild(dept);
        return ztrees;
    }

    /**
     * 加载角色机构（数据权限）列表树
     */
    @GetMapping("/roleDeptTreeData")
    @ResponseBody
    public List<Ztree> deptTreeData(RlSysRole role)
    {
        List<Ztree> ztrees = deptService.roleOrgTreeData(role);
        return ztrees;
    }
}
