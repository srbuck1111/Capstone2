package co.grandcircus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskHandler {

	public static void main(String[] args) {

		List<Task> tasks = new ArrayList<>();

		Scanner scan = new Scanner(System.in);

		int ans = 0;

		tasks.add(new Task("Nina", "Berate Matt", "10/10/2020"));
		tasks.add(new Task("Matt", "Get human rights back", "04/20/4000"));
		tasks.add(new Task("Sean", "Finish this program", "10/27/2019"));

		while (ans != 8) {

			ans = Validator.getInt(scan, printOptions() + "Choice: ", 1, 8);
			System.out.println();
			
			int choice = 0;

			switch (ans) {

			case 1:
				if (tasks.size() > 0) {
					listTasks(tasks);
				} else {
					System.out.println("\nNo tasks available to be listed.");
				}
				break;
			case 2:
				tasks.add(Task.createTask());
				break;
			case 3:
				if (tasks.size() > 0) {
					listTasks(tasks);
					choice = Validator.getInt(scan, "Which task is to be deleted? (enter 0 for none) ", 0,
							tasks.size());
					if (choice > 0) {
						tasks.remove(choice - 1);
					}
				} else {
					System.out.println("\nNo tasks available to be deleted.");
				}
				System.out.println();
				break;
			case 4:
				choice = 0;
				if (tasks.size() > 0) {
					listTasks(tasks);
					choice = Validator.getInt(scan, "Which task is to be marked complete? (enter 0 for none) ", 0,
							tasks.size());
					if (choice > 0) {
						tasks.get(choice - 1).setCompletion(true);
					}
				} else {
					System.out.println("\nNo tasks available to be marked complete.");
				}
				System.out.println();
				break;
			case 5:
				choice = 0;
				if (tasks.size() > 0) {
					listTasks(tasks);
					choice = Validator.getInt(scan, "\nWhich task is to be modified? (enter 0 for none) ", 1,
							tasks.size());
					if (choice > 0) {
						modifyTask(tasks.get(choice - 1));
					}
				} else {
					System.out.println("\nNo tasks available to be modified.");
				}
				break;
			case 6:
				System.out.println("\nWhose tasks are to be listed? (enter 0 for none)");
				for (int i = 0; i < getNames(tasks).size(); i++) {
					System.out.println((i + 1) + ". " + getNames(tasks).get(i));
				}
				int nameChoice = Validator.getInt(scan, "choice: ", 0, getNames(tasks).size());
				if (nameChoice != 0) {
					System.out.println();
					String name = getNames(tasks).get(nameChoice - 1);
					for (Task task : tasks) {
						if (task.getName().equals(name)) {
							System.out.println("Task " + (tasks.indexOf(task) + 1) + ".\n" + task.toString() + "\n");
						}
					}
				}
				break;
			case 7:
				String dateFilter = Validator.getStringMatchingRegex(scan, "\nEnter due date (mm/dd/yyyy): ",
						"[0-1][0-9][/][0-3][0-9][/][0-9]{4}");
				System.out.println();
				String[] dateArrString = dateFilter.split("/");
				int[] dateArr = {Integer.parseInt(dateArrString[0]), Integer.parseInt(dateArrString[1]), Integer.parseInt(dateArrString[2])};
				for (Task task : tasks) {
					String[] taskDateArrString = task.getDueDate().split("/");
					int[] taskDateArr = {Integer.parseInt(taskDateArrString[0]), Integer.parseInt(taskDateArrString[1]), Integer.parseInt(taskDateArrString[2])};
					if (taskDateArr[2] > dateArr[2]) {
						System.out.println("Task " + (tasks.indexOf(task) + 1) + ".\n" + task.toString() + "\n");
					} else if (taskDateArr[2] == dateArr[2]) {
						if (taskDateArr[1] > dateArr[1]) {
							System.out.println("Task " + (tasks.indexOf(task) + 1) + ".\n" + task.toString() + "\n");
						} else if (taskDateArr[1] == dateArr[1]) {
							if (taskDateArr[0] >= dateArr[0]) {
								System.out.println("Task " + (tasks.indexOf(task) + 1) + ".\n" + task.toString() + "\n");
							}
						}
					}
				}
				break;
			case 8:
				System.out.println("\nGoodbye.");
				break;
			default:
				System.out.println("\ninput not recognized, how did you even get here?");
				break;
			}

		}

		scan.close();

	}

	public static String printOptions() {
		return "1. List Tasks\n2. Add Task\n3. Delete Task\n4. Mark Task Complete\n5. Modify Task\n6. List Tasks by Team Member\n7. List Tasks before date\n8. Quit\n";
	}

	private static void listTasks(List<Task> tasks) {

		for (Task t : tasks) {
			System.out.println("Task: " + (tasks.indexOf(t) + 1) + "\n" + t.toString() + "\n");
		}
	}

	public static Task modifyTask(Task task) {
		Scanner scan = new Scanner(System.in);
		int valChoice = 5;
		do {
			valChoice = Validator.getInt(scan,
					"What value are you modifying?\n1. Team Member Name\n2. Description\n3. Due Date\n4. Completion Status\n5. Stop Modifying\nAnswer: ",
					1, 5);
			System.out.println();
			switch (valChoice) {
			case 1:
				task.setName(Validator.getString(scan, "Enter new Name: "));
				break;
			case 2:
				task.setDesc(Validator.getString(scan, "Enter new Description: "));
				break;
			case 3:
				task.setDueDate(Validator.getStringMatchingRegex(scan, "Enter due date (mm/dd/yyyy): ",
						"[0-1][0-9][/][0-3][0-9][/][0-9]{4}"));
				break;
			case 4:
				int compChoice = Validator.getInt(scan, "1. Task complete\n2. Task incomplete\n3. Cancel\nAnswer: ", 1,
						3);
				System.out.println();
				switch (compChoice) {
				case 1:
					task.setCompletion(true);
					break;
				case 2:
					task.setCompletion(false);
					break;
				case 3:
					break;
				}
				break;
			case 5:
				break;
			}
		} while (valChoice != 5);
		scan.close();
		return task;
	}

	public static ArrayList<String> getNames(List<Task> tasks) {

		ArrayList<String> names = new ArrayList<>();

		for (Task task : tasks) {

			if (!names.contains(task.getName())) {

				names.add(task.getName());

			}

		}

		return names;

	}

}
