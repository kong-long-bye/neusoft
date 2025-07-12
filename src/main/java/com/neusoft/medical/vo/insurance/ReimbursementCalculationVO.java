package com.neusoft.medical.vo.insurance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 报销计算数据传输对象
 * @author Neusoft
 * @date 2025-07-10
 */
@ApiModel(description = "报销计算数据传输对象")
public class ReimbursementCalculationVO {

    @ApiModelProperty(value = "患者ID")
    private Integer patientId;

    @ApiModelProperty(value = "患者姓名")
    private String patientName;

    @ApiModelProperty(value = "住院号")
    private String caseNumber;

    @ApiModelProperty(value = "医院等级")
    private String hospitalLevel;

    @ApiModelProperty(value = "人员类别：1-在职 0-退休")
    private String peopleType;

    @ApiModelProperty(value = "人员类别名称")
    private String peopleTypeName;

    @ApiModelProperty(value = "费用明细列表")
    private List<ExpenseDetail> expenseDetails;

    @ApiModelProperty(value = "费用汇总")
    private ExpenseSummary expenseSummary;

    @ApiModelProperty(value = "报销计算结果")
    private ReimbursementResult reimbursementResult;

    @ApiModelProperty(value = "计算时间")
    private LocalDateTime calculateTime;

    // 内部类：费用明细
    @ApiModel(description = "费用明细")
    public static class ExpenseDetail {
        @ApiModelProperty(value = "费用项目ID")
        private Integer itemId;

        @ApiModelProperty(value = "费用项目名称")
        private String itemName;

        @ApiModelProperty(value = "费用类型：1-药品 2-诊疗项目 3-医疗服务")
        private Integer expenseType;

        @ApiModelProperty(value = "费用类型名称")
        private String expenseTypeName;

        @ApiModelProperty(value = "药品分类（甲类/乙类/丙类）")
        private String drugCategory;

        @ApiModelProperty(value = "单价")
        private BigDecimal unitPrice;

        @ApiModelProperty(value = "数量")
        private Integer quantity;

        @ApiModelProperty(value = "费用小计")
        private BigDecimal totalAmount;

        @ApiModelProperty(value = "报销比例")
        private BigDecimal reimbursementRatio;

        @ApiModelProperty(value = "报销金额")
        private BigDecimal reimbursementAmount;

        @ApiModelProperty(value = "自付金额")
        private BigDecimal selfPayAmount;

        public ExpenseDetail() {}

        // Getter and Setter methods
        public Integer getItemId() { return itemId; }
        public void setItemId(Integer itemId) { this.itemId = itemId; }

        public String getItemName() { return itemName; }
        public void setItemName(String itemName) { this.itemName = itemName; }

        public Integer getExpenseType() { return expenseType; }
        public void setExpenseType(Integer expenseType) { this.expenseType = expenseType; }

        public String getExpenseTypeName() { return expenseTypeName; }
        public void setExpenseTypeName(String expenseTypeName) { this.expenseTypeName = expenseTypeName; }

        public String getDrugCategory() { return drugCategory; }
        public void setDrugCategory(String drugCategory) { this.drugCategory = drugCategory; }

        public BigDecimal getUnitPrice() { return unitPrice; }
        public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }

        public Integer getQuantity() { return quantity; }
        public void setQuantity(Integer quantity) { this.quantity = quantity; }

        public BigDecimal getTotalAmount() { return totalAmount; }
        public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

        public BigDecimal getReimbursementRatio() { return reimbursementRatio; }
        public void setReimbursementRatio(BigDecimal reimbursementRatio) { this.reimbursementRatio = reimbursementRatio; }

        public BigDecimal getReimbursementAmount() { return reimbursementAmount; }
        public void setReimbursementAmount(BigDecimal reimbursementAmount) { this.reimbursementAmount = reimbursementAmount; }

