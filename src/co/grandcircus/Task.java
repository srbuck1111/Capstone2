package co.grandcircus;

import java.util.Scanner;

public class Task {

	private String name;
	private String desc;
	private String dueDate;
	private boolean completed;

	// Constructors
	public Task() {
		name = null;
		desc = null;
		dueDate = null;
		completed = false;
	}

	public Task(String name, String desc, String dueDate) {
		this.name = name;
		this.desc = desc;
		this.dueDate = dueDate;
	}

	// Setters
	public void setName(String name) {
		this.name = name;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public void setCompletion(boolean completed) {
		this.completed = completed;
	}

	// Getters
	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}

	public String getDueDate() {
		return dueDate;
	}

	public boolean getCompletion() {
		return completed;
	}

	// Utility methods
	public static Task createTask() {
		String name;
		String desc;
		String dueDate;
		Scanner scan = new Scanner(System.in);
		name = Validator.getString(scan, "Enter team member name: ");
		desc = Validator.getString(scan, "Enter description: ");
		dueDate = Validator.getStringMatchingRegex(scan, "Enter due date (mm/dd/yyyy): ",
				"[0-1][0-9][/][0-3][0-9][/][0-9]{4}");
		System.out.println();
		return new Task(name, desc, dueDate);
	}

	@Override
	public String toString() {
		String completed = "incomplete";
		if (this.completed) {
			completed = "complete";
		}
		return String.format("%-20s %-12s %-12s %-100s \n%-20s %-12s %-12s %-100s" , "Team Member Name:" , "Due Date:", "Status:", "Description:" , name, dueDate, completed, desc);
	}
}