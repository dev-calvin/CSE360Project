import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

@SuppressWarnings("restriction")
public class EditNote {
	//arraylist of list objects
	List<ListItem> listItemArray;
	int selectedNote;
	
	boolean ticked;
	
	public EditNote (ListItemArray arrayList, int noteIndex) {
		listItemArray = arrayList.listItemArray;
		selectedNote = noteIndex;
	}
	
	public void createEditNoteGUI() {
		VBox outerLayout = new VBox();
		HBox noteDetails = createNoteDetails();
		HBox noteActions = createNoteActions();
		HBox editHeader = createEditHeader();
		VBox editForm = createEditForm();

		outerLayout.getChildren().addAll(noteDetails,noteActions,editHeader,editForm);
		
		Main.editNote = new Scene(outerLayout, 1200, 800);  
	}

	public VBox createEditForm() {
		CheckBox inProgressCB;
		Region bottom;
		Label title, dueDay, dueMonth, priority, inProgress, statusMonth, statusDay;
		TextField titleField, priorityField;
		ComboBox<String> dueDaySelector, dueMonthSelector, statusDaySelector, statusMonthSelector;
		Button save, cancel;
		
		VBox form = new VBox();
		form.setPadding(new Insets(10));
		form.setSpacing(50);
		form.setAlignment(Pos.BOTTOM_CENTER);
		
		HBox due = new HBox();
		due.setPadding(new Insets(15, 12, 15, 12));
		due.setSpacing(50);
		due.setAlignment(Pos.CENTER);
		
		HBox titleBox = new HBox();
		titleBox.setPadding(new Insets(15, 12, 15, 12));
		titleBox.setSpacing(50);
		titleBox.setAlignment(Pos.CENTER);
		title = new Label("Title");
		titleField = new TextField();
		titleField.setMaxWidth(300);
		titleBox.getChildren().addAll(title, titleField);
		
		dueMonth = new Label("Due Month");
		
		HBox statusBox = new HBox();
		statusBox.setPadding(new Insets(15, 12, 15, 12));
		statusBox.setSpacing(50);
		statusBox.setAlignment(Pos.CENTER);
				
		dueDaySelector = new ComboBox<String>();
		dueDaySelector.getItems().addAll(" ", "1","2","3","4","5", "6", "7", "8", "9", "10", "11", "12", "13",
	    		"14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
	    		"28", "29", "30", "31");
		dueDaySelector.getSelectionModel().selectFirst();
		
		dueDay = new Label("Due Day");
		
		dueMonthSelector = new ComboBox<String>();
		dueMonthSelector.getItems().addAll(" ", "1","2","3","4","5", "6", "7", "8", "9", "10", "11", "12");
		dueMonthSelector.getSelectionModel().selectFirst();
		
		HBox priorityBox = new HBox();
		priorityBox.setPadding(new Insets(15, 12, 15, 12));
		priorityBox.setSpacing(50);
		priorityBox.setAlignment(Pos.CENTER);
		priority = new Label("Priority Number");
		priorityField = new TextField();
		priorityField.setMaxWidth(50);
		priorityBox.getChildren().addAll(priority, priorityField);
				
		inProgressCB = new CheckBox();
		inProgressCB.setIndeterminate(false);
		inProgressCB.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue){
					ticked = true;

	            }else{
					ticked = false;
	            }				
			}
	    });
		
		inProgress = new Label("In Progress");
		
		statusMonth = new Label("Started Month");
		
		statusMonthSelector = new ComboBox<String>();
		statusMonthSelector.getItems().addAll("1","2","3","4","5", "6", "7", "8", "9", "10", "11", "12");
		
		statusDay = new Label("Started Day");
		
		statusDaySelector = new ComboBox<String>();
		statusDaySelector.getItems().addAll("1","2","3","4","5", "6", "7", "8", "9", "10", "11", "12", "13",
	    		"14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
	    		"28", "29", "30", "31");		
		
		
		bottom = new Region();
		VBox.setVgrow(bottom, Priority.ALWAYS);
		
		save = new Button("Save");
		save.setOnAction(e -> {
			validateForm(titleField, dueMonthSelector, dueDaySelector, priorityField, inProgressCB, statusMonthSelector, statusDaySelector);			
		});
		
		cancel = new Button("Cancel");
		cancel.setOnAction(e -> {
			Main.window.setScene(Main.list);
		});
		
		statusBox.getChildren().addAll(inProgress, inProgressCB, statusMonth, statusMonthSelector, statusDay, statusDaySelector);
		due.getChildren().addAll(dueMonth, dueMonthSelector, dueDay, dueDaySelector);
		form.getChildren().addAll(titleBox, due, priorityBox, statusBox, bottom, save, cancel);
		return form;
	}

	private void validateForm(TextField titleField, ComboBox<String> dueMonthSelector, ComboBox<String> dueDaySelector,
			TextField priorityField, CheckBox inProgressCB, ComboBox<String> statusMonthSelector,
			ComboBox<String> statusDaySelector) {
		//validates data and submits edits
		//TODO validate data
		
		
//		/*replacing idividual parts*/
//		Main.arrayList.listItemArray.get(selectedNote).setTitle(titleField.getText());
//		Main.arrayList.listItemArray.get(selectedNote).setDueDate(Integer.parseInt(dueMonthSelector.getValue()), Integer.parseInt(dueDaySelector.getValue()));
//		Main.arrayList.listItemArray.get(selectedNote).setPriorityNumber(Integer.parseInt(priorityField.getText()));
//		if(ticked) {
//			Main.arrayList.listItemArray.get(selectedNote).changeStatus("In Progress", Integer.parseInt(statusMonthSelector.getValue()), Integer.parseInt(statusDaySelector.getValue()));
//		}
//		/**/
		String priority = checkPriorityField(priorityField);
		Boolean unique = isUnique(titleField.getText());

		if (titleField.getText().isEmpty() || dueMonthSelector.getValue() == " " || dueDaySelector.getValue() == " "
				|| priority != "correct") {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			String alerts = new String();
			
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
			
			if(priority != "correct") {
				alerts += priority;
			}
			
			if(unique != true) {
				alerts += "Title must be unique.\n";
			}
			
			alert.setTitle("Edit Note Error");
			alert.setHeaderText("All fields must be filled in.\nPlease correct the following:");
			alert.setContentText(alerts);
			alert.showAndWait();			
			
		} else {
			/*replacing whole list item*/
			Main.arrayList.deleteListItem(selectedNote + 1);
			Main.arrayList.newListItem(titleField.getText(), Integer.parseInt(dueMonthSelector.getValue()), Integer.parseInt(dueDaySelector.getValue()), Integer.parseInt(priorityField.getText()));
						
			if(ticked) {
				Main.arrayList.listItemArray.get(Integer.parseInt(priorityField.getText())-1).changeStatus("In Progress", Integer.parseInt(statusMonthSelector.getValue()), Integer.parseInt(statusDaySelector.getValue()));
			}
			/**/
			
			//refreshes list and switches scenes
			FillList listScene = new FillList(Main.arrayList);
			listScene.createListGUI();
			Main.window.setScene(Main.list);
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
	
	private String checkPriorityField(TextField priorityField) {
		if (priorityField.getText().isEmpty()) {
			return "Needs a priority number";
		} else if (!priorityField.getText().matches("[0-9]+")) {
			return "Priority can only contain numbers!";
		} else if (Integer.parseInt(priorityField.getText()) > (Main.arrayList.listItemArray.size() + 1)) {
			return "Priority should not be more than than " + (Main.arrayList.listItemArray.size() + 1) + ".";
		} else if (Integer.parseInt(priorityField.getText()) < 1) {
			return "Priority should not be less than 1";
		}
		
		return "correct";
	}
	
	public boolean isUnique(String title) {
		 List<ListItem> titles = Main.arrayList.listItemArray.stream()
				 .filter(listItem -> listItem.getTitle().equals(title)).collect(Collectors.toList());
		 
		 if(titles.size() > 0) {
			 return false;
		 }
		return true;
	}

	public HBox createEditHeader() {
		Label headerLabel;
		HBox header = new HBox();
		header.setPadding(new Insets(15, 12, 15, 12));
		header.setSpacing(300);
		header.setStyle("-fx-background-color: #D7D7D7;");
		header.setAlignment(Pos.CENTER_LEFT);
		
		headerLabel = new Label("Edit Note");
		
		
		header.getChildren().add(headerLabel);
		return header;
	}

	public HBox createNoteActions() {
		Button delete;
		Button complete;
		
		HBox actions = new HBox();
		actions.setPadding(new Insets(15, 12, 15, 12));
		actions.setSpacing(300);
		actions.setAlignment(Pos.CENTER);
		
		delete = new Button("Delete");
		delete.setOnAction(e -> {
			Date today = new Date(); // Fri Jun 17 14:54:28 PDT 2016
			Calendar cal = Calendar.getInstance();
			cal.setTime(today); // don't forget this if date is arbitrary e.g. 01-01-2014

			int day = cal.get(Calendar.DAY_OF_MONTH);
			int month = cal.get(Calendar.MONTH) +1;
			
			Main.arrayList.changeStatus("Deleted", month, day, selectedNote+1);
			
			//refreshes list and switches scenes
			FillList listScene = new FillList(Main.arrayList);
			listScene.createListGUI();
			Main.window.setScene(Main.list);
		});
		
		
		complete = new Button("Complete");
		complete.setOnAction(e -> {
			Date today = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(today);

			int day = cal.get(Calendar.DAY_OF_MONTH);
			int month = cal.get(Calendar.MONTH)+1;
			
			Main.arrayList.changeStatus("Completed", month, day, selectedNote+1);
			
			//refreshes list and switches scenes
			FillList listScene = new FillList(Main.arrayList);
			listScene.createListGUI();
			Main.window.setScene(Main.list);
		});
		
		
		
		actions.getChildren().addAll(delete,complete);
		return actions;
	}

	public HBox createNoteDetails() {
		Label priorityNumberLabel, dueDateLabel, titleLabel, statusLabel;

		HBox noteDetails = new HBox();
		noteDetails.setPadding(new Insets(15, 12, 15, 12));
		noteDetails.setSpacing(300);
		noteDetails.setStyle("-fx-background-color: #D7D7D7;");
		
		priorityNumberLabel = new Label("#" + Integer.toString(Main.arrayList.listItemArray.get(selectedNote).getPriorityNumber()));
		dueDateLabel = new Label(Integer.toString(Main.arrayList.listItemArray.get(selectedNote).getDueMonth()) + "/" + Integer.toString(Main.arrayList.listItemArray.get(selectedNote).getDueDay()));
		titleLabel = new Label(Main.arrayList.listItemArray.get(selectedNote).getTitle());
		statusLabel = new Label(Main.arrayList.listItemArray.get(selectedNote).getStatus());
		
		noteDetails.getChildren().addAll(priorityNumberLabel,dueDateLabel, titleLabel, statusLabel);

		
		return noteDetails;
	}
	
}
