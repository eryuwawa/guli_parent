package com.atguigu.util;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

//统一返回结果的类
@Data
public class GuiguResult {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<>();

    //把构造方法私有
    private GuiguResult() {}

    //成功静态方法
    public static GuiguResult ok() {
        GuiguResult r = new GuiguResult();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    //失败静态方法
    public static GuiguResult error() {
        GuiguResult r = new GuiguResult();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }

    public GuiguResult success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public GuiguResult message(String message){
        this.setMessage(message);
        return this;
    }

    public GuiguResult code(Integer code){
        this.setCode(code);
        return this;
    }

    public GuiguResult data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public GuiguResult data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
