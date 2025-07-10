package com.neusoft.medical.entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;

/**
 * 用户信息表实体
 * @author Neusoft
 * @date 2025-07-10
 */
@ApiModel(description = "用户信息")
public class SysUser {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "登录用户名")
    private String loginName;

    @ApiModelProperty(value = "真实姓名")
    private String userName;

    @ApiModelProperty(value = "性别：1-男 2-女")
    private Integer gender;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "联系方式")
    private String mobile;

    @ApiModelProperty(value = "用户状态：1-正常 0-禁用")
    private Integer status;

    @ApiModelProperty(value = "用户邮箱")
    private String email;

    @ApiModelProperty(value = "逻辑删除标识：0-未删除；1-已删除")
    private Integer deleted;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime gmtModified;

    // 构造方法
    public SysUser() {}

    // Getter and Setter methods
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getLoginName() { return loginName; }
    public void setLoginName(String loginName) { this.loginName = loginName; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public Integer getGender() { return gender; }
    public void setGender(Integer gender) { this.gender = gender; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getDeleted() { return deleted; }
    public void setDeleted(Integer deleted) { this.deleted = deleted; }

    public LocalDateTime getGmtCreate() { return gmtCreate; }
    public void setGmtCreate(LocalDateTime gmtCreate) { this.gmtCreate = gmtCreate; }

    public LocalDateTime getGmtModified() { return gmtModified; }
    public void setGmtModified(LocalDateTime gmtModified) { this.gmtModified = gmtModified; }
}