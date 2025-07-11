package com.neusoft.medical.service.basicinfo.impl;

import com.neusoft.medical.common.result.PageResult;
import com.neusoft.medical.entity.MedicalService;
import com.neusoft.medical.mapper.MedicalServiceMapper;
import com.neusoft.medical.service.basicinfo.MedicalServiceService;
import com.neusoft.medical.vo.basicinfo.MedicalServiceVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 医疗服务设施数据维护业务逻辑实现
 * @author Neusoft
 * @date 2025-07-10
 */
@Service
@Transactional
public class MedicalServiceServiceImpl implements MedicalServiceService {

    @Autowired
    private MedicalServiceMapper medicalServiceMapper;

    @Override
    public PageResult<MedicalServiceVO> selectPage(Integer pageNum, Integer pageSize, String facilityName) {
        // 计算偏移量
        Integer offset = (pageNum - 1) * pageSize;

        // 处理查询参数
        String searchName = StringUtils.hasText(facilityName) ? facilityName.trim() : null;

        // 查询数据列表
        List<MedicalService> serviceList = medicalServiceMapper.selectPage(offset, pageSize, searchName);

        // 查询总记录数
        Long total = medicalServiceMapper.selectCount(searchName);

        // 转换为VO对象
        List<MedicalServiceVO> serviceVOList = convertToVOList(serviceList);

        return PageResult.of(total, pageNum, pageSize, serviceVOList);
    }

    @Override
    public List<MedicalServiceVO> searchByName(String facilityName) {
        if (!StringUtils.hasText(facilityName)) {
            return new ArrayList<>();
        }

        List<MedicalService> serviceList = medicalServiceMapper.searchByName(facilityName.trim());
        return convertToVOList(serviceList);
    }

    @Override
    public boolean save(MedicalServiceVO medicalServiceVO) {
        MedicalService medicalService = new MedicalService();
        BeanUtils.copyProperties(medicalServiceVO, medicalService);
        medicalService.setCreatedTime(LocalDateTime.now());
        medicalService.setUpdatedTime(LocalDateTime.now());

        return medicalServiceMapper.insert(medicalService) > 0;
    }

    @Override
    public boolean updateById(Integer facilityId, MedicalServiceVO medicalServiceVO) {
        MedicalService medicalService = new MedicalService();
        BeanUtils.copyProperties(medicalServiceVO, medicalService);
        medicalService.setId(facilityId);
        medicalService.setUpdatedTime(LocalDateTime.now());

        return medicalServiceMapper.updateById(medicalService) > 0;
    }

    @Override
    public boolean deleteByIds(List<Integer> facilityIds) {
        if (facilityIds == null || facilityIds.isEmpty()) {
            return false;
        }

        if (facilityIds.size() == 1) {
            return medicalServiceMapper.deleteById(facilityIds.get(0)) > 0;
        } else {
            return medicalServiceMapper.deleteBatchByIds(facilityIds) > 0;
        }
    }

    @Override
    public MedicalServiceVO getById(Integer facilityId) {
        MedicalService medicalService = medicalServiceMapper.selectById(facilityId);
        if (medicalService == null) {
            return null;
        }

        MedicalServiceVO medicalServiceVO = new MedicalServiceVO();
        BeanUtils.copyProperties(medicalService, medicalServiceVO);
        return medicalServiceVO;
    }

    /**
     * 将实体列表转换为VO列表
     */
    private List<MedicalServiceVO> convertToVOList(List<MedicalService> serviceList) {
        List<MedicalServiceVO> voList = new ArrayList<>();
        for (MedicalService service : serviceList) {
            MedicalServiceVO vo = new MedicalServiceVO();
            BeanUtils.copyProperties(service, vo);
            voList.add(vo);
        }
        return voList;
    }
}
