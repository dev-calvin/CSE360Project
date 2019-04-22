import java.util.List;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

@SuppressWarnings("restriction")
public class EditNote {
	//arraylist of list objects
	List<ListItem> listItemArray;
	
	public EditNote (ListItemArray arrayList) {
		listItemArray = arrayList.listItemArray;
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
		Region top, bottom;
		Label title, dueDay, dueMonth, priority, inProgress, statusMonth, statusDay;
		TextField titleField, priorityField;
		ComboBox<String> dueDaySelector, dueMonthSelector, statusDaySelector, statusMonthSelector;
		Button save;
		
		VBox form = new VBox();
		form.setPadding(new Insets(10));
		form.setSpacing(8);
		form.setAlignment(Pos.BOTTOM_CENTER);
		
		HBox due = new HBox();
		due.setPadding(new Insets(15, 12, 15, 12));
		due.setSpacing(300);
		
		top = new Region();
		VBox.setVgrow(top, Priority.ALWAYS);
		
		title = new Label("title");
		titleField = new TextField();
		
		dueMonth = new Label("Due Month");
				
		dueDaySelector = new ComboBox<String>();
		dueDaySelector.getItems().addAll("1","2","3","4","5", "6", "7", "8", "9", "10", "11", "12", "13",
	    		"14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
	    		"28", "29", "30", "31");
		
		dueDay = new Label("Due Day");
		
		dueMonthSelector = new ComboBox<String>();
		dueMonthSelector.getItems().addAll("1","2","3","4","5", "6", "7", "8", "9", "10", "11", "12");
		
		priority = new Label("Priority Number");
		
		priorityField = new TextField();
				
		inProgressCB = new CheckBox();
		inProgressCB.setIndeterminate(false);
		
		inProgress = new Label("In Progress");
		
		statusMonth = new Label("started Month");
		
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
		
		due.getChildren().addAll(dueMonth, dueMonthSelector, dueDay, dueDaySelector);
		form.getChildren().addAll(top, title, titleField, due, priority, priorityField, inProgressCB, inProgress, statusMonth, statusMonthSelector, statusDay, statusDaySelector, bottom, save);
		return form;
	}

	private void validateForm(TextField titleField, ComboBox<String> dueMonthSelector, ComboBox<String> dueDaySelector,
			TextField priorityField, CheckBox inProgressCB, ComboBox<String> statusMonthSelector,
			ComboBox<String> statusDaySelector) {
		// TODO Auto-generated method stub
		
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
		
		delete = new Button("Delete");
		complete = new Button("Complete");
		
		actions.getChildren().addAll(delete,complete);
		return actions;
	}

	public HBox createNoteDetails() {
		Label priorityNumberLabel, dueDateLabel, titleLabel, statusLabel;

		HBox noteDetails = new HBox();
		noteDetails.setPadding(new Insets(15, 12, 15, 12));
		noteDetails.setSpacing(300);
		noteDetails.setStyle("-fx-background-color: #D7D7D7;");
		
		priorityNumberLabel = new Label("Priority #");
		dueDateLabel = new Label("Due Date");
		titleLabel = new Label("Title");
		statusLabel = new Label("Status");
		
		noteDetails.getChildren().addAll(priorityNumberLabel,dueDateLabel, titleLabel, statusLabel);

		
		return noteDetails;
	}
	
}
