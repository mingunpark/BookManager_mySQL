package controller;

import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

import library_M.MainClass;
import models.User;

public class UserManage {
	
	private User user=new User();
	private Scanner sc=new Scanner(System.in);
	private String name=null,id=null,phNum=null,mail=null;
	private String passwrd;
	private final int NEWSPACE = 80;
	private Console console=System.console();
	private CheckError error=new CheckError();
	
	
	public String getUser(){
		return id;
	}
	public String getPass(){
		return passwrd;
	}
	
	public User signUp(ArrayList<User> member){
		
		
		User temp = null;
		do{
			try{
				
				System.out.println("==========회원가입==========");
				System.out.println("이름 입력(2-10자 이내의 한글) : ");
				name=sc.next();

				id=error.isExistId(member);
							
				
				System.out.println("비밀번호 입력(8-16자 이내) : ");
				passwrd=sc.next();
				System.out.println("전화번호 입력(형식 => 010-####-####) : ");
				phNum=sc.next();
				System.out.println("이메일 입력(형식 => gusdn0563@naver.com) : ");
				mail=sc.next();
				
				if(name.length()>=2 && name.length()<=10 && id.length()>=4 && id.length()<=16
						&& passwrd.length()>=8 && passwrd.length()<=16 && phNum.matches("01[016789]-(\\d{3,4})-(\\d{4})")
						&& mail.contains("@"))
				{

							
					System.out.println("회원 가입 완료.");
					try{ Thread.sleep(1000);}catch(Exception e){e.printStackTrace();}

										
					break;
					
				}else
					throw new Exception();
				
					
			}catch(Exception e){
				for(int i=0;i<3;i++)
					System.out.println("");
				
				if(name.length()<2 || name.length()>10){
					System.out.println("[ 이 름 ]");
				}
				if(id.length()<4 ||  id.length()>16){
					System.out.println("[ 아 이 디 ]");
				}
				if(passwrd.length()<8 || passwrd.length()>16){
					System.out.println("[ 비 밀 번 호 ]");
				}
				if(!(phNum.matches("01[016789]-(\\d{3,4})-(\\d{4})"))){
					System.out.println("[ 휴대폰 번호 ]");
				}
				if(!mail.contains("@")){
					System.out.println("[ 이 메 일 ]");
				}
				System.out.println("양식에 맞게 입력해주시기 바랍니다.");
				
			}
		}while(true);
		
		user.setMail(mail);
		user.setName(name);
		user.setPass(passwrd);
		user.setPhone(phNum);
		user.setId(id);

		return user;
		
	}
	
	
	public boolean signIn(ArrayList<User> member){
		
		int count=0;
		int success=0;
		
		do{
			try{
				System.out.println("==========로그인==========");
				
				System.out.println("아이디 : ");
				id=sc.next();
				System.out.println("비밀번호 : ");
				passwrd = sc.next();
				
				for(User i : member){
					if(i.getID().compareTo(id)==0 && i.getPass().compareTo(passwrd)==0)
					{
						System.out.println("로그인에 성공하셨습니다.");
						try{ Thread.sleep(1000);}
						catch(Exception e){e.printStackTrace();}
						success=1;
						break;
						
					}
				}
				if(success==1)
					break;
				else
					throw new Exception();

				
			}catch(Exception e){
				count++;
				
				if(count==3){
					System.out.println("로그인 3회로 인해 프로그램 종료!");
					System.exit(0);
				}
				System.out.println("");
				System.out.println("잘못된 아이디 혹은 비밀번호입니다.");
				System.out.println("3회 틀릴 시 프로그램은 종료됩니다. ["+count+"/3]" );
				System.out.println("");	
			}
		}while(true);
		
		return true;
		
	}
	
	public void editUserInfo(ArrayList<User> userList){
		
		User user=new User();
		User temp=new User();
		String name=null,phNum=null,mail=null;
		String passwrd;
		String pasCheck1;
		int i=0;
		
		MainClass main=new MainClass();
		
		
		for(int j=0;j<userList.size();j++)
		{
			if(userList.get(j).getID().compareTo(main.getUser())==0){
				i=j;
				break;
			}			
		}
		
		System.out.print("비밀번호 입력 : ");
		pasCheck1=sc.next();
		
		if(userList.get(i).getPass().compareTo(pasCheck1)==0){
			
			do{
				try{
					System.out.println("================회원 정보 수정================");
					System.out.println("이름 입력(기존 : "+userList.get(i).getName()+" ) : ");
					name=sc.next();
					System.out.println("비밀번호 입력(8-16자 이내) : ");
					passwrd=sc.next();
					System.out.println("전화번호 입력(기존 : "+userList.get(i).getPhone()+" ) : ");
					phNum=sc.next();
					System.out.println("이메일 입력(기존 : "+userList.get(i).getMail()+" ) : ");
					mail=sc.next();
									
					if(name.length()>=2 && name.length()<=10 && passwrd.length()>=8 && passwrd.length()<=16 && phNum.matches("01[016789]-(\\d{3,4})-(\\d{4})")
							&& mail.contains("@"))
					{
						System.out.println("회원 정보 수정 완료.");
						try{ Thread.sleep(1000);}
						catch(Exception e){e.printStackTrace();}		
						break;
						
					}else{
						throw new Exception();
					}
					
				}catch(Exception e)
				{
					System.out.println("입력 양식에 맞게 다시 입력해 주세요!");
				}
				
			}while(true);
			
			userList.get(i).setName(name);
			userList.get(i).setPass(passwrd);
			userList.get(i).setPhone(phNum);
			userList.get(i).setMail(mail);
		}else{
			System.out.println("비밀번호가 올바르지 않습니다!");
			System.out.println("계속 진행하시려면 Enter키를 눌러주시기 바랍니다.");
			ManageBook.pause();
		}
	
		
	}
	
	public void deleteUser(ArrayList<User> userList){
		
		int i=0;
		MainClass main=new MainClass();
		
		for(int j=0;j<userList.size();j++)
		{
			if(userList.get(j).getID().compareTo(main.getUser())==0){
				i=j;
				break;
			}			
		}
		
		
		System.out.println(userList.get(i).getID()+" | "+main.getUser() );
		
		userList.remove(i);
	}
	




}
