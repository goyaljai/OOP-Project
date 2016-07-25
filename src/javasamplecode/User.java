package javasamplecode;


public class User {

	private String FNAME;
	private String LNAME;
	private String GENDER;
	private String PASS;
	private int UID;
	private int FOLLOW_COUNT;
	private String PROFILEPICTURE;
	private String USERNAME;

	public String getUSERNAME() {
		return USERNAME;
	}

	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}

	public String getFNAME()
	{
		return FNAME;
	}
	
	public void setFNAME(String FNAME) 
	{
		this.FNAME = FNAME;
	}
	
	public String getLNAME()
	{
		return LNAME;
	}
	
	public void setLNAME(String LNAME)
	{
		this.LNAME = LNAME;
	}
	
	public String getGENDER() 
	{
		return GENDER;
	}
	
	public void setGENDER(String GENDER) 
	{
		this.GENDER =GENDER;
	}
	
	public String getPASS() 
	{
		return PASS;
	}
	
	public void setPASS(String PASS) 
	{
		this.PASS = PASS;
	}
	
	public int getUID()
	{
		return UID;
	}
	
	public void setUID(int UID) 
	{
		this.UID = UID;
	}
	
	public int getFOLLOW_COUNT() 
	{
		return FOLLOW_COUNT;
	}
	
	public void setFOLLOW_COUNT(int FOLLOW_COUNT) 
	{
		this.FOLLOW_COUNT = FOLLOW_COUNT;
	}
	
	public String getPROFILEPICTURE() 
	{
		return PROFILEPICTURE;
	}
	
	public void setPROFILEPICTURE(String PROFILEPICTURE) 
	{
		this.PROFILEPICTURE = PROFILEPICTURE;
	}
	
}

