
package com.neusoft.medical.controller.basicinfo;

import com.neusoft.medical.common.result.Result;
import com.neusoft.medical.service.basicinfo.HospitalReimbursementService;
import com.neusoft.medical.vo.basicinfo.HospitalReimbursementVO;
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
 * 一级医院报销比例维护控制器
 * @author Neusoft
 * @date 2025-07-10
 */
@Api(tags = "一级医院报销比例维护")
@RestController
@RequestMapping("/api/level1-hospital-ratios")
public class Level1HospitalRatioController {

    @Autowired
    private HospitalReimbursementService hospitalReimbursementService;

    private static final String HOSPITAL_LEVEL = "一级";

    /**
     * 获取一级医院报销比例
     */
    @ApiOperation(value = "获取一级医院报销比例", notes = "根据人员类别获取一级医院报销比例配置")
    @ApiImplicitParam(name = "employeeType", value = "人员类别（在职/退休）", required = true, dataType = "string", paramType = "query", example = "在职")
    @GetMapping
    public Result<List<HospitalReimbursementVO>> getRatios(@RequestParam String employeeType) {
        List<HospitalReimbursementVO> ratioList = hospitalReimbursementService.getRatiosByLevel(HOSPITAL_LEVEL, employeeType);
        return Result.success(ratioList);
    }

    /**
     * 根据ID查询一级医院报销比例信息
     */
    @ApiOperation(value = "查询一级医院报销比例详情", notes = "根据报销比例ID查询详细信息")
    @ApiImplicitParam(name = "ratioId", value = "报销比例ID", required = true, dataType = "int", paramType = "path", example = "1")
    @GetMapping("/{ratioId}")
    public Result<HospitalReimbursementVO> getById(@PathVariable Integer ratioId) {
        HospitalReimbursementVO ratio = hospitalReimbursementService.getById(ratioId);
        return ratio != null ? Result.success(ratio) : Result.error("一级医院报销比例信息不存在");
    }

    /**
     * 新增费用区间
     */
    @ApiOperation(value = "新增一级医院费用区间", notes = "为一级医院新增费用区间和对应的报销比例")
    @PostMapping
    public Result<Void> save(@ApiParam(value = "一级医院报销比例信息", required = true) @Valid @RequestBody HospitalReimbursementVO hospitalReimbursementVO) {
        try {
            // 强制设置为一级医院
            hospitalReimbursementVO.setHospitalLevel(HOSPITAL_LEVEL);

            boolean success = hospitalReimbursementService.save(hospitalReimbursementVO);
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
     * 修改报销比例
     */
    @ApiOperation(value = "修改一级医院报销比例", notes = "根据报销比例ID修改一级医院报销比例配置")
    @ApiImplicitParam(name = "ratioId", value = "报销比例ID", required = true, dataType = "int", paramType = "path", example = "1")
    @PutMapping("/{ratioId}")
    public Result<Void> updateById(
            @PathVariable Integer ratioId,
            @ApiParam(value = "一级医院报销比例信息", required = true) @Valid @RequestBody HospitalReimbursementVO hospitalReimbursementVO) {

        try {
            // 强制设置为一级医院
            hospitalReimbursementVO.setHospitalLevel(HOSPITAL_LEVEL);

            boolean success = hospitalReimbursementService.updateById(ratioId, hospitalReimbursementVO);
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
     * 删除费用区间
     */
    @ApiOperation(value = "删除一级医院费用区间", notes = "根据报销比例ID删除一级医院费用区间配置")
    @ApiImplicitParam(name = "ratioId", value = "报销比例ID", required = true, dataType = "int", paramType = "path", example = "1")
    @DeleteMapping("/{ratioId}")
    public Result<Void> deleteById(@PathVariable Integer ratioId) {
        boolean success = hospitalReimbursementService.deleteById(ratioId);
        if (success) {
            Result<Void> result = Result.success();
            result.setMessage("删除成功");
            return result;
        } else {
            return Result.error("删除失败");
        }
    }

    /**
     * 检查费用区间是否重叠
     */
    @ApiOperation(value = "检查一级医院费用区间是否重叠", notes = "检查指定的费用区间是否与现有配置重叠")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "peopleType", value = "人员类别", required = true, dataType = "string", paramType = "query", example = "1"),
            @ApiImplicitParam(name = "minPayLevel", value = "起付金额", required = true, dataType = "string", paramType = "query", example = "600"),
            @ApiImplicitParam(name = "maxPayLevel", value = "等级线", required = true, dataType = "string", paramType = "query", example = "3000"),
            @ApiImplicitParam(name = "excludeId", value = "排除的ID", dataType = "int", paramType = "query", example = "1")
    })
    @GetMapping("/check-range")
    public Result<Boolean> checkRangeOverlap(@RequestParam String peopleType,
                                             @RequestParam String minPayLevel,
                                             @RequestParam String maxPayLevel,
                                             @RequestParam(required = false) Integer excludeId) {
        boolean overlap = hospitalReimbursementService.isRangeOverlap(HOSPITAL_LEVEL, peopleType, minPayLevel, maxPayLevel, excludeId);
        return Result.success(overlap);
    }
}