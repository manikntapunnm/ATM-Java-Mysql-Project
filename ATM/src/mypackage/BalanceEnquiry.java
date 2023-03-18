package mypackage;



import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

class BalanceEnquiry extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
    JTextField t1, t2;
    JButton b1, b2, b3;
    JLabel l1, l2, l3;
    String pin;

    BalanceEnquiry(String pin) {
        this.pin = pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("mypackage/icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(680,680, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 0, 675,675);
        add(l3);

        l1 = new JLabel();
        l1.setForeground(Color.magenta);
        l1.setFont(new Font("System", Font.BOLD, 20));

        b1 = new JButton("BACK");

        setLayout(null);

        l1.setBounds(170, 180, 400, 35);
        l3.add(l1);

        b1.setBounds(370, 290, 150, 35);
        l3.add(b1);
        Conn c1 = new Conn();
        int balance = 0;
        try{
            
            ResultSet rs = c1.s.executeQuery("select * from bank where pin = '"+pin+"'");
            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
        }catch(Exception e){
        	System.out.println(e);
        }
        
        l1.setText("Your Current Balance is Rs "+balance);

        b1.addActionListener(this);

        setSize(680,680);
        // setUndecorated(true);
         setLocation(50,0);
         setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Transactions(pin).setVisible(true);
    }

    public static void main(String[] args) {
        new BalanceEnquiry("").setVisible(true);
    }
}

