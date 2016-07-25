package javasamplecode;

import java.sql.Blob;

public class images implements Comparable{
	private String IMAGE;
	private int IMAGE_ID;
	private String CAPTION;
	private int LIKE_COUNT;
	private int VIEW_COUNT;
	private String ACCESS_USERS;
	private int UID;
	private Blob aBlob;
	public Blob getaBlob() {
		return aBlob;
	}

	
	public void setaBlob(Blob aBlob) {
		this.aBlob = aBlob;
	}

	//private Blob aBlob;
	public String getIMAGE()
	{
		return IMAGE;
	}
	
	public void setIMAGE(String IMAGE)
	{
		this.IMAGE=IMAGE;
	}
	
	public int getIMAGE_ID() 
	{
		return IMAGE_ID;
	}
	
	public void setIMAGE_ID(int IMAGE_ID) 
	{
		this.IMAGE_ID = IMAGE_ID;
	}
	
	public String getCAPTION() 
	{
		return CAPTION;
	}
	
	public void setCAPTION(String CAPTION)
	{
		this.CAPTION = CAPTION;
	}
	
	public int getLIKE_COUNT() 
	{
		return LIKE_COUNT;
	}
	
	public void setLIKE_COUNT(int LIKE_COUNT) 
	{
		this.LIKE_COUNT = LIKE_COUNT;
	}
	
	public int getVIEW_COUNT()
	{
		return VIEW_COUNT;
	}
	
	public void setVIEW_COUNT(int VIEW_COUNT)
	{
		this.VIEW_COUNT = VIEW_COUNT;
	}
	
	public String getACCESS_USERS()
	{
		return ACCESS_USERS;
	}
	
	public void setACCESS_USERS(String ACCESS_USERS) 
	{
		this.ACCESS_USERS = ACCESS_USERS;
	}
	public int getUID() 
	{
		return UID;
	}
	
	public void setUID(int UID) 
	{
		this.UID = UID;
	}


	@Override
	public int compareTo(Object o) {
			images other = (images)o;
			if (this == other)
				return 0;
			if (this.IMAGE_ID < other.IMAGE_ID) return 1;
			else if (this.IMAGE_ID == other.IMAGE_ID) return 0;
			else return -1;
	}	
}
