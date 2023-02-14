package bank.management.atm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class SignUpTwo extends JFrame implements ActionListener{
    String formno,name;
    JButton next,cancel;
    JTextField panText,aadharText;
    JRadioButton syes,sno,eyes,eno;
    JComboBox religionBox,categoryBox,incomeBox,qualificationBox,ocupationBox;
    SignUpTwo(String formno,String name){
        this.formno = formno;
        this.name = name;
        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 2");
        setSize(500,620);
        setLocation(350,10);
        setResizable(false);
        //getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLUE));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(null);
        setContentPane(new JLabel(new ImageIcon(ClassLoader.getSystemResource("icons/signUpTwoBackground.png"))));

        //LABELS
        JLabel additionalDetails = new JLabel("Page 1: Additional Details ");
        additionalDetails.setFont(new Font("Railway",Font.BOLD,22));
        additionalDetails.setBounds(110,20,300,30);
        additionalDetails.setForeground(Color.WHITE);
        add(additionalDetails);

        JLabel religion = new JLabel("Religion");
        religion.setFont(new Font("Railway",Font.ROMAN_BASELINE,12));
        religion.setBounds(100,70,300,12);
        religion.setForeground(Color.WHITE);
        add(religion);

        String religionSet[] = {"Hindu","Muslim","Christian","Other"};
        religionBox = new JComboBox(religionSet);
        religionBox.setBackground(Color.WHITE);
        religionBox.setFont(new Font("Railway",Font.BOLD,14));
        religionBox.setBounds(100,85,300,30);
        religionBox.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        add(religionBox);

        JLabel category = new JLabel("Category");
        category.setFont(new Font("Railway",Font.ROMAN_BASELINE,12));
        category.setBounds(100,125,300,12);
        category.setForeground(Color.WHITE);
        add(category);

        String categorySet[] = {"General","OBC","SC","ST","Other"};
        categoryBox = new JComboBox(categorySet);
        categoryBox.setFont(new Font("Railway",Font.BOLD,14));
        categoryBox.setBackground(Color.WHITE);
        categoryBox.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        categoryBox.setBounds(100,140,300,30);
        add(categoryBox);

        JLabel income = new JLabel("Income ");
        income.setFont(new Font("Railway",Font.ROMAN_BASELINE,12));
        income.setBounds(100,175,300,12);
        income.setForeground(Color.WHITE);
        add(income);

        String incomeSet[] = {"NULL","1,50,000","2,50,000","<5,00,000","Up to 10,00,000"};
        incomeBox = new JComboBox(incomeSet);
        incomeBox.setBackground(Color.WHITE);
        incomeBox.setFont(new Font("Railway",Font.BOLD,14));
        incomeBox.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        incomeBox.setBounds(100,190,300,30);
        add(incomeBox);

        JLabel education = new JLabel("Educational Qualification");
        education.setFont(new Font("Railway",Font.ROMAN_BASELINE,12));
        education.setBounds(100,225,300,12);
        education.setForeground(Color.WHITE);
        add(education);

        String qualificationSet[] = {"Non Graduate","Graduate","Post Graduate","Doctrate","Others"};
        qualificationBox = new JComboBox(qualificationSet);
        qualificationBox.setFont(new Font("Railway",Font.BOLD,14));
        qualificationBox.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        qualificationBox.setBackground(Color.WHITE);
        qualificationBox.setBounds(100,240,300,30);
        add(qualificationBox);

        JLabel ocupation = new JLabel("Ocupation");
        ocupation.setFont(new Font("Railway",Font.ROMAN_BASELINE,12));
        ocupation.setBounds(100,280,300,15);
        ocupation.setForeground(Color.WHITE);
        add(ocupation);

        String ocupationSet[] = {"Salaried","Self employed","Bussiness","Student","Retired","Others"};
        ocupationBox = new JComboBox(ocupationSet);
        ocupationBox.setFont(new Font("Railway",Font.BOLD,14));
        ocupationBox.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        ocupationBox.setBackground(Color.WHITE);
        ocupationBox.setBounds(100,295,300,30);
        add(ocupationBox);

        JLabel pan = new JLabel("PAN Number");
        pan.setFont(new Font("Railway",Font.ROMAN_BASELINE,12));
        pan.setBounds(100,340,300,12);
        pan.setForeground(Color.WHITE);
        add(pan);

        panText = new JTextField();
        panText.setFont(new Font("Railway",Font.BOLD,14));
        panText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        panText.setBounds(100,355,300,30);
        add(panText);

        JLabel senior = new JLabel("Are you a senior Citizen?");
        senior.setFont(new Font("Railway",Font.ROMAN_BASELINE,12));
        senior.setBounds(100,390,300,12);
        senior.setForeground(Color.WHITE);
        add(senior);

        syes = new JRadioButton("Yes");
        syes.setFont(new Font("Railway",Font.BOLD,14));
        syes.setBackground(new Color(76,111,191));
        syes.setFocusable(false);
        syes.setForeground(Color.WHITE);
        syes.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        syes.setBounds(100,405,80,20);
        add(syes);
        sno = new JRadioButton("No");
        sno.setFont(new Font("Railway",Font.BOLD,14));
        sno.setBackground(new Color(76,111,191));
        sno.setFocusable(false);
        sno.setForeground(Color.WHITE);
        sno.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        sno.setBounds(180,405,80,20);
        add(sno);
        ButtonGroup seniorButtonGroup = new ButtonGroup();
        seniorButtonGroup.add(syes);
        seniorButtonGroup.add(sno);

        JLabel exAcnt = new JLabel("Existing Account");
        exAcnt.setFont(new Font("Railway",Font.ROMAN_BASELINE,12));
        exAcnt.setBounds(100,440,300,12);
        exAcnt.setForeground(Color.WHITE);
        add(exAcnt);

        eyes = new JRadioButton("Yes");
        eyes.setFont(new Font("Railway",Font.BOLD,14));
        eyes.setBackground(new Color(76,111,191));
        eyes.setFocusable(false);
        eyes.setForeground(Color.WHITE);
        eyes.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        eyes.setBounds(100,460,80,20);
        add(eyes);
        eno = new JRadioButton("No");
        eno.setFont(new Font("Railway",Font.BOLD,14));
        eno.setBackground(new Color(76,111,191));
        eno.setFocusable(false);
        eno.setForeground(Color.WHITE);
        eno.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        eno.setBounds(180,460,80,20);
        add(eno);
        ButtonGroup exAcntButtonGroup = new ButtonGroup();
        exAcntButtonGroup.add(eyes);
        exAcntButtonGroup.add(eno);

        JLabel aadhar = new JLabel("Aadhar Number ");
        aadhar.setFont(new Font("Railway",Font.ROMAN_BASELINE,12));
        aadhar.setBounds(100,490,300,12);
        aadhar.setForeground(Color.WHITE);
        add(aadhar);

        aadharText = new JTextField();
        aadharText.setFont(new Font("Railway",Font.BOLD,14));
        aadharText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        aadharText.setBounds(100,505,300,30);
        add(aadharText);

        next = new JButton("Next");
        next.setFont(new Font("Railway",Font.BOLD,14));
        next.setBounds(330,543,70,25);
        next.setFocusable(false);
        next.setBackground(new Color(123,150,212));
        next.setForeground(Color.WHITE);
        next.setBorderPainted(false);
        next.addActionListener(this);
        next.addMouseListener(new MouseAdapter(){
        @Override
        public void mouseEntered(MouseEvent evt){
            next.setBackground(Color.green);
        }
        @Override
        public void mouseExited(MouseEvent evt){
            next.setBackground(new Color(123,150,212));
        }
        });
        add(next);
        
        cancel = new JButton("Cancel");
        cancel.setFont(new Font("Railway",Font.BOLD,14));
        cancel.setBounds(100,543,82,25);
        cancel.setFocusable(false);
        cancel.setBackground(new Color(123,150,212));
        cancel.setForeground(Color.WHITE);
        cancel.setBorderPainted(false);
        cancel.addActionListener(this);
        cancel.addMouseListener(new MouseAdapter(){
        @Override
        public void mouseEntered(MouseEvent evt){
            cancel.setBackground(Color.YELLOW);
            cancel.setForeground(Color.BLACK);
        }
        @Override
        public void mouseExited(MouseEvent evt){
            cancel.setBackground(new Color(123,150,212));
            cancel.setForeground(Color.WHITE);
        }
        });
        add(cancel);

        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == next){
            String religion = (String)religionBox.getSelectedItem();
            String category = (String)categoryBox.getSelectedItem();
            String income = (String)incomeBox.getSelectedItem();
            String edu = (String)qualificationBox.getSelectedItem();
            String ocupation = (String)ocupationBox.getSelectedItem();
            String senior = null;
            if(syes.isSelected()){
                senior = "Yes";
            }
            else if(sno.isSelected()){
                senior = "No";
            }
            String exAcnt = null;
            if(eyes.isSelected()){ 
                exAcnt = "Yes";
            }
            else if(eno.isSelected()){
                exAcnt = "No";
            }
            String pan = panText.getText();
            String aadhar = aadharText.getText();

            try{
                if(pan.equals("")||aadhar.equals("")){
                    JOptionPane.showMessageDialog(null,"All fields are Mandatory");
                }
                else if(aadhar.length()!=12){
                    JOptionPane.showMessageDialog(null,"Pin should be 12 digits");
                }
                else if(Pattern.matches("[a-zA-Z]+", aadhar)){
                        JOptionPane.showMessageDialog(null,"Pin should contain only numbers");
                }
                else{
                    Conn c = new Conn();
                    String query = "insert into signuptwo values('"+formno+"','"+religion+"','"+category+"','"+income+"','"+edu+"','"+ocupation+"','"+pan+"','"+aadhar+"','"+senior+"','"+exAcnt+"');";
                    c.s.executeUpdate(query);

                    setVisible(false);
                    new SignUpThree(formno,name).setVisible(true);

                }
            }catch(SQLException ex1){
                System.out.println(ex1);
            }
        }else if(ae.getSource() == cancel){
            int choice = JOptionPane.YES_NO_OPTION;
            choice = JOptionPane.showConfirmDialog(null,"Are you sure want to cancel?","Warning",choice);
            if(choice == JOptionPane.YES_OPTION){
                try{
                    Conn conn = new Conn();
                    conn.s.executeUpdate("delete from signup where formno = '"+formno+"';");
                    setVisible(false);
                    new Login().setVisible(true);
                }catch(SQLException ex2){
                    System.out.println(ex2);
                }
            }
        }
    }
    public static void main(String args[]) {
       new SignUpTwo("","");
    }
}
