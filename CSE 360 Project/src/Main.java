import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;

@SuppressWarnings("restriction")
public class Main extends Application{
	Stage window;
	Scene list, newNote, editNote, sortList, saveWarning;
	//buttons for list
	Button addNote, report, load, save, sort;
	//buttons for newNote
	Button cancel;

	public static void main(String[] args) {
		//entry point for program
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//initializes stage
		window = primaryStage;
		
		//list scene
		BorderPane listLayout = new BorderPane();
		HBox listTop = listTop();
		listLayout.setTop(listTop);
//		listLayout.setCenter(/*list goes here */);
		list = new Scene(listLayout, 1200, 800);   
		
		//newNote scene
		//cancel button
				cancel = new Button();
				cancel.setText("Cancel");
				cancel.setOnAction(e -> {
					window.setScene(list);
				});
		StackPane newNoteLayout = new StackPane();
		newNoteLayout.getChildren().addAll(cancel);
		newNote = new Scene(newNoteLayout, 400, 400);
		
		//sets up entry point scene
		window.setScene(list);
		window.setTitle("CSE 360 List");
		window.show();
	}
	
	public HBox listTop() {
	    HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #FFFFFF;");
	    
		//add note button
		addNote = new Button();
		addNote.setText("New Note");
	    addNote.setPrefSize(100, 20);
		addNote.setOnAction(e -> {
			window.setScene(newNote);
		});
		
		//add note button
		report = new Button();
		report.setText("Report");
	    report.setPrefSize(100, 20);
		report.setOnAction(e -> {
			//create report
		});
		
		hbox.getChildren().addAll(addNote,report);

	    return hbox;
	}

}
