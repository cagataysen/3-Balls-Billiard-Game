import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
import java.io.*;


public class BallPanel extends JPanel implements ActionListener, MouseListener {
	

	double sz = 30;

	double Vx, Vy;
	
	double Vx2, Vy2;
	
	double Vx3, Vy3;
	
    int P1Score = 0;
    int P2Score = 0;
	
	public int handXPos = 0;
	public int handYPos = 0;
	
	public boolean playerTurn;
	
	Boolean P1 = false;
	Boolean P2 = false;
	Boolean P3 = false;
	Boolean P4 = false; 
	String cheat;
	
	
	Ellipse2D.Double ball1 = new Ellipse2D.Double(100,200,sz,sz);

	Ellipse2D.Double ball2 = new Ellipse2D.Double(500,250,sz,sz);
	
	Ellipse2D.Double ball3 = new Ellipse2D.Double(500,150,sz,sz);

	Rectangle2D.Double Table1 = new Rectangle2D.Double(20, 20, 793, 387);
	
	Rectangle2D.Double Table2 = new Rectangle2D.Double(10, 10, 813, 407);
		
	Font titleFont = new Font("Times New Roman", Font.BOLD, 55);
	

	Timer t  = new Timer (30,this);
	
	Timer t2 = new Timer (30,this);
	
	Timer t3 = new Timer (30,this);

    Timer Stick_timer = new Timer (30,this);
	

	BallPanel() {

		addMouseListener(this);

		setDoubleBuffered(true);
		
	}
	
	
	@Override

	public void actionPerformed(ActionEvent e) {

		updateBallPosition();

	}

	
	@Override

	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		
		Graphics2D g2d = (Graphics2D)g;
		Graphics2D s = (Graphics2D)g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.setColor(Color.black);
		g2d.fill(Table2);
		
		g2d.setColor(Color.green);
		g2d.fill(Table1);
		
		g2d.setColor(Color.white);
		g2d.fill(ball1);
		
		g2d.setColor(Color.YELLOW);
		g2d.fill(ball2);
		
		g2d.setColor(Color.red);
		g2d.fill(ball3);
		
		
		g2d.setColor(Color.black);
		g2d.drawString("Position of Ball1 : " + ball1.getCenterX() + " , " + ball1.getCenterY() , 40, 460);
		g2d.drawString("Position of Ball2 : " + ball2.getCenterX() + " , " + ball2.getCenterY() , 440, 460);
		g2d.drawString("Position of Ball3 : " + ball3.getCenterX() + " , " + ball3.getCenterY() , 840, 460);
		g2d.drawString("Score of Player1 : " + P1Score, 40 , 440);
		g2d.drawString("Score of Player2 : " + P2Score, 540 , 440);
		g2d.drawString("Speed of Ball1 : " + Math.hypot(Vx, Vy), 40, 480);
		g2d.drawString("Speed of Ball2 : " + Math.hypot(Vx2, Vy2), 440, 480);
		g2d.drawString("Speed of Ball3 : " + Math.hypot(Vx3, Vy3), 840, 480);
		
		if( Math.hypot(Vx , Vy) == 0 && Math.hypot(Vx2 , Vy2) == 0 && playerTurn == false) {
			
			Rectangle2D.Double Stick = new Rectangle2D.Double(ball1.getCenterX(), ball1.getCenterY(), 220, 8);
			s.rotate(Math.atan((ball1.getY() - handYPos )/(ball1.getX() - handXPos )), ball1.getCenterX(),ball1.getCenterY());
			s.setColor(Color.magenta);
			s.setBackground(Color.magenta);
			s.fill(Stick);
		
		}
		else if( Math.hypot(Vx , Vy) == 0 && Math.hypot(Vx2 , Vy2) == 0 &&  playerTurn == true) {
			
			Rectangle2D.Double Stick = new Rectangle2D.Double(ball2.getCenterX(), ball2.getCenterY(), 220, 8);
			s.rotate(Math.atan((ball2.getY() - handYPos )/(ball2.getX() - handXPos )), ball2.getCenterX(),ball2.getCenterY());
			s.setColor(Color.cyan);
			s.setBackground(Color.cyan);
			s.fill(Stick);
		
		}
		
		if(P1Score == 50) {
		
			super.paintComponent(g);
			g.setFont(titleFont);
			g.drawString("Player 1 WON!", 550, 350);
	
		}	
	
