package com.lch.serviceBase.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: lch
 * @time: 2022/5/13 20:48
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuLiException extends RuntimeException{

    private Integer code;//状态码
    private String msg;//异常信息

}
