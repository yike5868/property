package com.zl.property.controller;

import com.alibaba.fastjson.JSON;
import com.zl.property.model.dto.ResultDto;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping(value = "/file", produces = "text/plain;charset=UTF-8")
public class FileController {
    static org.slf4j.Logger logger = LoggerFactory.getLogger(FileController.class);

    /**
     * 在配置文件中配置的文件保存路径
     */
    @Value("${img.savedir}")
    private String savedir;
    @Value("${img.viewdir}")
    private String viewdir;

    @Value("${img.url}")
    private String imgUrl;


    private final ResourceLoader resourceLoader;

    @Autowired
    public FileController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


    //上传的方法
    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes, HttpServletRequest request) {

        Path path = null;
        String fileUrl = null;
        if (!file.isEmpty()) {

            try {
                //重命名文件
                String fileName = UUID.randomUUID()+"."+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
                fileUrl = viewdir+"/"+fileName;
                path = Paths.get(savedir, fileName);
                logger.info("上传图片,地址{}", path.toString());

                Files.copy(file.getInputStream(), path);
                redirectAttributes.addFlashAttribute("message",
                        "You successfully uploaded " + file.getOriginalFilename() + "!");
            } catch (IOException | RuntimeException e) {
                redirectAttributes.addFlashAttribute("message", "Failued to upload " + file.getOriginalFilename() + " => " + e.getMessage());
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "Failed to upload " + file.getOriginalFilename() + " because it was empty");
        }

        ResultDto resultDto = new ResultDto();

        if (fileUrl != null) {
            String resultPath = imgUrl + fileUrl;
            resultDto.setData(resultPath);
        } else {
            resultDto.setMessage("图片上传失败！");
            resultDto.setHasSuccess(true);
            resultDto.setSuccess(false);
        }

        return JSON.toJSONString(resultDto);
    }

    @RequestMapping("/getimgs")
    public void getimg(String address, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String local = "/Users/zhanglin03/Desktop/property/property/upload-dir";
            FileInputStream hFile = new FileInputStream(local + address);
            int i = hFile.available();
            byte data[] = new byte[i];
            hFile.read(data);
            hFile.close();
            response.setContentType("image/*");
            OutputStream toClient = response.getOutputStream();
            toClient.write(data);
            toClient.close();
        } catch (IOException e) {
            PrintWriter toClient = response.getWriter();
            response.setContentType("text/html;charset=gb2312");
            toClient.write("无法打开图片");
            toClient.close();
        }


    }
}
