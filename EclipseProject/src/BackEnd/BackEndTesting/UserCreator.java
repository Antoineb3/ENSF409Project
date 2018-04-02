/**
 * 
 */
package BackEnd.BackEndTesting;

import BackEnd.Model.*;
import SharedObjects.Student;
import SharedObjects.User;

/**
 * @author Antoine
 *
 */
public class UserCreator {

	public static void main(String[] args) {
		UserTable userTable = new UserTable(new StatementExecutor("jdbc:mysql://localhost:3306/LearningPlatformDB?useSSL=false", 
															  "root", "bacon"), 
										"UserTable");
		
		User antoine = new Student(-1, "bacon2", "antoine.bizon1@ucalgary.ca", "Antoine", "Bzon");
		
		System.out.println("Adding antoine to users");
		userTable.addToDB(antoine);
		
		System.out.println("Searching for Antoine");
		antoine = (User) userTable.search("FIRSTNAME", "Antoine").get(0);
		System.out.println(antoine);
		
		System.out.println("Editing Antoines name and searching by id");
		userTable.editRow("LASTNAME", "Bizon", "ID", Integer.toString(antoine.getID()));
		antoine = (User) userTable.search("ID", Integer.toString(antoine.getID())).get(0);
		System.out.println(antoine);
		
		System.out.println("Adding antoine to users");
	}
}
