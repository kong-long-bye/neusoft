package com.neusoft.medical.controller.basicinfo;

import com.neusoft.medical.common.result.Result;
import com.neusoft.medical.service.basicinfo.DrugReimbursementService;
import com.neusoft.medical.vo.basicinfo.DrugReimbursementVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 药品报销比例维护控制器
 * @author Neusoft
 * @date 2025-07-10
 */
@Api(tags = "药品报销比例维护")
@RestController
@RequestMapping("/api/drug-reimbursement-ratios")
public class DrugReimbursementController {

    @Autowired
    private DrugReimbursementService drugReimbursementService;

    /**
     * 获取药品报销比例列表
     */
    @ApiOperation(value = "获取药品报销比例列表", notes = "获取所有药品报销比例配置信息")
    @GetMapping
    public Result<List<DrugReimbursementVO>> getAllRatios() {
        List<DrugReimbursementVO> ratioList = drugReimbursementService.getAllRatios();
        return Result.success(ratioList);
    }

    /**
     * 获取启用状态的药品报销比例列表
     */
    @ApiOperation(value = "获取启用的药品报销比例列表", notes = "获取状态为启用的药品报销比例配置信息")
    @GetMapping("/enabled")
    public Result<List<DrugReimbursementVO>> getEnabledRatios() {
        List<DrugReimbursementVO> ratioList = drugReimbursementService.getEnabledRatios();
        return Result.success(ratioList);
    }

    /**
     * 根据ID查询药品报销比例信息
     */
    @ApiOperation(value = "查询药品报销比例详情", notes = "根据报销比例ID查询详细信息")
    @ApiImplicitParam(name = "ratioId", value = "报销比例ID", required = true, dataType = "int", paramType = "path", example = "1")
    @GetMapping("/{ratioId}")
    public Result<DrugReimbursementVO> getById(@PathVariable Integer ratioId) {
        DrugReimbursementVO ratio = drugReimbursementService.getById(ratioId);
        return ratio != null ? Result.success(ratio) : Result.error("药品报销比例信息不存在");
    }

    /**
     * 新增药品类型
     */
    @ApiOperation(value = "新增药品报销比例", notes = "新增药品类型和对应的报销比例配置")
    @PostMapping
    public Result<Void> save(@ApiParam(value = "药品报销比例信息", required = true) @Valid @RequestBody DrugReimbursementVO drugReimbursementVO) {
        try {
            boolean success = drugReimbursementService.save(drugReimbursementVO);
            if (success) {
                Result<Void> result = Result.success();
                result.setMessage("新增成功");
                return result;
            } else {
                return Result.error("新增失败");
            }
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 修改药品报销比例
     */
    @ApiOperation(value = "修改药品报销比例", notes = "根据报销比例ID修改药品报销比例配置")
    @ApiImplicitParam(name = "ratioId", value = "报销比例ID", required = true, dataType = "int", paramType = "path", example = "1")
    @PutMapping("/{ratioId}")
    public Result<Void> updateById(
            @PathVariable Integer ratioId,
            @ApiParam(value = "药品报销比例信息", required = true) @Valid @RequestBody DrugReimbursementVO drugReimbursementVO) {

        try {
            boolean success = drugReimbursementService.updateById(ratioId, drugReimbursementVO);
            if (success) {
                Result<Void> result = Result.success();
                result.setMessage("修改成功");
                return result;
            } else {
                return Result.error("修改失败");
            }
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 删除药品类型
     */
    @ApiOperation(value = "删除药品报销比例", notes = "根据报销比例ID删除药品报销比例配置")
    @ApiImplicitParam(name = "ratioId", value = "报销比例ID", required = true, dataType = "int", paramType = "path", example = "1")
    @DeleteMapping("/{ratioId}")
    public Result<Void> deleteById(@PathVariable Integer ratioId) {
        boolean success = drugReimbursementService.deleteById(ratioId);
        if (success) {
            Result<Void> result = Result.success();
            result.setMessage("删除成功");
            return result;
        } else {
            return Result.error("删除失败");
        }
    }

    /**
     * 检查报销类型是否存在
     */
    @ApiOperation(value = "检查报销类型是否存在", notes = "检查指定的报销类型是否已经存在")
    @ApiImplicitParam(name = "drugReimbursementType", value = "报销类型", required = true, dataType = "string", paramType = "query", example = "甲类药品")
    @GetMapping("/check-type")
    public Result<Boolean> checkTypeExists(@RequestParam String drugReimbursementType,
                                           @RequestParam(required = false) Integer excludeId) {
        boolean exists = drugReimbursementService.existsByType(drugReimbursementType, excludeId);
        return Result.success(exists);
    }
}