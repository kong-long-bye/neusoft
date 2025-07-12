package com.neusoft.medical.service.insurance;

import com.neusoft.medical.common.result.PageResult;
import com.neusoft.medical.entity.PatientRegistration;
import com.neusoft.medical.vo.insurance.ReimbursementCalculationVO;

import java.util.List;

/**
 * 参保人员保险费用报销业务逻辑接口
 * @author Neusoft
 * @date 2025-07-10
 */
public interface ReimbursementService {

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
     * 计算费用报销金额
     * @param personId 患者ID
     * @param hospitalLevel 医院等级
     * @param peopleType 人员类别（1-在职 0-退休）
     * @param startDate 费用开始日期
     * @param endDate 费用结束日期
     * @return 报销计算结果
     */
    ReimbursementCalculationVO calculateReimbursement(Integer personId, String hospitalLevel,
                                                      String peopleType, String startDate, String endDate);

    /**
     * 执行费用报销
     * @param personId 患者ID
     * @param hospitalLevel 医院等级
     * @param peopleType 人员类别（1-在职 0-退休）
     * @param approver 审批人
     * @param remark 备注
     * @param startDate 费用开始日期
     * @param endDate 费用结束日期
     * @return 报销执行结果
     */
    ReimbursementCalculationVO.ReimbursementRecord executeReimbursement(Integer personId, String hospitalLevel,
                                                                        String peopleType, String approver,
                                                                        String remark, String startDate, String endDate);

    /**
     * 查询报销历史记录
     * @param personId 患者ID
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 报销历史记录列表
     */
    List<ReimbursementCalculationVO.ReimbursementRecord> getReimbursementHistory(Integer personId, String startDate, String endDate);
}