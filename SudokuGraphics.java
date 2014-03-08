import javax.swing.*;
import java.awt.*;

public class SudokuGraphics extends JPanel {

	public static void main(String[] args) {
        SudokuGraphics object = new SudokuGraphics();
    }

    private Graphics view;

    public SudokuGraphics() {
        JFrame frame = new JFrame();
        frame.paintComponent(view);
        frame.setSize(470, 470);
        frame.setVisible(true);
    }

    public void paintComponent(Graphics obj) {
    	System.out.println("wtf");
    	
    	// The x-coordinate for row 
    	int rowx = 10;

    	// The y coordinate for row
        int rowy = 60;

        // The x-coordinate for row which the line will go to  
        int rowx2 = 460;

        // The x-coordinate for drawing the blocks on the canvas
        int x = 11;

        // This draws the main rectangle which contains all other lines and rectangles
        obj.drawRect(10, 10, 450, 450);
        // This draws 9 rectangles representing the blocks on the board
        for(int j = 0; j < 3; j++){
        	obj.drawRect(x, 11, 148, 148);
        	obj.drawRect(x, 161, 148, 148);
        	obj.drawRect(x, 311, 148, 148);
        	x += 150;
        }
        
        // This draws all the lines for the rows and columns
        for(int i = 0; i < 8; i++){
        	obj.drawLine(rowx, rowy, rowx2, rowy);
        	obj.drawLine(rowy, rowx, rowy, rowx2);
        	rowy += 50;
        }

        int objx = 10;
        int objy = 10;
        for(int z = 0; z < 81; z++){
        	JLabel label = new JLabel(Integer.toString(z), JLabel.CENTER);
    		// label.setText(Integer.toString(z));
    		label.setLayout(null);
    		add(label);
    		label.setSize(50, 50);
    		label.setLocation(objx, objy);
    		objx += 50;
    		objy += 50;
        }
    }
}

    