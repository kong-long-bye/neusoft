package com.neusoft.medical.service.system;

import com.neusoft.medical.vo.system.LoginVO;
import com.neusoft.medical.vo.system.LoginResponseVO;

/**
 * 登录服务接口
 * @author Neusoft
 * @date 2025-07-10
 */
public interface LoginService {

    /**
     * 用户登录
     * @param loginVO 登录参数
     * @return 登录结果
     */
    LoginResponseVO login(LoginVO loginVO);

    /**
     * 用户登出
     * @param token 用户令牌
     * @return 登出结果
     */
    boolean logout(String token);

    /**
     * 检查用户名是否存在
     * @param loginName 登录用户名
     * @return 是否存在
     */
    boolean checkLoginNameExists(String loginName);
}