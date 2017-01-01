package algorithms.apriori;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//This class will be used to move the algorithms outputs into the database
public class AprioriDAO extends dao.BaseDAO{
	
	/*
	 * The outputs form the a priori algorithm typically look something like this:
	 * {1} support 4 // i.e. the item "1" appears 4 times
	 * to store the outputs from the algorithm, these are the current fields I consider:
	 * id, algorithm, userId, projectId, value, support, noValues, fileId
	 * Thus we should be able to utilize each
	 */
	
	public static void insertValues(String fileUrl) throws IOException{
		
		//Store for all lines in the file
		List<String> lines = new ArrayList<String>();
		
		// Open the file
		FileInputStream fstream = new FileInputStream(fileUrl);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String strLine;

		//Read File Line By Line
		while ((strLine = br.readLine()) != null)   {
		  lines.add(strLine);
		}
		
		//close reader
		br.close();
		
		for(String s: lines){
			
			//Segregate the line into its unique sections
			String[] parts = s.split("#SUP");
			String itemValue = parts[0]; 
			String support = parts[1]; 
			int size = 1;
			
			if(itemValue.contains(",")){
				String[] items = itemValue.split(",");
				size = items.length;
			}
			
			//Push values to database
			int userId = 0; //To be implemented
			int projectId = 0; //To be implemented
			int fileId; //foriegn key to thingo
			String algorithm = "Apriori";
			String value = itemValue;
			int noValues = size;
			
			
			
		}
		
	}

}
