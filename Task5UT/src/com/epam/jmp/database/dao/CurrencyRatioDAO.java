package com.epam.jmp.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.jmp.database.connect.ConnectionPool;
import com.epam.jmp.model.CurrencyRatio;
import com.epam.jmp.model.CurrencyCode;

public class CurrencyRatioDAO implements ICurrencyRatioDAO {

    private static final String SQL_SELECT_ALL = "SELECT * FROM `task5`.CurrencyRatio";
    private static final String SQL_SELECT_ONE_BY_ID = "SELECT * FROM `task5`.CurrencyRatio WHERE id = ?";
    private static final String SQL_SELECT_ONE_BY_ID_BANK = "SELECT * FROM `task5`.CurrencyRatio WHERE idBank = ?";
    private static final String SQL_ADD =
     "INSERT INTO `task5`.`currencyratio` (`idBank`, `initial`, `result`, `ratio`) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE =
     "UPDATE `task5`.`currencyratio` SET `idBank`=?, `initial`=?, `result`=?, `ratio`=? WHERE `id`=?";
    private static final String SQL_DELETE = "DELETE FROM `task5`.`currencyratio` WHERE `id` IN(";
    private static final Logger log = Logger.getLogger(CurrencyRatioDAO.class);
    	
    private ConnectionPool connectionPool = new ConnectionPool(DRIVER, URL, USER, PASSWORD, POOL_SIZE);

    private CurrencyRatio buildCurrencyRatio(ResultSet resultSet) throws SQLException {
    	CurrencyRatio currencyRatio = new CurrencyRatio();
        currencyRatio.setId(resultSet.getInt(1));
        currencyRatio.setIdBank(resultSet.getInt(2));
        currencyRatio.setInitial(CurrencyCode.valueOf(resultSet.getString(3)));
        currencyRatio.setResult(CurrencyCode.valueOf(resultSet.getString(4)));
        currencyRatio.setRatio(resultSet.getDouble(5));
        return currencyRatio;
    }

    @Override
    public List<CurrencyRatio> getList() {
        List<CurrencyRatio> listCurrencyRatio = new ArrayList<CurrencyRatio>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SQL_SELECT_ALL);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	CurrencyRatio currencyRatio = buildCurrencyRatio(resultSet);
                listCurrencyRatio.add(currencyRatio);
            }

        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                log.error(CurrencyRatioDAO.class.getName(), e);
            }
        } finally {
            if (resultSet != null && statement != null) {
                try {
                    resultSet.close();
                    statement.close();
                } catch (SQLException ex) {
                    if (log.isDebugEnabled()) {
                        log.error(CurrencyRatioDAO.class.getName(), ex);
                    }
                }
            }

            connectionPool.closeConnection(connection);

        }

        return listCurrencyRatio;
    }
    
      
    @Override
    public CurrencyRatio fetchById(int id) {
    	CurrencyRatio currencyRatio = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SQL_SELECT_ONE_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                currencyRatio = buildCurrencyRatio(resultSet);
            }

        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                log.error(CurrencyRatioDAO.class.getName(), e);
            }
        } finally {
            if (resultSet != null && statement != null) {
                try {
                    resultSet.close();
                    statement.close();
                } catch (SQLException ex) {
                    if (log.isDebugEnabled()) {
                        log.error(CurrencyRatioDAO.class.getName(), ex);
                    }
                }
            }

            connectionPool.closeConnection(connection);

        }

        return currencyRatio;
    }
    
    
    @Override
    public List<CurrencyRatio> search(String key, Object value)  {
    	List<CurrencyRatio> list = new ArrayList<CurrencyRatio>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.getConnection();
            if(! (value instanceof Integer)){
            	value = "'" + value + "'";
            }
            statement = connection.prepareStatement("SELECT * FROM `task5`.CurrencyRatio WHERE `"+key+"` = " + value);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	CurrencyRatio ob = buildCurrencyRatio(resultSet);
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
    public List<CurrencyRatio> fetchByBankId(int id) {
    	List<CurrencyRatio> listCurrencyRatio = new ArrayList<CurrencyRatio>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SQL_SELECT_ONE_BY_ID_BANK);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	CurrencyRatio currencyRatio = buildCurrencyRatio(resultSet);
                listCurrencyRatio.add(currencyRatio);
            }

        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                log.error(CurrencyRatioDAO.class.getName(), e);
            }
        } finally {
            if (resultSet != null && statement != null) {
                try {
                    resultSet.close();
                    statement.close();
                } catch (SQLException ex) {
                    if (log.isDebugEnabled()) {
                        log.error(CurrencyRatioDAO.class.getName(), ex);
                    }
                }
            }

            connectionPool.closeConnection(connection);

        }

        return listCurrencyRatio;
    }
    
    
    /**
     * @return new ID
     */
    @Override
    public int save(CurrencyRatio currencyRatio) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        int currencyRatioID = currencyRatio.getId();

        try {
            connection = connectionPool.getConnection();
            if (currencyRatioID == 0) {
                //statement = connection.prepareStatement(SQL_ADD, new String[]{"ID"});
            	//"INSERT INTO `task5`.`currencyratio` (`idBank`, `initial`, `result`, `ratio`) VALUES (?, ?, ?, ?)";
            	statement = connection.prepareStatement(SQL_ADD);
                statement.setInt(1, currencyRatio.getIdBank());
                statement.setString(2, currencyRatio.getInitial().name());
                statement.setString(3, currencyRatio.getResult().name());
                statement.setDouble(4, currencyRatio.getRatio());
                statement.execute();
                resultSet = statement.getGeneratedKeys();
                if (resultSet != null && resultSet.next()) {
                    currencyRatioID = resultSet.getInt(1);
                }

            } else {
                statement = connection.prepareStatement(SQL_UPDATE);
                statement.setInt(1, currencyRatio.getIdBank());
                statement.setString(2, currencyRatio.getInitial().name());
                statement.setString(3, currencyRatio.getResult().name());
                statement.setDouble(4, currencyRatio.getRatio());
                statement.setInt(5, currencyRatioID);
                statement.execute();
            }

        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                log.error(CurrencyRatioDAO.class.getName(), e);
            }
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    if (log.isDebugEnabled()) {
                        log.error(CurrencyRatioDAO.class.getName(), ex);
                    }
                }
            }

            connectionPool.closeConnection(connection);

        }
        return currencyRatioID;
    }
       
    @Override
    public void remove(Integer[] currencyRatioID) {
        
        Connection connection = null;
        PreparedStatement statement = null;

        StringBuilder sql = new StringBuilder(SQL_DELETE);

        for (Integer id : currencyRatioID) {
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
                log.error(CurrencyRatioDAO.class.getName(), e);
            }
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    if (log.isDebugEnabled()) {
                        log.error(CurrencyRatioDAO.class.getName(), ex);
                    }
                }
            }

            connectionPool.closeConnection(connection);

        }        
    }
 
}
