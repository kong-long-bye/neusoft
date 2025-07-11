package com.neusoft.medical.service.basicinfo;

import com.neusoft.medical.common.result.PageResult;
import com.neusoft.medical.vo.basicinfo.MedicalServiceVO;
import java.util.List;

/**
 * 医疗服务设施数据维护业务逻辑接口
 * @author Neusoft
 * @date 2025-07-10
 */
public interface MedicalServiceService {

    /**
     * 医疗服务设施分页查询
     * @param pageNum 页码
     * @param pageSize 每页记录数
     * @param facilityName 设施名称（模糊查询）
     * @return 分页结果
     */
    PageResult<MedicalServiceVO> selectPage(Integer pageNum, Integer pageSize, String facilityName);

    /**
     * 医疗服务设施搜索
     * @param facilityName 设施名称
     * @return 医疗服务设施列表
     */
    List<MedicalServiceVO> searchByName(String facilityName);

    /**
     * 新增医疗服务设施
     * @param medicalServiceVO 医疗服务设施信息
     * @return 操作结果
     */
    boolean save(MedicalServiceVO medicalServiceVO);

    /**
     * 修改医疗服务设施
     * @param facilityId 设施ID
     * @param medicalServiceVO 医疗服务设施信息
     * @return 操作结果
     */
    boolean updateById(Integer facilityId, MedicalServiceVO medicalServiceVO);

    /**
     * 删除医疗服务设施（单选/多选）
     * @param facilityIds 设施ID列表
     * @return 操作结果
     */
    boolean deleteByIds(List<Integer> facilityIds);

    /**
     * 根据ID查询医疗服务设施信息
     * @param facilityId 设施ID
     * @return 医疗服务设施信息
     */
    MedicalServiceVO getById(Integer facilityId);
}