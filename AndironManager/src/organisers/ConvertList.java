package organisers;

import dto.*;
import dao.*;
import java.util.ArrayList;
import java.util.List;

public class ConvertList {

	// Method is used to convert all composite entities into useful classes
	public static List<Output> getAllOutputFiles(List<ProjectOutput> list) {
		List<Output> outputFiles = new ArrayList<Output>();

		for (ProjectOutput po : list) {
			Output output = OutputDAO.selectById(po.getOutputId());
			outputFiles.add(output);
		}

		return outputFiles;

	}

	// Method is used to convert all composite entities into useful classes
	public static List<Project> getAllProjectsFromOutput(List<ProjectOutput> list) {
		List<Project> projects = new ArrayList<Project>();

		for (ProjectOutput po : list) {
			Project project = ProjectDAO.selectById(po.getProjectId());
			projects.add(project);
		}

		return projects;

	}

	// Method is used to convert all composite entities into useful classes
	public static List<Project> getAllProjectsFromInput(List<ProjectInput> list) {
		List<Project> projects = new ArrayList<Project>();

		for (ProjectInput po : list) {
			Project project = ProjectDAO.selectById(po.getProjectId());
			projects.add(project);
		}

		return projects;

	}

	// Method is used to convert all composite entities into useful classes
	public static List<Input> getAllInputFiles(List<ProjectInput> list) {
		List<Input> inputFiles = new ArrayList<Input>();

		for (ProjectInput po : list) {
			Input input = InputDAO.selectById(po.getInputId());
			inputFiles.add(input);
		}

		return inputFiles;

	}
}
