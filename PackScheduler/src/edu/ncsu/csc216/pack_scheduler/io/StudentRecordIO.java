package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Input/output class for Student records, inputs a filename that is read to create the student objects
 *
 * @author Christopher Gilbert
 * @author Hinano Turner
 * @author Samir Naseri
 */
public class StudentRecordIO {

	/**
	 * Inputs a filename to be read from for the Student objects to be created from, if those
	 * student objects can be created, they are then added to SortedList of student objects,
	 * "students"
	 *
	 * @param fileName inputed name for file of input
	 * @return a sorted list of student objects
	 * @throws FileNotFoundException for inputed files that are not accessible
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {

		//Create an empty list of Student objects
		SortedList<Student> students = new SortedList<>();
		//Create a file scanner to read the file
		Scanner scnr = new Scanner(new FileInputStream(fileName));

		//While there are more lines, read the line, process it in processStudent, and get the object, then add student
		while (scnr.hasNextLine()) {
			try {
				Student student = processStudent(scnr.nextLine());
				students.add(student);
			} catch (IllegalArgumentException e) {
				//Invalid student object, skipped line.
			}
		}
		scnr.close();
		return students;
	}

	/**
	 * Takes line input from inputed file and converts contents into a student object.
	 *
	 * @param nextLine inputed line from file given
	 * @throws IllegalArgumentException if NoSuchElementException is caught 
	 * @return Student object
	 */
	private static Student processStudent(String nextLine) {
		Scanner scnr = new Scanner(nextLine);
		scnr.useDelimiter(",");
 	   //read in tokens for first name, last name, id, email, password, and max credits in local variables
		try {
			String fName = scnr.next();
			String lName = scnr.next();
			String id = scnr.next();
			String email = scnr.next();
			String pWord = scnr.next();
			int maxCredits = scnr.nextInt();
			Student student = new Student(fName, lName, id, email, pWord, maxCredits);
			scnr.close();
			return student;

		} catch(NoSuchElementException e) {
			scnr.close();
			throw new IllegalArgumentException("invalid format");
		}
	}

	/**
	 * Writes the student objects into an output file
	 *
	 * @param fileName inputed file name for output 
	 * @param studentDirectory SortedList of students
	 * @throws IOException For invalid
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
		try{
			//writes student object to file
			PrintStream output = new PrintStream(new File(fileName));

			for (int i = 0; i < studentDirectory.size(); i++) {
				output.println(studentDirectory.get(i));
			}
			
			output.close();
		} catch (IOException e) {
			throw new IOException(fileName + " (No such file or directory)");
		}
	}
}