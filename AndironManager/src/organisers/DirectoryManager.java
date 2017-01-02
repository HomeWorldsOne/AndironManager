package organisers;

import java.io.File;

import dao.InputDAO;
import dao.OutputDAO;
import dto.Input;
import dto.Output;

//This will be used to construct and return the correct directory
//for project specific files
//return types are strings
public class DirectoryManager {

	public static void makeDirectory(int projectId) {

		// Constructs a unique directory based on the project id
		// this method should only be called when the project is first created

		// This method will create a directory if it does not already exist

		// build directory path
		String PATH = "/remote/dir/server/";
		String directoryName = PATH.concat(Integer.toString(projectId));

		// Check
		File directory = new File(String.valueOf(directoryName));
		if (!directory.exists()) {
			directory.mkdir();
		}

	}

	public static String getDirectory(int fileType, int id) {

		// Where fileType of 1 = input
		// fileType of 2 = output

		if (fileType == 1) {
			// This is an input file
			Input input = InputDAO.selectById(id);
			return input.getFileUrl();
		} else if (fileType == 2) {
			// This is an output file
			Output output = OutputDAO.selectById(id);
			return output.getOutputFileUrl();
		}

		return null;
	}
}
