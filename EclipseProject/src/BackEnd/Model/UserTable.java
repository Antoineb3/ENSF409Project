package BackEnd.Model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import SharedObjects.User;
import SharedObjects.Student;
import SharedObjects.Professor;

/**
 * This class implements the abstract methods in the Table class for the user table.
 * @author 	Antoine Bizon, Ross Bartlett 
 * @version 1.0
 * @since	2018-03-30
 */
public class UserTable extends Table{

	/**
	 * Constructs an object of class UserTable.
	 * @param execute the Statement executor object.
	 * @param tableName the name of the table.
	 */
	public UserTable(StatementExecutor execute, String tableName) {
		super(execute, tableName);
	}

	/* (non-Javadoc)
	 * @see BackEnd.Model.Table#addToDB(java.io.Serializable)
	 */
	@Override
	public <T extends Serializable>  ArrayList<Integer> addToDB(T addition) {
		User user = (User) addition;
		char userType = derterminUserType(user);
		String update = "INSERT INTO " + tableName +
				" VALUES ( " + IDGenerator.generateID() + ", '" + 
				user.getPassword() + "', '" +
				user.getEmail() + "', '" +
				user.getFirstName() + "', '" +
				user.getLastName() + "', '" +
				userType + "');";
		ArrayList<Integer> result = new ArrayList<Integer>();
		result.add(execute.preformUpdate(update));
		return result;
	}

	/**
	 * Private method to determine what type of user it is before saving it to the database.
	 * @param toInsert the User object being added to the database.
	 * @return 'S' for student, 'P' for prefessor.
	 */
	private char derterminUserType(User toInsert) {
		if(toInsert instanceof Student) {
			return 'S';
		}
		else if(toInsert instanceof Professor) {
			return 'P';
		}
		else {
			System.err.println("Object Type error");
			return 0;
		}
	}

	/* (non-Javadoc)
	 * @see BackEnd.Model.Table#listFromResultSet(java.sql.ResultSet)
	 */
	@Override
	public ArrayList<? extends Serializable> listFromResultSet(ResultSet results) {
		ArrayList<User> users = new ArrayList<User>();
		try {
			while(results.next()) {
				char type = results.getString(6).charAt(0);
				if(type == 'S') {
					users.add(new Student(results.getInt(1), results.getString(2), results.getString(3),
										  results.getString(4), results.getString(5)));
				}
				else if(type == 'P') {
					users.add(new Professor(results.getInt(1),results.getString(2),results.getString(3),
											results.getString(4),results.getString(5)));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	
}
