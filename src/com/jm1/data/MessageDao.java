package com.jm1.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.jm1.common.PagedList;
import com.jm1.model.Message;
import com.jm1.util.JDBCUtil;
import com.mysql.cj.protocol.Resultset;
import com.sun.xml.internal.fastinfoset.sax.SAXDocumentSerializer;

import sun.management.counter.Variability;

public class MessageDao {

	public boolean insert(Message item) {
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			conn =  JDBCUtil.getConnection();
			statement = conn.createStatement();
			String sql = String.format("INSERT INTO message(id, nick, email, content) VALUES(%s, %s, %s, %s)", item.getId(), item.getNick(), item.getEmail(), item.getContent());
			boolean flag = statement.execute(sql);
			return flag;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.release(resultSet, statement, conn);
		}

		return false;
	}
	
	public boolean delete(Message item) {
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			conn =  JDBCUtil.getConnection();
			statement = conn.createStatement();
			String sql = String.format("DELETE FROM message WHERE id = %s", item.getId());
			int num = statement.executeUpdate(sql);
			return num > 0;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.release(resultSet, statement, conn);
		}

		return false;
	}
	
	public boolean update(Message item) {
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			conn =  JDBCUtil.getConnection();
			statement = conn.createStatement();
			String sql = String.format("UPDATE message SET name = %s, nick = %s, email = %s, content = %s WHERE id = %s", item.getNick(), item.getEmail(), item.getContent(), item.getId());
			int num = statement.executeUpdate(sql);
			return num > 0;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.release(resultSet, statement, conn);
		}

		return false;
	}
	
	public Message find(Message item) {
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			conn =  JDBCUtil.getConnection();
			statement = conn.createStatement();
			String sql = String.format("SELECT * FROM message WHERE id = %s", item.getId());
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				Message msg = new Message();
				UUID  id = UUID.fromString(rs.getString("id"));
				msg.setId(id);
				msg.setNick(rs.getString("nick"));
				msg.setEmail(rs.getString("email"));
				msg.setContent(rs.getString("content"));
				return msg;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.release(rs, statement, conn);
		}
		return null;
	}
	
	public List<Message> SelectAll() {
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		List<Message> list = new ArrayList<Message>();
		
		try {
			conn =  JDBCUtil.getConnection();
			statement = conn.createStatement();
			String sql = String.format("SELECT * FROM message");
			rs = statement.executeQuery(sql);
			
			
			while (rs.next()) {
				Message msg = new Message();
				UUID  id = UUID.fromString(rs.getString("id"));
				msg.setId(id);
				msg.setNick(rs.getString("nick"));
				msg.setEmail(rs.getString("email"));
				msg.setContent(rs.getString("content"));
				list.add(msg);
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.release(rs, statement, conn);
		}
		return list;
	}
	
	public List<Message> selectByEmail(String email) {

		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		List<Message> list = new ArrayList<Message>();
		
		try {
			conn =  JDBCUtil.getConnection();
			statement = conn.createStatement();
			String sql = String.format("SELECT * FROM message WHERE email LIKE '%%s%'", email);
			rs = statement.executeQuery(sql);
			
			
			while (rs.next()) {
				Message msg = new Message();
				UUID  id = UUID.fromString(rs.getString("id"));
				msg.setId(id);
				msg.setNick(rs.getString("nick"));
				msg.setEmail(rs.getString("email"));
				msg.setContent(rs.getString("content"));
				list.add(msg);
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.release(rs, statement, conn);
		}
		return list;
	}
	
	public PagedList<Message> selectPaged(int pageIndex, int pageSize, String email) {
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		ResultSet totalRs = null;
		List<Message> list = new ArrayList<Message>();
		int offset = 0;
		int size = 0;
		
		if(pageIndex < 0)
			pageIndex = 1;
		if(pageSize < 0)
			pageSize = 10;
		
		offset = (pageIndex - 1) * pageSize;
		size = pageSize;

		
		try {
			conn =  JDBCUtil.getConnection();
			statement = conn.createStatement();
			String sql = String.format("SELECT * FROM message  LIMIT %d, %d email LIKE '%%s%'", email);
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				Message msg = new Message();
				UUID  id = UUID.fromString(rs.getString("id"));
				msg.setId(id);
				msg.setNick(rs.getString("nick"));
				msg.setEmail(rs.getString("email"));
				msg.setContent(rs.getString("content"));
				list.add(msg);
			}
			
			totalRs = statement.executeQuery("SELECT COUNT(id) as total FROM message WHERE email LIKE '%%s%'");
			
			int total = totalRs.getInt("total");
			

			PagedList<Message> pagedList = new PagedList<Message>(list, total);
			return pagedList;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.release(rs, statement, conn);
			JDBCUtil.release(totalRs, null, null);
		}
		return null;
	}
}
