import java.util.*;

public class sudokuObject{

	private int value = 0;

	private int block = 0;

	private ArrayList<Integer> options = new ArrayList<Integer>();

	public static void main(String[] args)
	{
		sudokuObject test = new sudokuObject(0, 3);
		System.out.println(test.options.get(4));
		System.out.println(test.getValue());
		System.out.println(test.getBlock());
	}

	// The constructor for creating the sudoku object
	public sudokuObject(int value, int block)
	{
		this.value = value;
		this.block = block;
		for(int i = 1; i < 10; i++){
			options.add(i);
		}
	}

	//Returns the value 
	public int getValue()
	{
		return value;
	}

	//Sets the value 
	public void setValue(int value)
	{
		this.value = value;
	}

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

	public ArrayList getOptions(){
		return this.options;
	}

	public void removeOption(int number){
		for(int i = 0; i < options.size(); i++){
			if(number == options.get(i)){
				options.remove(i);
			}
		}
		if(options.size() == 1){
			this.value = options.get(0);
		}
	}
}