package com.epam.jmp.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.jmp.database.connect.ConnectionPool;
import com.epam.jmp.model.Bank;

public class BankDAO implements IBankDAO {

    private static final String SQL_SELECT_ALL = "SELECT * FROM `task5`.Bank";
    private static final String SQL_SELECT_ONE_BY_ID = "SELECT * FROM `task5`.Bank WHERE idBank = ?";
    private static final String SQL_ADD =
     "INSERT INTO `task5`.`Bank` (`name`, `street`, `number`, `phone`) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE =
     "UPDATE `task5`.`Bank` SET `name`=?, `street`=?, `number`=?, `phone`=? WHERE `idBank`=?";
    private static final String SQL_DELETE = "DELETE FROM `task5`.`Bank` WHERE `idBank` IN(";
    private static final Logger log = Logger.getLogger(BankDAO.class);
    	
    private ConnectionPool connectionPool = new ConnectionPool(DRIVER, URL, USER, PASSWORD, POOL_SIZE);

    private Bank buildBank(ResultSet resultSet) throws SQLException {
    	Bank bank = new Bank();
        bank.setIdBank(resultSet.getInt(1));
        bank.setName(resultSet.getString(2));
        bank.setStreet(resultSet.getString(3));
        bank.setNumber(resultSet.getString(4));
        bank.setPhone(resultSet.getString(4));
        return bank;
    }

    @Override
    public List<Bank> getList() {
        List<Bank> listBank = new ArrayList<Bank>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SQL_SELECT_ALL);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	Bank bank = buildBank(resultSet);
                listBank.add(bank);
            }

        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                log.error(BankDAO.class.getName(), e);
            }
        } finally {
            if (resultSet != null && statement != null) {
                try {
                    resultSet.close();
                    statement.close();
                } catch (SQLException ex) {
                    if (log.isDebugEnabled()) {
                        log.error(BankDAO.class.getName(), ex);
                    }
                }
            }

            connectionPool.closeConnection(connection);

        }

        return listBank;
    }
    
    @Override
    public List<Bank> search(String key, Object value)  {
    	List<Bank> list = new ArrayList<Bank>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.getConnection();
            if(! (value instanceof Integer)){
            	value = "'" + value + "'";
            }
            statement = connection.prepareStatement("SELECT * FROM `task5`.Bank WHERE `"+key+"` = " + value);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	Bank ob = buildBank(resultSet);
            	list.add(ob);
            }

        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                log.error(PersonDAO.class.getName(), e);
            }
        } finally {
            if (resultSet != null && statement != null) {
                try {
                    resultSet.close();
                    statement.close();
                } catch (SQLException ex) {
                    if (log.isDebugEnabled()) {
                        log.error(PersonDAO.class.getName(), ex);
                    }
                }
            }

            connectionPool.closeConnection(connection);

        }

        return list;
    }
    
    
    @Override
    public Bank fetchById(int id) {
    	Bank bank = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SQL_SELECT_ONE_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                bank = buildBank(resultSet);
            }

        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                log.error(BankDAO.class.getName(), e);
            }
        } finally {
            if (resultSet != null && statement != null) {
                try {
                    resultSet.close();
                    statement.close();
                } catch (SQLException ex) {
                    if (log.isDebugEnabled()) {
                        log.error(BankDAO.class.getName(), ex);
                    }
                }
            }

            connectionPool.closeConnection(connection);

        }

        return bank;
    }
    

    /**
     * @return new ID
     */
    @Override
    public int save(Bank bank) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        int bankID = bank.getIdBank();

        try {
            connection = connectionPool.getConnection();
            if (bankID == 0) {
                //statement = connection.prepareStatement(SQL_ADD, new String[]{"ID"});
            	statement = connection.prepareStatement(SQL_ADD);
                statement.setString(1, bank.getName());
                statement.setString(2, bank.getStreet());
                statement.setString(3, bank.getNumber());
                statement.setString(4, bank.getPhone());
                statement.execute();
                resultSet = statement.getGeneratedKeys();
                if (resultSet != null && resultSet.next()) {
                    bankID = resultSet.getInt(1);
                }

            } else {
                statement = connection.prepareStatement(SQL_UPDATE);
                statement.setString(1, bank.getName());
                statement.setString(2, bank.getStreet());
                statement.setString(3, bank.getNumber());
                statement.setString(4, bank.getPhone());
                statement.setInt(5, bankID);
                statement.execute();
            }

        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                log.error(BankDAO.class.getName(), e);
            }
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    if (log.isDebugEnabled()) {
                        log.error(BankDAO.class.getName(), ex);
                    }
                }
            }

            connectionPool.closeConnection(connection);

        }
        return bankID;
    }
       
    @Override
    public void remove(Integer[] bankID) {
        
        Connection connection = null;
        PreparedStatement statement = null;

        StringBuilder sql = new StringBuilder(SQL_DELETE);

        for (Integer id : bankID) {
            sql.append(id);
            sql.append(",");
        }
        int strLen = sql.length();
        sql.replace(strLen - 1, strLen, ")");


        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(sql.toString());
            statement.executeUpdate();

        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                log.error(BankDAO.class.getName(), e);
            }
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    if (log.isDebugEnabled()) {
                        log.error(BankDAO.class.getName(), ex);
                    }
                }
            }

            connectionPool.closeConnection(connection);

        }        
    }
 
}
