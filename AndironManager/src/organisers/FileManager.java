package organisers;

import dao.InputDAO;
import dao.OutputDAO;
import dto.Input;
import dto.Output;

public class FileManager {

	public static String getFileName(int fileId, int fileType) {

		String fileName = "";

		if (fileType == 1) {
			// this is an input file
			Input input = InputDAO.selectById(fileId);
			fileName = input.getDataName();

		} else if (fileType == 2) {
			// this is an output file
			Output output = OutputDAO.selectById(fileId);
			fileName = output.getOutputFileName();

		}

		return fileName;
	}

}
