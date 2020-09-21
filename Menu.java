import java.awt.Checkbox;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Menu extends JFrame implements ActionListener{
	
	protected static String username;
	JLabel label;
	JTextField text;
	JButton begin;
	JButton TwoPlayer;
	JButton highScores;
	GridLayout gl;
	//UserContent uContent;
	BallPanel bp;
	SoloPlayer sp;
	
	public Menu() {
		super( " 3 Ball Billard Menu " );
		super.setLocationRelativeTo(null);
	}
	
	public void showMenu() {
		
		//gl = new GridLayout(4,1);
	try{
		super.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:/Users/HP/Desktop/BallPool/BG.jpg")))));
	}
	catch(IOException e){
		System.out.println("NO IMAGE");
		
	}
	setLayout(null);
		//setSize(800, 800);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setVisible(true);
		//uContent = new UserContent();
		bp = new BallPanel();
		sp = new SoloPlayer();
		
		label = new JLabel("Username:");
		label.setBounds(100, 50, 100, 40);
		
		text = new JTextField();
		text.setBounds(170, 50, 100, 40);
		
		begin = new JButton("Start");
		begin.setBounds(100, 100, 176, 46);
		
		TwoPlayer = new JButton("2 Player Game");
		TwoPlayer.setBounds(100, 156, 176, 46);
		
		highScores = new JButton("High Scores");
		highScores.setBounds(100, 212, 176, 46);
		
		
		begin.addActionListener(this);
		TwoPlayer.addActionListener(this);

		add(label);
		add(text);
		add(begin);
		add(TwoPlayer);
		add(highScores);
		
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	
		if(e.getSource() == begin) {
			
			setVisible(false);
			username = text.getText().toString();
			JFrame f = new JFrame("BallAnimation");
			f.add(sp);
			f.setSize(843,462);
			
			sp.setBackground(Color.white);
			f.setVisible(true);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		}

		if(e.getSource() == TwoPlayer) {
			
			setVisible(false);
			username = text.getText().toString();
			JFrame a = new JFrame("BallAnimation");
			a.add(bp);
			a.setSize(843,462);
			
			bp.setBackground(Color.white);
			a.setVisible(true);
			a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		}
	
			
		
		
	}

	

}
