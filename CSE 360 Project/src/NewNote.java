import java.awt.Window;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

@SuppressWarnings("restriction")
public class NewNote {
	
	//arraylist of list objects
	List<ListItem> listItemArray;
	
	public NewNote (ListItemArray arrayList) {
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
		Button create;
		
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
		
		bottom = new Region();
		VBox.setVgrow(bottom, Priority.ALWAYS);
		
		create = new Button("Create");
		create.setOnAction(e -> {
			validateRecord(titleField, dueMonthSelector, dueDaySelector, priorityField);			
		});
		
		due.getChildren().addAll(dueMonth, dueMonthSelector, dueDay, dueDaySelector);
		form.getChildren().addAll(top, title, titleField, due, priority, priorityField, bottom, create);
		return form;
	}

	private void validateRecord(TextField titleField, ComboBox<String> dueMonthSelector, ComboBox<String> dueDaySelector, TextField priorityField) {
//       if ((titleField.getText() != null && !titleField.getText().isEmpty())) {
//           title = titleField;
//       } else {
////            Main.listItemArray.add(new ListItem(title, dueMonth, dueDay, priorityNumber));
//       }
	}
}
