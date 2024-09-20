/**
 * A bag that can hold any single type of item.
 * @author Faryal Alizai
 * @param <T> the item type that the bag is holding
 */

public class OneItemBag<T> {

    /**
     * Item held within the bag.
     */

    private T item;

    /**
     * Constructs a new OneItemBag.
     */

    public OneItemBag() {
        if (item != null) {
            this.item = item;
            //puts item if not null

        }
        else
        {
            this.item = null;
            //otherwise sets to null
        }
    }

    /**
     * Will try to add an item to the bag, but item will only be added if bag is equal to null/empty.
     * @param item to add to the bag
     * @return it will output true if item was added, but it will output false if bag already has an item in it
     */

    public boolean addItem(T item) {
        if (this.item == null) {
            this.item = item;
            //adds item into the bag
            return true;
            //if item adds then it will return true
        }
        else
        {
            return false;
            //item already in bag if false
        }
    }

    /**
     * Removes item from bag and returns it, will return null if empty.
     * @return will return last removed item or will print null if the bag was empty
     */

    public T removeItem() {
        T removedItem = this.item;
        //keeps a temporary variable
        this.item = null;
        //sets item to null
        
        return removedItem;
        //returns the removed item
    }

    /**
     * Checks to see if the bag has an item.
     * @return will return false if theres no item in the bag and will return true if there is an item found in the bag
     */

    public boolean hasItem() {
        if (this.item != null) {
            return true;
            //if this.item is not null then it will return true
        }
        else if (this.item == null) {
            return false;
            //returns false if item equals null
        }
        return false;
        //it will return false by deafult if this.item is not null
    }
}