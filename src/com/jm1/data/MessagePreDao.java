/**
 * 
 */
package com.jm1.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.jm1.common.PagedList;
import com.jm1.model.Message;
import com.jm1.util.JDBCUtil;

/**
 * @author evol
 * 使用预编译的SQL
 */
public class MessagePreDao {
	public boolean insert(Message item) {
		Connection conn = null;
		PreparedStatement preStatement = null;
		ResultSet resultSet = null;
		
		try {
			conn =  JDBCUtil.getConnection();
			String sql = "INSERT INTO message(id, nick, email, content) VALUES(?, ?, ?, ?)";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, item.getId().toString());
			preStatement.setString(2, item.getNick());
			preStatement.setString(3, item.getEmail());
			preStatement.setString(4, item.getContent());
			
			int flag = preStatement.executeUpdate();
			return flag > 0;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.release(resultSet, preStatement, conn);
		}

		return false;
	}
	
	public boolean delete(String id) {
		Connection conn = null;
		PreparedStatement preStatement = null;
		ResultSet resultSet = null;
		
		try {
			conn =  JDBCUtil.getConnection();
			String sql = "DELETE FROM message WHERE id = ?";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, id);
			int num = preStatement.executeUpdate();
			return num > 0;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.release(resultSet, preStatement, conn);
		}

		return false;
	}
	
	public boolean update(Message item) {
		Connection conn = null;
		PreparedStatement preStatement = null;
		ResultSet resultSet = null;
		
		try {
			conn =  JDBCUtil.getConnection();
			String sql = "UPDATE message SET nick = ?, email = ?, content = ? WHERE id = ?";
			preStatement = conn.prepareStatement(sql);
			
			preStatement.setString(1, item.getNick());
			preStatement.setString(2, item.getEmail());
			preStatement.setString(3, item.getContent());
			preStatement.setString(4, item.getId().toString());

			int num = preStatement.executeUpdate();
			return num > 0;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.release(resultSet, preStatement, conn);
		}

		return false;
	}
	
	public Message find(String messageId) {
		Connection conn = null;
		PreparedStatement preStatement = null;
		ResultSet rs = null;
		
		try {
			conn =  JDBCUtil.getConnection();
			String sql = "SELECT * FROM message WHERE id = ?";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, messageId);
			rs = preStatement.executeQuery();
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
			JDBCUtil.release(rs, preStatement, conn);
		}
		return null;
	}
	
	public List<Message> SelectAll() {
		Connection conn = null;
		PreparedStatement preStatement = null;
		ResultSet rs = null;
		List<Message> list = new ArrayList<Message>();
		
		try {
			conn =  JDBCUtil.getConnection();
			String sql = String.format("SELECT * FROM message");
			preStatement = conn.prepareStatement(sql);
			rs = preStatement.executeQuery();
			
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
			JDBCUtil.release(rs, preStatement, conn);
		}
		return list;
	}
	
	public List<Message> selectByEmail(String email) {

		Connection conn = null;
		PreparedStatement preStatement = null;
		ResultSet rs = null;
		List<Message> list = new ArrayList<Message>();
		
		try {
			conn =  JDBCUtil.getConnection();
			String sql = "SELECT * FROM message WHERE email LIKE ?";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, "%" + email + "%");
			rs = preStatement.executeQuery();
			
			
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
			JDBCUtil.release(rs, preStatement, conn);
		}
		return list;
	}
	
	public PagedList<Message> selectPaged(int pageIndex, int pageSize, String email) {
		Connection conn = null;
		PreparedStatement preStatement = null;
		PreparedStatement preStatement2 = null;
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

			String sql;
			if(email == null)
			{
				sql = "SELECT * FROM message  LIMIT ?, ?";
				preStatement = conn.prepareStatement(sql);
				preStatement.setInt(1, offset);
				preStatement.setInt(2, 2);
			}
			else
			{
				sql = "SELECT * FROM message  LIMIT ?, ? WHERE email LIKE '%?%'";
				preStatement = conn.prepareStatement(sql);
				preStatement.setInt(1, offset);
				preStatement.setInt(2, 2);
				preStatement.setString(3, email);
			}
			rs = preStatement.executeQuery();
			while (rs.next()) {
				Message msg = new Message();
				UUID  id = UUID.fromString(rs.getString("id"));
				msg.setId(id);
				msg.setNick(rs.getString("nick"));
				msg.setEmail(rs.getString("email"));
				msg.setContent(rs.getString("content"));
				list.add(msg);
			}
			
			String countSql;

			if(email == null)
			{
				countSql = "SELECT COUNT(id) as total FROM message";
				preStatement2 = conn.prepareStatement(countSql);
			}
			else
			{
				countSql = "SELECT COUNT(id) as total FROM message WHERE email LIKE '%?%'";
				preStatement2 = conn.prepareStatement(countSql);
				preStatement2.setString(1, email);
			}
				
			totalRs = preStatement2.executeQuery();
			totalRs.next();
			int total = totalRs.getInt("total");
			

			PagedList<Message> pagedList = new PagedList<Message>(list, total, pageIndex, pageSize);
			return pagedList;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.release(rs, preStatement, conn);
			JDBCUtil.release(totalRs, preStatement2, null);
		}
		return null;
	}
}
