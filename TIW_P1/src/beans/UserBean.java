package beans;

public class UserBean implements java.io.Serializable{

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String email;
	  
	  private String pass;
	  
	  private String name;
	  
	  private String surname1;
	  
	  private String surname2;
	  
	  private String city;

	  public UserBean(){
		  
	  }
	  
	public UserBean(String email, String pass, String name, String surname1, String surname2, String city) {
		this.email = email;
		this.pass = pass;
		this.name = name;
		this.surname1 = surname1;
		this.surname2 = surname2;
		this.city = city;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surname1
	 */
	public String getSurname1() {
		return surname1;
	}

	/**
	 * @param surname1 the surname1 to set
	 */
	public void setSurname1(String surname1) {
		this.surname1 = surname1;
	}

	/**
	 * @return the surname2
	 */
	public String getSurname2() {
		return surname2;
	}

	/**
	 * @param surname2 the surname2 to set
	 */
	public void setSurname2(String surname2) {
		this.surname2 = surname2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}	  


	  
}