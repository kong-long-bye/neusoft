package com.neusoft.medical.mapper.medical;

import com.neusoft.medical.entity.InpatientMedical;
import com.neusoft.medical.vo.medical.PatientMedicalServiceVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 患者医疗服务Mapper接口
 * @author Neusoft
 * @date 2025-07-10
 */
@Mapper
public interface PatientMedicalServiceMapper {
    
    /**
     * 分页查询患者医疗服务信息
     * @param patientId 患者ID
     * @param serviceName 医疗服务名称
     * @param offset 偏移量
     * @param limit 限制数量
     * @return 患者医疗服务列表
     */
    List<PatientMedicalServiceVO> selectPatientMedicalServicePage(@Param("patientId") Integer patientId,
                                                                 @Param("serviceName") String serviceName,
                                                                 @Param("offset") Integer offset,
                                                                 @Param("limit") Integer limit);
    
    /**
     * 查询患者医疗服务总数
     * @param patientId 患者ID
     * @param serviceName 医疗服务名称
     * @return 总数
     */
    Long selectPatientMedicalServiceCount(@Param("patientId") Integer patientId,
                                        @Param("serviceName") String serviceName);
    
    /**
     * 根据医疗服务名称搜索医疗服务信息
     * @param serviceName 医疗服务名称
     * @return 医疗服务信息列表
     */
    List<PatientMedicalServiceVO> searchMedicalServices(@Param("serviceName") String serviceName);
    
    /**
     * 新增患者医疗服务
     * @param inpatientMedical 患者医疗服务信息
     * @return 影响行数
     */
    int insertPatientMedicalService(InpatientMedical inpatientMedical);
    
    /**
     * 根据ID查询患者医疗服务信息
     * @param id 主键ID
     * @return 患者医疗服务信息
     */
    PatientMedicalServiceVO selectPatientMedicalServiceById(@Param("id") Integer id);
    
    /**
     * 更新患者医疗服务信息
     * @param inpatientMedical 患者医疗服务信息
     * @return 影响行数
     */
    int updatePatientMedicalService(InpatientMedical inpatientMedical);
    
    /**
     * 删除患者医疗服务信息
     * @param id 主键ID
     * @return 影响行数
     */
    int deletePatientMedicalService(@Param("id") Integer id);
} 