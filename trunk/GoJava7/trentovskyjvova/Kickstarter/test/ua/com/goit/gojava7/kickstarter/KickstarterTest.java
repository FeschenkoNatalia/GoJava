package ua.com.goit.gojava7.kickstarter;

import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ua.com.goit.gojava7.kickstarter.config.DaoProvider;
import ua.com.goit.gojava7.kickstarter.config.DataSource;
import ua.com.goit.gojava7.kickstarter.console.ConsolePrinter;
import ua.com.goit.gojava7.kickstarter.console.ConsoleScanner;

@RunWith(MockitoJUnitRunner.class)
public class KickstarterTest {
	@Mock
	private ConsolePrinter consolePrinter;
	@Mock
	private ConsoleScanner consoleScanner;

	private DaoProvider initializer = new DaoProvider(DataSource.MEMORY);

	@InjectMocks
	private Kickstarter kickstarter = new Kickstarter(consolePrinter,
			consoleScanner, initializer);

	@Before
	public void setUp() {

	}

	@Test
	public void testShowCategoriesMenuEntered0SaysBye() {
		kickstarter.runKickstarter();

		verify(consolePrinter).print("Goodbye!");
	}

	@Test
	public void testShowCategoriesMenuEnter1NoCategoriesAtAll() {
		when(consoleScanner.scan()).thenReturn(1, 0);

		kickstarter.runKickstarter();

		verify(consolePrinter).print("Goodbye!");
	}

	@Test
	public void testShowCategoriesMenuEnter1Has1Category() {

		when(consoleScanner.scan()).thenReturn(1, 0);

		kickstarter.runKickstarter();

		verify(consolePrinter, times(2)).print(contains("1 : Games"));
		verify(consolePrinter).print("Goodbye!");
	}

	@Test
	public void testShowProjectsMenuEnter1Has1Project() {

		when(consoleScanner.scan()).thenReturn(1, 6, 0, 1, 0);

		kickstarter.runKickstarter();

		verify(consolePrinter, times(3)).print(contains("1 : Games"));
		verify(consolePrinter).print(
				contains("Please, enter the number between 0 and"));
		verify(consolePrinter, times(2)).print(contains("Super project"));
		verify(consolePrinter).print("Goodbye!");
	}

	@Test
	public void testShowProjectDetails() {

		when(consoleScanner.scan()).thenReturn(1, 1, 0);

		kickstarter.runKickstarter();

		verify(consolePrinter, times(2)).print(contains("1 : Games"));
		verify(consolePrinter).print(
				contains("You selected 'Super project' project"));
		verify(consolePrinter).print(contains("to invest in the project"));
		verify(consolePrinter).print("Goodbye!");
	}

	@Test
	public void testOwnAmontDonateInTheProject() {

		when(consoleScanner.scan()).thenReturn(1, 1, 1, 1, 0);
		when(consoleScanner.scanLine()).thenReturn("sf", "234234");

		kickstarter.runKickstarter();

		verify(consolePrinter, times(2)).print(contains("1 : Games"));
		verify(consolePrinter, times(2)).print(
				contains("You selected 'Super project' project"));
		verify(consolePrinter, times(4)).print(
				contains("to invest in the project"));
		verify(consolePrinter, times(4)).print(contains("0 : to project list"));
		verify(consolePrinter).print(contains("pledged: 31"));
		verify(consolePrinter).print("Goodbye!");
	}
}