import java.io.Serializable;

@SuppressWarnings("serial")
public class ListItem implements Serializable{
	enum Status {
		NOTSTARTED("Not Started"),
		INPROGRESS("In Progress"),
		DELETED("Deleted"),
		COMPLETED("Completed");
		
		String status;
		
		Status(String currentStatus){
			status = currentStatus;
		}
		
		public String toString (){
			return status;
		}
	}
	
	int priorityNumber;
	int dueMonth;
	int dueDay;
	String title;
	Status currentStatus = Status.NOTSTARTED;
	int statusMonth;
	int statusDay;
	
	public ListItem (String itemTitle, int itemDueMonth, int itemDueDay, int itemPriorityNumber){
		title = itemTitle;
		dueMonth = itemDueMonth;
		dueDay = itemDueDay;
		priorityNumber = itemPriorityNumber;
	}
	
	public void setPriorityNumber(int newNum){
		priorityNumber = newNum;
	}
	
	public void setTitle(String newTitle){
		title = newTitle;
	}
	
	public void setDueDate(int newMonth, int newDay){
		dueMonth = newMonth;
		dueDay = newDay;
	}
	
	public void changeStatus(String newStatus, int newMonth, int newDay){
		switch (newStatus){
		case "In Progress" :
			currentStatus = Status.INPROGRESS;
			break;
		case "Deleted" :
			currentStatus = Status.DELETED;
			break;
		case "Completed" :
			currentStatus = Status.COMPLETED;
		}
		statusMonth = newMonth;
		statusDay = newDay;
	}
	
	public int getPriorityNumber (){
		return priorityNumber;
	}
	
	public int getDueMonth (){
		return dueMonth;
	}
	
	public int getDueDay (){
		return dueDay;
	}
	
	public String getTitle (){
		return title;
	}
	
	public String getStatus(){
		return currentStatus.toString();
	}
	
	public int getStatusMonth(){
		return statusMonth;
	}
	
	public int getStatusDay(){
		return statusDay;
	}
}
