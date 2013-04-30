package edu.cst438.fourup;
 
import java.util.ArrayList;
import java.util.List;
 
public class AccountObject
{
	private String email = "";
	private String hPassword = "";
	private List<String> searchHistory = new ArrayList<String>();
	
	public AccountObject(String email, String hPassword)
	{
		this.email = email;
		this.hPassword = hPassword;
	}
	
	public String toString()
	{
		return this.email + "\r\n" + this.hPassword;
	}
	//getter and setter methods
	
	public String getEmail()
	{
		return this.email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getHPassword()
	{
		return this.hPassword;
	}
	
	public void setHPassword(String hPassword)
	{
		this.hPassword = hPassword;
	}
	
	public List getSearchHistory()
	{
		return this.searchHistory;
	}
	
	public void setSearchHistory(List searchHistory)
	{
		this.searchHistory = searchHistory;
	}

	public void addSearchItem(String anItem){
		this.searchHistory.add(anItem);
	}
}