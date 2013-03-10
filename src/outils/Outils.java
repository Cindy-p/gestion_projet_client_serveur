package outils;

import java.util.Scanner;

public class Outils {

	private static Scanner clavier;

	public static String lireChaine(String message) {
		clavier = new Scanner(System.in);
		System.out.print(message);
		String s = clavier.next();
		return s;
	}

	public static int lireEntier(String message) {
		int i = 0;
		boolean ok;
		do {
			ok = true;
			try {
				System.out.print(message);
				clavier = new Scanner(System.in);
				i = clavier.nextInt();
			} catch (Exception e) {
				System.out.println("Mauvais format");
				ok = false;
			}
		} while (!ok);
		return i;
	}

	public static double lireDecimal(String message) {
		double d = 0;
		boolean ok;
		do {
			ok = true;
			try {
				System.out.print(message);
				clavier = new Scanner(System.in);
				d = clavier.nextDouble();
			} catch (Exception e) {
				System.out.println("Mauvais format");
				ok = false;
			}
		} while (!ok);
		return d;
	}

	public static void main(String[] args) {
		double d = lireDecimal("Saisir un decimal");
	}

}
