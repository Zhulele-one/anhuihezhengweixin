package com.yys.anhuihezhengweixin.controller;

import com.yys.anhuihezhengweixin.entity.base.HTMLEntity;
import com.yys.anhuihezhengweixin.entity.base.Sidebar;
import com.yys.anhuihezhengweixin.entity.base.TextEntity;
import com.yys.anhuihezhengweixin.entity.content.BaseText;
import com.yys.anhuihezhengweixin.entity.content.ImgText;
import com.yys.anhuihezhengweixin.response.SimpleResponse;
import com.yys.anhuihezhengweixin.service.FormService;
import com.yys.anhuihezhengweixin.service.PageService;
import com.yys.anhuihezhengweixin.service.TextService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.yys.anhuihezhengweixin.util.HTMLUtils.completeStringData;
import static com.yys.anhuihezhengweixin.util.HtmlTag.*;
import static com.yys.anhuihezhengweixin.util.UrlUtils.getImgTextUrl;
import static com.yys.anhuihezhengweixin.util.UrlUtils.itemFilePath;

@Log4j2
@Controller
@RequestMapping("/__preview__/")
public class PreViewController {

    private HTMLEntity htmlEntity;
    private List<TextEntity> textEntities;
    private List<Sidebar> sidebars;
    private String content;
    private Integer imgTextId;

    private final PageService pageService;

    private final TextService textService;

    private final FormService formService;


    @Autowired
    public PreViewController(PageService pageService, TextService textService, FormService formService) {
        this.pageService = pageService;
        this.textService = textService;
        this.formService = formService;
    }


    @ModelAttribute
    public void init(){
        if(textEntities == null){
            if(sidebars == null){
                sidebars = pageService.initSidebar(PREVIEWID);
            }
            textEntities = pageService.initPageText(PREVIEWID,sidebars);
        }
        if(htmlEntity == null){
            htmlEntity = pageService.initPage(PREVIEWID);
        }

    }




    //////新建页面方法

    @GetMapping("/addPage/{textEntityId}")
    public ModelAndView addPage(@PathVariable Long textEntityId){
        ModelAndView mv = completeStringData(htmlEntity,textEntities,PREVIEWTEMPLATE,BIREFIRETEXTENTITY,
                "<h1>你可以在此区域填写内容,加入图片直接拖拽即可</h1>");

        TextEntity textEntityById = pageService.getTextEntityById(textEntityId);

        mv.addObject("textEntity",textEntityById);

        return mv;
    }




    ////编辑页面方法




    @GetMapping("/editPage/{id}/{isHaveParent}")
    public ModelAndView editPageById(@PathVariable("id") Long id,@PathVariable("isHaveParent") int isHaveParent){

        BaseText baseText = textService.getBaseTextById(id);

        int textEntityId = baseText.getTextEntityId();

        ImgText imgText = pageService.getImgTextByTextId(Math.toIntExact(id));

        TextEntity textEntity = pageService.getTextEntityBySideId(textEntityId);


        ModelAndView mv =  completeStringData(htmlEntity,textEntities,PREVIEWTEMPLATE,EDITTEXTENTITY,baseText.getContent());


//        mv.addObject("textEntityList",pageService.getTextEntityList());
//        mv.addObject("textEntityId",textEntityId);
        mv.addObject("imgText",imgText);
        mv.addObject("textEntity",textEntity);
//        mv.addObject("parentId",baseText.getTextEntityId());
        mv.addObject("textId",id);
        mv.addObject("isHaveParent",isHaveParent);


        return mv;
    }

    //预览页面方法

    @PostMapping("/previewPage")
    @ResponseBody
    public SimpleResponse previewPage(String content){
        if(content == null){
            return new SimpleResponse("生成失败",SimpleResponse.ERROR);
        }
        this.content = content;
        return new SimpleResponse("/preview/previewPage0",SimpleResponse.SUCCESS);
    }

    ///显示预览页面

