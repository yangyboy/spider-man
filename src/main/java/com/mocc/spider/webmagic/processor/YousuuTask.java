package com.mocc.spider.webmagic.processor;


import com.mocc.spider.webmagic.MyDownloader;
import com.mocc.spider.webmagic.pipeline.YousuuPipeline;
import com.mocc.spider.webmagic.spider.MySpider;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.scheduler.RedisScheduler;

@Component
public class YousuuTask {

    private static final String SITE_CODE = "yousuu";

    private static final String URL = "http://www.yousuu.com/bookstore/?type&tag&countWord&status&update&sort&page=";

    public void doTask() {
        MySpider mySpider = MySpider.create(new YousuuProcessor());

        mySpider.setDownloader(new MyDownloader(SITE_CODE));
        mySpider.setScheduler(new RedisScheduler(SITE_CODE));
        mySpider.addPipeline(new YousuuPipeline());

        mySpider.thread(10);

        int totalPage = 11014;

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
