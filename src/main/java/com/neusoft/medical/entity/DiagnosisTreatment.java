package com.neusoft.medical.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 诊疗项目表实体
 * @author Neusoft
 * @date 2025-07-10
 */
@ApiModel(description = "诊疗项目信息")
public class DiagnosisTreatment {

    @ApiModelProperty(value = "主键")
    private Integer id;

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

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    // 构造方法
    public DiagnosisTreatment() {}

    // Getter and Setter methods
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

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

    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }

    public LocalDateTime getCreatedTime() { return createdTime; }
    public void setCreatedTime(LocalDateTime createdTime) { this.createdTime = createdTime; }

    public LocalDateTime getUpdatedTime() { return updatedTime; }
    public void setUpdatedTime(LocalDateTime updatedTime) { this.updatedTime = updatedTime; }
}