    @GetMapping("/previewPage0")
    public ModelAndView previewPage0(){
        BaseText baseText = new BaseText();
        baseText.setContent(content);
        baseText.setId(0L);
        return  completeStringData(htmlEntity,textEntities,TEXTTEMPLATE,PREVIEWTEXTENTITY,baseText);
    }

    @GetMapping("/previewPage0/{entityId}")
    public ModelAndView previewPage1(@PathVariable("entityId") Integer id){

        ModelAndView mv = new ModelAndView(TEXTTEMPLATE);

        TextEntity textEntityById = pageService.getTextEntityBySideId(id);
        Integer groupId = textEntityById.getGroupId();
        textEntityById.setTitle("预览页面");

        List<Sidebar> sidebars = pageService.initSidebar(groupId);
        textEntityById.setSidebars(sidebars);

        HTMLEntity htmlEntityById = pageService.getHTMLEntityById(Long.valueOf(groupId));

        htmlEntityById.setTextEntity(textEntityById);


        BaseText baseText = new BaseText();
        baseText.setContent(content);
        baseText.setId(0L);

        textEntityById.setContent(baseText);

        mv.addObject(HTML,htmlEntityById);
        return mv;
    }

    @Transactional
    @GetMapping("/deletePage/{id}")
    @ResponseBody
    public SimpleResponse deletePage(@PathVariable("id") Long id){
        ImgText imgTextById = pageService.getImgTextById(Math.toIntExact(id));

        if(imgTextById == null){
            return new SimpleResponse("删除失败,此列表不存在",SimpleResponse.ERROR);
        }

        Integer textId = imgTextById.getTextId();

        BaseText baseTextById = textService.getBaseTextById(Long.valueOf(textId));

        if(baseTextById != null){
            boolean isDeleteText = textService.deleteBasePage(Long.valueOf(textId));
            if(isDeleteText){
                log.info("删除BasePage成功:" + baseTextById);
                Boolean isDeleteImgText = pageService.deleteImgPage(id);
                if(isDeleteImgText){
                    log.info("删除ImgTexe成功:" + imgTextById);
                } else {
                    throw new RuntimeException("删除列表失败" + imgTextById);
                }
            } else {
                log.info("删除BasePage失败");
                throw new RuntimeException("删除basePage失败:" + baseTextById);
            }
        } else {
            Boolean isDeleteImgText = pageService.deleteImgPage(id);
            if(isDeleteImgText){
                log.info("删除列表成功:" + imgTextById);
            } else {
                throw new RuntimeException("删除列表失败" + imgTextById);
            }

        }
        return new SimpleResponse("删除成功!",SimpleResponse.SUCCESS);
    }


    @Transactional
    @GetMapping("/deleteStringPage/{id}")
    @ResponseBody
    public SimpleResponse deleteStringPage(@PathVariable("id") Long id){


        BaseText baseTextById = textService.getBaseTextById(id);

        if(baseTextById != null){
            boolean isDeleteText = textService.deleteBasePage(baseTextById.getId());
            if(isDeleteText){
                log.info("删除BasePage成功:" + baseTextById);
            } else {
                log.info("删除BasePage失败");
                throw new RuntimeException("删除basePage失败:" + baseTextById);
            }
        } else {
           throw new RuntimeException("此文章不存在");
        }
        return new SimpleResponse("删除成功!",SimpleResponse.SUCCESS);
    }


    @Transactional
    @GetMapping("/deleteRecruit/{id}")
    @ResponseBody
    public SimpleResponse deleteRecruit(@PathVariable("id") Long id){

        ImgText imgTextById = pageService.getImgTextById(Math.toIntExact(id));

        if(imgTextById == null){
            return new SimpleResponse("删除失败此列表不存在",SimpleResponse.ERROR);
        }

        Integer textId = imgTextById.getTextId();

        SimpleResponse simpleResponse = this.deletePage(id);

        if(!simpleResponse.getCode()){
            return simpleResponse;
        }

        Boolean isDeleteJob = formService.deleteJobByJobId(textId);
        if(isDeleteJob){
            log.info("删除岗位成功");
        } else {
            throw new RuntimeException("删除失败,此岗位不存在");
        }

        return new SimpleResponse("删除成功!",SimpleResponse.SUCCESS);
    }




