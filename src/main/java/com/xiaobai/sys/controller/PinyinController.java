package com.xiaobai.sys.controller;

import com.alibaba.druid.util.StringUtils;
import com.xiaobai.util.PinyinUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("sys/pinyin")
public class PinyinController {
    @RequestMapping("getAlpha")
    @ResponseBody
    public String getAlpha(String str){
        return StringUtils.isEmpty(str)?"":PinyinUtils.getAlpha(str);
    }
    @RequestMapping("getPinyin")
    @ResponseBody
    public String getPinyin(String str){
        return StringUtils.isEmpty(str)?"":PinyinUtils.getPingYin(str);
    }
}
