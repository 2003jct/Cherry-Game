package awtassignment;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
class Intro
{
	JFrame f;
	JLabel l0,l1;
	Color qw=Color.blue,qw1=Color.red;
	Color chooseColor(Component r)
	{
		return JColorChooser.showDialog(r,"Choose",Color.CYAN);   
	}
	Intro()
	{
		f=new JFrame("Cherry Game");
	    l0=new JLabel("Cherry");
		l0.setBounds(30,40,300,300);
		l0.setForeground(Color.PINK);
		l0.setFont(new Font("Serial",Font.BOLD,40));
		l0.setFont(new Font("Serial",Font.ITALIC,40));
		f.add(l0);
		    l1=new JLabel("Game");
			l1.setBounds(80,90,300,300);
			l1.setForeground(Color.PINK);
			l1.setFont(new Font("Serial",Font.BOLD,40));
			l1.setFont(new Font("Serial",Font.ITALIC,40));
			f.add(l1);
		JButton b=new JButton("Start game");
		b.setBounds(80,300,100,60);
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.getContentPane().setBackground(Color.orange);
				f.setSize(300,300);
				f.getContentPane().removeAll();
				JButton s=new JButton("Click to select Color1");
				s.setFont(new Font("Serial",Font.PLAIN,12));
				s.setBounds(70,100,150,60);
				JButton s1=new JButton("Select Color2");
				s1.setBounds(70,100,150,60);
				s1.setFont(new Font("Serial",Font.PLAIN,12));
				f.add(s);
				JLabel o=new JLabel("");
				 o.setBounds(70,70,200,200);
				 f.add(o);
				s.addActionListener(new ActionListener()
						{
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								 qw=chooseColor(s);
								 if(qw==Color.green || qw==Color.white || qw==Color.gray || qw==Color.pink || qw==null)
								 {
									 o.setText("Choose another color");
									 return;
								 }
								 f.remove(s);
								 f.add(s1);
							}
					
						});
				s1.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						 qw1=chooseColor(s1);
						 if(qw1==Color.green || qw1==Color.white || qw1==Color.gray || qw1==Color.pink || qw1==qw || qw1==null)
						 {
							 o.setText("Choose another color");
							 return;
						 }
						 f.dispose();
						 new Snake(qw,qw1);
					}
			
				});
				 
			}
			
		});
		b.setBackground(Color.GREEN);
		f.getContentPane().setBackground(Color.black);;
		f.add(b);
		f.setSize(280,499);
		f.setLayout(null);
		f.setVisible(true);
	}
}
public class CherryGame {
     public static void main(String args[])
     {
    	 new Intro();
     }
}
