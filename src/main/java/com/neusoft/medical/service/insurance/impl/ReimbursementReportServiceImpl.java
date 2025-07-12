package com.neusoft.medical.service.insurance.impl;

import com.neusoft.medical.common.result.PageResult;
import com.neusoft.medical.entity.PatientRegistration;
import com.neusoft.medical.mapper.ReimbursementReportMapper;
import com.neusoft.medical.service.insurance.ReimbursementReportService;
import com.neusoft.medical.vo.insurance.ReimbursementReportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 参保人员保险费用报销详情报表业务逻辑实现类
 * @author Neusoft
 * @date 2025-07-10
 */
@Service
public class ReimbursementReportServiceImpl implements ReimbursementReportService {

    @Autowired
    private ReimbursementReportMapper reimbursementReportMapper;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public PageResult<PatientRegistration> getPersonsPage(Integer pageNum, Integer pageSize, String personName) {
        try {
            // 构建查询条件
            Map<String, Object> params = new HashMap<>();
            if (StringUtils.hasText(personName)) {
                params.put("realName", personName.trim());
            }

            // 计算分页参数
            int offset = (pageNum - 1) * pageSize;
            params.put("offset", offset);
            params.put("limit", pageSize);

            // 查询总数
            Long total = reimbursementReportMapper.countByCondition(params);

            // 查询分页数据
            List<PatientRegistration> list = reimbursementReportMapper.selectPageByCondition(params);

            return PageResult.of(total, pageNum, pageSize, list);
        } catch (Exception e) {
            throw new RuntimeException("查询参保人员分页数据失败：" + e.getMessage(), e);
        }
    }

