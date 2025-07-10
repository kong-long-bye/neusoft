package com.neusoft.medical.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;

/**
 * 疾病信息表实体
 * @author Neusoft
 * @date 2025-07-10
 */
@ApiModel(description = "疾病信息")
public class DiseaseInfo {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "疾病编码")
    private String diseaseCode;

    @ApiModelProperty(value = "疾病名称")
    private String diseaseName;

    @ApiModelProperty(value = "国际疾病ICD编码")
    private String diseaseICD;

    @ApiModelProperty(value = "疾病类型")
    private String diseaseCategory;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    // 构造方法
    public DiseaseInfo() {}

    // Getter and Setter methods
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getDiseaseCode() { return diseaseCode; }
    public void setDiseaseCode(String diseaseCode) { this.diseaseCode = diseaseCode; }

    public String getDiseaseName() { return diseaseName; }
    public void setDiseaseName(String diseaseName) { this.diseaseName = diseaseName; }

    public String getDiseaseICD() { return diseaseICD; }
    public void setDiseaseICD(String diseaseICD) { this.diseaseICD = diseaseICD; }

    public String getDiseaseCategory() { return diseaseCategory; }
    public void setDiseaseCategory(String diseaseCategory) { this.diseaseCategory = diseaseCategory; }

    public LocalDateTime getCreatedTime() { return createdTime; }
    public void setCreatedTime(LocalDateTime createdTime) { this.createdTime = createdTime; }

    public LocalDateTime getUpdatedTime() { return updatedTime; }
    public void setUpdatedTime(LocalDateTime updatedTime) { this.updatedTime = updatedTime; }
}
