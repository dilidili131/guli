package com.lch.oss.controller;

import com.lch.commonUtils.Result;
import com.lch.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description:
 * @author: lch
 * @time: 2022/5/27 14:56
 */
@RestController
@RequestMapping("/eduOss/fileOss")
@CrossOrigin
@Api(description = "oss")
public class OssController {

    @Autowired
    private OssService ossService;

    @ApiOperation("头像上传")
    @PostMapping("/upload")
    public Result uploadOssFile(MultipartFile file){

        String url = ossService.oploadFileAvatar(file);
        return Result.ok().data("url",url);
    }
}
