package com.zl.property.controller;

import com.zl.property.service.imp.UserServiceImp;
import com.zl.property.utils.JsonObjectUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/file",produces="text/plain;charset=UTF-8")
public class FileController {
    static org.slf4j.Logger logger = LoggerFactory.getLogger(FileController.class);

    /**
     * 在配置文件中配置的文件保存路径
     */
    @Value("${img.location}")
    private String location;

    public static final String ROOT = "upload-dir";

    private final ResourceLoader resourceLoader;

    @Autowired
    public FileController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String provideUploadInfo(Model model) throws IOException {

        model.addAttribute("files", Files.walk(Paths
                .get(ROOT))
                .filter(path -> !path.equals(Paths.get(ROOT)))
                .map(path -> Paths.get(ROOT).relativize(path))
                .map(path -> linkTo(methodOn(FileController.class).getFile(path.toString())).withRel(path.toString()))
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    //显示图片的方法关键 匹配路径像 localhost:8080/b7c76eb3-5a67-4d41-ae5c-1642af3f8746.png
    @RequestMapping(method = RequestMethod.GET, value = "/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable String filename) {

        try {
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(ROOT, filename).toString()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //上传的方法
    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes, HttpServletRequest request) {
//        System.out.println(request.getParameter("member"));
        if (!file.isEmpty()) {

            try {
                Path path = Paths.get(ROOT, file.getOriginalFilename());
                logger.info("上传图片,地址{}",path.toString());

                Files.copy(file.getInputStream(), path);
                redirectAttributes.addFlashAttribute("message",
                        "You successfully uploaded " + file.getOriginalFilename() + "!");
            } catch (IOException|RuntimeException e) {
                redirectAttributes.addFlashAttribute("message", "Failued to upload " + file.getOriginalFilename() + " => " + e.getMessage());
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "Failed to upload " + file.getOriginalFilename() + " because it was empty");
        }

        return "redirect:/";
    }


//    @RequestMapping("/save")
//    public String save(@RequestParam(value = "file") MultipartFile file, userInfo uf, RedirectAttributes attributes, HttpServletRequest request) {
//        if (uf != null) {
//            if (!file.isEmpty()) {
//                String fileName = UUID.randomUUID().toString().replaceAll("-", "") + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
//                String filePath ="/Users/thomas-wu/upload/" + fileName;
//                System.out.println(filePath);
//                try {
//                    file.transferTo(new File(filePath));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                uf.setHead_href("getimgs?address="+filePath);
//            }
//
//        }
//        String result = userService.saveUser(uf);
//        JSONObject jb = JSONObject.fromObject(result);
//        if (jb.getInt("status") == 1 && "信息保存成功".equals(jb.getString("msg"))) {
//            attributes.addFlashAttribute("error", "信息保存成功");
//            return "redirect:/user";
//        }
//        attributes.addFlashAttribute("error", "信息保存失败");
//        return "redirect:/edit";
//    }

    @RequestMapping("/getimgs")
    public void getimg(String address, HttpServletRequest request, HttpServletResponse response) throws IOException{
        try {
            String local = "/Users/zhanglin/Documents/property/property";
            FileInputStream hFile=new FileInputStream(local+address);
            int i=hFile.available();
            byte data[]=new byte[i];
            hFile.read(data);
            hFile.close();
            response.setContentType("image/*");
            OutputStream toClient=response.getOutputStream();
            toClient.write(data);
            toClient.close();
        }catch (IOException e){
            PrintWriter toClient=response.getWriter();
            response.setContentType("text/html;charset=gb2312");
            toClient.write("无法打开图片");
            toClient.close();
        }


    }
}