    @PostMapping("/savePage")
    @ResponseBody
    @Transactional
    public SimpleResponse savePage(BaseText baseText,Long parentId,String parentTitle,String parentAbstract,String imgUrl){

        if(baseText == null){
            return new SimpleResponse("保存失败,请指定文章放置的位置",SimpleResponse.ERROR);
        }
        if(content == null){
            return new SimpleResponse("保存失败,请先生成页面内容",SimpleResponse.ERROR);
        }


        BaseText baseTextById = textService.getBaseTextById(baseText.getId());

        if(baseTextById.getId() != 0){
            baseText = baseTextById;
        } else {
            baseText.setId(null);
        }


        baseText.setContent(content);

        System.out.println(baseText);

        BaseText baseTextNew = textService.savePage(baseText);

        if(baseTextNew == null){
            return new SimpleResponse("保存失败,保存出错,请检查数据库",SimpleResponse.ERROR);
        }

        ImgText imgText ;

        if(parentId == null || parentId == 0){
            imgText = new ImgText();
        } else {
            imgText = pageService.getImgTextById(Math.toIntExact(parentId));
            if(imgText == null){
                imgText = new ImgText();
            }
        }

        if(!"".equals(imgUrl)){
            imgText.setImgUrl(imgUrl);
        }

        imgText.setName(parentTitle);
        imgText.setTextEntityId(baseTextNew.getTextEntityId());
        imgText.setAbstractText(parentAbstract);
        imgText.setTextId(Math.toIntExact(baseTextNew.getId()));

        imgText.setUrl(getImgTextUrl(baseText.getTextEntityId()));

        ImgText imgTextNew = pageService.saveImgText(imgText);

        if(imgTextNew == null){
            imgTextId = 0;
            return new SimpleResponse("保存预览列表失败请重新保存",SimpleResponse.ERROR);
        }

        imgTextId = Math.toIntExact(imgTextNew.getId());

        return new SimpleResponse("保存成功",SimpleResponse.SUCCESS);
    }


    @PostMapping("/receiveItemImg")
    @ResponseBody
    public SimpleResponse receiveItemImg(@RequestParam("imgFile") MultipartFile imgFile) {
        if (imgFile.isEmpty()) {
            log.info("图片为空,将不在列表页面使用图片");
            return new SimpleResponse("图片为空,将不在列表页面使用图片",SimpleResponse.SUCCESS);
        }
        String fileName = imgFile.getOriginalFilename();

        String imgUrl = itemFilePath + fileName;

        File dest = new File(imgUrl);

        if(!dest.exists()){
            boolean mkdirs = dest.mkdirs();
            if(!mkdirs){
                log.error("创建列表文件夹失败,请手动创建");
            }
        }

        try {
            ImgText imgTextById = pageService.getImgTextById(imgTextId);
            if(imgTextById == null){
                return new SimpleResponse("图片为空,将不在列表页面使用图片",SimpleResponse.SUCCESS);
            }
            imgTextById.setImgUrl(imgUrl);
            pageService.saveImgText(imgTextById);

            imgFile.transferTo(dest);
            log.info("保存列表预览图片成功");
            return new SimpleResponse("保存预览图片成功",SimpleResponse.SUCCESS);
        } catch (IOException e) {
            log.info("保存列表预览图片失败");
            return new SimpleResponse("图片为空,将不在列表页面使用图片",SimpleResponse.SUCCESS);
        }
    }

    @PostMapping("/receiveImg")
    @ResponseBody
    public String receiveImg(@RequestParam("upload") MultipartFile file) {
        log.info("上传图片成功");
        return file + "";
    }
}
