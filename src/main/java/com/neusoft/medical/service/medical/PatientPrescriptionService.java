package com.neusoft.medical.service.medical;

import com.neusoft.medical.common.result.PageResult;
import com.neusoft.medical.entity.InpatientDrugs;
import com.neusoft.medical.vo.medical.PatientPrescriptionVO;
import java.util.List;

/**
 * 患者药品处方服务接口
 * @author Neusoft
 * @date 2025-07-10
 */
public interface PatientPrescriptionService {
    
    /**
     * 分页查询患者药品处方信息
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @param patientId 患者ID
     * @param drugName 药品名称
     * @return 分页结果
     */
    PageResult<PatientPrescriptionVO> getPatientPrescriptionPage(Integer pageNum, Integer pageSize, 
                                                               Integer patientId, String drugName);
    
    /**
     * 根据药品名称搜索药品信息
     * @param drugName 药品名称
     * @return 药品信息列表
     */
    List<PatientPrescriptionVO> searchDrugs(String drugName);
    
    /**
     * 新增患者药品处方
     * @param inpatientDrugs 患者药品处方信息
     * @return 是否成功
     */
    boolean addPatientPrescription(InpatientDrugs inpatientDrugs);
    
    /**
     * 根据ID查询患者药品处方信息
     * @param id 主键ID
     * @return 患者药品处方信息
     */
    PatientPrescriptionVO getPatientPrescriptionById(Integer id);
    
    /**
     * 更新患者药品处方信息
     * @param inpatientDrugs 患者药品处方信息
     * @return 是否成功
     */
    boolean updatePatientPrescription(InpatientDrugs inpatientDrugs);
    
    /**
     * 删除患者药品处方信息
     * @param id 主键ID
     * @return 是否成功
     */
    boolean deletePatientPrescription(Integer id);
} 