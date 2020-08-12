package com.yys.anhuihezhengweixin.util;

import com.yys.anhuihezhengweixin.entity.base.TextEntity;

import java.util.List;

/**
 * @author zq
 * 此工具类用来从头部标识获得的头部信息集中获取具体的头部信息
 */
public class TextEntityUtils {
    /**
     * @param id 头部信息id
     * @param textEntities 头部数据集
     * @return 返回具体头部信息
     */
    public static TextEntity getTextEntityEmpty(int id, List<TextEntity> textEntities){
        if(id<1){
            id = 1;
        }
        if(id > textEntities.size()){
            id = textEntities.size();
        }
        return textEntities.get(id - 1) == null ?
                new TextEntity(0L,null,null,null,null,null,null,null)
                : textEntities.get(id - 1);
    }
}
