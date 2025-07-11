package com.neusoft.medical.vo.medical;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 患者药品处方VO
 * @author Neusoft
 * @date 2025-07-10
 */
@ApiModel(description = "患者药品处方信息")
public class PatientPrescriptionVO {
    
    @ApiModelProperty(value = "主键")
    private Integer id;
    
    @ApiModelProperty(value = "患者ID")
    private Integer patientId;
    
    @ApiModelProperty(value = "患者姓名")
    private String patientName;
    
    @ApiModelProperty(value = "药品ID")
    private Integer drugId;
    
    @ApiModelProperty(value = "药品中文名称")
    private String chinaName;
    
    @ApiModelProperty(value = "商品名")
    private String goodsName;
    
    @ApiModelProperty(value = "规格")
    private String specifications;
    
    @ApiModelProperty(value = "单位")
    private String drugUnit;
    
    @ApiModelProperty(value = "生产企业")
    private String drugManufacturer;
    
    @ApiModelProperty(value = "价格")
    private BigDecimal drugPrice;
    
    @ApiModelProperty(value = "分类")
    private String insuranceType;
    
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
    
    @ApiModelProperty(value = "医嘱状态：1-正常执行 0-作废 2-停止")
    private Integer status;
    
    @ApiModelProperty(value = "医嘱状态名称")
    private String statusName;
    
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;
    
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;
    
    // 构造方法
    public PatientPrescriptionVO() {}
    
    // Getter and Setter methods
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public Integer getPatientId() { return patientId; }
    public void setPatientId(Integer patientId) { this.patientId = patientId; }
    
    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }
    
    public Integer getDrugId() { return drugId; }
    public void setDrugId(Integer drugId) { this.drugId = drugId; }
    
    public String getChinaName() { return chinaName; }
    public void setChinaName(String chinaName) { this.chinaName = chinaName; }
    
    public String getGoodsName() { return goodsName; }
    public void setGoodsName(String goodsName) { this.goodsName = goodsName; }
    
    public String getSpecifications() { return specifications; }
    public void setSpecifications(String specifications) { this.specifications = specifications; }
    
    public String getDrugUnit() { return drugUnit; }
    public void setDrugUnit(String drugUnit) { this.drugUnit = drugUnit; }
    
    public String getDrugManufacturer() { return drugManufacturer; }
    public void setDrugManufacturer(String drugManufacturer) { this.drugManufacturer = drugManufacturer; }
    
    public BigDecimal getDrugPrice() { return drugPrice; }
    public void setDrugPrice(BigDecimal drugPrice) { this.drugPrice = drugPrice; }
    
    public String getInsuranceType() { return insuranceType; }
    public void setInsuranceType(String insuranceType) { this.insuranceType = insuranceType; }
    
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
    
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    
    public String getStatusName() { return statusName; }
    public void setStatusName(String statusName) { this.statusName = statusName; }
    
    public LocalDateTime getCreatedTime() { return createdTime; }
    public void setCreatedTime(LocalDateTime createdTime) { this.createdTime = createdTime; }
    
    public LocalDateTime getUpdatedTime() { return updatedTime; }
    public void setUpdatedTime(LocalDateTime updatedTime) { this.updatedTime = updatedTime; }
} 