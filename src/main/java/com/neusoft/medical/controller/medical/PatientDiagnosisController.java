package com.neusoft.medical.controller.medical;

import com.neusoft.medical.common.result.PageResult;
import com.neusoft.medical.common.result.Result;
import com.neusoft.medical.entity.InpatientDisease;
import com.neusoft.medical.service.medical.PatientDiagnosisService;
import com.neusoft.medical.vo.medical.PatientDiagnosisVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 患者疾病诊断控制器
 * @author Neusoft
 * @date 2025-07-10
 */
@Api(tags = "患者疾病诊断管理")
@RestController
@RequestMapping("/api/patient-diagnoses")
public class PatientDiagnosisController {
    
    @Autowired
    private PatientDiagnosisService patientDiagnosisService;
    
    /**
     * 疾病信息分页查询
     */
    @ApiOperation(value = "疾病信息分页查询", notes = "分页查询患者疾病诊断信息")
    @GetMapping("/page")
    public Result<PageResult<PatientDiagnosisVO>> getPatientDiagnosisPage(
            @ApiParam(value = "页码", example = "1") @RequestParam(defaultValue = "1") Integer pageNum,
            @ApiParam(value = "每页大小", example = "10") @RequestParam(defaultValue = "10") Integer pageSize,
            @ApiParam(value = "患者ID") @RequestParam(required = false) Integer patientId,
            @ApiParam(value = "疾病名称") @RequestParam(required = false) String diseaseName) {
        
        try {
            PageResult<PatientDiagnosisVO> result = patientDiagnosisService.getPatientDiagnosisPage(pageNum, pageSize, patientId, diseaseName);
            return Result.success(result);
        } catch (Exception e) {
            return Result.error("查询失败：" + e.getMessage());
        }
    }
    
    /**
     * 疾病信息搜索
     */
    @ApiOperation(value = "疾病信息搜索", notes = "根据疾病名称搜索疾病信息")
    @GetMapping("/search")
    public Result<List<PatientDiagnosisVO>> searchDiseases(
            @ApiParam(value = "疾病名称", required = true) @RequestParam String diseaseName) {
        
        try {
            List<PatientDiagnosisVO> result = patientDiagnosisService.searchDiseases(diseaseName);
            return Result.success(result);
        } catch (Exception e) {
            return Result.error("搜索失败：" + e.getMessage());
        }
    }
    
    /**
     * 新增患者疾病诊断
     */
    @ApiOperation(value = "新增患者疾病诊断", notes = "新增患者疾病诊断信息")
    @PostMapping
    public Result<String> addPatientDiagnosis(
            @ApiParam(value = "患者疾病诊断信息", required = true) @RequestBody InpatientDisease inpatientDisease) {
        
        try {
            boolean success = patientDiagnosisService.addPatientDiagnosis(inpatientDisease);
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
     * 根据ID查询患者疾病诊断信息
     */
    @ApiOperation(value = "根据ID查询患者疾病诊断信息", notes = "根据主键ID查询患者疾病诊断详细信息")
    @GetMapping("/{id}")
    public Result<PatientDiagnosisVO> getPatientDiagnosisById(
            @ApiParam(value = "主键ID", required = true) @PathVariable Integer id) {
        
        try {
            PatientDiagnosisVO result = patientDiagnosisService.getPatientDiagnosisById(id);
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
     * 更新患者疾病诊断信息
     */
    @ApiOperation(value = "更新患者疾病诊断信息", notes = "更新患者疾病诊断信息")
    @PutMapping("/{id}")
    public Result<String> updatePatientDiagnosis(
            @ApiParam(value = "主键ID", required = true) @PathVariable Integer id,
            @ApiParam(value = "患者疾病诊断信息", required = true) @RequestBody InpatientDisease inpatientDisease) {
        
        try {
            inpatientDisease.setId(id);
            boolean success = patientDiagnosisService.updatePatientDiagnosis(inpatientDisease);
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
     * 删除患者疾病诊断信息
     */
    @ApiOperation(value = "删除患者疾病诊断信息", notes = "根据主键ID删除患者疾病诊断信息")
    @DeleteMapping("/{id}")
    public Result<String> deletePatientDiagnosis(
            @ApiParam(value = "主键ID", required = true) @PathVariable Integer id) {
        
        try {
            boolean success = patientDiagnosisService.deletePatientDiagnosis(id);
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