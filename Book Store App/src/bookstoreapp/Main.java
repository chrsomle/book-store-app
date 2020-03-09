package bookstoreapp;

import java.util.Scanner;

public class Main {

	public Main() throws InterruptedException{
		boolean isTerminated = false;
		
		Scanner s = new Scanner(System.in);
		
		String name[] = {
			"Comic",
			"Math Book",
			"Social Book",
			"Economic Book",
			"Geographic Book"
		};
		
		int qty[] = {121, 54, 54, 58, 60};
		int price[] = {20_000, 120_000, 80_000, 80_000, 100_000};
		
		int priceCart[] = {0, 0, 0, 0, 0};
		int qtyCart[] = {0, 0, 0, 0, 0};
		
		int choice = 0, choiceBook = 0;
		
		int grand = 0, qtyTemp = 0;
		
		do {
			for(int i=0; i<30; i++) System.out.println();
			System.out.println("                Chris Book Store                \n");
			System.out.println("------------------------------------------------");
			System.out.printf( "| %-3s | %-16s | %5s | %11s |\n", "No.", "Title", "Qty", "Price");
			System.out.println("------------------------------------------------");
			for(int i=0; i<name.length; i++) {
				System.out.printf("| %-3d | %-16s | %,5d | Rp %,8d |\n", i+1, name[i], qty[i], price[i]);
			}
			System.out.println("------------------------------------------------\n");
			System.out.println("1. Add to Shopping Cart");
			System.out.println("2. View Shopping Cart");
			System.out.println("3. Pay and Exit");
			System.out.println("Choose: ");
			do {
				choice = 0;
				try {
					System.out.print(">> ");
					choice = s.nextInt();
				} catch(Exception e){
					choice = 0;
				}
				s.nextLine();
			}while(choice < 1 || choice > 3);
			switch(choice) {
			case 1:
				do {
					choiceBook = 0;
					try {
						System.out.print("\nWhich book you want to add[1.."+name.length+"]? ");
						choiceBook = s.nextInt();
					} catch(Exception e) {
						choiceBook = 0;
					}
					s.nextLine();
				}while(choiceBook < 1 || choiceBook > name.length);
				if(qty[choiceBook-1] > 0) {
					do {
						qtyTemp = 0;
						System.out.print("\nHow many book you want to add[1.."+qty[choiceBook-1]+"]? ");
						try {
							qtyTemp = s.nextInt();
						} catch(Exception e) {
							qtyTemp = 0;
						}
						s.nextLine();
					}while(qtyTemp < 1 || qtyTemp > qty[choiceBook-1]);
					for(int i=0; i<40; i++) System.out.println();
					System.out.println("Purchase Information:");
					System.out.printf("%-8s: %s\n", "Title", name[choiceBook-1]);
					System.out.printf("%-8s: %,d\n", "Qty", qtyTemp);
					System.out.printf("%-8s: Rp %,d\n", "Total", price[choiceBook-1] * qtyTemp);
					byte isConfirmed = -1;
					do {
						isConfirmed = -1;
						System.out.print("Are those information correct[Yes/No]? ");
						String temp = s.nextLine();
						isConfirmed = (byte) (temp.equalsIgnoreCase("Yes") ? 1 : temp.equalsIgnoreCase("No") ? 0 : -1);
					}while(isConfirmed == -1);
					if(isConfirmed == 1) {
						qtyCart[choiceBook-1] += qtyTemp;
						qty[choiceBook-1] -= qtyTemp;
						priceCart[choiceBook-1] += qtyCart[choiceBook-1] * price[choiceBook-1];
						grand = 0;
						for(int i=0; i<priceCart.length; i++) grand += priceCart[i];
					}
				}
				else {
					System.out.println("The book you wanted to add has no stock left..");
					System.out.println("\nPress Enter to Continue..");
					s.nextLine();
				}
				break;
			case 2:
				boolean isEmpty = true;
				for(int i=0; i<name.length; i++) {
					if(qtyCart[i] > 0 && priceCart[i] > 0) {
						isEmpty = false;
						break;
					}
				}
				if(!isEmpty) {
					for(int i=0; i<40; i++) System.out.println();
					System.out.println("Shopping Cart");
					System.out.println("-----------------------------------------------");
					System.out.printf("| %-18s | %5s | %14s |\n", "Title", "Qty", "Total Price");
					System.out.println("-----------------------------------------------");
					for(int i=0; i<qtyCart.length; i++) {
						if(qtyCart[i] > 0) {
							System.out.printf("| %-18s | %,5d | Rp %,11d |\n", name[i], qtyCart[i], priceCart[i]);
						}
					}
					System.out.println("-----------------------------------------------");
					System.out.printf("\nGrand Total: Rp %,d\n", grand);
				}
				else {
					System.out.println("\nYou haven't added anything to the Shopping Cart..");
				}
				System.out.println("Press Enter to Continue..");
				s.nextLine();
				break;
			case 3:
				if(grand > 0) {
					for(int i=0; i<40; i++) System.out.println();
					System.out.printf("Grand Total: Rp %,d\n", grand);
					int payment = 0;
					do {
						payment = 0;
						try {
							System.out.print("Payment: ");
							payment = s.nextInt();
						} catch(Exception e) {
							payment = 0;
						}
						s.nextLine();
					}while(payment < 0);
					while(payment < grand){
						int temp = 0;
						System.err.printf("\nStill Lacking Rp %,d.", grand-payment);
						Thread.sleep(50);
						try {
							System.out.print("\nYou need to add more: ");
							temp = s.nextInt();
						} catch(Exception e) {
							temp = 0;
						}
						s.nextLine();
						payment += temp;
					};
					System.out.println("\nThank You!");
					if(payment > grand) {
						System.out.printf("Here's your change: Rp %,d\n", (payment-grand));
					}
					isTerminated = true;
				}
				else {
					System.out.println("\nYou haven't added anything to the Shopping Cart..");
					System.out.println("Press Enter to Continue");
				}
				if(isTerminated) System.out.println("\nPress Enter to Exit..");
				s.nextLine();
				break;
			}
		}while(!isTerminated);
		s.close();
	}
	
	public static void main(String[] args) throws InterruptedException {
		new Main();
	}
}
