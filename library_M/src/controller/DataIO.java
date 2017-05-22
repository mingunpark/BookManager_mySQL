package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetImpl;

import models.Book;
import models.BorrowInfo;
import models.User;

public class DataIO {
	
	private final String DBPATH = "jdbc:mysql://210.107.213.142:3306/library" ;
	private final String DBNAME = "mingun" ;
	private final String DBPASS = "1234" ;


	private Connection conn;
	private Statement smt;
	
	
	private void initDB(){
		
		try {
			conn=(Connection)DriverManager.getConnection(DBPATH,DBNAME,DBPASS);
			smt=conn.createStatement();
						
			if(conn!=null){
				System.out.println("Connection Success!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Connection Fail...");
			
			e.printStackTrace();
		}
		
	}
	
	private void closeDB(){
		
		try{
			
			conn.close();
			smt.close();
		}catch(SQLException se){
			se.printStackTrace();
		}finally{
			try{
				if(smt!=null)
					smt.close();
			}catch(SQLException se2){
			}
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
	}
	
	public ArrayList<Book> bringBookInfo(){
		ArrayList<Book> temp=new ArrayList<Book>();
		String bookSql="select * from book";
		String borSql="select * from borrow";
		
		ResultSet rs=null;
		ResultSet brs=null;
		
		initDB();
		
		try{
		
			rs=smt.executeQuery(bookSql);
			
			while(rs.next()){
				Book book=new Book();
				BorrowInfo bkinfo=null;
				
				book.setIndex(rs.getInt("index"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPrice(rs.getInt("price"));
				book.setPage(rs.getInt("page"));
				book.setBorrow(rs.getInt("borrowed"));
				
				if(rs.getInt("borrowed")==1){
					Statement borSmt=conn.createStatement();
					brs=borSmt.executeQuery(borSql);
					bkinfo=new BorrowInfo();
					
					while(brs.next()){
						if(rs.getString("title").compareTo(brs.getString("title"))==0){
							
							bkinfo.setBorrower(brs.getString("borrower"));
							bkinfo.setBorrowTime(brs.getString("borrowTime"));
							bkinfo.setExtend(brs.getInt("extend"));
							book.setBorrowInfo(bkinfo);
						}
					}
				
					brs.close();
					 
				}else{
					bkinfo=new BorrowInfo();
					bkinfo.setBorrower(null);
					bkinfo.setBorrowTime(null);
					bkinfo.setExtend(0);
					book.setBorrowInfo(bkinfo);

				}
				
				
				
				temp.add(book);
			}
			
			
			rs.close();
		
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		closeDB();
		
		return temp;
	}

	public ArrayList<User> bringUserInfo(){
		ArrayList<User> temp=new ArrayList<User>();
		String userSql="select * from user";
		
		
		initDB();
		
		try{
			
			ResultSet rs=smt.executeQuery(userSql);
					
			while(rs.next()){
				
				User user=new User();
				
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPass(rs.getString("password"));
				user.setPhone(rs.getNString("phone"));
				user.setMail(rs.getString("email"));				
				
				temp.add(user);
			}
			
			
			rs.close();
		
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		closeDB();
		
		return temp;
	}
	
	
	public void storeBookInfo(ArrayList<Book> book){
		
		String delBookDB = "delete from book";
		String delBorrDB = "delete from borrow";
		
		String saveBookDB = null;
		String saveBorrDB = null;
		int count=1;
		
		
		initDB();
		

		
		
		try{
			Statement borSmt=conn.createStatement();
			smt.executeUpdate(delBookDB);
			borSmt.executeUpdate(delBorrDB);
			
			for(Book i : book){
				saveBookDB = "insert into book values("+i.getIndex()+",'"+i.getTitle()+"','"+i.getAuthor()+"',"
							+i.getPrice()+","+i.getPage()+","+i.getBorrow()+")";
				smt.executeUpdate(saveBookDB);
				
				if(i.getBorrow()==1){
					
					
					saveBorrDB = "insert into borrow values("+count+",'"+i.getTitle()+"','"+i.getBorrowInfo().getBorrower()+"','"+i.getBorrowInfo().getBorrowTime()+"',"+i.getBorrowInfo().getExtend()+")";
					
					borSmt.executeUpdate(saveBorrDB);
					count++;
				}
			}
			
			
			
		}catch(SQLException se){
			System.out.println("SAVE BOOK FAIL...");
			se.printStackTrace();
		}
		
		System.out.println("SAVE BOOK SUCCESS!");
		closeDB();
	
		

	}
	
	public void storeUserInfo(ArrayList<User> user){
		
		String delUserDB = "delete from user";
	
		
		String saveUserDB = null;
	
		initDB();
	
		
		try{
			smt.executeUpdate(delUserDB);
			
			for(User i : user){
				saveUserDB = "insert into user values('"+i.getID()+"','"+i.getName()+"','"+i.getPass()+"','"
							+i.getPhone()+"','"+i.getMail()+"')";
				smt.executeUpdate(saveUserDB);
				
			}
			
		
		}catch(SQLException se){
			System.out.println("SAVE USER FAIL...");
			se.printStackTrace();
		}
		
		System.out.println("SAVE USER SUCCESS!");
		closeDB();
		
	}
}
