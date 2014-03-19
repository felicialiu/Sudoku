import java.util.*;

public class Entry {

	// Current (or default) value for an entry in a sudoku
	private int value = 0;

	// Entry options (still) possible
	private ArrayList<Integer> options = new ArrayList<Integer>();

	// The constructor for creating a sudoku Entry
	public Entry(int value)
	{
		this.value = value;
		if(value == 0){
			for(int i = 1; i < 10; i++){
				this.options.add(i);
			}
		} else {
			this.options.add(value);
		}
	}

	// Checks whether options contains only one value, and if so, sets the
	// Sudoku cell value to that value, returns true if so
	public boolean checkOptions() {
		if(options.size() == 1) {
			this.value = options.get(0);
			return true;
		}
		return false;
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
		this.options.clear();
		this.options.add(value);
	}

	// Returns the ArrayList containing the current possible options for this
	// sudoku Entry ((sub)set of values 1-9)
	public ArrayList<Integer> getOptions(){
		return this.options;
	}

	// Removes an option from the list of options, returns false if option
	// has been removed, true if nothing has changed
	public boolean removeOption(int number){
		for(int i = 0; i < options.size(); i++){
			if(number == options.get(i)){
				options.remove(i);
				return false;
			}
		}
		return true;		
	}

	// Checks whether a cell can be assigned a final value, returns true when
	// it has done so
	public boolean assignValue() {
		if(options.size() == 1) {
			this.value = options.get(0);
			return true;
		} else {
			return false;
		}
	}
}