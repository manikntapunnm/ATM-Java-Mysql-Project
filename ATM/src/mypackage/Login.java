package mypackage;
//for image package..
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
//For action listener
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicButtonUI;
// which implements action through action listener
public class Login extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
//global variables
    JLabel l1,l2,l3;
    JTextField tf1;
    JPasswordField pf2;
    JButton b1,b2,b3;
   /* public class RoundBorder implements Border {

        private int radius;

        public RoundBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(Color.gray);
            g.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }*/
    
    
    public class RoundedButtonUI extends BasicButtonUI {
	    private final Color background;

	    public RoundedButtonUI(Color background) {
	        this.background = background;
	    }

	    @Override
	    public void installUI(JComponent c) {
	        super.installUI(c);
	        AbstractButton button = (AbstractButton) c;
	        button.setOpaque(false);
	       // button.setBorder(new RoundBorder(10));
	        
	    }

	    @Override
	    public void paint(Graphics g, JComponent c) {
	        AbstractButton button = (AbstractButton) c;
	        ButtonModel model = button.getModel();
	        boolean isPressed = model.isPressed();
	        boolean isRollover = model.isRollover();

	        int x = 0;
	        int y = 0;
	        int width = button.getWidth();
	        int height = button.getHeight();
	        int arc = 10;

	        if (isPressed) {
	            g.setColor(background.darker());
	        } else if (isRollover) {
	            g.setColor(background.brighter());
	        } else {
	            g.setColor(background);
	        }

	        g.fillRoundRect(x, y, width - 1, height - 1, arc, arc);

	        super.paint(g, button);
	    }
	}
    
  
    Login(){
    	
    	
    	class RoundBorder implements Border {
    	    private int radius;

    	    RoundBorder(int radius) {
    	        this.radius = radius;
    	    }

    	    public Insets getBorderInsets(Component c) {
    	        return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
    	    }

    	    public boolean isBorderOpaque() {
    	        return true;
    	    }

    	    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
    	        g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    	    }
    	}
    	//which sets title of frame
        setTitle("UNION BANK ATM");
        
        //sets imageIcon as object with ClassLoader.getSystemResource
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("mypackage/icons/logo.jpg"));
        //image scaling (size)  with getScaledInstance
        Image i2 = i1.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        //To get rid of error with size and image at a time we use again object with i3
        ImageIcon i3 = new ImageIcon(i2);
        // which sets position and location
        JLabel l11 = new JLabel(i3);
        //location of image at top right corner
        l11.setBounds(360, 20, 90, 90);
        add(l11);
        
        //Label text
        l1 = new JLabel("WELCOME TO ATM");
        //font size
        l1.setFont(new Font("Osward", Font.BOLD, 35));
        //Position
        l1.setBounds(20,40,450,40);
        //Font color
        l1.setForeground(Color.white);
        // shows the text
        add(l1);
        
      //Label text
        l2 = new JLabel("Card No:");
      //font size
        l2.setFont(new Font("Raleway", Font.BOLD, 25));
      //Position
        l2.setBounds(20,150,375,30);
      //Font color
        l2.setForeground(Color.BLACK);
        add(l2);
        // object for text field with size
        tf1 = new JTextField(15);
      //Position of card text field
        tf1.setBounds(175,150,275,30);
        tf1.setFont(new Font("Arial", Font.BOLD, 15));
        add(tf1);
      //Label text 
        l3 = new JLabel("PIN No:");
      //font size
        l3.setFont(new Font("Raleway", Font.BOLD, 25));
      //Position
        l3.setBounds(20,220,375,30);
      //Font color
        l3.setForeground(Color.BLACK);
        add(l3);
        
        pf2 = new JPasswordField(15);
        pf2.setFont(new Font("Arial", Font.BOLD, 15));
        pf2.setBounds(175,220,275,30);
        add(pf2);
        
        // these are all global
       // buttons with black background with white foreground, position, size
        
        b1 = new JButton("SIGN IN");
       // b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("Arial", Font.BOLD, 14));
        b1.setBounds(20, 300, 120, 40);
        b1.addActionListener(this);//invokes action listener
       // b1.setBorder(new RoundBorder(10));
        b1.setUI(new RoundedButtonUI(Color.DARK_GRAY));
       // b1.setFocusPainted(false); // remove focus border
        add(b1);
        
        b2 = new JButton("CLEAR");
       // b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.setFont(new Font("Arial", Font.BOLD, 14));
        b2.setBounds(175, 300, 120, 40);//invokes action listener
        b2.addActionListener(this);
       // b2.setBorder(new RoundBorder(10));
        b2.setUI(new RoundedButtonUI(Color.DARK_GRAY));
       // b2.setFocusPainted(false); // remove focus border
        add(b2);
        
        b3 = new JButton("SIGN UP");
       // b3.setBackground(Color.black);
        b3.setForeground(Color.WHITE);
        b3.setFont(new Font("Arial", Font.BOLD, 14));
        b3.setBounds(330, 300, 120, 40);
        //invokes action listener
        b3.addActionListener(this);
        //b3.setBorder(new RoundBorder(20));
        b3.setUI(new RoundedButtonUI(Color.DARK_GRAY));
        //b3.setFocusPainted(false); // remove focus border
        add(b3);
        
     // which sets background color of frame as gray  
        getContentPane().setBackground(Color.LIGHT_GRAY);
     // to get rid of location problem because of set bounds function
        setLayout(null);
     // sets location of window fixes
        setLocation(250,80);      
     // below code creates frame and shows empty frame
        setSize(485,410);
        setVisible(true);
        
         
    }


 

    
    
    
    
    // action listener  and action event class's object  class
   
    public void actionPerformed(ActionEvent ae){
        try{        
            if(ae.getSource()==b1){//signin
            	// which connects mysql database
                Conn c = new Conn();
                String cardno  = tf1.getText();
                //String pin  = pf2.getText();
                char[] password = pf2.getPassword();
                String pin = new String(password);

                String q  = "select * from login where cardno = '"+cardno+"' and pin = '"+pin+"'";
                try {
                ResultSet rs = c.s.executeQuery(q);
                if(rs.next()){
                    setVisible(false);
                    new Transactions(pin).setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
                }
                }catch (Exception e) {
                	System.out.println(e);
                }
                
                
            }else if(ae.getSource()==b2){//clear
                tf1.setText("5040936089839994");//cardTextField
                pf2.setText("");//pinTextField
            }else if(ae.getSource()==b3){//signup
                setVisible(false);//current window closes
                new Signup().setVisible(true);//opens signup form
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
    
    public static void main(String[] args){
        new Login().setVisible(true);
    }

    
}





