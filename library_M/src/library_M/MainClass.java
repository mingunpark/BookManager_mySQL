package library_M;

import java.util.ArrayList;
import java.util.Scanner;

import controller.DataIO;
import controller.ManageBook;
import controller.UserManage;
import models.Book;
import models.User;
import view.LibraryUI;

public class MainClass {
	
	public final static int NEWSPACE = 80;
	private static String nowUser;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		UserManage login = new UserManage(); // 회원 가입 및 로그인 등 회원 정보를 관리하는 class
		LibraryUI screen = new LibraryUI(); // 로그인 화면 및 메인화면 등의 화면을 표현하는 class
		ManageBook manager=new ManageBook();//도서관리 화면에서의 모든것
		
		boolean successLogin=false;//true이면 로그인 성공
		
		DataIO dataIO=new DataIO();
		ArrayList<User> u_inform=dataIO.bringUserInfo();//회원정보를 담아 놓는 Arraylist
		ArrayList<Book> b_inform=dataIO.bringBookInfo();//책정보를 담아 놓는 ArrayList
		
		int loginMenu=0; // 로그인 화면에서의 메뉴 선택
		int mainMenu=0;
		
		do{
			loginMenu=screen.printLogin();
			
			if(loginMenu==1)
			{//로그인
				newScreen();
				
				successLogin=login.signIn(u_inform);
			
				while(successLogin){
					nowUser=login.getUser();
					mainMenu=screen.printMenu();
					switch(mainMenu){
					case 1: //도서검색
						newScreen();
						manager.searchBook(b_inform);
						break;
					case 2: //전체 도서 출력
						newScreen();
						manager.viewAll(b_inform);
						break;
					case 3: //도서 대여
						newScreen();
						b_inform=manager.borrowBook(b_inform);
						break;
					case 4: //도서 반납
						newScreen();
						b_inform=manager.returnBook(b_inform);
						break;
					case 5: //대출 연장
						newScreen();
						b_inform=manager.extendBorrow(b_inform);
						break;
					case 6: //회원 정보 수정
						newScreen();
						login.editUserInfo(u_inform);
						break;
					case 7: //로그아웃
						newScreen();
						successLogin=false;
						break;
					case 8: //회원 탈퇴
						newScreen();
						login.deleteUser(u_inform);
						successLogin=false;
						break;
					case 9: // 시스템 종료
						System.out.println("시스템을 종료 하겠습니다.");
						dataIO.storeUserInfo(u_inform);
						dataIO.storeBookInfo(b_inform);
						System.exit(0);
					}
	
				}
				
			}
			else if(loginMenu==2)//회원가입
			{
				newScreen();
				u_inform.add(login.signUp(u_inform));
				
				try{
					Thread.sleep(1000);
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
			else if(loginMenu==3){ //시스템 종료
				System.out.println("시스템을 종료 하겠습니다.");
				dataIO.storeUserInfo(u_inform);
				dataIO.storeBookInfo(b_inform);
				System.exit(0);
			}
		
		}while(true);
		
	}
	
	public static void newScreen(){
		for(int i=0;i<NEWSPACE;i++){
			System.out.println("");
		}
	}
	
	public String getUser(){
		return nowUser;
	}
	


}


