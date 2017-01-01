package dto;

public class Output {

	//Private variables
	private int outputId;
    private String outputAlgorithm;
    private String dateMade;
    private String outputFileName;
    private String outputFileUrl;
    
    //Getters and Setters
	public int getOutputId() {
		return outputId;
	}
	public void setOutputId(int outputId) {
		this.outputId = outputId;
	}
	public String getOutputAlgorithm() {
		return outputAlgorithm;
	}
	public void setOutputAlgorithm(String outputAlgorithm) {
		this.outputAlgorithm = outputAlgorithm;
	}
	public String getDateMade() {
		return dateMade;
	}
	public void setDateMade(String dateMade) {
		this.dateMade = dateMade;
	}
	public String getOutputFileName() {
		return outputFileName;
	}
	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}
	public String getOutputFileUrl() {
		return outputFileUrl;
	}
	public void setOutputFileUrl(String outputFileUrl) {
		this.outputFileUrl = outputFileUrl;
	}
    
	
}
