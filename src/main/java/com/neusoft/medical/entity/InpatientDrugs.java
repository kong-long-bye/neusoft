package com.neusoft.medical.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;

/**
 * 患者医嘱药品信息表实体
 * @author Neusoft
 * @date 2025-07-10
 */
@ApiModel(description = "患者医嘱药品信息")
public class InpatientDrugs {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "患者ID")
    private Integer patientId;

    @ApiModelProperty(value = "起始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "医嘱内容")
    private String doctorOrder;

    @ApiModelProperty(value = "医嘱频次（次/天）")
    private Integer orderNumber;

    @ApiModelProperty(value = "用法")
    private String useMethod;

    @ApiModelProperty(value = "药品表外键")
    private Integer drugId;

    @ApiModelProperty(value = "医嘱状态：1-正常执行 0-作废 2-停止")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    // 构造方法
    public InpatientDrugs() {}

    // Getter and Setter methods
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getPatientId() { return patientId; }
    public void setPatientId(Integer patientId) { this.patientId = patientId; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }

    public String getDoctorOrder() { return doctorOrder; }
    public void setDoctorOrder(String doctorOrder) { this.doctorOrder = doctorOrder; }

    public Integer getOrderNumber() { return orderNumber; }
    public void setOrderNumber(Integer orderNumber) { this.orderNumber = orderNumber; }

    public String getUseMethod() { return useMethod; }
    public void setUseMethod(String useMethod) { this.useMethod = useMethod; }

    public Integer getDrugId() { return drugId; }
    public void setDrugId(Integer drugId) { this.drugId = drugId; }

    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }

    public LocalDateTime getCreatedTime() { return createdTime; }
    public void setCreatedTime(LocalDateTime createdTime) { this.createdTime = createdTime; }

    public LocalDateTime getUpdatedTime() { return updatedTime; }
    public void setUpdatedTime(LocalDateTime updatedTime) { this.updatedTime = updatedTime; }
}
