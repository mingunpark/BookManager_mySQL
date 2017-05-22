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
		
		UserManage login = new UserManage(); // ȸ�� ���� �� �α��� �� ȸ�� ������ �����ϴ� class
		LibraryUI screen = new LibraryUI(); // �α��� ȭ�� �� ����ȭ�� ���� ȭ���� ǥ���ϴ� class
		ManageBook manager=new ManageBook();//�������� ȭ�鿡���� ����
		
		boolean successLogin=false;//true�̸� �α��� ����
		
		DataIO dataIO=new DataIO();
		ArrayList<User> u_inform=dataIO.bringUserInfo();//ȸ�������� ��� ���� Arraylist
		ArrayList<Book> b_inform=dataIO.bringBookInfo();//å������ ��� ���� ArrayList
		
		int loginMenu=0; // �α��� ȭ�鿡���� �޴� ����
		int mainMenu=0;
		
		do{
			loginMenu=screen.printLogin();
			
			if(loginMenu==1)
			{//�α���
				newScreen();
				
				successLogin=login.signIn(u_inform);
			
				while(successLogin){
					nowUser=login.getUser();
					mainMenu=screen.printMenu();
					switch(mainMenu){
					case 1: //�����˻�
						newScreen();
						manager.searchBook(b_inform);
						break;
					case 2: //��ü ���� ���
						newScreen();
						manager.viewAll(b_inform);
						break;
					case 3: //���� �뿩
						newScreen();
						b_inform=manager.borrowBook(b_inform);
						break;
					case 4: //���� �ݳ�
						newScreen();
						b_inform=manager.returnBook(b_inform);
						break;
					case 5: //���� ����
						newScreen();
						b_inform=manager.extendBorrow(b_inform);
						break;
					case 6: //ȸ�� ���� ����
						newScreen();
						login.editUserInfo(u_inform);
						break;
					case 7: //�α׾ƿ�
						newScreen();
						successLogin=false;
						break;
					case 8: //ȸ�� Ż��
						newScreen();
						login.deleteUser(u_inform);
						successLogin=false;
						break;
					case 9: // �ý��� ����
						System.out.println("�ý����� ���� �ϰڽ��ϴ�.");
						dataIO.storeUserInfo(u_inform);
						dataIO.storeBookInfo(b_inform);
						System.exit(0);
					}
	
				}
				
			}
			else if(loginMenu==2)//ȸ������
			{
				newScreen();
				u_inform.add(login.signUp(u_inform));
				
				try{
					Thread.sleep(1000);
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
			else if(loginMenu==3){ //�ý��� ����
				System.out.println("�ý����� ���� �ϰڽ��ϴ�.");
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


