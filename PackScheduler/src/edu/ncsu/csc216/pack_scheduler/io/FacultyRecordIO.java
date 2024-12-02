package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Input/output class for Faculty records. Supports reading faculty records from
 * a file and writing faculty records to a file. The class uses a LinkedList to
 * store the records.
 * 
 */
public class FacultyRecordIO {

	/**
	 * Reads faculty records from the given file and returns them as a LinkedList of
	 * Faculty objects.
	 * 
	 * @param fileName the name of the file to read from
	 * @return a LinkedList of Faculty objects
	 * @throws FileNotFoundException if the file does not exist
	 */
	public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException {
		LinkedList<Faculty> faculties = new LinkedList<>();
		Scanner fileScanner = new Scanner(new FileInputStream(fileName));

		while (fileScanner.hasNextLine()) {
			try {
				Faculty faculty = processFaculty(fileScanner.nextLine());
				faculties.add(faculty);
			} catch (IllegalArgumentException e) {

			}
		}

		fileScanner.close();
		return faculties;
	}

	/**
	 * Processes a single line of input into a Faculty object.
	 * 
	 * @param nextLine the line to process
	 * @return a Faculty object
	 * @throws IllegalArgumentException if the line format is invalid
	 */
	private static Faculty processFaculty(String nextLine) {
		Scanner lineScanner = new Scanner(nextLine);
		lineScanner.useDelimiter(",");

		try {
			String firstName = lineScanner.next();
			String lastName = lineScanner.next();
			String id = lineScanner.next();
			String email = lineScanner.next();
			String password = lineScanner.next();
			int maxCourses = lineScanner.nextInt();

			Faculty faculty = new Faculty(firstName, lastName, id, email, password, maxCourses);
			lineScanner.close();
			return faculty;

		} catch (NoSuchElementException e) {
			lineScanner.close();
			throw new IllegalArgumentException("Invalid faculty record.");
		}
	}

	/**
	 * Writes the given list of faculty records to the specified file.
	 * 
	 * @param fileName         the name of the file to write to
	 * @param facultyDirectory the LinkedList of Faculty objects to write
	 * @throws IOException if an error occurs while writing to the file
	 */
	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> facultyDirectory) throws IOException {
		try {
			PrintStream output = new PrintStream(new File(fileName));

			for (Faculty faculty : facultyDirectory) {
				output.println(faculty.toString());
			}

			output.close();
		} catch (IOException e) {
			throw new IOException(fileName + " (No such file or directory)");
		}
	}
}
