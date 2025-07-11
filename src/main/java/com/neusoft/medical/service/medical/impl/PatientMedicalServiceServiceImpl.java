package com.neusoft.medical.service.medical.impl;

import com.neusoft.medical.common.result.PageResult;
import com.neusoft.medical.entity.InpatientMedical;
import com.neusoft.medical.mapper.medical.PatientMedicalServiceMapper;
import com.neusoft.medical.service.medical.PatientMedicalServiceService;
import com.neusoft.medical.vo.medical.PatientMedicalServiceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 患者医疗服务服务实现类
 * @author Neusoft
 * @date 2025-07-10
 */
@Service
public class PatientMedicalServiceServiceImpl implements PatientMedicalServiceService {
    
    @Autowired
    private PatientMedicalServiceMapper patientMedicalServiceMapper;
    
    @Override
    public PageResult<PatientMedicalServiceVO> getPatientMedicalServicePage(Integer pageNum, Integer pageSize, 
                                                                          Integer patientId, String serviceName) {
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
        List<PatientMedicalServiceVO> list = patientMedicalServiceMapper.selectPatientMedicalServicePage(patientId, serviceName, offset, pageSize);
        Long total = patientMedicalServiceMapper.selectPatientMedicalServiceCount(patientId, serviceName);
        
        return PageResult.of(total, pageNum, pageSize, list);
    }
    
    @Override
    public List<PatientMedicalServiceVO> searchMedicalServices(String serviceName) {
        if (!StringUtils.hasText(serviceName)) {
            return null;
        }
        return patientMedicalServiceMapper.searchMedicalServices(serviceName);
    }
    
    @Override
    public boolean addPatientMedicalService(InpatientMedical inpatientMedical) {
        // 参数校验
        if (inpatientMedical == null || inpatientMedical.getPatientId() == null 
            || inpatientMedical.getMedicalId() == null) {
            return false;
        }
        
        // 设置默认值
        if (inpatientMedical.getStatus() == null) {
            inpatientMedical.setStatus(1); // 默认正常执行
        }
        if (inpatientMedical.getOrderTime() == null) {
            inpatientMedical.setOrderTime(LocalDateTime.now());
        }
        
        return patientMedicalServiceMapper.insertPatientMedicalService(inpatientMedical) > 0;
    }
    
    @Override
    public PatientMedicalServiceVO getPatientMedicalServiceById(Integer id) {
        if (id == null) {
            return null;
        }
        return patientMedicalServiceMapper.selectPatientMedicalServiceById(id);
    }
    
    @Override
    public boolean updatePatientMedicalService(InpatientMedical inpatientMedical) {
        // 参数校验
        if (inpatientMedical == null || inpatientMedical.getId() == null) {
            return false;
        }
        
        return patientMedicalServiceMapper.updatePatientMedicalService(inpatientMedical) > 0;
    }
    
    @Override
    public boolean deletePatientMedicalService(Integer id) {
        if (id == null) {
            return false;
        }
        return patientMedicalServiceMapper.deletePatientMedicalService(id) > 0;
    }
} 