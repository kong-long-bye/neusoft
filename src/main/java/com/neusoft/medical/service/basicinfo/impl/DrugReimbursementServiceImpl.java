package com.neusoft.medical.service.basicinfo.impl;

import com.neusoft.medical.entity.DrugReimbursement;
import com.neusoft.medical.mapper.DrugReimbursementMapper;
import com.neusoft.medical.service.basicinfo.DrugReimbursementService;
import com.neusoft.medical.vo.basicinfo.DrugReimbursementVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 药品报销比例维护业务逻辑实现
 * @author Neusoft
 * @date 2025-07-10
 */
@Service
@Transactional
public class DrugReimbursementServiceImpl implements DrugReimbursementService {

    @Autowired
    private DrugReimbursementMapper drugReimbursementMapper;

    @Override
    public List<DrugReimbursementVO> getAllRatios() {
        List<DrugReimbursement> ratioList = drugReimbursementMapper.selectAll();
        return convertToVOList(ratioList);
    }

    @Override
    public List<DrugReimbursementVO> getEnabledRatios() {
        List<DrugReimbursement> ratioList = drugReimbursementMapper.selectEnabled();
        return convertToVOList(ratioList);
    }

    @Override
    public DrugReimbursementVO getById(Integer ratioId) {
        DrugReimbursement drugReimbursement = drugReimbursementMapper.selectById(ratioId);
        if (drugReimbursement == null) {
            return null;
        }

        DrugReimbursementVO drugReimbursementVO = new DrugReimbursementVO();
        BeanUtils.copyProperties(drugReimbursement, drugReimbursementVO);
        return drugReimbursementVO;
    }

    @Override
    public boolean save(DrugReimbursementVO drugReimbursementVO) {
        // 检查报销类型是否已存在
        if (existsByType(drugReimbursementVO.getDrugReimbursementType(), null)) {
            throw new RuntimeException("该报销类型已存在");
        }

        DrugReimbursement drugReimbursement = new DrugReimbursement();
        BeanUtils.copyProperties(drugReimbursementVO, drugReimbursement);
        drugReimbursement.setCreatedTime(LocalDateTime.now());
        drugReimbursement.setUpdatedTime(LocalDateTime.now());

        // 默认设置为启用状态
        if (drugReimbursement.getStatus() == null) {
            drugReimbursement.setStatus(1);
        }

        return drugReimbursementMapper.insert(drugReimbursement) > 0;
    }

    @Override
    public boolean updateById(Integer ratioId, DrugReimbursementVO drugReimbursementVO) {
        // 检查报销类型是否已存在（排除当前记录）
        if (existsByType(drugReimbursementVO.getDrugReimbursementType(), ratioId)) {
            throw new RuntimeException("该报销类型已存在");
        }

        DrugReimbursement drugReimbursement = new DrugReimbursement();
        BeanUtils.copyProperties(drugReimbursementVO, drugReimbursement);
        drugReimbursement.setId(ratioId);
        drugReimbursement.setUpdatedTime(LocalDateTime.now());

        return drugReimbursementMapper.updateById(drugReimbursement) > 0;
    }

    @Override
    public boolean deleteById(Integer ratioId) {
        return drugReimbursementMapper.deleteById(ratioId) > 0;
    }

    @Override
    public boolean existsByType(String drugReimbursementType, Integer excludeId) {
        int count = drugReimbursementMapper.countByType(drugReimbursementType, excludeId);
        return count > 0;
    }

    /**
     * 将实体列表转换为VO列表
     */
    private List<DrugReimbursementVO> convertToVOList(List<DrugReimbursement> ratioList) {
        List<DrugReimbursementVO> voList = new ArrayList<>();
        for (DrugReimbursement ratio : ratioList) {
            DrugReimbursementVO vo = new DrugReimbursementVO();
            BeanUtils.copyProperties(ratio, vo);
            voList.add(vo);
        }
        return voList;
    }
}