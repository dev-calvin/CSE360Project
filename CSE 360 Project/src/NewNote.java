import java.awt.Window;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

@SuppressWarnings("restriction")
public class NewNote {

	// arraylist of list objects
	List<ListItem> listItemArray;

	public NewNote(ListItemArray arrayList) {
		listItemArray = arrayList.listItemArray;
	}

	public void createNewNoteGUI() {
		VBox outerLayout = createOuterLayout();
		Main.newNote = new Scene(outerLayout, 1200, 800);
	}

	private VBox createOuterLayout() {
		VBox outerLayout = new VBox();
		HBox header = createHeader();
		VBox form = createForm();

		outerLayout.getChildren().addAll(header, form);

		return outerLayout;
	}

	private HBox createHeader() {
		Region left, right;
		Label newNote;

		HBox header = new HBox();
		header.setPadding(new Insets(15, 12, 15, 12));
		header.setSpacing(300);
		header.setStyle("-fx-background-color: #D7D7D7;");

		left = new Region();
		HBox.setHgrow(left, Priority.ALWAYS);
		right = new Region();
		HBox.setHgrow(right, Priority.ALWAYS);

		newNote = new Label("New Note");

		header.getChildren().addAll(left, newNote, right);
		return header;
	}

	private VBox createForm() {
		Region top, bottom;
		Label title, dueDay, dueMonth, priority;
		TextField titleField, priorityField;
		ComboBox<String> dueDaySelector, dueMonthSelector;
		Button create, cancel;

		VBox form = new VBox();
		form.setPadding(new Insets(10));
		form.setSpacing(8);
		form.setAlignment(Pos.BOTTOM_CENTER);

		HBox due = new HBox();
		due.setPadding(new Insets(15, 12, 15, 12));
		due.setSpacing(300);

		top = new Region();
		VBox.setVgrow(top, Priority.ALWAYS);

		title = new Label("Title");
		titleField = new TextField();

		dueMonth = new Label("Due Month");

		dueDaySelector = new ComboBox<String>();
		dueDaySelector.getItems().addAll(" ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
				"15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31");
		dueDaySelector.getSelectionModel().selectFirst();

		dueDay = new Label("Due Day");

		dueMonthSelector = new ComboBox<String>();
		dueMonthSelector.getItems().addAll(" ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
		dueMonthSelector.getSelectionModel().selectFirst();


		priority = new Label("Priority Number");

		priorityField = new TextField();

		bottom = new Region();
		VBox.setVgrow(bottom, Priority.ALWAYS);

		create = new Button("Create");
		create.setOnAction(e -> {
			validateRecord(titleField, dueMonthSelector, dueDaySelector, priorityField);
		});

		cancel = new Button("Cancel");
		cancel.setOnAction(e -> {
			Main.window.setScene(Main.list);
		});

		due.getChildren().addAll(dueMonth, dueMonthSelector, dueDay, dueDaySelector);
		form.getChildren().addAll(top, title, titleField, due, priority, priorityField, bottom, create, cancel);
		return form;
	}

	private void validateRecord(TextField titleField, ComboBox<String> dueMonthSelector,
			ComboBox<String> dueDaySelector, TextField priorityField) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		Alert successful = new Alert(Alert.AlertType.INFORMATION);
		String alerts = new String();
		
		System.out.println("1 " + titleField.getText().isEmpty());
		System.out.println("2 " + dueMonthSelector.getValue() == " ");
		System.out.println("3 " + dueDaySelector.getValue() == " ");
		System.out.println("4 " + !priorityField.getText().isEmpty());
		System.out.println("5 " +!priorityField.getText().matches("[0-9]+"));
		System.out.println("6 " + dateValidation(Integer.parseInt(dueMonthSelector.getValue()), Integer.parseInt(dueDaySelector.getValue())));

		
		if (titleField.getText().isEmpty() || dueMonthSelector.getValue() == " " || dueDaySelector.getValue() == " "
				|| priorityField.getText().isEmpty() || !priorityField.getText().matches("[0-9]+") || dateValidation(
						Integer.parseInt(dueMonthSelector.getValue()), Integer.parseInt(dueDaySelector.getValue()))) {
			
			if (titleField.getText().isEmpty()
					|| (titleField.getText().length() == 1 && titleField.getText().equals(" "))) {
				alerts = "Needs a title. \n";
			}
			
			if (dueMonthSelector.getValue() == " ") {
				alerts = alerts + "Needs a due month.\n";
			}
			
			if (dueDaySelector.getValue() == " ") {
				alerts = alerts + "Needs a due day.\n";
			} else if (dueDaySelector.getValue() != " " && Integer.parseInt(dueMonthSelector.getValue()) == 2
					&& dateValidation(Integer.parseInt(dueMonthSelector.getValue()),
							Integer.parseInt(dueDaySelector.getValue()))) {
				alerts = alerts + "February does not have more than 29 days \n";
			} else if (dueDaySelector.getValue() != " " && Integer.parseInt(dueMonthSelector.getValue()) > 2
					&& dateValidation(Integer.parseInt(dueMonthSelector.getValue()),
							Integer.parseInt(dueDaySelector.getValue()))) {
				alerts = alerts + "The date " + dueMonthSelector.getValue() + "/" + dueDaySelector.getValue() + " does not exist. \n";
			}
			
			/*this code is never reached*/
			if (priorityField.getText().isEmpty()) {
				alerts = alerts + "Needs a priority number";
			} else if (!priorityField.getText().matches("[0-9]+")) {
				alerts = alerts + "Priority can only contain numbers!";
			} else if (Integer.parseInt(priorityField.getText()) > (Main.arrayList.listItemArray.size() + 1)) {
				alerts = alerts + "Priority should not be more than than " + (Main.arrayList.listItemArray.size() + 1) + ".";
			}
			/**/

			alert.setTitle("Input Validation");
			alert.setHeaderText("Please correct the following:");
			alert.setContentText(alerts);
			alert.showAndWait();
		} else {
			Main.arrayList.newListItem(titleField.getText(), Integer.parseInt(dueMonthSelector.getValue()),
					Integer.parseInt(dueDaySelector.getValue()), Integer.parseInt(priorityField.getText()));
			
			//refreshes list and switches scenes
			FillList listScene = new FillList(Main.arrayList);
			listScene.createListGUI();
			Main.window.setScene(Main.list);
			
			titleField.clear();
			dueMonthSelector.setValue(" ");
			dueDaySelector.setValue(" ");
			priorityField.clear();
			successful.setTitle("Successful");
			successful.setHeaderText("Note successfully created!");
			successful.setContentText("Click \"OK\" to dismiss this message.");
			successful.showAndWait();
		}
	}

	public boolean dateValidation(int dueMonth, int dueDay) {
		boolean flag = false;

		if (dueMonth == 2 && dueDay > 29 || dueMonth == 4 && dueDay > 30 || dueMonth == 6 && dueDay > 30
				|| dueMonth == 9 && dueDay > 30 || dueMonth == 11 && dueDay > 30) {
			flag = true;
		}
		return flag;
	}
}