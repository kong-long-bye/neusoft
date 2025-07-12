package com.neusoft.medical.mapper;

import com.neusoft.medical.entity.PatientRegistration;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 参保人员保险费用报销数据访问接口
 * @author Neusoft
 * @date 2025-07-10
 */
@Mapper
public interface ReimbursementMapper {

    /**
     * 根据条件查询患者信息总数
     * @param params 查询条件
     * @return 总记录数
     */
    Long countByCondition(@Param("params") Map<String, Object> params);

    /**
     * 根据条件分页查询患者信息
     * @param params 查询条件（包含offset和limit）
     * @return 患者信息列表
     */
    List<PatientRegistration> selectPageByCondition(@Param("params") Map<String, Object> params);

    /**
     * 根据条件查询患者信息列表
     * @param params 查询条件
     * @return 患者信息列表
     */
    List<PatientRegistration> selectListByCondition(@Param("params") Map<String, Object> params);

    /**
     * 根据患者ID查询患者信息
     * @param patientId 患者ID
     * @return 患者信息
     */
    PatientRegistration selectPatientById(@Param("patientId") Integer patientId);

    /**
     * 查询药品费用明细
     * @param params 查询参数（包含personId、startDate、endDate）
     * @return 药品费用明细列表
     */
    List<Map<String, Object>> selectDrugExpenseDetails(@Param("params") Map<String, Object> params);

    /**
     * 查询诊疗项目费用明细
     * @param params 查询参数（包含personId、startDate、endDate）
     * @return 诊疗项目费用明细列表
     */
    List<Map<String, Object>> selectTreatmentExpenseDetails(@Param("params") Map<String, Object> params);

    /**
     * 查询医疗服务费用明细
     * @param params 查询参数（包含personId、startDate、endDate）
     * @return 医疗服务费用明细列表
     */
    List<Map<String, Object>> selectServiceExpenseDetails(@Param("params") Map<String, Object> params);

    /**
     * 查询医院报销规则
     * @param hospitalLevel 医院等级
     * @param peopleType 人员类别
     * @param totalExpense 总费用
     * @return 报销规则
     */
    Map<String, Object> selectHospitalReimbursementRule(@Param("hospitalLevel") String hospitalLevel,
                                                        @Param("peopleType") String peopleType,
                                                        @Param("totalExpense") java.math.BigDecimal totalExpense);

    /**
     * 查询药品报销规则
     * @param drugReimbursementType 药品报销类型
     * @return 药品报销规则
     */
    Map<String, Object> selectDrugReimbursementRule(@Param("drugReimbursementType") String drugReimbursementType);
}