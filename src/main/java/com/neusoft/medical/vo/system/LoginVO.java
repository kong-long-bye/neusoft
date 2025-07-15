package com.neusoft.medical.vo.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 登录请求数据传输对象
 * @author Neusoft
 * @date 2025-07-10
 */
@ApiModel(description = "登录请求参数")
public class LoginVO {

    @ApiModelProperty(value = "登录用户名", required = true, example = "admin")
    private String loginName;

    @ApiModelProperty(value = "密码", required = true, example = "admin")
    private String password;

    @ApiModelProperty(value = "验证码", example = "1234")
    private String captcha;

    // 构造方法
    public LoginVO() {}

    public LoginVO(String loginName, String password) {
        this.loginName = loginName;
        this.password = password;
    }

    // Getter and Setter methods
    public String getLoginName() { return loginName; }
    public void setLoginName(String loginName) { this.loginName = loginName; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getCaptcha() { return captcha; }
    public void setCaptcha(String captcha) { this.captcha = captcha; }

    @Override
    public String toString() {
        return "LoginVO{" +
                "loginName='" + loginName + '\'' +
                ", password='[PROTECTED]'" +
                ", captcha='" + captcha + '\'' +
                '}';
    }
}
