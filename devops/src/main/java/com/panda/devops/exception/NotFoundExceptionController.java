package com.panda.devops.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class NotFoundExceptionController implements ErrorController {
    private static final String ERROR_PATH="/error";

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping(ERROR_PATH)
    public Object handleError() {
        Map<String,String> map=new HashMap<>();
        map.put("404", "访问路径不存在！");
        return map;
    }
}
