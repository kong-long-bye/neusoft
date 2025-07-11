package com.neusoft.medical.mapper;

import com.neusoft.medical.entity.DrugReimbursement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 药品报销等级信息表数据访问接口
 * @author Neusoft
 * @date 2025-07-10
 */
@Mapper
public interface DrugReimbursementMapper {

    /**
     * 根据ID查询药品报销比例信息
     * @param id 报销比例ID
     * @return 药品报销比例信息
     */
    DrugReimbursement selectById(@Param("id") Integer id);

    /**
     * 获取所有药品报销比例列表
     * @return 药品报销比例列表
     */
    List<DrugReimbursement> selectAll();

    /**
     * 获取启用状态的药品报销比例列表
     * @return 启用状态的药品报销比例列表
     */
    List<DrugReimbursement> selectEnabled();

    /**
     * 新增药品报销比例
     * @param drugReimbursement 药品报销比例信息
     * @return 影响行数
     */
    int insert(DrugReimbursement drugReimbursement);

    /**
     * 根据ID更新药品报销比例
     * @param drugReimbursement 药品报销比例信息
     * @return 影响行数
     */
    int updateById(DrugReimbursement drugReimbursement);

    /**
     * 根据ID删除药品报销比例
     * @param id 报销比例ID
     * @return 影响行数
     */
    int deleteById(@Param("id") Integer id);

    /**
     * 根据报销类型查询是否存在
     * @param drugReimbursementType 报销类型
     * @param excludeId 排除的ID（用于修改时检查重复）
     * @return 存在的记录数
     */
    int countByType(@Param("drugReimbursementType") String drugReimbursementType,
                    @Param("excludeId") Integer excludeId);
}