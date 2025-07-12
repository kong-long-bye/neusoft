package com.neusoft.medical.service.medical.impl;

import com.neusoft.medical.common.result.PageResult;
import com.neusoft.medical.entity.InpatientDrugs;
import com.neusoft.medical.mapper.medical.PatientPrescriptionMapper;
import com.neusoft.medical.service.medical.PatientPrescriptionService;
import com.neusoft.medical.vo.medical.PatientPrescriptionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 患者药品处方服务实现类
 * @author Neusoft
 * @date 2025-07-10
 */
@Service
public class PatientPrescriptionServiceImpl implements PatientPrescriptionService {
    
    @Autowired
    private PatientPrescriptionMapper patientPrescriptionMapper;
    
    @Override
    public PageResult<PatientPrescriptionVO> getPatientPrescriptionPage(Integer pageNum, Integer pageSize, 
                                                                      Integer patientId, String drugName) {
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
        List<PatientPrescriptionVO> list = patientPrescriptionMapper.selectPatientPrescriptionPage(patientId, drugName, offset, pageSize);
        Long total = patientPrescriptionMapper.selectPatientPrescriptionCount(patientId, drugName);
        
        return PageResult.of(total, pageNum, pageSize, list);
    }
    
    @Override
    public List<PatientPrescriptionVO> searchDrugs(String drugName) {
        if (!StringUtils.hasText(drugName)) {
            return null;
        }
        return patientPrescriptionMapper.searchDrugs(drugName);
    }
    
    @Override
    public boolean addPatientPrescription(InpatientDrugs inpatientDrugs) {
        // 参数校验
        if (inpatientDrugs == null || inpatientDrugs.getPatientId() == null 
            || inpatientDrugs.getDrugId() == null) {
            return false;
        }
        
        // 设置默认值
        if (inpatientDrugs.getStatus() == null) {
            inpatientDrugs.setStatus(1); // 默认正常执行
        }
        if (inpatientDrugs.getStartTime() == null) {
            inpatientDrugs.setStartTime(LocalDateTime.now());
        }
        
        return patientPrescriptionMapper.insertPatientPrescription(inpatientDrugs) > 0;
    }
    
    @Override
    public PatientPrescriptionVO getPatientPrescriptionById(Integer id) {
        if (id == null) {
            return null;
        }
        return patientPrescriptionMapper.selectPatientPrescriptionById(id);
    }
    
    @Override
    public boolean updatePatientPrescription(InpatientDrugs inpatientDrugs) {
        // 参数校验
        if (inpatientDrugs == null || inpatientDrugs.getId() == null) {
            return false;
        }
        
        return patientPrescriptionMapper.updatePatientPrescription(inpatientDrugs) > 0;
    }
    
    @Override
    public boolean deletePatientPrescription(Integer id) {
        if (id == null) {
            return false;
        }
        return patientPrescriptionMapper.deletePatientPrescription(id) > 0;
    }
} 