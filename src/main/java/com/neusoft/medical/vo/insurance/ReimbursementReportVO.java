package com.neusoft.medical.vo.insurance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.List;

/**
 * 报销详情报表数据传输对象
 * @author Neusoft
 * @date 2025-07-10
 */
@ApiModel(description = "报销详情报表数据传输对象")
public class ReimbursementReportVO {

    @ApiModelProperty(value = "患者ID")
    private Integer patientId;

    @ApiModelProperty(value = "患者姓名")
    private String patientName;

    @ApiModelProperty(value = "住院号")
    private String caseNumber;

    @ApiModelProperty(value = "总费用")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "药品费用占比数据")
    private List<CategoryRatioData> drugCategoryRatio;

    @ApiModelProperty(value = "费用类型占比数据")
    private List<CategoryRatioData> expenseTypeRatio;

    @ApiModelProperty(value = "费用明细汇总")
    private ExpenseSummary expenseSummary;

    // 构造方法
    public ReimbursementReportVO() {}

    // 内部类：分类占比数据
    @ApiModel(description = "分类占比数据")
    public static class CategoryRatioData {
        @ApiModelProperty(value = "分类名称")
        private String categoryName;

        @ApiModelProperty(value = "分类金额")
        private BigDecimal amount;

        @ApiModelProperty(value = "占比百分比")
        private BigDecimal percentage;

        @ApiModelProperty(value = "项目数量")
        private Integer itemCount;

        public CategoryRatioData() {}

        public CategoryRatioData(String categoryName, BigDecimal amount, BigDecimal percentage, Integer itemCount) {
            this.categoryName = categoryName;
            this.amount = amount;
            this.percentage = percentage;
            this.itemCount = itemCount;
        }

        // Getter and Setter methods
        public String getCategoryName() { return categoryName; }
        public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

        public BigDecimal getAmount() { return amount; }
        public void setAmount(BigDecimal amount) { this.amount = amount; }

        public BigDecimal getPercentage() { return percentage; }
        public void setPercentage(BigDecimal percentage) { this.percentage = percentage; }

        public Integer getItemCount() { return itemCount; }
        public void setItemCount(Integer itemCount) { this.itemCount = itemCount; }
    }

    // 内部类：费用汇总
    @ApiModel(description = "费用汇总")
    public static class ExpenseSummary {
        @ApiModelProperty(value = "甲类药品费用")
        private BigDecimal categoryADrugAmount;

        @ApiModelProperty(value = "乙类药品费用")
        private BigDecimal categoryBDrugAmount;

        @ApiModelProperty(value = "丙类药品费用")
        private BigDecimal categoryCDrugAmount;

        @ApiModelProperty(value = "诊疗项目费用")
        private BigDecimal treatmentAmount;

        @ApiModelProperty(value = "医疗服务费用")
        private BigDecimal serviceAmount;

        @ApiModelProperty(value = "总药品费用")
        private BigDecimal totalDrugAmount;

        @ApiModelProperty(value = "总费用")
        private BigDecimal totalAmount;

        public ExpenseSummary() {}

        // Getter and Setter methods
        public BigDecimal getCategoryADrugAmount() { return categoryADrugAmount; }
        public void setCategoryADrugAmount(BigDecimal categoryADrugAmount) { this.categoryADrugAmount = categoryADrugAmount; }

        public BigDecimal getCategoryBDrugAmount() { return categoryBDrugAmount; }
        public void setCategoryBDrugAmount(BigDecimal categoryBDrugAmount) { this.categoryBDrugAmount = categoryBDrugAmount; }

        public BigDecimal getCategoryCDrugAmount() { return categoryCDrugAmount; }
        public void setCategoryCDrugAmount(BigDecimal categoryCDrugAmount) { this.categoryCDrugAmount = categoryCDrugAmount; }

        public BigDecimal getTreatmentAmount() { return treatmentAmount; }
        public void setTreatmentAmount(BigDecimal treatmentAmount) { this.treatmentAmount = treatmentAmount; }

        public BigDecimal getServiceAmount() { return serviceAmount; }
        public void setServiceAmount(BigDecimal serviceAmount) { this.serviceAmount = serviceAmount; }

        public BigDecimal getTotalDrugAmount() { return totalDrugAmount; }
        public void setTotalDrugAmount(BigDecimal totalDrugAmount) { this.totalDrugAmount = totalDrugAmount; }

        public BigDecimal getTotalAmount() { return totalAmount; }
        public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    }

    // Getter and Setter methods
    public Integer getPatientId() { return patientId; }
    public void setPatientId(Integer patientId) { this.patientId = patientId; }

    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }

    public String getCaseNumber() { return caseNumber; }
    public void setCaseNumber(String caseNumber) { this.caseNumber = caseNumber; }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

    public List<CategoryRatioData> getDrugCategoryRatio() { return drugCategoryRatio; }
    public void setDrugCategoryRatio(List<CategoryRatioData> drugCategoryRatio) { this.drugCategoryRatio = drugCategoryRatio; }

    public List<CategoryRatioData> getExpenseTypeRatio() { return expenseTypeRatio; }
    public void setExpenseTypeRatio(List<CategoryRatioData> expenseTypeRatio) { this.expenseTypeRatio = expenseTypeRatio; }

    public ExpenseSummary getExpenseSummary() { return expenseSummary; }
    public void setExpenseSummary(ExpenseSummary expenseSummary) { this.expenseSummary = expenseSummary; }
}