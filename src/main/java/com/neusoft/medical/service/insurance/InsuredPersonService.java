package com.neusoft.medical.service.insurance;

import com.neusoft.medical.common.result.PageResult;
import com.neusoft.medical.vo.insurance.InsuredPersonVO;

import java.util.List;

/**
 * 参保人员保险信息维护业务逻辑接口
 * @author Neusoft
 * @date 2025-07-12
 */
public interface InsuredPersonService {

    /**
     * 参保人员分页查询
     * @param pageNum 当前页码
     * @param pageSize 每页记录数
     * @param personName 人员姓名（可选）
     * @return 分页参保人员列表
     */
    PageResult<InsuredPersonVO> getPersonsPage(Integer pageNum, Integer pageSize, String personName);

    /**
     * 参保人员搜索
     * @param personName 人员姓名
     * @return 匹配的参保人员列表
     */
    List<InsuredPersonVO> searchPersons(String personName);

    /**
     * 新增参保人员
     * @param insuredPersonVO 参保人员信息
     * @return 操作是否成功
     */
    boolean addPerson(InsuredPersonVO insuredPersonVO);

    /**
     * 修改参保人员信息
     * @param insuredPersonVO 参保人员信息
     * @return 操作是否成功
     */
    boolean updatePerson(InsuredPersonVO insuredPersonVO);
}