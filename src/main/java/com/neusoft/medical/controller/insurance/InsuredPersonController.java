package com.neusoft.medical.controller.insurance;

import com.neusoft.medical.common.result.PageResult;
import com.neusoft.medical.common.result.Result;
import com.neusoft.medical.service.insurance.InsuredPersonService;
import com.neusoft.medical.vo.insurance.InsuredPersonVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 参保人员保险信息维护控制器
 * @author Neusoft
 * @date 2025-07-12
 */
@Api(tags = "参保人员保险信息维护")
@RestController
@RequestMapping("/api/insurance-management")
public class InsuredPersonController {

    @Autowired
    private InsuredPersonService insuredPersonService;

    /**
     * 参保人员分页查询
     * @param pageNum 当前页码
     * @param pageSize 每页记录数
     * @param personName 人员姓名（可选）
     * @return 分页参保人员列表
     */
    @ApiOperation("参保人员分页查询")
    @GetMapping("/persons/page")
    public Result<PageResult<InsuredPersonVO>> getPersonsPage(
            @ApiParam(value = "当前页码", required = true, example = "1")
            @RequestParam(defaultValue = "1") Integer pageNum,
            @ApiParam(value = "每页记录数", required = true, example = "10")
            @RequestParam(defaultValue = "10") Integer pageSize,
            @ApiParam(value = "人员姓名", required = false)
            @RequestParam(required = false) String personName) {

        PageResult<InsuredPersonVO> pageResult = insuredPersonService.getPersonsPage(pageNum, pageSize, personName);
        return Result.success(pageResult);
    }

    /**
     * 参保人员搜索
     * @param personName 人员姓名
     * @return 匹配的参保人员列表
     */
    @ApiOperation("参保人员搜索")
    @GetMapping("/persons/search")
    public Result<List<InsuredPersonVO>> searchPersons(
            @ApiParam(value = "人员姓名", required = true)
            @RequestParam String personName) {

        List<InsuredPersonVO> persons = insuredPersonService.searchPersons(personName);
        return Result.success(persons);
    }

    /**
     * 新增参保人员
     * @param insuredPersonVO 参保人员信息
     * @return 操作结果
     */
    @ApiOperation("新增参保人员")
    @PostMapping("/persons")
    public Result<String> addPerson(
            @ApiParam(value = "参保人员信息", required = true)
            @RequestBody InsuredPersonVO insuredPersonVO) {

        boolean success = insuredPersonService.addPerson(insuredPersonVO);
        if (success) {
            return Result.success("新增参保人员成功");
        } else {
            return Result.error("新增参保人员失败");
        }
    }

    /**
     * 修改参保人员信息
     * @param personId 人员ID
     * @param insuredPersonVO 参保人员信息
     * @return 操作结果
     */
    @ApiOperation("修改参保人员信息")
    @PutMapping("/persons/{personId}")
    public Result<String> updatePerson(
            @ApiParam(value = "人员ID", required = true)
            @PathVariable Integer personId,
            @ApiParam(value = "参保人员信息", required = true)
            @RequestBody InsuredPersonVO insuredPersonVO) {

        insuredPersonVO.setId(personId);
        boolean success = insuredPersonService.updatePerson(insuredPersonVO);
        if (success) {
            return Result.success("修改参保人员信息成功");
        } else {
            return Result.error("修改参保人员信息失败");
        }
    }
}