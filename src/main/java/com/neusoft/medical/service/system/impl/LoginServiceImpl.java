package com.neusoft.medical.service.system.impl;

import com.neusoft.medical.entity.SysUser;
import com.neusoft.medical.mapper.SysUserMapper;
import com.neusoft.medical.service.system.LoginService;
import com.neusoft.medical.vo.system.LoginVO;
import com.neusoft.medical.vo.system.LoginResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * 登录服务实现类
 * @author Neusoft
 * @date 2025-07-10
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public LoginResponseVO login(LoginVO loginVO) {
        // 1. 参数校验
        if (loginVO == null || !StringUtils.hasText(loginVO.getLoginName()) || !StringUtils.hasText(loginVO.getPassword())) {
            throw new RuntimeException("用户名和密码不能为空");
        }

        // 2. 查询用户信息
        SysUser user = sysUserMapper.selectByLoginNameAndPassword(loginVO.getLoginName(), loginVO.getPassword());
        if (user == null) {
            throw new RuntimeException("用户名或密码错误");
        }

        // 3. 检查用户状态
        if (user.getStatus() == null || user.getStatus() != 1) {
            throw new RuntimeException("用户已被禁用");
        }

        if (user.getDeleted() != null && user.getDeleted() == 1) {
            throw new RuntimeException("用户不存在");
        }

        // 4. 生成简单的token（实际项目中应使用JWT等安全方案）
        String token = generateSimpleToken(user.getId(), user.getLoginName());

        // 5. 构建返回结果
        LoginResponseVO responseVO = new LoginResponseVO();
        responseVO.setUserId(user.getId());
        responseVO.setLoginName(user.getLoginName());
        responseVO.setUserName(user.getUserName());
        responseVO.setEmail(user.getEmail());
        responseVO.setToken(token);

        return responseVO;
    }

    @Override
    public boolean logout(String token) {
        // 简单实现：在实际项目中，这里应该将token加入黑名单或从缓存中移除
        // 目前只做简单的参数检查
        return StringUtils.hasText(token);
    }

    @Override
    public boolean checkLoginNameExists(String loginName) {
        if (!StringUtils.hasText(loginName)) {
            return false;
        }
        SysUser user = sysUserMapper.selectByLoginName(loginName);
        return user != null;
    }

    /**
     * 生成简单的token
     * 注意：这只是一个简单实现，生产环境建议使用JWT等更安全的方案
     */
    private String generateSimpleToken(Integer userId, String loginName) {
        return "TOKEN_" + userId + "_" + loginName + "_" + UUID.randomUUID().toString().replace("-", "");
    }
}