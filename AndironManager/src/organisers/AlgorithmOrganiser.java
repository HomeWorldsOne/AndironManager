package organisers;
import java.io.IOException;

import algorithms.apriori.AlgoApriori;
import algorithms.apriori.AprioriDAO;

//This class mainly focuses on taking in an algorithm, file path etc. and
//performing the desires action
//this class exists to clean up code from the servlet classes.
//This class will also constuct classes for storing the outputs of the algorithms into the databases. 
public class AlgorithmOrganiser {
	
	/*
	 *Algorithms are indexed by simple integers
	 *	1	=>	Apriori  
	 */

	//Main class, selects which method to implement
	public void selector(int algorithmIndex, String inputFilePath, String outputFilePath) throws IOException{
		switch (algorithmIndex){
			//Case when requested algorithm is "Apriori"
			case 1:	doApriori(inputFilePath, outputFilePath);
			break;
			
			//case more...
		}
	}
	
	//Algorithm classes
	public void doApriori(String inputFilePath, String outputFilePath) throws IOException{
		
		//Id variable
		String algorithm = "Apriori";
		
		//Operation
		AlgoApriori apriori = new AlgoApriori();
		apriori.runAlgorithm(0.4, inputFilePath, outputFilePath);
		apriori.printStats();
		
		//Create DTO's
		createDtos(inputFilePath, outputFilePath, algorithm);
		
		//Insert into database
		AprioriDAO.insertValues(outputFilePath);
	}
	
	//Database objects for storage and indexing
	public void createDtos(String inputFilePath, String outputFilePath, String algorithm){
		
	}
	
	
}
