
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
				"CourseTable");

//
//		System.out.println("Adding ENCM333 to courses");
//		Course encm333 = new Course(-1, 3, "ENCM333", false); //profID 3 is Bartley
//		courseTable.addToDB(encm333);

	}

}
