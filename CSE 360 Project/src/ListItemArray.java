import java.util.ArrayList;
import java.util.List;

public class ListItemArray {
		//arraylist of list objects
		List<ListItem> listItemArray = new ArrayList<>();
		
		public ListItemArray (){
			//initialize with fake data
			listItemArray.add(new ListItem("note", 2, 11, 1));
			listItemArray.get(0).changeStatus("In Progress", 1, 25);
			listItemArray.add(new ListItem("number 2", 7, 28, 2));
			listItemArray.add(new ListItem("number 3", 12, 31, 3));
			listItemArray.add(new ListItem("number 4", 2, 5, 4));

		}
		
		// adds item to list
		public void newListItem(String title, int dueMonth, int dueDay, int priorityNumber){
			listItemArray.add(new ListItem(title, dueMonth, dueDay, priorityNumber));
		}
		
		// delete list item
		public void deleteListItem(String title){
			
		}
		// complete list item
		public void completeListItem(String title){
			
		}
		// sort by priority number
		public void sortPriorityNumber(ArrayList<ListItem> listItemArray) {
			
		}
		// sort by due date
		public void sortDueDate() {
			
		}
		// sort by title
		public void sortTitle() {
	
		}
		// sort by status
		public void sortStatus() {
			
		}
}
