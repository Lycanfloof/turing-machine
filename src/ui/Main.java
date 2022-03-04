package ui;

import java.util.Scanner;

import model.TuringMachine;

public class Main {

	public static void main(String[] args) {
		
		TuringMachine turingMachine = new TuringMachine('A', new char[] {'0', '1', '_'});
		Scanner scanner = new Scanner(System.in);
		TuringMachineUI turingMachineUI = new TuringMachineUI(turingMachine, scanner);
		turingMachineUI.menu();
		
	}

}
