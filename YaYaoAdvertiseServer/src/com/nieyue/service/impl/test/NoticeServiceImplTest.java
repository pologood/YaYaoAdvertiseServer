package com.nieyue.service.impl.test;

import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.nieyue.bean.Notice;
import com.nieyue.service.NoticeService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/spring-dao.xml","classpath:config/spring-service.xml"})
@TransactionConfiguration(transactionManager="txManager",defaultRollback=false)
public class NoticeServiceImplTest {
	@Resource
	 NoticeService  noticeService;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddNotice() {
		Notice p = new Notice();
		p.setType("dsfdsf");
		p.setUpdateDate(new Date());
		noticeService.addNotice(p);
	}

	@Test
	public void testDelNotice() {
		noticeService.delNotice(1000);
	}

	@Test
	public void testUpdateNotice() {
		Notice p = noticeService.loadNotice(1000);
		p.setUpdateDate(new Date());
		noticeService.updateNotice(p);
	}

	@Test
	public void testLoadNotice() {
		fail("Not yet implemented");
	}

	@Test
	public void testCountAll() {
		int n = noticeService.countAll();
		System.out.println(n);
	}


	@Test
	public void testBrowsePagingNotice() {
		List<Notice> l = noticeService.browsePagingNotice(1, 1, "update_date", "desc");
		for (int i = 0; i < l.size(); i++) {
			System.out.println(l.get(i).getType());
			
		}
	}

}
