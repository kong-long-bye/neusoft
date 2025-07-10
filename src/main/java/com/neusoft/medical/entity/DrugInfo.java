package com.neusoft.medical.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 药品信息表实体
 * @author Neusoft
 * @date 2025-07-10
 */
@ApiModel(description = "药品信息")
public class DrugInfo {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "分类")
    private String insuranceType;

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

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    // 构造方法
    public DrugInfo() {}

    // Getter and Setter methods
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getInsuranceType() { return insuranceType; }
    public void setInsuranceType(String insuranceType) { this.insuranceType = insuranceType; }

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

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public LocalDateTime getCreatedTime() { return createdTime; }
    public void setCreatedTime(LocalDateTime createdTime) { this.createdTime = createdTime; }

    public LocalDateTime getUpdatedTime() { return updatedTime; }
    public void setUpdatedTime(LocalDateTime updatedTime) { this.updatedTime = updatedTime; }
}
