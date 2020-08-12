package com.yys.anhuihezhengweixin.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Map;
import java.util.Properties;


public class PorpertiesConfig {

    public static void updateProperties(String fileName, Map<String, String> keyValueMap) throws Exception {
        //获取文件路径
        String filePath = Profile.class.getClassLoader().getResource(fileName).toURI().getPath();
        System.out.println("propertiesPath:" + filePath);
        Properties props = new Properties();
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            // 从输入流中读取属性列表（键和元素对）
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            props.load(br);
            br.close();
            // 写入属性文件
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath)));
            // 清空旧的文件
            // props.clear();
            for (String key : keyValueMap.keySet()) {
                props.setProperty(key, keyValueMap.get(key));
            }
            System.out.println(props);
            props.store(bw, "改变数据");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Visit " + filePath + " for updating " + "" + " value error");
        } finally {
            try {
                br.close();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
