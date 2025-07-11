package com.neusoft.medical.service.basicinfo;

import com.neusoft.medical.vo.basicinfo.DrugReimbursementVO;
import java.util.List;

/**
 * 药品报销比例维护业务逻辑接口
 * @author Neusoft
 * @date 2025-07-10
 */
public interface DrugReimbursementService {

    /**
     * 获取药品报销比例列表
     * @return 药品报销比例列表
     */
    List<DrugReimbursementVO> getAllRatios();

    /**
     * 获取启用状态的药品报销比例列表
     * @return 启用状态的药品报销比例列表
     */
    List<DrugReimbursementVO> getEnabledRatios();

    /**
     * 根据ID查询药品报销比例信息
     * @param ratioId 报销比例ID
     * @return 药品报销比例信息
     */
    DrugReimbursementVO getById(Integer ratioId);

    /**
     * 新增药品报销比例
     * @param drugReimbursementVO 药品报销比例信息
     * @return 操作结果
     */
    boolean save(DrugReimbursementVO drugReimbursementVO);

    /**
     * 修改药品报销比例
     * @param ratioId 报销比例ID
     * @param drugReimbursementVO 药品报销比例信息
     * @return 操作结果
     */
    boolean updateById(Integer ratioId, DrugReimbursementVO drugReimbursementVO);

    /**
     * 删除药品报销比例
     * @param ratioId 报销比例ID
     * @return 操作结果
     */
    boolean deleteById(Integer ratioId);

    /**
     * 检查报销类型是否已存在
     * @param drugReimbursementType 报销类型
     * @param excludeId 排除的ID（用于修改时检查重复）
     * @return 是否存在
     */
    boolean existsByType(String drugReimbursementType, Integer excludeId);
}
