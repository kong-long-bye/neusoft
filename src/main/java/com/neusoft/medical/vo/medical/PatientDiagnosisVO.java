package com.neusoft.medical.vo.medical;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;

/**
 * 患者疾病诊断VO
 * @author Neusoft
 * @date 2025-07-10
 */
@ApiModel(description = "患者疾病诊断信息")
public class PatientDiagnosisVO {
    
    @ApiModelProperty(value = "主键")
    private Integer id;
    
    @ApiModelProperty(value = "患者ID")
    private Integer patientId;
    
    @ApiModelProperty(value = "患者姓名")
    private String patientName;
    
    @ApiModelProperty(value = "疾病ID")
    private Integer diseaseId;
    
    @ApiModelProperty(value = "疾病编码")
    private String diseaseCode;
    
    @ApiModelProperty(value = "疾病名称")
    private String diseaseName;
    
    @ApiModelProperty(value = "国际疾病ICD编码")
    private String diseaseICD;
    
    @ApiModelProperty(value = "疾病类型")
    private String diseaseCategory;
    
    @ApiModelProperty(value = "疾病诊断时间")
    private LocalDateTime orderTime;
    
    @ApiModelProperty(value = "诊断类型（1、入院诊断，2、主要诊断，3、其他诊断）")
    private Integer diseaseType;
    
    @ApiModelProperty(value = "诊断类型名称")
    private String diseaseTypeName;
    
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;
    
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;
    
    // 构造方法
    public PatientDiagnosisVO() {}
    
    // Getter and Setter methods
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public Integer getPatientId() { return patientId; }
    public void setPatientId(Integer patientId) { this.patientId = patientId; }
    
    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }
    
    public Integer getDiseaseId() { return diseaseId; }
    public void setDiseaseId(Integer diseaseId) { this.diseaseId = diseaseId; }
    
    public String getDiseaseCode() { return diseaseCode; }
    public void setDiseaseCode(String diseaseCode) { this.diseaseCode = diseaseCode; }
    
    public String getDiseaseName() { return diseaseName; }
    public void setDiseaseName(String diseaseName) { this.diseaseName = diseaseName; }
    
    public String getDiseaseICD() { return diseaseICD; }
    public void setDiseaseICD(String diseaseICD) { this.diseaseICD = diseaseICD; }
    
    public String getDiseaseCategory() { return diseaseCategory; }
    public void setDiseaseCategory(String diseaseCategory) { this.diseaseCategory = diseaseCategory; }
    
    public LocalDateTime getOrderTime() { return orderTime; }
    public void setOrderTime(LocalDateTime orderTime) { this.orderTime = orderTime; }
    
    public Integer getDiseaseType() { return diseaseType; }
    public void setDiseaseType(Integer diseaseType) { this.diseaseType = diseaseType; }
    
    public String getDiseaseTypeName() { return diseaseTypeName; }
    public void setDiseaseTypeName(String diseaseTypeName) { this.diseaseTypeName = diseaseTypeName; }
    
    public LocalDateTime getCreatedTime() { return createdTime; }
    public void setCreatedTime(LocalDateTime createdTime) { this.createdTime = createdTime; }
    
    public LocalDateTime getUpdatedTime() { return updatedTime; }
    public void setUpdatedTime(LocalDateTime updatedTime) { this.updatedTime = updatedTime; }
} 