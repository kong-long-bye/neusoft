package com.neusoft.medical.controller.medical;

import com.neusoft.medical.common.result.PageResult;
import com.neusoft.medical.common.result.Result;
import com.neusoft.medical.entity.InpatientMedical;
import com.neusoft.medical.service.medical.PatientMedicalServiceService;
import com.neusoft.medical.vo.medical.PatientMedicalServiceVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 患者医疗服务控制器
 * @author Neusoft
 * @date 2025-07-10
 */
@Api(tags = "患者医疗服务管理")
@RestController
@RequestMapping("/api/patient-medical-services")
public class PatientMedicalServiceController {
    
    @Autowired
    private PatientMedicalServiceService patientMedicalServiceService;
    
    /**
     * 患者医疗服务分页查询
     */
    @ApiOperation(value = "患者医疗服务分页查询", notes = "分页查询患者医疗服务信息")
    @GetMapping("/page")
    public Result<PageResult<PatientMedicalServiceVO>> getPatientMedicalServicePage(
            @ApiParam(value = "页码", example = "1") @RequestParam(defaultValue = "1") Integer pageNum,
            @ApiParam(value = "每页大小", example = "10") @RequestParam(defaultValue = "10") Integer pageSize,
            @ApiParam(value = "患者ID") @RequestParam(required = false) Integer patientId,
            @ApiParam(value = "医疗服务名称") @RequestParam(required = false) String serviceName) {
        
        try {
            PageResult<PatientMedicalServiceVO> result = patientMedicalServiceService.getPatientMedicalServicePage(pageNum, pageSize, patientId, serviceName);
            return Result.success(result);
        } catch (Exception e) {
            return Result.error("查询失败：" + e.getMessage());
        }
    }
    
    /**
     * 医疗服务搜索
     */
    @ApiOperation(value = "医疗服务搜索", notes = "根据医疗服务名称搜索医疗服务信息")
    @GetMapping("/search")
    public Result<List<PatientMedicalServiceVO>> searchMedicalServices(
            @ApiParam(value = "医疗服务名称", required = true) @RequestParam String serviceName) {
        
        try {
            List<PatientMedicalServiceVO> result = patientMedicalServiceService.searchMedicalServices(serviceName);
            return Result.success(result);
        } catch (Exception e) {
            return Result.error("搜索失败：" + e.getMessage());
        }
    }
    
    /**
     * 新增患者医疗服务
     */
    @ApiOperation(value = "新增患者医疗服务", notes = "新增患者医疗服务信息")
    @PostMapping
    public Result<String> addPatientMedicalService(
            @ApiParam(value = "患者医疗服务信息", required = true) @RequestBody InpatientMedical inpatientMedical) {
        
        try {
            boolean success = patientMedicalServiceService.addPatientMedicalService(inpatientMedical);
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
     * 根据ID查询患者医疗服务信息
     */
    @ApiOperation(value = "根据ID查询患者医疗服务信息", notes = "根据主键ID查询患者医疗服务详细信息")
    @GetMapping("/{id}")
    public Result<PatientMedicalServiceVO> getPatientMedicalServiceById(
            @ApiParam(value = "主键ID", required = true) @PathVariable Integer id) {
        
        try {
            PatientMedicalServiceVO result = patientMedicalServiceService.getPatientMedicalServiceById(id);
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
     * 更新患者医疗服务信息
     */
    @ApiOperation(value = "更新患者医疗服务信息", notes = "更新患者医疗服务信息")
    @PutMapping("/{id}")
    public Result<String> updatePatientMedicalService(
            @ApiParam(value = "主键ID", required = true) @PathVariable Integer id,
            @ApiParam(value = "患者医疗服务信息", required = true) @RequestBody InpatientMedical inpatientMedical) {
        
        try {
            inpatientMedical.setId(id);
            boolean success = patientMedicalServiceService.updatePatientMedicalService(inpatientMedical);
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
     * 删除患者医疗服务信息
     */
    @ApiOperation(value = "删除患者医疗服务信息", notes = "根据主键ID删除患者医疗服务信息")
    @DeleteMapping("/{id}")
    public Result<String> deletePatientMedicalService(
            @ApiParam(value = "主键ID", required = true) @PathVariable Integer id) {
        
        try {
            boolean success = patientMedicalServiceService.deletePatientMedicalService(id);
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