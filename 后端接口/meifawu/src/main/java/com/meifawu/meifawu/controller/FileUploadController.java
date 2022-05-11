package com.meifawu.meifawu.controller;

import com.meifawu.meifawu.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Api(tags="文件上传类")
@RestController
@CrossOrigin//跨域解除限制
public class FileUploadController {

    @PostMapping("fileupload")
    //传过来的默认名字为file
    public String fileupload(MultipartFile file){
        System.out.println("file:"+file.getOriginalFilename());
        //将前端文件 保存到本地
        //防止图片重复 UUID.randomUUID().toString()
        String fileName = UUID.randomUUID().toString()+file.getOriginalFilename();
        System.out.println(fileName);
        try {
            file.transferTo(new File("E:/testFileData/", fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }
}
