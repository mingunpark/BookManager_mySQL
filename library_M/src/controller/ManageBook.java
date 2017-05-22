package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import library_M.MainClass;
import models.Book;
import view.LibraryUI;

public class ManageBook {
	
	public ArrayList<Book> bookList;
	public LibraryUI manageScreen;
	Scanner sc;
	int index,price,page,borrowed;
	String title,author,borrower;
	
	
	public ManageBook(){
		
		manageScreen=new LibraryUI();
		sc=new Scanner(System.in);
		
			
	}		

	
	public void searchBook(ArrayList<Book> bookList){
		
		ArrayList<Book> temp=new ArrayList<Book>();
		
		temp=manageScreen.printSearch(bookList);
		manageScreen.printBookInf(temp);
		
		System.out.println("��� �����Ͻ÷��� EnterŰ�� �����ּ���");
		pause();
		
		
	}
	
	public void viewAll(ArrayList<Book> bookList){
		manageScreen.printBookInf(bookList);
		System.out.println("��� �����Ͻ÷��� EnterŰ�� �����ּ���");
		pause();
	}
	
	public ArrayList<Book> returnBook(ArrayList<Book> bookList){
		
		
		ArrayList<Book> posReturn=new ArrayList<Book>();
		String bookNum;
		int checkcount=0;
		int posCheck=0;
		MainClass main=new MainClass();
	
		
		for(Book i : bookList){
			if(i.getBorrow()==1 && i.getBorrowInfo().getBorrower()==main.getUser()){
				posReturn.add(i);
				checkcount++;
			}	
		}
		manageScreen.printBorrowInf(posReturn);
		

		
		if(checkcount!=0){
			do{
				try{
					System.out.print("�ݳ��ϰ� ���� ���� å��ȣ �Է� : ");
					bookNum=sc.next();
					
					
					for(Book i : posReturn){
						if(Integer.parseInt(bookNum)==i.getIndex())
							posCheck=i.getIndex();
							
					}
					if(bookList.get(posCheck-1).getBorrow()==1){
						bookList.get(posCheck-1).setBorrow(0);
						bookList.get(posCheck-1).getBorrowInfo().setBorrower(null);
						bookList.get(posCheck-1).getBorrowInfo().setBorrowTime(null);
						bookList.get(posCheck-1).getBorrowInfo().setExtend(0);
						System.out.println("�ݳ��� �Ϸ� �Ǿ����ϴ�.");
						System.out.println("����Ͻ÷��� EnterŰ�� ���� �ּ���.");
						pause();
						break;
					}
					else
						throw new Exception();
					
				}catch(Exception e)
				{
					System.out.println("�߸��� å ��ȣ �Դϴ�.");
				}
			}while(true);
		}else{
			System.out.println("�ݳ� �� å�� �����ϴ�. EnterŰ�� �����ּ���");
			pause();
		}
		
		
		return bookList;
		
		
	}
	
	public ArrayList<Book> borrowBook(ArrayList<Book> bookList){

		ArrayList<Book> posBorrow=new ArrayList<Book>();
		String bookNum;
		MainClass main=new MainClass();
		ArrayList<Book> temp=new ArrayList<Book>();
		int posCheck=0;
		temp=manageScreen.printSearch(bookList);
		
		for(Book i : bookList){
			for(Book j : temp){
				if(i.getBorrow()==0 && i.getTitle().compareTo(j.getTitle())==0){
					posBorrow.add(i);
					
				}	
			}
		}
		manageScreen.printBookInf(posBorrow);
		
		SimpleDateFormat fm=new SimpleDateFormat("yyyy��MM��dd��");
		String time;
		Calendar cal;
		
		
		do{
			try{
				
				System.out.print("���� �ް� ���� ���� å��ȣ �Է� : ");
				bookNum=sc.next();
				
				for(Book i : posBorrow){
					if(Integer.parseInt(bookNum)==i.getIndex())
						posCheck=i.getIndex();
						
				}
				if(bookList.get(posCheck-1).getBorrow()==0){
					cal=Calendar.getInstance();
					time=fm.format(cal.getTime());
					bookList.get(posCheck-1).getBorrowInfo().setBorrowTime(time);
					bookList.get(posCheck-1).setBorrow(1);
					bookList.get(posCheck-1).getBorrowInfo().setBorrower(main.getUser());
					
					System.out.println("���� �Ϸ� �Ǿ����ϴ�.");
					System.out.println("����Ͻ÷��� EnterŰ�� ���� �ּ���.");
					pause();
					break;
				}else{
					throw new Exception();
				}
				
			}catch(Exception e)
			{
				System.out.println("�߸��� å ��ȣ �Դϴ�.");
			}
		}while(true);
		
		return bookList;
	}
	
	public ArrayList<Book> extendBorrow(ArrayList<Book> bookList){
		
		SimpleDateFormat fm=new SimpleDateFormat("yyyy��MM��dd��");
		String time;
		Calendar cal;
		ArrayList<Book> posReturn=new ArrayList<Book>();
		String bookNum;
		int checkcount=0;
		int posCheck=0;
		MainClass main=new MainClass();
	
		
		for(Book i : bookList){
			if(i.getBorrow()==1 && i.getBorrowInfo().getBorrower()==main.getUser()){
				posReturn.add(i);
				checkcount++;
			}	
		}
		
		manageScreen.printBorrowInf(posReturn);
		
		if(checkcount!=0){
			do{
				try{
					System.out.println("������ �ִ� 3ȸ ���� �����մϴ�.");
					System.out.print("���� �����ϰ� ���� ���� å��ȣ �Է� : ");
					bookNum=sc.next();
					int count=bookList.get(Integer.parseInt(bookNum)-1).getBorrowInfo().getExtend();
					for(Book i : posReturn){
						if(Integer.parseInt(bookNum)==i.getIndex())
							posCheck=i.getIndex();
							
					}
					if(count<=3 && posCheck!=0){
						System.out.println((count+1)+"��° ������ �Ϸ� �Ǿ����ϴ�.");
						cal=Calendar.getInstance();
						time=fm.format(cal.getTime());
						bookList.get(posCheck-1).getBorrowInfo().setBorrowTime(time);
						bookList.get(posCheck-1).getBorrowInfo().setExtend(count+1);
						System.out.println("����Ͻ÷��� EnterŰ�� ���� �ּ���.");
						pause();
						break;
					}
					else
						throw new Exception();
					
				}catch(Exception e)
				{
					System.out.println("�߸��� å ��ȣ �Դϴ�.");
				}
			}while(true);
		}else{
			System.out.println("���� �� å�� �����ϴ�. EnterŰ�� �����ּ���");
			pause();
			
		}
		
		return bookList;
				
	}
	
	static void pause() {
		// TODO Auto-generated method stub
		
		try{
			System.in.read();
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	

}
