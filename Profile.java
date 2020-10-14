/**
 * 
 * @author Anthony Chen, Hoda Moustafa
 *
 */

public class Profile {
	private String fname;
	private String lname;
	
	public Profile(String fname, String lname) {
		this.fname = fname; 
		this.lname = lname; 
	}
	
	/**
	 * @return the fname
	 */
	public String getFname() {
		return fname;
	}
	/**
	 * @param fname the fname to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}
	/**
	 * @return the lname
	 */
	public String getLname() {
		return lname;
	}
	/**
	 * @param lname the lname to set
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}
}
