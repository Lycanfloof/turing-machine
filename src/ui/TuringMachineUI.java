package ui;

import java.util.Scanner;

import model.TuringMachine;

public class TuringMachineUI {

	private TuringMachine turingMachine;
	private Scanner scanner;
	
	public TuringMachineUI(TuringMachine turingMachine, Scanner scanner) {
		super();
		setTuringMachine(turingMachine);
		setScanner(scanner);
	}

	public TuringMachine getTuringMachine() {
		return turingMachine;
	}

	public void setTuringMachine(TuringMachine turingMachine) {
		this.turingMachine = turingMachine;
	}
	
	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	public void enterStatus() {
		
		System.out.println("Enter the machine's status (Character from A-Z).");
		char status = (scanner.nextLine().charAt(0));
		
		if(Character.isLetter(status)) {
			turingMachine.setStatus(Character.toUpperCase(status));
			System.out.println("The status has been changed.");
		}else {
			System.out.println("You entered an unvalid value.");
		}
		
	}
	
	public void enterTape() {
		System.out.println("Enter a range of 0's, 1's and _'s (Example: '101010_11_00').");
		char[] tape = scanner.nextLine().toCharArray();
		boolean isValid = true;
		for(int i = 0; i < tape.length-1 && isValid; i++) {
			isValid = (tape[i] == '0' || tape[i] == '1' || tape[i] == '_');
		}
		if(isValid) {
			 turingMachine.setTape(tape);
			 System.out.println("The tape has been changed.");
		}else System.out.println("You entered an unvalid set of values.");
	}
	
	public void enterCommand() {
		
		String message = "Enter a command 'ABCDE' where:\n";
		message += "A one of the following values: '0', '1', '_'.\n";
		message += "B is a letter from A to Z.\n";
		message += "C one of the following values: '0', '1', '_', '*'.\n";
		message += "D is either 'L' or 'R'.\n";
		message += "E is a letter from A to Z.";
		System.out.println(message);
		
		char[] commandArray = scanner.nextLine().toCharArray();
		
		boolean isEqualToPointer = commandArray[0] == turingMachine.getTape()[turingMachine.getPointer()];
		boolean isEqualToStatus = commandArray[1] == turingMachine.getStatus();
		boolean isEntryValid = (commandArray[2] == '0') || (commandArray[2] == '1') || (commandArray[2] == '_') || (commandArray[2] == '*');
		boolean isDirectionValid = (commandArray[3] == 'L') || (commandArray[3] == 'R');
		boolean isFinalStatusValid = Character.isLetter(commandArray[4]);
		
		if(isEqualToPointer && isEqualToStatus && isEntryValid && isDirectionValid && isFinalStatusValid)
			executeCommand(commandArray);
		else showAlert(isEqualToPointer, isEqualToStatus, isEntryValid, isDirectionValid, isFinalStatusValid);
		
	}
	
	public void executeCommand(char[] command) {
		if(command[2] != '*') turingMachine.setTapeValue(command[2]);
		
		movePointer(command[3]);
		
		turingMachine.setStatus(command[4]);
	}
	
	public void movePointer(char movement) {
		
		int pointer = turingMachine.getPointer();
		char[] tape = turingMachine.getTape();
		
		switch(movement) {
		case 'L':
			
			if(pointer <= 0) turingMachine.setPointer((tape.length-1) + pointer);
			else turingMachine.setPointer(pointer - 1);
			break;
			
		case 'R':
			
			if(pointer >= tape.length-1) turingMachine.setPointer(pointer - (tape.length-1));
			else turingMachine.setPointer(pointer + 1);
			break;
			
		}
	}
	
	public void showAlert(boolean isEqualToPointer, boolean isEqualToStatus, boolean isEntryValid, boolean isDirectionValid, boolean isFinalStatusValid) {
		String message = "The command could not be executed.\n";
		if(isEqualToPointer) message+= "The first parameter has to be equal to the pointer value.\n";
		if(isEqualToStatus) message+= "The second parameter has to be equal to the machine's status.\n";
		if(isEntryValid) message+= "The third parameter wasn't a valid value (You have to enter one of the following values: '0', '1', '_', '*').\n";
		if(isDirectionValid) message+= "The fourth parameter wasn't a valid value (You have to enter either 'L' or 'R').\n";
		if(isFinalStatusValid) message+= "The fifth parameter wasn't a valid value (You have to enter a letter from A to Z).\n";
		System.out.print(message);
	}
	
	public void menu() {
		System.out.println("Welcome.\n");
		boolean isRunning = true;
		int option;
		while(isRunning) {
			showTuringMachine();
			showMainMenu();
			option = scanner.nextInt();
			scanner.nextLine();
			switch(option) {
			case 1:
				enterStatus();
				break;
			case 2:
				enterTape();
				break;
			case 3:
				enterCommand();
				break;
			case 4:
				isRunning = false;
				break;
			}
		}
	}
	
	public void showTuringMachine() {
		System.out.println("Status: " + turingMachine.getStatus());
		String pointerMessage = " ";
		for(int i = 0; i < turingMachine.getPointer(); i++) {
			pointerMessage += "  ";
		}
		pointerMessage += "|";
		System.out.println(pointerMessage);
		System.out.println(String.valueOf(turingMachine.getTape()).replaceAll("", "|"));
	}
	
	public void showMainMenu() {
		System.out.println("\nEnter one of the following commands:");
		System.out.println("1) Set status.");
		System.out.println("2) Set tape.");
		System.out.println("3) Enter a command.");
		System.out.println("4) Exit the application.");
	}
	
}