    @Override
    public List<PatientRegistration> searchPersons(String personName) {
        if (!StringUtils.hasText(personName)) {
            throw new IllegalArgumentException("患者姓名不能为空");
        }

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("realName", personName.trim());

            return reimbursementReportMapper.selectListByCondition(params);
        } catch (Exception e) {
            throw new RuntimeException("搜索参保人员失败：" + e.getMessage(), e);
        }
    }

    @Override
    public List<ReimbursementReportVO.CategoryRatioData> getDrugCategoryChart(Integer personId, String startDate, String endDate) {
        if (personId == null) {
            throw new IllegalArgumentException("患者ID不能为空");
        }

        try {
            Map<String, Object> params = buildTimeRangeParams(personId, startDate, endDate);
            List<Map<String, Object>> drugCategoryData = reimbursementReportMapper.selectDrugCategoryStatistics(params);

            // 计算总金额
            BigDecimal totalAmount = drugCategoryData.stream()
                    .map(data -> (BigDecimal) data.get("amount"))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            // 构建占比数据
            List<ReimbursementReportVO.CategoryRatioData> result = new ArrayList<>();
            for (Map<String, Object> data : drugCategoryData) {
                String categoryName = (String) data.get("category_name");
                BigDecimal amount = (BigDecimal) data.get("amount");
                Integer itemCount = ((Long) data.get("item_count")).intValue();

                // 计算占比
                BigDecimal percentage = BigDecimal.ZERO;
                if (totalAmount.compareTo(BigDecimal.ZERO) > 0) {
                    percentage = amount.divide(totalAmount, 4, RoundingMode.HALF_UP)
                            .multiply(new BigDecimal("100"))
                            .setScale(2, RoundingMode.HALF_UP);
                }

                result.add(new ReimbursementReportVO.CategoryRatioData(categoryName, amount, percentage, itemCount));
            }

            return result;
        } catch (Exception e) {
            throw new RuntimeException("查询药品费用分类占比失败：" + e.getMessage(), e);
        }
    }

    @Override
    public List<ReimbursementReportVO.CategoryRatioData> getExpenseTypeChart(Integer personId, String startDate, String endDate) {
        if (personId == null) {
            throw new IllegalArgumentException("患者ID不能为空");
        }

        try {
            Map<String, Object> params = buildTimeRangeParams(personId, startDate, endDate);
            List<Map<String, Object>> expenseTypeData = reimbursementReportMapper.selectExpenseTypeStatistics(params);

            // 计算总金额
            BigDecimal totalAmount = expenseTypeData.stream()
                    .map(data -> (BigDecimal) data.get("amount"))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            // 构建占比数据
            List<ReimbursementReportVO.CategoryRatioData> result = new ArrayList<>();
            for (Map<String, Object> data : expenseTypeData) {
                String typeName = (String) data.get("expense_type_name");
                BigDecimal amount = (BigDecimal) data.get("amount");
                Integer itemCount = ((Long) data.get("item_count")).intValue();

                // 计算占比
                BigDecimal percentage = BigDecimal.ZERO;
                if (totalAmount.compareTo(BigDecimal.ZERO) > 0) {
                    percentage = amount.divide(totalAmount, 4, RoundingMode.HALF_UP)
                            .multiply(new BigDecimal("100"))
                            .setScale(2, RoundingMode.HALF_UP);
                }

                result.add(new ReimbursementReportVO.CategoryRatioData(typeName, amount, percentage, itemCount));
            }

            return result;
        } catch (Exception e) {
            throw new RuntimeException("查询费用类型占比失败：" + e.getMessage(), e);
        }
    }

    @Override
    public ReimbursementReportVO getComprehensiveReport(Integer personId, String startDate, String endDate) {
        if (personId == null) {
            throw new IllegalArgumentException("患者ID不能为空");
        }

        try {
            Map<String, Object> params = buildTimeRangeParams(personId, startDate, endDate);

            // 获取患者基本信息
            PatientRegistration patient = reimbursementReportMapper.selectPatientById(personId);
            if (patient == null) {
                throw new IllegalArgumentException("未找到指定的患者信息");
            }

            // 构建综合报表数据
            ReimbursementReportVO report = new ReimbursementReportVO();
            report.setPatientId(patient.getId());
            report.setPatientName(patient.getRealName());
            report.setCaseNumber(patient.getCaseNumber());

            // 获取药品分类占比数据
            List<ReimbursementReportVO.CategoryRatioData> drugCategoryRatio = getDrugCategoryChart(personId, startDate, endDate);
            report.setDrugCategoryRatio(drugCategoryRatio);

            // 获取费用类型占比数据
            List<ReimbursementReportVO.CategoryRatioData> expenseTypeRatio = getExpenseTypeChart(personId, startDate, endDate);
            report.setExpenseTypeRatio(expenseTypeRatio);

            // 构建费用汇总
            ReimbursementReportVO.ExpenseSummary summary = buildExpenseSummary(params);
            report.setExpenseSummary(summary);
            report.setTotalAmount(summary.getTotalAmount());

            return report;
        } catch (Exception e) {
            throw new RuntimeException("查询综合费用报销详情失败：" + e.getMessage(), e);
        }
    }

    /**
     * 构建费用汇总数据
     */
    private ReimbursementReportVO.ExpenseSummary buildExpenseSummary(Map<String, Object> params) {
        // 获取费用汇总数据
        Map<String, Object> summaryData = reimbursementReportMapper.selectExpenseSummary(params);

        ReimbursementReportVO.ExpenseSummary summary = new ReimbursementReportVO.ExpenseSummary();

        // 设置各类费用
        summary.setCategoryADrugAmount(getAmountFromMap(summaryData, "category_a_drug_amount"));
        summary.setCategoryBDrugAmount(getAmountFromMap(summaryData, "category_b_drug_amount"));
        summary.setCategoryCDrugAmount(getAmountFromMap(summaryData, "category_c_drug_amount"));
        summary.setTreatmentAmount(getAmountFromMap(summaryData, "treatment_amount"));
        summary.setServiceAmount(getAmountFromMap(summaryData, "service_amount"));

        // 计算总药品费用
        BigDecimal totalDrugAmount = summary.getCategoryADrugAmount()
                .add(summary.getCategoryBDrugAmount())
                .add(summary.getCategoryCDrugAmount());
        summary.setTotalDrugAmount(totalDrugAmount);

        // 计算总费用
        BigDecimal totalAmount = totalDrugAmount
                .add(summary.getTreatmentAmount())
                .add(summary.getServiceAmount());
        summary.setTotalAmount(totalAmount);

        return summary;
    }

    /**
     * 从Map中安全获取金额值
     */
    private BigDecimal getAmountFromMap(Map<String, Object> map, String key) {
        Object value = map.get(key);
        if (value == null) {
            return BigDecimal.ZERO;
        }
        if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        }
        return new BigDecimal(value.toString());
    }

    /**
     * 构建时间范围查询参数
     */
    private Map<String, Object> buildTimeRangeParams(Integer personId, String startDate, String endDate) {
        Map<String, Object> params = new HashMap<>();
        params.put("personId", personId);

        if (StringUtils.hasText(startDate)) {
            try {
                LocalDateTime start = LocalDateTime.parse(startDate, DATE_FORMATTER);
                params.put("startDate", start);
            } catch (Exception e) {
                throw new IllegalArgumentException("开始日期格式错误，请使用 yyyy-MM-dd HH:mm:ss 格式");
            }
        }

        if (StringUtils.hasText(endDate)) {
            try {
                LocalDateTime end = LocalDateTime.parse(endDate, DATE_FORMATTER);
                params.put("endDate", end);
            } catch (Exception e) {
                throw new IllegalArgumentException("结束日期格式错误，请使用 yyyy-MM-dd HH:mm:ss 格式");
            }
        }

        return params;
    }
}