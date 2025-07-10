package com.neusoft.medical.common.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 统一响应结果封装类
 * @author Neusoft
 * @date 2025-07-10
 */
@ApiModel(description = "统一响应结果")
public class Result<T> {

    @ApiModelProperty(value = "响应状态码", example = "200")
    private Integer code;

    @ApiModelProperty(value = "响应消息", example = "success")
    private String message;

    @ApiModelProperty(value = "响应数据")
    private T data;

    // 成功状态码
    public static final Integer SUCCESS_CODE = 200;
    // 失败状态码
    public static final Integer ERROR_CODE = 500;

    public Result() {}

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功响应（无数据）
     */
    public static <T> Result<T> success() {
        return new Result<>(SUCCESS_CODE, "success", null);
    }

    /**
     * 成功响应（带数据）
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(SUCCESS_CODE, "success", data);
    }

    /**
     * 成功响应（自定义消息和数据）
     */
    public static <T> Result<T> success(String message, T data) {
        return new Result<>(SUCCESS_CODE, message, data);
    }

    /**
     * 失败响应（默认消息）
     */
    public static <T> Result<T> error() {
        return new Result<>(ERROR_CODE, "操作失败", null);
    }

    /**
     * 失败响应（自定义消息）
     */
    public static <T> Result<T> error(String message) {
        return new Result<>(ERROR_CODE, message, null);
    }

    /**
     * 失败响应（自定义状态码和消息）
     */
    public static <T> Result<T> error(Integer code, String message) {
        return new Result<>(code, message, null);
    }

    /**
     * 失败响应（自定义状态码、消息和数据）
     */
    public static <T> Result<T> error(Integer code, String message, T data) {
        return new Result<>(code, message, data);
    }

    // Getter and Setter methods
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}