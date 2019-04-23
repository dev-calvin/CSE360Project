import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class SaveList {
	public void createSave () {
		ListItemArray arrayList = Main.arrayList;

	    try {    
	        // Saving of object in a file 
	        FileOutputStream file = new FileOutputStream("ListSave.ser"); 
	        ObjectOutputStream arrayListObj = new ObjectOutputStream(file); 
	          
	        // Serialization of the arraylist object 
	        arrayListObj.writeObject(arrayList); 
	          
	        arrayListObj.close(); 
	        file.close(); 
	    } catch(IOException ex) { 
	        ex.printStackTrace(); 
	    } 
	}
	
	public ListItemArray loadSave() {
		ListItemArray arrayList = null;
		
		// Deserialization 
        try
        {    
            // Reading the object from a file 
            FileInputStream file = new FileInputStream("ListSave.ser"); 
            ObjectInputStream arrayListObj = new ObjectInputStream(file); 
              
            // Method for deserialization of object 
            arrayList = (ListItemArray)arrayListObj.readObject(); 
              
            arrayListObj.close(); 
            file.close();
        } 
          
        catch(IOException ex) 
        { 
            System.out.println("IOException is caught"); 
        } 
          
        catch(ClassNotFoundException ex) 
        { 
            System.out.println("ClassNotFoundException is caught"); 
        } 
        
        return arrayList;
	}
}
