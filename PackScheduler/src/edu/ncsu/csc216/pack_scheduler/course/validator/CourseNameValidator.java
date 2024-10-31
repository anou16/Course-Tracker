package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Class that validates a course name using a finite state machine (FSM).
 * The course name must follow the format of 1-4 letters, followed by exactly 3 digits,
 * and optionally a single letter suffix. This class uses the State pattern for FSM transitions.
 * 
 * It validates input based on states and transitions for letters and digits,
 * and throws an exception for invalid transitions or inputs.
 * @author Michael Wang
 * @author Neha Pothireddy
 */
public class CourseNameValidator {

    /** The number of letters encountered */
    private int letterCount;
    
    /** The number of digits encountered */
    private int digitCount;
    
    /** Boolean if the state is valid or not*/
    private boolean validState;
    
    /** The current state of the FSM */
    private State currentState;

    /**
     * Constructor for CourseNameValidator. Initializes the FSM in the InitialState.
     */
    public CourseNameValidator() {
        this.currentState = new InitialState(); 
        this.validState = false;
    }

    /**
	 * Validates the given course name by processing each character and transitioning 
	 * through the FSM states.
	 * 
	 * @param courseName the name of the course to validate
	 * @return true if the course name is valid, false otherwise
	 * @throws InvalidTransitionException if an invalid transition or character is encountered
	 */
	public boolean isValid(String courseName) throws InvalidTransitionException {
	    letterCount = 0;
	    digitCount = 0;
	    currentState = new InitialState(); 
	    
	   // System.out.println("course: " + courseName);
	    
	    for (int i = 0; i < courseName.length(); i++) {
	        char c = courseName.charAt(i);
	        if (Character.isLetter(c)) {
	            currentState.onLetter();
	            //System.out.println("in letter: " + digitCount);
	        } else if (Character.isDigit(c)) {
	            currentState.onDigit();
	            //System.out.println("in number: " + digitCount);
	        } else {
	            currentState.onOther(); 
	        }
	    }
	
//	    System.out.println(digitCount);
//	    System.out.println(digitCount == 3);
//	    System.out.println();
	    
	    return validState;
	}

	/**
     * Abstract State class for handling transitions. Contains methods to handle
     * letter, digit, and other types of inputs.
     */
    private abstract class State {
        /**
         * Handles a letter input and transitions the FSM accordingly.
         * 
         * @throws InvalidTransitionException if the transition is invalid
         */
        public abstract void onLetter() throws InvalidTransitionException;
        
        /**
         * Handles a digit input and transitions the FSM accordingly.
         * 
         * @throws InvalidTransitionException if the transition is invalid
         */
        public abstract void onDigit() throws InvalidTransitionException;
        
        /**
         * Handles any input that is not a letter or digit.
         * 
         * @throws InvalidTransitionException if an invalid character is encountered
         */
        public void onOther() throws InvalidTransitionException {
            throw new InvalidTransitionException("Course name can only contain letters and digits.");
        }
    }

    /**
     * Initial state. This state expects the first input to be a letter.
     */
    private class InitialState extends State {
    	/**
    	 * if it is on letter, increases the letterCount and makes the state letter
    	 */
        @Override
        public void onLetter() {
            letterCount++;
            currentState = new LetterState(); 
        }
        
        /**
         * if the starting character is a digit, it throws an exception
         * 
         * @throws InvalidTransitionException if the course doesn't start with a letter
         */
        @Override
        public void onDigit() throws InvalidTransitionException {
            throw new InvalidTransitionException("Course name must start with a letter.");
        }
    }

    /**
     * LetterState for handling up to 4 letters in the course name.
     */
    private class LetterState extends State {
    	/**
    	 * Set MAX LETTER NUMBER = 4
    	 */
        private static final int MAX_LETTERS = 4;

        /**
         *  Checks if there can be more letters in the name, or it throws an error
         * 
         *  @throws InvalidTransitionException if there are more than 4 letters
         */
        @Override
        public void onLetter() throws InvalidTransitionException {
            if (letterCount < MAX_LETTERS) {
                letterCount++;
            } else {
                throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
            }
        }

        /**
         * if the character is a digit, it increases the count and makes the state a number state
         */
        @Override
        public void onDigit() {
            digitCount++;
            currentState = new NumberState(); 
        }
    }

    /**
     * DigitState for handling exactly 3 digits in the course name.
     */
    private class NumberState extends State {
    	/** Max Digits Final  */
        private static final int MAX_DIGITS = 3;

        /**
         * if the character is a letter, the state moves to suffix, if it isn't, it throws an error
         * 
         *  @throws InvalidTransitionException if there are more than 3 digits
         */
        @Override
        public void onLetter() throws InvalidTransitionException {
            if (digitCount == MAX_DIGITS) {
                // Transition to SuffixState after exactly 3 digits and a letter is found
                currentState = new SuffixState();
                validState = true;
            } else {
                throw new InvalidTransitionException("Course name must have 3 digits.");
            }
        }

        /**
         * If the character is a digit, it increased the count if there are less than 3 or sets valid state
         * if there are more than 3 digits, it throws an error
         * 
         *  @throws InvalidTransitionException if there are more than 3 digits
         */
        @Override
        public void onDigit() throws InvalidTransitionException {
            if (digitCount < MAX_DIGITS) {
                digitCount++;
                
                if(digitCount == MAX_DIGITS) {
                	validState = true;
                }
            } else {
                throw new InvalidTransitionException("Course name can only have 3 digits.");
            }
        }
    }
    /**
     * SuffixState for handling an optional single letter suffix.
     */
    
    private class SuffixState extends State {
    	/**
    	 * If there is a letter in suffix, that means there are 2 suffixes and that is an error
    	 * 
    	 *  @throws InvalidTransitionException if there is an additional letter suffix
    	 */
        @Override
        public void onLetter() throws InvalidTransitionException {
            // No additional letters allowed after the suffix
        	
            throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
        }

        /**
    	 * If there is a digit in suffix, it throws an error
    	 * 
    	 *  @throws InvalidTransitionException if there is a digit suffix
    	 */
        @Override
        public void onDigit() throws InvalidTransitionException {
            // No digits allowed after the suffix
            throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");
        }

    }
}
