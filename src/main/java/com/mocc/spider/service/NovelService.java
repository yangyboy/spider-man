package com.mocc.spider.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mocc.spider.entity.Novel;
import com.mocc.spider.mapper.NovelMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NovelService extends ServiceImpl<NovelMapper, Novel> {

}
