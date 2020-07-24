package com.rlis.system.service.impl;

import com.rlis.common.annotation.DataScope;
import com.rlis.common.constant.UserConstants;
import com.rlis.common.core.domain.Ztree;
import com.rlis.common.exception.BusinessException;
import com.rlis.common.utils.StringUtils;
import com.rlis.system.domain.RlSysOrg;
import com.rlis.system.domain.RlSysRole;
import com.rlis.system.mapper.RlSysOrgMapper;
import com.rlis.system.service.IRlSysOrgService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description: 机构管理 服务实现
 * @Author: tangxiaohui
 * @CreateDate: 2020/7/10 11:40
 * @Copyright: Copyright (c) 2020
 * @Company: 成都信通网易医疗科技发展有限公司
 * @Version: 1.0
 */
@Service
public class RlSysOrgServiceImpl implements IRlSysOrgService {
    @Autowired
    private RlSysOrgMapper orgMapper;

    /**
     * 查询机构管理数据
     *
     * @param org 机构信息
     * @return 机构信息集合
     */
    @Override
    @DataScope(orgAlias = "d")
    public List<RlSysOrg> selectOrgList(RlSysOrg org)
    {
        return orgMapper.selectOrgList(org);
    }

    /**
     * 查询机构管理树
     *
     * @param org 机构信息
     * @return 所有机构信息
     */
    @Override
    @DataScope(orgAlias = "d")
    public List<Ztree> selectOrgTree(RlSysOrg org)
    {
        List<RlSysOrg> orgList = orgMapper.selectOrgList(org);
        List<Ztree> ztrees = initZtree(orgList);
        return ztrees;
    }

    /**
     * 查询机构管理树（排除下级）
     *
     * @param org 机构
     * @return 所有机构信息
     */
    @Override
    @DataScope(orgAlias = "d")
    public List<Ztree> selectOrgTreeExcludeChild(RlSysOrg org)
    {
        Long orgId = org.getOrgId();
        List<RlSysOrg> orgList = orgMapper.selectOrgList(org);
        Iterator<RlSysOrg> it = orgList.iterator();
        while (it.hasNext())
        {
            RlSysOrg d = (RlSysOrg) it.next();
            if (d.getOrgId().intValue() == orgId
                    || ArrayUtils.contains(StringUtils.split(d.getAncestors(), ","), orgId + ""))
            {
                it.remove();
            }
        }
        List<Ztree> ztrees = initZtree(orgList);
        return ztrees;
    }

    /**
     * 根据角色ID查询机构（数据权限）
     *
     * @param role 角色对象
     * @return 机构列表（数据权限）
     */
    @Override
    public List<Ztree> roleOrgTreeData(RlSysRole role)
    {
        Long roleId = role.getRoleId();
        List<Ztree> ztrees = new ArrayList<Ztree>();
        List<RlSysOrg> orgList = selectOrgList(new RlSysOrg());
        if (StringUtils.isNotNull(roleId))
        {
            List<String> roleOrgList = orgMapper.selectRoleOrgTree(roleId);
            ztrees = initZtree(orgList, roleOrgList);
        }
        else
        {
            ztrees = initZtree(orgList);
        }
        return ztrees;
    }

    /**
     * 对象转机构树
     *
     * @param orgList 机构列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<RlSysOrg> orgList)
    {
        return initZtree(orgList, null);
    }

    /**
     * 对象转机构树
     *
     * @param orgList 机构列表
     * @param roleOrgList 角色已存在菜单列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<RlSysOrg> orgList, List<String> roleOrgList)
    {

        List<Ztree> ztrees = new ArrayList<Ztree>();
        boolean isCheck = StringUtils.isNotNull(roleOrgList);
        for (RlSysOrg org : orgList)
        {
            if (UserConstants.DEPT_NORMAL.equals(org.getStatus()))
            {
                Ztree ztree = new Ztree();
                ztree.setId(org.getOrgId());
                ztree.setpId(org.getParentId());
                ztree.setName(org.getOrgName());
                ztree.setTitle(org.getOrgName());
                if (isCheck)
                {
                    ztree.setChecked(roleOrgList.contains(org.getOrgId() + org.getOrgName()));
                }
                ztrees.add(ztree);
            }
        }
        return ztrees;
    }

    /**
     * 查询机构人数
     *
     * @param parentId 机构ID
     * @return 结果
     */
    @Override
    public int selectOrgCount(Long parentId)
    {
        RlSysOrg org = new RlSysOrg();
        org.setParentId(parentId);
        return orgMapper.selectOrgCount(org);
    }

