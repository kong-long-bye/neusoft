package com.neusoft.medical.controller.insurance;

import com.neusoft.medical.common.result.PageResult;
import com.neusoft.medical.common.result.Result;
import com.neusoft.medical.entity.PatientRegistration;
import com.neusoft.medical.service.insurance.PersonExpenseService;
import com.neusoft.medical.vo.insurance.ExpenseDetailVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 参保人员费用查询控制器
 * @author Neusoft
 * @date 2025-07-10
 */
@Api(tags = "参保人员费用查询")
@RestController
@RequestMapping("/api/expense-query")
public class PersonExpenseController {

    @Autowired
    private PersonExpenseService personExpenseService;

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
            PageResult<PatientRegistration> result = personExpenseService.getPersonsPage(pageNum, pageSize, personName);
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
            List<PatientRegistration> result = personExpenseService.searchPersons(personName);
            return Result.success(result);
        } catch (Exception e) {
            return Result.error("搜索参保人员失败：" + e.getMessage());
        }
    }

    /**
     * 参保人员药品费用查询
     */
    @ApiOperation(value = "参保人员药品费用查询", notes = "查询指定参保人员的药品费用详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "personId", value = "患者ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "startDate", value = "开始日期(yyyy-MM-dd HH:mm:ss)", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "结束日期(yyyy-MM-dd HH:mm:ss)", required = false, dataType = "string", paramType = "query")
    })
    @GetMapping("/persons/{personId}/drug-expenses")
    public Result<List<ExpenseDetailVO>> getDrugExpenses(
            @PathVariable Integer personId,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        try {
            List<ExpenseDetailVO> result = personExpenseService.getDrugExpenses(personId, startDate, endDate);
            return Result.success(result);
        } catch (Exception e) {
            return Result.error("查询药品费用失败：" + e.getMessage());
        }
    }

    /**
     * 参保人员诊疗项目费用查询
     */
    @ApiOperation(value = "参保人员诊疗项目费用查询", notes = "查询指定参保人员的诊疗项目费用详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "personId", value = "患者ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "startDate", value = "开始日期(yyyy-MM-dd HH:mm:ss)", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "结束日期(yyyy-MM-dd HH:mm:ss)", required = false, dataType = "string", paramType = "query")
    })
    @GetMapping("/persons/{personId}/treatment-expenses")
    public Result<List<ExpenseDetailVO>> getTreatmentExpenses(
            @PathVariable Integer personId,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        try {
            List<ExpenseDetailVO> result = personExpenseService.getTreatmentExpenses(personId, startDate, endDate);
            return Result.success(result);
        } catch (Exception e) {
            return Result.error("查询诊疗项目费用失败：" + e.getMessage());
        }
    }

    /**
     * 参保人员医疗服务费用查询
     */
    @ApiOperation(value = "参保人员医疗服务费用查询", notes = "查询指定参保人员的医疗服务费用详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "personId", value = "患者ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "startDate", value = "开始日期(yyyy-MM-dd HH:mm:ss)", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "结束日期(yyyy-MM-dd HH:mm:ss)", required = false, dataType = "string", paramType = "query")
    })
    @GetMapping("/persons/{personId}/service-expenses")
    public Result<List<ExpenseDetailVO>> getServiceExpenses(
            @PathVariable Integer personId,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        try {
            List<ExpenseDetailVO> result = personExpenseService.getServiceExpenses(personId, startDate, endDate);
            return Result.success(result);
        } catch (Exception e) {
            return Result.error("查询医疗服务费用失败：" + e.getMessage());
        }
    }
}