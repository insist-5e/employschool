package com.mydesign.employschool.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
    private boolean code; // 结果
    private String message; // 提示信息
    private Object data; // 返回数据
    public static Result success(Object data){
        return new Result(true, "success", data);
    }
    public static Result fail(Object data){
        return new Result(false, "failed", data);
    }
}
