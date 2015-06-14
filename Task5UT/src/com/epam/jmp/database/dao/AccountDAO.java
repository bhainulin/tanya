package com.epam.jmp.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.jmp.database.connect.ConnectionPool;
import com.epam.jmp.model.Account;
import com.epam.jmp.model.Bank;
import com.epam.jmp.model.CurrencyCode;

public class AccountDAO implements IAccountDAO {

    private static final String SQL_SELECT_ALL = "SELECT * FROM `task5`.Account";
    private static final String SQL_SELECT_ONE_BY_ID = "SELECT * FROM `task5`.Account WHERE idAccount = ?";
    private static final String SQL_ADD =
    "INSERT INTO `task5`.`account` (`idBank`, `idPerson`, `currencyCode`, `value`) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE =
     "UPDATE `task5`.`account` SET `idBank`=?, `idPerson`=?, `currencyCode`=?, `value`=? WHERE `idAccount`=?";
    private static final String SQL_DELETE = "DELETE FROM `task5`.`Account` WHERE `idAccount` IN(";
    private static final Logger log = Logger.getLogger(AccountDAO.class);
    	
    private ConnectionPool connectionPool = new ConnectionPool(DRIVER, URL, USER, PASSWORD, POOL_SIZE);

    private Account buildAccount(ResultSet resultSet) throws SQLException {
    	Account account = new Account();
        account.setIdAccount(resultSet.getInt(1));
        account.setIdBank(resultSet.getInt(2));
        account.setIdPerson(resultSet.getInt(3));
        account.setCurrencyCode(CurrencyCode.valueOf(resultSet.getString(4)));
        account.setValue(resultSet.getDouble(5));
        return account;
    }

    @Override
    public List<Account> getList() {
        List<Account> listAccount = new ArrayList<Account>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SQL_SELECT_ALL);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	Account account = buildAccount(resultSet);
                listAccount.add(account);
            }

        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                log.error(AccountDAO.class.getName(), e);
            }
        } finally {
            if (resultSet != null && statement != null) {
                try {
                    resultSet.close();
                    statement.close();
                } catch (SQLException ex) {
                    if (log.isDebugEnabled()) {
                        log.error(AccountDAO.class.getName(), ex);
                    }
                }
            }

            connectionPool.closeConnection(connection);

        }

        return listAccount;
    }
    
    @Override
    public List<Account> search(String key, Object value)  {
    	List<Account> list = new ArrayList<Account>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.getConnection();
            if(! (value instanceof Integer)){
            	value = "'" + value + "'";
            }
            statement = connection.prepareStatement("SELECT * FROM `task5`.account WHERE `"+key+"` = " + value);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	Account ob = buildAccount(resultSet);
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
    public Account fetchById(int id) {
    	Account account = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SQL_SELECT_ONE_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                account = buildAccount(resultSet);
            }

        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                log.error(AccountDAO.class.getName(), e);
            }
        } finally {
            if (resultSet != null && statement != null) {
                try {
                    resultSet.close();
                    statement.close();
                } catch (SQLException ex) {
                    if (log.isDebugEnabled()) {
                        log.error(AccountDAO.class.getName(), ex);
                    }
                }
            }

            connectionPool.closeConnection(connection);

        }

        return account;
    }
    
    
    /**
     * @return new ID
     */
    @Override
    public int save(Account account) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        int accountID = account.getIdPerson();

        try {
            connection = connectionPool.getConnection();
            if (accountID == 0) {
                //statement = connection.prepareStatement(SQL_ADD, new String[]{"ID"});
            	//"INSERT INTO `task5`.`account` (`idBank`, `idPerson`, `currencyCode`, `value`) VALUES (?, ?, ?, ?)";
            	statement = connection.prepareStatement(SQL_ADD);
                statement.setInt(1, account.getIdBank());
                statement.setInt(2, account.getIdPerson());
                statement.setString(3, account.getCurrencyCode().name());
                statement.setDouble(4, account.getValue());
                statement.execute();
                resultSet = statement.getGeneratedKeys();
                if (resultSet != null && resultSet.next()) {
                    accountID = resultSet.getInt(1);
                }

            } else {
                statement = connection.prepareStatement(SQL_UPDATE);
                statement.setInt(1, account.getIdBank());
                statement.setInt(2, account.getIdPerson());
                statement.setString(3, account.getCurrencyCode().name());
                statement.setDouble(4, account.getValue());
                statement.setInt(5, accountID);
                statement.execute();
            }

        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                log.error(AccountDAO.class.getName(), e);
            }
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    if (log.isDebugEnabled()) {
                        log.error(AccountDAO.class.getName(), ex);
                    }
                }
            }

            connectionPool.closeConnection(connection);

        }
        return accountID;
    }
       
    @Override
    public void remove(Integer[] accountID) {
        
        Connection connection = null;
        PreparedStatement statement = null;

        StringBuilder sql = new StringBuilder(SQL_DELETE);

        for (Integer id : accountID) {
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
                log.error(AccountDAO.class.getName(), e);
            }
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    if (log.isDebugEnabled()) {
                        log.error(AccountDAO.class.getName(), ex);
                    }
                }
            }

            connectionPool.closeConnection(connection);

        }        
    }
 
}
