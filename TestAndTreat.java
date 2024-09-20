//TO DO: Finish this!

// You may NOT add any arrays as a member variable in this class

// You MAY add an import to java.util.Comparator if
// you are NOT making the Person class Comparable. 
// No other imports are allowed.

/**
 * TestAndTreat extend TreatAll and deals with scenarios where you need to test and treat.
 * has two lines, one for testing and one for treatment
 */
class TestAndTreat extends TreatAll {

	/**
	 * Time needed to test a person.
	 */
	private final int testingTime;

	/**
	 * Line of people who need to be tested.
	 */
	private Line<Person> testingLine = new Line<>();
	
	/**
	 * The wait time for the test.
	 */
	private int testWaitTime = 0;
	
	/**
	 * A constructor that initalizes TestAndTreat with testing and treatment times.
	 * @param testingTime is the time needed to test someone
	 * @param treatmentTime is the time needed to treat someone
	 */
	public TestAndTreat(int testingTime, int treatmentTime) {

		//calls parent class constructor 
		super(treatmentTime);
		this.testingTime = testingTime;
	}
	/**
	 * Returns the line of people who need to be tested.
	 * @return is the testing line
	 */
	public Line<Person> getTestingLine() {

		return testingLine;
	}

	/**
	 * Returns the total number of people who are pending in both lines also calculates it.
	 * @return is the total number of people waiting in both lines
	 */
	public int getPending() {
		//gets people who are pending from the treatment line
		int superPending = super.getPending();
		//adds the pending people from the testing line
		return testingLine.getSize() + superPending;
	}

	/**
	 * Adds person to the testing line and sets testWaitTime.
	 * @param p is the person added to the testing line
	 */
	public void addPerson(Person p) {
		//adds person to the back of the testing line
		testingLine.add(p, testingLine.getSize());
		//sets the test wait time if this is first person
		if (testingLine.getSize() > 0) {
			if (testingLine.getSize() == 1) {
				//sets the time to test a person
				testWaitTime = testingTime;
			}
		}
	}

	/**
	 * Tick method updates both test and treat as well as the decrementing time.
	 * if the person is done testing they be marked safe or they will move to treatment line
	 */
	public void tick() {

		//calls parent class
		super.tick();

		//decrements test wait time
		if (testWaitTime > 0) {
			testWaitTime = (testWaitTime - 1);
		}
		int i = 0;
		//goes through testing line if there are people there
		while (i < testingLine.getSize()) {

			//gets first person in testing line
			Person testedPerson = testingLine.get(i);

			//checks if test for person is done
			if (testWaitTime == 0) {

				//if the tests results are more than 0.4 then it'll move to the treatment line
				if (testedPerson.getTestResult() > 0.4) {

					//removes from testing line
					testingLine.remove(0);
					//adds to treatment line
					super.addPerson(testedPerson);
				}
				else
				{
					//removes person from testing line if negative
					testingLine.remove(0);
				}

				//resets tests wait time
				if (testingLine.getSize() > 0) {
					//resets the test wait time
					testWaitTime = testingTime;
				}
			}
			else
			{
				i =(i + 1);
			}
		}
	}
	
	/**
	 * Method toString is used to display the test wait time and doctor wait time.
	 * @return a string that shows the test wait time and the doctor wait time
	 */
	public String toString() {
		
		//returns the test wait time and the doctor wait time
		return "Scenario 2: Test available in " + testWaitTime + " minute(s). Doctor Free in " + doctorWaitTime + " minute(s).";
	}
}