package BackEnd.Model;

/**
 * This class has an array of tables containing one table object for each table in the database.
 * @author 	Antoine Bizon, Ross Bartlett 
 * @version 1.0
 * @since	2018-04-03
 */
public class DB {
	/**
	 * An array of table objects that correspond to all the tables in the database.
	 */
	private Table [] tables;
	
	/**
	 * Constructs a DB object by construction the table objects for all the tables in the database. 
	 */
	public DB() {
		tables = new Table[6];
		StatementExecutor sharedExecutor = new StatementExecutor("jdbc:mysql://localhost:3306/LearningPlatformDB?useSSL=false", 
				  												"root", "bacon");
		tables[0] = new UserTable(sharedExecutor, "UserTable");
		tables[1] = new CourseTable(sharedExecutor, "CourseTable");
		tables[2] = new StudentEnrollmentTable(sharedExecutor, "StudentEnrollmentTable");
		tables[3] = new AssignmentTable(sharedExecutor, "AssignmentTable");
		tables[4] = new SubmissionTable(sharedExecutor, "SubmissionTable");
		tables[5] = new GradeTable(sharedExecutor, "GradeTable");
		
	}
	
	/**
	 * Returns the table at a given index. 
	 * @param i the index in table array.
	 * @return the table at that index.
	 */
	public Table getTableAt(int i) {
		return tables[i];
	}
}
