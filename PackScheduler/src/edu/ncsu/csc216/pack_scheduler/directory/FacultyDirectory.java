package edu.ncsu.csc216.pack_scheduler.directory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.LinkedList;

import edu.ncsu.csc216.pack_scheduler.io.FacultyRecordIO;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;

/**
 * Maintains a directory of Faculty for the university.
 * Provides functionality to add, remove, load, and save faculty records.
 * 
 * This class supports use cases CU18, UC19, UC21, and UC22.
 * 
 */
public class FacultyDirectory {

    /** The hashing algorithm for passwords */
    private static final String HASH_ALGORITHM = "SHA-256";
    /** List of Faculty in the directory */
    private LinkedList<Faculty> facultyDirectory;

    /**
     * Constructs an empty FacultyDirectory.
     * Initializes the facultyDirectory by calling newFacultyDirectory().
     * @author wangc
     */
    public FacultyDirectory() {
        newFacultyDirectory();
    }

    /**
     * Creates a new, empty FacultyDirectory by reinitializing the facultyDirectory
     * field to a new LinkedList of Faculty. This method supports [CU18].
     */
    public void newFacultyDirectory() {
        facultyDirectory = new LinkedList<>();
    }

    /**
     * Loads faculty records from the specified file into the FacultyDirectory.
     * This method supports [UC19].
     * 
     * @param fileName the name of the file containing faculty records
     * @throws IllegalArgumentException if the file cannot be read
     */
    public void loadFacultyFromFile(String fileName) {
        try {
            facultyDirectory = FacultyRecordIO.readFacultyRecords(fileName);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Unable to read file " + fileName);
        }
    }

    /**
     * Adds a Faculty member to the directory. This method supports [UC21].
     * 
     * @param firstName the faculty's first name
     * @param lastName the faculty's last name
     * @param id the faculty's unique ID
     * @param email the faculty's email
     * @param password the faculty's password
     * @param repeatPassword the faculty's repeated password
     * @param maxCourses the maximum number of courses the faculty can teach
     * @return true if the faculty is added, false if the ID is not unique
     * @throws IllegalArgumentException for invalid input values
     */
    public boolean addFaculty(String firstName, String lastName, String id, String email, String password,
            String repeatPassword, int maxCourses) {
        if (password == null || repeatPassword == null || "".equals(password) || "".equals(repeatPassword)) {
            throw new IllegalArgumentException("Invalid password");
        }
        if (!password.equals(repeatPassword)) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        String hashPW = hashString(password);

        Faculty faculty;
        try {
            faculty = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        for (Faculty f : facultyDirectory) {
            if (f.getId().equals(faculty.getId())) {
                return false; 
            }
        }

        facultyDirectory.add(faculty);
        return true;
    }

    /**
     * Removes a faculty member from the directory. This method supports [UC22].
     * 
     * @param id the ID of the faculty to remove
     * @return true if the faculty is removed, false otherwise
     */
    public boolean removeFaculty(String id) {
        for (int i = 0; i < facultyDirectory.size(); i++) {
            if (facultyDirectory.get(i).getId().equals(id)) {
                facultyDirectory.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a 2D array containing the faculty's first name, last name, and ID.
     * This method supports the display of the Faculty Directory in the GUI.
     * 
     * @return a 2D array of faculty information
     */
    public String[][] getFacultyDirectory() {
        String[][] directory = new String[facultyDirectory.size()][3];
        for (int i = 0; i < facultyDirectory.size(); i++) {
            Faculty f = facultyDirectory.get(i);
            directory[i][0] = f.getFirstName();
            directory[i][1] = f.getLastName();
            directory[i][2] = f.getId();
        }
        return directory;
    }

    /**
     * Saves the faculty directory to the specified file.
     * This method supports [UC20].
     * 
     * @param fileName the name of the file to save to
     * @throws IllegalArgumentException if an error occurs while writing to the file
     */
    public void saveFacultyDirectory(String fileName) {
        try {
            FacultyRecordIO.writeFacultyRecords(fileName, facultyDirectory);
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to write to file " + fileName);
        }
    }

    /**
     * Hashes a string using SHA-256 and Base64 encoding.
     * 
     * @param password the string to hash
     * @return the hashed string
     * @throws IllegalArgumentException if the password cannot be hashed
     */
    private String hashString(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
            digest.update(password.getBytes());
            return Base64.getEncoder().encodeToString(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("Cannot hash password");
        }
    }
}
