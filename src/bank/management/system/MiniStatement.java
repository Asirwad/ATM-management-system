
package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;


public class MiniStatement extends JFrame{
    String pinNo,cardNo;
    MiniStatement(String pinNo,String cardNo){
        this.pinNo = pinNo;
        this.cardNo = cardNo;
        setTitle("Mini ststement");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        
        Conn conn = new Conn();
        
        JLabel statement = new JLabel();
        add(statement);
        
        JLabel bankName = new JLabel("CECTL BANK");
        bankName.setBounds(130,20,200,20);
        bankName.setFont(new Font("Railway",Font.BOLD,19));
        add(bankName);
        
        JLabel card = new JLabel("Card Number : "+cardNo.substring(0,4)+"XXXXXXXX"+cardNo.substring(12));
        card.setBounds(20,80,300,20);
        add(card);
        
        JLabel text = new JLabel("<html><u>Details of last ten transactions</u></html>");
        text.setBounds(20,155,400,20);
        add(text);
        
        JLabel balanceLabel = new JLabel();
        balanceLabel.setBounds(20,110,300,20);
        add(balanceLabel);
        try{
            ResultSet rs = conn.s.executeQuery("select balance from account where cardno = '"+cardNo+"';");
            rs.next();
            int bal = rs.getInt("balance");
            balanceLabel.setText("Balance : Rs"+bal+"/-");
        }catch(SQLException ex){
            System.out.println(ex);
        }
        
        try{
            ResultSet rs = conn.s.executeQuery("select * from bank where cardno = '"+cardNo+"' order by date desc;");
            int i=10;
            while(rs.next() && i-->0){
                statement.setText(statement.getText() + "<html>" + rs.getString("date") +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount") + "<br><br><html>");
                
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        
        
        statement.setBounds(20,140,400,400);
        setSize(400,550);
        setResizable(false);
        setLocation(20,20);
        getContentPane().setBackground(Color.white);
        setVisible(true);
        
    }
    public static void main(String args[]) {
        new MiniStatement("","");
    }
}
