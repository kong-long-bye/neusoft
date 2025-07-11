package com.neusoft.medical.service.basicinfo;

import com.neusoft.medical.common.result.PageResult;
import com.neusoft.medical.entity.DrugInfo;
import com.neusoft.medical.vo.basicinfo.DrugInfoVO;
import java.util.List;

/**
 * 医保药品数据维护业务逻辑接口
 * @author Neusoft
 * @date 2025-07-10
 */
public interface DrugInfoService {

    /**
     * 药品信息分页查询
     * @param pageNum 页码
     * @param pageSize 每页记录数
     * @param drugName 药品名称（模糊查询）
     * @return 分页结果
     */
    PageResult<DrugInfoVO> selectPage(Integer pageNum, Integer pageSize, String drugName);

    /**
     * 药品信息搜索
     * @param drugName 药品名称
     * @return 药品信息列表
     */
    List<DrugInfoVO> searchByName(String drugName);

    /**
     * 新增药品信息
     * @param drugInfoVO 药品信息
     * @return 操作结果
     */
    boolean save(DrugInfoVO drugInfoVO);

    /**
     * 修改药品信息
     * @param drugId 药品ID
     * @param drugInfoVO 药品信息
     * @return 操作结果
     */
    boolean updateById(Integer drugId, DrugInfoVO drugInfoVO);

    /**
     * 删除药品信息（单选/多选）
     * @param drugIds 药品ID列表
     * @return 操作结果
     */
    boolean deleteByIds(List<Integer> drugIds);

    /**
     * 根据ID查询药品信息
     * @param drugId 药品ID
     * @return 药品信息
     */
    DrugInfoVO getById(Integer drugId);
}