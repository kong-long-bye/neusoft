package com.neusoft.medical.service.basicinfo.impl;

import com.neusoft.medical.entity.HospitalReimbursement;
import com.neusoft.medical.mapper.HospitalReimbursementMapper;
import com.neusoft.medical.service.basicinfo.HospitalReimbursementService;
import com.neusoft.medical.vo.basicinfo.HospitalReimbursementVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 医院报销比例维护业务逻辑实现
 * @author Neusoft
 * @date 2025-07-10
 */
@Service
@Transactional
public class HospitalReimbursementServiceImpl implements HospitalReimbursementService {

    @Autowired
    private HospitalReimbursementMapper hospitalReimbursementMapper;

    @Override
    public List<HospitalReimbursementVO> getRatiosByLevel(String hospitalLevel, String employeeType) {
        // 转换人员类别：在职->1，退休->0
        String peopleType = null;
        if ("在职".equals(employeeType)) {
            peopleType = "1";
        } else if ("退休".equals(employeeType)) {
            peopleType = "0";
        }

        List<HospitalReimbursement> ratioList = hospitalReimbursementMapper.selectByHospitalLevel(hospitalLevel, peopleType);
        return convertToVOList(ratioList);
    }

    @Override
    public HospitalReimbursementVO getById(Integer ratioId) {
        HospitalReimbursement hospitalReimbursement = hospitalReimbursementMapper.selectById(ratioId);
        if (hospitalReimbursement == null) {
            return null;
        }

        HospitalReimbursementVO hospitalReimbursementVO = new HospitalReimbursementVO();
        BeanUtils.copyProperties(hospitalReimbursement, hospitalReimbursementVO);
        return hospitalReimbursementVO;
    }

    @Override
    public boolean save(HospitalReimbursementVO hospitalReimbursementVO) {
        // 检查费用区间是否重叠
        if (isRangeOverlap(hospitalReimbursementVO.getHospitalLevel(),
                hospitalReimbursementVO.getPeopleType(),
                hospitalReimbursementVO.getMinPayLevel(),
                hospitalReimbursementVO.getMaxPayLevel(),
                null)) {
            throw new RuntimeException("费用区间与现有配置重叠，请重新设置");
        }

        HospitalReimbursement hospitalReimbursement = new HospitalReimbursement();
        BeanUtils.copyProperties(hospitalReimbursementVO, hospitalReimbursement);
        hospitalReimbursement.setCreatedTime(LocalDateTime.now());
        hospitalReimbursement.setUpdatedTime(LocalDateTime.now());

        // 默认设置为启用状态
        if (hospitalReimbursement.getStatus() == null) {
            hospitalReimbursement.setStatus("1");
        }

        return hospitalReimbursementMapper.insert(hospitalReimbursement) > 0;
    }

    @Override
    public boolean updateById(Integer ratioId, HospitalReimbursementVO hospitalReimbursementVO) {
        // 检查费用区间是否重叠（排除当前记录）
        if (isRangeOverlap(hospitalReimbursementVO.getHospitalLevel(),
                hospitalReimbursementVO.getPeopleType(),
                hospitalReimbursementVO.getMinPayLevel(),
                hospitalReimbursementVO.getMaxPayLevel(),
                ratioId)) {
            throw new RuntimeException("费用区间与现有配置重叠，请重新设置");
        }

        HospitalReimbursement hospitalReimbursement = new HospitalReimbursement();
        BeanUtils.copyProperties(hospitalReimbursementVO, hospitalReimbursement);
        hospitalReimbursement.setId(ratioId);
        hospitalReimbursement.setUpdatedTime(LocalDateTime.now());

        return hospitalReimbursementMapper.updateById(hospitalReimbursement) > 0;
    }

    @Override
    public boolean deleteById(Integer ratioId) {
        return hospitalReimbursementMapper.deleteById(ratioId) > 0;
    }

    @Override
    public boolean isRangeOverlap(String hospitalLevel, String peopleType, String minPayLevel, String maxPayLevel, Integer excludeId) {
        int count = hospitalReimbursementMapper.countOverlapRange(hospitalLevel, peopleType, minPayLevel, maxPayLevel, excludeId);
        return count > 0;
    }

    /**
     * 将实体列表转换为VO列表
     */
    private List<HospitalReimbursementVO> convertToVOList(List<HospitalReimbursement> ratioList) {
        List<HospitalReimbursementVO> voList = new ArrayList<>();
        for (HospitalReimbursement ratio : ratioList) {
            HospitalReimbursementVO vo = new HospitalReimbursementVO();
            BeanUtils.copyProperties(ratio, vo);
            voList.add(vo);
        }
        return voList;
    }
}
