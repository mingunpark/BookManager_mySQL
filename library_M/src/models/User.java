package models;

public class User {
	
	private String name;
	private String userId;
	private String password;
	private String phoneNum;
	private String mail;
	
	
	public String getName(){
		
		return name;
	}

	public String getID(){
		
		return userId;
	}

	public String getPass(){
		
		return password;
	}

	public String getPhone(){
		
		return phoneNum;
	}

	public String getMail(){
		
		return mail;
	}
	
	public void setName(String name){ this.name=name;}
	public void setId(String id){ this.userId=id;}
	public void setPass(String pass){ this.password=pass;}
	public void setPhone(String phone){ this.phoneNum=phone;}
	public void setMail(String mail){ this.mail=mail;}

	
}
