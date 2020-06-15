package com.mocc.spider.controller;

import com.mocc.spider.service.JobInfoService;
import com.mocc.spider.service.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpiderController {

    private final JobInfoService jobInfoService;
    private final NovelService novelService;

    @Autowired
    public SpiderController(JobInfoService jobInfoService, NovelService novelService) {
        this.jobInfoService = jobInfoService;
        this.novelService = novelService;
    }

    @GetMapping("/getJobInfo")
    public String getJobInfo() {
        jobInfoService.getJobInfo();
        return "success";
    }

    @GetMapping("/getNovelInfo")
    public String getNovelInfo() {
        novelService.novelInfo();
        return "success";
    }

}
