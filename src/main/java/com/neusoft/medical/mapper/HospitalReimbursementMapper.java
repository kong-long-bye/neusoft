package com.neusoft.medical.mapper;

import com.neusoft.medical.entity.HospitalReimbursement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 医院报销等级信息表数据访问接口
 * @author Neusoft
 * @date 2025-07-10
 */
@Mapper
public interface HospitalReimbursementMapper {

    /**
     * 根据ID查询医院报销比例信息
     * @param id 报销比例ID
     * @return 医院报销比例信息
     */
    HospitalReimbursement selectById(@Param("id") Integer id);

    /**
     * 根据医院等级和人员类别查询报销比例列表
     * @param hospitalLevel 医院等级
     * @param peopleType 人员类别（可选）
     * @return 医院报销比例列表
     */
    List<HospitalReimbursement> selectByHospitalLevel(@Param("hospitalLevel") String hospitalLevel,
                                                      @Param("peopleType") String peopleType);

    /**
     * 新增医院报销比例
     * @param hospitalReimbursement 医院报销比例信息
     * @return 影响行数
     */
    int insert(HospitalReimbursement hospitalReimbursement);

    /**
     * 根据ID更新医院报销比例
     * @param hospitalReimbursement 医院报销比例信息
     * @return 影响行数
     */
    int updateById(HospitalReimbursement hospitalReimbursement);

    /**
     * 根据ID删除医院报销比例
     * @param id 报销比例ID
     * @return 影响行数
     */
    int deleteById(@Param("id") Integer id);

    /**
     * 检查费用区间是否重叠
     * @param hospitalLevel 医院等级
     * @param peopleType 人员类别
     * @param minPayLevel 起付金额
     * @param maxPayLevel 等级线
     * @param excludeId 排除的ID（用于修改时检查重复）
     * @return 重叠的记录数
     */
    int countOverlapRange(@Param("hospitalLevel") String hospitalLevel,
                          @Param("peopleType") String peopleType,
                          @Param("minPayLevel") String minPayLevel,
                          @Param("maxPayLevel") String maxPayLevel,
                          @Param("excludeId") Integer excludeId);
}