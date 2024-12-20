package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.AliOssUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws Exception {
        String originalFileName = file.getOriginalFilename();
        String fileName = UUID.randomUUID() + originalFileName.substring(originalFileName.lastIndexOf("."));
//        file.transferTo(new File("E:\\idm\\常规\\图片1\\"+fileName));
        String url = AliOssUtil.uploadFile(fileName,file.getInputStream());
        return Result.success(url);

    }
}
