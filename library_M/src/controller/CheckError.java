package controller;

import java.util.ArrayList;
import java.util.Scanner;

import models.User;

public class CheckError {
	
	private final int ID_TRUE = 1;
	private final int ID_FALSE = 0;
	
	private Scanner sc=new Scanner(System.in);
	
	
	
	public String isExistId(ArrayList<User> member){
		
		String id=null;
		int check=0;
		
		
		while(check!=ID_TRUE){
			System.out.println("아이디 입력(4-16자 이내) : ");
			id=sc.next();
			for(User u : member){
				if(u.getID().compareTo(id)==0){
					System.out.println("이미 존재하는 아이디 입니다!");
					check=ID_FALSE;
					break;
				}
				else{
					check=ID_TRUE;
					continue;
				}
				
			}
			if(check==ID_FALSE){
				System.out.println("형식에 맞게 다시 입력해주세요.");
			}
			
		}
		
		return id;
		
	}

}
