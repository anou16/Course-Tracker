package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;

/**
 * Tests the FacultyRecordIO class using a valid faculty records file.
 * 
 * @author wangc
 */
public class FacultyRecordIOTest {

	/** Valid faculty records file */
	private static final String VALID_FACULTY_FILE = "test-files/faculty_records.txt";
	/** Invalid faculty records file */
	private static final String INVALID_FACULTY_FILE = "test-files/invalid_faculty_records.txt";
	/** Expected faculty records file */
	private static final String EXPECTED_FACULTY_FILE = "test-files/expected_faculty_records.txt";

	/**
	 * Ensures the test-files directory exists
	 * 
	 * @throws Exception if file is invalid.
	 */
	@Before
	public void setUp() throws Exception {
		File directory = new File("test-files");
		if (!directory.exists()) {
			directory.mkdir();
		}
	}

	/** Tests reading valid faculty records. */
	@Test
	public void testReadValidFacultyRecords() {
		try {
			LinkedList<Faculty> faculties = FacultyRecordIO.readFacultyRecords(VALID_FACULTY_FILE);
			assertEquals(8, faculties.size());

			Faculty f1 = faculties.get(0);
			assertEquals("Ashely", f1.getFirstName());
			assertEquals("Witt", f1.getLastName());
			assertEquals("awitt", f1.getId());
			assertEquals("mollis@Fuscealiquetmagna.net", f1.getEmail());
			assertEquals("MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=", f1.getPassword());
			assertEquals(2, f1.getMaxCourses());

			Faculty f2 = faculties.get(1);
			assertEquals("Fiona", f2.getFirstName());
			assertEquals("Meadows", f2.getLastName());
			assertEquals("fmeadow", f2.getId());
			assertEquals("pharetra.sed@et.org", f2.getEmail());
			assertEquals("MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=", f2.getPassword());
			assertEquals(3, f2.getMaxCourses());

		} catch (IOException e) {
			fail("IOException should not occur while reading valid faculty records.");
		}
	}

	/** Tests reading invalid faculty records. */
	@Test
	public void testReadInvalidFacultyRecords() {
		try {
			LinkedList<Faculty> faculties = FacultyRecordIO.readFacultyRecords(INVALID_FACULTY_FILE);
			assertEquals(0, faculties.size());
		} catch (IOException e) {
			fail("IOException should not occur while reading invalid faculty records.");
		}
	}

	/** Tests writing faculty records. */
	@Test
	public void testWriteFacultyRecords() {
		LinkedList<Faculty> faculties = new LinkedList<>();
		faculties.add(new Faculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net",
				"MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=", 2));
		faculties.add(new Faculty("Fiona", "Meadows", "fmeadow", "pharetra.sed@et.org",
				"MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=", 3));

		try {

			FacultyRecordIO.writeFacultyRecords("test-files/actual_faculty_records.txt", faculties);

			Scanner expectedScanner = new Scanner(new File(EXPECTED_FACULTY_FILE));
			Scanner actualScanner = new Scanner(new File("test-files/actual_faculty_records.txt"));

			while (expectedScanner.hasNextLine() && actualScanner.hasNextLine()) {
				assertEquals(expectedScanner.nextLine(), actualScanner.nextLine());
			}

			expectedScanner.close();
			actualScanner.close();
		} catch (IOException e) {
			fail("IOException should not occur while writing faculty records.");
		}
	}
}
