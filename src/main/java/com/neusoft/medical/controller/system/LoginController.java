package com.neusoft.medical.controller.system;

import com.neusoft.medical.common.result.Result;
import com.neusoft.medical.service.system.LoginService;
import com.neusoft.medical.vo.system.LoginVO;
import com.neusoft.medical.vo.system.LoginResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 登录控制器
 * @author Neusoft
 * @date 2025-07-10
 */
@Api(tags = "用户登录管理")
@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 用户登录
     */
    @ApiOperation(value = "用户登录", notes = "用户登录接口")
    @PostMapping("/login")
    public Result<LoginResponseVO> login(@RequestBody @ApiParam(value = "登录参数", required = true) LoginVO loginVO) {
        try {
            LoginResponseVO responseVO = loginService.login(loginVO);
            return Result.success("登录成功", responseVO);
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        } catch (Exception e) {
            return Result.error("登录失败，系统异常");
        }
    }

    /**
     * 用户登出
     */
    @ApiOperation(value = "用户登出", notes = "用户登出接口")
    @PostMapping("/logout")
    public Result<Void> logout(@RequestHeader @ApiParam(value = "用户令牌", required = true) String token) {
        try {
            boolean success = loginService.logout(token);
            if (success) {
                Result<Void> result = Result.success();
                result.setMessage("登出成功");
                return result;
            } else {
                return Result.error("登出失败");
            }
        } catch (Exception e) {
            return Result.error("登出失败，系统异常");
        }
    }

    /**
     * 检查用户名是否存在
     */
    @ApiOperation(value = "检查用户名", notes = "检查用户名是否已存在")
    @GetMapping("/check-username")
    public Result<Boolean> checkUsername(@RequestParam @ApiParam(value = "登录用户名", required = true) String loginName) {
        try {
            boolean exists = loginService.checkLoginNameExists(loginName);
            return Result.success("查询成功", exists);
        } catch (Exception e) {
            return Result.error("查询失败");
        }
    }
}