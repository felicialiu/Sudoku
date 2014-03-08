import java.util.*;

public class Entry {

	// Current (or default) value for an entry in a sudoku
	private int value = 0;

	// Entry options (still) possible
/* catch if options.size() == 0 */
	private ArrayList<Integer> options = new ArrayList<Integer>();

	// DONE TESTING
	public static void main(String[] args)
	{	
		
		Entry test = new Entry(0);
		ArrayList options = test.getOptions(); 
		for(int i = 0; i < options.size(); i++) {
			System.out.println(options.get(i));
		}
		for(int i = 0; i < 9; i++) {
			test.removeOption(i);
		}
		options = test.getOptions(); 
		for(int i = 0; i < options.size(); i++) {
			System.out.println(options.get(i));
		}
		// test.removeOption(4);
		//System.out.println(test.options.get(4));
		System.out.println(test.getValue());
		//System.out.println(test.getBlock());
		test.checkOptions();
		System.out.println(test.getValue());

		
	}

	// The constructor for creating a sudoku Entry
	public Entry(int value)
	{
		this.value = value;
		if(value == 0){
			for(int i = 1; i < 10; i++){
				this.options.add(i);
			}
		}
	}

	// Checks whether options contains only one value, and if so, sets the
	// Sudoku cell value to that value
	public void checkOptions() {
		if(options.size() == 1) {
			this.value = options.get(0);
		}
	}

	// Returns the current value of the sudoku Entry
	public int getValue()
	{
		return value;
	}

	// Sets the current Entry value to value 
	public void setValue(int value)
	{
		this.value = value;
	}

	// Returns the ArrayList containing the current possible options for this
	// sudoku Entry ((sub)set of values 1-9)
	public ArrayList getOptions(){
		return this.options;
	}

	// Removes an option from the list of options
	public void removeOption(int number){
		for(int i = 0; i < options.size(); i++){
			if(number == options.get(i)){
				options.remove(i);
			}
		}
		
		// If there is only one option left, it must be the value of the Entry
		if(options.size() == 1){
			this.value = options.get(0);
			//System.out.println(options.get(0));
		}
		
	}
}

/*
	// Returns the block
	public int getBlock()
	{
		if(block == 0) {
			System.out.println("No block has been found");
			return 0;
		} else {
			return this.block;
		}
	}

	public void setBlock(){
		this.block = block;
	}
*/	