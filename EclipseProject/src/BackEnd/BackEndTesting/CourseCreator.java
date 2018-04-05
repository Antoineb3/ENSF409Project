
package BackEnd.BackEndTesting;

import BackEnd.Model.*;
import SharedObjects.Course;


/**
 * @author Ross
 *
 */
public class CourseCreator {
	/**
	 * 
	 */
	public static void main(String[] args) {
		CourseTable courseTable = new CourseTable(new StatementExecutor("jdbc:mysql://localhost:3306/LearningPlatformDB?useSSL=false", 
				"root", "bacon"), //change the last param to you local password
				"UserTable");


		System.out.println("Adding ENSF409 to courses");
		Course ensf409 = new Course(-1, 3, "ENSF409", false); //profID 3 is moshirpour
		courseTable.addToDB(ensf409);

	}

}
