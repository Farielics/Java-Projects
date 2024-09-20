//TO DO: Add JavaDocs

//********************************************************
//*******  DO NOT EDIT ANYTHING BELOW THIS LINE    *******
//*******        EXCEPT TO ADD JAVADOCS            *******
//*******                                          *******
//*******   YOU MAY CHANGE THE toString() method   *******
//*******     OR IMPLEMENT Comparable<Person>      *******
//*******   (IF YOU WOULD FIND HELPFUL TO DO SO)   *******
//********************************************************

/**
 * This class reprsents test results, their remainiing time, and id.
 */
class Person {

	/**
	 * Tracks number of people made.
	 */
	private static int numPeople = 0;
	
	/**
	 * Result of persons test.
	 */
	private double testResult;
	
	/**
	 * The time left for person to wait.
	 */
	private int timeLeft;
	
	/**
	 * Shows if the person is alive or dead.
	 */
	private boolean isDead;
	
	/**
	 * Id for the person.
	 */
	private final int id;
	
	/**
	 * Makes a person with a test result and certain time left.
	 * @param testResult the persons test result
	 * @param timeLeft the time left the person has
	 */
	public Person(double testResult, int timeLeft) {
		this.testResult = testResult;
		this.timeLeft = timeLeft;
		this.id = numPeople++;
	}
	/**
	 * Private constructor to make a person with test results, certain time left, and their id.
	 * @param testResult the persons test results
	 * @param timeLeft the persons time left
	 * @param id the persons id
	 */
	private Person(double testResult, int timeLeft, int id) {
		this.testResult = testResult;
		this.timeLeft = timeLeft;
		this.id = id;
	}
	
	/**
	 * Makes time go down by 1 minute if it is not equal to the MAX_VALUE.
	 */
	public void tick() {
		if(timeLeft != Integer.MAX_VALUE) timeLeft--;
	}
	
	/**
	 * Sees if the person is dead.
	 * @return the result of the persons life, if true then the person is dead if false then they are alive
	 */
	public boolean isDead() {
		return timeLeft <= 0;
	}
	
	/**
	 * Gets the persons test results.
	 * @return the test results of the person are returned 
	 */
	public double getTestResult() {
		//Note: test result is created when person is created,
		//but shouldn't be accessed until the person has been
		//through the testing line in scenario 2
		return testResult;
	}
	
	/**
	 * Gets the persons id.
	 * @return the id of the person
	 */
	public int getId() {
		return id;
	}

	/**
	 * Makes a copy of the current person.
	 * @return the a copy of persons test results, time left, and id
	 */
	public Person clone() {
		return new Person(testResult, timeLeft, id);
	}

	/**
	 * Makes a description of the person.
	 * @return the string which includes the persons id status/time left
	 */
	public String toString() {
		return "ID: " + id + ", " + ((timeLeft == Integer.MAX_VALUE) ? "Not Infected" : "Time Left: " + timeLeft + " minutes");
	}
}