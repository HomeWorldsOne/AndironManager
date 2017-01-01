package organisers;


//This will be used to construct and return the correct directory
//for project specific files
//return types are strings
public class DirectoryManager {

	public static void makeDirectory(int projectId){
		
		//Constructs a unique directory based on the project id
		//this method should only be called when the project is first created
		
	}
	
	public static String getDirectory(int fileType, int id){
		
		//Where fileType of 1 = input
		//fileType of 2 = output
		
		if(fileType == 1){
			//This is an input file
		} else if(fileType == 2){
			//This is an output file
		}
		
		
		String directory = "";
		
		return directory;
	}
}
