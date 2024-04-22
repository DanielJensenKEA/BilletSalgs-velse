import org.w3c.dom.ls.LSOutput;

import java.util.Random;
import java.util.Scanner;

public class UI {
    Scanner sc;
    int input = 0;
    int SENTINEL = 9;
    int dageTilEvent = 5;
    int dageRabatIndtrædelse = 10; //Rabat ved mere end 10 dage før event
    int dageDoorRate = 0;
    int ticketIDRandMAX = 99999;
    Random rand;
    SolgteBilletter salg = new SolgteBilletter();


    public UI() {
        sc = new Scanner(System.in);
        rand = new Random();
        startProgram();
    }

    public void startProgram() {
        setDaysTillEvent();

        while (input != SENTINEL) {
            displayMenu();
            input = sc.nextInt();
            sc.nextLine();
            switch (input) {
                case 1: {//Lave Billet
                    if (dageTilEvent == dageDoorRate) {
                        TicketGenerationDoor();
                        break;
                    } else {
                        TicketGeneration();
                        break;
                    }
                }
                case 2: {//Se liste af solgte billetter
                    if (salg.countNumOfTicketsStudRabat() == 0 && salg.countNumOfTicketsForsalg() == 0 && salg.countNumOfTicketsDoor() == 0) {
                        System.out.println("There are presently no registered ticket sales.");
                    } else {
                        seeListofSoldTickets();
                    }
                    break;
                }
                case 3: { //display collected Student IDS
                    if (salg.countNumOfTicketsStudRabat() == 0) {
                        System.out.println("There are presently no registered student IDs.");
                    } else {
                        displayStudentIDs();
                    }
                    break;
                }

            }

        }
    }
    public void setDaysTillEvent() {
        boolean loopBreakCondition = false;
        System.out.println("How many days are left until the event starts?");
        System.out.println("Default is 5 days which will trigger no additional discount. Ticket generation will be exclusively sales performed by the door.");
        System.out.println("If set to 10 or more additional options will be available to ticket sales.\n");


        while(true) {
            if(loopBreakCondition) {
                break;
            }
            System.out.println("1. Type any number to set the preferred days until the event starts.");
            System.out.println("2. Set to default.");
            System.out.print(">");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    System.out.print("Number of days: ");
                    dageTilEvent = sc.nextInt();
                    System.out.println("You have successfully set " + dageTilEvent + " as the amount of days until the event starts.");
                    loopBreakCondition = true;
                    break;
                }
                case 2: {
                    System.out.println("The number of days have been default been set to " + dageTilEvent + " days.");
                    loopBreakCondition = true;
                    break;
                }
                default: {
                    System.out.println("Please select one of the options given to you.");
                }
            }
        }
    }

    public void displayMenu() {
        System.out.println(" ");
        System.out.println("Type 1 to order a ticket.");
        System.out.println("Type 2 to see a list of sold tickets.");
        System.out.println("Type 3 to see a list of collected student ids.");
        System.out.println(" ");
    }

    public int generateTicketID() {
        return rand.nextInt(ticketIDRandMAX + 10000);
    }

    public void displayStudentIDs() {
        System.out.println("Student IDS listed from lowest to highest: \n");
        //salg.collectAndPrintAllStudentIDs();
        //System.out.println(salg.collectAndPrintAllStudentIDs());
        System.out.println(salg.collectAndPrintAllStudentIDS2());
    }

    public void seeListofSoldTickets() {
        System.out.println("Salg i døren: " + salg.countNumOfTicketsDoor());
        System.out.println("Forsalg: " + salg.countNumOfTicketsForsalg());
        System.out.println("Forsalg med studenterrabat: " + salg.countNumOfTicketsStudRabat());
    }

    public void TicketGenerationDoor() {

        int tempPris = 0;
        do {
            System.out.println("Please enter the name to be associated with the ticket.");
            String name = sc.nextLine();
            String nameSubStr = name.toUpperCase().charAt(0) + name.substring(1).toLowerCase();

            System.out.println("The ticket price will be 150,-. Would you like to continue your purchase? Type yes or no.");
            String choice = sc.nextLine().toLowerCase();

            if (choice.equals("yes")) {
                int billetID = generateTicketID();
                BilletDør BD1 = new BilletDør(nameSubStr, tempPris, billetID);
                BD1.BeregnBilletPris();
                salg.addTicketDoor(BD1);
                System.out.println("Ticket purchase completed.");
                System.out.println(BD1.udSkrivBillet());
                break; // Exit the loop if ticket purchase is completed
            } else {
                System.out.println("Returning to menu. Purchase not completed.");
                break; // Exit the loop if user chooses not to continue with ticket purchase
            }
        } while (true);
    }


    public void TicketGeneration() { //forsalg og studierabat
        boolean condition = true;
        boolean student = false;

        while (condition) {
            System.out.println("Please enter the name to be associated with the ticket.");
            String name = sc.nextLine();
            String nameSubStr = name.toUpperCase().charAt(0) + name.substring(1).toLowerCase();

            System.out.println("Are you a student? Type yes or no.");
            String choice = sc.nextLine().toLowerCase();
            if (choice.equals("yes")) {
                student = true;
            }

            int pris = 0;
            int dageFørKøb = dageTilEvent;

            if (student) {
                System.out.println("Please type in your Student ID.");
                int studentID = sc.nextInt();
                sc.nextLine();
                int ticketID = generateTicketID();
                BilletForsalgStudRabat BFSR1 = new BilletForsalgStudRabat(nameSubStr, pris, dageFørKøb, ticketID, studentID);
                BFSR1.BeregnBilletPris();

                System.out.println("The price for your ticket is " + BFSR1.getPris() + ". Would you like to continue? Type yes or no.");
                choice = sc.nextLine().toLowerCase();
                if (choice.equals("yes")) {
                    salg.addTicketForsalgStudRabat(BFSR1);
                    System.out.println(BFSR1.udSkrivBillet());
                } else {
                    System.out.println("Returning to menu. Purchase not completed.");
                }

            } else {
                int ticketID = generateTicketID();
                BilletForsalg10Dag BF10D = new BilletForsalg10Dag(nameSubStr, pris, dageFørKøb, ticketID);
                BF10D.BeregnBilletPris();

                System.out.println("The price for your ticket is " + BF10D.getPris() + ". Would you like to continue? Type yes or no.");
                choice = sc.nextLine().toLowerCase();
                if (choice.equals("yes")) {
                    salg.addTicketForsalg(BF10D);
                    System.out.println(BF10D.udSkrivBillet());
                } else {
                    System.out.println("Returning to menu. Purchase not completed.");
                }

            }
            condition = false;
        }

    }
}


/* //ticketGenerationDoor
        int tempPris = 0;
        System.out.println("Please enter the name to be associated with the ticket.");
        String name = sc.nextLine();
        //sc.nextLine();


        System.out.println("Hello " + name + ". The ticket price will be 150,-. Would you like to continue your purchase?");
        String choice = sc.nextLine().toLowerCase();
        //sc.nextLine();

        if (choice.equals("yes")) {
            int billetID = rand.nextInt(ticketIDRandMAX);
            BilletDør BD1 = new BilletDør(name, tempPris, billetID);
            BD1.BeregnBilletPris();
            salg.addTicketDoor(BD1);
            System.out.println("DEBUG: choice if sætning");
        } else {
            System.out.println("Returning to menu.");
            System.out.println("DEBUG: else sætning");
        }
 */

