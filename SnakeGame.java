package awtassignment;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.Arrays;
import java.awt.GridLayout;
import javax.swing.*;

class Snake
{
	Color c1,c2;
	final int cherry[]= {6,27,45,59,70,93}; //contains number at which cherry is present
	JFrame f;
	JTextField tf,tf1; 
	JTextField l[]=new JTextField[100];   //array text fields
	JPanel panel;                           // contains array of text fields in grid form
	JButton b,b1,b2=new JButton("Home page"),b3=new JButton("Restart");   //b for player 1, b1 for player 2 , b2 for restart game
	JLabel q,w=new JLabel("");             //q is result label w is warning label or cherry label
	Color c[]=new Color[100];              //stores the color of each tile so that it can be recolored
	int x=6;    // randomly generated number will be stored in x
	int value1=0,value2=0;   //player 1 location on grid  player 2 location on grid
	boolean play=true;       //p1 is updated if true
	class Dice extends Canvas  //dice is painted
	{
		int y;
		public void paint(Graphics g) {
			setBackground(Color.white); 
			setForeground(Color.black); 
			g.draw3DRect(4, 2, 80, 60, true); 
			for(int i=0;i<y;i++)
			{
				if(i<3)
				g.fillOval(((3)*10), ((i+2)*10)%35+((i+2)*10)/5+2,10, 10); //for adjusting dots on dice
				else
			    g.fillOval(((6)*8), ((i+2)*10)%35+((i+2)*10)/5+1,10, 10);
					
			}
		}
	}
	Dice d=new Dice(); //for painting dice
	void p2()          //action for player 2 button
	{
		if(!play)
		{
		w.setText("");   //to remove warnings
		int x=(int) ((Math.random()*100)%6);   //random number is generated
		x++;
		if(d!=null)
		f.remove(d);                           // removing old dice graphic
        d=new Dice();                          //adding new dice graphic
	    d.y=x;
	    d.setBounds(340,350,90,65);
	    f.add(d);	
			tf1.setText(2+" : "+x+"");           //generated number is displayed
			if(value2!=0)
			{
				if(l[value2-1].getBackground()==Color.green) //if it is green(both) change it to blue(p1) as red(p2) left
				{
					l[value2-1].setBackground(c1);
				}
				else
				l[value2-1].setBackground(c[value2-1]); //changing color to default color
			}
			if(value2+x<=100) //adding generated number 
				value2+=x;
			// if it enters cherry value is updated with value displayed
			if(Arrays.binarySearch(cherry, value2)>=0 && Arrays.binarySearch(cherry, value2)<cherry.length) 
			{
				value2=(int)(((Math.random()*1000)%99)+1);
				w.setText("cherry sent you to "+value2);
			}
			l[value2-1].setBackground(c2); //set color to updated value
			if(value2==100) //game over
			{
				tf1.setText("click below"); //reseting text field
				l[value1-1].setBackground(c[value1-1]); //reseting background
				value1=0; //reseting values
				value2=0;
				play=true; //reseting play
				l[99].setBackground(c[99]); 
				removeall(); //making all components unvisible
				q=new JLabel("Player 2 won the match"); //displaying message
				q.setFont(new Font("Serial",Font.BOLD,20));
				q.setBounds(100,50,300,300);
				b2.setBounds(160,240,99,40); //restart button
				b3.setBounds(160,290,99,40);
				b3.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						restart();
						
					}
					
				});
				f.add(b3);
				f.add(b2);
				f.add(q);
				return;
			}
			play=!play; //to change chance to other player
			if(value1==value2) 
				l[value2-1].setBackground(Color.green); //if both are there color is green
		}
		else
		{
			w.setText("Its not your chance"); //if it is not player 2 chance but button is clicked
		}
	}
	void p1() //same as player 2
	{
		if(play)
		{
		  w.setText("");
		int x=(int) ((Math.random()*100)%6);
		x++;
		if(d!=null)
			f.remove(d);
	        d=new Dice();
		    d.y=x;
		d.setBounds(10,350,90,65);
		f.add(d);
			tf.setText(1+": "+x+"");
			if(value1!=0)
			{
				if(l[value1-1].getBackground()==Color.green)
				{
					l[value1-1].setBackground(c2);
				}
				else
				l[value1-1].setBackground(c[value1-1]);
			}
			if(value1+x<=100)
				value1+=x;
			if(Arrays.binarySearch(cherry, value1)>=0 && Arrays.binarySearch(cherry, value1)<cherry.length)
			{
				value1=(int)(((Math.random()*1000)%99)+1);
				w.setText("cherry sent you to "+value1);
			}
			l[value1-1].setBackground(c1);
			if(value1==100)
			{
				tf.setText("click below");
				l[value2-1].setBackground(c[value2-1]);
				value1=0;
				value2=0;
				play=true;
				l[99].setBackground(c[99]);
				removeall();
				q=new JLabel("Player 1 won the match");
				q.setFont(new Font("Serial",Font.BOLD,20));
				q.setBounds(100,50,300,300);
				b2.setBounds(160,240,99,40);
				b3.setBounds(160,290,99,40);
				b3.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						restart();
						
					}
					
				});
				f.add(b3);
				f.add(b2);
				f.add(q);
				return;
			}
		
		play=!play;
		if(value1==value2)
			l[value2-1].setBackground(Color.green);
		
	}
		else
			w.setText("Its not your chance");
	}
	JMenuBar mb;
	void addMenu()
	{
		mb=new JMenuBar();
		String s[]= {"Options"};
		JMenu m[]=new JMenu[s.length];
		for(int i=0;i<s.length;i++)
		{
			m[i]=new JMenu(s[i]);
			mb.add(m[i]);
		}
		f.setJMenuBar(mb);
		String z[][]= {{"Restart", "Exit"},};
	JMenuItem mi[][]=new JMenuItem[c.length][];
	for(int i=0;i<z.length;i++)
	{
		mi[i]=new JMenuItem[z[i].length];
		for(int j=0;j<z[i].length;j++)
		{
			mi[i][j]=new JMenuItem(z[i][j]);
			m[i].add(mi[i][j]);
		}
	}
	mi[0][1].addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e) {
					f.dispose();
					
				}
		
			});
	mi[0][0].addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e) {
					restart();
				}
		
			});
	}
	void addAll()
	{
		f.remove(b3);
		f.remove(b2);
		f.remove(q);
		panel.setVisible(true);
		b.setVisible(true);
		tf.setVisible(true);
		tf1.setVisible(true);
		b1.setVisible(true);
	}
	void restart()
	{
		if(!b.isVisible())
		{
			addAll();
			return;
		}
		f.remove(d);
		tf.setText("Click below");
		tf1.setText("Click below");
		if(value1!=0)
		{
			l[value1-1].setBackground(c[value1-1]);
		}
		if(value2!=0)
		{
			l[value2-1].setBackground(c[value2-1]);
		}
		value1=0;
		value2=0;
		play=true;
		w.setText("");
	}
	Snake(Color a1,Color a2)
	{
		c1=a1;c2=a2;
		f=new JFrame("Cherry Game"); //cherry box gives a random number
		addMenu();
		w.setBounds(160,-40,150,100);
		f.add(w);
		panel =new JPanel();	//contains numbers
		int k=0;	              //setting colors on condition
		for(int i=0;i<100;i++)
		{
			l[i]=new JTextField((i+1)+"");
			if((i+1)%2==k)
			{
			l[i].setBackground(Color.gray);
			c[i]=Color.gray;
			}
			else
			{
				l[i].setBackground(Color.white);
				c[i]=Color.white;
			}
			if((i+1)%10==0)
			{
				if(k==0)
					k=1;
				else
					k=0;
			}
			l[i].setEditable(false);
			panel.add(l[i]);
		}
		for(int i=0;i<cherry.length;i++)   //adding cherries to board
		{
			l[cherry[i]-1].setBackground(Color.PINK);
			c[cherry[i]-1]=Color.PINK;
			l[cherry[i]-1].setText("  🍒 ");
	     }
		// adding all elements and restarts
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
	              new Intro();
			}
			
		});
		b=new JButton("P1");
		b.setBounds(120,390,50,40);
		tf=new JTextField("Click below");
		tf.setEditable(false);
		tf.setBounds(120,330,70,50);
		tf1=new JTextField("Click below");
		tf1.setEditable(false);
		tf1.setBounds(260,330,70,50);
		tf.setBackground(c1);
		tf1.setBackground(c2);
		b.setBackground(c1);
		f.add(tf);
		f.add(tf1);
		b1=new JButton("P2");
		b1.setBounds(260,390,50,40);
		b1.setBackground(c2);
		//player2
		b1.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e) {
						p2();
					}
			
				});
		f.add(b1);
		//player 1
		b.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						p1();
					}
			
				});
		f.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				
				f.dispose();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		f.getContentPane().setBackground(Color.magenta);
		f.add(b);
        panel.setBounds(80,20,300,300);
		panel.setLayout(new GridLayout(10,10));
		
		f.setSize(500, 500);
		f.add(panel);
		f.setLayout(null);
		
		f.setVisible(true);
	}
	void removeall() //makes all elements invisible
	{
		panel.setVisible(false);
		if(d!=null)
		f.remove(d);
		tf1.setVisible(false);
		tf.setVisible(false);
		b.setVisible(false);
		b1.setVisible(false);
	}
}
public class SnakeGame {
          public static void main(String args[])
          {
        	  new Snake(Color.blue,Color.red);
          }
}
