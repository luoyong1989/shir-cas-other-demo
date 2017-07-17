package com.ly.controller;

import com.ly.controller.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ly on 2017/6/27.
 */
@RestController
public class TestController {
    @Autowired
    UserDto userDto;

    @RequestMapping("/body")
    public Object mian(){
        Map<String, Object> map = new HashMap<>();
        map.put("time",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        map.put("msg","你好啊");
        map.put("user",userDto);
        return map;
    }

}
