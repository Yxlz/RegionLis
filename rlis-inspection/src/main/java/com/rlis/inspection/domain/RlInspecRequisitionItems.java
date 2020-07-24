package com.rlis.inspection.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.rlis.common.annotation.Excel;

import java.math.BigDecimal;

/**
 * 检验申请项目对象 RL_INSPEC_REQUISITION_ITEMS
 * 
 * @author tangxiaohui
 * @date 2020-07-23
 */
public class RlInspecRequisitionItems
{

    /** 主键ID */
    private String id;

    /** 申请单ID */
    @Excel(name = "申请单ID")
    private String requisitionId;

    /** 申请项目编码 */
    @Excel(name = "申请项目编码")
    private String itemCode;

    /** 申请项目名称 */
    @Excel(name = "申请项目名称")
    private String itemName;

    /** 项目价格 */
    @Excel(name = "项目价格")
    private BigDecimal itemPrice;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setRequisitionId(String requisitionId)
    {
        this.requisitionId = requisitionId;
    }

    public String getRequisitionId()
    {
        return requisitionId;
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
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("requisitionId", getRequisitionId())
                .append("itemCode", getItemCode())
                .append("itemName", getItemName())
                .append("itemPrice", getItemPrice())
                .toString();
    }
}
