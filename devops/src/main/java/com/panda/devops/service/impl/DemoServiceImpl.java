package com.panda.devops.service.impl;

import com.panda.devops.service.DemoService;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public String hello() {
        return "hello";
    }
}
