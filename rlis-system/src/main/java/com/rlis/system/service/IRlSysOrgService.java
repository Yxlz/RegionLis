package com.rlis.system.service;

import com.rlis.common.core.domain.Ztree;
import com.rlis.system.domain.RlSysOrg;
import com.rlis.system.domain.RlSysRole;

import java.util.List;

/**
 * @Description: 机构管理 服务层
 * @Author: tangxiaohui
 * @CreateDate: 2020/7/10 11:37
 * @Copyright: Copyright (c) 2020
 * @Company: 成都信通网易医疗科技发展有限公司
 * @Version: 1.0
 */
public interface IRlSysOrgService {
    /**
     * 查询机构管理数据
     *
     * @param org 机构信息
     * @return 机构信息集合
     */
    public List<RlSysOrg> selectOrgList(RlSysOrg org);

    /**
     * 查询机构管理树
     *
     * @param org 机构信息
     * @return 所有机构信息
     */
    public List<Ztree> selectOrgTree(RlSysOrg org);

    /**
     * 查询机构管理树（排除下级）
     *
     * @param org 机构信息
     * @return 所有机构信息
     */
    public List<Ztree> selectOrgTreeExcludeChild(RlSysOrg org);

    /**
     * 根据角色ID查询菜单
     *
     * @param role 角色对象
     * @return 菜单列表
     */
    public List<Ztree> roleOrgTreeData(RlSysRole role);

    /**
     * 查询机构人数
     *
     * @param parentId 父机构ID
     * @return 结果
     */
    public int selectOrgCount(Long parentId);

    /**
     * 查询机构是否存在用户
     *
     * @param orgId 机构ID
     * @return 结果 true 存在 false 不存在
     */
    public boolean checkOrgExistUser(Long orgId);

    /**
     * 删除机构管理信息
     *
     * @param orgId 机构ID
     * @return 结果
     */
    public int deleteOrgById(Long orgId);

    /**
     * 新增保存机构信息
     *
     * @param org 机构信息
     * @return 结果
     */
    public int insertOrg(RlSysOrg org);

    /**
     * 修改保存机构信息
     *
     * @param org 机构信息
     * @return 结果
     */
    public int updateOrg(RlSysOrg org);

    /**
     * 根据机构ID查询信息
     *
     * @param orgId 机构ID
     * @return 机构信息
     */
    public RlSysOrg selectOrgById(Long orgId);

    /**
     * 根据ID查询所有子机构（正常状态）
     *
     * @param orgId 机构ID
     * @return 子机构数
     */
    public int selectNormalChildrenOrgById(Long orgId);

    /**
     * 校验机构名称是否唯一
     *
     * @param org 机构信息
     * @return 结果
     */
    public String checkOrgNameUnique(RlSysOrg org);
}
