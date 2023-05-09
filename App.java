package com.example;
import java.util.Calendar;
import java.util.Date;

import org.fluttercode.datafactory.impl.DataFactory;
/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
            //call the functions and print the results
    int billerID = generatePMTSS_RPS_BILLER_ID();
    String billerName = generatePMTSS_RPS_BILLER_NAME();
    System.out.println("Biller ID: " + billerID);
    System.out.println("Biller Name: " + billerName);

    int paymentID = generatePMT_ID();
    String confirmNo = generatePMT_CONFIRM_NO();
    System.out.println("Payment ID: " + paymentID);
    System.out.println("Confirmation Number: " + confirmNo);


    Date billStartDate = generateBILL_START_DATE(); //random bill start date
    System.out.println("Bill Start Date: " + billStartDate);

    Date billDueDate = generateBILL_DUE_AMOUNT(billStartDate); //random bill due date
    System.out.println("Bill Due Date: " + billDueDate);

    Date paymentStartDate1 = generatePMT_START_DATE_bt(billStartDate, billDueDate); //on-time payment
    Date paymentStartDate2 = generatePMT_START_DATE_after(billDueDate); //late payment
    System.out.println("Payment Start Date (on-time): " + paymentStartDate1);
    System.out.println("Payment Start Date (late): " + paymentStartDate2);

    Date paymentCompleteDate1 = generatePMT_COMPLETE_DATE_after(paymentStartDate1); //on-time payment
    Date paymentCompleteDate2 = generatePMT_COMPLETE_DATE_after(paymentStartDate2); //late payment
    System.out.println("Payment Complete Date (on-time): " + paymentCompleteDate1);
    System.out.println("Payment Complete Date (late): " + paymentCompleteDate2);

    //create some sample amounts for testing
    int billAmount = generateBILL_AMOUNT();
    System.out.println("Bill Amount: " + billAmount);

    int paymentAmount1 = generatePMT_AMT_less(billAmount); //less amount paid
    int paymentAmount2 = generatePMT_AMT_equal(billAmount); //same amount paid
    System.out.println("Payment Amount (less): " + paymentAmount1);
    System.out.println("Payment Amount (equal): " + paymentAmount2);

    int paymentState = generatePMT_STATE_successful(); //successful payment state
    System.out.println("Payment State: " + paymentState);

    int billID = generateBILL_ID(); //random bill ID
    System.out.println("Bill ID: " + billID);

    String memberFirstName = generatePMTSS_MEM_FIRST_NAME(); //random member first name
    System.out.println("Member First Name: " + memberFirstName);

    String memberLastName = generatePMTSS_MEM_LAST_NAME(); //random member last name
    System.out.println("Member Last Name: " + memberLastName);
    }

    public static int generatePMTSS_RPS_BILLER_ID() { //random biller ID between 500000 and 999999.
        DataFactory df = new DataFactory();
        int billerID = df.getNumberBetween(500000, 999999);

        return billerID;
      }

    public static String generatePMTSS_RPS_BILLER_NAME(){ //random biller name from a predefined array of names.

        DataFactory df = new DataFactory();
        String[] names = {"aman", "deepanshu", "sam", "tom"};
        String random_name = df.getItem(names);
        return random_name;    
    }

    public static int generatePMT_ID() { //generates a random payment ID between 1060000000 and 1060000000. 
        DataFactory df = new DataFactory();
        int paymentID = df.getNumberBetween(1060000000, 1070000000);
        return paymentID;
      }

      public static String generatePMT_CONFIRM_NO(){ //generates a random text of 7 characters using a data factory.
        DataFactory df = new DataFactory();
        String random_text = df.getRandomChars(7);
        return random_text;
      }

        public static Date generatePMT_START_DATE_bt(Date BILL_START_DATE, Date BILL_DUE_DATE){ //generates a random on-time payment start date between the bill start date and the bill due date.
            DataFactory df = new DataFactory();

            Date minDate = BILL_START_DATE;
            Date maxDate = BILL_DUE_DATE;
            Date random_date = df.getDateBetween(minDate, maxDate);
            return random_date;
        }

        public static Date generatePMT_START_DATE_after(Date BILL_DUE_DATE){ //generates a random late payment start date by adding 30 days to the bill due date.
            DataFactory df = new DataFactory();

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(BILL_DUE_DATE);

            int delta = df.getNumberBetween(1, 29);
            calendar.add(Calendar.DATE, delta);
            Date random_date = calendar.getTime();
            return random_date;
        }

        public static Date generatePMT_COMPLETE_DATE_after(Date BILL_DUE_DATE){ //PMT start after due date
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(BILL_DUE_DATE);
            // Add 30 days to the calendar
            calendar.add(Calendar.DATE, 5);
            // Get the date after 30 days from the calendar
            Date random_date = calendar.getTime();
            return random_date;
        }

        public static int generatePMT_AMT_less(int BILL_AMOUNT) { //less amount paid
            DataFactory df = new DataFactory();
            int PMT_AMT = df.getNumberBetween(1, BILL_AMOUNT-2);
            return PMT_AMT;
        }
    
        public static int generatePMT_AMT_equal(int BILL_AMOUNT) { //same amount paid
            return BILL_AMOUNT;
        }

        public static int generatePMT_STATE_successful(){
            return 104;
        }

        public static int generateBILL_ID() {
            DataFactory df = new DataFactory();
            int billID = df.getNumberBetween(100, 100000);
            return billID;
        }

        public static Date generateBILL_START_DATE(){
            DataFactory df = new DataFactory();
            Date minDate = df.getDate(2022, 1, 1);
            Date maxDate = df.getDate(2022, 12, 31);
            Date random_date = df.getDateBetween(minDate, maxDate);

            return random_date;
        }

        public static Date generateBILL_DUE_AMOUNT(Date BILL_START_DATE){ //PMT start after due date
            Calendar calendar = Calendar.getInstance();

            calendar.setTime(BILL_START_DATE);
            calendar.add(Calendar.DATE, 20);

            Date random_date = calendar.getTime();
            return random_date;
        }

        public static int generateBILL_AMOUNT(){
            DataFactory df = new DataFactory();
            int billAMT = df.getNumberBetween(100, 100000);
            return billAMT;
        }

        public static String generatePMTSS_MEM_FIRST_NAME(){
            DataFactory df = new DataFactory();
            String random_first_name = df.getFirstName();
            return random_first_name;
        }

        public static String generatePMTSS_MEM_LAST_NAME(){
            DataFactory df = new DataFactory();
            String random_last_name = df.getLastName();
            return random_last_name;
        }
}