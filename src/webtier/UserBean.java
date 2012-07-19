package webtier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dbtier.ConnectionManager;

public class UserBean {
	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private int idPopolazione;
	private String popolazione;
	
	public boolean valid;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String newFirstName) {
		firstName = newFirstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String newLastName) {
		lastName = newLastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String newPassword) {
		password = newPassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUserName(String newUsername) {
		username = newUsername;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean newValid) {
		valid = newValid;
	}


	public String getPopolazione() {
		return popolazione;
	}

	public void setPopolazione(String popolazione) {
		System.out.println("Settata popolazione a "+popolazione);
		this.popolazione = popolazione;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPopolazione(int idNuovaPopolazione) {
		// TODO Auto-generated method stub
		Connection cm=ConnectionManager.getConnection();
		Statement stmt;
		try {
			stmt = cm.createStatement();
			ResultSet rs = stmt.executeQuery("select descrizione from popolazione where id="+idNuovaPopolazione+";");
		boolean more = rs.next();
		// if user does not exist set the isValid variable to false
		if (more) {
			this.idPopolazione=idNuovaPopolazione;
			this.setPopolazione(rs.getString("descrizione"));
		} else {
			System.out
			.println("Sorry, popolazione non registrata "+idNuovaPopolazione);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public int getIdPopolazione() {
		return idPopolazione;
	}

}