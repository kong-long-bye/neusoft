package com.neusoft.medical.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;

/**
 * 药品报销等级信息表实体
 * @author Neusoft
 * @date 2025-07-10
 */
@ApiModel(description = "药品报销等级信息")
public class DrugReimbursement {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "报销等级类型")
    private String drugReimbursementType;

    @ApiModelProperty(value = "报销比例")
    private Integer drugReimbursementProportion;

    @ApiModelProperty(value = "报销等级信息")
    private String drugReimbursementInfo;

    @ApiModelProperty(value = "状态：1-正常 0-禁用")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    // 构造方法
    public DrugReimbursement() {}

    // Getter and Setter methods
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getDrugReimbursementType() { return drugReimbursementType; }
    public void setDrugReimbursementType(String drugReimbursementType) { this.drugReimbursementType = drugReimbursementType; }

    public Integer getDrugReimbursementProportion() { return drugReimbursementProportion; }
    public void setDrugReimbursementProportion(Integer drugReimbursementProportion) { this.drugReimbursementProportion = drugReimbursementProportion; }

    public String getDrugReimbursementInfo() { return drugReimbursementInfo; }
    public void setDrugReimbursementInfo(String drugReimbursementInfo) { this.drugReimbursementInfo = drugReimbursementInfo; }

    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }

    public LocalDateTime getCreatedTime() { return createdTime; }
    public void setCreatedTime(LocalDateTime createdTime) { this.createdTime = createdTime; }

    public LocalDateTime getUpdatedTime() { return updatedTime; }
    public void setUpdatedTime(LocalDateTime updatedTime) { this.updatedTime = updatedTime; }
}