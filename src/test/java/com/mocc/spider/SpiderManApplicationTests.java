package com.mocc.spider;

import com.mocc.spider.mapper.JobInfoMapper;
import com.mocc.spider.entity.JobInfo;
import org.apache.catalina.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.InfoProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpiderManApplicationTests {

	@Autowired
	private JobInfoMapper jobInfoMapper;


	@Test
	public void testSelect() {
		System.out.println(("----- selectAll method test ------"));
		List<JobInfo> infoList = jobInfoMapper.selectList(null);
		infoList.forEach(System.out::println);
	}


	@Test
	public void testInsert() {
		JobInfo info = new JobInfo();

		info.setJobName("123");

		int insert = jobInfoMapper.insert(info);
		System.out.println(insert);

	}

}
