package com.neusoft.medical.controller.medical;

import com.neusoft.medical.common.result.PageResult;
import com.neusoft.medical.common.result.Result;
import com.neusoft.medical.entity.InpatientDiagnosis;
import com.neusoft.medical.service.medical.PatientTreatmentService;
import com.neusoft.medical.vo.medical.PatientTreatmentVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 患者诊疗项目控制器
 * @author Neusoft
 * @date 2025-07-10
 */
@Api(tags = "患者诊疗项目管理")
@RestController
@RequestMapping("/api/patient-treatments")
public class PatientTreatmentController {
    
    @Autowired
    private PatientTreatmentService patientTreatmentService;
    
    /**
     * 患者诊疗项目分页查询
     */
    @ApiOperation(value = "患者诊疗项目分页查询", notes = "分页查询患者诊疗项目信息")
    @GetMapping("/page")
    public Result<PageResult<PatientTreatmentVO>> getPatientTreatmentPage(
            @ApiParam(value = "页码", example = "1") @RequestParam(defaultValue = "1") Integer pageNum,
            @ApiParam(value = "每页大小", example = "10") @RequestParam(defaultValue = "10") Integer pageSize,
            @ApiParam(value = "患者ID") @RequestParam(required = false) Integer patientId,
            @ApiParam(value = "诊疗项目名称") @RequestParam(required = false) String treatmentName) {
        
        try {
            PageResult<PatientTreatmentVO> result = patientTreatmentService.getPatientTreatmentPage(pageNum, pageSize, patientId, treatmentName);
            return Result.success(result);
        } catch (Exception e) {
            return Result.error("查询失败：" + e.getMessage());
        }
    }
    
    /**
     * 诊疗项目搜索
     */
    @ApiOperation(value = "诊疗项目搜索", notes = "根据诊疗项目名称搜索诊疗项目信息")
    @GetMapping("/search")
    public Result<List<PatientTreatmentVO>> searchTreatments(
            @ApiParam(value = "诊疗项目名称", required = true) @RequestParam String treatmentName) {
        
        try {
            List<PatientTreatmentVO> result = patientTreatmentService.searchTreatments(treatmentName);
            return Result.success(result);
        } catch (Exception e) {
            return Result.error("搜索失败：" + e.getMessage());
        }
    }
    
    /**
     * 新增患者诊疗项目
     */
    @ApiOperation(value = "新增患者诊疗项目", notes = "新增患者诊疗项目信息")
    @PostMapping
    public Result<String> addPatientTreatment(
            @ApiParam(value = "患者诊疗项目信息", required = true) @RequestBody InpatientDiagnosis inpatientDiagnosis) {
        
        try {
            boolean success = patientTreatmentService.addPatientTreatment(inpatientDiagnosis);
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
     * 根据ID查询患者诊疗项目信息
     */
    @ApiOperation(value = "根据ID查询患者诊疗项目信息", notes = "根据主键ID查询患者诊疗项目详细信息")
    @GetMapping("/{id}")
    public Result<PatientTreatmentVO> getPatientTreatmentById(
            @ApiParam(value = "主键ID", required = true) @PathVariable Integer id) {
        
        try {
            PatientTreatmentVO result = patientTreatmentService.getPatientTreatmentById(id);
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
     * 更新患者诊疗项目信息
     */
    @ApiOperation(value = "更新患者诊疗项目信息", notes = "更新患者诊疗项目信息")
    @PutMapping("/{id}")
    public Result<String> updatePatientTreatment(
            @ApiParam(value = "主键ID", required = true) @PathVariable Integer id,
            @ApiParam(value = "患者诊疗项目信息", required = true) @RequestBody InpatientDiagnosis inpatientDiagnosis) {
        
        try {
            inpatientDiagnosis.setId(id);
            boolean success = patientTreatmentService.updatePatientTreatment(inpatientDiagnosis);
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
     * 删除患者诊疗项目信息
     */
    @ApiOperation(value = "删除患者诊疗项目信息", notes = "根据主键ID删除患者诊疗项目信息")
    @DeleteMapping("/{id}")
    public Result<String> deletePatientTreatment(
            @ApiParam(value = "主键ID", required = true) @PathVariable Integer id) {
        
        try {
            boolean success = patientTreatmentService.deletePatientTreatment(id);
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