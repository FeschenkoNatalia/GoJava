package ua.nenya.pages;

import static org.junit.Assert.*;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;

import ua.nenya.dao.UserDao;
import ua.nenya.dao.memory.UserDaoMemoryImpl;
import ua.nenya.main.KickstarterInitilizer;
import ua.nenya.pages.RegistrationPage;
import ua.nenya.project.User;
import ua.nenya.util.IO;

public class RegistrationPageTest {

	private IO mockIo;
	private KickstarterInitilizer initilizer = new KickstarterInitilizer();
	private RegistrationPage registrationPage = new RegistrationPage();
	
	@Before
	public void init() {
		mockIo = mock(IO.class);
		UserDao users = new UserDaoMemoryImpl();
		User userAlex = new User("alex", "111", "a@a.ua");
		initilizer.setUserDao(users);
		initilizer.getUserDao().getUsers().add(userAlex);
	}
	
	@Test
	public void registrationPageTestUserExists() {
		
		when(mockIo.readConsole()).thenReturn("alex");
		
		registrationPage.registration(initilizer, mockIo);
		
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mockIo, times(2)).writeln(captor.capture());
        assertEquals(
                "[User with login alex alredy exists!, ]"
               , captor.getAllValues().toString());
	}
	
	@Test
	public void registrationPageTestInvalidLogin() {
		when(mockIo.readConsole()).thenReturn("");
		
		registrationPage.registration(initilizer, mockIo);
		
		InOrder order = inOrder(mockIo);
	      order.verify(mockIo).writeln("Login is invalid!");
	}
	
	@Test
	public void registrationPageTestNotConfirmedPassword() {
		when(mockIo.readConsole()).thenReturn("a").thenReturn("111").thenReturn("222");
		
		registrationPage.registration(initilizer, mockIo);
		
		InOrder order = inOrder(mockIo);
	      order.verify(mockIo).writeln("Password is invalid!");
	}
	
	@Test
	public void registrationPageTestInvalidEmail() {
		when(mockIo.readConsole()).thenReturn("a").thenReturn("a").thenReturn("a").thenReturn("a@.ua");
		
		registrationPage.registration(initilizer, mockIo);
		
		InOrder order = inOrder(mockIo);
	      order.verify(mockIo).writeln("Email is invalid!");
	}
	
	@Test
	public void registrationPageTestRegistrationFine() {
		when(mockIo.readConsole()).thenReturn("a").thenReturn("a").thenReturn("a").thenReturn("a@a.ua");
		
		registrationPage.registration(initilizer, mockIo);
		
		InOrder order = inOrder(mockIo);
	      order.verify(mockIo).writeln("You are registered");
	      order.verify(mockIo).writeln("");
	}

}
