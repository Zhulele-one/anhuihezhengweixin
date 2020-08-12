package com.yys.anhuihezhengweixin.service;

import com.yys.anhuihezhengweixin.dao.BaseTextDao;
import com.yys.anhuihezhengweixin.entity.content.BaseText;
import com.yys.anhuihezhengweixin.entity.content.ImgText;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class TextService {
    private final BaseTextDao baseTextDao;


    @Autowired
    public TextService(BaseTextDao baseTextDao) {
        this.baseTextDao = baseTextDao;
    }


    public BaseText getBaseTextByTextEntityId(Integer id){

        List<BaseText> allByTextEntityId = baseTextDao.findAllByTextEntityId(id);
        if(allByTextEntityId == null || allByTextEntityId.size()<1){
            log.info("文章不存在 请检查数据库 basetext表 或 imgtext表");
            return new BaseText(0L,0,"文章不存在","");
        }else{
            return allByTextEntityId.get(0);
        }
    }

    public BaseText getBaseTextById(Long id){
        Optional<BaseText> byId = baseTextDao.findById(id);
        if(!byId.isPresent()){
            log.info("文章不存在 请检查数据库 basetext表 或 imgtext表");
            return new BaseText(0L,0,"文章不存在","");
        } else {
            return byId.get();
        }
    }

    public BaseText savePage(BaseText baseText) {
        return baseTextDao.save(baseText);

    }

    public boolean deleteBasePage(Long id) {
        Optional<BaseText> byId = baseTextDao.findById(id);
        if(byId.isPresent()){
            baseTextDao.delete(byId.get());
            return true;
        } else {
            log.info("没有此文章");
            return false;
        }
    }
}
