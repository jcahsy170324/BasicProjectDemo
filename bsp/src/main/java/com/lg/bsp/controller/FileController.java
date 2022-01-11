package com.lg.bsp.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName FileController
 * @Description 文件上传下载
 * @Author jincheng
 * @Date 2022/1/7 14:37
 * @Version 1.0
 **/
@RestController
@RequestMapping("/file")
public class FileController {
    /**
     * @Author jincheng
     * @Description //文件存储位置/
     * */
    private final static String FILESERVER = "http://172.16.52.44:8080/upload/";
    @PostMapping("/upload")
    public Map<String,String> upload(MultipartFile headPhoto, HttpServletRequest req) throws IOException {
        Map<String,String> map=new HashMap<>();
        // 指定文件存储目录为我们项目部署环境下的upload目录
        String realPath = req.getServletContext().getRealPath("/upload");
        File dir = new File(realPath);
        // 如果不存在则创建目录
        if(!dir.exists()){
            dir.mkdirs();
        }
        // 获取文件名
        String originalFilename = headPhoto.getOriginalFilename();
        // 避免文件名冲突,使用UUID替换文件名
        String uuid = UUID.randomUUID().toString();
        // 获取拓展名
        String extendsName = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 新的文件名
        String newFileName=uuid.concat(extendsName);
        // 创建 sun公司提供的jersey包中的client对象
        Client client=Client.create();
        WebResource resource = client.resource(FILESERVER + newFileName);
        //  文件保存到另一个服务器上去了
        resource.put(String.class, headPhoto.getBytes());
        // 上传成功之后,把文件的名字和文件的类型返回给浏览器
        map.put("message", "上传成功");
        map.put("newFileName", FILESERVER+newFileName);
        map.put("filetype", headPhoto.getContentType());
        return map;
    }

    @GetMapping("/download")
    public void fildDownLoad(String photo, String fileType, HttpServletResponse response) throws IOException {
        // 设置响应头
        // 告诉浏览器要将数据保存到磁盘上,不在浏览器上直接解析
        response.setHeader("Content-Disposition", "attachment;filename="+photo);
        // 告诉浏览下载的文件类型
        response.setContentType(fileType);
        // 获取一个文件的输入流
        InputStream inputStream = new URL(FILESERVER + photo).openStream();
        // 获取一个指向浏览器的输出流
        ServletOutputStream outputStream = response.getOutputStream();
        // 向浏览器响应文件即可
        IOUtils.copy(inputStream, outputStream);
    }
}
