package com.neusoft.medical.controller.insurance;

import com.neusoft.medical.common.result.PageResult;
import com.neusoft.medical.common.result.Result;
import com.neusoft.medical.entity.PatientRegistration;
import com.neusoft.medical.service.insurance.ReimbursementReportService;
import com.neusoft.medical.vo.insurance.ReimbursementReportVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 参保人员保险费用报销详情报表控制器
 * @author Neusoft
 * @date 2025-07-10
 */
@Api(tags = "参保人员保险费用报销详情报表")
@RestController
@RequestMapping("/api/reimbursement-reports")
public class ReimbursementReportController {

    @Autowired
    private ReimbursementReportService reimbursementReportService;

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
            PageResult<PatientRegistration> result = reimbursementReportService.getPersonsPage(pageNum, pageSize, personName);
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
            List<PatientRegistration> result = reimbursementReportService.searchPersons(personName);
            return Result.success(result);
        } catch (Exception e) {
            return Result.error("搜索参保人员失败：" + e.getMessage());
        }
    }

    /**
     * 药品费用分类占比报表
     */
    @ApiOperation(value = "药品费用分类占比报表", notes = "查询指定参保人员的甲乙丙三类药品费用占比数据，用于图表展示")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "personId", value = "患者ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "startDate", value = "开始日期(yyyy-MM-dd HH:mm:ss)", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "结束日期(yyyy-MM-dd HH:mm:ss)", required = false, dataType = "string", paramType = "query")
    })
    @GetMapping("/persons/{personId}/drug-category-chart")
    public Result<List<ReimbursementReportVO.CategoryRatioData>> getDrugCategoryChart(
            @PathVariable Integer personId,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        try {
            List<ReimbursementReportVO.CategoryRatioData> result =
                    reimbursementReportService.getDrugCategoryChart(personId, startDate, endDate);
            return Result.success(result);
        } catch (Exception e) {
            return Result.error("查询药品费用分类占比失败：" + e.getMessage());
        }
    }

    /**
     * 费用类型占比报表
     */
    @ApiOperation(value = "费用类型占比报表", notes = "查询指定参保人员的药品费用、诊疗项目费用、医疗服务费用三类占比数据，用于图表展示")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "personId", value = "患者ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "startDate", value = "开始日期(yyyy-MM-dd HH:mm:ss)", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "结束日期(yyyy-MM-dd HH:mm:ss)", required = false, dataType = "string", paramType = "query")
    })
    @GetMapping("/persons/{personId}/expense-type-chart")
    public Result<List<ReimbursementReportVO.CategoryRatioData>> getExpenseTypeChart(
            @PathVariable Integer personId,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        try {
            List<ReimbursementReportVO.CategoryRatioData> result =
                    reimbursementReportService.getExpenseTypeChart(personId, startDate, endDate);
            return Result.success(result);
        } catch (Exception e) {
            return Result.error("查询费用类型占比失败：" + e.getMessage());
        }
    }

    /**
     * 综合费用报销详情报表
     */
    @ApiOperation(value = "综合费用报销详情报表", notes = "查询指定参保人员的包含所有费用分类、占比、明细的综合报表数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "personId", value = "患者ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "startDate", value = "开始日期(yyyy-MM-dd HH:mm:ss)", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "结束日期(yyyy-MM-dd HH:mm:ss)", required = false, dataType = "string", paramType = "query")
    })
    @GetMapping("/persons/{personId}/comprehensive-report")
    public Result<ReimbursementReportVO> getComprehensiveReport(
            @PathVariable Integer personId,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        try {
            ReimbursementReportVO result =
                    reimbursementReportService.getComprehensiveReport(personId, startDate, endDate);
            return Result.success(result);
        } catch (Exception e) {
            return Result.error("查询综合费用报销详情失败：" + e.getMessage());
        }
    }
}