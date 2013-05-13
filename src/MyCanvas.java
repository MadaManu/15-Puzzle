/*
@author: Vladut Madalin Druta
*/
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComponent;

//Vladut Madalin Druta
//10345527
//drutav@tcd.ie

class MyCanvas extends JComponent {

		private Image [] theImg;
		private PuzzleBoard theBoard;
		public MyCanvas (Image[] a, PuzzleBoard b){
			theImg = a;
			theBoard = b;
		}

	 public void paint(Graphics g) {
	    Graphics2D g2 = (Graphics2D) g;
	    Image bg_img = Toolkit.getDefaultToolkit().getImage("bg.png");
	    g2.drawImage(bg_img, 10, 10, this);
	    int[][] the2D = theBoard.get2D();
	    	for(int i=0;i<the2D.length;i++)
	    		for(int j=0;j<the2D[0].length;j++)
	    			if(the2D[i][j]!=-1)
	    				g2.drawImage(theImg[the2D[i][j]-1],j*45 +30,i*45 + 30 , this);
	    g2.finalize();

	 }
}