/**
 * This class has a growing array.
 * @param <T> is the element types held in the array
 */
class Line<T> {

	/**
	 * Array to hold data.
	 */
	private T[] data;

	/**
	 * To count items.
	 */
	private int size;
	
	/**
	 * Makes line with size of 1.
	 */
	@SuppressWarnings("unchecked")
	public Line() {
		//temp variable to hold the starting size
		int startSize = 1;

		//makes an array with a size of 1 using the temp variable
		Object[] tempArray = new Object[startSize];

		//stores the variable before copying
		Object[] tempArrayCopy = tempArray;
		//casts it to T array
		data = (T[]) tempArrayCopy;

		//puts size at 0 because nothing is added
		size = (size + 0);
	}
	
	/**
	 * Gets the item at a certain position.
	 * @param index the index of the items position
	 * @return the items at the index
	 * @throws IndexOutOfBoundException if it's out of bounds
	 */
	public T get(int index) {

		//sees if size is equal to 0
		if (size == 0) {
			throw new IndexOutOfBoundsException("Out of bound index: " + index);
		}

		//sees if index is less than 0
		if (index < 0) {

			//if out of bounds it will throw an exception
			throw new IndexOutOfBoundsException("Out of bound index: " + index);
		}
		
		//sees if index is greater than or equal to the size
		if (index > size) {

			//if out of bounds it will throw an exception
			throw new IndexOutOfBoundsException("Out of bound index: " + index);
		}

		//returns the item at index
		return data[index];
	}
	
	/**
	 * Removes items at a certain position and moves the items left to the right.
	 * @param index is where the item is to remove them
	 * @return is the item that was removed
	 * @throws IndexOutOfBoundsException if its out of the bounds
	 */
	public T remove(int index) {

		//sees if the size = 0
		if (size == 0) {
			throw new IndexOutOfBoundsException("Out of bounds index" + index);
		}

		//sees if the index is less than 0
		if (index < 0) {

			//if out of bounds it will throw an exception
			throw new IndexOutOfBoundsException("Out of bounds index: " + index);
		}

		//sees if the index is greater than or equal to the size
		if (index > size) {

			//if out of bounds it will throw an exception
			throw new IndexOutOfBoundsException("Out of bounds index: " + index);
		}

		//holds item that will be removed
		T removedItem = data[index];

		//moves the items to fill any gaps left from removing one
		for (int i = index; i < size - 1; i++) {

			//moves items left by one spot
			T leftItem = data[i + 1];
			data[i] = leftItem;
		}

		//makes last item = null
		data[size - 1] = null;
		//subtracts 1 from the arrays size
		int newSize = (size - 1);
		size = newSize;

		//returns the item that had been removed
		return removedItem;
	}
	/**
	 * Adds a new item at the index and will shift the items if needed.
	 * @param item the item thats added/going to be added
	 * @param index the index where the item will be added/to add to
	 * @throws IndexOutOfBoundsException if its out of bounds
	 */
	@SuppressWarnings("unchecked")
	public void add(T item, int index) {
		
		//sees if index is less than 0
		if (index < 0) {

			//if out of bounds it will throw an exception
			throw new IndexOutOfBoundsException("Out of bounds index" + index);
		}

		//sees if index is greater than size
		if (index > size) {

			//if out of bounds it will throw an exception
			throw new IndexOutOfBoundsException("Out of bounds index" + index);
		}

		//if array is full then change the size
		if (size == data.length) {

			int newSpace = (data.length * 2);
			//makes a new array thats double the size of the current array
			T[] newArray = (T[]) new Object[newSpace];

			//takes the old data and puts it into the new data
			for (int i = 0; i < size; i++) {
				T values = data[i];
				newArray[i] = values;
			}
			//changes old array with new array
			data = newArray;
		}

		//moves items to the right to make space for new item
		for (int i = size; i > index; i--) {
			//moves each item right by one spot
			T rightItem = data[i - 1];
			data[i] = rightItem;
		}

		//puts new item into the index
		data[index] = item;
		//adds 1 to the array size
		int newSize = (size + 1);
		size = newSize;
	}
	/**
	 * Returns the number of items in the line.
	 * @return the lines size
	 */
	public int getSize() {

		//retuns the current size
		return size;
	}
	/**
	 * Returns the arrays size and the items.
	 * @return the array length
	 */
	public int getCapacity() {

		//returns the arrays length
		return data.length;
	}
	
	/**
	 * Main method used to test the line code to ensure it works.
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {
		//This is a main-method tester.
		//You may alter/change/remove this method.
		
		//It doesn't test everything, but it should give you an idea of
		//the types of scenarios you should be testing when writing this
		//class. The JUnit tests for grading will test a lot more things!
		
		Line<Integer> test = new Line<>();
		if(test.getSize() == 0 && test.getCapacity() == 1) {
			System.out.println("yay 1");
		}
		
		test.add(1, 0);
		if(test.getSize() == 1 && test.getCapacity() == 1 && test.get(0) == 1) {
			System.out.println("yay 2");
		}
		
		test.add(2, 0);
		if(test.getSize() == 2 && test.getCapacity() == 2 && test.get(0) == 2 && test.get(1) == 1) {
			System.out.println("yay 3");
		}
		
		test.add(3, 2);
		if(test.getSize() == 3 && test.getCapacity() == 4 && test.get(0) == 2 && test.get(1) == 1 && test.get(2) == 3) {
			System.out.println("yay 4");
		}
		
		test.remove(2);
		if(test.getSize() == 2 && test.getCapacity() == 4 && test.get(0) == 2 && test.get(1) == 1) {
			System.out.println("yay 5");
		}
		
		test.remove(0);
		if(test.getSize() == 1 && test.getCapacity() == 4 && test.get(0) == 1) {
			System.out.println("yay 6");
		}
		
		try {
			test.add(4, 2);
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println("yay 7");
		}
		
		try {
			test.remove(-1);
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println("yay 8");
		}
		
		test.remove(0);
		try {
			test.get(0);
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println("yay 9");
		}
	}
}