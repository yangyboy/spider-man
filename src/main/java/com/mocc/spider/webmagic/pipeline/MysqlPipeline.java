package com.mocc.spider.webmagic.pipeline;

import com.mocc.spider.entity.JobInfo;
import com.mocc.spider.service.JobInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Component
@Slf4j
public class MysqlPipeline implements Pipeline {

    private final JobInfoService jobInfoService;

    @Autowired
    public MysqlPipeline(JobInfoService jobInfoService) {
        this.jobInfoService = jobInfoService;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        //获取封装好的数据
        JobInfo jobInfo = resultItems.get("jobInfo");
        if (jobInfo != null) {
            jobInfoService.save(jobInfo);
        }
    }
}
