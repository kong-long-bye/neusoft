package com.neusoft.medical.mapper.medical;

import com.neusoft.medical.entity.InpatientDisease;
import com.neusoft.medical.vo.medical.PatientDiagnosisVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 患者疾病诊断Mapper接口
 * @author Neusoft
 * @date 2025-07-10
 */
@Mapper
public interface PatientDiagnosisMapper {
    
    /**
     * 分页查询患者疾病诊断信息
     * @param patientId 患者ID
     * @param diseaseName 疾病名称
     * @param offset 偏移量
     * @param limit 限制数量
     * @return 患者疾病诊断列表
     */
    List<PatientDiagnosisVO> selectPatientDiagnosisPage(@Param("patientId") Integer patientId,
                                                       @Param("diseaseName") String diseaseName,
                                                       @Param("offset") Integer offset,
                                                       @Param("limit") Integer limit);
    
    /**
     * 查询患者疾病诊断总数
     * @param patientId 患者ID
     * @param diseaseName 疾病名称
     * @return 总数
     */
    Long selectPatientDiagnosisCount(@Param("patientId") Integer patientId,
                                   @Param("diseaseName") String diseaseName);
    
    /**
     * 根据疾病名称搜索疾病信息
     * @param diseaseName 疾病名称
     * @return 疾病信息列表
     */
    List<PatientDiagnosisVO> searchDiseases(@Param("diseaseName") String diseaseName);
    
    /**
     * 新增患者疾病诊断
     * @param inpatientDisease 患者疾病诊断信息
     * @return 影响行数
     */
    int insertPatientDiagnosis(InpatientDisease inpatientDisease);
    
    /**
     * 根据ID查询患者疾病诊断信息
     * @param id 主键ID
     * @return 患者疾病诊断信息
     */
    PatientDiagnosisVO selectPatientDiagnosisById(@Param("id") Integer id);
    
    /**
     * 更新患者疾病诊断信息
     * @param inpatientDisease 患者疾病诊断信息
     * @return 影响行数
     */
    int updatePatientDiagnosis(InpatientDisease inpatientDisease);
    
    /**
     * 删除患者疾病诊断信息
     * @param id 主键ID
     * @return 影响行数
     */
    int deletePatientDiagnosis(@Param("id") Integer id);
} 