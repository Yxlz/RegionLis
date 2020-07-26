package com.rlis.web.controller.inspection;

import com.rlis.common.annotation.Log;
import com.rlis.common.core.controller.BaseController;
import com.rlis.common.core.domain.AjaxResult;
import com.rlis.common.core.page.PageDomain;
import com.rlis.common.core.page.TableDataInfo;
import com.rlis.common.core.page.TableSupport;
import com.rlis.common.enums.BusinessType;
import com.rlis.common.utils.DateUtils;
import com.rlis.common.utils.UUIDGenerator;
import com.rlis.common.utils.poi.ExcelUtil;
import com.rlis.core.util.ShiroUtils;
import com.rlis.inspection.domain.RlInspecRequisition;
import com.rlis.inspection.domain.RlInspecRequisitionBarcode;
import com.rlis.inspection.service.IRlInspecRequisitionBarcodeService;
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

    @Resource
    private IRlInspecRequisitionBarcodeService barcodeService;

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
        ExcelUtil<RlInspecRequisition> util = new ExcelUtil<>(RlInspecRequisition.class);
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
        rlInspecRequisition.setId(UUIDGenerator.getUUID());
        rlInspecRequisition.setCreateBy(ShiroUtils.getLoginName());
        RlSysUser user = rlSysUserService.selectUserByLoginName(ShiroUtils.getLoginName());
        rlInspecRequisition.setAppDocCode(user.getLoginName());
        rlInspecRequisition.setAppDocName(user.getUserName());
        rlInspecRequisition.setAppOrgCode(user.getOrg().getOrgId()+"");
        rlInspecRequisition.setAppOrgName(user.getOrg().getOrgName());
        rlInspecRequisition.setAppOrgPhone(user.getOrg().getPhone());
        rlInspecRequisition.setPatientId(rlInspecRequisition.getPatientIdno());
        rlInspecRequisition.setAppStatus(0);
        rlInspecRequisition.setPatientCode(DateUtils.dateTime()+new Random().nextInt(9999));
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
     * 修改检验申请
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id, ModelMap mmap)
    {
        RlInspecRequisition rlInspecRequisition = rlInspecRequisitionService.selectRlInspecRequisitionById(id);
        mmap.put("rlInspecRequisition", rlInspecRequisition);
        return prefix + "/detail";
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
        rlInspecRequisition.setUpdateBy(ShiroUtils.getLoginName());
        rlInspecRequisition.setUpdateTime(DateUtils.getNowDate());
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

    /**
     * 提交检验申请（打印条码）
     */
    @RequiresPermissions("inspection:requisition:edit")
    @Log(title = "提交申请", businessType = BusinessType.UPDATE)
    @PostMapping("/commit")
    @ResponseBody
    public AjaxResult commit(String id)
    {
        RlInspecRequisition rlInspecRequisition = rlInspecRequisitionService.selectRlInspecRequisitionById(id);
        try {
            rlInspecRequisitionService.commitRequisition(rlInspecRequisition);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success();
//        return toAjax(rlInspecRequisitionService.updateRlInspecRequisition(rlInspecRequisition));
    }

    /**
     * 查询条码
     */
    @PostMapping("/barcode")
    @ResponseBody
    public TableDataInfo list(RlInspecRequisitionBarcode barcode)
    {
        TableDataInfo rspData = new TableDataInfo();
        List<RlInspecRequisitionBarcode> barcodeList = barcodeService.getBarcodesByRequisitionId(barcode.getRequisitionId());
        PageDomain pageDomain = TableSupport.buildPageRequest();
        if (null == pageDomain.getPageNum() || null == pageDomain.getPageSize())
        {
            rspData.setRows(barcodeList);
            rspData.setTotal(barcodeList.size());
            return rspData;
        }
        Integer pageNum = (pageDomain.getPageNum() - 1) * 10;
        Integer pageSize = pageDomain.getPageNum() * 10;
        if (pageSize > barcodeList.size())
        {
            pageSize = barcodeList.size();
        }
        rspData.setRows(barcodeList.subList(pageNum, pageSize));
        rspData.setTotal(barcodeList.size());
        return rspData;
    }
}
