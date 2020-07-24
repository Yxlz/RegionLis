package com.rlis.inspection.mapper;

import java.util.List;
import com.rlis.inspection.domain.RlInspecRequisition;
import com.rlis.inspection.domain.RlInspecRequisitionItems;

/**
 * 检验申请Mapper接口
 * 
 * @author tangxiaohui
 * @date 2020-07-23
 */
public interface RlInspecRequisitionMapper 
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
     * 删除检验申请
     * 
     * @param id 检验申请ID
     * @return 结果
     */
    public int deleteRlInspecRequisitionById(String id);

    /**
     * 批量删除检验申请
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRlInspecRequisitionByIds(String[] ids);

    /**
     * 批量删除检验申请项目
     * 
     * @param customerIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteRlInspecRequisitionItemsByRequisitionIds(String[] ids);
    
    /**
     * 批量新增检验申请项目
     * 
     * @param rlInspecRequisitionItemsList 检验申请项目列表
     * @return 结果
     */
    public int batchRlInspecRequisitionItems(List<RlInspecRequisitionItems> rlInspecRequisitionItemsList);
    

    /**
     * 通过检验申请ID删除检验申请项目信息
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    public int deleteRlInspecRequisitionItemsByRequisitionId(String id);
}
