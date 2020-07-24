package com.rlis.system.mapper;

import com.rlis.system.domain.RlSysOrg;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface RlSysOrgMapper {
    /**
     * 查询机构人数
     *
     * @param org 机构信息
     * @return 结果
     */
    public int selectOrgCount(RlSysOrg org);

    /**
     * 查询机构是否存在用户
     *
     * @param orgId 机构ID
     * @return 结果
     */
    public int checkOrgExistUser(Long orgId);

    /**
     * 查询机构管理数据
     *
     * @param org 机构信息
     * @return 机构信息集合
     */
    public List<RlSysOrg> selectOrgList(RlSysOrg org);

    /**
     * 删除机构管理信息
     *
     * @param orgId 机构ID
     * @return 结果
     */
    public int deleteOrgById(Long orgId);

    /**
     * 新增机构信息
     *
     * @param org 机构信息
     * @return 结果
     */
    public int insertOrg(RlSysOrg org);

    /**
     * 修改机构信息
     *
     * @param org 机构信息
     * @return 结果
     */
    public int updateOrg(RlSysOrg org);

    /**
     * 修改子元素关系
     *
     * @param orgs 子元素
     * @return 结果
     */
    public int updateOrgChildren(@Param("orgs") List<RlSysOrg> orgs);

    /**
     * 根据机构ID查询信息
     *
     * @param orgId 机构ID
     * @return 机构信息
     */
    public RlSysOrg selectOrgById(Long orgId);

    /**
     * 校验机构名称是否唯一
     *
     * @param orgName 机构名称
     * @param parentId 父机构ID
     * @return 结果
     */
    public RlSysOrg checkOrgNameUnique(@Param("orgName") String orgName, @Param("parentId") Long parentId);

    /**
     * 根据角色ID查询机构
     *
     * @param roleId 角色ID
     * @return 机构列表
     */
    public List<String> selectRoleOrgTree(Long roleId);

    /**
     * 修改所在机构的父级机构状态
     *
     * @param org 机构
     */
    public void updateOrgStatus(RlSysOrg org);

    /**
     * 根据ID查询所有子机构
     *
     * @param orgId 机构ID
     * @return 机构列表
     */
    public List<RlSysOrg> selectChildrenOrgById(Long orgId);

    /**
     * 根据ID查询所有子机构（正常状态）
     *
     * @param orgId 机构ID
     * @return 子机构数
     */
    public int selectNormalChildrenOrgById(Long orgId);
}