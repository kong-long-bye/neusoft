package com.neusoft.medical.service.insurance.impl;

import com.neusoft.medical.common.result.PageResult;
import com.neusoft.medical.entity.PatientRegistration;
import com.neusoft.medical.mapper.PersonExpenseMapper;
import com.neusoft.medical.service.insurance.PersonExpenseService;
import com.neusoft.medical.vo.insurance.ExpenseDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 参保人员费用查询业务逻辑实现类
 * @author Neusoft
 * @date 2025-07-10
 */
@Service
public class PersonExpenseServiceImpl implements PersonExpenseService {

    @Autowired
    private PersonExpenseMapper personExpenseMapper;

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
            Long total = personExpenseMapper.countByCondition(params);

            // 查询分页数据
            List<PatientRegistration> list = personExpenseMapper.selectPageByCondition(params);

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

            return personExpenseMapper.selectListByCondition(params);
        } catch (Exception e) {
            throw new RuntimeException("搜索参保人员失败：" + e.getMessage(), e);
        }
    }

    @Override
    public List<ExpenseDetailVO> getDrugExpenses(Integer personId, String startDate, String endDate) {
        if (personId == null) {
            throw new IllegalArgumentException("患者ID不能为空");
        }

        try {
            Map<String, Object> params = buildTimeRangeParams(personId, startDate, endDate);
            List<ExpenseDetailVO> expenses = personExpenseMapper.selectDrugExpenses(params);

            // 设置费用类型和状态名称
            for (ExpenseDetailVO expense : expenses) {
                expense.setExpenseType(1);
                expense.setExpenseTypeName("药品费用");
                expense.setStatusName(getStatusName(expense.getStatus()));

                // 计算总金额（单价 * 数量）
                if (expense.getUnitPrice() != null && expense.getQuantity() != null) {
                    expense.setTotalAmount(expense.getUnitPrice().multiply(
                            java.math.BigDecimal.valueOf(expense.getQuantity())));
                }
            }

            return expenses;
        } catch (Exception e) {
            throw new RuntimeException("查询药品费用失败：" + e.getMessage(), e);
        }
    }

    @Override
    public List<ExpenseDetailVO> getTreatmentExpenses(Integer personId, String startDate, String endDate) {
        if (personId == null) {
            throw new IllegalArgumentException("患者ID不能为空");
        }

        try {
            Map<String, Object> params = buildTimeRangeParams(personId, startDate, endDate);
            List<ExpenseDetailVO> expenses = personExpenseMapper.selectTreatmentExpenses(params);

            // 设置费用类型和状态名称
            for (ExpenseDetailVO expense : expenses) {
                expense.setExpenseType(2);
                expense.setExpenseTypeName("诊疗项目费用");
                expense.setStatusName(getStatusName(expense.getStatus()));

                // 诊疗项目默认数量为1，总金额等于单价
                if (expense.getQuantity() == null) {
                    expense.setQuantity(1);
                }
                if (expense.getUnitPrice() != null) {
                    expense.setTotalAmount(expense.getUnitPrice());
                }
            }

            return expenses;
        } catch (Exception e) {
            throw new RuntimeException("查询诊疗项目费用失败：" + e.getMessage(), e);
        }
    }

    @Override
    public List<ExpenseDetailVO> getServiceExpenses(Integer personId, String startDate, String endDate) {
        if (personId == null) {
            throw new IllegalArgumentException("患者ID不能为空");
        }

        try {
            Map<String, Object> params = buildTimeRangeParams(personId, startDate, endDate);
            List<ExpenseDetailVO> expenses = personExpenseMapper.selectServiceExpenses(params);

            // 设置费用类型和状态名称
            for (ExpenseDetailVO expense : expenses) {
                expense.setExpenseType(3);
                expense.setExpenseTypeName("医疗服务费用");
                expense.setStatusName(getStatusName(expense.getStatus()));

                // 医疗服务默认数量为1，总金额等于单价
                if (expense.getQuantity() == null) {
                    expense.setQuantity(1);
                }
                if (expense.getUnitPrice() != null) {
                    expense.setTotalAmount(expense.getUnitPrice());
                }
            }

            return expenses;
        } catch (Exception e) {
            throw new RuntimeException("查询医疗服务费用失败：" + e.getMessage(), e);
        }
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

    /**
     * 获取状态名称
     */
    private String getStatusName(Integer status) {
        if (status == null) {
            return "未知";
        }
        switch (status) {
            case 0:
                return "作废";
            case 1:
                return "正常执行";
            case 2:
                return "停止";
            default:
                return "未知";
        }
    }
}