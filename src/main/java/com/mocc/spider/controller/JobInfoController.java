package com.mocc.spider.controller;

import com.mocc.spider.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobInfoController {

    private final JobInfoService jobInfoService;

    @Autowired
    public JobInfoController(JobInfoService jobInfoService) {
        this.jobInfoService = jobInfoService;
    }

    @GetMapping("/getJobInfo")
    public String getJobInfo() {
        jobInfoService.getJobInfo();
        return "success";
    }
}
