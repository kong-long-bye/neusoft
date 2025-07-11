package com.neusoft.medical.controller.basicinfo;

import com.neusoft.medical.common.result.PageResult;
import com.neusoft.medical.common.result.Result;
import com.neusoft.medical.service.basicinfo.DiagnosisTreatmentService;
import com.neusoft.medical.vo.basicinfo.TreatmentVO;
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
 * 诊疗项目数据维护控制器
 * @author Neusoft
 * @date 2025-07-10
 */
@Api(tags = "诊疗项目数据维护")
@RestController
@RequestMapping("/api/treatments")
public class DiagnosisTreatmentController {

    @Autowired
    private DiagnosisTreatmentService diagnosisTreatmentService;

    /**
     * 诊疗项目分页查询
     */
    @ApiOperation(value = "诊疗项目分页查询", notes = "分页查询诊疗项目信息，支持按项目名称模糊查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "query", example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = true, dataType = "int", paramType = "query", example = "10"),
            @ApiImplicitParam(name = "treatmentName", value = "诊疗项目名称（模糊查询）", dataType = "string", paramType = "query", example = "CT检查")
    })
    @GetMapping("/page")
    public Result<PageResult<TreatmentVO>> selectPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String treatmentName) {

        PageResult<TreatmentVO> pageResult = diagnosisTreatmentService.selectPage(pageNum, pageSize, treatmentName);
        return Result.success(pageResult);
    }

    /**
     * 诊疗项目搜索
     */
    @ApiOperation(value = "诊疗项目搜索", notes = "根据诊疗项目名称搜索匹配的项目列表")
    @ApiImplicitParam(name = "treatmentName", value = "诊疗项目名称", required = true, dataType = "string", paramType = "query", example = "CT检查")
    @GetMapping("/search")
    public Result<List<TreatmentVO>> searchByName(@RequestParam String treatmentName) {
        List<TreatmentVO> treatmentList = diagnosisTreatmentService.searchByName(treatmentName);
        return Result.success(treatmentList);
    }

    /**
     * 新增诊疗项目
     */
    @ApiOperation(value = "新增诊疗项目", notes = "新增诊疗项目基本信息")
    @PostMapping
    public Result<Void> save(@ApiParam(value = "诊疗项目信息", required = true) @Valid @RequestBody TreatmentVO treatmentVO) {
        boolean success = diagnosisTreatmentService.save(treatmentVO);
        if (success) {
            Result<Void> result = Result.success();
            result.setMessage("新增成功");
            return result;
        } else {
            return Result.error("新增失败");
        }
    }

    /**
     * 修改诊疗项目
     */
    @ApiOperation(value = "修改诊疗项目", notes = "根据诊疗项目ID修改项目信息")
    @ApiImplicitParam(name = "treatmentId", value = "诊疗项目ID", required = true, dataType = "int", paramType = "path", example = "1")
    @PutMapping("/{treatmentId}")
    public Result<Void> updateById(
            @PathVariable Integer treatmentId,
            @ApiParam(value = "诊疗项目信息", required = true) @Valid @RequestBody TreatmentVO treatmentVO) {

        boolean success = diagnosisTreatmentService.updateById(treatmentId, treatmentVO);
        if (success) {
            Result<Void> result = Result.success();
            result.setMessage("修改成功");
            return result;
        } else {
            return Result.error("修改失败");
        }
    }

    /**
     * 删除诊疗项目（单选/多选）
     */
    @ApiOperation(value = "删除诊疗项目", notes = "根据诊疗项目ID列表删除项目信息，支持单选和多选")
    @DeleteMapping
    public Result<Void> deleteByIds(@ApiParam(value = "诊疗项目ID列表", required = true) @RequestBody List<Integer> treatmentIds) {
        boolean success = diagnosisTreatmentService.deleteByIds(treatmentIds);
        if (success) {
            Result<Void> result = Result.success();
            result.setMessage("删除成功");
            return result;
        } else {
            return Result.error("删除失败");
        }
    }

    /**
     * 根据ID查询诊疗项目信息
     */
    @ApiOperation(value = "查询诊疗项目详情", notes = "根据诊疗项目ID查询项目详细信息")
    @ApiImplicitParam(name = "treatmentId", value = "诊疗项目ID", required = true, dataType = "int", paramType = "path", example = "1")
    @GetMapping("/{treatmentId}")
    public Result<TreatmentVO> getById(@PathVariable Integer treatmentId) {
        TreatmentVO treatment = diagnosisTreatmentService.getById(treatmentId);
        return treatment != null ? Result.success(treatment) : Result.error("诊疗项目信息不存在");
    }
}
