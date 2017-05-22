package models;

public class BorrowInfo {
	
	private String borrower;
	private String borrowTime;
	private int extend;
	
	
	public BorrowInfo(){
		
		borrower=null;
		borrowTime=null;
		extend=0;
	}
	
	public String getBorrower() {
		return borrower;
	}
	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}
	public String getBorrowTime() {
		return borrowTime;
	}
	public void setBorrowTime(String borrowTime) {
		this.borrowTime = borrowTime;
	}
	public int getExtend() {
		return extend;
	}
	public void setExtend(int extend) {
		this.extend = extend;
	}

}
