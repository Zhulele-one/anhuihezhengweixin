package com.yys.anhuihezhengweixin.controller;

import com.yys.anhuihezhengweixin.entity.base.*;
import com.yys.anhuihezhengweixin.response.SimpleResponse;
import com.yys.anhuihezhengweixin.service.EditService;
import com.yys.anhuihezhengweixin.service.FormService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.yys.anhuihezhengweixin.util.UrlUtils.FormFilePath;
import static com.yys.anhuihezhengweixin.util.UrlUtils.otherImgPath;

@RequestMapping("/edit")
@ResponseBody
@Log4j2
@Controller
public class EditController {

    private final EditService editService;


    @Autowired
    public EditController(EditService sideBarService) {
        this.editService = sideBarService;
    }

    @PostMapping("/receiveImg/{id}")
    public SimpleResponse receiveImg(@PathVariable Integer id ,@PathVariable MultipartFile file){
        if (file.isEmpty()) {
            log.info("图片为空");
            return new SimpleResponse("图片为空",SimpleResponse.SUCCESS);
        }
        String fileName = file.getOriginalFilename();

        String imgUrl = otherImgPath + fileName;

        File dest = new File(imgUrl);

        if(!dest.exists()){
            boolean mkdirs = dest.mkdirs();
            if(!mkdirs){
                log.error("创建列表文件夹失败,请手动创建");
            }
        }

        OtherImg otherImgById = editService.findOtherImgByPicId(id);

        if(otherImgById == null){
            otherImgById = new OtherImg();
            otherImgById.setPicId(id);
        }

        otherImgById.setUrl(imgUrl);

        OtherImg otherImg = editService.saveOtherImg(otherImgById);

        if(otherImg == null){
            return new SimpleResponse("保存图片数据失败,请检查数据库",SimpleResponse.ERROR);
        }

        try {
            file.transferTo(dest);
            log.info("保存图片成功");
            return new SimpleResponse("保存图片成功",SimpleResponse.SUCCESS);
        } catch (IOException e) {
            log.info("保存图片失败");
            return new SimpleResponse("保存图片失败,图片为空",SimpleResponse.SUCCESS);
        }
    }


}
