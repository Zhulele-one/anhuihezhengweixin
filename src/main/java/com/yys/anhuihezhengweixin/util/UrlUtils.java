package com.yys.anhuihezhengweixin.util;

import com.yys.anhuihezhengweixin.entity.content.ImgText;

import java.util.List;

import static com.yys.anhuihezhengweixin.util.HtmlTag.*;


/**
 * @author zq
 * 此工具类用于对url进行处理
 */
public class UrlUtils {

    public static String BIREFINGURL = "/about/birefing";
    public static String NEWSMESSAGEURL = "";
    public static String ABOUTMESSAGEURL = "/about/message";
    public static String DIFANGRULEURL = "/news/message/" + RULETEXTENTITY;
    public static String COUNTRYRULEURL = "/news/message/" + RULETEXTENTITY;
    public static String PROXYURL = "/business/message/" + PROXYTEXTENTITY;
    public static String SUPERVISIONURL = "/business/message/" + SUPERVISIONTEXTENTITY;
    public static String COSTURL = "/business/message/" + COSTTEXTENTITY;
    public static String PROJECTURL = "/business/message/" + PROJECTTEXTENTITY;
    public static String CULTRUEURL = "/culture/message";
//    public static String RECRUITFORM = "/recruit/recruitForm/" + RECRUITTEXTENTITY;
    public static String RECRUITFORM = "/recruit/recruitForm";
    public static String RECRUITMESSAGE = "/recruit/message";
    public static String LEARNINGURL = "/news/message/" + LEARNINGTEXTENTITY;
    public static String COMPANYNEWSURL = "/news/message/" + COMPANYNEWSTEXTENTITY;
    public static String HANGYENEWSURL = "/news/message/" + HANGYENEWSTEXTENTITY;

    // public static String filePath = "/hezheng/images/loader/";
    public static String itemFilePath = "D:/hezheng/img/";
    public static String FormFilePath = "D:/hezheng/recruit/";
    public static String otherImgPath = "D:/hezheng/otherimg/";


    /**
     * @param imgTextList 模板数据
     *                    用于拼接url
     */
    public static void completeUrl(List<ImgText> imgTextList){
        for (ImgText i : imgTextList){
            String url = i.getUrl() + "/" + i.getTextId();
            i.setUrl(url);
        }
    }

    public static String getImgTextUrl(int textEntityId){
        switch (textEntityId){

            //公司简介
            case 1 : return BIREFINGURL ;

            //荣誉资质
            case 2 : return NEWSMESSAGEURL;

            //业绩展示
            case 3 : return ABOUTMESSAGEURL;

            // 地方政策法规
            case 10 : return DIFANGRULEURL;

            // 国家政策法规
            case 11 : return COUNTRYRULEURL;

            //公司新闻
            case 7 : return COMPANYNEWSURL;

            //行业新闻
            case 8 : return HANGYENEWSURL;

            //学习园地
            case 12 : return LEARNINGURL;

            //招标代理
            case 13 : return PROXYURL;

            //工程监理
            case 14 : return SUPERVISIONURL;

            //造价咨询
            case 15 : return COSTURL;

            //工程设计
            case 16 : return PROJECTURL;

            //党的建设
            case 17 : return CULTRUEURL;

            //招贤纳士
            case 18 : return RECRUITMESSAGE;

            default: return "";
        }
    }
}
