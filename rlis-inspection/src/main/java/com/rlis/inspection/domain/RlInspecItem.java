package com.rlis.inspection.domain;

import com.rlis.common.annotation.Excel;
import com.rlis.common.annotation.Excels;
import com.rlis.common.core.domain.BaseEntity;
import com.rlis.system.domain.RlSysOrg;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 检验项目对象 RL_INSPEC_ITEM
 *
 * @author tangxiaohui
 * @date 2020-07-14
 */
public class RlInspecItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 项目ID 主键 */
    private String itemId;

    /** 项目代码 */
    @Excel(name = "项目代码")
    private String itemCode;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String itemName;

    /** 助记码 */
    private String mnemonicCode;

    /** 机构ID */
    private Long orgId;

    /** 1-启用;0-停用 */
    @Excel(name = "状态" , readConverterExp = "0=停用,1=使用")
    private String enable;

    /** 项目价格 */
    @Excel(name = "项目价格")
    private BigDecimal itemPrice;

    /** 对码项目ID */
    private String fixItemId;

    /** 机构对象 */
    @Excels({
            @Excel(name = "机构名称", targetAttr = "orgName", type = Excel.Type.EXPORT),
            @Excel(name = "机构负责人", targetAttr = "leader", type = Excel.Type.EXPORT),
            @Excel(name = "项目合同折扣", targetAttr = "discount", type = Excel.Type.EXPORT)
    })
    private RlSysOrg org;

    /** 标准项目 */
    @Excels({
            @Excel(name = "标准项目代码", targetAttr = "itemCode", type = Excel.Type.EXPORT),
            @Excel(name = "标准项目名称", targetAttr = "itemName", type = Excel.Type.EXPORT),
            @Excel(name = "标准项目价格", targetAttr = "itemPrice", type = Excel.Type.EXPORT)
    })
    private RlInspecItem fixItem;

    public String getFixItemId() {
        return fixItemId;
    }

    public void setFixItemId(String fixItemId) {
        this.fixItemId = fixItemId;
    }

    public RlSysOrg getOrg() {
        return org;
    }

    public void setOrg(RlSysOrg org) {
        this.org = org;
    }

    public RlInspecItem getFixItem() {
        return fixItem;
    }

    public void setFixItem(RlInspecItem fixItem) {
        this.fixItem = fixItem;
    }

    public void setItemId(String itemId)
    {
        this.itemId = itemId;
    }

    public String getItemId()
    {
        return itemId;
    }
    public void setItemCode(String itemCode)
    {
        this.itemCode = itemCode;
    }

    public String getItemCode()
    {
        return itemCode;
    }
    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }

    public String getItemName()
    {
        return itemName;
    }
    public void setMnemonicCode(String mnemonicCode)
    {
        this.mnemonicCode = mnemonicCode;
    }

    public String getMnemonicCode()
    {
        return mnemonicCode;
    }
    public void setOrgId(Long orgId)
    {
        this.orgId = orgId;
    }

    public Long getOrgId()
    {
        return orgId;
    }
    public void setEnable(String enable)
    {
        this.enable = enable;
    }

    public String getEnable()
    {
        return enable;
    }
    public void setItemPrice(BigDecimal itemPrice)
    {
        this.itemPrice = itemPrice;
    }

    public BigDecimal getItemPrice()
    {
        return itemPrice;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("itemId", getItemId())
                .append("itemCode", getItemCode())
                .append("itemName", getItemName())
                .append("mnemonicCode", getMnemonicCode())
                .append("orgId", getOrgId())
                .append("enable", getEnable())
                .append("itemPrice", getItemPrice())
                .append("remark", getRemark())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}