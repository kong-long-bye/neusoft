package com.neusoft.medical.service.basicinfo.impl;

import com.neusoft.medical.common.result.PageResult;
import com.neusoft.medical.entity.DrugInfo;
import com.neusoft.medical.mapper.DrugInfoMapper;
import com.neusoft.medical.service.basicinfo.DrugInfoService;
import com.neusoft.medical.vo.basicinfo.DrugInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 医保药品数据维护业务逻辑实现
 * @author Neusoft
 * @date 2025-07-10
 */
@Service
@Transactional
public class DrugInfoServiceImpl implements DrugInfoService {

    @Autowired
    private DrugInfoMapper drugInfoMapper;

    @Override
    public PageResult<DrugInfoVO> selectPage(Integer pageNum, Integer pageSize, String drugName) {
        // 计算偏移量
        Integer offset = (pageNum - 1) * pageSize;

        // 处理查询参数
        String searchName = StringUtils.hasText(drugName) ? drugName.trim() : null;

        // 查询数据列表
        List<DrugInfo> drugInfoList = drugInfoMapper.selectPage(offset, pageSize, searchName);

        // 查询总记录数
        Long total = drugInfoMapper.selectCount(searchName);

        // 转换为VO对象
        List<DrugInfoVO> drugInfoVOList = convertToVOList(drugInfoList);

        return PageResult.of(total, pageNum, pageSize, drugInfoVOList);
    }

    @Override
    public List<DrugInfoVO> searchByName(String drugName) {
        if (!StringUtils.hasText(drugName)) {
            return new ArrayList<>();
        }

        List<DrugInfo> drugInfoList = drugInfoMapper.searchByName(drugName.trim());
        return convertToVOList(drugInfoList);
    }

    @Override
    public boolean save(DrugInfoVO drugInfoVO) {
        DrugInfo drugInfo = new DrugInfo();
        BeanUtils.copyProperties(drugInfoVO, drugInfo);
        drugInfo.setCreatedTime(LocalDateTime.now());
        drugInfo.setUpdatedTime(LocalDateTime.now());

        return drugInfoMapper.insert(drugInfo) > 0;
    }

    @Override
    public boolean updateById(Integer drugId, DrugInfoVO drugInfoVO) {
        DrugInfo drugInfo = new DrugInfo();
        BeanUtils.copyProperties(drugInfoVO, drugInfo);
        drugInfo.setId(drugId);
        drugInfo.setUpdatedTime(LocalDateTime.now());

        return drugInfoMapper.updateById(drugInfo) > 0;
    }

    @Override
    public boolean deleteByIds(List<Integer> drugIds) {
        if (drugIds == null || drugIds.isEmpty()) {
            return false;
        }

        if (drugIds.size() == 1) {
            return drugInfoMapper.deleteById(drugIds.get(0)) > 0;
        } else {
            return drugInfoMapper.deleteBatchByIds(drugIds) > 0;
        }
    }

    @Override
    public DrugInfoVO getById(Integer drugId) {
        DrugInfo drugInfo = drugInfoMapper.selectById(drugId);
        if (drugInfo == null) {
            return null;
        }

        DrugInfoVO drugInfoVO = new DrugInfoVO();
        BeanUtils.copyProperties(drugInfo, drugInfoVO);
        return drugInfoVO;
    }

    /**
     * 将实体列表转换为VO列表
     */
    private List<DrugInfoVO> convertToVOList(List<DrugInfo> drugInfoList) {
        List<DrugInfoVO> voList = new ArrayList<>();
        for (DrugInfo drugInfo : drugInfoList) {
            DrugInfoVO vo = new DrugInfoVO();
            BeanUtils.copyProperties(drugInfo, vo);
            voList.add(vo);
        }
        return voList;
    }
}