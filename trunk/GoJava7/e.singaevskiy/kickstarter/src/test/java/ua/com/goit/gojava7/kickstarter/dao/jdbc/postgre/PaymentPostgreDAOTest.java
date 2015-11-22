package ua.com.goit.gojava7.kickstarter.dao.jdbc.postgre;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ua.com.goit.gojava7.kickstarter.beans.Project;
import ua.com.goit.gojava7.kickstarter.dao.jdbc.JdbcDispatcher;
import ua.com.goit.gojava7.kickstarter.dao.memory.util.Memory;
import ua.com.goit.gojava7.kickstarter.util.Utils;

public class PaymentPostgreDAOTest {

    Memory mem;
    PaymentPostgreDAO dao;

    @Before
    public void setUp() throws Exception {
        Properties properties = Utils.readProperties("./kicks-files/config.properties");
        JdbcDispatcher dispatcher = new JdbcDispatcher(
                properties.getProperty("driver"),
                properties.getProperty("url"),
                properties.getProperty("user"), 
                properties.getProperty("password"));
  
        mem = new Memory();        
        dao = new PaymentPostgreDAO(dispatcher);         
    }

    @Test
    public void testAddGetAll() {
        dao.clear();
        dao.addAll(mem.getPayments());
        assertThat(dao.getAll(), is(mem.getPayments()));
    }
    
    @Test
    public void testAddGet() {
        assertFalse(true);
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void testException() throws Exception {
        JdbcDispatcher dispatcher = Mockito.mock(JdbcDispatcher.class);
        Mockito.when(dispatcher.getConnection()).thenThrow(SQLException.class);
        dao = new PaymentPostgreDAO(dispatcher); 
        dao.clear();
        dao.addAll(mem.getPayments());
        dao.getAll();
        dao.getByProject(new Project());
    }
}
