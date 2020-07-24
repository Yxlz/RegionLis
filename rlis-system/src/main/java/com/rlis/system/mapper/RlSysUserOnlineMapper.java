package com.rlis.system.mapper;

import com.rlis.system.domain.RlSysUserOnline;

import java.util.List;

/**
 * @ClassName: RlSysUserOnlineMapper
 * @Description: 在线用户 数据层
 * @Author tangxiaohui
 * @Copyright: Copyright (c) 2020
 * @Company: 成都信通网易医疗科技发展有限公司
 * @DateTime 2020/7/10 14:19
 */
public interface RlSysUserOnlineMapper
{
    /**
     * 通过会话序号查询信息
     * 
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    public RlSysUserOnline selectOnlineById(String sessionId);

    /**
     * 通过会话序号删除信息
     * 
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    public int deleteOnlineById(String sessionId);

    /**
     * 保存会话信息
     * 
     * @param online 会话信息
     * @return 结果
     */
    int saveOnline(RlSysUserOnline online);

    /**
     * 更新会话信息
     *
     * @param online 会话信息
     * @return 结果
     */
    int updateOnline(RlSysUserOnline online);

    /**
     * 查询会话集合
     * 
     * @param userOnline 会话参数
     * @return 会话集合
     */
    public List<RlSysUserOnline> selectUserOnlineList(RlSysUserOnline userOnline);

    /**
     * 查询过期会话集合
     * 
     * @param lastAccessTime 过期时间
     * @return 会话集合
     */
    public List<RlSysUserOnline> selectOnlineByExpired(String lastAccessTime);


}
