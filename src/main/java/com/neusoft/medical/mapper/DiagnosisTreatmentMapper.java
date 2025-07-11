package com.neusoft.medical.mapper;

import com.neusoft.medical.entity.DiagnosisTreatment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 诊疗项目表数据访问接口
 * @author Neusoft
 * @date 2025-07-10
 */
@Mapper
public interface DiagnosisTreatmentMapper {

    /**
     * 根据ID查询诊疗项目信息
     * @param id 诊疗项目ID
     * @return 诊疗项目信息
     */
    DiagnosisTreatment selectById(@Param("id") Integer id);

    /**
     * 分页查询诊疗项目信息
     * @param offset 偏移量
     * @param limit 每页记录数
     * @param treatmentName 诊疗项目名称（模糊查询）
     * @return 诊疗项目信息列表
     */
    List<DiagnosisTreatment> selectPage(@Param("offset") Integer offset,
                                        @Param("limit") Integer limit,
                                        @Param("treatmentName") String treatmentName);

    /**
     * 查询诊疗项目总数
     * @param treatmentName 诊疗项目名称（模糊查询）
     * @return 总记录数
     */
    Long selectCount(@Param("treatmentName") String treatmentName);

    /**
     * 根据诊疗项目名称搜索
     * @param treatmentName 诊疗项目名称
     * @return 诊疗项目信息列表
     */
    List<DiagnosisTreatment> searchByName(@Param("treatmentName") String treatmentName);

    /**
     * 新增诊疗项目信息
     * @param diagnosisTreatment 诊疗项目信息
     * @return 影响行数
     */
    int insert(DiagnosisTreatment diagnosisTreatment);

    /**
     * 根据ID更新诊疗项目信息
     * @param diagnosisTreatment 诊疗项目信息
     * @return 影响行数
     */
    int updateById(DiagnosisTreatment diagnosisTreatment);

    /**
     * 根据ID删除诊疗项目信息
     * @param id 诊疗项目ID
     * @return 影响行数
     */
    int deleteById(@Param("id") Integer id);

    /**
     * 批量删除诊疗项目信息
     * @param ids 诊疗项目ID列表
     * @return 影响行数
     */
    int deleteBatchByIds(@Param("ids") List<Integer> ids);
}