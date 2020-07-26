package com.rlis.inspection.domain;

import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.rlis.common.annotation.Excel;
import com.rlis.common.core.domain.BaseEntity;

/**
 * 检验申请对象 RL_INSPEC_REQUISITION
 * 
 * @author tangxiaohui
 * @date 2020-07-23
 */
public class RlInspecRequisition extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;

    /** 申请单号 */
    @Excel(name = "申请单号")
    private String appNo;

    /** 申请单类型 */
    @Excel(name = "申请单类型")
    private String appType;

    /** 申请时间 */
    @Excel(name = "申请时间")
    private String appTime;

    /** 状态 */
    @Excel(name = "状态", readConverterExp = "0=编辑,1=提交,2=未知")
    private Integer appStatus;

    /** 申请机构编码 */
    private String appOrgCode;

    /** 申请机构名称 */
    @Excel(name = "申请机构名称")
    private String appOrgName;

    /** 申请医生编码 */
    private String appDocCode;

    /** 申请医生名称 */
    @Excel(name = "申请医生名称")
    private String appDocName;

    /** 申请机构电话 */
    private String appOrgPhone;

    /** 申请单总金额 */
    @Excel(name = "申请项目总金额")
    private BigDecimal appAmount;

    /** 服务机构编码 */
    private String serviceOrgCode;

    /** 服务机构名称 */
    @Excel(name = "服务机构名称")
    private String serviceOrgName;

    /** 患者ID */
    private String patientId;

    /** 患者姓名 */
    @Excel(name = "患者姓名")
    private String patientName;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String patientIdno;

    /** 年龄 */
    @Excel(name = "年龄")
    private String patientAge;

    /** 性别 */
    @Excel(name = "性别", readConverterExp = "0=男,1=女,2=未知")
    private String patientSex;

    /** 电话 */
    @Excel(name = "电话")
    private String patientPhone;

    /** 就诊类型 */
    @Excel(name = "就诊类型", readConverterExp = "IP=住院,OP=门诊")
    private String patientType;

    /** 就诊号 */
    @Excel(name = "就诊号")
    private String patientCode;

    /** 初步诊断 */
    @Excel(name = "初步诊断")
    private String patientDiagnosis;

    /** 检验申请项目信息 */
    private List<RlInspecRequisitionItems> rlInspecRequisitionItemsList;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setAppNo(String appNo) 
    {
        this.appNo = appNo;
    }

    public String getAppNo() 
    {
        return appNo;
    }
    public void setAppType(String appType) 
    {
        this.appType = appType;
    }

    public String getAppType() 
    {
        return appType;
    }
    public void setAppTime(String appTime) 
    {
        this.appTime = appTime;
    }

    public String getAppTime() 
    {
        return appTime;
    }
    public void setAppStatus(Integer appStatus) 
    {
        this.appStatus = appStatus;
    }

    public Integer getAppStatus() 
    {
        return appStatus;
    }
    public void setAppOrgCode(String appOrgCode) 
    {
        this.appOrgCode = appOrgCode;
    }

    public String getAppOrgCode() 
    {
        return appOrgCode;
    }
    public void setAppOrgName(String appOrgName) 
    {
        this.appOrgName = appOrgName;
    }

    public String getAppOrgName() 
    {
        return appOrgName;
    }
    public void setAppDocCode(String appDocCode) 
    {
        this.appDocCode = appDocCode;
    }

    public String getAppDocCode() 
    {
        return appDocCode;
    }
    public void setAppDocName(String appDocName) 
    {
        this.appDocName = appDocName;
    }

    public String getAppDocName() 
    {
        return appDocName;
    }
    public void setAppOrgPhone(String appOrgPhone) 
    {
        this.appOrgPhone = appOrgPhone;
    }

    public BigDecimal getAppAmount() {
        return appAmount;
    }

    public void setAppAmount(BigDecimal appAmount) {
        this.appAmount = appAmount;
    }

    public String getAppOrgPhone()
    {
        return appOrgPhone;
    }
    public void setServiceOrgCode(String serviceOrgCode) 
    {
        this.serviceOrgCode = serviceOrgCode;
    }

    public String getServiceOrgCode() 
    {
        return serviceOrgCode;
    }
    public void setServiceOrgName(String serviceOrgName) 
    {
        this.serviceOrgName = serviceOrgName;
    }

    public String getServiceOrgName() 
    {
        return serviceOrgName;
    }
    public void setPatientId(String patientId) 
    {
        this.patientId = patientId;
    }

    public String getPatientId() 
    {
        return patientId;
    }
    public void setPatientName(String patientName) 
    {
        this.patientName = patientName;
    }

    public String getPatientName() 
    {
        return patientName;
    }
    public void setPatientIdno(String patientIdno) 
    {
        this.patientIdno = patientIdno;
    }

    public String getPatientIdno() 
    {
        return patientIdno;
    }
    public void setPatientAge(String patientAge) 
    {
        this.patientAge = patientAge;
    }

    public String getPatientAge() 
    {
        return patientAge;
    }
    public void setPatientSex(String patientSex) 
    {
        this.patientSex = patientSex;
    }

    public String getPatientSex() 
    {
        return patientSex;
    }
    public void setPatientPhone(String patientPhone) 
    {
        this.patientPhone = patientPhone;
    }

    public String getPatientPhone() 
    {
        return patientPhone;
    }
    public void setPatientType(String patientType) 
    {
        this.patientType = patientType;
    }

    public String getPatientType() 
    {
        return patientType;
    }
    public void setPatientCode(String patientCode) 
    {
        this.patientCode = patientCode;
    }

    public String getPatientCode() 
    {
        return patientCode;
    }
    public void setPatientDiagnosis(String patientDiagnosis) 
    {
        this.patientDiagnosis = patientDiagnosis;
    }

    public String getPatientDiagnosis() 
    {
        return patientDiagnosis;
    }

    public List<RlInspecRequisitionItems> getRlInspecRequisitionItemsList()
    {
        return rlInspecRequisitionItemsList;
    }

    public void setRlInspecRequisitionItemsList(List<RlInspecRequisitionItems> rlInspecRequisitionItemsList)
    {
        this.rlInspecRequisitionItemsList = rlInspecRequisitionItemsList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("appNo", getAppNo())
            .append("appType", getAppType())
            .append("appTime", getAppTime())
            .append("appStatus", getAppStatus())
            .append("appOrgCode", getAppOrgCode())
            .append("appOrgName", getAppOrgName())
            .append("appDocCode", getAppDocCode())
            .append("appDocName", getAppDocName())
            .append("appOrgPhone", getAppOrgPhone())
                .append("appAmount", getAppAmount())
            .append("serviceOrgCode", getServiceOrgCode())
            .append("serviceOrgName", getServiceOrgName())
            .append("patientId", getPatientId())
            .append("patientName", getPatientName())
            .append("patientIdno", getPatientIdno())
            .append("patientAge", getPatientAge())
            .append("patientSex", getPatientSex())
            .append("patientPhone", getPatientPhone())
            .append("patientType", getPatientType())
            .append("patientCode", getPatientCode())
            .append("patientDiagnosis", getPatientDiagnosis())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("rlInspecRequisitionItemsList", getRlInspecRequisitionItemsList())
            .toString();
    }
}
