import javax.swing.*;
//import javax.swing.JFrame; //imports JFrame library
//import javax.swing.JTextField; //imports JTextField library
//import java.awt.GridLayout; //imports GridLayout library
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class SudokuGraphics {
 
    // the frame which contains all the panels
    JFrame frame = new JFrame("Sudoku solver"); //creates frame

    // This is the solve button 
    JButton solveButton = new JButton("Solve");

    // This is the textfield grid which represents the board
    JTextField[][] grid;

    final Board board2;

    public static void main(String[] args) {
        //new SudokuGraphics(9,9);//makes new SudokuGraphics with 2 parameters
    }

    public SudokuGraphics(int width, int length, Board board){ //constructor
            // This sets the layout of the panels(left sudoku, right buttons)
            frame.setLayout(new GridLayout(1,2)); //set layout

            board2 = board;

            // This is the panel which contains the sudoku board
            JPanel sudoku = new JPanel(new GridLayout(width,length));

            // This grid is the 9x9 board with textfields
            grid = new JTextField[width][length]; //allocate the size of grid

            // This fills in the textfields with the specified board objects
            //for(int v = 0; v < 9; v++){
                for(int y = 0; y < length; y++){
                        for(int x = 0; x < width; x++){
                                grid[x][y] = new JTextField(Integer.toString(board.getRow(y)[x].getValue())); //creates new textfield 
                                grid[x][y].setHorizontalAlignment(JTextField.CENTER);   
                                sudoku.add(grid[x][y]); //adds textfield to grid
                        }
                }
            //}

            

            // This is the panel which contains the buttons
            JPanel buttonPanel = new JPanel(new GridLayout(1, 1));
            buttonPanel.add(solveButton);

            //Add action listener to the solve button
            solveButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    Sudoku.solve(board2);
                    Sudoku.drawSudoku(9,9,board2);
                }
            });

            // This panel contains all the button panels
            JPanel east = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.anchor = GridBagConstraints.WEST;
            gbc.weighty = 1;
            east.add(buttonPanel, gbc);

            // All the panels are added to the frame and the size and visibility
            // of the frame is set
            frame.add(sudoku, BorderLayout.WEST);
            frame.add(east, BorderLayout.EAST);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500);
            frame.setVisible(true);
    }   
}