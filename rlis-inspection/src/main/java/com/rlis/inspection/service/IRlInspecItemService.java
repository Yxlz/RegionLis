package com.rlis.inspection.service;

import com.rlis.inspection.domain.RlInspecItem;

import java.util.List;

/**
 * 检验项目Service接口
 *
 * @author tangxiaohui
 * @date 2020-07-14
 */
public interface IRlInspecItemService
{
    /**
     * 查询检验项目
     *
     * @param itemId 检验项目ID
     * @return 检验项目
     */
    RlInspecItem selectRlInspecItemById(String itemId);

    /**
     * 查询检验项目列表
     *
     * @param rlInspecItem 检验项目
     * @return 检验项目集合
     */
    List<RlInspecItem> selectRlInspecItemList(RlInspecItem rlInspecItem);

    /**
     * 新增检验项目
     *
     * @param rlInspecItem 检验项目
     * @return 结果
     */
    int insertRlInspecItem(RlInspecItem rlInspecItem);

    /**
     * 修改检验项目
     *
     * @param rlInspecItem 检验项目
     * @return 结果
     */
    int updateRlInspecItem(RlInspecItem rlInspecItem);

    /**
     * 批量删除检验项目
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteRlInspecItemByIds(String ids);

    /**
     * 删除检验项目信息
     *
     * @param itemId 检验项目ID
     * @return 结果
     */
    int deleteRlInspecItemById(String itemId);

    /**
     * 项目状态改变
     *
     * @param rlInspecItem 检验项目
     * @return 结果
     */
    int changeStatus(RlInspecItem rlInspecItem);

}
