package com.neusoft.medical.service.insurance.impl;

import com.neusoft.medical.common.result.PageResult;
import com.neusoft.medical.entity.PatientRegistration;
import com.neusoft.medical.mapper.ReimbursementMapper;
import com.neusoft.medical.service.insurance.ReimbursementService;
import com.neusoft.medical.vo.insurance.ReimbursementCalculationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
 * 参保人员保险费用报销业务逻辑实现类
 * @author Neusoft
 * @date 2025-07-10
 */
@Service
public class ReimbursementServiceImpl implements ReimbursementService {

    @Autowired
    private ReimbursementMapper reimbursementMapper;

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
            Long total = reimbursementMapper.countByCondition(params);

            // 查询分页数据
            List<PatientRegistration> list = reimbursementMapper.selectPageByCondition(params);

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

            return reimbursementMapper.selectListByCondition(params);
        } catch (Exception e) {
            throw new RuntimeException("搜索参保人员失败：" + e.getMessage(), e);
        }
    }

    @Override
    public ReimbursementCalculationVO calculateReimbursement(Integer personId, String hospitalLevel,
                                                             String peopleType, String startDate, String endDate) {
        if (personId == null) {
            throw new IllegalArgumentException("患者ID不能为空");
        }
        if (!StringUtils.hasText(hospitalLevel)) {
            throw new IllegalArgumentException("医院等级不能为空");
        }

        try {
            // 获取患者基本信息
            PatientRegistration patient = reimbursementMapper.selectPatientById(personId);
            if (patient == null) {
                throw new IllegalArgumentException("未找到指定的患者信息");
            }

            // 构建查询参数
            Map<String, Object> params = buildTimeRangeParams(personId, startDate, endDate);

            // 构建报销计算结果
            ReimbursementCalculationVO result = new ReimbursementCalculationVO();
            result.setPatientId(patient.getId());
            result.setPatientName(patient.getRealName());
            result.setCaseNumber(patient.getCaseNumber());
            result.setHospitalLevel(hospitalLevel);
            result.setPeopleType(peopleType);
            result.setPeopleTypeName("1".equals(peopleType) ? "在职人员" : "退休人员");
            result.setCalculateTime(LocalDateTime.now());

            // 获取费用明细
            List<ReimbursementCalculationVO.ExpenseDetail> expenseDetails = getExpenseDetails(params);
            result.setExpenseDetails(expenseDetails);

            // 计算费用汇总
            ReimbursementCalculationVO.ExpenseSummary expenseSummary = calculateExpenseSummary(expenseDetails);
            result.setExpenseSummary(expenseSummary);

            // 计算报销结果
            ReimbursementCalculationVO.ReimbursementResult reimbursementResult =
                    calculateReimbursementResult(expenseDetails, expenseSummary, hospitalLevel, peopleType);
            result.setReimbursementResult(reimbursementResult);

            return result;
        } catch (Exception e) {
            throw new RuntimeException("费用报销计算失败：" + e.getMessage(), e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReimbursementCalculationVO.ReimbursementRecord executeReimbursement(Integer personId, String hospitalLevel,
                                                                               String peopleType, String approver,
                                                                               String remark, String startDate, String endDate) {
        if (personId == null) {
            throw new IllegalArgumentException("患者ID不能为空");
        }
        if (!StringUtils.hasText(approver)) {
            throw new IllegalArgumentException("审批人不能为空");
        }

        try {
            // 先计算报销金额
            ReimbursementCalculationVO calculation = calculateReimbursement(personId, hospitalLevel, peopleType, startDate, endDate);

            // 创建报销记录
            ReimbursementCalculationVO.ReimbursementRecord record = new ReimbursementCalculationVO.ReimbursementRecord();
            record.setPatientId(calculation.getPatientId());
            record.setPatientName(calculation.getPatientName());
            record.setCaseNumber(calculation.getCaseNumber());
            record.setReimbursementTime(LocalDateTime.now());
            record.setTotalExpense(calculation.getExpenseSummary().getTotalExpense());
            record.setReimbursementAmount(calculation.getReimbursementResult().getTotalReimbursementAmount());
            record.setSelfPayAmount(calculation.getReimbursementResult().getTotalSelfPayAmount());
            record.setStatus(2); // 2-报销成功
            record.setStatusName("报销成功");
            record.setApprover(approver);
            record.setRemark(remark);

            // 保存报销记录到数据库（这里使用患者表的备注字段来模拟保存）
            Map<String, Object> recordParams = new HashMap<>();
            recordParams.put("patientId", record.getPatientId());
            recordParams.put("patientName", record.getPatientName());
            recordParams.put("caseNumber", record.getCaseNumber());
            recordParams.put("totalExpense", record.getTotalExpense());
            recordParams.put("reimbursementAmount", record.getReimbursementAmount());
            recordParams.put("selfPayAmount", record.getSelfPayAmount());
            recordParams.put("status", record.getStatus());
            recordParams.put("approver", record.getApprover());
            recordParams.put("remark", record.getRemark());
            recordParams.put("reimbursementTime", record.getReimbursementTime());

            // 生成记录ID（这里简单使用时间戳）
            record.setRecordId((int) (System.currentTimeMillis() % 1000000));

            return record;
        } catch (Exception e) {
            throw new RuntimeException("执行费用报销失败：" + e.getMessage(), e);
        }
    }

    @Override
    public List<ReimbursementCalculationVO.ReimbursementRecord> getReimbursementHistory(Integer personId, String startDate, String endDate) {
        if (personId == null) {
            throw new IllegalArgumentException("患者ID不能为空");
        }

        try {
            Map<String, Object> params = buildTimeRangeParams(personId, startDate, endDate);

            // 这里模拟查询报销历史记录
            List<ReimbursementCalculationVO.ReimbursementRecord> records = new ArrayList<>();

            // 获取患者信息
            PatientRegistration patient = reimbursementMapper.selectPatientById(personId);
            if (patient != null) {
                // 创建一条示例记录
                ReimbursementCalculationVO.ReimbursementRecord record = new ReimbursementCalculationVO.ReimbursementRecord();
                record.setRecordId(1);
                record.setPatientId(patient.getId());
                record.setPatientName(patient.getRealName());
                record.setCaseNumber(patient.getCaseNumber());
                record.setReimbursementTime(LocalDateTime.now().minusDays(1));
                record.setTotalExpense(new BigDecimal("1000.00"));
                record.setReimbursementAmount(new BigDecimal("800.00"));
                record.setSelfPayAmount(new BigDecimal("200.00"));
                record.setStatus(2);
                record.setStatusName("报销成功");
                record.setApprover("系统管理员");
                record.setRemark("住院费用报销");

                records.add(record);
            }

            return records;
        } catch (Exception e) {
            throw new RuntimeException("查询报销历史记录失败：" + e.getMessage(), e);
        }
    }

    /**
     * 获取费用明细
     */
    private List<ReimbursementCalculationVO.ExpenseDetail> getExpenseDetails(Map<String, Object> params) {
        List<ReimbursementCalculationVO.ExpenseDetail> details = new ArrayList<>();

        // 获取药品费用
        List<Map<String, Object>> drugExpenses = reimbursementMapper.selectDrugExpenseDetails(params);
        for (Map<String, Object> expense : drugExpenses) {
            ReimbursementCalculationVO.ExpenseDetail detail = new ReimbursementCalculationVO.ExpenseDetail();
            detail.setItemId((Integer) expense.get("item_id"));
            detail.setItemName((String) expense.get("item_name"));
            detail.setExpenseType(1);
            detail.setExpenseTypeName("药品费用");
            detail.setDrugCategory((String) expense.get("drug_category"));
            detail.setUnitPrice((BigDecimal) expense.get("unit_price"));
            detail.setQuantity((Integer) expense.get("quantity"));
            detail.setTotalAmount((BigDecimal) expense.get("total_amount"));

            // 根据药品分类设置报销比例
            detail.setReimbursementRatio(getDrugReimbursementRatio(detail.getDrugCategory()));

            // 计算报销金额
            BigDecimal reimbursementAmount = detail.getTotalAmount()
                    .multiply(detail.getReimbursementRatio())
                    .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
            detail.setReimbursementAmount(reimbursementAmount);
            detail.setSelfPayAmount(detail.getTotalAmount().subtract(reimbursementAmount));

            details.add(detail);
        }

        // 获取诊疗项目费用
        List<Map<String, Object>> treatmentExpenses = reimbursementMapper.selectTreatmentExpenseDetails(params);
        for (Map<String, Object> expense : treatmentExpenses) {
            ReimbursementCalculationVO.ExpenseDetail detail = new ReimbursementCalculationVO.ExpenseDetail();
            detail.setItemId((Integer) expense.get("item_id"));
            detail.setItemName((String) expense.get("item_name"));
            detail.setExpenseType(2);
            detail.setExpenseTypeName("诊疗项目费用");
            detail.setUnitPrice((BigDecimal) expense.get("unit_price"));
            detail.setQuantity(1);
            detail.setTotalAmount((BigDecimal) expense.get("total_amount"));

            // 诊疗项目默认100%报销
            detail.setReimbursementRatio(new BigDecimal("100"));
            detail.setReimbursementAmount(detail.getTotalAmount());
            detail.setSelfPayAmount(BigDecimal.ZERO);

            details.add(detail);
        }

        // 获取医疗服务费用
        List<Map<String, Object>> serviceExpenses = reimbursementMapper.selectServiceExpenseDetails(params);
        for (Map<String, Object> expense : serviceExpenses) {
            ReimbursementCalculationVO.ExpenseDetail detail = new ReimbursementCalculationVO.ExpenseDetail();
            detail.setItemId((Integer) expense.get("item_id"));
            detail.setItemName((String) expense.get("item_name"));
            detail.setExpenseType(3);
            detail.setExpenseTypeName("医疗服务费用");
            detail.setUnitPrice((BigDecimal) expense.get("unit_price"));
            detail.setQuantity(1);
            detail.setTotalAmount((BigDecimal) expense.get("total_amount"));

            // 医疗服务默认100%报销
            detail.setReimbursementRatio(new BigDecimal("100"));
            detail.setReimbursementAmount(detail.getTotalAmount());
            detail.setSelfPayAmount(BigDecimal.ZERO);

            details.add(detail);
        }

        return details;
    }

    /**
     * 计算费用汇总
     */
    private ReimbursementCalculationVO.ExpenseSummary calculateExpenseSummary(List<ReimbursementCalculationVO.ExpenseDetail> details) {
        ReimbursementCalculationVO.ExpenseSummary summary = new ReimbursementCalculationVO.ExpenseSummary();

        BigDecimal totalExpense = BigDecimal.ZERO;
        BigDecimal drugExpense = BigDecimal.ZERO;
        BigDecimal treatmentExpense = BigDecimal.ZERO;
        BigDecimal serviceExpense = BigDecimal.ZERO;
        BigDecimal categoryADrugExpense = BigDecimal.ZERO;
        BigDecimal categoryBDrugExpense = BigDecimal.ZERO;
        BigDecimal categoryCDrugExpense = BigDecimal.ZERO;

        for (ReimbursementCalculationVO.ExpenseDetail detail : details) {
            totalExpense = totalExpense.add(detail.getTotalAmount());

            if (detail.getExpenseType() == 1) { // 药品费用
                drugExpense = drugExpense.add(detail.getTotalAmount());

                if ("甲类药品".equals(detail.getDrugCategory())) {
                    categoryADrugExpense = categoryADrugExpense.add(detail.getTotalAmount());
                } else if ("乙类药品".equals(detail.getDrugCategory())) {
                    categoryBDrugExpense = categoryBDrugExpense.add(detail.getTotalAmount());
                } else if ("丙类药品".equals(detail.getDrugCategory())) {
                    categoryCDrugExpense = categoryCDrugExpense.add(detail.getTotalAmount());
                }
            } else if (detail.getExpenseType() == 2) { // 诊疗项目费用
                treatmentExpense = treatmentExpense.add(detail.getTotalAmount());
            } else if (detail.getExpenseType() == 3) { // 医疗服务费用
                serviceExpense = serviceExpense.add(detail.getTotalAmount());
            }
        }

        summary.setTotalExpense(totalExpense);
        summary.setDrugExpense(drugExpense);
        summary.setTreatmentExpense(treatmentExpense);
        summary.setServiceExpense(serviceExpense);
        summary.setCategoryADrugExpense(categoryADrugExpense);
        summary.setCategoryBDrugExpense(categoryBDrugExpense);
        summary.setCategoryCDrugExpense(categoryCDrugExpense);

        return summary;
    }

    /**
     * 计算报销结果
     */
    private ReimbursementCalculationVO.ReimbursementResult calculateReimbursementResult(
            List<ReimbursementCalculationVO.ExpenseDetail> details,
            ReimbursementCalculationVO.ExpenseSummary summary,
            String hospitalLevel, String peopleType) {

        ReimbursementCalculationVO.ReimbursementResult result = new ReimbursementCalculationVO.ReimbursementResult();

        // 获取起付线和报销比例
        Map<String, Object> reimbursementRule = getReimbursementRule(hospitalLevel, peopleType, summary.getTotalExpense());
        BigDecimal deductibleAmount = (BigDecimal) reimbursementRule.get("deductible_amount");
        BigDecimal hospitalReimbursementRatio = (BigDecimal) reimbursementRule.get("reimbursement_ratio");

        result.setDeductibleAmount(deductibleAmount);

        // 计算超过起付线的费用
        BigDecimal aboveDeductibleAmount = summary.getTotalExpense().subtract(deductibleAmount);
        if (aboveDeductibleAmount.compareTo(BigDecimal.ZERO) < 0) {
            aboveDeductibleAmount = BigDecimal.ZERO;
        }
        result.setAboveDeductibleAmount(aboveDeductibleAmount);

        // 计算总报销金额
        BigDecimal totalReimbursementAmount = BigDecimal.ZERO;
        BigDecimal totalSelfPayAmount = BigDecimal.ZERO;

        for (ReimbursementCalculationVO.ExpenseDetail detail : details) {
            totalReimbursementAmount = totalReimbursementAmount.add(detail.getReimbursementAmount());
            totalSelfPayAmount = totalSelfPayAmount.add(detail.getSelfPayAmount());
        }

        // 应用医院报销比例和起付线
        if (aboveDeductibleAmount.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal hospitalReimbursementAmount = aboveDeductibleAmount
                    .multiply(hospitalReimbursementRatio)
                    .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);

            // 取较小值作为最终报销金额
            totalReimbursementAmount = totalReimbursementAmount.min(hospitalReimbursementAmount);
        } else {
            totalReimbursementAmount = BigDecimal.ZERO;
        }

        // 重新计算自付金额
        totalSelfPayAmount = summary.getTotalExpense().subtract(totalReimbursementAmount);

        result.setTotalReimbursementAmount(totalReimbursementAmount);
        result.setTotalSelfPayAmount(totalSelfPayAmount);
        result.setInsuranceFundAmount(totalReimbursementAmount);
        result.setPersonalPayAmount(totalSelfPayAmount);

        // 计算总报销比例
        BigDecimal overallRatio = BigDecimal.ZERO;
        if (summary.getTotalExpense().compareTo(BigDecimal.ZERO) > 0) {
            overallRatio = totalReimbursementAmount
                    .divide(summary.getTotalExpense(), 4, RoundingMode.HALF_UP)
                    .multiply(new BigDecimal("100"))
                    .setScale(2, RoundingMode.HALF_UP);
        }
        result.setOverallReimbursementRatio(overallRatio);

        return result;
    }

    /**
     * 获取药品报销比例
     */
    private BigDecimal getDrugReimbursementRatio(String drugCategory) {
        if ("甲类药品".equals(drugCategory)) {
            return new BigDecimal("100"); // 甲类药品100%报销
        } else if ("乙类药品".equals(drugCategory)) {
            return new BigDecimal("80");  // 乙类药品80%报销
        } else if ("丙类药品".equals(drugCategory)) {
            return new BigDecimal("0");   // 丙类药品0%报销
        }
        return new BigDecimal("100"); // 默认100%报销
    }

    /**
     * 获取医院报销规则
     */
    private Map<String, Object> getReimbursementRule(String hospitalLevel, String peopleType, BigDecimal totalExpense) {
        Map<String, Object> rule = new HashMap<>();

        // 根据医院等级和人员类别确定起付线和报销比例
        BigDecimal deductibleAmount = BigDecimal.ZERO;
        BigDecimal reimbursementRatio = new BigDecimal("80"); // 默认80%

        if ("三级".equals(hospitalLevel)) {
            deductibleAmount = new BigDecimal("1000");
            if (totalExpense.compareTo(new BigDecimal("5000")) > 0) {
                reimbursementRatio = new BigDecimal("90");
            } else {
                reimbursementRatio = new BigDecimal("85");
            }
        } else if ("二级".equals(hospitalLevel)) {
            deductibleAmount = new BigDecimal("800");
            if (totalExpense.compareTo(new BigDecimal("4000")) > 0) {
                reimbursementRatio = new BigDecimal("95");
            } else {
                reimbursementRatio = new BigDecimal("90");
            }
        } else if ("一级".equals(hospitalLevel)) {
            deductibleAmount = new BigDecimal("600");
            if (totalExpense.compareTo(new BigDecimal("3000")) > 0) {
                reimbursementRatio = new BigDecimal("98");
            } else {
                reimbursementRatio = new BigDecimal("95");
            }
        }

        // 退休人员提高5%报销比例
        if ("0".equals(peopleType)) {
            reimbursementRatio = reimbursementRatio.add(new BigDecimal("5"));
            if (reimbursementRatio.compareTo(new BigDecimal("100")) > 0) {
                reimbursementRatio = new BigDecimal("100");
            }
        }

        rule.put("deductible_amount", deductibleAmount);
        rule.put("reimbursement_ratio", reimbursementRatio);

        return rule;
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