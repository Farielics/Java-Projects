//TO DO: Finish this!
/**
 * The TreatAll class deals with treatment in the queues.
 * also deals with treatment line, doctors wait time, and treatments for patients(life and death)
 */
class TreatAll extends Scenario {
	//******************************************************
	//*******  DO NOT EDIT ANYTHING IN THIS SECTION  *******
	//******************************************************
	
	/**
	 *  The time to administer a treatment regimen.
	 */
	protected final int treatmentTime;
	
	/**
	 *  The line for treatment.
	 */
	protected Line<Person> treatmentLine = new Line<>();
	
	/**
	 * Accepts the treatment time.
	 * 
	 * @param treatmentTime the time to administer a treatment regimen
	 */
	public TreatAll(int treatmentTime) {
		this.treatmentTime = treatmentTime;
	}
	
	/**
	 *  Allows the GUI access to the treatment line.
	 *  
	 *  @return the treatment line
	 */
	public Line<Person> getTreatmentLine() {
		return treatmentLine;
	}
	
	//******************************************************
	//*******         MAKE YOUR CHANGES BELOW        *******
	//******************************************************

	/**
	 * Tracks the wait time for doctor.
	 */
	protected int doctorWaitTime = 0;
	
	/**
	 * Returns the amount of people in the treatment line.
	 * @return the number of people in the treatment line
	 */
	public int getPending() {

		//returns the number fo people in the treatment line
		return treatmentLine.getSize();
	}
	/**
	 * Adds person to the end of the treatment line.
	 * sees if anyone else is in line, if only person then they will begin getting treated
	 * @param p is the person to be added
	 */
	public void addPerson(Person p) {

		//adds a person to the end of treatment line
		treatmentLine.add(p, treatmentLine.getSize());
		//sees if anyone else is in line, will start treatment for person in line
		if (treatmentLine.getSize() > 0) {
			//starts treatment if treatment line has one person
			if (treatmentLine.getSize() == 1) {

				doctorWaitTime = treatmentTime;
			}
		}
	}
	
	/**
	 * Deals with the treatment proccess of people in the line.
	 * gives treatment, deals with deaths, and it moves onto next person if someone is cured/dies
	 */
	public void tick() {

		//decrements the remaining time if its greater than 0
		if (doctorWaitTime > 0) {

			doctorWaitTime = (doctorWaitTime - 1);
		}

		int i = 0;

		//goes through the treatment line as long as the treatment line as long as people are in the line
		while (i < treatmentLine.getSize()) {
			Person newPerson = treatmentLine.get(i);
			newPerson.tick();

			//Person firstPerson = treatmentLine.get(0);
			//treatmentLine.get(i);
			//treatmentLine.get(i).tick();

			//treats first person in line
			if (treatmentLine.get(i).isDead()) {

				//removes person from treatment line if they are dead and adds a + 1 to numDeaths
				treatmentLine.remove(i);
				numDeaths = (numDeaths + 1);

				//if there are still people in line it will reset the doctors time
				if (treatmentLine.getSize() > 0) {
					doctorWaitTime = treatmentTime;
				}

			} else if (doctorWaitTime == 0) {
				//removes person from treatment line if the treatment is done and adds a + 1 to numSafe
				treatmentLine.remove(i);
				numSafe = (numSafe + 1);

				//if people are still in line it will reset the doctors time
				if (treatmentLine.getSize() > 0) {
					doctorWaitTime = treatmentTime;
				}
			}
			else 
			{
				i = (i + 1);
			}
		}
	}

	/**
	 * Returns a string for the current scenario.
	 * lets us know the number of minutes until the doctor is free
	 * @return the string that tells us when the doctor will be free
	 */
	//@Override
	public String toString() {

		//Replace "??" with the number of minutes until the doctor is free
		//i.e. the treatment time remaining
		return "Scenario 1: Doctor Free in " + doctorWaitTime + " minute(s).";
	}
}
