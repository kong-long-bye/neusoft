package com.neusoft.medical.vo.basicinfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;

/**
 * 医院报销比例数据传输对象
 * @author Neusoft
 * @date 2025-07-10
 */
@ApiModel(description = "医院报销比例传输对象")
public class HospitalReimbursementVO {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "报销起付金额", required = true, example = "1000")
    private String minPayLevel;

    @ApiModelProperty(value = "报销等级线", required = true, example = "5000")
    private String maxPayLevel;

    @ApiModelProperty(value = "人员类别(1 在职人员，0 退休人员)", required = true, example = "1")
    private String peopleType;

    @ApiModelProperty(value = "报销比例", required = true, example = "85")
    private String payProportion;

    @ApiModelProperty(value = "医院等级", required = true, example = "三级")
    private String hospitalLevel;

    @ApiModelProperty(value = "状态：1-正常 0-禁用", example = "1")
    private String status;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    // 构造方法
    public HospitalReimbursementVO() {}

    // Getter and Setter methods
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getMinPayLevel() { return minPayLevel; }
    public void setMinPayLevel(String minPayLevel) { this.minPayLevel = minPayLevel; }

    public String getMaxPayLevel() { return maxPayLevel; }
    public void setMaxPayLevel(String maxPayLevel) { this.maxPayLevel = maxPayLevel; }

    public String getPeopleType() { return peopleType; }
    public void setPeopleType(String peopleType) { this.peopleType = peopleType; }

    public String getPayProportion() { return payProportion; }
    public void setPayProportion(String payProportion) { this.payProportion = payProportion; }

    public String getHospitalLevel() { return hospitalLevel; }
    public void setHospitalLevel(String hospitalLevel) { this.hospitalLevel = hospitalLevel; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedTime() { return createdTime; }
    public void setCreatedTime(LocalDateTime createdTime) { this.createdTime = createdTime; }

    public LocalDateTime getUpdatedTime() { return updatedTime; }
    public void setUpdatedTime(LocalDateTime updatedTime) { this.updatedTime = updatedTime; }
}
