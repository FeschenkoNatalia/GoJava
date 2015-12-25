package ua.com.goit.gojava7.kickstarter.dao.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import ua.com.goit.gojava7.kickstarter.dao.DatabaseDao;
import ua.com.goit.gojava7.kickstarter.domain.Payment;

@Repository
public class PaymentDatabaseDao extends DatabaseDao<Payment>{
	private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(PaymentDatabaseDao.class);
	
	private static String table  = "payments";
    private static String fields = "id,cardOwner,cardNumber,projectId,amount";
	@Override
	public Connection getConnection() throws SQLException {
		return super.dataSource.getConnection();
	}

	protected Payment readElement(ResultSet resultSet) throws SQLException {
		Payment payment = new Payment();
		payment.setAmount(resultSet.getLong("amount"));
		payment.setCardNumber(resultSet.getLong("cardNumber"));
		payment.setCardOwner(resultSet.getString("cardOwner"));
		payment.setProjectId(resultSet.getInt("projectId"));
		payment.setId(resultSet.getInt("id"));
		return payment;
	}






	
	
	public List<Payment> getAll(){
        String query = "select " + fields + " from " + table;
        List<Payment> results = jdbcTemplate.query(query, new RowMapper<Payment>() {

            @Override
            public Payment mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                Payment payment = new Payment();
                payment.setAmount(resultSet.getLong("amount"));
                payment.setCardNumber(resultSet.getLong("cardNumber"));
                payment.setCardOwner(resultSet.getString("cardOwner"));
                payment.setProjectId(resultSet.getInt("projectId"));
                payment.setId(resultSet.getInt("id"));
                return payment;
            }});
        
        return results;
	}
	
	@Override
	public Payment get(int index) {
	    String query = "select " + fields + " from " + table + " where id="+index;
	    return jdbcTemplate.query(query, new ResultSetExtractor<Payment>(){

            @Override
            public Payment extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if (resultSet.next()) {
                    Payment payment = new Payment();
                    payment.setAmount(resultSet.getLong("amount"));
                    payment.setCardNumber(resultSet.getLong("cardNumber"));
                    payment.setCardOwner(resultSet.getString("cardOwner"));
                    payment.setProjectId(resultSet.getInt("projectId"));
                    payment.setId(resultSet.getInt("id"));
                    return payment;
                }
                throw new NoSuchElementException();
            }
	        
	    });
	}

	@Override
	public void add(Payment payment) {
		if (payment.getId() > 0) {
	        // update
	        String sql = "UPDATE payments SET cardOwner=?, cardNumber=?, projectId=?, "
	                    + "amount=? WHERE id=?";
	        jdbcTemplate.update(sql, payment.getCardOwner(), payment.getCardNumber(),
	                payment.getProjectId(), payment.getAmount(), payment.getId());
	    } else {
	        // insert
	        String sql = "INSERT INTO payments (cardOwner, cardNumber, projectId, amount)"
	                    + " VALUES (?, ?, ?, ?)";
	        jdbcTemplate.update(sql, payment.getCardOwner(), payment.getCardNumber(),
	                payment.getProjectId(), payment.getAmount());
	    }
		logger.debug("Adding/updating payment: " + payment.toString());

		
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
