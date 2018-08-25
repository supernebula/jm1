/**
 * 
 */
package com.jm1.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.jm1.common.PagedList;
import com.jm1.data.MessageDao;
import com.jm1.model.Message;

/**
 * @author evol
 *
 */
class MessageDaoTest {

	/**
	 * Test method for {@link com.jm1.data.MessageDao#insert(com.jm1.model.MessageBean)}.
	 */
	@Test
	void testInsert() {
		MessageDao messageDao = new MessageDao();
		Message item = new Message();
		item.setId(UUID.randomUUID());
		item.setNick("王五22");
		item.setEmail("wangwu22@sina.com");
		item.setContent("今天人挺多的");
	    boolean isSuccess = messageDao.insert(item);
		assertTrue(isSuccess);
	}

	/**
	 * Test method for {@link com.jm1.data.MessageDao#delete(com.jm1.model.MessageBean)}.
	 */
	@Test
	void testDelete() {
		MessageDao messageDao = new MessageDao();
		String id = "f50f7f95-cd45-4fec-86ad-e95b3ff41f84";
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
		MessageDao messageDao = new MessageDao();
		String id = "1c05707d-d2cf-486d-9c31-3e1d4555709f";
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
		MessageDao messageDao = new MessageDao();
		Message item = messageDao.find("b9845730-626b-4f13-9e52-42214698deb7");
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
		MessageDao messageDao = new MessageDao();
		List<Message> list = messageDao.SelectAll();
	    System.out.println("Record total:" + list.size());
		assertTrue(list.size() > 0, "没有任何记录");
	}

	/**
	 * Test method for {@link com.jm1.data.MessageDao#selectByEmail(java.lang.String)}.
	 */
	@Test
	void testSelectByEmail() {
		MessageDao messageDao = new MessageDao();
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
		MessageDao messageDao = new MessageDao();
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
