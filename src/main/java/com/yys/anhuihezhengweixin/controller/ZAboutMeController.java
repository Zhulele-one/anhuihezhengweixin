package com.yys.anhuihezhengweixin.controller;

import com.yys.anhuihezhengweixin.entity.base.*;
import com.yys.anhuihezhengweixin.response.SimpleResponse;
import com.yys.anhuihezhengweixin.service.FormService;
import com.yys.anhuihezhengweixin.service.PageService;
import com.yys.anhuihezhengweixin.util.MPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.yys.anhuihezhengweixin.util.HTMLUtils.completeData;
import static com.yys.anhuihezhengweixin.util.HtmlTag.*;

@Controller
@RequestMapping("/aboutme/")
public class ZAboutMeController {


    private HTMLEntity htmlEntity;
    private List<TextEntity> textEntities;

    private final PageService pageService;
    private final FormService formService;

    @Autowired
    public ZAboutMeController(PageService pageService, FormService formService) {
        this.pageService = pageService;
        this.formService = formService;
    }


    @ModelAttribute
    public void init(){
        if(textEntities == null){
            textEntities = pageService.initPageText(ABOUTMEID,null);
        }
        if(htmlEntity == null){
            htmlEntity = pageService.initPage(ABOUTMEID);
        }

    }





    @GetMapping("/callback")
    public ModelAndView aboutMeCallBack(){
        ModelAndView mv = completeData(pageService,htmlEntity,textEntities,
                CALLBACKFORMTEMPLATE,ABOUTMETEXTENTITY,null,false);

        List<BackType> backTypes = formService.getBackTypes();
        mv.addObject("backTypes",backTypes);

        return mv;
    }

//    @GetMapping("/callme")
//    public ModelAndView aboutMeCallMe(){
//        return completeData(pageService,htmlEntity,textEntities,
//                CALLMETEMPLATE,ABOUTMETEXTENTITY,null,false);
//    }

    @PostMapping("/callback/message")
    @ResponseBody
    public SimpleResponse aboutMeCallBackMessage(BackFormEntity backFormEntity){
        System.out.println(backFormEntity);
        if(backFormEntity == null){
            return new SimpleResponse("提交失败",SimpleResponse.ERROR);
        }
        if(formService.saveFormBack(backFormEntity) == null){
            return new SimpleResponse("提交失败",SimpleResponse.ERROR);
        } else {
            return new SimpleResponse("提交成功",SimpleResponse.SUCCESS);
        }
    }

    @GetMapping("/seeBackForm/{page}")
    public ModelAndView seeRecruitForm(@PathVariable Integer page){

        ModelAndView mv = completeData(pageService,htmlEntity,textEntities,ZBACKFORMLISTTEMPLATE,ABOUTMETEXTENTITY,null,false);
        Page<BackFormEntity> formList = formService.getBackFormList(page);

        mv.addObject("formList",formList.getContent());

        mv.addObject("backTypes",formService.getBackTypes());

        MPage mPage = new MPage(formList.getTotalElements(), formList.getSize(), formList.getTotalPages(), formList.getNumber());
        mv.addObject("pageable",mPage);


        return mv;
    }

    @GetMapping("/deletePerson/{id}")
    @ResponseBody
    public SimpleResponse deletePerson(@PathVariable Long id){
        BackFormEntity formEntity = formService.findBackInfo(id);
        if(formEntity != null){
            formService.deleteBackForm(formEntity);
            return new SimpleResponse("删除成功",SimpleResponse.SUCCESS);
        } else {
            return new SimpleResponse("此人员信息不存在",SimpleResponse.ERROR);
        }
    }




}
