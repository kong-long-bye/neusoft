package com.neusoft.medical.mapper.medical;

import com.neusoft.medical.entity.InpatientDrugs;
import com.neusoft.medical.vo.medical.PatientPrescriptionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 患者药品处方Mapper接口
 * @author Neusoft
 * @date 2025-07-10
 */
@Mapper
public interface PatientPrescriptionMapper {
    
    /**
     * 分页查询患者药品处方信息
     * @param patientId 患者ID
     * @param drugName 药品名称
     * @param offset 偏移量
     * @param limit 限制数量
     * @return 患者药品处方列表
     */
    List<PatientPrescriptionVO> selectPatientPrescriptionPage(@Param("patientId") Integer patientId,
                                                             @Param("drugName") String drugName,
                                                             @Param("offset") Integer offset,
                                                             @Param("limit") Integer limit);
    
    /**
     * 查询患者药品处方总数
     * @param patientId 患者ID
     * @param drugName 药品名称
     * @return 总数
     */
    Long selectPatientPrescriptionCount(@Param("patientId") Integer patientId,
                                      @Param("drugName") String drugName);
    
    /**
     * 根据药品名称搜索药品信息
     * @param drugName 药品名称
     * @return 药品信息列表
     */
    List<PatientPrescriptionVO> searchDrugs(@Param("drugName") String drugName);
    
    /**
     * 新增患者药品处方
     * @param inpatientDrugs 患者药品处方信息
     * @return 影响行数
     */
    int insertPatientPrescription(InpatientDrugs inpatientDrugs);
    
    /**
     * 根据ID查询患者药品处方信息
     * @param id 主键ID
     * @return 患者药品处方信息
     */
    PatientPrescriptionVO selectPatientPrescriptionById(@Param("id") Integer id);
    
    /**
     * 更新患者药品处方信息
     * @param inpatientDrugs 患者药品处方信息
     * @return 影响行数
     */
    int updatePatientPrescription(InpatientDrugs inpatientDrugs);
    
    /**
     * 删除患者药品处方信息
     * @param id 主键ID
     * @return 影响行数
     */
    int deletePatientPrescription(@Param("id") Integer id);
} 