package com.neusoft.medical.mapper;

import com.neusoft.medical.entity.PatientRegistration;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 住院患者表数据访问接口
 * @author Neusoft
 * @date 2025-07-12
 */
@Mapper
public interface PatientRegistrationMapper {

    /**
     * 根据ID查询患者信息
     * @param id 患者ID
     * @return 患者信息
     */
    PatientRegistration selectById(@Param("id") Integer id);

    /**
     * 根据患者姓名模糊查询
     * @param personName 患者姓名
     * @return 患者列表
     */
    List<PatientRegistration> selectByPersonName(@Param("personName") String personName);

    /**
     * 插入患者记录
     * @param patient 患者信息
     * @return 影响行数
     */
    int insert(PatientRegistration patient);

    /**
     * 根据ID更新患者信息
     * @param patient 患者信息
     * @return 影响行数
     */
    int updateById(PatientRegistration patient);

    /**
     * 查询所有患者
     * @return 患者列表
     */
    List<PatientRegistration> selectAll();

    /**
     * 根据患者姓名查询总数
     * @param personName 患者姓名
     * @return 总数
     */
    Long countByPersonName(@Param("personName") String personName);

    /**
     * 根据患者姓名分页查询
     * @param personName 患者姓名
     * @param offset 偏移量
     * @param limit 限制数量
     * @return 患者列表
     */
    List<PatientRegistration> selectByPersonNameWithPage(@Param("personName") String personName,
                                                         @Param("offset") Integer offset,
                                                         @Param("limit") Integer limit);
}