package com.rlis.inspection.service.impl;

import com.rlis.common.core.text.Convert;
import com.rlis.common.utils.DateUtils;
import com.rlis.inspection.domain.RlInspecItem;
import com.rlis.inspection.mapper.RlInspecItemMapper;
import com.rlis.inspection.service.IRlInspecItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 检验项目Service业务层处理
 *
 * @author tangxiaohui
 * @date 2020-07-14
 */
@Service
public class RlInspecItemServiceImpl implements IRlInspecItemService
{


    @Resource
    private RlInspecItemMapper rlInspecItemMapper;

    /**
     * 查询检验项目
     *
     * @param itemId 检验项目ID
     * @return 检验项目
     */
    @Override
    public RlInspecItem selectRlInspecItemById(String itemId)
    {
        return rlInspecItemMapper.selectRlInspecItemById(itemId);
    }

    /**
     * 查询检验项目列表
     *
     * @param rlInspecItem 检验项目
     * @return 检验项目
     */
    @Override
    public List<RlInspecItem> selectRlInspecItemList(RlInspecItem rlInspecItem)
    {
        return rlInspecItemMapper.selectRlInspecItemList(rlInspecItem);
    }

    /**
     * 新增检验项目
     *
     * @param rlInspecItem 检验项目
     * @return 结果
     */
    @Override
    public int insertRlInspecItem(RlInspecItem rlInspecItem)
    {
        return rlInspecItemMapper.insertRlInspecItem(rlInspecItem);
    }

    /**
     * 修改检验项目
     *
     * @param rlInspecItem 检验项目
     * @return 结果
     */
    @Override
    public int updateRlInspecItem(RlInspecItem rlInspecItem)
    {
        rlInspecItem.setUpdateTime(DateUtils.getNowDate());
        return rlInspecItemMapper.updateRlInspecItem(rlInspecItem);
    }

    /**
     * 删除检验项目对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteRlInspecItemByIds(String ids)
    {
        return rlInspecItemMapper.deleteRlInspecItemByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除检验项目信息
     *
     * @param itemId 检验项目ID
     * @return 结果
     */
    @Override
    public int deleteRlInspecItemById(String itemId)
    {
        return rlInspecItemMapper.deleteRlInspecItemById(itemId);
    }

    /**
     * 项目状态改变
     *
     * @param rlInspecItem 检验项目
     * @return 结果
     */
    @Override
    public int changeStatus(RlInspecItem rlInspecItem) {
        return rlInspecItemMapper.updateRlInspecItem(rlInspecItem);
    }
}