import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.Random;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
       JFrame frame= new JFrame("Bus booking system");
       JLabel snolb,emaillb,addresslb,mobilelb,genderlb,agelb,destiantionlb,sourcelb,bookingid;
       JTextField snotf,emailtf,addresstf,mobiletf,gendertf,agetf,destinationtf,sourcetf;
       JButton bookbtn,clearbtn,exitbtn;

        snolb= new JLabel("Sno:");
        emaillb= new JLabel("Email:");
        addresslb= new JLabel("Address:");
        mobilelb= new JLabel("Mobile:");
        genderlb= new JLabel("Gender:");
        agelb= new JLabel("Age:");
        destiantionlb= new JLabel("Destination:");
        sourcelb= new JLabel("Source:");
        bookingid = new JLabel();

        snotf= new JTextField();
        emailtf= new JTextField();
        addresstf= new JTextField();
        mobiletf= new JTextField();
        gendertf= new JTextField();
        agetf= new JTextField();
        destinationtf= new JTextField();
        sourcetf= new JTextField();

        clearbtn= new JButton("Clear");
        bookbtn = new JButton("Book now");
        exitbtn = new JButton("exit");

        snolb.setBounds(20, 30, 100, 40);
        emaillb.setBounds(20, 70, 100, 40);
        addresslb.setBounds(20, 110, 100, 40);
        mobilelb.setBounds(20, 150, 100, 40);
        genderlb.setBounds(280, 30, 100, 40);
        agelb.setBounds(280, 70, 100, 40);
        destiantionlb.setBounds(280, 110, 100, 40);
        sourcelb.setBounds(280, 150, 100, 40);
        bookingid.setBounds(50, 300, 450,40 );

        snotf.setBounds(120, 30, 150, 40);
        emailtf.setBounds(120, 70, 150, 40);
        addresstf.setBounds(120, 110, 150, 40);
        mobiletf.setBounds(120, 150, 150, 40);
        gendertf.setBounds(400, 30, 150, 40);
        agetf.setBounds(400, 70, 150, 40);
        destinationtf.setBounds(400, 110, 150, 40);
        sourcetf.setBounds(400, 150, 150, 40);

        clearbtn.setBounds(50, 200, 150,45);
        bookbtn.setBounds(220, 200, 150,45);
        exitbtn.setBounds(400, 200, 150,45);
        frame.add(bookingid);
         frame.add(clearbtn);
         frame.add(bookbtn);
         frame.add(exitbtn);

        frame.add(snolb);
        frame.add(emaillb);
        frame.add(addresslb);
        frame.add(mobilelb);
        frame.add(genderlb);
        frame.add(agelb);
        frame.add(destiantionlb);
        frame.add(sourcelb);

        frame.add(snotf);
        frame.add(emailtf);
        frame.add(addresstf);
        frame.add(mobiletf);
        frame.add(gendertf);
        frame.add(agetf);
        frame.add(destinationtf);
        frame.add(sourcetf);

        frame.setLayout(null);
       frame.setResizable(false);
       frame.setSize(600,560);
       frame.setVisible(true);

       clearbtn.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
              snotf.setText("");
              emailtf.setText("");
              addresstf.setText("");
              mobiletf.setText("");
              gendertf.setText("");
              agetf.setText("");
              destinationtf.setText("");
              sourcetf.setText("");

           }
       });
       bookbtn.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               if(snotf.getText().toString().isEmpty() ||
                       emailtf.getText().isEmpty()||
                       addresstf.getText().isEmpty()||
                       mobiletf.getText().isEmpty()||
                       gendertf.getText().isEmpty()||
                       agetf.getText().isEmpty()||
                       destinationtf.getText().isEmpty()||
                       sourcetf.getText().isEmpty())
               {
                   bookingid.setText("Please fill the details");
               }
               else{
                   String url= "jdbc:mysql://localhost:3306/busbooking";
                   String username= "root";
                   String password="";
                   try {
                       Connection connection= DriverManager.getConnection(url,username,password);
                       String sql = " insert into tickitbooking"
                               + " values (null, ?, ?, ?,?,?,?,?,?)";
                       PreparedStatement preparedStmt = connection.prepareStatement(sql);

                       preparedStmt.setString(1, emailtf.getText().toString());
                       preparedStmt.setString( 2, addresstf.getText().toString());
                       preparedStmt.setString(3, mobiletf.getText().toString());
                       preparedStmt.setString(4, gendertf.getText().toString());
                       preparedStmt.setString(5, agetf.getText().toString());
                       preparedStmt.setString(6, destinationtf.getText().toString());
                       preparedStmt.setString(7, sourcetf.getText().toString());
                       preparedStmt.setString(8, bookingid.getText().toString());
                       preparedStmt.execute();
                       System.out.println("db connected");
                   } catch (SQLException ex) {
                       throw new RuntimeException(ex + "Not connected");
                   }
                   Random random= new Random();
                   int id= random.nextInt(999999);
                   bookingid.setText("Your booking is confirmed and id"+id);
               }

           }
       });
    }
}