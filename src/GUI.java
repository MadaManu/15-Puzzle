/*
@author: Vladut Madalin Druta
*/

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

//Vladut Madalin Druta
//10345527
//drutav@tcd.ie

public class GUI implements MouseListener, KeyListener {
	
	static JFrame window = new JFrame();
	JPanel firstPane;
	JPanel drawingPanel;
	PuzzleBoard aBoard;
	MyCanvas theDrawing;
	int moves = 0;
	JPanel infoOnGame;
	JTextArea theInfo = new JTextArea();

	public GUI() {
		// the constructor that actually builds the hole application
		// creates a window, sets a close on the default close button
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		window.setBounds(30, 30, 400, 300); // sets the size on the window
		JButton start = new JButton("Start!"); // adds a button
		start.setBounds(125, 80, 100, 25); 
		start.addMouseListener(this); // adds an action when the button is pressed
		window.setLayout(new BorderLayout()); 
		firstPane = new JPanel();
		firstPane.add(start);
		window.add(firstPane);// add the button to the window
		window.setVisible(true);  // and makes the window visible
	}

	
//	method that actually draws the board and let's the user see how the board looks like and also give functionality and redraws the board if
//	any changes have been made
	public void drawTheBoard() {
		boolean isSolvable; 
		Image[] imgs = new Image[15];
		for (int i = 0; i < imgs.length; i++)
			imgs[i] = Toolkit.getDefaultToolkit().getImage((i + 1) + ".png"); // loads the images needed for the tiles of the board
		aBoard = new PuzzleBoard(4); // creates a new puzzle board by calling the class 
		isSolvable = aBoard.isSolvable(); // checks if the actual board is solvable
		while (!isSolvable) { // if is not solvable randomises the board and keeps looping until it finds a board that is solvable
//			this saves the user from playing a board that is actually not solvable
			aBoard.randomize();
			isSolvable = aBoard.isSolvable();
		}
		// when a valid board was found
		theDrawing = new MyCanvas(imgs, aBoard); // we create the drawing that has to be displayed in the window and also by creating the drawing 
//		it draws itself for the first time - next time when is drawn is when a change happened in the display of the board
		drawingPanel = new JPanel();
		drawingPanel.setLayout(new BorderLayout());
		drawingPanel.add(theDrawing); // adds the drawing to a panel in the window
		drawingPanel.setVisible(true);
		infoOnGame = new JPanel();
		theInfo.disable(); 
		theInfo.setDisabledTextColor(Color.BLACK);
		theInfo.setText("Moves: " + moves + "\nPuzzle not solved!"); // adds information on the side for the user
		infoOnGame.setLayout(new BorderLayout()); 
		infoOnGame.add(theInfo);
		window.requestFocus();
		window.addKeyListener(this); // adds the keylistener so that the user can use the arrows on the keyboard to move the empty tile on the board
		window.add(drawingPanel, BorderLayout.CENTER);
		window.add(infoOnGame, BorderLayout.EAST);
		// window.add(theDrawing);

	}

	
//	method that will handle the mouse click on the start button
	@Override
	public void mouseClicked(MouseEvent e) {
		e.getComponent().setVisible(false); // stops the button from being displayed on screen 
		e.getComponent().setFocusable(false); // defocuses from the button
		firstPane.setVisible(false); // disables from view the panel
		drawTheBoard(); // draws the board
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

//	method that handles the key input
	@Override
	public void keyPressed(KeyEvent e) {
		if (!aBoard.isSolved()) { // make changes if the board is not solved
//			there is no point to make changes when the puzzle is solved
			
			if (e.getKeyCode() == 39) {  // right arrow was pressed 
				aBoard.moveEmptyRight(); // call the method on the board to move the empty to right
				moves++; // count the move
			}
			if (e.getKeyCode() == 38) { // up arrow pressed
				aBoard.moveEmptyUp(); // move the empty tile up
				moves++; // count move
			}
			if (e.getKeyCode() == 37) { // left key pressed
				aBoard.moveEmptyLeft(); // move the blank tile to left
				moves++; // count the move
			}
			if (e.getKeyCode() == 40) { // down button pressed
				aBoard.moveEmptyDown(); // move down
				moves++; // count the move
			}
			if(aBoard.isSolved()) // check if with this move the board is not solved
				theInfo.setText("YOU FINISHED! \n Congratulations! \n Number of moves: "+moves); // display this if the user finished the puzzle
			else
				theInfo.setText("Moves: " + moves + "\n Puzzle not solved!"); // otherwise set the new info to user
		}

		theDrawing.repaint(); // once a move is done we have to show that to the user so the drawing 

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
