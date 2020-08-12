package com.yys.anhuihezhengweixin.controller;

import com.yys.anhuihezhengweixin.entity.base.OtherImg;
import com.yys.anhuihezhengweixin.service.EditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ResourceController {

    private final EditService editService;


    @Autowired
    public ResourceController(EditService editService) {
        this.editService = editService;
    }

    @GetMapping("/getPicResource")
    public String getPic(String path, HttpServletResponse response) throws IOException {
        if(path == null){
            return null;
        }
        path = path.substring(1,path.length() - 1);
        File file = new File(path);

        if(file.exists()){
            FileInputStream inputStream = new FileInputStream(path);
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] buf = new byte[1024];
            int len;
            while( (len = inputStream.read(buf))!=-1){
                outputStream.write(buf,0,len);
            }
            inputStream.close();
            outputStream.flush();
            outputStream.close();
        }
        return null;
    }


    @GetMapping("/getIndexPicResource/{picId}")
    public String getIndexPicResource(@PathVariable("picId") Integer picId, HttpServletResponse response) throws IOException {

        if(picId == null){
            return null;
        }

        OtherImg otherImg = editService.findOtherImgByPicId(picId);

        String path = otherImg.getUrl();

        if(path == null){
            return null;
        }



        File file = new File(path);

        if(file.exists()){
            FileInputStream inputStream = new FileInputStream(path);

            ServletOutputStream outputStream = response.getOutputStream();
            byte[] buf = new byte[1024];
            int len;
            while( (len = inputStream.read(buf))!=-1){
                outputStream.write(buf,0,len);
            }
            inputStream.close();
            outputStream.flush();
            outputStream.close();
        }
        return null;
    }


    @GetMapping("/getDocResource")
    public String getDocResource(String path, HttpServletResponse response) throws IOException {
        if(path == null){
            return null;
        }
        path = path.substring(1,path.length() - 1);
        File file = new File(path);

        if(file.exists()){
            response.setContentType("application/msword");
            FileInputStream inputStream = new FileInputStream(path);
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] buf = new byte[1024];
            int len;
            while( (len = inputStream.read(buf))!=-1){
                outputStream.write(buf,0,len);
            }
            inputStream.close();
            outputStream.flush();
            outputStream.close();
        }
        return null;
    }



}
