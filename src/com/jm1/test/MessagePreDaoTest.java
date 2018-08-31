package com.jm1.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jm1.common.PagedList;
import com.jm1.data.MessageDao;
import com.jm1.data.MessagePreDao;
import com.jm1.model.Message;

class MessagePreDaoTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}
	

	/**
	 * Test method for {@link com.jm1.data.MessageDao#insert(com.jm1.model.MessageBean)}.
	 */
	@Test
	void testInsert() {
		MessagePreDao messageDao = new MessagePreDao();
		Message item = new Message();
		item.setId(UUID.randomUUID());
		item.setNick("配110");
		item.setEmail("pei110@110.com");
		item.setContent("今天人挺多的");
	    boolean isSuccess = messageDao.insert(item);
		assertTrue(isSuccess);
	}

	/**
	 * Test method for {@link com.jm1.data.MessageDao#delete(com.jm1.model.MessageBean)}.
	 */
	@Test
	void testDelete() {
		MessagePreDao messageDao = new MessagePreDao();
		String id = "dae01065-9941-4c23-9247-bb16b4f491f4";
		Message item = messageDao.find(id);
		assertNotNull(item, "没有匹配的记录，不需要删除");
		
		boolean flag = messageDao.delete(id);
		assertTrue(flag, "删除失败");
		
	}

	/**
	 * Test method for {@link com.jm1.data.MessageDao#update(com.jm1.model.MessageBean)}.
	 */
	@Test
	void testUpdate() {
		MessagePreDao messageDao = new MessagePreDao();
		String id = "3f34f9c7-f7a0-4b07-85c8-03872fc009d7";
		Message item = messageDao.find(id);
		assertNotNull(item, "没有匹配的记录，不需要更新");
		item.setNick(item.getNick() + "-1");
		item.setEmail(item.getEmail() + "-1");
		item.setContent(item.getContent() + "-1");
		item.setNick(item.getNick() + "-1");
		boolean flag = messageDao.update(item);
		assertTrue(flag, "更新失败");
	}

	/**
	 * Test method for {@link com.jm1.data.MessageDao#find(com.jm1.model.MessageBean)}.
	 */
	@Test
	void testFind() {
		MessagePreDao messageDao = new MessagePreDao();
		Message item = messageDao.find("3f34f9c7-f7a0-4b07-85c8-03872fc009d7");
		if(item != null)
		{
		    System.out.println(String.format("id: %s,  nick: %s,  email: %s, content: %s", 
		    		item.getId(), item.getNick(), item.getEmail(), item.getContent()));
		}
		assertNotNull(item, "没有匹配的记录");
	}
	
	/**
	 * Test method for {@link com.jm1.data.MessageDao#find(com.jm1.model.MessageBean)}.
	 */
	@Test
	void testSelectAll() {
		MessagePreDao messageDao = new MessagePreDao();
		List<Message> list = messageDao.SelectAll();
	    System.out.println("Record total:" + list.size());
		assertTrue(list.size() > 0, "没有任何记录");
	}

	/**
	 * Test method for {@link com.jm1.data.MessageDao#selectByEmail(java.lang.String)}.
	 */
	@Test
	void testSelectByEmail() {
		MessagePreDao messageDao = new MessagePreDao();
		List<Message> list = messageDao.selectByEmail("zhangsan@");
	    System.out.println("Record total:" + list.size());
		if(list != null)
		{
			for (Message item : list) {
			    System.out.println(String.format("id: %s,  nick: %s,  email: %s, content: %s", 
			    		item.getId(), item.getNick(), item.getEmail(), item.getContent()));
			}

		}
		assertTrue(list.size() > 0, "没有任何记录");
	}

	/**
	 * Test method for {@link com.jm1.data.MessageDao#selectPaged(java.lang.String)}.
	 */
	@Test
	void testSelectPaged() {
		MessagePreDao messageDao = new MessagePreDao();
		PagedList<Message> pagedlist = messageDao.selectPaged(1, 3, null);
	    System.out.println(String.format("Record total: %d, Page Total: %d, PageIndex: %d, PageSize: %d", pagedlist.recordTotal, pagedlist.pageTotal, pagedlist.pageIndex, pagedlist.pageSize));
		if(pagedlist.items != null)
		{
			for (Message item : pagedlist.items) {
			    System.out.println(String.format("id: %s,  nick: %s,  email: %s, content: %s", 
			    		item.getId(), item.getNick(), item.getEmail(), item.getContent()));
			}

		}
		assertTrue(pagedlist.items.size() > 0, "没有任何记录");
	}


}
