package com.rlis.system.service.impl;

import com.rlis.common.annotation.DataScope;
import com.rlis.common.constant.UserConstants;
import com.rlis.common.core.text.Convert;
import com.rlis.common.exception.BusinessException;
import com.rlis.common.utils.StringUtils;
import com.rlis.common.utils.spring.SpringUtils;
import com.rlis.system.domain.RlSysRole;
import com.rlis.system.domain.RlSysRoleMenu;
import com.rlis.system.domain.RlSysRoleOrg;
import com.rlis.system.domain.RlSysUserRole;
import com.rlis.system.mapper.RlSysRoleMapper;
import com.rlis.system.mapper.RlSysRoleMenuMapper;
import com.rlis.system.mapper.RlSysRoleOrgMapper;
import com.rlis.system.mapper.RlSysUserRoleMapper;
import com.rlis.system.service.IRlSysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 角色 业务层处理
 * 
 * @author ruoyi
 */
@Service
public class RlSysRoleServiceImpl implements IRlSysRoleService
{
    @Autowired
    private RlSysRoleMapper roleMapper;

    @Autowired
    private RlSysRoleMenuMapper roleMenuMapper;

    @Autowired
    private RlSysUserRoleMapper userRoleMapper;

    @Autowired
    private RlSysRoleOrgMapper roleOrgMapper;

    /**
     * 根据条件分页查询角色数据
     * 
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    @Override
    @DataScope(orgAlias = "d")
    public List<RlSysRole> selectRoleList(RlSysRole role)
    {
        return roleMapper.selectRoleList(role);
    }

    /**
     * 根据用户ID查询权限
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectRoleKeys(Long userId)
    {
        List<RlSysRole> perms = roleMapper.selectRolesByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (RlSysRole perm : perms)
        {
            if (StringUtils.isNotNull(perm))
            {
                permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 根据用户ID查询角色
     * 
     * @param userId 用户ID
     * @return 角色列表
     */
    @Override
    public List<RlSysRole> selectRolesByUserId(Long userId)
    {
        List<RlSysRole> userRoles = roleMapper.selectRolesByUserId(userId);
        List<RlSysRole> roles = selectRoleAll();
        for (RlSysRole role : roles)
        {
            for (RlSysRole userRole : userRoles)
            {
                if (role.getRoleId().longValue() == userRole.getRoleId().longValue())
                {
                    role.setFlag(true);
                    break;
                }
            }
        }
        return roles;
    }

    /**
     * 查询所有角色
     * 
     * @return 角色列表
     */
    @Override
    public List<RlSysRole> selectRoleAll()
    {
        return SpringUtils.getAopProxy(this).selectRoleList(new RlSysRole());
    }

    /**
     * 通过角色ID查询角色
     * 
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    @Override
    public RlSysRole selectRoleById(Long roleId)
    {
        return roleMapper.selectRoleById(roleId);
    }

    /**
     * 通过角色ID删除角色
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    @Override
    public boolean deleteRoleById(Long roleId)
    {
        return roleMapper.deleteRoleById(roleId) > 0 ? true : false;
    }

    /**
     * 批量删除角色信息
     * 
     * @param ids 需要删除的数据ID
     * @throws Exception
     */
    @Override
    public int deleteRoleByIds(String ids) throws BusinessException
    {
        Long[] roleIds = Convert.toLongArray(ids);
        for (Long roleId : roleIds)
        {
            checkRoleAllowed(new RlSysRole(roleId));
            RlSysRole role = selectRoleById(roleId);
            if (countUserRoleByRoleId(roleId) > 0)
            {
                throw new BusinessException(String.format("%1$s已分配,不能删除", role.getRoleName()));
            }
        }
        return roleMapper.deleteRoleByIds(roleIds);
    }

