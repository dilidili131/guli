package com.lch.eduservice.controller;

import com.lch.commonUtils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: lch
 * @time: 2022/5/16 22:27
 */
@Api(description = "登录")
@RestController
@CrossOrigin
@RequestMapping("/eduService/user")
public class EduLoginController {

    //login
    @ApiOperation("登录")
    @PostMapping("/login")
    public Result login(){
        //System.out.println("login");
        return Result.ok().data("token","admin");
    }

    //info
    @ApiOperation("获取信息")
    @GetMapping("/info")
    public Result info(){
        //System.out.println("info");
        return Result.ok()
                .data("roles","[admin]")
                .data("name","admin")
                .data("avatar","https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi-1.lanrentuku.com%2F2020%2F11%2F6%2F9232f107-4dac-4006-9c7e-e825df5e52e1.png%3FimageView2%2F2%2Fw%2F500&refer=http%3A%2F%2Fi-1.lanrentuku.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1655303636&t=c115738b13437461b53d798681c28fc4");

    }
}
