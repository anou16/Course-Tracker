//Imports and Packages
package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * This is the Catalog class to implement the functionality of a catalog, using already
 * implemented features in WolfScheduler or StudentDirectory for reference. 
 * 
 * @author Samir Naseri
 * @author Chris Gilbert
 * @author Hinano Turner
 */ 
public class CourseCatalog {

	//State Fields 
	/** A Course Catalog */
	private SortedList<Course> catalog;
	
	/**
	 * This method is the Constructor for the CourseCatalog Class.
	 * This method constructs an empty catalog - SortedList. 
	 */
	public CourseCatalog() {
		catalog = new SortedList<>();
	}
	
	/**
	 * This method essentially just constructs an empty catalog.
	 * Making it a new catalog.
	 */
	public void newCourseCatalog() {
		catalog = new SortedList<Course>();
	}
	
	/**
	 * This method loads in the course records to the catalog. 
	 * Any FileNotFoundExceptions are caught and an IllegalArgumentException is thrown to the client.
	 * 
	 * @param fileName - File to load in the courses from
	 * @throws IllegalArgumentException if the file does not exist 
	 */
	public void loadCoursesFromFile(String fileName) {
		try {
			catalog = CourseRecordIO.readCourseRecords(fileName);
		} catch(FileNotFoundException e) {
			throw new IllegalArgumentException("Cannot find file.");
		}
	}
	
	/**
	 * This method adds a Course to the catalog. 
	 * Returns true if the Course is added and false if the Course already exists in the catalog.
	 * 
	 * @param name - Name of course
	 * @param title - Title of course
	 * @param section - Section of course
	 * @param credits - Credits of course
	 * @param instructorId - Instructor of course
	 * @param meetingDays - Meeting Days of course
	 * @param startTime - Start Time of course
	 * @param endTime - End Time of course
	 * @param enrollmentCap - New course roll cap parameter
	 * @return boolean - True or False
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays, int startTime, int endTime) {
		//Creating empty new course. 
		Course addCourse = null;
		//Try adding new course information. 
		addCourse = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime);
		
		//Checking for duplicates
		for(int i = 0; i < catalog.size(); i++) {
			Course course = catalog.get(i);
			//use equals method
			if (course.getName().equals(name) && course.getSection().equals(section) ) {
				return false;
			}
		}
		catalog.add(addCourse);
		return true; 
	}
	
	/**
	 * This method just removes a course from the catalog. 
	 * Returns true if the Course is removed from the catalog and false if the Course is not in the catalog.
	 * 
	 * @param name - Name of course to remove. 
	 * @param section - Section of course to remove. 
	 * @return boolean - True or False
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		Course course = getCourseFromCatalog(name, section);
		//Making sure it is not null before we try to remove.
		if(course != null) {
			for(int i = 0; i < catalog.size(); i++) {
				if(catalog.get(i).equals(course)) {
					catalog.remove(i);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * This method essentially finds and gets the course we are looking for from the catalog.
	 * It does this by looking for the distinct name and section within the catalog. 
	 * Returns null if the Course is not in the catalog.
	 * 
	 * @param name - Name of course searching for
	 * @param section - Section of course searching for 
	 * @return Course or Null
	 */
	public Course getCourseFromCatalog(String name, String section) {
		// Looking for distinct course 
		for(int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			if (c.getName().equals(name) && c.getSection().equals(section))	{
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Returns the name, section, title, and meeting information for Courses in the catalog.
	 * 
	 * @return Course Catalog 
	 */
	public String[][] getCourseCatalog() {
		String[][] catalogArray = new String[catalog.size()][];
		for (int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			catalogArray[i] = c.getShortDisplayArray();
		}
		return catalogArray;
	}
	
	/**
	 * Saves the catalog course records to the given file.
	 * Any IOExceptions are caught and an IllegalArgumentException is thrown to the client.
	 * 
	 * @param fileName - The file to write to
	 * @throws IllegalArgumentException if any IOExceptions are caught
	 */
	public void saveCourseCatalog(String fileName) {
		try {
			CourseRecordIO.writeCourseRecords(fileName, catalog);
		} catch (IOException e) { 
			throw new IllegalArgumentException("The file cannot be saved.");
		}
	}
	
}
