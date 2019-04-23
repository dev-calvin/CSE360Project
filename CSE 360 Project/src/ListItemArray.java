import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListItemArray {
		//arraylist of list objects
		/* list number 1 */
		List<ListItem> listItemArray = new ArrayList<>();
		/* list number 2 */
		List<ListItem> completedDeleted = new ArrayList<>();
		
		public ListItemArray (){
			//initialize with fake data
			listItemArray.add(new ListItem("Take out trash", 2, 11, 1));
			listItemArray.get(0).changeStatus("In Progress", 1, 25);
			listItemArray.add(new ListItem("Eat an apple", 7, 28, 2));
			listItemArray.add(new ListItem("Go for a run", 12, 31, 3));
			listItemArray.add(new ListItem("zipline set up", 2, 5, 4));
			sortStatus(listItemArray,1);
			
			
			newListItem("tester", 1,1,5);
			newListItem("tester2", 1,1,6);
			newListItem("tester3", 1,1,7);
			
			changeStatus("Completed", 1, 7, 2);
		}
		
		// adds item to list
		public void newListItem(String title, int dueMonth, int dueDay, int priorityNumber){
			sortPriorityNumber(listItemArray,1);
			
			if(priorityNumber == listItemArray.size()) {
				listItemArray.add(new ListItem(title, dueMonth, dueDay, priorityNumber));
			} else {
				listItemArray.add(priorityNumber - 1, new ListItem(title, dueMonth, dueDay, priorityNumber));
				
				for(int i = priorityNumber; i < listItemArray.size(); i++) {
					listItemArray.get(i).setPriorityNumber(i+1);
				}
			}
		}
		
		// delete list item from list PERMANANT
		public void deleteListItem(int priorityNumber){
			sortPriorityNumber(listItemArray,1);
			
			if(priorityNumber == listItemArray.size()) {
				listItemArray.remove(listItemArray.size() - 1);
			} else {
				for(int i = priorityNumber; i < listItemArray.size(); i++) {
					listItemArray.get(i).setPriorityNumber(i);
				}
				
				listItemArray.remove(priorityNumber -1);
			}
		}
		
		// change status of list item
		public void changeStatus(String status, int statusMonth, int statusDay, int priorityNumber){
			sortPriorityNumber(listItemArray,1);

			listItemArray.get(priorityNumber - 1).changeStatus(status, statusMonth, statusDay);
			
			if(status == "Completed" || status == "Deleted") {
				sortStatus(completedDeleted,2);

				if(status == "Completed") {
					completedDeleted.add(0,listItemArray.get(priorityNumber -1));
				} else if(status == "Deleted"){
					completedDeleted.add(listItemArray.get(priorityNumber -1));
				}
				
				
				if(priorityNumber == listItemArray.size()) {
					deleteListItem(priorityNumber);
				} else {
					for(int i = priorityNumber; i < listItemArray.size(); i++) {
						listItemArray.get(i).setPriorityNumber(i);
					}
					
					deleteListItem(priorityNumber);
				}
			}
		}
		// sort by priority number
		public void sortPriorityNumber(List<ListItem> listItemArrayinput, int listNumber) {
		    Collections.sort(listItemArrayinput, priorityNumberComparator);
		    if(listNumber == 1) {
			    listItemArray = listItemArrayinput;
		    } else if(listNumber == 2) {
			    completedDeleted = listItemArrayinput;
		    }
		}
		// sort by due date
		public void sortDueDate(List<ListItem> listItemArrayinput, int listNumber) {
			Collections.sort(listItemArrayinput, dateDueComparator);			   
			if(listNumber == 1) {
			    listItemArray = listItemArrayinput;
		    } else if(listNumber == 2) {
			    completedDeleted = listItemArrayinput;
		    }		
		}
		// sort by title
		public void sortTitle(List<ListItem> listItemArrayinput, int listNumber) {
		    Collections.sort(listItemArrayinput, titleComparator);
		    if(listNumber == 1) {
			    listItemArray = listItemArrayinput;
		    } else if(listNumber == 2) {
			    completedDeleted = listItemArrayinput;
		    }		
		}
		// sort by status
		public void sortStatus(List<ListItem> listItemArrayinput, int listNumber) {
			Collections.sort(listItemArrayinput, statusComparator);			   
			if(listNumber == 1) {
			    listItemArray = listItemArrayinput;
		    } else if(listNumber == 2) {
			    completedDeleted = listItemArrayinput;
		    }		
		}
		
		/*Comparator for sorting the list by title*/
	    public static Comparator<ListItem> titleComparator = new Comparator<ListItem>() {

		public int compare(ListItem s1, ListItem s2) {
		   String title1 = s1.getTitle().toUpperCase();
		   String title2 = s2.getTitle().toUpperCase();

		   //ascending order
		   return title1.compareTo(title2);

		   //descending order
		   //return title2.compareTo(title1);
	    }};
	    
	    /*Comparator for sorting the list by priority number*/
	    public static Comparator<ListItem> priorityNumberComparator = new Comparator<ListItem>() {

		public int compare(ListItem s1, ListItem s2) {

		   int priorityNumber1 = s1.getPriorityNumber();
		   int priorityNumber2 = s2.getPriorityNumber();

		   /*For ascending order*/
		   return priorityNumber1-priorityNumber2;

		   /*For descending order*/
		   //priorityNumber2-priorityNumber1;
	   }};
	   
	   /*Comparator for sorting the list by date due*/
	    public static Comparator<ListItem> dateDueComparator = new Comparator<ListItem>() {

		public int compare(ListItem s1, ListItem s2) {

		   int dueDate1 = s1.getDueDay() + s1.getDueMonth()*30;
		   int dueDate2 = s2.getDueDay() + s2.getDueMonth()*30;

		   /*For ascending order*/
		   return dueDate1-dueDate2;

		   /*For descending order*/
//		   return dueDate2-dueDate1;
	   }};
	   
	   /*Comparator for sorting the list by date due*/
	    public static Comparator<ListItem> statusComparator = new Comparator<ListItem>() {

		public int compare(ListItem s1, ListItem s2) {

		   int dueDate1 = s1.getStatusDay() + s1.getStatusMonth()*30;
		   int dueDate2 = s2.getStatusDay() + s2.getStatusMonth()*30;
		   
		   switch(s1.getStatus()) {
		   		case "Not Started":
		   			dueDate1 += 1000;
		   			break;
		   		case "In Progress":
		   			dueDate1 += 2000;
		   			break;
		   		case "Completed":
		   			dueDate1 += 3000;
		   			break;
		   		case "Deleted":
		   			dueDate1 += 4000;
		   			break;
		   }
		   
		   switch(s2.getStatus()) {
	   		case "Not Started":
	   			dueDate2 += 1000;
	   			break;
	   		case "In Progress":
	   			dueDate2 += 2000;
	   			break;
	   		case "Completed":
	   			dueDate2 += 3000;
	   			break;
	   		case "Deleted":
	   			dueDate2 += 4000;
	   			break;
	   }

		   /*For ascending order*/
		   return dueDate1-dueDate2;

		   /*For descending order*/
//		   return dueDate2-dueDate1;
	   }};

}
