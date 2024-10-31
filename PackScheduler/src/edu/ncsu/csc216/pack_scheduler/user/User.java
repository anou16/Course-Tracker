package edu.ncsu.csc216.pack_scheduler.user;

import java.util.Objects;


/**
 * Class that creates a user of the program
 * 
 * @author Neha Pothireddy
 * @author Samir
 * @author Micheal
 */
public abstract class User {

	/**
	 * Private string containing students first name
	 */
	private String firstName;
	/**
	 * Private string containing students first name
	 */
	private String lastName;
	/**
	 * ID number for individual students
	 */
	private String id;
	/**
	 * Students individual email assigned
	 */
	private String email;
	/**
	 * Individual students password assignment
	 */
	private String password;
	
	/** Maximum credits a student can have */
	public static final int MAX_CREDITS = 18;

	

	/**
	 * Creates the User object in the constructor
	 * 
	 * @param firstName first name of the user 
	 * @param lastName last name of the user 
	 * @param id id of the user 
	 * @param email email of the user 
	 * @param password password of the user 
	 */
	public User(String firstName, String lastName, String id, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.email = email;
		this.password = password;
	}

	/**
	 * Returns students first name
	 *
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets students first name
	 *
	 * @param firstName the firstName to set
	 * @throws IllegalArgumentException for null or empty strings passed as first name
	 */
	public void setFirstName(String firstName) {
		if(firstName == null || "".equals(firstName)) {
			throw new IllegalArgumentException("Invalid first name");
		}
		this.firstName = firstName;
	}

	/**
	 * Returns students last name
	 *
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets students last name
	 *
	 * @param lastName the lastName to set
	 * @throws IllegalArgumentException for null or empty strings passed as last name
	 */
	public void setLastName(String lastName) {
		if (lastName  == null || "".equals(lastName)) {
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
	}

	/**
	 * Returns the students ID
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the students ID
	 *
	 * @param id the id to set
	 * @throws IllegalArgumentException for null or empty strings passed as id
	 */
	protected void setId(String id) {
		// checks if valid ID
		if (id == null || "".equals(id)) {
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
	}

	/**
	 * Returns the students email address
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the students email address
	 *
	 * @param email the email to set
	 * @throws IllegalArgumentException for null or empty strings passed as email,
	 * string without an "@" or an "." character or where the "." is before the "@"
	 */
	public void setEmail(String email) {
		//checks from proper email format
		if (email == null || "".equals(email) || !email.contains("@") || !email.contains(".")) {
			throw new IllegalArgumentException("Invalid email");
		}
		if (email.indexOf('@') > email.lastIndexOf('.')) {
			throw new IllegalArgumentException("Invalid email");
		}
		this.email = email;
	}

	/**
	 * Returns the students password
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the students password
	 *
	 * @param password the password to set
	 * @throws IllegalArgumentException for null or empty strings passed as password
	 */
	public void setPassword(String password) {
		if (password == null || "".equals(password)) {
			throw new IllegalArgumentException("Invalid password");
		}
		this.password = password;
	}

	
	/**
	 * creates hash 
	 * @return objects 
	 */
	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, id, lastName, password);
	}

	/**
	 * Sees if two different objects are equal to one another. 
	 * 
	 * @param obj - object 
	 * @return object 
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(password, other.password);
	}

	/**
	 * returns comma separated list of student fields.
	 * @return String version of fields for student object
	 */
	@Override
	public String toString() {
		return firstName + "," + lastName + "," + id + "," + email
				+ "," + password;
	}

}