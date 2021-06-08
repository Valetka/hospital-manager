package com.hospital_manager.dao.implementations;

import com.hospital_manager.dao.AccountDAO;
import com.hospital_manager.dao.connection.ConnectionPool;
import com.hospital_manager.dao.connection.PoolProvider;
import com.hospital_manager.dao.connection.exception.ConnectionPoolException;
import com.hospital_manager.dao.exceptions.DAOException;
import com.hospital_manager.dao.exceptions.DataNotFoundException;
import com.hospital_manager.entities.Account;
import com.hospital_manager.entities.UserInfo;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class AccountDAOImplementation implements AccountDAO {
    private static final Logger logger = LogManager.getLogger(AccountDAOImplementation.class);

    private static final String FIND_BY_LOGIN_AND_PASSWORD = "SELECT * FROM accounts JOIN roles ON" +
            " accounts.id_role = roles.id WHERE login = ? and password = ? ";
    private static final String FIND_USER_BY_LOGIN = "select * from accounts where login = ?";
    private static final String FIND_ACCOUNT_BY_ID_AND_PASSWORD = "SELECT * FROM accounts where id=? and password= ?";
    private static final String UPDATE_PASSWORD = "update accounts set password = ? where id = ?";
    private static final String INSERT_ACCOUNT = "insert into accounts(login,password,id_role) VALUES (?,?,?)";
    private static final String INSERT_PATIENT = "insert into patients(account_id,status) VALUES (?,2)";
    private static final String INSERT_STAFF = "insert into staff(firstname,lastname,account) VALUES (?,?,?)";


    private final ConnectionPool connectionPool = PoolProvider.getConnectionPool();


    @Override
    public Account authorization(String login, String password) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Account account = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(FIND_BY_LOGIN_AND_PASSWORD);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                account = accountMapping(resultSet);
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.log(Level.ERROR,e);
            throw new DAOException("find error ",e);
        }finally {
            connectionPool.releaseConnection(connection);
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.log(Level.ERROR,e);
                    throw new DAOException("Close preparedStatement error ", e);
                }
            }
        }
        return account;
    }

    @Override
    public void registration(UserInfo regInfo) throws DAOException {
        Connection connection = null;
        PreparedStatement accountSt = null;
        Savepoint savepointOne = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);
            savepointOne = connection.setSavepoint("SavepointOne");

            insertAccount(regInfo,connection);
            accountSt = connection.prepareStatement(FIND_USER_BY_LOGIN);
            accountSt.setString(1, regInfo.getLogin());
            ResultSet resultSet = accountSt.executeQuery();
            if(resultSet.next()) {
                if (regInfo.getRoleId() == 3) {
                    insertPatient(resultSet.getInt(1), connection);
                } else {
                    insertStaff(regInfo, resultSet.getInt(1), connection);
                }
                connection.commit();
            }
            accountSt.close();

        } catch (SQLException | ConnectionPoolException throwables) {
            logger.log(Level.ERROR,throwables);
            try {
                if(connection!=null)
                    connection.rollback(savepointOne);
            } catch (SQLException e) {
                throw new DAOException(throwables);
            }
            throw new DAOException(throwables);
        }finally {
            connectionPool.releaseConnection(connection);
            try {
                if (accountSt != null && !accountSt.isClosed()) {
                    accountSt.close();
                }
            }catch (SQLException e){
                logger.log(Level.ERROR,e);
                throw new DAOException("Close preparedStatement error ", e);
            }
        }
    }

    @Override
    public void updatePassword(long accountId, String oldPass, String newPass) throws DAOException {
        Connection connection = null;
        Connection connectionForUpdate = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatementForUpdate = null;

        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(FIND_ACCOUNT_BY_ID_AND_PASSWORD);
            preparedStatement.setLong(1, accountId);
            preparedStatement.setString(2, oldPass);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                connectionForUpdate = connectionPool.getConnection();
                preparedStatementForUpdate = connectionForUpdate.prepareStatement(UPDATE_PASSWORD);
                preparedStatementForUpdate.setString(1, newPass);
                preparedStatementForUpdate.setLong(2, accountId);
                preparedStatementForUpdate.execute();
            }
            else {
                logger.log(Level.WARN,"wrong old password");
                throw new DataNotFoundException("wrong old password");
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.log(Level.WARN,e);
            throw new DAOException("find error ",e);
        }finally {
            connectionPool.releaseConnection(connection);
            connectionPool.releaseConnection(connectionForUpdate);
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.log(Level.WARN,e);
                    throw new DAOException("Close preparedStatement error ", e);
                }
            }
            if(preparedStatementForUpdate!=null){
                try {
                    preparedStatementForUpdate.close();
                } catch (SQLException e) {
                    logger.log(Level.WARN,e);
                    throw new DAOException("Close preparedStatement error ", e);
                }
            }
        }
    }

    @Override
    public Long findByLogin(String login) throws DAOException {
        Connection connection;
        PreparedStatement preparedStatement;
        Long id = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
        } catch (SQLException | ConnectionPoolException throwables) {
            logger.log(Level.WARN,throwables);
            throw new DAOException(throwables);
        }

        return id;
    }

    private void insertStaff(UserInfo info, int accountId, Connection connection)throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STAFF);
        preparedStatement.setString(1,info.getFirstname());
        preparedStatement.setString(2,info.getLastname());
        preparedStatement.setInt(3,accountId);
        preparedStatement.execute();
        preparedStatement.close();

    }

    private void insertPatient( int accountId,Connection connection) throws SQLException, ConnectionPoolException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PATIENT);
        preparedStatement.setInt(1,accountId);
        preparedStatement.execute();
        preparedStatement.close();
    }

    private void insertAccount(UserInfo userInfo, Connection connection) throws SQLException, ConnectionPoolException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT);
        preparedStatement.setString(1, userInfo.getLogin());
        preparedStatement.setString(2, userInfo.getPassword());
        preparedStatement.setLong(3, userInfo.getRoleId());
        preparedStatement.execute();
        preparedStatement.close();
    }

    private Account accountMapping(ResultSet resultSet) throws SQLException, ConnectionPoolException {
        Account account = new Account() ;
        account.setId(resultSet.getLong(1));
        account.setLogin(resultSet.getString(2));
        account.setPassword(resultSet.getString(3));
        account.setRoleId(resultSet.getLong(4));
        return account;
    }
}
