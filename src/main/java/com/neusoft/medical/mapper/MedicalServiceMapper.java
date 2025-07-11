package com.neusoft.medical.mapper;

import com.neusoft.medical.entity.MedicalService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 医疗服务设施表数据访问接口
 * @author Neusoft
 * @date 2025-07-10
 */
@Mapper
public interface MedicalServiceMapper {

    /**
     * 根据ID查询医疗服务设施信息
     * @param id 设施ID
     * @return 医疗服务设施信息
     */
    MedicalService selectById(@Param("id") Integer id);

    /**
     * 分页查询医疗服务设施信息
     * @param offset 偏移量
     * @param limit 每页记录数
     * @param facilityName 设施名称（模糊查询）
     * @return 医疗服务设施信息列表
     */
    List<MedicalService> selectPage(@Param("offset") Integer offset,
                                    @Param("limit") Integer limit,
                                    @Param("facilityName") String facilityName);

    /**
     * 查询医疗服务设施总数
     * @param facilityName 设施名称（模糊查询）
     * @return 总记录数
     */
    Long selectCount(@Param("facilityName") String facilityName);

    /**
     * 根据设施名称搜索
     * @param facilityName 设施名称
     * @return 医疗服务设施信息列表
     */
    List<MedicalService> searchByName(@Param("facilityName") String facilityName);

    /**
     * 新增医疗服务设施信息
     * @param medicalService 医疗服务设施信息
     * @return 影响行数
     */
    int insert(MedicalService medicalService);

    /**
     * 根据ID更新医疗服务设施信息
     * @param medicalService 医疗服务设施信息
     * @return 影响行数
     */
    int updateById(MedicalService medicalService);

    /**
     * 根据ID删除医疗服务设施信息
     * @param id 设施ID
     * @return 影响行数
     */
    int deleteById(@Param("id") Integer id);

    /**
     * 批量删除医疗服务设施信息
     * @param ids 设施ID列表
     * @return 影响行数
     */
    int deleteBatchByIds(@Param("ids") List<Integer> ids);
}