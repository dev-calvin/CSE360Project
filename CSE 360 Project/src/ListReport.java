import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

public class ListReport {
	//arraylist of list objects
	List<ListItem> listItemArray;
	//arraylist of completed and deleted list objects
	List<ListItem> completedDeleted;
		
	public ListReport(ListItemArray arrayList) {
		listItemArray = arrayList.listItemArray;
		completedDeleted = arrayList.completedDeleted;
	}
	
	public void createReport(){
	    FileWriter fileWriter;
		try {
			fileWriter = new FileWriter("Report.txt");
	    PrintWriter printWriter = new PrintWriter(fileWriter);
	    
	    List<ListItem> deleted = completedDeleted.stream()
                .filter(listItem -> listItem.getStatus().equals("Deleted"))
                .collect(Collectors.toList());
	    List<ListItem> completed = completedDeleted.stream()
                .filter(listItem -> listItem.getStatus().equals("Completed"))
                .collect(Collectors.toList());
	    List<ListItem> notStarted = listItemArray.stream()
                .filter(listItem -> listItem.getStatus().equals("Not Started"))
                .collect(Collectors.toList());
	    List<ListItem> inProgress = listItemArray.stream()
                .filter(listItem -> listItem.getStatus().equals("In Progress"))
                .collect(Collectors.toList());
	    
	    printWriter.print("List Report\n\n");
	    printWriter.print("List Items Not Started:\n");
	    
	    notStarted.forEach(listItem -> {
	    	printWriter.printf("- #%d %d/%d %s\n",
	    			listItem.priorityNumber, listItem.dueMonth, listItem.dueDay, listItem.title);
	    });
	    
	    printWriter.print("List Items In Progress:\n");
	    inProgress.forEach(listItem -> {
	    	printWriter.printf("- #%d %d/%d %s, Started on: %d/%d\n",
	    			listItem.priorityNumber, listItem.dueMonth, listItem.dueDay, listItem.title, listItem.statusMonth, listItem.statusDay);
	    });
	    
	    printWriter.print("List Items That Were Deleted:\n");
	    deleted.forEach(listItem -> {
	    	printWriter.printf("- #%d %d/%d %s, Deleted on: %d/%d\n",
	    			listItem.priorityNumber, listItem.dueMonth, listItem.dueDay, listItem.title, listItem.statusMonth, listItem.statusDay);
	    });
	    
	    printWriter.print("List Items That Were Completed:\n");
	    completed.forEach(listItem -> {
	    	printWriter.printf("- #%d %d/%d %s, Completed on: %d/%d\n",
	    			listItem.priorityNumber, listItem.dueMonth, listItem.dueDay, listItem.title, listItem.statusMonth, listItem.statusDay);
	    });
	    
	    printWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
