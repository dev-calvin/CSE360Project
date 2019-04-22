import java.util.List;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.geometry.Insets;

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
		
		return null;
	}

	public HBox createEditHeader() {
		// TODO Auto-generated method stub
		return null;
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
