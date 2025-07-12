package com.neusoft.medical.service.insurance;

import com.neusoft.medical.common.result.PageResult;
import com.neusoft.medical.entity.PatientRegistration;
import com.neusoft.medical.vo.insurance.ExpenseDetailVO;

import java.util.List;

/**
 * 参保人员费用查询业务逻辑接口
 * @author Neusoft
 * @date 2025-07-10
 */
public interface PersonExpenseService {

    /**
     * 参保人员分页查询
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @param personName 患者姓名（可选）
     * @return 分页结果
     */
    PageResult<PatientRegistration> getPersonsPage(Integer pageNum, Integer pageSize, String personName);

    /**
     * 参保人员搜索
     * @param personName 患者姓名
     * @return 匹配的参保人员列表
     */
    List<PatientRegistration> searchPersons(String personName);

    /**
     * 查询参保人员药品费用详情
     * @param personId 患者ID
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 药品费用详情列表
     */
    List<ExpenseDetailVO> getDrugExpenses(Integer personId, String startDate, String endDate);

    /**
     * 查询参保人员诊疗项目费用详情
     * @param personId 患者ID
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 诊疗项目费用详情列表
     */
    List<ExpenseDetailVO> getTreatmentExpenses(Integer personId, String startDate, String endDate);

    /**
     * 查询参保人员医疗服务费用详情
     * @param personId 患者ID
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 医疗服务费用详情列表
     */
    List<ExpenseDetailVO> getServiceExpenses(Integer personId, String startDate, String endDate);
}