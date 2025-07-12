package com.neusoft.medical.vo.insurance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 费用详情数据传输对象
 * @author Neusoft
 * @date 2025-07-10
 */
@ApiModel(description = "费用详情数据传输对象")
public class ExpenseDetailVO {

    @ApiModelProperty(value = "患者ID")
    private Integer patientId;

    @ApiModelProperty(value = "患者姓名")
    private String patientName;

    @ApiModelProperty(value = "住院号")
    private String caseNumber;

    @ApiModelProperty(value = "费用项目ID")
    private Integer itemId;

    @ApiModelProperty(value = "费用项目名称")
    private String itemName;

    @ApiModelProperty(value = "费用类型：1-药品 2-诊疗项目 3-医疗服务")
    private Integer expenseType;

    @ApiModelProperty(value = "费用类型名称")
    private String expenseTypeName;

    @ApiModelProperty(value = "项目编码")
    private String itemCode;

    @ApiModelProperty(value = "规格/说明")
    private String specification;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;

    @ApiModelProperty(value = "数量/频次")
    private Integer quantity;

    @ApiModelProperty(value = "总金额")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "开立时间")
    private LocalDateTime orderTime;

    @ApiModelProperty(value = "医嘱内容")
    private String doctorOrder;

    @ApiModelProperty(value = "用法")
    private String useMethod;

    @ApiModelProperty(value = "药品分类（甲类/乙类/丙类）")
    private String drugCategory;

    @ApiModelProperty(value = "生产企业")
    private String manufacturer;

    @ApiModelProperty(value = "医嘱状态：1-正常执行 0-作废 2-停止")
    private Integer status;

    @ApiModelProperty(value = "状态名称")
    private String statusName;

    // 构造方法
    public ExpenseDetailVO() {}

    // Getter and Setter methods
    public Integer getPatientId() { return patientId; }
    public void setPatientId(Integer patientId) { this.patientId = patientId; }

    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }

    public String getCaseNumber() { return caseNumber; }
    public void setCaseNumber(String caseNumber) { this.caseNumber = caseNumber; }

    public Integer getItemId() { return itemId; }
    public void setItemId(Integer itemId) { this.itemId = itemId; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public Integer getExpenseType() { return expenseType; }
    public void setExpenseType(Integer expenseType) { this.expenseType = expenseType; }

    public String getExpenseTypeName() { return expenseTypeName; }
    public void setExpenseTypeName(String expenseTypeName) { this.expenseTypeName = expenseTypeName; }

    public String getItemCode() { return itemCode; }
    public void setItemCode(String itemCode) { this.itemCode = itemCode; }

    public String getSpecification() { return specification; }
    public void setSpecification(String specification) { this.specification = specification; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

    public LocalDateTime getOrderTime() { return orderTime; }
    public void setOrderTime(LocalDateTime orderTime) { this.orderTime = orderTime; }

    public String getDoctorOrder() { return doctorOrder; }
    public void setDoctorOrder(String doctorOrder) { this.doctorOrder = doctorOrder; }

    public String getUseMethod() { return useMethod; }
    public void setUseMethod(String useMethod) { this.useMethod = useMethod; }

    public String getDrugCategory() { return drugCategory; }
    public void setDrugCategory(String drugCategory) { this.drugCategory = drugCategory; }

    public String getManufacturer() { return manufacturer; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }

    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }

    public String getStatusName() { return statusName; }
    public void setStatusName(String statusName) { this.statusName = statusName; }
}