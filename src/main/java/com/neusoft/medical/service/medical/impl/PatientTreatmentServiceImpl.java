package com.neusoft.medical.service.medical.impl;

import com.neusoft.medical.common.result.PageResult;
import com.neusoft.medical.entity.InpatientDiagnosis;
import com.neusoft.medical.mapper.medical.PatientTreatmentMapper;
import com.neusoft.medical.service.medical.PatientTreatmentService;
import com.neusoft.medical.vo.medical.PatientTreatmentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 患者诊疗项目服务实现类
 * @author Neusoft
 * @date 2025-07-10
 */
@Service
public class PatientTreatmentServiceImpl implements PatientTreatmentService {
    
    @Autowired
    private PatientTreatmentMapper patientTreatmentMapper;
    
    @Override
    public PageResult<PatientTreatmentVO> getPatientTreatmentPage(Integer pageNum, Integer pageSize, 
                                                                 Integer patientId, String treatmentName) {
        // 参数校验
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 10;
        }
        
        // 计算偏移量
        int offset = (pageNum - 1) * pageSize;
        
        // 查询数据
        List<PatientTreatmentVO> list = patientTreatmentMapper.selectPatientTreatmentPage(patientId, treatmentName, offset, pageSize);
        Long total = patientTreatmentMapper.selectPatientTreatmentCount(patientId, treatmentName);
        
        return PageResult.of(total, pageNum, pageSize, list);
    }
    
    @Override
    public List<PatientTreatmentVO> searchTreatments(String treatmentName) {
        if (!StringUtils.hasText(treatmentName)) {
            return null;
        }
        return patientTreatmentMapper.searchTreatments(treatmentName);
    }
    
    @Override
    public boolean addPatientTreatment(InpatientDiagnosis inpatientDiagnosis) {
        // 参数校验
        if (inpatientDiagnosis == null || inpatientDiagnosis.getPatientId() == null 
            || inpatientDiagnosis.getDiagnosisId() == null) {
            return false;
        }
        
        // 设置默认值
        if (inpatientDiagnosis.getStatus() == null) {
            inpatientDiagnosis.setStatus(1); // 默认正常执行
        }
        if (inpatientDiagnosis.getOrderTime() == null) {
            inpatientDiagnosis.setOrderTime(LocalDateTime.now());
        }
        
        return patientTreatmentMapper.insertPatientTreatment(inpatientDiagnosis) > 0;
    }
    
    @Override
    public PatientTreatmentVO getPatientTreatmentById(Integer id) {
        if (id == null) {
            return null;
        }
        return patientTreatmentMapper.selectPatientTreatmentById(id);
    }
    
    @Override
    public boolean updatePatientTreatment(InpatientDiagnosis inpatientDiagnosis) {
        // 参数校验
        if (inpatientDiagnosis == null || inpatientDiagnosis.getId() == null) {
            return false;
        }
        
        return patientTreatmentMapper.updatePatientTreatment(inpatientDiagnosis) > 0;
    }
    
    @Override
    public boolean deletePatientTreatment(Integer id) {
        if (id == null) {
            return false;
        }
        return patientTreatmentMapper.deletePatientTreatment(id) > 0;
    }
} 