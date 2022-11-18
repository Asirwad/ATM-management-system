
package bank.management.system;

import org.jsoup.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.JOptionPane;

public class StatementPrinter {
    
    StatementPrinter(String statement,int balance){
        try{
            //parsing the html file to normal text
            String plainText = Jsoup.parse(statement).text();
            //creating a valid date format so as to give it to the filename 
            java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
            //getting current date and time
            java.util.Date date = new java.util.Date();
            String fileName = ""+formatter.format(date);
            String filePath = "C:\\Users\\asirw\\OneDrive\\Desktop\\statement"+fileName+".pdf";
            
            Document doc = new Document();
            PdfWriter.getInstance(doc,new java.io.FileOutputStream(filePath));
            
            doc.open();
            
            Paragraph heading = new Paragraph("CECTL BANK\n");
            doc.add(heading);
            Paragraph balPara = new Paragraph("Balance : Rs"+balance+"/-\n");
            doc.add(balPara);
            Paragraph para = new Paragraph(plainText);
            doc.add(para);
            
            doc.close();
            
            JOptionPane.showMessageDialog(null,"Mini statement downloaded successfully");
            }catch(Exception e){
                System.out.println(e);
            }
    }
}
