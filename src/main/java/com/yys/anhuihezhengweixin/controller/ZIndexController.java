package com.yys.anhuihezhengweixin.controller;

import com.yys.anhuihezhengweixin.entity.base.OtherImg;
import com.yys.anhuihezhengweixin.entity.base.Pay;
import com.yys.anhuihezhengweixin.entity.base.Record;
import com.yys.anhuihezhengweixin.response.SimpleResponse;
import com.yys.anhuihezhengweixin.service.EditService;
import com.yys.anhuihezhengweixin.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static com.yys.anhuihezhengweixin.util.HtmlTag.*;

@Controller
public class ZIndexController {

    private List<OtherImg> bannerList = new ArrayList<>();
    private List<OtherImg> businessImgList = new ArrayList<>();
    private OtherImg aboutImg = new OtherImg();

    private static final Integer BANNER_SIZE = 3;
    private static final Integer BUSINESS_IMG_SIZE = 4;
    private static final Integer BANNER_PIC_MAX = 3;
    private static final Integer ABOUT_PIC_NUM = 4;
    private static final Integer BUSINESS_PIC_MIN = 5;
    private static final Integer BUSINESS_PIC_MAX = 8;


    private final EditService editService;

    private final FormService formService;

    @Autowired
    public ZIndexController(EditService editService, FormService formService) {
        this.editService = editService;
        this.formService = formService;
    }

    @GetMapping("/index")
    public String index(){
        return MODELTEMPLATE;
    }

    @GetMapping("/zIndex")
    public String zIndex(){
        return ZINDEXTEMPLATE;
    }

    @GetMapping("/indexSetting")
    public ModelAndView indexSetting(){
        ModelAndView mv = new ModelAndView(ZINDEXSETTINGTEMPLATE);

        if(bannerList.size() < BANNER_SIZE || businessImgList.size() < BUSINESS_IMG_SIZE || aboutImg.getPicId() == null){
            List<OtherImg> allOtherImg = editService.findAllOtherImg();

            getBannerList(allOtherImg);

            getAboutImg(allOtherImg);

            getBusinessImgList(allOtherImg);
        }

        mv.addObject("bannerList",bannerList);
        mv.addObject("aboutImg",aboutImg);
        mv.addObject("businessImgList",businessImgList);


        List<Pay> pays = formService.getPays();
        mv.addObject("pays",pays);

        List<Record> records = formService.getRecords();
        mv.addObject("records",records);


        return mv;
    }

    private void getBannerList(List<OtherImg> allOtherImg){
        for (OtherImg o:allOtherImg){
            if(bannerList.size() >= BANNER_SIZE){
                break;
            }

            if(o.getPicId() <= BANNER_PIC_MAX){
                bannerList.add(o);
            }
        }
    }

    private void getAboutImg(List<OtherImg> allOtherImg){
        for (OtherImg o : allOtherImg){
            if(o.getPicId().equals(ABOUT_PIC_NUM)){
                aboutImg = o;
                break;
            }
        }
    }

    private void getBusinessImgList(List<OtherImg> allOtherImg){
        for (OtherImg o : allOtherImg){
            if(businessImgList.size() >= BUSINESS_IMG_SIZE){
                break;
            }
            if(o.getPicId() >= BUSINESS_PIC_MIN && o.getPicId() <= BUSINESS_PIC_MAX){
                businessImgList.add(o);
            }

        }
        if(businessImgList.size() < BUSINESS_IMG_SIZE){
            businessImgList = null;
        }
    }

    @GetMapping("/addPay/{payZoom}")
    @ResponseBody
    public SimpleResponse addPay(@PathVariable String payZoom){
        Long payCount = formService.getPayCount();

        Pay pay = new Pay();

        pay.setPayId((int) (payCount + 1));
        pay.setPayZoom(payZoom);

        Pay pay1 = formService.savePay(pay);
        if(pay1 != null){
            return new SimpleResponse("保存成功",SimpleResponse.SUCCESS);
        } else {
            return new SimpleResponse("保存失败",SimpleResponse.ERROR);
        }
    }

    @GetMapping("/delPay/{payId}")
    @ResponseBody
    public SimpleResponse delPay(@PathVariable Integer payId){

        Pay payByPayId = formService.findPayByPayId(payId);
        if(payByPayId == null){
            return new SimpleResponse("你要删除的信息不存在",SimpleResponse.ERROR);
        }

        formService.deletePay(payByPayId);

        return new SimpleResponse("删除成功",SimpleResponse.SUCCESS);

    }

    @GetMapping("/editPay/{payId}/{payZoom}")
    @ResponseBody
    public SimpleResponse editPay(@PathVariable("payId")Integer payId,@PathVariable("payZoom") String payZoom){

        Pay payById = formService.findPayByPayId(payId);
        if(payById == null){
            return new SimpleResponse("修改失败,此信息不存在",SimpleResponse.ERROR);
        }
        payById.setPayZoom(payZoom);
        Pay pay1 = formService.savePay(payById);

        if(pay1 != null){
            return new SimpleResponse("修改成功",SimpleResponse.SUCCESS);
        } else {
            return new SimpleResponse("修改失败",SimpleResponse.SUCCESS);
        }
    }



    @GetMapping("/addRecord/{recordName}")
    @ResponseBody
    public SimpleResponse addRecord(@PathVariable String recordName){
        Long payCount = formService.getRecordCount();
        Record record = new Record();
        record.setRecordId((int) (payCount + 1));
        record.setRecordName(recordName);
        Record record1 = formService.saveRecord(record);
        if(record1 != null){
            return new SimpleResponse("保存成功",SimpleResponse.SUCCESS);
        } else {
            return new SimpleResponse("保存失败",SimpleResponse.ERROR);
        }
    }


    @GetMapping("/delRecord/{recordId}")
    @ResponseBody
    public SimpleResponse delRecord(@PathVariable Integer recordId){

        Record recordById = formService.findRecordByRecordId(recordId);

        if(recordById == null){
            return new SimpleResponse("你要删除的信息不存在",SimpleResponse.ERROR);
        }

        formService.deleteRecord(recordById);

        return new SimpleResponse("删除成功",SimpleResponse.SUCCESS);

    }

    @GetMapping("/editRecord/{recordId}/{recordName}")
    @ResponseBody
    public SimpleResponse editRecord(@PathVariable("recordId")Integer recordId,@PathVariable("recordName") String recordName){

        Record recordById = formService.findRecordByRecordId(recordId);
        if(recordById == null){
            return new SimpleResponse("修改失败,此信息不存在",SimpleResponse.ERROR);
        }
        recordById.setRecordName(recordName);
        Record record = formService.saveRecord(recordById);

        if(record != null){
            return new SimpleResponse("修改成功",SimpleResponse.SUCCESS);
        } else {
            return new SimpleResponse("修改失败",SimpleResponse.SUCCESS);
        }
    }


}
