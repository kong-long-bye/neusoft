package com.neusoft.medical.controller.basicinfo;


import com.neusoft.medical.common.result.PageResult;

import com.neusoft.medical.common.result.Result;
import com.neusoft.medical.service.basicinfo.DrugInfoService;
import com.neusoft.medical.vo.basicinfo.DrugInfoVO;
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
 * 医保药品数据维护控制器
 * @author Neusoft
 * @date 2025-07-10
 */
@Api(tags = "医保药品数据维护")
@RestController
@RequestMapping("/api/drugs")
public class DrugInfoController {

    @Autowired
    private DrugInfoService drugInfoService;

    /**
     * 药品信息分页查询
     */
    @ApiOperation(value = "药品信息分页查询", notes = "分页查询药品信息，支持按药品名称模糊查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "query", example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = true, dataType = "int", paramType = "query", example = "10"),
            @ApiImplicitParam(name = "drugName", value = "药品名称（模糊查询）", dataType = "string", paramType = "query", example = "阿莫西林")
    })
    @GetMapping("/page")
    public Result<PageResult<DrugInfoVO>> selectPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String drugName) {

        PageResult<DrugInfoVO> pageResult = drugInfoService.selectPage(pageNum, pageSize, drugName);
        return Result.success(pageResult);
    }

    /**
     * 药品信息搜索
     */
    @ApiOperation(value = "药品信息搜索", notes = "根据药品名称搜索匹配的药品列表")
    @ApiImplicitParam(name = "drugName", value = "药品名称", required = true, dataType = "string", paramType = "query", example = "阿莫西林")
    @GetMapping("/search")
    public Result<List<DrugInfoVO>> searchByName(@RequestParam String drugName) {
        List<DrugInfoVO> drugList = drugInfoService.searchByName(drugName);
        return Result.success(drugList);
    }

    /**
     * 新增药品信息
     */
    @ApiOperation(value = "新增药品信息", notes = "新增药品基本信息")
    @PostMapping
    public Result<Void> save(@ApiParam(value = "药品信息", required = true) @Valid @RequestBody DrugInfoVO drugInfoVO) {
        boolean success = drugInfoService.save(drugInfoVO);
        if (success) {
            Result<Void> result = Result.success();
            result.setMessage("新增成功");
            return result;
        } else {
            return Result.error("新增失败");
        }
    }

    /**
     * 修改药品信息
     */
    @ApiOperation(value = "修改药品信息", notes = "根据药品ID修改药品信息")
    @ApiImplicitParam(name = "drugId", value = "药品ID", required = true, dataType = "int", paramType = "path", example = "1")
    @PutMapping("/{drugId}")
    public Result<Void> updateById(
            @PathVariable Integer drugId,
            @ApiParam(value = "药品信息", required = true) @Valid @RequestBody DrugInfoVO drugInfoVO) {

        boolean success = drugInfoService.updateById(drugId, drugInfoVO);
        if (success) {
            Result<Void> result = Result.success();
            result.setMessage("修改成功");
            return result;
        } else {
            return Result.error("修改失败");
        }
    }

    /**
     * 删除药品信息（单选/多选）
     */
    @ApiOperation(value = "删除药品信息", notes = "根据药品ID列表删除药品信息，支持单选和多选")
    @DeleteMapping
    public Result<Void> deleteByIds(@ApiParam(value = "药品ID列表", required = true) @RequestBody List<Integer> drugIds) {
        boolean success = drugInfoService.deleteByIds(drugIds);
        if (success) {
            Result<Void> result = Result.success();
            result.setMessage("删除成功");
            return result;
        } else {
            return Result.error("删除失败");
        }
    }

    /**
     * 根据ID查询药品信息
     */
    @ApiOperation(value = "查询药品详情", notes = "根据药品ID查询药品详细信息")
    @ApiImplicitParam(name = "drugId", value = "药品ID", required = true, dataType = "int", paramType = "path", example = "1")
    @GetMapping("/{drugId}")
    public Result<DrugInfoVO> getById(@PathVariable Integer drugId) {
        DrugInfoVO drugInfo = drugInfoService.getById(drugId);
        return drugInfo != null ? Result.success(drugInfo) : Result.error("药品信息不存在");
    }
}