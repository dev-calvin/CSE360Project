import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
		Button save;
		
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
		dueDaySelector.getItems().addAll("1","2","3","4","5", "6", "7", "8", "9", "10", "11", "12", "13",
	    		"14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
	    		"28", "29", "30", "31");
		
		dueDay = new Label("Due Day");
		
		dueMonthSelector = new ComboBox<String>();
		dueMonthSelector.getItems().addAll("1","2","3","4","5", "6", "7", "8", "9", "10", "11", "12");
		
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
		
		statusBox.getChildren().addAll(inProgress, inProgressCB, statusMonth, statusMonthSelector, statusDay, statusDaySelector);
		due.getChildren().addAll(dueMonth, dueMonthSelector, dueDay, dueDaySelector);
		form.getChildren().addAll(titleBox, due, priorityBox, statusBox, bottom, save);
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
		
		
		/*replacing whole list item*/
		Main.arrayList.deleteListItem(selectedNote);
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
			int month = cal.get(Calendar.MONTH);
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
			int month = cal.get(Calendar.MONTH);
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
