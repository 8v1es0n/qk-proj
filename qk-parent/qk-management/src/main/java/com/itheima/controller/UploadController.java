package com.itheima.controller;

import com.itheima.common.Result;
import com.itheima.common.utils.OssFileUpload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@RestController
public class UploadController {
    @Autowired
    private OssFileUpload ossFileUpload;


    /**
     * 文件上传
     * @param image 图片文件
     * @return  返回结果
     * @throws IOException  处理IO流异常
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传开始：image {}", image.getOriginalFilename());
        String url = ossFileUpload.upload(Objects.requireNonNull(image.getOriginalFilename()), image.getInputStream());
        log.info("文件上传完成：url {}", url);
        return Result.success(url);
    }
}
