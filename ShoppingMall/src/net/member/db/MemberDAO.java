package net.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	
	DataSource ds;
	Connection con = null;
	PreparedStatement stmt = null;	
	ResultSet rs = null;

	public MemberDAO(){
		try{
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		ds = (DataSource) envCtx.lookup("jdbc/OracleDB");
		}
		catch(Exception err){
			err.printStackTrace();			
		}
	}
	
	public void freeCon(){
		if(con != null){try{con.close();}catch(Exception err){err.printStackTrace();}}		
		if(rs != null){try{rs.close();}catch(Exception err){err.printStackTrace();}}
		if(stmt != null){try{stmt.close();}catch(Exception err){err.printStackTrace();}}		
	}
	
	// ȸ������
	public boolean insertMember(MemberBean mb)throws SQLException{
		String sql = null;
		boolean result = false;
		try{
			con = ds.getConnection();
			sql = "insert into member values" + 
			"(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, mb.getMEMBER_ID());
			stmt.setString(2, mb.getMEMBER_PW());
			stmt.setString(3, mb.getMEMBER_NAME());
			stmt.setInt(4, mb.getMEMBER_JUMIN1());
			stmt.setInt(5, mb.getMEMBER_JUMIN2());
			stmt.setString(6, mb.getMEMBER_EMAIL());
			stmt.setString(7, mb.getMEMBER_EMAIL_GET());
			stmt.setString(8, mb.getMEMBER_MOBILE());
			stmt.setString(9, mb.getMEMBER_PHONE());
			stmt.setString(10, mb.getMEMBER_ZIPCODE());
			stmt.setString(11, mb.getMEMBER_ADDR1());
			stmt.setString(12, mb.getMEMBER_ADDR2());
			stmt.setInt(13, mb.getMEMBER_ADMIN());
			stmt.setTimestamp(14, mb.getMEMBER_JOIN_DATE());			
			stmt.executeUpdate();
			
			result = true;	// ��� ���� �Ǿ����� ��� ���� true�� �ٲ۴�.	
		}
		catch(Exception err){
			err.printStackTrace();			
		}
		finally{
		freeCon();
		}
		return result;
	}
	
	// ȸ�� Ȯ�� â
	public int userCheck(String id, String pw)throws SQLException{ 
		String sql = null;
		int x = -1;
		
		try{
			con = ds.getConnection();
			sql = "select MEMBER_PW from member  where MEMBER_ID=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			
			rs = stmt.executeQuery();
			if(rs.next()){
				String memberpw = rs.getString("MEMBER_PW");
				
				if(memberpw.equals(pw)){ // DB�� ����� PW�� �Ѱܹ��� PW�� ��ġ�� ���
					x = 1; // x���� 1�� ���� ����
				}
				else{
					x = 0; // �׷��� ���� ��� 0�� ���� ����
				}
			}
			
		}
		catch(Exception err){
			err.printStackTrace();
		}
		finally{
			freeCon();
		}		
		return x; // ��� ���� �Ѱ��ش�.
	}
	
	// idȮ��
	public int confirmId(String id)throws SQLException{
		String sql = null;
		int x = -1;
		
		try{
			con = ds.getConnection();
			sql = "select MEMBER_ID from member where MEMBER_ID=?"; // member���̺� �ִ� id�÷� ��ȸ
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id); // �Ѱ� ���� id�� �ѱ�
			
			rs = stmt.executeQuery(); // rs�� �˻�
			if(rs.next()){ // id�� �ִٸ� x�� 1�� ���� ����
				x = 1;
			}			
		}
		catch(Exception err){
			err.printStackTrace();
		}
		finally{
			freeCon();
		}
		return x;
	}
	
	// ȸ������ �ҷ�����
	public MemberBean getMember(String id)throws SQLException{
		MemberBean member = null;
		String sql = null;
		
		try{
			con = ds.getConnection();
			sql = "select * from member where MEMBER_ID=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			
			if(rs.next()){
				member = new MemberBean();
				
				member.setMEMBER_ID(rs.getString("MEMBER_ID"));
				member.setMEMBER_NAME(rs.getString("MEMBER_NAME"));
				member.setMEMBER_JUMIN1(rs.getInt("MEMBER_JUMIN1"));
				member.setMEMBER_JUMIN2(rs.getInt("MEMBER_JUMIN2"));
				member.setMEMBER_EMAIL(rs.getString("MEMBER_EMAIL"));
				member.setMEMBER_EMAIL_GET(rs.getString("MEMBER_EMAIL_GET"));
				member.setMEMBER_MOBILE(rs.getString("MEMBER_MOBILE"));
				member.setMEMBER_PHONE(rs.getString("MEMBER_PHONE"));
				member.setMEMBER_ZIPCODE(rs.getString("MEMBER_ZIPCODE"));
				member.setMEMBER_ADDR1(rs.getString("MEMBER_ADDR1"));
				member.setMEMBER_ADDR2(rs.getString("MEMBER_ADDR2"));				
			}			
		}
		catch(Exception err){
			err.printStackTrace();
		}
		finally{
			freeCon();
		}		
		return member;
	}
	
	// ȸ������ ����
	public void updateMember(MemberBean mb)throws SQLException{
		String sql = null;
		
		try{
			con = ds.getConnection();
			sql = "update member set MEMBER_PW=?, MEMBER_NAME=?, MEMBER_EMAIL=?, MEMBER_EMAIL_GET=?, "
					+ "MEMBER_MOBILE=?, MEMBER_PHONE=?, MEMBER_ZIPCODE=?, MEMBER_ADDR1=?,"
					+ "MEMBER_ADDR2=? where MEMBER_ID=?";
			
			stmt = con.prepareStatement(sql);
			stmt.setString(1, mb.getMEMBER_PW());
			stmt.setString(2, mb.getMEMBER_NAME());
			stmt.setString(3, mb.getMEMBER_EMAIL());
			stmt.setString(4, mb.getMEMBER_EMAIL_GET());			
			stmt.setString(5, mb.getMEMBER_MOBILE());
			stmt.setString(6, mb.getMEMBER_PHONE());
			stmt.setString(7, mb.getMEMBER_ZIPCODE());
			stmt.setString(8, mb.getMEMBER_ADDR1());
			stmt.setString(9, mb.getMEMBER_ADDR2());
			stmt.setString(10, mb.getMEMBER_ID());
			
			stmt.executeUpdate();
		}
		catch(Exception err){
			err.printStackTrace();
		}
		finally{
			freeCon();
		}
	}
	
	// ȸ������ ����
	public int deleteMember(String id, String pw)throws SQLException{
		int x = -1;
		String sql = null;
		
		try{
			con = ds.getConnection();
			sql = "select MEMBER_PW from member where MEMBER_ID=?";
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			if(rs.next()){
				String memberpw = rs.getString("MEMBER_PW");
				if(memberpw.equals(pw)){
					sql = "delete from member where MEMBER_ID=?";
					stmt = con.prepareStatement(sql);
					stmt.setString(1, id);
					stmt.executeUpdate();
					x = 1;
				}
				else{
					x = 0;
				}
			}			
		}
		catch(Exception err){
			err.printStackTrace();
		}
		finally{
			freeCon();
		}
		
		return x;		
	}
	
	public MemberBean findId(String name, String jumin1, String jumin2)throws SQLException{
		MemberBean member = null;
		String sql = null;
		
		try{
			con = ds.getConnection();
			sql = "select MEMBER_ID, MEMBER_PW, MEMBER_JUMIN1, MEMBER_JUMIN2 from member where MEMBER_NAME=?";
			
			stmt = con.prepareStatement(sql);
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			
			if(rs.next()){
				
				String memberjumin1 = rs.getString("MEMBER_JUMIN1");
				String memberjumin2 = rs.getString("MEMBER_JUMIN2");
				
				if(memberjumin1.equals(jumin1) && memberjumin2.equals(jumin2)){
					member = new MemberBean();
					member.setMEMBER_ID(rs.getString("MEMBER_ID"));
					member.setMEMBER_PW(rs.getString("MEMBER_PW"));
					}
			}
		}
		catch(Exception err){
			err.printStackTrace();
		}
		finally{
			freeCon();
		}
		return member;
	}
	
	// ������ 
	public boolean isAdmin(String id){
		String sql = "select MEMBER_ADMIN from member where MEMBER_ID=?";
		int member_admin = 0;
		boolean result = false;
		
		try{
			con = ds.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			rs.next();
			
			member_admin = rs.getInt("MEMBER_ADMIN");
			
			if(member_admin == 1){
				result = true;
			}
		}
		catch(Exception err){
			err.printStackTrace();
		}
		finally{
			freeCon();
		}
		return result;
	}
	
	public List searchZipcode(String searchdong){
		String sql = "select * from zipcode where dong like?";
		
		List zipcodeList = new ArrayList();
		System.out.println("searchZipcode ���Դ�");
		
		try{
			con = ds.getConnection();
			stmt = con.prepareStatement(sql);
	
			System.out.println("�˻��� �ּҴ� " + searchdong);
			stmt.setString(1, "%"+searchdong+"%");
			rs = stmt.executeQuery();
			
			while(rs.next()){
				String sido = rs.getString("sido");
				System.out.println(sido);
				String gugun = rs.getString("gugun");
				String dong = rs.getString("dong");
				String ri = rs.getString("ri");
				String bunji = rs.getString("bunji");
				
				if(ri == null) ri="";
				if(bunji == null)  bunji="";
				String zipcode = rs.getString("zipcode");
				System.out.println(zipcode);
				String addr = sido + " " + gugun + " " + dong + " " + ri + " " + bunji;
				
				zipcodeList.add(zipcode + "," + addr);			
				System.out.println(zipcodeList);
			}			
		}
		catch(Exception err){
			err.printStackTrace();
		}
		finally{
			freeCon();
		}
		
		return zipcodeList;
	}	
}
