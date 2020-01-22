package com.lss.universal.util;



import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "API",description = "返回实体")
public class API<T> {
    @ApiModelProperty(value = "当前请求状态的说明")
    private String  msg ="SUCCESS";
    @ApiModelProperty(value = "指示接口的请求状态")
    private Integer status = 200;
    @ApiModelProperty(value = "返回请求的实体")
    private T  data;
    public  static <T> API<T> ok(T data){
        API<T> api  = new API<>();
        api.setData(data);
        return api;
    }
    public  static <T> API e(Integer status,String msg){
        API<T> api  = new API<>();
        api.setStatus(status);
        api.setMsg(msg);
        return api;
    }

}
