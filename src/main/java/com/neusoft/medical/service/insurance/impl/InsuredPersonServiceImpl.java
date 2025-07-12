package com.neusoft.medical.service.insurance.impl;

import com.neusoft.medical.common.result.PageResult;
import com.neusoft.medical.entity.PatientRegistration;
import com.neusoft.medical.mapper.PatientRegistrationMapper;
import com.neusoft.medical.service.insurance.InsuredPersonService;
import com.neusoft.medical.vo.insurance.InsuredPersonVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 * 参保人员保险信息维护业务逻辑实现类
 * @author Neusoft
 * @date 2025-07-12
 */
@Service
@Transactional
public class InsuredPersonServiceImpl implements InsuredPersonService {

    @Autowired
    private PatientRegistrationMapper patientRegistrationMapper;

    @Override
    public PageResult<InsuredPersonVO> getPersonsPage(Integer pageNum, Integer pageSize, String personName) {
        // 计算偏移量
        int offset = (pageNum - 1) * pageSize;

        // 查询总数
        Long total = patientRegistrationMapper.countByPersonName(personName);

        // 查询分页数据
        List<PatientRegistration> patients = patientRegistrationMapper.selectByPersonNameWithPage(personName, offset, pageSize);

        // 转换为VO列表
        List<InsuredPersonVO> voList = convertToVOList(patients);

        // 返回分页结果
        return PageResult.of(total, pageNum, pageSize, voList);
    }

    @Override
    public List<InsuredPersonVO> searchPersons(String personName) {
        // 查询患者列表
        List<PatientRegistration> patients = patientRegistrationMapper.selectByPersonName(personName);

        // 转换为VO列表并返回
        return convertToVOList(patients);
    }

    @Override
    public boolean addPerson(InsuredPersonVO insuredPersonVO) {
        // 创建实体对象
        PatientRegistration patient = new PatientRegistration();

        // 复制属性
        BeanUtils.copyProperties(insuredPersonVO, patient);

        // 设置创建时间
        patient.setCreatedTime(LocalDateTime.now());

        // 根据医保状态设置结算类别
        if ("1".equals(insuredPersonVO.getInsuranceStatus())) {
            patient.setRegistMethod("医保");
        } else {
            patient.setRegistMethod("自费");
        }

        // 插入数据
        int result = patientRegistrationMapper.insert(patient);

        return result > 0;
    }

    @Override
    public boolean updatePerson(InsuredPersonVO insuredPersonVO) {
        // 查询原记录
        PatientRegistration patient = patientRegistrationMapper.selectById(insuredPersonVO.getId());
        if (patient == null) {
            return false;
        }

        // 更新字段
        patient.setRealName(insuredPersonVO.getRealName());
        patient.setGender(insuredPersonVO.getGender());
        patient.setCardNumber(insuredPersonVO.getCardNumber());
        patient.setBirthday(insuredPersonVO.getBirthday());
        patient.setAge(insuredPersonVO.getAge());
        patient.setHomeAddress(insuredPersonVO.getHomeAddress());

        // 根据医保状态更新结算类别
        if ("1".equals(insuredPersonVO.getInsuranceStatus())) {
            patient.setRegistMethod("医保");
        } else {
            patient.setRegistMethod("自费");
        }

        // 设置更新时间
        patient.setUpdatedTime(LocalDateTime.now());

        // 更新数据
        int result = patientRegistrationMapper.updateById(patient);

        return result > 0;
    }

    /**
     * 将实体列表转换为VO列表
     * @param patients 患者实体列表
     * @return VO列表
     */
    private List<InsuredPersonVO> convertToVOList(List<PatientRegistration> patients) {
        List<InsuredPersonVO> voList = new ArrayList<>();

        for (PatientRegistration patient : patients) {
            InsuredPersonVO vo = new InsuredPersonVO();
            BeanUtils.copyProperties(patient, vo);

            // 设置医保状态
            if ("医保".equals(patient.getRegistMethod())) {
                vo.setInsuranceStatus("1");
            } else {
                vo.setInsuranceStatus("0");
            }

            // 根据年龄判断人员类别（假设60岁以上为退休人员）
            if (patient.getBirthday() != null) {
                int age = Period.between(patient.getBirthday(), LocalDate.now()).getYears();
                if (age >= 60) {
                    vo.setPeopleType("0"); // 退休人员
                } else {
                    vo.setPeopleType("1"); // 在职人员
                }
            }

            voList.add(vo);
        }

        return voList;
    }
}