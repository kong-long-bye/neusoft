package com.neusoft.medical.controller.basicinfo;

import com.neusoft.medical.common.result.PageResult;
import com.neusoft.medical.common.result.Result;
import com.neusoft.medical.service.basicinfo.MedicalServiceService;
import com.neusoft.medical.vo.basicinfo.MedicalServiceVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 医疗服务设施数据维护控制器
 * @author Neusoft
 * @date 2025-07-10
 */
@Api(tags = "医疗服务设施数据维护")
@RestController
@RequestMapping("/api/medical-facilities")
public class MedicalServiceController {

    @Autowired
    private MedicalServiceService medicalServiceService;

    /**
     * 医疗服务设施分页查询
     */
    @ApiOperation(value = "医疗服务设施分页查询", notes = "分页查询医疗服务设施信息，支持按设施名称模糊查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "query", example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = true, dataType = "int", paramType = "query", example = "10"),
            @ApiImplicitParam(name = "facilityName", value = "设施名称（模糊查询）", dataType = "string", paramType = "query", example = "病房")
    })
    @GetMapping("/page")
    public Result<PageResult<MedicalServiceVO>> selectPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String facilityName) {

        PageResult<MedicalServiceVO> pageResult = medicalServiceService.selectPage(pageNum, pageSize, facilityName);
        return Result.success(pageResult);
    }

    /**
     * 医疗服务设施搜索
     */
    @ApiOperation(value = "医疗服务设施搜索", notes = "根据设施名称搜索匹配的医疗服务设施列表")
    @ApiImplicitParam(name = "facilityName", value = "设施名称", required = true, dataType = "string", paramType = "query", example = "病房")
    @GetMapping("/search")
    public Result<List<MedicalServiceVO>> searchByName(@RequestParam String facilityName) {
        List<MedicalServiceVO> facilityList = medicalServiceService.searchByName(facilityName);
        return Result.success(facilityList);
    }

    /**
     * 新增医疗服务设施
     */
    @ApiOperation(value = "新增医疗服务设施", notes = "新增医疗服务设施基本信息")
    @PostMapping
    public Result<Void> save(@ApiParam(value = "医疗服务设施信息", required = true) @Valid @RequestBody MedicalServiceVO medicalServiceVO) {
        boolean success = medicalServiceService.save(medicalServiceVO);
        if (success) {
            Result<Void> result = Result.success();
            result.setMessage("新增成功");
            return result;
        } else {
            return Result.error("新增失败");
        }
    }

    /**
     * 修改医疗服务设施
     */
    @ApiOperation(value = "修改医疗服务设施", notes = "根据设施ID修改医疗服务设施信息")
    @ApiImplicitParam(name = "facilityId", value = "设施ID", required = true, dataType = "int", paramType = "path", example = "1")
    @PutMapping("/{facilityId}")
    public Result<Void> updateById(
            @PathVariable Integer facilityId,
            @ApiParam(value = "医疗服务设施信息", required = true) @Valid @RequestBody MedicalServiceVO medicalServiceVO) {

        boolean success = medicalServiceService.updateById(facilityId, medicalServiceVO);
        if (success) {
            Result<Void> result = Result.success();
            result.setMessage("修改成功");
            return result;
        } else {
            return Result.error("修改失败");
        }
    }

    /**
     * 删除医疗服务设施（单选/多选）
     */
    @ApiOperation(value = "删除医疗服务设施", notes = "根据设施ID列表删除医疗服务设施信息，支持单选和多选")
    @DeleteMapping
    public Result<Void> deleteByIds(@ApiParam(value = "设施ID列表", required = true) @RequestBody List<Integer> facilityIds) {
        boolean success = medicalServiceService.deleteByIds(facilityIds);
        if (success) {
            Result<Void> result = Result.success();
            result.setMessage("删除成功");
            return result;
        } else {
            return Result.error("删除失败");
        }
    }

    /**
     * 根据ID查询医疗服务设施信息
     */
    @ApiOperation(value = "查询医疗服务设施详情", notes = "根据设施ID查询医疗服务设施详细信息")
    @ApiImplicitParam(name = "facilityId", value = "设施ID", required = true, dataType = "int", paramType = "path", example = "1")
    @GetMapping("/{facilityId}")
    public Result<MedicalServiceVO> getById(@PathVariable Integer facilityId) {
        MedicalServiceVO facility = medicalServiceService.getById(facilityId);
        return facility != null ? Result.success(facility) : Result.error("医疗服务设施信息不存在");
    }
}