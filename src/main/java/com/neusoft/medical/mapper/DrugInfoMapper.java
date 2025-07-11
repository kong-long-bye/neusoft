package com.neusoft.medical.mapper;


import com.neusoft.medical.entity.DrugInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 药品信息表数据访问接口
 * @author Neusoft
 * @date 2025-07-10
 */
@Mapper
public interface DrugInfoMapper {

    /**
     * 根据ID查询药品信息
     * @param id 药品ID
     * @return 药品信息
     */
    DrugInfo selectById(@Param("id") Integer id);

    /**
     * 分页查询药品信息
     * @param offset 偏移量
     * @param limit 每页记录数
     * @param drugName 药品名称（模糊查询）
     * @return 药品信息列表
     */
    List<DrugInfo> selectPage(@Param("offset") Integer offset,
                              @Param("limit") Integer limit,
                              @Param("drugName") String drugName);

    /**
     * 查询药品总数
     * @param drugName 药品名称（模糊查询）
     * @return 总记录数
     */
    Long selectCount(@Param("drugName") String drugName);

    /**
     * 根据药品名称搜索
     * @param drugName 药品名称
     * @return 药品信息列表
     */
    List<DrugInfo> searchByName(@Param("drugName") String drugName);

    /**
     * 新增药品信息
     * @param drugInfo 药品信息
     * @return 影响行数
     */
    int insert(DrugInfo drugInfo);

    /**
     * 根据ID更新药品信息
     * @param drugInfo 药品信息
     * @return 影响行数
     */
    int updateById(DrugInfo drugInfo);

    /**
     * 根据ID删除药品信息
     * @param id 药品ID
     * @return 影响行数
     */
    int deleteById(@Param("id") Integer id);

    /**
     * 批量删除药品信息
     * @param ids 药品ID列表
     * @return 影响行数
     */
    int deleteBatchByIds(@Param("ids") List<Integer> ids);
}

