package com.neusoft.medical.service.medical;

import com.neusoft.medical.common.result.PageResult;
import com.neusoft.medical.entity.InpatientMedical;
import com.neusoft.medical.vo.medical.PatientMedicalServiceVO;
import java.util.List;

/**
 * 患者医疗服务服务接口
 * @author Neusoft
 * @date 2025-07-10
 */
public interface PatientMedicalServiceService {
    
    /**
     * 分页查询患者医疗服务信息
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @param patientId 患者ID
     * @param serviceName 医疗服务名称
     * @return 分页结果
     */
    PageResult<PatientMedicalServiceVO> getPatientMedicalServicePage(Integer pageNum, Integer pageSize, 
                                                                   Integer patientId, String serviceName);
    
    /**
     * 根据医疗服务名称搜索医疗服务信息
     * @param serviceName 医疗服务名称
     * @return 医疗服务信息列表
     */
    List<PatientMedicalServiceVO> searchMedicalServices(String serviceName);
    
    /**
     * 新增患者医疗服务
     * @param inpatientMedical 患者医疗服务信息
     * @return 是否成功
     */
    boolean addPatientMedicalService(InpatientMedical inpatientMedical);
    
    /**
     * 根据ID查询患者医疗服务信息
     * @param id 主键ID
     * @return 患者医疗服务信息
     */
    PatientMedicalServiceVO getPatientMedicalServiceById(Integer id);
    
    /**
     * 更新患者医疗服务信息
     * @param inpatientMedical 患者医疗服务信息
     * @return 是否成功
     */
    boolean updatePatientMedicalService(InpatientMedical inpatientMedical);
    
    /**
     * 删除患者医疗服务信息
     * @param id 主键ID
     * @return 是否成功
     */
    boolean deletePatientMedicalService(Integer id);
} 