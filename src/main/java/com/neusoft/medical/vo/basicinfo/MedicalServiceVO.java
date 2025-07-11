package com.neusoft.medical.vo.basicinfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 医疗服务设施数据传输对象
 * @author Neusoft
 * @date 2025-07-10
 */
@ApiModel(description = "医疗服务设施传输对象")
public class MedicalServiceVO {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "项目类别", required = true, example = "病房")
    private String medicalType;

    @ApiModelProperty(value = "项目编码", required = true, example = "BF001")
    private String medicalNumber;

    @ApiModelProperty(value = "项目国家编码", example = "510101001")
    private String countryNumber;

    @ApiModelProperty(value = "项目名称", required = true, example = "普通病房床位费")
    private String medicalName;

    @ApiModelProperty(value = "项目说明", example = "普通病房床位日费用")
    private String medicalInfo;

    @ApiModelProperty(value = "除外内容", example = "重症监护除外")
    private String medicalExclude;

    @ApiModelProperty(value = "计价单位", example = "床日")
    private String medicalUnit;

    @ApiModelProperty(value = "价格", example = "80.00")
    private BigDecimal medicalPrice;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    // 构造方法
    public MedicalServiceVO() {}

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