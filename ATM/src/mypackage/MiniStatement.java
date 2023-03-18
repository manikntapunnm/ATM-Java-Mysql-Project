package mypackage;



import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.LinkedList;

public class MiniStatement extends JFrame implements ActionListener{
 
    
	private static final long serialVersionUID = 1L;
	JButton b1, b2;
    JLabel l1;
    MiniStatement(String pin){
        super("Mini Statement");
        getContentPane().setBackground(Color.white);
        setSize(400,700);
        setLocation(20,20);
        
        l1 = new JLabel();
        add(l1);
        
        JLabel l2 = new JLabel("Union Bank");
        l2.setBounds(140, 40, 150, 20);
        l2.setForeground(Color.red);
        l2.setFont(new Font("Arial", Font.BOLD, 20));
        add(l2);
        
        JLabel l3 = new JLabel();
        l3.setBounds(20, 80, 300, 20);
        l3.setForeground(Color.black);
        add(l3);
        
        JLabel l4 = new JLabel();
        l4.setBounds(20, 520, 300, 20);
        l4.setForeground(Color.blue);
        l4.setFont(new Font("Arial", Font.BOLD, 20));
        add(l4);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from login where pin = '"+pin+"'");//
            while(rs.next()){
                l3.setText("Card Number:    " + rs.getString("cardno").substring(0, 4) + "XXXXXXXX" + rs.getString("cardno").substring(12));
            }
        }catch(Exception e){
        	System.out.println(e);
        }/*its working correctly
        
        try{
        	
            int balance = 0;
            Conn c1  = new Conn();
            ResultSet rs = c1.s.executeQuery("SELECT * FROM bank WHERE pin = '"+pin+"' ORDER BY date DESC LIMIT 10 OFFSET 0");
            while(rs.next()){
                l1.setText(l1.getText() + "<html>"+rs.getString("date")+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount") + "<br><br><html>");
            	//l1.setText("<html>" + rs.getString("date")+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount") + "<br><br>" + l1.getText());
                if(rs.getString("type").equals("Deposit")){
                    balance += Integer.parseInt(rs.getString("amount"));
                }else{
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
            l4.setText("Current Balance Rs "+balance);
        }catch(Exception e){
            e.printStackTrace();
           
        }*/
        try {
            int balance = 0;
            Conn c1 = new Conn();
            ResultSet rs = c1.s.executeQuery("SELECT * FROM bank where pin = '"+pin+"' ORDER BY date DESC LIMIT 10");

            // create a linked list to store the transactions in reverse order
            LinkedList<String> transactions = new LinkedList<String>();

            while(rs.next()) {
                // add each transaction to the beginning of the linked list
                transactions.addFirst("<html>"+rs.getString("date")+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount") + "<br><br><html>");

                if(rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }

            // iterate over the transactions in reverse order and append them to the label
            for(String transaction : transactions) {
                l1.setText(l1.getText() + transaction);
            }

            l4.setText("Current Balance Rs "+balance);
        } catch(Exception e) {
            e.printStackTrace();
        }


        
        setLayout(null);
        b1 = new JButton("Exit");
        b1.setBackground(Color.GRAY);
        b1.setForeground(Color.white);
        add(b1);
        
        b1.addActionListener(this);
        
        l1.setBounds(20, 140, 400, 310);
        l1.setFont(new Font("Arial", Font.BOLD, 12));
        l1.setForeground(Color.GRAY);
        b1.setBounds(20, 600, 100, 25);
    }
    public void actionPerformed(ActionEvent ae){
        this.setVisible(false);
    }
    
    public static void main(String[] args){
        new MiniStatement("").setVisible(true);
    }
    
}

