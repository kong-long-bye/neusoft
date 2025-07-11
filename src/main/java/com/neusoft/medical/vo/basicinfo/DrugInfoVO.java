package com.neusoft.medical.vo.basicinfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 药品信息数据传输对象
 * @author Neusoft
 * @date 2025-07-10
 */
@ApiModel(description = "药品信息传输对象")
public class DrugInfoVO {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "分类", required = true, example = "甲类")
    private String insuranceType;

    @ApiModelProperty(value = "药品中文名称", required = true, example = "阿莫西林胶囊")
    private String chinaName;

    @ApiModelProperty(value = "商品名", example = "安必仙")
    private String goodsName;

    @ApiModelProperty(value = "规格", example = "0.25g×24粒")
    private String specifications;

    @ApiModelProperty(value = "单位", example = "盒")
    private String drugUnit;

    @ApiModelProperty(value = "生产企业", example = "XX制药有限公司")
    private String drugManufacturer;

    @ApiModelProperty(value = "价格", example = "25.50")
    private BigDecimal drugPrice;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    // 构造方法
    public DrugInfoVO() {}

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