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
		
		System.out.println("계속 진행하시려면 Enter키를 눌러주세요");
		pause();
		
		
	}
	
	public void viewAll(ArrayList<Book> bookList){
		manageScreen.printBookInf(bookList);
		System.out.println("계속 진행하시려면 Enter키를 눌러주세요");
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
					System.out.print("반납하고 싶은 도서 책번호 입력 : ");
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
						System.out.println("반납이 완료 되었습니다.");
						System.out.println("계속하시려면 Enter키를 눌러 주세요.");
						pause();
						break;
					}
					else
						throw new Exception();
					
				}catch(Exception e)
				{
					System.out.println("잘못된 책 번호 입니다.");
				}
			}while(true);
		}else{
			System.out.println("반납 할 책이 없습니다. Enter키를 눌러주세요");
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
		
		SimpleDateFormat fm=new SimpleDateFormat("yyyy년MM월dd일");
		String time;
		Calendar cal;
		
		
		do{
			try{
				
				System.out.print("대출 받고 싶은 도서 책번호 입력 : ");
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
					
					System.out.println("대출 완료 되었습니다.");
					System.out.println("계속하시려면 Enter키를 눌러 주세요.");
					pause();
					break;
				}else{
					throw new Exception();
				}
				
			}catch(Exception e)
			{
				System.out.println("잘못된 책 번호 입니다.");
			}
		}while(true);
		
		return bookList;
	}
	
	public ArrayList<Book> extendBorrow(ArrayList<Book> bookList){
		
		SimpleDateFormat fm=new SimpleDateFormat("yyyy년MM월dd일");
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
					System.out.println("연장은 최대 3회 까지 가능합니다.");
					System.out.print("대출 연장하고 싶은 도서 책번호 입력 : ");
					bookNum=sc.next();
					int count=bookList.get(Integer.parseInt(bookNum)-1).getBorrowInfo().getExtend();
					for(Book i : posReturn){
						if(Integer.parseInt(bookNum)==i.getIndex())
							posCheck=i.getIndex();
							
					}
					if(count<=3 && posCheck!=0){
						System.out.println((count+1)+"번째 연장이 완료 되었습니다.");
						cal=Calendar.getInstance();
						time=fm.format(cal.getTime());
						bookList.get(posCheck-1).getBorrowInfo().setBorrowTime(time);
						bookList.get(posCheck-1).getBorrowInfo().setExtend(count+1);
						System.out.println("계속하시려면 Enter키를 눌러 주세요.");
						pause();
						break;
					}
					else
						throw new Exception();
					
				}catch(Exception e)
				{
					System.out.println("잘못된 책 번호 입니다.");
				}
			}while(true);
		}else{
			System.out.println("연장 할 책이 없습니다. Enter키를 눌러주세요");
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
