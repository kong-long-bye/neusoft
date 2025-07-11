package com.neusoft.medical.service.basicinfo.impl;

import com.neusoft.medical.common.result.PageResult;
import com.neusoft.medical.entity.DiagnosisTreatment;
import com.neusoft.medical.mapper.DiagnosisTreatmentMapper;
import com.neusoft.medical.service.basicinfo.DiagnosisTreatmentService;
import com.neusoft.medical.vo.basicinfo.TreatmentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 诊疗项目数据维护业务逻辑实现
 * @author Neusoft
 * @date 2025-07-10
 */
@Service
@Transactional
public class DiagnosisTreatmentServiceImpl implements DiagnosisTreatmentService {

    @Autowired
    private DiagnosisTreatmentMapper diagnosisTreatmentMapper;

    @Override
    public PageResult<TreatmentVO> selectPage(Integer pageNum, Integer pageSize, String treatmentName) {
        // 计算偏移量
        Integer offset = (pageNum - 1) * pageSize;

        // 处理查询参数
        String searchName = StringUtils.hasText(treatmentName) ? treatmentName.trim() : null;

        // 查询数据列表
        List<DiagnosisTreatment> treatmentList = diagnosisTreatmentMapper.selectPage(offset, pageSize, searchName);

        // 查询总记录数
        Long total = diagnosisTreatmentMapper.selectCount(searchName);

        // 转换为VO对象
        List<TreatmentVO> treatmentVOList = convertToVOList(treatmentList);

        return PageResult.of(total, pageNum, pageSize, treatmentVOList);
    }

    @Override
    public List<TreatmentVO> searchByName(String treatmentName) {
        if (!StringUtils.hasText(treatmentName)) {
            return new ArrayList<>();
        }

        List<DiagnosisTreatment> treatmentList = diagnosisTreatmentMapper.searchByName(treatmentName.trim());
        return convertToVOList(treatmentList);
    }

    @Override
    public boolean save(TreatmentVO treatmentVO) {
        DiagnosisTreatment diagnosisTreatment = new DiagnosisTreatment();
        BeanUtils.copyProperties(treatmentVO, diagnosisTreatment);
        diagnosisTreatment.setCreatedTime(LocalDateTime.now());
        diagnosisTreatment.setUpdatedTime(LocalDateTime.now());

        return diagnosisTreatmentMapper.insert(diagnosisTreatment) > 0;
    }

    @Override
    public boolean updateById(Integer treatmentId, TreatmentVO treatmentVO) {
        DiagnosisTreatment diagnosisTreatment = new DiagnosisTreatment();
        BeanUtils.copyProperties(treatmentVO, diagnosisTreatment);
        diagnosisTreatment.setId(treatmentId);
        diagnosisTreatment.setUpdatedTime(LocalDateTime.now());

        return diagnosisTreatmentMapper.updateById(diagnosisTreatment) > 0;
    }

    @Override
    public boolean deleteByIds(List<Integer> treatmentIds) {
        if (treatmentIds == null || treatmentIds.isEmpty()) {
            return false;
        }

        if (treatmentIds.size() == 1) {
            return diagnosisTreatmentMapper.deleteById(treatmentIds.get(0)) > 0;
        } else {
            return diagnosisTreatmentMapper.deleteBatchByIds(treatmentIds) > 0;
        }
    }

    @Override
    public TreatmentVO getById(Integer treatmentId) {
        DiagnosisTreatment diagnosisTreatment = diagnosisTreatmentMapper.selectById(treatmentId);
        if (diagnosisTreatment == null) {
            return null;
        }

        TreatmentVO treatmentVO = new TreatmentVO();
        BeanUtils.copyProperties(diagnosisTreatment, treatmentVO);
        return treatmentVO;
    }

    /**
     * 将实体列表转换为VO列表
     */
    private List<TreatmentVO> convertToVOList(List<DiagnosisTreatment> treatmentList) {
        List<TreatmentVO> voList = new ArrayList<>();
        for (DiagnosisTreatment treatment : treatmentList) {
            TreatmentVO vo = new TreatmentVO();
            BeanUtils.copyProperties(treatment, vo);
            voList.add(vo);
        }
        return voList;
    }
}
