import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

@SuppressWarnings("restriction")
public class SortList {
	String selected = "";
		
	public SortList() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Sort List");
		
		Label header;
		RadioButton priorityNumberBtn, titleBtn, dueDateBtn, statusBtn;
		Button sort;
		
		VBox sortLayout = new VBox();
		sortLayout.setPadding(new Insets(10));
		sortLayout.setSpacing(8);
		sortLayout.setAlignment(Pos.CENTER);
		
		header = new Label("Sort By: ");
		
		final ToggleGroup group = new ToggleGroup();

		priorityNumberBtn = new RadioButton("Priority Number");
		priorityNumberBtn.setToggleGroup(group);
		priorityNumberBtn.setSelected(true);
		
		titleBtn = new RadioButton("Note Title");
		titleBtn.setToggleGroup(group);
		
		dueDateBtn = new RadioButton("Due Date");
		dueDateBtn.setToggleGroup(group);
		
		statusBtn = new RadioButton("Status");
		statusBtn.setToggleGroup(group);
		
		// add a change listener 
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() { 
            public void changed(ObservableValue<? extends Toggle> ob,  
                                                    Toggle o, Toggle n) 
            { 
  
                RadioButton rb = (RadioButton)group.getSelectedToggle(); 
  
                if (rb != null) { 
                    selected = rb.getText(); 
                } 
            } 
        });
		//arraylist of list objects
		List<ListItem> listItemArray = Main.arrayList.listItemArray;
		
		sort = new Button("Sort");
		sort.setOnAction(e -> {
			switch(selected){
				case "Note Title":
					Main.arrayList.sortTitle(listItemArray, 1);
					break;
				case "Due Date":
					Main.arrayList.sortDueDate(listItemArray, 1);
					break;
				case "Status":
					Main.arrayList.sortStatus(listItemArray, 1);
					break;
				default:
					Main.arrayList.sortPriorityNumber(listItemArray, 1);
					break;
			}
			
			window.close();
			FillList listScene = new FillList(Main.arrayList);
			listScene.createListGUI();
			Main.window.setScene(Main.list);		
		});
		
		sortLayout.getChildren().addAll(header, priorityNumberBtn, titleBtn, dueDateBtn, statusBtn, sort);
		Main.sortList = new Scene(sortLayout, 400, 400);  
		window.setScene(Main.sortList);
		window.showAndWait();
	}
	
	
}
