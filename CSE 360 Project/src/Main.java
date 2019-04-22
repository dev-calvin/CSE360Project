import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class Main extends Application{
	static Stage window;
	static Scene list, newNote, editNote, sortList, saveWarning;

	//buttons for newNote
	Button cancel;

	static ListItemArray arrayList;
	
	public static void main(String[] args) {
		//entry point for program
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//initializes stage
		window = primaryStage;
		//initializes arrayList
		arrayList = new ListItemArray();
		
		//list scene
		FillList listScene = new FillList(arrayList);
		listScene.createListGUI();
		
//		//editNote scene
//		EditNote editNoteScene = new EditNote(arrayList);
//		editNoteScene.createEditNoteGUI();
		
		//newNote scene
		NewNote newNoteScene = new NewNote(arrayList);
		newNoteScene.createNewNoteGUI();
		
//		//cancel button
//				cancel = new Button();
//				cancel.setText("Cancel");
//				cancel.setOnAction(e -> {
//					window.setScene(list);
//				});
//		StackPane newNoteLayout = new StackPane();
//		newNoteLayout.getChildren().addAll(cancel);
//		newNote = new Scene(newNoteLayout, 400, 400);
		
		//sets up entry point scene
		window.setScene(list);
		window.setTitle("CSE 360 List");
		window.show();
	}
}
