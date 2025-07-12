package com.neusoft.medical.mapper.medical;

import com.neusoft.medical.entity.InpatientDiagnosis;
import com.neusoft.medical.vo.medical.PatientTreatmentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 患者诊疗项目Mapper接口
 * @author Neusoft
 * @date 2025-07-10
 */
@Mapper
public interface PatientTreatmentMapper {
    
    /**
     * 分页查询患者诊疗项目信息
     * @param patientId 患者ID
     * @param treatmentName 诊疗项目名称
     * @param offset 偏移量
     * @param limit 限制数量
     * @return 患者诊疗项目列表
     */
    List<PatientTreatmentVO> selectPatientTreatmentPage(@Param("patientId") Integer patientId,
                                                       @Param("treatmentName") String treatmentName,
                                                       @Param("offset") Integer offset,
                                                       @Param("limit") Integer limit);
    
    /**
     * 查询患者诊疗项目总数
     * @param patientId 患者ID
     * @param treatmentName 诊疗项目名称
     * @return 总数
     */
    Long selectPatientTreatmentCount(@Param("patientId") Integer patientId,
                                   @Param("treatmentName") String treatmentName);
    
    /**
     * 根据诊疗项目名称搜索诊疗项目信息
     * @param treatmentName 诊疗项目名称
     * @return 诊疗项目信息列表
     */
    List<PatientTreatmentVO> searchTreatments(@Param("treatmentName") String treatmentName);
    
    /**
     * 新增患者诊疗项目
     * @param inpatientDiagnosis 患者诊疗项目信息
     * @return 影响行数
     */
    int insertPatientTreatment(InpatientDiagnosis inpatientDiagnosis);
    
    /**
     * 根据ID查询患者诊疗项目信息
     * @param id 主键ID
     * @return 患者诊疗项目信息
     */
    PatientTreatmentVO selectPatientTreatmentById(@Param("id") Integer id);
    
    /**
     * 更新患者诊疗项目信息
     * @param inpatientDiagnosis 患者诊疗项目信息
     * @return 影响行数
     */
    int updatePatientTreatment(InpatientDiagnosis inpatientDiagnosis);
    
    /**
     * 删除患者诊疗项目信息
     * @param id 主键ID
     * @return 影响行数
     */
    int deletePatientTreatment(@Param("id") Integer id);
} 