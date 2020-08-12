package com.yys.anhuihezhengweixin.controller;

import com.alibaba.fastjson.JSON;
import com.yys.anhuihezhengweixin.entity.base.*;
import com.yys.anhuihezhengweixin.entity.content.BaseText;
import com.yys.anhuihezhengweixin.entity.content.ImgText;
import com.yys.anhuihezhengweixin.response.SimpleResponse;
import com.yys.anhuihezhengweixin.service.FormService;
import com.yys.anhuihezhengweixin.service.PageService;
import com.yys.anhuihezhengweixin.service.TextService;
import com.yys.anhuihezhengweixin.util.MPage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.yys.anhuihezhengweixin.util.HTMLUtils.completeData;
import static com.yys.anhuihezhengweixin.util.HTMLUtils.completeStringData;
import static com.yys.anhuihezhengweixin.util.HtmlTag.*;
import static com.yys.anhuihezhengweixin.util.TextEntityUtils.getTextEntityEmpty;
import static com.yys.anhuihezhengweixin.util.UrlUtils.*;

@Log4j2
@Controller
@RequestMapping("/__recruit__")
public class RecruitController {



    private HTMLEntity htmlEntity;
    private List<TextEntity> textEntities;
    private List<Sidebar> sidebars;


    private final PageService pageService;
    private final FormService formService;
    private final TextService textService;

    private File file;


    @Autowired
    public RecruitController(PageService pageService, FormService formService, TextService textService) {
        this.pageService = pageService;
        this.formService = formService;
        this.textService = textService;
    }


    @ModelAttribute
    public void init(){
        if(textEntities == null){
            if(sidebars == null){
                sidebars = pageService.initSidebar(RECRUITID);
            }
            textEntities = pageService.initPageText(RECRUITID,sidebars);
        }
        if(htmlEntity == null){
            htmlEntity = pageService.initPage(RECRUITID);
        }

    }


    @GetMapping("/{page}")
    public ModelAndView recruit(@PathVariable Integer page){
//        ModelAndView mv =  completeData(pageService,htmlEntity,textEntities,
//                RECRUITTEMPLATE,RECRUITTEXTENTITY,RECRUITCONENT,true);

        ModelAndView mv = new ModelAndView(RECRUITTEMPLATE);

        TextEntity textEntity = getTextEntityEmpty(RECRUITTEXTENTITY,textEntities);

        List<ImgText> imgTextList = pageService.getContent(RECRUITCONENT,page).getContent();

        completeUrl(imgTextList);


        for (ImgText imgText : imgTextList) {
            Integer textId = imgText.getTextId();
            Job jobByJobId = formService.getJobByJobId(textId);
            if(jobByJobId == null){
                log.info("识别到还没有此job,将会添加");
                jobByJobId = new Job();
            }
            jobByJobId.setJobId(imgText.getTextId());
            jobByJobId.setJobName(imgText.getName());
            Job job = formService.saveJob(jobByJobId);
            if(job == null){
                log.info("添加失败,刷新页面会重新进行识别添加");
            }
        }

        textEntity.setContent(imgTextList);

        htmlEntity.setTextEntity(textEntity);
        mv.addObject(HTML,htmlEntity);

        mv.addObject("recruitFormUrl",RECRUITFORM);

        return mv;
    }


    @GetMapping("/recruitForm/{id}")
    public ModelAndView recruitForm(@PathVariable Integer id){
        if(id == null){
            id = 1;
        }
        ModelAndView mv = completeData(pageService,htmlEntity,textEntities,
                FORMTEMPLATE,RECRUITTEXTENTITY,null,false);

        mv.addObject("jobs",formService.getJobs());
        mv.addObject("records",formService.getRecords());
        mv.addObject("pays",formService.getPays());
        mv.addObject("jobId",id);

        return mv;
    }



    @PostMapping("/recruitForm/message")
    @ResponseBody
    @Transactional
    public SimpleResponse recruitForm(FormEntity formEntity){
        System.out.println(formEntity);

        if(file == null){
            return new SimpleResponse("提交失败,请确认简历提交成功",SimpleResponse.ERROR);
        }

        if (formEntity == null){
            file = null;
            return new SimpleResponse("提交失败",SimpleResponse.ERROR);
        }

        formEntity.setFilePath(FormFilePath + file.getName());

        if(formService.saveForm(formEntity) == null){
            file = null;
            return new SimpleResponse("提交失败了",SimpleResponse.ERROR);
        } else {
            file = null;
            return new SimpleResponse("保存信息成功",SimpleResponse.SUCCESS);
        }
    }

    @PostMapping("/receiveImg")
    @ResponseBody
    @Transactional
    public SimpleResponse receiveImg(MultipartFile formFile){
        if (formFile.isEmpty()) {
            log.info("简历为空");
            return new SimpleResponse("简历为空",SimpleResponse.SUCCESS);
        }
        String fileName = formFile.getOriginalFilename();

        String imgUrl = FormFilePath + fileName;

        File dest = new File(imgUrl);



        if(!dest.exists()){
            boolean mkdirs = dest.mkdirs();
            if(!mkdirs){
                log.error("创建列表文件夹失败,请手动创建");
            }
        }

        try {

            formFile.transferTo(dest);
            log.info("保存列表预览图片成功");
            file = dest;
            return new SimpleResponse("保存预览图片成功",SimpleResponse.SUCCESS);
        } catch (IOException e) {
            log.info("保存列表预览图片失败");
            return new SimpleResponse("图片为空,将不在列表页面使用图片",SimpleResponse.SUCCESS);
        }
    }



    @GetMapping("/message/{id}")
    @ResponseBody
    public SimpleResponse aboutAchievementMessage(@PathVariable Long id){

        BaseText byId = textService.getBaseTextById(id);

        if(byId == null){
            return new SimpleResponse("获取招聘信息失败",SimpleResponse.ERROR);
        }

        String content = byId.getContent();

        return new SimpleResponse(content,SimpleResponse.SUCCESS);
    }

    @GetMapping("/seeRecruitForm/{page}")
    public ModelAndView seeRecruitForm(@PathVariable Integer page){

        ModelAndView mv = completeData(pageService,htmlEntity,textEntities,FORMLISTTEMPLATE,RECRUITTEXTENTITY,null,false);
        Page<FormEntity> formList = formService.getFormList(page);

        mv.addObject("formList",formList.getContent());

        mv.addObject("jobs",formService.getJobs());
        mv.addObject("records",formService.getRecords());
        mv.addObject("pays",formService.getPays());


        MPage mPage = new MPage(formList.getTotalElements(), formList.getSize(), formList.getTotalPages(), formList.getNumber());
        mv.addObject("pageable",mPage);


        return mv;
    }


}
