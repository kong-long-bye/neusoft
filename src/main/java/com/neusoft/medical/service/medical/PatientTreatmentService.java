package com.neusoft.medical.service.medical;

import com.neusoft.medical.common.result.PageResult;
import com.neusoft.medical.entity.InpatientDiagnosis;
import com.neusoft.medical.vo.medical.PatientTreatmentVO;
import java.util.List;

/**
 * 患者诊疗项目服务接口
 * @author Neusoft
 * @date 2025-07-10
 */
public interface PatientTreatmentService {
    
    /**
     * 分页查询患者诊疗项目信息
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @param patientId 患者ID
     * @param treatmentName 诊疗项目名称
     * @return 分页结果
     */
    PageResult<PatientTreatmentVO> getPatientTreatmentPage(Integer pageNum, Integer pageSize, 
                                                          Integer patientId, String treatmentName);
    
    /**
     * 根据诊疗项目名称搜索诊疗项目信息
     * @param treatmentName 诊疗项目名称
     * @return 诊疗项目信息列表
     */
    List<PatientTreatmentVO> searchTreatments(String treatmentName);
    
    /**
     * 新增患者诊疗项目
     * @param inpatientDiagnosis 患者诊疗项目信息
     * @return 是否成功
     */
    boolean addPatientTreatment(InpatientDiagnosis inpatientDiagnosis);
    
    /**
     * 根据ID查询患者诊疗项目信息
     * @param id 主键ID
     * @return 患者诊疗项目信息
     */
    PatientTreatmentVO getPatientTreatmentById(Integer id);
    
    /**
     * 更新患者诊疗项目信息
     * @param inpatientDiagnosis 患者诊疗项目信息
     * @return 是否成功
     */
    boolean updatePatientTreatment(InpatientDiagnosis inpatientDiagnosis);
    
    /**
     * 删除患者诊疗项目信息
     * @param id 主键ID
     * @return 是否成功
     */
    boolean deletePatientTreatment(Integer id);
} 