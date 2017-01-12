package algorithms.apriori;

public class AprioriDTO {
	
	//Private variables
	private int aprioriEntriesId;
	private int outputId;
	private String values;
	private int numOfValues;
	private int support;
	
	//Getters and Setters
	public int getAprioriEntriesId() {
		return aprioriEntriesId;
	}
	public void setAprioriEntriesId(int aprioriEntriesId) {
		this.aprioriEntriesId = aprioriEntriesId;
	}
	public int getOutputId() {
		return outputId;
	}
	public void setOutputId(int outputId) {
		this.outputId = outputId;
	}
	public String getValues() {
		return values;
	}
	public void setValues(String values) {
		this.values = values;
	}
	public int getNumOfValues() {
		return numOfValues;
	}
	public void setNumOfValues(int numOfValues) {
		this.numOfValues = numOfValues;
	}
	public int getSupport() {
		return support;
	}
	public void setSupport(int support) {
		this.support = support;
	}
	
	
}
