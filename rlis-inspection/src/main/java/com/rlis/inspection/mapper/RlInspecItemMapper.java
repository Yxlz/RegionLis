package com.rlis.inspection.mapper;

import com.rlis.inspection.domain.RlInspecItem;

import java.util.List;

/**
 * 检验项目Mapper接口
 *
 * @author tangxiaohui
 * @date 2020-07-14
 */
public interface RlInspecItemMapper
{
    /**
     * 查询检验项目
     *
     * @param itemId 检验项目ID
     * @return 检验项目
     */
    public RlInspecItem selectRlInspecItemById(String itemId);

    /**
     * 查询检验项目列表
     *
     * @param rlInspecItem 检验项目
     * @return 检验项目集合
     */
    public List<RlInspecItem> selectRlInspecItemList(RlInspecItem rlInspecItem);

    /**
     * 新增检验项目
     *
     * @param rlInspecItem 检验项目
     * @return 结果
     */
    public int insertRlInspecItem(RlInspecItem rlInspecItem);

    /**
     * 修改检验项目
     *
     * @param rlInspecItem 检验项目
     * @return 结果
     */
    public int updateRlInspecItem(RlInspecItem rlInspecItem);

    /**
     * 删除检验项目
     *
     * @param itemId 检验项目ID
     * @return 结果
     */
    public int deleteRlInspecItemById(String itemId);

    /**
     * 批量删除检验项目
     *
     * @param itemIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteRlInspecItemByIds(String[] itemIds);

    int deleteByPrimaryKey(String itemId);

    int insert(RlInspecItem record);

    int insertSelective(RlInspecItem record);

    RlInspecItem selectByPrimaryKey(String itemId);

    int updateByPrimaryKeySelective(RlInspecItem record);

    int updateByPrimaryKey(RlInspecItem record);
}