    /**
     * 新增保存角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertRole(RlSysRole role)
    {
        // 新增角色信息
        roleMapper.insertRole(role);
        return insertRoleMenu(role);
    }

    /**
     * 修改保存角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateRole(RlSysRole role)
    {
        // 修改角色信息
        roleMapper.updateRole(role);
        // 删除角色与菜单关联
        roleMenuMapper.deleteRoleMenuByRoleId(role.getRoleId());
        return insertRoleMenu(role);
    }

    /**
     * 修改数据权限信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    @Override
    @Transactional
    public int authDataScope(RlSysRole role)
    {
        // 修改角色信息
        roleMapper.updateRole(role);
        // 删除角色与部门关联
        roleOrgMapper.deleteRoleOrgByRoleId(role.getRoleId());
        // 新增角色和部门信息（数据权限）
        return insertRoleOrg(role);
    }

    /**
     * 新增角色菜单信息
     * 
     * @param role 角色对象
     */
    public int insertRoleMenu(RlSysRole role)
    {
        int rows = 1;
        // 新增用户与角色管理
//        List<RlSysRoleMenu> list = new ArrayList<RlSysRoleMenu>();
        for (Long menuId : role.getMenuIds())
        {
            RlSysRoleMenu rm = new RlSysRoleMenu();
            rm.setRoleId(role.getRoleId());
            rm.setMenuId(menuId);
            rows+=roleMenuMapper.insertRoleMenu(rm);
//            list.add(rm);
        }
//        if (list.size() > 0)
//        {
//            rows = roleMenuMapper.batchRoleMenu(list);
//        }
        return rows;
    }

    /**
     * 新增角色部门信息(数据权限)
     *
     * @param role 角色对象
     */
    public int insertRoleOrg(RlSysRole role)
    {
        int rows = 1;
        // 新增角色与部门（数据权限）管理
        List<RlSysRoleOrg> list = new ArrayList<RlSysRoleOrg>();
        for (Long orgId : role.getOrgIds())
        {
            RlSysRoleOrg rd = new RlSysRoleOrg();
            rd.setRoleId(role.getRoleId());
            rd.setOrgId(orgId);
            list.add(rd);
        }
        if (list.size() > 0)
        {
            rows = roleOrgMapper.batchRoleOrg(list);
        }
        return rows;
    }

    /**
     * 校验角色名称是否唯一
     * 
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public String checkRoleNameUnique(RlSysRole role)
    {
        Long roleId = StringUtils.isNull(role.getRoleId()) ? -1L : role.getRoleId();
        RlSysRole info = roleMapper.checkRoleNameUnique(role.getRoleName());
        if (StringUtils.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue())
        {
            return UserConstants.ROLE_NAME_NOT_UNIQUE;
        }
        return UserConstants.ROLE_NAME_UNIQUE;
    }

    /**
     * 校验角色权限是否唯一
     * 
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public String checkRoleKeyUnique(RlSysRole role)
    {
        Long roleId = StringUtils.isNull(role.getRoleId()) ? -1L : role.getRoleId();
        RlSysRole info = roleMapper.checkRoleKeyUnique(role.getRoleKey());
        if (StringUtils.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue())
        {
            return UserConstants.ROLE_KEY_NOT_UNIQUE;
        }
        return UserConstants.ROLE_KEY_UNIQUE;
    }

    /**
     * 校验角色是否允许操作
     * 
     * @param role 角色信息
     */
    @Override
    public void checkRoleAllowed(RlSysRole role)
    {
        if (StringUtils.isNotNull(role.getRoleId()) && role.isAdmin())
        {
            throw new BusinessException("不允许操作超级管理员角色");
        }
    }

    /**
     * 通过角色ID查询角色使用数量
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    @Override
    public int countUserRoleByRoleId(Long roleId)
    {
        return userRoleMapper.countUserRoleByRoleId(roleId);
    }

    /**
     * 角色状态修改
     * 
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public int changeStatus(RlSysRole role)
    {
        return roleMapper.updateRole(role);
    }

    /**
     * 取消授权用户角色
     * 
     * @param userRole 用户和角色关联信息
     * @return 结果
     */
    @Override
    public int deleteAuthUser(RlSysUserRole userRole)
    {
        return userRoleMapper.deleteUserRoleInfo(userRole);
    }

    /**
     * 批量取消授权用户角色
     * 
     * @param roleId 角色ID
     * @param userIds 需要删除的用户数据ID
     * @return 结果
     */
    @Override
    public int deleteAuthUsers(Long roleId, String userIds)
    {
        return userRoleMapper.deleteUserRoleInfos(roleId, Convert.toLongArray(userIds));
    }

    /**
     * 批量选择授权用户角色
     * 
     * @param roleId 角色ID
     * @param userIds 需要删除的用户数据ID
     * @return 结果
     */
    @Override
    public int insertAuthUsers(Long roleId, String userIds)
    {
        Long[] users = Convert.toLongArray(userIds);
        // 新增用户与角色管理
        List<RlSysUserRole> list = new ArrayList<RlSysUserRole>();
        for (Long userId : users)
        {
            RlSysUserRole ur = new RlSysUserRole();
            ur.setUserId(userId);
            ur.setRoleId(roleId);
            list.add(ur);
        }
        return userRoleMapper.batchUserRole(list);
    }
}
