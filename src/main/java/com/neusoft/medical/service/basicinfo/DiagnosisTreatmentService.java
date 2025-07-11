package com.neusoft.medical.service.basicinfo;

import com.neusoft.medical.common.result.PageResult;
import com.neusoft.medical.vo.basicinfo.TreatmentVO;
import java.util.List;

/**
 * 诊疗项目数据维护业务逻辑接口
 * @author Neusoft
 * @date 2025-07-10
 */
public interface DiagnosisTreatmentService {

    /**
     * 诊疗项目分页查询
     * @param pageNum 页码
     * @param pageSize 每页记录数
     * @param treatmentName 诊疗项目名称（模糊查询）
     * @return 分页结果
     */
    PageResult<TreatmentVO> selectPage(Integer pageNum, Integer pageSize, String treatmentName);

    /**
     * 诊疗项目搜索
     * @param treatmentName 诊疗项目名称
     * @return 诊疗项目列表
     */
    List<TreatmentVO> searchByName(String treatmentName);

    /**
     * 新增诊疗项目
     * @param treatmentVO 诊疗项目信息
     * @return 操作结果
     */
    boolean save(TreatmentVO treatmentVO);

    /**
     * 修改诊疗项目
     * @param treatmentId 诊疗项目ID
     * @param treatmentVO 诊疗项目信息
     * @return 操作结果
     */
    boolean updateById(Integer treatmentId, TreatmentVO treatmentVO);

    /**
     * 删除诊疗项目（单选/多选）
     * @param treatmentIds 诊疗项目ID列表
     * @return 操作结果
     */
    boolean deleteByIds(List<Integer> treatmentIds);

    /**
     * 根据ID查询诊疗项目信息
     * @param treatmentId 诊疗项目ID
     * @return 诊疗项目信息
     */
    TreatmentVO getById(Integer treatmentId);
}
