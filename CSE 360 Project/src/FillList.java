import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;

@SuppressWarnings("restriction")
public class FillList{
	
	//arraylist of list objects
	List<ListItem> listItemArray;
	
	public FillList (ListItemArray arrayList){
		listItemArray = arrayList.listItemArray;
	}
	
	public VBox createListGUI (){
		//center list
		VBox centerListLayout = listContainer();
		
		return centerListLayout;
	}
	
	
	public VBox listContainer() {
	    VBox listContainer = new VBox();
	    listContainer.setPadding(new Insets(10));
	    listContainer.setSpacing(8);
	    
	    for(int i = 0; i < listItemArray.size(); i++){
		    listContainer.getChildren().add(listItem(i));
	    }

	    return listContainer;
	}
	
	public HBox listItem(int arrayPosition) {
		Label priorityNumberLabel, dueDateLabel, titleLabel, statusLabel;

	    HBox listItem = new HBox();
	    listItem.setPadding(new Insets(15, 12, 15, 12));
	    listItem.setSpacing(10);
	    listItem.setStyle("-fx-background-color: #FFFFFF;");
	    
		priorityNumberLabel = new Label();
		priorityNumberLabel.setText(String.valueOf(listItemArray.get(arrayPosition).getPriorityNumber()));
		priorityNumberLabel.setPrefSize(100, 20);
		
		dueDateLabel = new Label();
		dueDateLabel.setText(
				String.valueOf(listItemArray.get(arrayPosition).getDueMonth()) 
				+ " / " 
				+ String.valueOf(listItemArray.get(arrayPosition).getDueDay())
				);
		dueDateLabel.setPrefSize(100, 20);
		
		titleLabel = new Label();
		titleLabel.setText(listItemArray.get(arrayPosition).getTitle());
		titleLabel.setPrefSize(100, 20);
		
		statusLabel = new Label();
		if (listItemArray.get(arrayPosition).getStatus().equals("NOTSTARTED")){
			statusLabel.setText(listItemArray.get(arrayPosition).getStatus());
		} else {
			statusLabel.setText(
					listItemArray.get(arrayPosition).getStatus()
					+ " "
					+ String.valueOf(listItemArray.get(arrayPosition).getStatusMonth())
					+ " / "
					+ String.valueOf(listItemArray.get(arrayPosition).getStatusDay())
					);
		}
		statusLabel.setPrefSize(200, 20);
		
		listItem.getChildren().addAll(priorityNumberLabel,dueDateLabel, titleLabel, statusLabel);

		listItem.setOnMouseClicked(e -> {
			Main.window.setScene(Main.editNote);
		});
	    return listItem;
	}
}
