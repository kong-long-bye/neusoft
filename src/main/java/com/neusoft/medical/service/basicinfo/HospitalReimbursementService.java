package com.neusoft.medical.service.basicinfo;

import com.neusoft.medical.vo.basicinfo.HospitalReimbursementVO;
import java.util.List;

/**
 * 医院报销比例维护业务逻辑接口
 * @author Neusoft
 * @date 2025-07-10
 */
public interface HospitalReimbursementService {

    /**
     * 根据医院等级和人员类别获取报销比例列表
     * @param hospitalLevel 医院等级
     * @param employeeType 人员类别（在职/退休）
     * @return 报销比例列表
     */
    List<HospitalReimbursementVO> getRatiosByLevel(String hospitalLevel, String employeeType);

    /**
     * 根据ID查询医院报销比例信息
     * @param ratioId 报销比例ID
     * @return 医院报销比例信息
     */
    HospitalReimbursementVO getById(Integer ratioId);

    /**
     * 新增医院报销比例
     * @param hospitalReimbursementVO 医院报销比例信息
     * @return 操作结果
     */
    boolean save(HospitalReimbursementVO hospitalReimbursementVO);

    /**
     * 修改医院报销比例
     * @param ratioId 报销比例ID
     * @param hospitalReimbursementVO 医院报销比例信息
     * @return 操作结果
     */
    boolean updateById(Integer ratioId, HospitalReimbursementVO hospitalReimbursementVO);

    /**
     * 删除医院报销比例
     * @param ratioId 报销比例ID
     * @return 操作结果
     */
    boolean deleteById(Integer ratioId);

    /**
     * 检查费用区间是否重叠
     * @param hospitalLevel 医院等级
     * @param peopleType 人员类别
     * @param minPayLevel 起付金额
     * @param maxPayLevel 等级线
     * @param excludeId 排除的ID（用于修改时检查重复）
     * @return 是否重叠
     */
    boolean isRangeOverlap(String hospitalLevel, String peopleType, String minPayLevel, String maxPayLevel, Integer excludeId);
}
