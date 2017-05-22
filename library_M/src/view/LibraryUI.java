package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import controller.ManageBook;
import models.Book;

public class LibraryUI {

	private final int NEWSPACE = 80;
	public String mode;
	private Scanner sc;
	
	public LibraryUI(){
		mode=null;
		sc = new Scanner(System.in);
		
	}
	
	public int printLogin(){
		
		do{
			try{
				System.out.println("==========초기화면==========");
				System.out.println("");
				System.out.println("'로그인' 또는 '계정 만들기'");
				System.out.println("");
				System.out.println("1 : Sign in   2 : Sign up   3 : Exit");
				System.out.print("(숫자만 입력) = > ");
				mode=sc.next();
				for(int i=0;i<3;i++)
					System.out.println("");
				
				if(Integer.valueOf(mode)<=3 && Integer.valueOf(mode)>=1)
					break;
				else
					throw new Exception();
			}catch(Exception e){
				System.out.println("잘못된 입력입니다!");
			}				
		}while(true);
		
		return Integer.valueOf(mode);
		
	}
	
	public int printMenu(){
		
		do{
			try{
				System.out.println("================사용자 메뉴===================");
				System.out.println("=             1. 도서검색                        =");
				System.out.println("=             2. 전체 도서 출력                  =");
				System.out.println("=             3. 도서 대여                       =");
				System.out.println("=             4. 도서 반납                       =");
				System.out.println("=             5. 대출 연장                       =");
				System.out.println("=             6. 회원 정보 수정                  =");
				System.out.println("=             7. 로그아웃                        =");
				System.out.println("=             8. 회원 탈퇴                       =");
				System.out.println("=             9. 사용 종료                       =");
				System.out.println("============================================");
				System.out.println("");
				System.out.print("원하시는 메뉴를 선택해주세요(숫자만 입력) : ");
				mode = sc.next();
				
				if(Integer.valueOf(mode)<=9 && Integer.valueOf(mode)>=1)
					break;
				else
					throw new Exception();
			}catch(Exception e){
				for(int i=0;i<NEWSPACE;i++){
					System.out.println("");
				}
				System.out.println("잘못된 입력입니다!");
			}
		}while(true);
		
		return Integer.valueOf(mode);
		
	}
	
	public int printBookSearch(){
		do{
			try{
				System.out.println("================도서 검색================");
				System.out.println("");
				System.out.println("검색 조건(입력한 단어를 포함하는 도서 출력)");
				System.out.println("1. 책 제목 ");
				System.out.println("2. 저자 ");
				System.out.println("3. 가격대 ");
				System.out.println("4. 뒤로가기 ");
				System.out.println("");
				System.out.print("원하는 방법 선택(숫자만 입력) : ");
				mode = sc.next();
				
				if(Integer.valueOf(mode)<=4 && Integer.valueOf(mode)>=1)
					break;
				else
					throw new Exception();
			}catch(Exception e){
				System.out.println("");
				System.out.println("잘못된 입력입니다!");
				System.out.println("");
				
			}
		}while(true);
		
		return Integer.valueOf(mode);
	}
	
	
	public void printBookInf(ArrayList<Book> book){
		
		for(Book i : book){
			System.out.println("------------------------------------------------------");
			System.out.println("- 책 번호 "+i.getIndex());
			System.out.println("");
			System.out.println("책 제목 : "+i.getTitle());
			System.out.println("저자 : "+i.getAuthor()+" | 책 가격 : "+i.getPrice()+" | 페이지 수 : "+i.getPage()+" |");
			if(i.getBorrow()==0){
				System.out.println("(O) 대출 가능 도서");
			}else if(i.getBorrow()==1){
				System.out.println(i.getBorrowInfo().getBorrower()+"님이 대출 중");
				System.out.println("(X) 대출 불가능 도서 ");
			}
		}
		
		System.out.println("리스트 마지막입니다.");
		
	}
	
	
	public ArrayList<Book> printSearch(ArrayList<Book> bookList){
		int mode=this.printBookSearch();
		String name, auth;
		
		ManageBook manage=new ManageBook();
		ArrayList<Book> temp=new ArrayList<Book>();
		ArrayList<Book> book=bookList;
		
		
		int chPrice=0;
		String limitChoice=null;
		
		switch(mode){
		case 1:
		{
			System.out.print("책 제목 입력 : ");
			name=sc.next();
			
			System.out.println("================도서 리스트================");
			for(int i=0;i<book.size();i++){
				if(book.get(i).getTitle().contains(name)){
					temp.add(book.get(i));
				}
			}
			break;
		}
		case 2:
		{
			System.out.print("책 저자 입력 : ");
			auth=sc.next();
			System.out.println("================도서 리스트================");
			
			for(int i=0;i<book.size();i++){
				if(book.get(i).getAuthor().contains(auth)){
					temp.add(book.get(i));
				}
			}
			break;
		}
		case 3:
		{
			do{
				try{
					System.out.println("1 : 하한선 설정   2 : 상한선 설정 ");
					System.out.print("=> ");
					limitChoice=sc.next();
					
					if(Integer.parseInt(limitChoice)==1 || Integer.parseInt(limitChoice)== 2)
						break;
					else
						throw new Exception();
				}catch(Exception e)
				{
					System.out.println("다시 입력해주시기 바랍니다");
					System.out.println("");
				}
			}while(true);
			
			System.out.print("금액 입력 : ");
			chPrice=sc.nextInt();
			System.out.println("");
			System.out.println("================도서 리스트================");
			if(Integer.parseInt(limitChoice)==1){
				for(int i=0;i<book.size();i++){
					if(book.get(i).getPrice()>=chPrice){
						temp.add(book.get(i));
					}
				}
			}else if(Integer.parseInt(limitChoice)==2){
				for(int i=0;i<book.size();i++){
					if(book.get(i).getPrice()<=chPrice){
						temp.add(book.get(i));
					}
				}
				
			}	
			break;
		}
		case 4:
		{
			break;
		}
		
		}
		
		return temp;
	}
	
	
	public void printBorrowInf(ArrayList<Book> book){
		
		SimpleDateFormat fm=new SimpleDateFormat("yyyy년MM월dd일");
		String borrowTime;
		String returnTime;
		Date returnD=null;
		for(Book i : book){
			System.out.println("------------------------------------------------------");
			System.out.println("- 책 번호 "+i.getIndex());
			System.out.println("");
			System.out.println("책 제목 : "+i.getTitle());
			System.out.println("대여 날짜 : "+i.getBorrowInfo().getBorrowTime());
			try {
				returnD=fm.parse(i.getBorrowInfo().getBorrowTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Calendar returnC=Calendar.getInstance();
			returnC.setTime(returnD);
			returnC.add(Calendar.DATE, 7);
			returnTime=fm.format(returnC.getTime());
			System.out.println("반납 기한 : "+returnTime);
			System.out.println("연장 횟수 : "+i.getBorrowInfo().getExtend());

		}
		
		
	}
	
}
