package com.neusoft.medical.service.medical.impl;

import com.neusoft.medical.common.result.PageResult;
import com.neusoft.medical.entity.InpatientDisease;
import com.neusoft.medical.mapper.medical.PatientDiagnosisMapper;
import com.neusoft.medical.service.medical.PatientDiagnosisService;
import com.neusoft.medical.vo.medical.PatientDiagnosisVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 患者疾病诊断服务实现类
 * @author Neusoft
 * @date 2025-07-10
 */
@Service
public class PatientDiagnosisServiceImpl implements PatientDiagnosisService {
    
    @Autowired
    private PatientDiagnosisMapper patientDiagnosisMapper;
    
    @Override
    public PageResult<PatientDiagnosisVO> getPatientDiagnosisPage(Integer pageNum, Integer pageSize, 
                                                                Integer patientId, String diseaseName) {
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
        List<PatientDiagnosisVO> list = patientDiagnosisMapper.selectPatientDiagnosisPage(patientId, diseaseName, offset, pageSize);
        Long total = patientDiagnosisMapper.selectPatientDiagnosisCount(patientId, diseaseName);
        
        return PageResult.of(total, pageNum, pageSize, list);
    }
    
    @Override
    public List<PatientDiagnosisVO> searchDiseases(String diseaseName) {
        if (!StringUtils.hasText(diseaseName)) {
            return null;
        }
        return patientDiagnosisMapper.searchDiseases(diseaseName);
    }
    
    @Override
    public boolean addPatientDiagnosis(InpatientDisease inpatientDisease) {
        // 参数校验
        if (inpatientDisease == null || inpatientDisease.getPatientId() == null 
            || inpatientDisease.getDiseaseId() == null || inpatientDisease.getDiseaseType() == null) {
            return false;
        }
        
        // 设置时间
        if (inpatientDisease.getOrderTime() == null) {
            inpatientDisease.setOrderTime(LocalDateTime.now());
        }
        
        return patientDiagnosisMapper.insertPatientDiagnosis(inpatientDisease) > 0;
    }
    
    @Override
    public PatientDiagnosisVO getPatientDiagnosisById(Integer id) {
        if (id == null) {
            return null;
        }
        return patientDiagnosisMapper.selectPatientDiagnosisById(id);
    }
    
    @Override
    public boolean updatePatientDiagnosis(InpatientDisease inpatientDisease) {
        // 参数校验
        if (inpatientDisease == null || inpatientDisease.getId() == null) {
            return false;
        }
        
        return patientDiagnosisMapper.updatePatientDiagnosis(inpatientDisease) > 0;
    }
    
    @Override
    public boolean deletePatientDiagnosis(Integer id) {
        if (id == null) {
            return false;
        }
        return patientDiagnosisMapper.deletePatientDiagnosis(id) > 0;
    }
} 