		if(P2Score == 50) {
		
			super.paintComponent(g);
			g.setFont(titleFont);
			g.drawString("Player 2 WON!", 550, 350);
		}
		
		
		repaint();
		
	}


	public void updateBallPosition() {

		ball1.x += Vx;

		ball1.y += Vy;
		
		ball2.x += Vx2;

		ball2.y += Vy2;
		
		ball3.x += Vx3;

		ball3.y += Vy3;
		
		

		
		if( ball1.x < 20 || ball1.x > 783) {
		
			Vx = -Vx;    
		
		}

		
		if( ball1.y < 20 || ball1.y > 377) {
		
			Vy = -Vy;		
		
		}
		
		
		if( ball2.x < 20 || ball2.x > 783) {
			
			Vx2 = -Vx2;    
		
		}

		
		if( ball2.y < 20 || ball2.y > 377) {
		
			Vy2 = -Vy2;		
		
		}
		
		
		if( ball3.x < 20 || ball3.x > 783) {
			
			Vx3 = -Vx3;    
		
		}

		
		if( ball3.y < 20 || ball3.y > 377) {
		
			Vy3 = -Vy3;		
		
		}
		
	
		// 1x2 and 2x1
		
		double dx = ball1.getCenterX() - ball2.getCenterX();
		double dy = ball1.getCenterY() - ball2.getCenterY();
		
		double d5x = ball2.getCenterX() - ball1.getCenterX();
		double d5y = ball2.getCenterY() - ball1.getCenterY();
	
		if(Math.hypot(Vx, Vy) > Math.hypot(Vx2, Vy2)) {	
	
			if( Math.hypot(dx, dy) < ball1.width ) {
			
				double VrMag = (Vx*dx + Vy*dy )/ Math.hypot(dx, dy);
				double Vrx = VrMag * dx / Math.hypot(dx, dy);
				double Vry = VrMag * dy / Math.hypot(dx, dy);
			
				double Vtx = Vx - Vrx;
				double Vty = Vy - Vry;
			
				Vx = Vtx;
				Vy = Vty;
				Vx2 = Vx2+Vrx;
				Vy2 = Vy2+Vry;
				
				P1 = true;
			    P3 = true;
			}
		
	    }
	
	    else {
	     
	    	if( Math.hypot(d5x, d5y) < ball2.width ) {
			
		    
	    		double VrMag = (Vx2*d5x + Vy2*d5y )/ Math.hypot(d5x, d5y);
	    		double Vrx = VrMag * d5x / Math.hypot(d5x, d5y);
	    		double Vry = VrMag * d5y / Math.hypot(d5x, d5y);
			
			
	    		double Vtx = Vx2 - Vrx;
	    		double Vty = Vy2 - Vry;
			
	    		Vx2 = Vtx;
	    		Vy2 = Vty;
	    		Vx = Vx+Vrx;
	    		Vy = Vy+Vry;
			
	    		P1 = true;
                P3 = true;	    	
	    	}
		
	    }
		
		// 1x3 and 3x1
		
		double d2x = ball1.getCenterX() - ball3.getCenterX();
		double d2y = ball1.getCenterY() - ball3.getCenterY();
	    
		double d4x = ball3.getCenterX() - ball1.getCenterX();
		double d4y = ball3.getCenterY() - ball1.getCenterY();
		
	    
		if(Math.hypot(Vx, Vy) > Math.hypot(Vx3, Vy3)) {  
	     
			if( Math.hypot(d2x, d2y) < ball1.width) {
			  
				double VrMag = (Vx*d2x + Vy*d2y )/ Math.hypot(d2x, d2y);
				double Vrx = VrMag * d2x / Math.hypot(d2x, d2y);
				double Vry = VrMag * d2y / Math.hypot(d2x, d2y);
				
				double Vtx = Vx - Vrx;
				double Vty = Vy - Vry;
				
				Vx = Vtx;
				Vy = Vty;
				Vx3 = Vx3+Vrx;
				Vy3 = Vy3+Vry;
				
				P2 = true;
		   }
	       
		}
		else {
		   
			if( Math.hypot(d4x, d4y) < ball3.width) {
			
			    double VrMag = (Vx3*d4x + Vy3*d4y )/ Math.hypot(d4x, d4y);
				double Vrx = VrMag * d4x / Math.hypot(d4x, d4y);
				double Vry = VrMag * d4y / Math.hypot(d4x, d4y);
				
				double Vtx = Vx3 - Vrx;
				double Vty = Vy3 - Vry;
				
				Vx3 = Vtx;
				Vy3 = Vty;
				Vx = Vx+Vrx;
				Vy = Vy+Vry;
				
				P2 = true;
		   
			}
			
		}
	
		double d3x = ball2.getCenterX() - ball3.getCenterX();
		double d3y = ball2.getCenterY() - ball3.getCenterY();
		
		double d6x = ball3.getCenterX() - ball2.getCenterX();
		double d6y = ball3.getCenterY() - ball2.getCenterY();
	
		if(Math.hypot(Vx2, Vy2) > Math.hypot(Vx3, Vy3)) {
	
			if( Math.hypot(d3x, d3y) < ball2.width) {
		
			    double VrMag = (Vx2*d3x + Vy2*d3y )/ Math.hypot(d3x, d3y);
				double Vrx = VrMag * d3x / Math.hypot(d3x, d3y);
				double Vry = VrMag * d3y / Math.hypot(d3x, d3y);
				
				double Vtx = Vx2 - Vrx;
				double Vty = Vy2 - Vry;
				
				Vx2 = Vtx;
				Vy2 = Vty;
				Vx3 = Vx3+Vrx;
				Vy3 = Vy3+Vry;		

				P4 = true;
				
			}
	
		}
		
		else {
		
			if( Math.hypot(d6x, d6y) < ball3.width) {
			    
				double VrMag = (Vx3*d6x + Vy3*d6y )/ Math.hypot(d6x, d6y);
				double Vrx = VrMag * d6x / Math.hypot(d6x, d6y);
			    double Vry = VrMag * d6y / Math.hypot(d6x, d6y);
			
		    	double Vtx = Vx3 - Vrx;
	     		double Vty = Vy3 - Vry;
			
	    		Vx3 = Vtx;
	    	   	Vy3 = Vty;
    			Vx2 = Vx2+Vrx;
     			Vy2 = Vy2+Vry;		
		
     			P4 = true;
     			
			}
		}

	
		highScoreCount();
	
		deceleration();
		
		repaint();

		
	}
	
	
	public void highScoreCount() {
		

		if (P1 == true && P2 == true) {
			
			P1Score = P1Score + 1;
			playerTurn = false;		
			P1 = false;
			P2 = false;
			P3 = false;
			P4 = false;
		
		}
		else if (Math.hypot(Vx, Vy) == 0 && Math.hypot(Vx, Vy) == 0 && Math.hypot(Vx, Vy) == 0) {
			
			P1 = false;
			P2 = false;
			P3 = false;
			P4 = false;
		
		}
		
	    if(P3 == true && P4 == true) {
		
			P2Score = P2Score + 1;
			playerTurn = true;
			P1 = false;
			P2 = false;
			P3 = false;
			P4 = false;
			
		}
	    else if (Math.hypot(Vx, Vy) == 0 && Math.hypot(Vx, Vy) == 0 && Math.hypot(Vx, Vy) == 0) {
			
			P1 = false;
			P2 = false;
			P3 = false;
			P4 = false;
		
		}
		
		
        try{
        
        	String numberAsString = Integer.toString(P1Score);
            File dosya = new File("C:\\Users\\HP\\Desktop\\BallPool\\HighScore.txt");
            FileWriter yazici = new FileWriter(dosya,true);
            BufferedWriter yaz = new BufferedWriter(yazici);
            yaz.write(numberAsString);
            yaz.close();
            
         }
         catch (Exception hata){
            
        	 hata.printStackTrace();
         
         }
        
		
	}
	
	
	public void deceleration() {

		Vx = Vx*0.99;

		Vy = Vy*0.99;
		
     	Vx2 = Vx2*0.99;

	    Vy2 = Vy2*0.99;
	
		Vx3 = Vx3*0.99;

		Vy3 = Vy3*0.99;
	
		if(Vx*Vx + Vy*Vy < 0.1) {

			Vx=0;
			Vy=0;

		}		
		
		if(Vx2*Vx2 + Vy2*Vy2 < 0.1) {

			Vx2=0;
			Vy2=0;
			
		}	
		
		if(Vx3*Vx3 + Vy3*Vy3 < 0.1) {

			Vx3=0;
			Vy3=0;
			
		}
		
	}

	

	@Override

	public void mouseClicked(MouseEvent e) {
		
		if( Math.hypot(Vx, Vy) == 0 && Math.hypot(Vx2, Vy2) == 0 && Math.hypot(Vx3, Vy3) == 0) {
	    
			if (playerTurn == false) {
				
				double x1 = e.getX();

				double y1 = e.getY();
		
				double V = Math.hypot( (ball1.getX()-x1), (ball1.getY()-y1) ) / 50 + 10;
				
				double x2 = ball1.x + ball1.width/2;
				
				double y2 = ball1.y + ball1.height/2;
				
				double dx = x1-x2;

				double dy = y1-y2;
		
				Vx= V*dx/Math.sqrt(dx*dx+dy*dy);
				
				Vy= V*dy/Math.sqrt(dx*dx+dy*dy);
				
				playerTurn = true;
		
				
				if(e.getButton() == MouseEvent.BUTTON3) {
		            
				     P1Score =  P1Score + 50 ;
			          
				}
				
				t.start();
				
		}
		else if( playerTurn == true) {
		    
	        double x1 = e.getX();

			double y1 = e.getY();
			
			double V = Math.hypot( (ball2.getX()-x1), (ball2.getY()-y1) ) / 50 + 10;
					
			double x2 = ball2.x + ball2.width/2;

			double y2 = ball2.y + ball2.height/2;

			double dx = x1-x2;

			double dy = y1-y2;

			Vx2= V*dx/Math.sqrt(dx*dx+dy*dy);

			Vy2= V*dy/Math.sqrt(dx*dx+dy*dy);
				
			playerTurn = false;

			
			if(e.getButton() == MouseEvent.BUTTON3) {
	            
			     P2Score =  P2Score + 50 ;
		          
			}
			
			t2.start();
			
		
		
		}
		
		}
	}

	

	@Override

	public void mousePressed (MouseEvent e) {

		handXPos = e.getX();
	    handYPos = e.getY();

	}



	@Override

	public void mouseEntered(MouseEvent e) {

	}


	@Override

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override

	public void mouseReleased(MouseEvent e){
	
	}

	
	public void mouseDragged(MouseEvent e) {
				
	}

	public void mouseMoved(MouseEvent e) {
		  
	}

}
