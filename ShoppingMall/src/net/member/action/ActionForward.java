package net.member.action;

// View 페이지에 포워딩할 때 필요한 정보를 저장할 수 있는 클래스
public class ActionForward {
	private boolean isRedirect = false;
	private String path = null;
	
	public boolean isRedirect(){
		return isRedirect;
	}
	
	public void setPath(String path){
		this.path = path;
	}
	
	public String getPath(){
		return path;
	}
	
	public void setRedirect(boolean b){
		isRedirect = b;
	}
}
