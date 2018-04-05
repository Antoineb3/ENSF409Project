/**
 * 
 */
package BackEnd.BackEndTesting;

import BackEnd.Model.*;
import SharedObjects.Professor;
import SharedObjects.Student;
import SharedObjects.User;

/**
 * @author Antoine
 *
 */
public class UserCreator {

	public static void main(String[] args) {
		UserTable userTable = new UserTable(new StatementExecutor("jdbc:mysql://localhost:3306/LearningPlatformDB?useSSL=false", 
															  "root", "bacon"), //change the last param to you local password
										"UserTable");
		
		User antoine = new Student(-1, "bacon2", "antoine.bizon1@ucalgary.ca", "Antoine", "Bzon");
		
//		System.out.println("Adding antoine to users");
//		userTable.addToDB(antoine);
//		
//		System.out.println("Searching for Antoine");
//		antoine = (User) userTable.search("FIRSTNAME", "'Antoine'").get(0);
//		System.out.println("found: "+antoine);
//		
//		System.out.println("Editing Antoines name and searching by id");
//		userTable.editRow("LASTNAME", "'Bizon'", "ID", "'"+Integer.toString(antoine.getID())+"'");
//
//		antoine = (User) userTable.search("ID", Integer.toString(antoine.getID())).get(0);
//		System.out.println("search after editing: "+antoine);
//		
		
//		System.out.println("Adding ross to users");
//		User ross = new Student(-1, "rossIsCool", "ross.bartlett@ucalgary.ca", "Ross", "Bartlett");
//		userTable.addToDB(ross);
//		
//		System.out.println("Adding carmen to users");
//		User carmen = new Student(-1, "carmenRocks", "carmen@gmail.com", "Carmen", "Ngo");
//		userTable.addToDB(carmen);
		
//		System.out.println("Adding bartley to users");
//		User bartley = new Professor(-1, "normie", "norm_bartley@gmail.com", "Norm", "Bartley");
//		userTable.addToDB(bartley);
//		
//		System.out.println("Adding moshirpour to users");
//		User moshirpour = new Professor(-1, "moshy", "mmorship@gmail.com", "Mohammad", "Moshirpour");
//		userTable.addToDB(moshirpour);
		
		System.out.println("Adding ENSF409 to courses");
		User moshirpour = new Professor(-1, "moshy", "mmorship@gmail.com", "Mohammad", "Moshirpour");
		userTable.addToDB(moshirpour);
		
	}
}