        public BigDecimal getSelfPayAmount() { return selfPayAmount; }
        public void setSelfPayAmount(BigDecimal selfPayAmount) { this.selfPayAmount = selfPayAmount; }
    }

    // 内部类：费用汇总
    @ApiModel(description = "费用汇总")
    public static class ExpenseSummary {
        @ApiModelProperty(value = "总费用")
        private BigDecimal totalExpense;

        @ApiModelProperty(value = "药品费用")
        private BigDecimal drugExpense;

        @ApiModelProperty(value = "诊疗项目费用")
        private BigDecimal treatmentExpense;

        @ApiModelProperty(value = "医疗服务费用")
        private BigDecimal serviceExpense;

        @ApiModelProperty(value = "甲类药品费用")
        private BigDecimal categoryADrugExpense;

        @ApiModelProperty(value = "乙类药品费用")
        private BigDecimal categoryBDrugExpense;

        @ApiModelProperty(value = "丙类药品费用")
        private BigDecimal categoryCDrugExpense;

        public ExpenseSummary() {}

        // Getter and Setter methods
        public BigDecimal getTotalExpense() { return totalExpense; }
        public void setTotalExpense(BigDecimal totalExpense) { this.totalExpense = totalExpense; }

        public BigDecimal getDrugExpense() { return drugExpense; }
        public void setDrugExpense(BigDecimal drugExpense) { this.drugExpense = drugExpense; }

        public BigDecimal getTreatmentExpense() { return treatmentExpense; }
        public void setTreatmentExpense(BigDecimal treatmentExpense) { this.treatmentExpense = treatmentExpense; }

        public BigDecimal getServiceExpense() { return serviceExpense; }
        public void setServiceExpense(BigDecimal serviceExpense) { this.serviceExpense = serviceExpense; }

        public BigDecimal getCategoryADrugExpense() { return categoryADrugExpense; }
        public void setCategoryADrugExpense(BigDecimal categoryADrugExpense) { this.categoryADrugExpense = categoryADrugExpense; }

        public BigDecimal getCategoryBDrugExpense() { return categoryBDrugExpense; }
        public void setCategoryBDrugExpense(BigDecimal categoryBDrugExpense) { this.categoryBDrugExpense = categoryBDrugExpense; }

        public BigDecimal getCategoryCDrugExpense() { return categoryCDrugExpense; }
        public void setCategoryCDrugExpense(BigDecimal categoryCDrugExpense) { this.categoryCDrugExpense = categoryCDrugExpense; }
    }

    // 内部类：报销计算结果
    @ApiModel(description = "报销计算结果")
    public static class ReimbursementResult {
        @ApiModelProperty(value = "起付线金额")
        private BigDecimal deductibleAmount;

        @ApiModelProperty(value = "超过起付线的费用")
        private BigDecimal aboveDeductibleAmount;

        @ApiModelProperty(value = "总报销金额")
        private BigDecimal totalReimbursementAmount;

        @ApiModelProperty(value = "总自付金额")
        private BigDecimal totalSelfPayAmount;

        @ApiModelProperty(value = "医保基金支付金额")
        private BigDecimal insuranceFundAmount;

        @ApiModelProperty(value = "个人支付金额")
        private BigDecimal personalPayAmount;

        @ApiModelProperty(value = "报销比例")
        private BigDecimal overallReimbursementRatio;

        public ReimbursementResult() {}

        // Getter and Setter methods
        public BigDecimal getDeductibleAmount() { return deductibleAmount; }
        public void setDeductibleAmount(BigDecimal deductibleAmount) { this.deductibleAmount = deductibleAmount; }

        public BigDecimal getAboveDeductibleAmount() { return aboveDeductibleAmount; }
        public void setAboveDeductibleAmount(BigDecimal aboveDeductibleAmount) { this.aboveDeductibleAmount = aboveDeductibleAmount; }

        public BigDecimal getTotalReimbursementAmount() { return totalReimbursementAmount; }
        public void setTotalReimbursementAmount(BigDecimal totalReimbursementAmount) { this.totalReimbursementAmount = totalReimbursementAmount; }

        public BigDecimal getTotalSelfPayAmount() { return totalSelfPayAmount; }
        public void setTotalSelfPayAmount(BigDecimal totalSelfPayAmount) { this.totalSelfPayAmount = totalSelfPayAmount; }

        public BigDecimal getInsuranceFundAmount() { return insuranceFundAmount; }
        public void setInsuranceFundAmount(BigDecimal insuranceFundAmount) { this.insuranceFundAmount = insuranceFundAmount; }

        public BigDecimal getPersonalPayAmount() { return personalPayAmount; }
        public void setPersonalPayAmount(BigDecimal personalPayAmount) { this.personalPayAmount = personalPayAmount; }

        public BigDecimal getOverallReimbursementRatio() { return overallReimbursementRatio; }
        public void setOverallReimbursementRatio(BigDecimal overallReimbursementRatio) { this.overallReimbursementRatio = overallReimbursementRatio; }
    }

    // 内部类：报销记录
    @ApiModel(description = "报销记录")
    public static class ReimbursementRecord {
        @ApiModelProperty(value = "记录ID")
        private Integer recordId;

        @ApiModelProperty(value = "患者ID")
        private Integer patientId;

        @ApiModelProperty(value = "患者姓名")
        private String patientName;

        @ApiModelProperty(value = "住院号")
        private String caseNumber;

        @ApiModelProperty(value = "报销时间")
        private LocalDateTime reimbursementTime;

        @ApiModelProperty(value = "总费用")
        private BigDecimal totalExpense;

        @ApiModelProperty(value = "报销金额")
        private BigDecimal reimbursementAmount;

        @ApiModelProperty(value = "自付金额")
        private BigDecimal selfPayAmount;

        @ApiModelProperty(value = "报销状态：1-计算完成 2-报销成功 3-报销失败")
        private Integer status;

        @ApiModelProperty(value = "状态名称")
        private String statusName;

        @ApiModelProperty(value = "审批人")
        private String approver;

        @ApiModelProperty(value = "备注")
        private String remark;

        public ReimbursementRecord() {}

        // Getter and Setter methods
        public Integer getRecordId() { return recordId; }
        public void setRecordId(Integer recordId) { this.recordId = recordId; }

        public Integer getPatientId() { return patientId; }
        public void setPatientId(Integer patientId) { this.patientId = patientId; }

        public String getPatientName() { return patientName; }
        public void setPatientName(String patientName) { this.patientName = patientName; }

        public String getCaseNumber() { return caseNumber; }
        public void setCaseNumber(String caseNumber) { this.caseNumber = caseNumber; }

        public LocalDateTime getReimbursementTime() { return reimbursementTime; }
        public void setReimbursementTime(LocalDateTime reimbursementTime) { this.reimbursementTime = reimbursementTime; }

        public BigDecimal getTotalExpense() { return totalExpense; }
        public void setTotalExpense(BigDecimal totalExpense) { this.totalExpense = totalExpense; }

        public BigDecimal getReimbursementAmount() { return reimbursementAmount; }
        public void setReimbursementAmount(BigDecimal reimbursementAmount) { this.reimbursementAmount = reimbursementAmount; }

        public BigDecimal getSelfPayAmount() { return selfPayAmount; }
        public void setSelfPayAmount(BigDecimal selfPayAmount) { this.selfPayAmount = selfPayAmount; }

        public Integer getStatus() { return status; }
        public void setStatus(Integer status) { this.status = status; }

        public String getStatusName() { return statusName; }
        public void setStatusName(String statusName) { this.statusName = statusName; }

        public String getApprover() { return approver; }
        public void setApprover(String approver) { this.approver = approver; }

        public String getRemark() { return remark; }
        public void setRemark(String remark) { this.remark = remark; }
    }

    // 构造方法
    public ReimbursementCalculationVO() {}

    // Getter and Setter methods
    public Integer getPatientId() { return patientId; }
    public void setPatientId(Integer patientId) { this.patientId = patientId; }

    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }

    public String getCaseNumber() { return caseNumber; }
    public void setCaseNumber(String caseNumber) { this.caseNumber = caseNumber; }

    public String getHospitalLevel() { return hospitalLevel; }
    public void setHospitalLevel(String hospitalLevel) { this.hospitalLevel = hospitalLevel; }

    public String getPeopleType() { return peopleType; }
    public void setPeopleType(String peopleType) { this.peopleType = peopleType; }

    public String getPeopleTypeName() { return peopleTypeName; }
    public void setPeopleTypeName(String peopleTypeName) { this.peopleTypeName = peopleTypeName; }

    public List<ExpenseDetail> getExpenseDetails() { return expenseDetails; }
    public void setExpenseDetails(List<ExpenseDetail> expenseDetails) { this.expenseDetails = expenseDetails; }

    public ExpenseSummary getExpenseSummary() { return expenseSummary; }
    public void setExpenseSummary(ExpenseSummary expenseSummary) { this.expenseSummary = expenseSummary; }

    public ReimbursementResult getReimbursementResult() { return reimbursementResult; }
    public void setReimbursementResult(ReimbursementResult reimbursementResult) { this.reimbursementResult = reimbursementResult; }

    public LocalDateTime getCalculateTime() { return calculateTime; }
    public void setCalculateTime(LocalDateTime calculateTime) { this.calculateTime = calculateTime; }
}