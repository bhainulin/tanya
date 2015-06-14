package com.epam.jmp.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.jmp.database.connect.ConnectionPool;
import com.epam.jmp.model.Person;

public class PersonDAO implements IPersonDAO {

    private static final String SQL_SELECT_ALL = "SELECT * FROM `task5`.Person";
    private static final String SQL_SELECT_ONE_BY_ID = "SELECT * FROM `task5`.Person WHERE idPerson = ?";
    private static final String SQL_ADD =
     "INSERT INTO `task5`.`person` (`firstname`, `lastname`, `birthday`) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE =
     "UPDATE `task5`.`Person` SET `firstname`=?, `lastname`=?, `birthday`=? WHERE `idPerson`=?";
    private static final String SQL_DELETE = "DELETE FROM `task5`.`person` WHERE `idPerson` IN(";
    private static final Logger log = Logger.getLogger(PersonDAO.class);
    	
    private ConnectionPool connectionPool = new ConnectionPool(DRIVER, URL, USER, PASSWORD, POOL_SIZE);

   /* public void setConnectionPool(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }*/

    private Person buildPerson(ResultSet resultSet) throws SQLException {
    	Person person = new Person();
        person.setIdPerson(resultSet.getInt(1));
        person.setFirstname(resultSet.getString(2));
        person.setLastname(resultSet.getString(3));
        person.setBirthday(resultSet.getDate(4));
        return person;
    }

    @Override
    public List<Person> getList() {
        List<Person> listPerson = new ArrayList<Person>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SQL_SELECT_ALL);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	Person person = buildPerson(resultSet);
                listPerson.add(person);
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

        return listPerson;
    }
    
    @Override
    public Person fetchById(int id) {
    	Person person = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SQL_SELECT_ONE_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                person = buildPerson(resultSet);
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

        return person;
    }
    
    @Override
    public List<Person> search(String key, Object value)  {
    	List<Person> listPerson = new ArrayList<Person>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.getConnection();
            if(! (value instanceof Integer)){
            	value = "'" + value + "'";
            }
            statement = connection.prepareStatement("SELECT * FROM `task5`.Person WHERE `"+key+"` = " + value);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
            	Person person = buildPerson(resultSet);
                listPerson.add(person);
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

        return listPerson;
    }
    

    /**
     * @return new ID
     */
    @Override
    public int save(Person person) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        int personID = person.getIdPerson();

        try {
            connection = connectionPool.getConnection();
            if (personID == 0) {
                //statement = connection.prepareStatement(SQL_ADD, new String[]{"ID"});
            	statement = connection.prepareStatement(SQL_ADD);
                statement.setString(1, person.getFirstname());
                statement.setString(2, person.getLastname());
                statement.setDate(3, person.getBirthday());
                statement.execute();
                resultSet = statement.getGeneratedKeys();
                if (resultSet != null && resultSet.next()) {
                    personID = resultSet.getInt(1);
                }

            } else {
                statement = connection.prepareStatement(SQL_UPDATE);
                statement.setString(1, person.getFirstname());
                statement.setString(2, person.getLastname());
                statement.setDate(3, person.getBirthday());
                statement.setInt(4, personID);
                statement.execute();
            }

        } catch (SQLException e) {
            if (log.isDebugEnabled()) {
                log.error(PersonDAO.class.getName(), e);
            }
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    if (log.isDebugEnabled()) {
                        log.error(PersonDAO.class.getName(), ex);
                    }
                }
            }

            connectionPool.closeConnection(connection);

        }
        return personID;
    }
       
    @Override
    public void remove(Integer[] personID) {
        
        Connection connection = null;
        PreparedStatement statement = null;

        StringBuilder sql = new StringBuilder(SQL_DELETE);

        for (Integer id : personID) {
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
                log.error(PersonDAO.class.getName(), e);
            }
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    if (log.isDebugEnabled()) {
                        log.error(PersonDAO.class.getName(), ex);
                    }
                }
            }

            connectionPool.closeConnection(connection);

        }        
    }
 
}
