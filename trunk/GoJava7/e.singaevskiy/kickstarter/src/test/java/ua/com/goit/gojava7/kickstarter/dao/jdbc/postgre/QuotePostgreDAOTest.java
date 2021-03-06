package ua.com.goit.gojava7.kickstarter.dao.jdbc.postgre;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ua.com.goit.gojava7.kickstarter.dao.jdbc.util.JdbcDispatcher;
import ua.com.goit.gojava7.kickstarter.domain.Quote;
import ua.com.goit.gojava7.kickstarter.util.Utils;

public class QuotePostgreDAOTest {

    QuotePostgreDAO dao;
    List<Quote> list;

    @Before
    public void setUp() throws Exception {

        Properties properties = Utils.readProperties("./src/test/resources/storages/db/config.properties");

        JdbcDispatcher dispatcher = new JdbcDispatcher(
                properties.getProperty("driver"), properties.getProperty("url"),
                properties.getProperty("user"), properties.getProperty("password"));

        dao = new QuotePostgreDAO(dispatcher); 
        
        list = new ArrayList<>();
        list.add(new Quote("a1", "t1"));
        list.add(new Quote("a2", "t2"));

    }
    
    @After
    public void tearDown() throws Exception {
        dao.clear();
    }

    @Test
    public void testAddGetAll() {   
        dao.addAll(list);
        assertThat(dao.getAll(), is(list));
    }

    @Test
    public void testAddGet() {
        Quote element = new Quote("a3", "t3");
        dao.add(new Quote("a0", "t0"));
        dao.add(element);
        dao.add(new Quote("a2", "t2"));
        assertThat(dao.get(1), is(element));
    }

    @Test
    public void testException() {
        Quote element = new Quote("a1", "t1");
        dao.add(element);
        dao.add(element);
        assertTrue(true);
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void testGoDespiteException() throws Exception {
        JdbcDispatcher dispatcher = Mockito.mock(JdbcDispatcher.class);
        Mockito.when(dispatcher.getConnection()).thenThrow(SQLException.class);
        dao = new QuotePostgreDAO(dispatcher);
        dao.clear();
        dao.add(null);
        dao.addAll(list);
        dao.getAll();
        dao.get(0);
        assertTrue(true);
    }

}
