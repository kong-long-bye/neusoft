package com.neusoft.medical.service.insurance;

import com.neusoft.medical.common.result.PageResult;
import com.neusoft.medical.entity.PatientRegistration;
import com.neusoft.medical.vo.insurance.ReimbursementReportVO;

import java.util.List;

/**
 * 参保人员保险费用报销详情报表业务逻辑接口
 * @author Neusoft
 * @date 2025-07-10
 */
public interface ReimbursementReportService {

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
     * 查询药品费用分类占比数据
     * @param personId 患者ID
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 甲乙丙三类药品费用占比数据
     */
    List<ReimbursementReportVO.CategoryRatioData> getDrugCategoryChart(Integer personId, String startDate, String endDate);

    /**
     * 查询费用类型占比数据
     * @param personId 患者ID
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 药品费用、诊疗项目费用、医疗服务费用三类占比数据
     */
    List<ReimbursementReportVO.CategoryRatioData> getExpenseTypeChart(Integer personId, String startDate, String endDate);

    /**
     * 查询综合费用报销详情报表
     * @param personId 患者ID
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 包含所有费用分类、占比、明细的综合报表数据
     */
    ReimbursementReportVO getComprehensiveReport(Integer personId, String startDate, String endDate);
}