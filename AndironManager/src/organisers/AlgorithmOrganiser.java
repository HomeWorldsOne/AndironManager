package organisers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import algorithms.apriori.AlgoApriori;
import algorithms.apriori.AprioriDAO;
import algorithms.apriori.AprioriDTO;

//This class mainly focuses on taking in an algorithm, file path etc. and
//performing the desires action
//this class exists to clean up code from the servlet classes.
//This class will also construct classes for storing the outputs of the algorithms into the databases. 
public class AlgorithmOrganiser {

	/*
	 * Algorithms are indexed by simple integers 1 => Apriori
	 */

	// Main class, selects which method to implement
	public void selector(int algorithmIndex, String inputFilePath, String outputFilePath) throws IOException {
		switch (algorithmIndex) {
		// Case when requested algorithm is "Apriori"
		case 1:
			doApriori(inputFilePath, outputFilePath);
			break;

		// case more...
		}
	}

	// Algorithm classes
	public void doApriori(String inputFilePath, String outputFilePath) throws IOException {

		// Id variable
		String algorithm = "Apriori";

		// Operation
		AlgoApriori apriori = new AlgoApriori();
		apriori.runAlgorithm(0.4, inputFilePath, outputFilePath);
		apriori.printStats();

	}

	// Database objects for storage and indexing
	public void createAprioriDtos(String outputFilePath, int outputId) throws IOException{
		// Construct the DTO's and add them to the database
		ArrayList<String> lines = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(outputFilePath));
		String line = null;
		while ((line = br.readLine()) != null) {
			lines.add(line);
		}

		for (String s : lines) {

			String values;
			String support;

			String[] parts = s.split("#SUP:");
			values = parts[0];
			support = parts[1];
			support = support.replace(" ", "");
			String[] valuesArray = values.split(" ");
			ArrayList<Integer> valuesIntArray = new ArrayList<>();

			for (int i = 0; i < valuesArray.length; i++) {
				valuesIntArray.add(Integer.parseInt(valuesArray[i]));
			}

			AprioriDTO aprioriDto = new AprioriDTO();
			aprioriDto.setSupport(Integer.parseInt(support));
			aprioriDto.setValues(values);
			aprioriDto.setNumOfValues(valuesArray.length);
			aprioriDto.setOutputId(outputId);
			
			AprioriDAO.add(aprioriDto);
			

		}
	}

}
