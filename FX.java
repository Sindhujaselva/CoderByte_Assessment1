package Fxtrading;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class FX {
    static List<ForeignExchange> ForeignExchanges = new ArrayList<>();
    static  float rate = 66;
     static int globaltradenumber = 1;


    public static class ForeignExchange {
//        private static int tradeCount = 0;

        private int tradeNo;
        private String customername;
        private String currencypair;
        private int amt;

        private float globalrate;


        public ForeignExchange(String customername, String currencypair, int amt, float globalrate, int tradeNo) {
            this.customername = customername;
            this.currencypair = currencypair;
            this.amt = amt;
//            this.tradeCount = tradeCount;
            this.tradeNo = tradeNo;
            this.globalrate = rate;

        }

        public int getTradeNo() {
            return tradeNo;
        }

        public String getCurrencypair() {
            return currencypair;
        }

        public String getCustomername() {
            return customername;
        }

        public double getAmt() {
            return amt;
        }

        public float getRate() {
            return rate;
        }

    }
        public static void bookTrade() {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the name:");
            String customerName = sc.next();

            System.out.println("Enter the currency:");

            String currencypair = sc.next();
            if (!currencypair.equalsIgnoreCase("USDINR")) {
                System.out.println("Invalid currency pair. Only USDINR is supported.");
                return;
            }
            System.out.println("ENter the amount to transfer:");
            int amount = sc.nextInt();

            System.out.println("Do you want to get Rate (Yes/No):");
            String answer = sc.next();
            int   inrAmount = (int) (amount * rate);

            if (answer.equalsIgnoreCase("Yes")) {
                System.out.print("Enter the rate:"+rate);
                System.out.println("you are transferred");

            }
            System.out.println("do you want to book /cancel:");
            String ans  = sc.next();
            if(ans.equalsIgnoreCase("Book")){
                System.out.println("Trade for USDINR has been booked with rate " + rate + " , The amount of Rs " +
                    inrAmount + " will be transferred in 2 working days to " + customerName + ".");
                ForeignExchange customer = new ForeignExchange(currencypair, customerName, inrAmount, rate,globaltradenumber);
                ForeignExchanges.add(customer);
                globaltradenumber ++;


            }
            else {
                System.out.println("Invalid action. Trade is Canceled.");


            }







//            System.out.println("Trade for USDINR has been booked with rate " + rate + " , The amount of Rs " +
//                    (inrAmount) + " will be transferrifed in 2 working days to " + customerName + ".");
//

        }

        private static void printTrades() {
            if (ForeignExchanges.size() > 0) {


                System.out.println("TradeNo CurrencyPair CustomerName Amount   Rate ");
                for (ForeignExchange a : ForeignExchanges) {
                    System.out.println( a.getTradeNo() + " " + a.getCurrencypair() + " " + a.getCustomername() + " " + a.getAmt() + "" + a.getRate());
                }
            } else {
                System.out.println("No customer data available");
            }
        }

//        private static String formatCurrency(double amount) {
//            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
//            return currencyFormat.format(amount);
//        }}

    public static void main(String[] args) {

        int option;
        char selectOption;
        int Exit = 1;
        Scanner sc = new Scanner(System.in);


        do {
            System.out.println("1. Book Trade");
            System.out.println("2. Print Trades");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    bookTrade();
                    break;
                case 2:

                    printTrades();
                    break;
                case 3:
                    System.out.print("Do you really want to exit (y/n): ");
                    String confirm = sc.next();
                    if (confirm.equalsIgnoreCase("yes")) {
                        System.out.println("Bye - have a good day");
                        return;
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        } while (true);
    }
}
