package com.example;
import com.example.App;
import java.util.Date;
import java.util.Calendar;
import java.sql.*;;

public class save2db {
    save2db(){

    }
    
    public static void main(){

        App Appobj = new App();
        
        int billerID = Appobj.generatePMTSS_RPS_BILLER_ID(); 
        String billerName = Appobj.generatePMTSS_RPS_BILLER_NAME();
        int paymentID = Appobj.generatePMT_ID();
        String confirmNo = Appobj.generatePMT_CONFIRM_NO();
    
        Date billStartDate = Appobj.generateBILL_START_DATE(); //random bill start date
    
        Date billDueDate = Appobj.generateBILL_DUE_AMOUNT(billStartDate); //random bill due date
    
        Date paymentStartDate1 = Appobj.generatePMT_START_DATE_bt(billStartDate, billDueDate); //on-time payment
        Date paymentStartDate2 = Appobj.generatePMT_START_DATE_after(billDueDate); //late payment
        System.out.println("Payment Start Date (on-time): " + paymentStartDate1);
        System.out.println("Payment Start Date (late): " + paymentStartDate2);
    
        Date paymentCompleteDate1 = Appobj.generatePMT_COMPLETE_DATE_after(paymentStartDate1); //payment
        //Date paymentCompleteDate2 = generatePMT_COMPLETE_DATE_after(paymentStartDate2); //late payment
        System.out.println("Payment Complete Date (on-time): " + paymentCompleteDate1);
        //System.out.println("Payment Complete Date (late): " + paymentCompleteDate2);
    
        int billAmount = Appobj.generateBILL_AMOUNT();
        System.out.println("Bill Amount: " + billAmount);
    
        int paymentAmount1 = Appobj.generatePMT_AMT_less(billAmount); //less amount paid
        int paymentAmount2 = Appobj.generatePMT_AMT_equal(billAmount); //same amount paid
        System.out.println("Payment Amount (less): " + paymentAmount1);
        System.out.println("Payment Amount (equal): " + paymentAmount2);
    
        int paymentState = Appobj.generatePMT_STATE_successful(); //successful payment state
        System.out.println("Payment State: " + paymentState);
    
        int billID = Appobj.generateBILL_ID(); //random bill ID
        System.out.println("Bill ID: " + billID);
    
        String memberFirstName = Appobj.generatePMTSS_MEM_FIRST_NAME(); //random member first name
        System.out.println("Member First Name: " + memberFirstName);
    
        String memberLastName = Appobj.generatePMTSS_MEM_LAST_NAME(); //random member last name
        System.out.println("Member Last Name: " + memberLastName);
    }

    public void insertToH2(int billerID, String billerName, int paymentID, String confirmNo, Date billStartDate, Date billDueDate, Date paymentStartDate, Date paymentCompleteDate, int billAmount, int paymentAmount, int paymentState) {
        try {
            //create a connection object
            Connection conn = null;
            //create a statement object
            Statement stmt = null;

            //load the H2 driver class
            Class.forName("org.h2.Driver");
            //connect to the H2 database using the embedded mode
            conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            //create a statement object
            stmt = conn.createStatement();
            //create a SQL query to insert the variables into a table named PMTSS_RPS
            String sql = "INSERT INTO PMTSS_RPS (BILLER_ID, BILLER_NAME, PMT_ID, PMT_CONFIRM_NO, BILL_START_DATE, BILL_DUE_DATE, PMT_START_DATE, PMT_COMPLETE_DATE, BILL_AMT, PMT_AMT, PMT_STATE) VALUES (" + billerID + ", '" + billerName + "', " + paymentID + ", '" + confirmNo + "', '" + billStartDate + "', '" + billDueDate + "', '" + paymentStartDate + "', '" + paymentCompleteDate + "', " + billAmount + ", " + paymentAmount + ", " + paymentState + ")";
            //execute the query and get the number of rows affected
            int rows = stmt.executeUpdate(sql);
            //print the result
            System.out.println(rows + " row(s) inserted.");
        } catch (Exception e) {
            //handle any exceptions
            e.printStackTrace();
        } finally {
            //close the resources
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}