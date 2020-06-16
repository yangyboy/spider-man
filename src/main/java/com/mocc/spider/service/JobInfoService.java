package com.mocc.spider.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mocc.spider.mapper.JobInfoMapper;
import com.mocc.spider.entity.JobInfo;
import com.mocc.spider.webmagic.processor.JobProcessor;
import com.mocc.spider.webmagic.pipeline.JobInfoPipeline;
import com.mocc.spider.webmagic.spider.MySpider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;

import java.util.List;

@Slf4j
@Service
public class JobInfoService extends ServiceImpl<JobInfoMapper, JobInfo> {
    //开始爬取的url
    String url = "https://search.51job.com/list/080200,000000,0000,26,9,99,%25E6%2588%25BF%25E4%25BA%25A7%25E7%25BB%258F%25E7%25BA%25AA%25E4%25BA%25BA,2,1.html?lang=c&stype=1&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";

    @Autowired
    private JobInfoPipeline mysqlPipeline;

    @Autowired
    private JobProcessor jobProcessor;

    public void getJobInfo() {
        log.info("开始爬取数据");

        //设置爬虫配置
        MySpider.create(jobProcessor)
                .addUrl(url) //设置初始爬取的url
                //使用布隆过滤器过滤重复url,需要引入guava包
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(100000)))
                .thread(50) //设置线程数
                .addPipeline(mysqlPipeline) //设置持久化
                .run();
    }

    public List<JobInfo> selectJobInfoByUrl(String url) {
        QueryWrapper<JobInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("url", url);
        List<JobInfo> jobInfos = this.baseMapper.selectList(wrapper);
        return jobInfos;
    }
}
