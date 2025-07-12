package com.neusoft.medical.service.medical;

import com.neusoft.medical.common.result.PageResult;
import com.neusoft.medical.entity.InpatientDisease;
import com.neusoft.medical.vo.medical.PatientDiagnosisVO;
import java.util.List;

/**
 * 患者疾病诊断服务接口
 * @author Neusoft
 * @date 2025-07-10
 */
public interface PatientDiagnosisService {
    
    /**
     * 分页查询患者疾病诊断信息
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @param patientId 患者ID
     * @param diseaseName 疾病名称
     * @return 分页结果
     */
    PageResult<PatientDiagnosisVO> getPatientDiagnosisPage(Integer pageNum, Integer pageSize, 
                                                         Integer patientId, String diseaseName);
    
    /**
     * 根据疾病名称搜索疾病信息
     * @param diseaseName 疾病名称
     * @return 疾病信息列表
     */
    List<PatientDiagnosisVO> searchDiseases(String diseaseName);
    
    /**
     * 新增患者疾病诊断
     * @param inpatientDisease 患者疾病诊断信息
     * @return 是否成功
     */
    boolean addPatientDiagnosis(InpatientDisease inpatientDisease);
    
    /**
     * 根据ID查询患者疾病诊断信息
     * @param id 主键ID
     * @return 患者疾病诊断信息
     */
    PatientDiagnosisVO getPatientDiagnosisById(Integer id);
    
    /**
     * 更新患者疾病诊断信息
     * @param inpatientDisease 患者疾病诊断信息
     * @return 是否成功
     */
    boolean updatePatientDiagnosis(InpatientDisease inpatientDisease);
    
    /**
     * 删除患者疾病诊断信息
     * @param id 主键ID
     * @return 是否成功
     */
    boolean deletePatientDiagnosis(Integer id);
} 