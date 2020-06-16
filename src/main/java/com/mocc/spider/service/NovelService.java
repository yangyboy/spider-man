package com.mocc.spider.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mocc.spider.entity.Novel;
import com.mocc.spider.mapper.NovelMapper;
import com.mocc.spider.webmagic.downloader.MyDownloader;
import com.mocc.spider.webmagic.pipeline.YousuuPipeline;
import com.mocc.spider.webmagic.processor.YousuuProcessor;
import com.mocc.spider.webmagic.spider.MySpider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Request;

@Slf4j
@Service
public class NovelService extends ServiceImpl<NovelMapper, Novel> {

    private static final String URL = "http://www.yousuu.com/bookstore/?type&tag&countWord&status&update&sort&page=";

    public void novelInfo() {
        log.info("开始爬取数据");

        MySpider mySpider = MySpider.create(new YousuuProcessor());

        mySpider.setDownloader(new MyDownloader());
//        mySpider.setScheduler(new RedisScheduler(SITE_CODE));
        mySpider.addPipeline(new YousuuPipeline());

        mySpider.thread(10);

        int totalPage = 2;

        // 添加起始url
        for(int i=1; i<=totalPage; i++) {
            Request request = new Request(URL + i);
            // 在Request额外信息中设置页面类型
            request.putExtra(YousuuProcessor.TYPE, YousuuProcessor.LIST_TYPE);
            mySpider.addRequest(request);
        }

        mySpider.run();
    }

}
