package com.neusoft.medical.mapper;

import com.neusoft.medical.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户信息表数据访问接口
 * @author Neusoft
 * @date 2025-07-10
 */
@Mapper
public interface SysUserMapper {

    /**
     * 根据登录用户名查询用户信息
     * @param loginName 登录用户名
     * @return 用户信息
     */
    SysUser selectByLoginName(@Param("loginName") String loginName);

    /**
     * 根据登录用户名和密码查询用户信息
     * @param loginName 登录用户名
     * @param password 密码
     * @return 用户信息
     */
    SysUser selectByLoginNameAndPassword(@Param("loginName") String loginName, @Param("password") String password);

    /**
     * 根据主键查询用户信息
     * @param id 用户ID
     * @return 用户信息
     */
    SysUser selectByPrimaryKey(@Param("id") Integer id);
}