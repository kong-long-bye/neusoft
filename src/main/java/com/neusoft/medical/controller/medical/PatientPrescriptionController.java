package com.neusoft.medical.controller.medical;

import com.neusoft.medical.common.result.PageResult;
import com.neusoft.medical.common.result.Result;
import com.neusoft.medical.entity.InpatientDrugs;
import com.neusoft.medical.service.medical.PatientPrescriptionService;
import com.neusoft.medical.vo.medical.PatientPrescriptionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 患者药品处方控制器
 * @author Neusoft
 * @date 2025-07-10
 */
@Api(tags = "患者药品处方管理")
@RestController
@RequestMapping("/api/patient-prescriptions")
public class PatientPrescriptionController {
    
    @Autowired
    private PatientPrescriptionService patientPrescriptionService;
    
    /**
     * 患者药品处方分页查询
     */
    @ApiOperation(value = "患者药品处方分页查询", notes = "分页查询患者药品处方信息")
    @GetMapping("/page")
    public Result<PageResult<PatientPrescriptionVO>> getPatientPrescriptionPage(
            @ApiParam(value = "页码", example = "1") @RequestParam(defaultValue = "1") Integer pageNum,
            @ApiParam(value = "每页大小", example = "10") @RequestParam(defaultValue = "10") Integer pageSize,
            @ApiParam(value = "患者ID") @RequestParam(required = false) Integer patientId,
            @ApiParam(value = "药品名称") @RequestParam(required = false) String drugName) {
        
        try {
            PageResult<PatientPrescriptionVO> result = patientPrescriptionService.getPatientPrescriptionPage(pageNum, pageSize, patientId, drugName);
            return Result.success(result);
        } catch (Exception e) {
            return Result.error("查询失败：" + e.getMessage());
        }
    }
    
    /**
     * 药品信息搜索
     */
    @ApiOperation(value = "药品信息搜索", notes = "根据药品名称搜索药品信息")
    @GetMapping("/search")
    public Result<List<PatientPrescriptionVO>> searchDrugs(
            @ApiParam(value = "药品名称", required = true) @RequestParam String drugName) {
        
        try {
            List<PatientPrescriptionVO> result = patientPrescriptionService.searchDrugs(drugName);
            return Result.success(result);
        } catch (Exception e) {
            return Result.error("搜索失败：" + e.getMessage());
        }
    }
    
    /**
     * 新增患者药品处方
     */
    @ApiOperation(value = "新增患者药品处方", notes = "新增患者药品处方信息")
    @PostMapping
    public Result<String> addPatientPrescription(
            @ApiParam(value = "患者药品处方信息", required = true) @RequestBody InpatientDrugs inpatientDrugs) {
        
        try {
            boolean success = patientPrescriptionService.addPatientPrescription(inpatientDrugs);
            if (success) {
                return Result.success("新增成功");
            } else {
                return Result.error("新增失败");
            }
        } catch (Exception e) {
            return Result.error("新增失败：" + e.getMessage());
        }
    }
    
    /**
     * 根据ID查询患者药品处方信息
     */
    @ApiOperation(value = "根据ID查询患者药品处方信息", notes = "根据主键ID查询患者药品处方详细信息")
    @GetMapping("/{id}")
    public Result<PatientPrescriptionVO> getPatientPrescriptionById(
            @ApiParam(value = "主键ID", required = true) @PathVariable Integer id) {
        
        try {
            PatientPrescriptionVO result = patientPrescriptionService.getPatientPrescriptionById(id);
            if (result != null) {
                return Result.success(result);
            } else {
                return Result.error("未找到相关记录");
            }
        } catch (Exception e) {
            return Result.error("查询失败：" + e.getMessage());
        }
    }
    
    /**
     * 更新患者药品处方信息
     */
    @ApiOperation(value = "更新患者药品处方信息", notes = "更新患者药品处方信息")
    @PutMapping("/{id}")
    public Result<String> updatePatientPrescription(
            @ApiParam(value = "主键ID", required = true) @PathVariable Integer id,
            @ApiParam(value = "患者药品处方信息", required = true) @RequestBody InpatientDrugs inpatientDrugs) {
        
        try {
            inpatientDrugs.setId(id);
            boolean success = patientPrescriptionService.updatePatientPrescription(inpatientDrugs);
            if (success) {
                return Result.success("更新成功");
            } else {
                return Result.error("更新失败");
            }
        } catch (Exception e) {
            return Result.error("更新失败：" + e.getMessage());
        }
    }
    
    /**
     * 删除患者药品处方信息
     */
    @ApiOperation(value = "删除患者药品处方信息", notes = "根据主键ID删除患者药品处方信息")
    @DeleteMapping("/{id}")
    public Result<String> deletePatientPrescription(
            @ApiParam(value = "主键ID", required = true) @PathVariable Integer id) {
        
        try {
            boolean success = patientPrescriptionService.deletePatientPrescription(id);
            if (success) {
                return Result.success("删除成功");
            } else {
                return Result.error("删除失败");
            }
        } catch (Exception e) {
            return Result.error("删除失败：" + e.getMessage());
        }
    }
} 