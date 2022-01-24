package com.lg.bsp.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName VResponse<T>
 * @Description 统一封装返回值格式
 * @Author jincheng
 * @Date 2022/1/7 9:14
 * @Version 1.0
 **/
@ApiModel(description = "返回数据值")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiFinalResult<T> {

    /**
     * @Description //通用错误码
     **/
    public static final int SUCCESS = 200;
    @ApiModelProperty(value = "返回值编码；200表示成功，其他全部失败")
    private int code = 200;
    @ApiModelProperty(value = "异常提示信息")
    private String msg = "";
    @ApiModelProperty(value = "具体返回值信息")
    private T result;

    public ApiFinalResult(int code, String errMsg) {
        this.code = code;
        this.msg = errMsg;
    }

    public static <T> ApiFinalResult<T> success(T result) {
        return new ApiFinalResult<T>(SUCCESS, null,result);
    }

    public static <T> ApiFinalResult<T> success(String msg, T result) {
        return new ApiFinalResult<T>(SUCCESS, msg,result);
    }

    public static <T> ApiFinalResult<T> success(String msg) {
        return new ApiFinalResult<T>(SUCCESS, msg);
    }

    public static <T> ApiFinalResult<T> success() {
        return new ApiFinalResult<T>(SUCCESS, null);
    }

    public static <T> ApiFinalResult<T> error(int code, String msg) {
        return new ApiFinalResult<T>(code,msg);
    }

    public static <T> ApiFinalResult<T> error(int code, String msg, T object) {
        return new ApiFinalResult<T>(code,msg,object);
    }

}
