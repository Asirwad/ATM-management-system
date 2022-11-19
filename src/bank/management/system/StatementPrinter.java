
package bank.management.system;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.sql.*;
import javax.swing.JOptionPane;

public class StatementPrinter {
    
    StatementPrinter(String cardNo){
        //creating a valid date format so as to give it to the filename 
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
        //getting current date and time
        java.util.Date date = new java.util.Date();
        String fileName = ""+formatter.format(date);
        String filePath = "C:\\Users\\asirw\\OneDrive\\Desktop\\statement"+fileName+".pdf";
        try{
            Document doc = new Document();
            PdfWriter.getInstance(doc,new java.io.FileOutputStream(filePath));
            doc.open();
            doc.add(new Paragraph("\t\t\t\t\tCECTL BANK\t"));
            Conn conn = new Conn();
            ResultSet rsBal = conn.s.executeQuery("select balance from account where cardno = '"+cardNo+"';");
            rsBal.next();
            int balance  = rsBal.getInt("balance");
            ResultSet rs = conn.s.executeQuery("select * from bank where cardno = '"+cardNo+"' order by date desc;");
            while(rs.next()){
                doc.add(new Paragraph("Date and time : "+ rs.getString("date")+"  "));
                doc.add(new Paragraph("Type : "+ rs.getString("type")+"  "));
                doc.add(new Paragraph("Amount : Rs" + rs.getString("amount")+"/-\n\n"));
            }
            doc.add(new Paragraph("Balance : Rs"+balance+"/-"));
            doc.close();
            JOptionPane.showMessageDialog(null,"Mini statement downloaded successfully");
        }catch(SQLException e){
             System.out.println(e);
        }catch(DocumentException e){
            System.out.println(e+"\nFailed to create document");
        }catch(FileNotFoundException e){
            System.out.println(e+"\nFailed to create pdf file");
        }
    }
}
