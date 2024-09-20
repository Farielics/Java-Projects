/**
 *  This is a demo of using the SingleItemBox class.
 *  @author K. Raven Russell
 */
public class BagUsageDemo {
	/**
	 *  This is a main method with demo code.
	 *  @param args command line args (not used)
	 */
	public static void main(String[] args) {
		//demo putting one kiwi in a bag
		
		/**
		 * Class for kiwis.
		 */
		class Kiwi { }
		
		//make some kiwis
		Kiwi k1 = new Kiwi();
		Kiwi k2 = new Kiwi();
		
		//put the kiwi in a box
		OneItemBag<Kiwi> kiwiBag = new OneItemBag<>();
		
		//check some things to make sure it's working
		if(kiwiBag.addItem(k1) && kiwiBag.hasItem()) {
			System.out.println("yay 1");
		}
		
		if(!kiwiBag.addItem(k2) && kiwiBag.hasItem()) {
			System.out.println("yay 2");
		}
		
		
		
		//demo with an acorn in a bag
		
		/**
		 * Class for acorns.
		 */
		class Acorn { }
		
		//make some acorns
		Acorn a1 = new Acorn();
		Acorn a2 = new Acorn();
		Acorn a3 = new Acorn();
		
		//put the acorn in a bag
		OneItemBag<Acorn> acornBag = new OneItemBag<>();
		
		//check some things
		if(acornBag.addItem(a1) && acornBag.removeItem().equals(a1) && !acornBag.hasItem()) {
			System.out.println("yay 3");
		}
		
		//check even more things
		if(acornBag.addItem(a3) && acornBag.hasItem() && acornBag.removeItem().equals(a3) && !acornBag.hasItem()) {
			System.out.println("yay 4");
		}
	}
}