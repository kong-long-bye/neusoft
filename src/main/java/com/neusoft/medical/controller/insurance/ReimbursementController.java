package com.neusoft.medical.controller.insurance;

import com.neusoft.medical.common.result.PageResult;
import com.neusoft.medical.common.result.Result;
import com.neusoft.medical.entity.PatientRegistration;
import com.neusoft.medical.service.insurance.ReimbursementService;
import com.neusoft.medical.vo.insurance.ReimbursementCalculationVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 参保人员保险费用报销控制器
 * @author Neusoft
 * @date 2025-07-10
 */
@Api(tags = "参保人员保险费用报销")
@RestController
@RequestMapping("/api/reimbursement")
public class ReimbursementController {

    @Autowired
    private ReimbursementService reimbursementService;

    /**
     * 参保人员分页查询
     */
    @ApiOperation(value = "参保人员分页查询", notes = "分页查询参保人员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "personName", value = "患者姓名", required = false, dataType = "string", paramType = "query")
    })
    @GetMapping("/persons/page")
    public Result<PageResult<PatientRegistration>> getPersonsPage(
            @RequestParam Integer pageNum,
            @RequestParam Integer pageSize,
            @RequestParam(required = false) String personName) {
        try {
            PageResult<PatientRegistration> result = reimbursementService.getPersonsPage(pageNum, pageSize, personName);
            return Result.success(result);
        } catch (Exception e) {
            return Result.error("查询参保人员失败：" + e.getMessage());
        }
    }

    /**
     * 参保人员搜索
     */
    @ApiOperation(value = "参保人员搜索", notes = "根据姓名搜索参保人员")
    @ApiImplicitParam(name = "personName", value = "患者姓名", required = true, dataType = "string", paramType = "query")
    @GetMapping("/persons/search")
    public Result<List<PatientRegistration>> searchPersons(@RequestParam String personName) {
        try {
            List<PatientRegistration> result = reimbursementService.searchPersons(personName);
            return Result.success(result);
        } catch (Exception e) {
            return Result.error("搜索参保人员失败：" + e.getMessage());
        }
    }

    /**
     * 费用报销计算
     */
    @ApiOperation(value = "费用报销计算", notes = "根据医院等级和费用明细计算报销金额")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "personId", value = "患者ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "hospitalLevel", value = "医院等级（一级/二级/三级）", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "peopleType", value = "人员类别（1-在职 0-退休）", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "费用开始日期(yyyy-MM-dd HH:mm:ss)", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "费用结束日期(yyyy-MM-dd HH:mm:ss)", required = false, dataType = "string", paramType = "query")
    })
    @PostMapping("/persons/{personId}/calculate")
    public Result<ReimbursementCalculationVO> calculateReimbursement(
            @PathVariable Integer personId,
            @RequestParam String hospitalLevel,
            @RequestParam(required = false, defaultValue = "1") String peopleType,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        try {
            ReimbursementCalculationVO result = reimbursementService.calculateReimbursement(
                    personId, hospitalLevel, peopleType, startDate, endDate);
            return Result.success(result);
        } catch (Exception e) {
            return Result.error("费用报销计算失败：" + e.getMessage());
        }
    }

    /**
     * 执行费用报销
     */
    @ApiOperation(value = "执行费用报销", notes = "根据计算结果执行费用报销操作")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "personId", value = "患者ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "hospitalLevel", value = "医院等级", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "peopleType", value = "人员类别（1-在职 0-退休）", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "approver", value = "审批人", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "remark", value = "报销备注", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "费用开始日期(yyyy-MM-dd HH:mm:ss)", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "费用结束日期(yyyy-MM-dd HH:mm:ss)", required = false, dataType = "string", paramType = "query")
    })
    @PostMapping("/persons/{personId}/execute")
    public Result<ReimbursementCalculationVO.ReimbursementRecord> executeReimbursement(
            @PathVariable Integer personId,
            @RequestParam String hospitalLevel,
            @RequestParam(required = false, defaultValue = "1") String peopleType,
            @RequestParam String approver,
            @RequestParam(required = false) String remark,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        try {
            ReimbursementCalculationVO.ReimbursementRecord result = reimbursementService.executeReimbursement(
                    personId, hospitalLevel, peopleType, approver, remark, startDate, endDate);
            return Result.success(result);
        } catch (Exception e) {
            return Result.error("执行费用报销失败：" + e.getMessage());
        }
    }

    /**
     * 报销记录查询
     */
    @ApiOperation(value = "报销记录查询", notes = "查询指定参保人员的历史报销记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "personId", value = "患者ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "startDate", value = "开始日期(yyyy-MM-dd HH:mm:ss)", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "结束日期(yyyy-MM-dd HH:mm:ss)", required = false, dataType = "string", paramType = "query")
    })
    @GetMapping("/persons/{personId}/history")
    public Result<List<ReimbursementCalculationVO.ReimbursementRecord>> getReimbursementHistory(
            @PathVariable Integer personId,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        try {
            List<ReimbursementCalculationVO.ReimbursementRecord> result =
                    reimbursementService.getReimbursementHistory(personId, startDate, endDate);
            return Result.success(result);
        } catch (Exception e) {
            return Result.error("查询报销记录失败：" + e.getMessage());
        }
    }
}