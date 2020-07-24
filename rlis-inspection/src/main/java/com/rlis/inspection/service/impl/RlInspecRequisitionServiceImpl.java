package com.rlis.inspection.service.impl;

import com.rlis.common.core.text.Convert;
import com.rlis.common.utils.DateUtils;
import com.rlis.common.utils.StringUtils;
import com.rlis.inspection.domain.RlInspecRequisition;
import com.rlis.inspection.domain.RlInspecRequisitionItems;
import com.rlis.inspection.mapper.RlInspecRequisitionMapper;
import com.rlis.inspection.service.IRlInspecRequisitionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 检验申请Service业务层处理
 * 
 * @author tangxiaohui
 * @date 2020-07-23
 */
@Service
public class RlInspecRequisitionServiceImpl implements IRlInspecRequisitionService 
{
    @Resource
    private RlInspecRequisitionMapper rlInspecRequisitionMapper;

    /**
     * 查询检验申请
     * 
     * @param id 检验申请ID
     * @return 检验申请
     */
    @Override
    public RlInspecRequisition selectRlInspecRequisitionById(String id)
    {
        return rlInspecRequisitionMapper.selectRlInspecRequisitionById(id);
    }

    /**
     * 查询检验申请列表
     * 
     * @param rlInspecRequisition 检验申请
     * @return 检验申请
     */
    @Override
    public List<RlInspecRequisition> selectRlInspecRequisitionList(RlInspecRequisition rlInspecRequisition)
    {
        return rlInspecRequisitionMapper.selectRlInspecRequisitionList(rlInspecRequisition);
    }

    /**
     * 新增检验申请
     * 
     * @param rlInspecRequisition 检验申请
     * @return 结果
     */
    @Transactional
    @Override
    public int insertRlInspecRequisition(RlInspecRequisition rlInspecRequisition)
    {
        rlInspecRequisition.setCreateTime(DateUtils.getNowDate());
        int rows = rlInspecRequisitionMapper.insertRlInspecRequisition(rlInspecRequisition);
        insertRlInspecRequisitionItems(rlInspecRequisition);
        return rows;
    }

    /**
     * 修改检验申请
     * 
     * @param rlInspecRequisition 检验申请
     * @return 结果
     */
    @Transactional
    @Override
    public int updateRlInspecRequisition(RlInspecRequisition rlInspecRequisition)
    {
        rlInspecRequisition.setUpdateTime(DateUtils.getNowDate());
        rlInspecRequisitionMapper.deleteRlInspecRequisitionItemsByRequisitionId(rlInspecRequisition.getId());
        insertRlInspecRequisitionItems(rlInspecRequisition);
        return rlInspecRequisitionMapper.updateRlInspecRequisition(rlInspecRequisition);
    }

    /**
     * 删除检验申请对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteRlInspecRequisitionByIds(String ids)
    {
        rlInspecRequisitionMapper.deleteRlInspecRequisitionItemsByRequisitionIds(Convert.toStrArray(ids));
        return rlInspecRequisitionMapper.deleteRlInspecRequisitionByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除检验申请信息
     * 
     * @param id 检验申请ID
     * @return 结果
     */
    @Override
    public int deleteRlInspecRequisitionById(String id)
    {
        rlInspecRequisitionMapper.deleteRlInspecRequisitionItemsByRequisitionId(id);
        return rlInspecRequisitionMapper.deleteRlInspecRequisitionById(id);
    }

    /**
     * 新增检验申请项目信息
     * 
     * @param rlInspecRequisition 检验申请对象
     */
    public void insertRlInspecRequisitionItems(RlInspecRequisition rlInspecRequisition)
    {
        List<RlInspecRequisitionItems> rlInspecRequisitionItemsList = rlInspecRequisition.getRlInspecRequisitionItemsList();
        String id = rlInspecRequisition.getId();
        if (StringUtils.isNotNull(rlInspecRequisitionItemsList))
        {
            List<RlInspecRequisitionItems> list = new ArrayList<>();
            for (RlInspecRequisitionItems rlInspecRequisitionItems : rlInspecRequisitionItemsList)
            {
                rlInspecRequisitionItems.setId(id);
                list.add(rlInspecRequisitionItems);
            }
            if (list.size() > 0)
            {
                rlInspecRequisitionMapper.batchRlInspecRequisitionItems(list);
            }
        }
    }
}
