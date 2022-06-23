package model;

public class TuringMachine {

	private char status;
	private char[] tape;
	private int pointer;

	public TuringMachine(char status, char[] tape) {
		super();
		setStatus(status);
		setTape(tape);
		setPointer((tape.length - 1) / 2);
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public char[] getTape() {
		return tape;
	}

	public void setTape(char[] tape) {
		this.tape = tape;
		setPointer((tape.length - 1) / 2);
	}

	public int getPointer() {
		return pointer;
	}

	public void setPointer(int pointer) {
		this.pointer = pointer;
	}

	public void setTapeValue(char value) {
		tape[pointer] = value;
	}

}
