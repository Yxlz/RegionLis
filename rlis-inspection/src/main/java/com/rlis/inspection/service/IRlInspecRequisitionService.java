package com.rlis.inspection.service;

import java.util.List;

import com.rlis.common.exception.barcode.GetLisBarCodeFailureException;
import com.rlis.inspection.domain.RlInspecRequisition;

/**
 * 检验申请Service接口
 * 
 * @author tangxiaohui
 * @date 2020-07-23
 */
public interface IRlInspecRequisitionService 
{
    /**
     * 查询检验申请
     * 
     * @param id 检验申请ID
     * @return 检验申请
     */
    public RlInspecRequisition selectRlInspecRequisitionById(String id);

    /**
     * 查询检验申请列表
     * 
     * @param rlInspecRequisition 检验申请
     * @return 检验申请集合
     */
    public List<RlInspecRequisition> selectRlInspecRequisitionList(RlInspecRequisition rlInspecRequisition);

    /**
     * 新增检验申请
     * 
     * @param rlInspecRequisition 检验申请
     * @return 结果
     */
    public int insertRlInspecRequisition(RlInspecRequisition rlInspecRequisition);

    /**
     * 修改检验申请
     * 
     * @param rlInspecRequisition 检验申请
     * @return 结果
     */
    public int updateRlInspecRequisition(RlInspecRequisition rlInspecRequisition);

    /**
     * 批量删除检验申请
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRlInspecRequisitionByIds(String ids);

    /**
     * 删除检验申请信息
     * 
     * @param id 检验申请ID
     * @return 结果
     */
    public int deleteRlInspecRequisitionById(String id);

    /**
     * 提交检验申请信息（打院内条码）
     *
     * @param rlInspecRequisition 检验申请
     */
    void commitRequisition(RlInspecRequisition rlInspecRequisition) throws GetLisBarCodeFailureException, Exception;
}
