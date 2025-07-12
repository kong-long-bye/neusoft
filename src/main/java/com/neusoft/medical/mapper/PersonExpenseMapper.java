package com.neusoft.medical.mapper;

import com.neusoft.medical.entity.PatientRegistration;
import com.neusoft.medical.vo.insurance.ExpenseDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 参保人员费用查询数据访问接口
 * @author Neusoft
 * @date 2025-07-10
 */
@Mapper
public interface PersonExpenseMapper {

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
     * 查询患者药品费用详情
     * @param params 查询参数（包含personId、startDate、endDate）
     * @return 药品费用详情列表
     */
    List<ExpenseDetailVO> selectDrugExpenses(@Param("params") Map<String, Object> params);

    /**
     * 查询患者诊疗项目费用详情
     * @param params 查询参数（包含personId、startDate、endDate）
     * @return 诊疗项目费用详情列表
     */
    List<ExpenseDetailVO> selectTreatmentExpenses(@Param("params") Map<String, Object> params);

    /**
     * 查询患者医疗服务费用详情
     * @param params 查询参数（包含personId、startDate、endDate）
     * @return 医疗服务费用详情列表
     */
    List<ExpenseDetailVO> selectServiceExpenses(@Param("params") Map<String, Object> params);
}