package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests FacultyDirectory. Verifies functionality for adding, removing, saving,
 * and loading faculty records. Also includes tests for invalid inputs and
 * duplicate IDs.
 * 
 * @author wangc
 */
public class FacultyDirectoryTest {

	/** Valid faculty records file */
	private final String validTestFile = "test-files/faculty_records.txt";
	/** Expected full faculty records file */
	private final String expectedFullFile = "test-files/expected_full_faculty_records.txt";
	/** Test file for saving faculty records */
	private final String testFile = "test-files/test_faculty_records.txt";
	/** Test first name */
	private static final String FIRST_NAME = "John";
	/** Test last name */
	private static final String LAST_NAME = "Doe";
	/** Test ID */
	private static final String ID = "jdoe";
	/** Test email */
	private static final String EMAIL = "jdoe@ncsu.edu";
	/** Test password */
	private static final String PASSWORD = "password";
	/** Test max courses */
	private static final int MAX_COURSES = 2;
	/** Instance of FacultyDirectory for testing. */
	private FacultyDirectory directory;

	/**
	 * Resets faculty_records.txt for use in other tests.
	 *
	 * @throws Exception if something fails during setup
	 */
	@BeforeEach
	public void setUp() throws Exception {
		directory = new FacultyDirectory();

		Path sourcePath = FileSystems.getDefault().getPath("test-files", "faculty_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "test_faculty_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset test files.");
		}
	}

	/**
	 * Tests FacultyDirectory().
	 */
	@Test
	public void testFacultyDirectory() {
		assertFalse(directory.removeFaculty("jdoe"));
		assertEquals(0, directory.getFacultyDirectory().length);
	}

	/**
	 * Tests FacultyDirectory.newFacultyDirectory().
	 */
	@Test
	public void testNewFacultyDirectory() {
		directory.loadFacultyFromFile(validTestFile);
		assertEquals(8, directory.getFacultyDirectory().length);

		directory.newFacultyDirectory();
		assertEquals(0, directory.getFacultyDirectory().length);
	}

	/**
	 * Tests FacultyDirectory.loadFacultyFromFile().
	 */
	@Test
	public void testLoadFacultyFromFile() {
		directory.loadFacultyFromFile(validTestFile);

		String[][] facultyDirectory = directory.getFacultyDirectory();
		assertEquals(8, facultyDirectory.length);

		// Verify the first record
		assertEquals("Ashely", facultyDirectory[0][0]);
		assertEquals("Witt", facultyDirectory[0][1]);
		assertEquals("awitt", facultyDirectory[0][2]);
	}

	/**
	 * Tests FacultyDirectory.addFaculty().
	 */
	@Test
	public void testAddFaculty() {
		directory.newFacultyDirectory();

		assertTrue(directory.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES));
		assertEquals(1, directory.getFacultyDirectory().length);

		// Test duplicate ID
		assertFalse(directory.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES));
		assertEquals(1, directory.getFacultyDirectory().length);

		// Test invalid max courses
		try {
			directory.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, -1);
			fail("Expected IllegalArgumentException for invalid maxCourses.");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid maxCourses", e.getMessage());
		}
	}

	/**
	 * Tests FacultyDirectory.removeFaculty().
	 */
	@Test
	public void testRemoveFaculty() {
		directory.loadFacultyFromFile(validTestFile);
		assertEquals(8, directory.getFacultyDirectory().length);

		assertTrue(directory.removeFaculty("awitt"));
		assertEquals(7, directory.getFacultyDirectory().length);

		assertFalse(directory.removeFaculty("nonexistent"));
	}

	/**
	 * Tests FacultyDirectory.saveFacultyDirectory().
	 */
	@Test
	public void testSaveFacultyDirectory() {
		directory.loadFacultyFromFile(validTestFile);
		assertEquals(8, directory.getFacultyDirectory().length);

		directory.saveFacultyDirectory(testFile);
		checkFiles(expectedFullFile, testFile);
	}

	/**
	 * Tests FacultyDirectory.getFacultyDirectory().
	 */
	@Test
	public void testGetFacultyDirectory() {
		directory.loadFacultyFromFile(validTestFile);
		String[][] facultyDirectory = directory.getFacultyDirectory();

		assertEquals(8, facultyDirectory.length);
		assertEquals("Ashely", facultyDirectory[0][0]);
		assertEquals("Witt", facultyDirectory[0][1]);
		assertEquals("awitt", facultyDirectory[0][2]);
	}

	/**
	 * Helper method to compare two files for the same contents.
	 *
	 * @param expFile the expected file
	 * @param actFile the actual file
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new FileInputStream(expFile));
				Scanner actScanner = new Scanner(new FileInputStream(actFile))) {

			while (expScanner.hasNextLine()) {
				assertTrue("Actual file has fewer lines than expected.", actScanner.hasNextLine());
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}

			assertFalse("Actual file has more lines than expected.", actScanner.hasNextLine());
		} catch (IOException e) {
			fail("Error reading files: " + e.getMessage());
		}
	}
}
