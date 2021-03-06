package com.sergiisavin.kickstarter;

import static org.junit.Assert.*;

import javax.naming.OperationNotSupportedException;

import org.junit.Before;
import org.junit.Test;

import com.sergiisavin.kickstarter.project.Project;
import com.sergiisavin.kickstarter.project.container.Projects;
import com.sergiisavin.kickstarter.project.container.memory.ProjectsContainer;

public class ProjectsTest {

	Projects projects;
	
	@Before
	public void setup(){		
		projects = new ProjectsContainer();
		Project project = new Project("Jumping Frog Toy", "01.05.2015", "01.11.2015", 20000.0, 0.0, "Toys",
				"DESCRIPTION", "PROJECT HISTORY", "VIDEO URL", "QUESTIONS AND ANSWERS");
		try {
			projects.add(project);
		} catch (OperationNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void getSize(){
		int size = projects.getSize();
		assertEquals(1, size);
	}
	
	@Test
	public void addProject(){
		int size = projects.getSize();
		Project project = new Project("Portable nuclear reactor", "01.05.2015", "01.11.2015", 20000.0, 0.0, "Gadgets",
				"DESCRIPTION", "PROJECT HISTORY", "VIDEO URL", "QUESTIONS AND ANSWERS");
		try {
			projects.add(project);
		} catch (OperationNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		size = projects.getSize();
		assertEquals(2, size);
	}
	
	@Test
	public void getProject(){
		Project project = projects.getProject(0);
		assertEquals("Jumping Frog Toy", project.getName());
	}

}
