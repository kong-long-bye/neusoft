package com.neusoft.medical.vo.medical;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 患者医疗服务VO
 * @author Neusoft
 * @date 2025-07-10
 */
@ApiModel(description = "患者医疗服务信息")
public class PatientMedicalServiceVO {
    
    @ApiModelProperty(value = "主键")
    private Integer id;
    
    @ApiModelProperty(value = "患者ID")
    private Integer patientId;
    
    @ApiModelProperty(value = "患者姓名")
    private String patientName;
    
    @ApiModelProperty(value = "医疗服务ID")
    private Integer medicalId;
    
    @ApiModelProperty(value = "项目类别")
    private String medicalType;
    
    @ApiModelProperty(value = "项目编码")
    private String medicalNumber;
    
    @ApiModelProperty(value = "项目国家编码")
    private String countryNumber;
    
    @ApiModelProperty(value = "项目名称")
    private String medicalName;
    
    @ApiModelProperty(value = "项目说明")
    private String medicalInfo;
    
    @ApiModelProperty(value = "除外内容")
    private String medicalExclude;
    
    @ApiModelProperty(value = "计价单位")
    private String medicalUnit;
    
    @ApiModelProperty(value = "价格")
    private BigDecimal medicalPrice;
    
    @ApiModelProperty(value = "开立时间")
    private LocalDateTime orderTime;
    
    @ApiModelProperty(value = "医嘱医疗服务信息内容")
    private String doctorOrder;
    
    @ApiModelProperty(value = "用法详情说明")
    private String useMethod;
    
    @ApiModelProperty(value = "医嘱状态：1-正常执行 0-作废 2-停止")
    private Integer status;
    
    @ApiModelProperty(value = "医嘱状态名称")
    private String statusName;
    
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;
    
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;
    
    // 构造方法
    public PatientMedicalServiceVO() {}
    
    // Getter and Setter methods
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public Integer getPatientId() { return patientId; }
    public void setPatientId(Integer patientId) { this.patientId = patientId; }
    
    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }
    
    public Integer getMedicalId() { return medicalId; }
    public void setMedicalId(Integer medicalId) { this.medicalId = medicalId; }
    
    public String getMedicalType() { return medicalType; }
    public void setMedicalType(String medicalType) { this.medicalType = medicalType; }
    
    public String getMedicalNumber() { return medicalNumber; }
    public void setMedicalNumber(String medicalNumber) { this.medicalNumber = medicalNumber; }
    
    public String getCountryNumber() { return countryNumber; }
    public void setCountryNumber(String countryNumber) { this.countryNumber = countryNumber; }
    
    public String getMedicalName() { return medicalName; }
    public void setMedicalName(String medicalName) { this.medicalName = medicalName; }
    
    public String getMedicalInfo() { return medicalInfo; }
    public void setMedicalInfo(String medicalInfo) { this.medicalInfo = medicalInfo; }
    
    public String getMedicalExclude() { return medicalExclude; }
    public void setMedicalExclude(String medicalExclude) { this.medicalExclude = medicalExclude; }
    
    public String getMedicalUnit() { return medicalUnit; }
    public void setMedicalUnit(String medicalUnit) { this.medicalUnit = medicalUnit; }
    
    public BigDecimal getMedicalPrice() { return medicalPrice; }
    public void setMedicalPrice(BigDecimal medicalPrice) { this.medicalPrice = medicalPrice; }
    
    public LocalDateTime getOrderTime() { return orderTime; }
    public void setOrderTime(LocalDateTime orderTime) { this.orderTime = orderTime; }
    
    public String getDoctorOrder() { return doctorOrder; }
    public void setDoctorOrder(String doctorOrder) { this.doctorOrder = doctorOrder; }
    
    public String getUseMethod() { return useMethod; }
    public void setUseMethod(String useMethod) { this.useMethod = useMethod; }
    
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    
    public String getStatusName() { return statusName; }
    public void setStatusName(String statusName) { this.statusName = statusName; }
    
    public LocalDateTime getCreatedTime() { return createdTime; }
    public void setCreatedTime(LocalDateTime createdTime) { this.createdTime = createdTime; }
    
    public LocalDateTime getUpdatedTime() { return updatedTime; }
    public void setUpdatedTime(LocalDateTime updatedTime) { this.updatedTime = updatedTime; }
} 