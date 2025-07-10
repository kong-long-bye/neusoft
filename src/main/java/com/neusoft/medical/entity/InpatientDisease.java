package com.neusoft.medical.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;

/**
 * 患者诊断疾病表实体
 * @author Neusoft
 * @date 2025-07-10
 */
@ApiModel(description = "患者诊断疾病信息")
public class InpatientDisease {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "住院患者表外键")
    private Integer patientId;

    @ApiModelProperty(value = "疾病表外键")
    private Integer diseaseId;

    @ApiModelProperty(value = "疾病诊断时间")
    private LocalDateTime orderTime;

    @ApiModelProperty(value = "诊断类型（1、入院诊断，2、主要诊断，3、其他诊断）")
    private Integer diseaseType;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    // 构造方法
    public InpatientDisease() {}

    // Getter and Setter methods
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getPatientId() { return patientId; }
    public void setPatientId(Integer patientId) { this.patientId = patientId; }

    public Integer getDiseaseId() { return diseaseId; }
    public void setDiseaseId(Integer diseaseId) { this.diseaseId = diseaseId; }

    public LocalDateTime getOrderTime() { return orderTime; }
    public void setOrderTime(LocalDateTime orderTime) { this.orderTime = orderTime; }

    public Integer getDiseaseType() { return diseaseType; }
    public void setDiseaseType(Integer diseaseType) { this.diseaseType = diseaseType; }

    public LocalDateTime getCreatedTime() { return createdTime; }
    public void setCreatedTime(LocalDateTime createdTime) { this.createdTime = createdTime; }

    public LocalDateTime getUpdatedTime() { return updatedTime; }
    public void setUpdatedTime(LocalDateTime updatedTime) { this.updatedTime = updatedTime; }
}