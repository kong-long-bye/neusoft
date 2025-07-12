package com.neusoft.medical.mapper;

import com.neusoft.medical.entity.PatientRegistration;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 参保人员保险费用报销详情报表数据访问接口
 * @author Neusoft
 * @date 2025-07-10
 */
@Mapper
public interface ReimbursementReportMapper {

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
     * 查询药品分类统计数据
     * @param params 查询参数（包含personId、startDate、endDate）
     * @return 药品分类统计结果列表
     */
    List<Map<String, Object>> selectDrugCategoryStatistics(@Param("params") Map<String, Object> params);

    /**
     * 查询费用类型统计数据
     * @param params 查询参数（包含personId、startDate、endDate）
     * @return 费用类型统计结果列表
     */
    List<Map<String, Object>> selectExpenseTypeStatistics(@Param("params") Map<String, Object> params);

    /**
     * 查询费用汇总数据
     * @param params 查询参数（包含personId、startDate、endDate）
     * @return 费用汇总结果
     */
    Map<String, Object> selectExpenseSummary(@Param("params") Map<String, Object> params);
}