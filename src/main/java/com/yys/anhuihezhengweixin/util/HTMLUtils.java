package com.yys.anhuihezhengweixin.util;

import com.yys.anhuihezhengweixin.entity.base.HTMLEntity;
import com.yys.anhuihezhengweixin.entity.base.Sidebar;
import com.yys.anhuihezhengweixin.entity.base.TextEntity;
import com.yys.anhuihezhengweixin.entity.content.ImgText;
import com.yys.anhuihezhengweixin.response.ImgTextResponse;
import com.yys.anhuihezhengweixin.response.SimpleResponse;
import com.yys.anhuihezhengweixin.service.PageService;
import org.springframework.data.domain.Page;
import org.springframework.web.servlet.ModelAndView;

import javax.print.attribute.standard.PagesPerMinute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yys.anhuihezhengweixin.util.HtmlTag.CULTURECONTENT;
import static com.yys.anhuihezhengweixin.util.HtmlTag.HTML;
import static com.yys.anhuihezhengweixin.util.TextEntityUtils.getTextEntityEmpty;
import static com.yys.anhuihezhengweixin.util.UrlUtils.completeUrl;

/**
 * @author zq
 * 此工具类 用来封装页面数据
 */
public class HTMLUtils {


    /**
     * @param pageService 传入service
     * @param htmlEntity 页面实体
     * @param textEntities 头部信息实体
     * @param viewNamme 模板名称
     * @param entityCode 头部数据标识
     * @param contentCode 模板数据标识
     * @param isCompleteUrl 是否拼装url
     * @return 返回 modelandview
     * 带有分页功能
     */
    public static ModelAndView completeData(
            PageService pageService,
            HTMLEntity htmlEntity,
            List<TextEntity> textEntities,
            String viewNamme,
            Integer entityCode,
            Integer contentCode,
            Integer pageNum,
            Boolean isCompleteUrl){
        ModelAndView mv = new ModelAndView(viewNamme);

        TextEntity textEntity = getTextEntityEmpty(entityCode,textEntities);

        if(contentCode != null){
            Page<ImgText> content = pageService.getContent(contentCode, pageNum);

            if(isCompleteUrl){
                completeUrl(content.getContent());
            }
            textEntity.setContent(content.getContent());

            ///只封装需要的参数
            MPage mPage = new MPage(content.getTotalElements(), content.getSize(), content.getTotalPages(), content.getNumber());
            mv.addObject("pageable",mPage);
        }

        htmlEntity.setTextEntity(textEntity);
        mv.addObject(HTML,htmlEntity);

        return mv;
    }


    /**
     * @param pageService 传入service
      * @param htmlEntity 页面实体
      * @param textEntities 头部信息实体
      * @param viewNamme 模板名称
      * @param entityCode 头部数据标识
      * @param contentCode 模板数据标识
      * @param isCompleteUrl 是否拼装url
      * @return 返回 modelandview
      * 不带有分页功能
     */
    public static ModelAndView completeData(
            PageService pageService,
            HTMLEntity htmlEntity,
            List<TextEntity> textEntities,
            String viewNamme,
            Integer entityCode,
            Integer contentCode,
            Boolean isCompleteUrl){
        ModelAndView mv = new ModelAndView(viewNamme);

        TextEntity textEntity = getTextEntityEmpty(entityCode,textEntities);

        if(contentCode != null) {
            List<ImgText> content = pageService.getContent(contentCode);

            if (isCompleteUrl) {
                completeUrl(content);
            }
            textEntity.setContent(content);
        }
        htmlEntity.setTextEntity(textEntity);
        mv.addObject(HTML,htmlEntity);

        return mv;
    }


    /**
     * @param htmlEntity 页面实体
     * @param textEntities 头部实体
     * @param viewName 模板名称
     * @param entityCode 头部数据
     * @param content 内容区域
     * @return 返回modelandview
     * 此方法内容为除了列表拼接 内容不固定
     */
    public static ModelAndView completeStringData(HTMLEntity htmlEntity,List<TextEntity> textEntities,String viewName,Integer entityCode,Object content){
        ModelAndView mv = new ModelAndView(viewName);

        TextEntity textEntity = getTextEntityEmpty(entityCode,textEntities);

        textEntity.setContent(content);

        htmlEntity.setTextEntity(textEntity);
        mv.addObject(HTML,htmlEntity);
        return mv;
    }


    public static ImgTextResponse completeAjaxData(PageService pageService,Integer page,Integer contentCode){
        Page<ImgText> content = pageService.getContent(contentCode, page);
        completeUrl(content.getContent());

        List<ImgText> imgTextList = content.getContent();

        if(imgTextList == null || imgTextList.size() < 1){
            return new ImgTextResponse(null, SimpleResponse.ERROR);
        }

        return new ImgTextResponse(imgTextList, SimpleResponse.SUCCESS);
    }

}