    /**
     * 查询机构是否存在用户
     *
     * @param orgId 机构ID
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean checkOrgExistUser(Long orgId)
    {
        int result = orgMapper.checkOrgExistUser(orgId);
        return result > 0 ? true : false;
    }

    /**
     * 删除机构管理信息
     *
     * @param orgId 机构ID
     * @return 结果
     */
    @Override
    public int deleteOrgById(Long orgId)
    {
        return orgMapper.deleteOrgById(orgId);
    }

    /**
     * 新增保存机构信息
     *
     * @param org 机构信息
     * @return 结果
     */
    @Override
    public int insertOrg(RlSysOrg org)
    {
        RlSysOrg info = orgMapper.selectOrgById(org.getParentId());
        // 如果父节点不为"正常"状态,则不允许新增子节点
        if (!UserConstants.DEPT_NORMAL.equals(info.getStatus()))
        {
            throw new BusinessException("机构停用，不允许新增");
        }
        org.setAncestors(info.getAncestors() + "," + org.getParentId());
        return orgMapper.insertOrg(org);
    }

    /**
     * 修改保存机构信息
     *
     * @param org 机构信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateOrg(RlSysOrg org)
    {
        RlSysOrg newParentOrg = orgMapper.selectOrgById(org.getParentId());
        RlSysOrg oldOrg = selectOrgById(org.getOrgId());
        if (StringUtils.isNotNull(newParentOrg) && StringUtils.isNotNull(oldOrg))
        {
            String newAncestors = newParentOrg.getAncestors() + "," + newParentOrg.getOrgId();
            String oldAncestors = oldOrg.getAncestors();
            org.setAncestors(newAncestors);
            updateOrgChildren(org.getOrgId(), newAncestors, oldAncestors);
        }
        int result = orgMapper.updateOrg(org);
        if (UserConstants.DEPT_NORMAL.equals(org.getStatus()))
        {
            // 如果该机构是启用状态，则启用该机构的所有上级机构
            updateParentOrgStatus(org);
        }
        return result;
    }

    /**
     * 修改该机构的父级机构状态
     *
     * @param org 当前机构
     */
    private void updateParentOrgStatus(RlSysOrg org)
    {
        String updateBy = org.getUpdateBy();
        org = orgMapper.selectOrgById(org.getOrgId());
        org.setUpdateBy(updateBy);
        orgMapper.updateOrgStatus(org);
    }

    /**
     * 修改子元素关系
     *
     * @param orgId 被修改的机构ID
     * @param newAncestors 新的父ID集合
     * @param oldAncestors 旧的父ID集合
     */
    public void updateOrgChildren(Long orgId, String newAncestors, String oldAncestors)
    {
        List<RlSysOrg> children = orgMapper.selectChildrenOrgById(orgId);
        for (RlSysOrg child : children)
        {
            child.setAncestors(child.getAncestors().replace(oldAncestors, newAncestors));
        }
        if (children.size() > 0)
        {
            orgMapper.updateOrgChildren(children);
        }
    }

    /**
     * 根据机构ID查询信息
     *
     * @param orgId 机构ID
     * @return 机构信息
     */
    @Override
    public RlSysOrg selectOrgById(Long orgId)
    {
        return orgMapper.selectOrgById(orgId);
    }

    /**
     * 根据ID查询所有子机构（正常状态）
     *
     * @param orgId 机构ID
     * @return 子机构数
     */
    @Override
    public int selectNormalChildrenOrgById(Long orgId)
    {
        return orgMapper.selectNormalChildrenOrgById(orgId);
    }

    /**
     * 校验机构名称是否唯一
     *
     * @param org 机构信息
     * @return 结果
     */
    @Override
    public String checkOrgNameUnique(RlSysOrg org)
    {
        Long orgId = StringUtils.isNull(org.getOrgId()) ? -1L : org.getOrgId();
        RlSysOrg info = orgMapper.checkOrgNameUnique(org.getOrgName(), org.getParentId());
        if (StringUtils.isNotNull(info) && info.getOrgId().longValue() != orgId.longValue())
        {
            return UserConstants.DEPT_NAME_NOT_UNIQUE;
        }
        return UserConstants.DEPT_NAME_UNIQUE;
    }
}
