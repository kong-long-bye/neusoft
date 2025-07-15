package com.neusoft.medical.vo.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 登录响应数据传输对象
 * @author Neusoft
 * @date 2025-07-10
 */
@ApiModel(description = "登录响应结果")
public class LoginResponseVO {

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "登录用户名")
    private String loginName;

    @ApiModelProperty(value = "真实姓名")
    private String userName;

    @ApiModelProperty(value = "用户邮箱")
    private String email;

    @ApiModelProperty(value = "访问令牌")
    private String token;

    // 构造方法
    public LoginResponseVO() {}

    public LoginResponseVO(Integer userId, String loginName, String userName, String email, String token) {
        this.userId = userId;
        this.loginName = loginName;
        this.userName = userName;
        this.email = email;
        this.token = token;
    }

    // Getter and Setter methods
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public String getLoginName() { return loginName; }
    public void setLoginName(String loginName) { this.loginName = loginName; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    @Override
    public String toString() {
        return "LoginResponseVO{" +
                "userId=" + userId +
                ", loginName='" + loginName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
