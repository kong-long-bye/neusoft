package com.neusoft.medical.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;

/**
 * 患者医嘱医疗服务项目信息表实体
 * @author Neusoft
 * @date 2025-07-10
 */
@ApiModel(description = "患者医嘱医疗服务项目信息")
public class InpatientMedical {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "患者ID")
    private Integer patientId;

    @ApiModelProperty(value = "开立时间")
    private LocalDateTime orderTime;

    @ApiModelProperty(value = "医嘱医疗服务信息内容")
    private String doctorOrder;

    @ApiModelProperty(value = "用法详情说明")
    private String useMethod;

    @ApiModelProperty(value = "医疗服务表外键")
    private Integer medicalId;

    @ApiModelProperty(value = "医嘱状态：1-正常执行 0-作废 2-停止")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    // 构造方法
    public InpatientMedical() {}

    // Getter and Setter methods
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getPatientId() { return patientId; }
    public void setPatientId(Integer patientId) { this.patientId = patientId; }

    public LocalDateTime getOrderTime() { return orderTime; }
    public void setOrderTime(LocalDateTime orderTime) { this.orderTime = orderTime; }

    public String getDoctorOrder() { return doctorOrder; }
    public void setDoctorOrder(String doctorOrder) { this.doctorOrder = doctorOrder; }

    public String getUseMethod() { return useMethod; }
    public void setUseMethod(String useMethod) { this.useMethod = useMethod; }

    public Integer getMedicalId() { return medicalId; }
    public void setMedicalId(Integer medicalId) { this.medicalId = medicalId; }

    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }

    public LocalDateTime getCreatedTime() { return createdTime; }
    public void setCreatedTime(LocalDateTime createdTime) { this.createdTime = createdTime; }

    public LocalDateTime getUpdatedTime() { return updatedTime; }
    public void setUpdatedTime(LocalDateTime updatedTime) { this.updatedTime = updatedTime; }
}