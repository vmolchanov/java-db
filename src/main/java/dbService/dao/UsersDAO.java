package dbService.dao;

import dbService.DBException;
import dbService.dataSets.UsersDataSet;
import dbService.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UsersDAO {
    private Executor executor;

    public UsersDAO(Connection connection) throws SQLException, DBException {
        this.executor = new Executor(connection);
        try {
            createTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public UsersDataSet get(long id) throws SQLException {
        return executor.execQuery("select * from users where id=" + id, result -> {
            result.next();
            return new UsersDataSet(result.getLong(1), result.getString(2));
        });
    }

    public List<UsersDataSet> getAll() throws SQLException {
        return executor.execQuery("select * from users", result -> {
            List<UsersDataSet> users = new ArrayList<>();
            while (result.next()) {
                users.add(new UsersDataSet(result.getLong("id"), result.getString("email")));
            }
            return users;
        });
    }

    public long getUserIdByEmail(String email) throws SQLException {
        String query = String.format("select * from users where email='%s'", email);
        return executor.execQuery(query, result -> {
            result.next();
            return result.getLong(1);
        });
    }

    public void insertUser(String email) throws SQLException {
        String query = String.format("insert into users (email) values ('%s')", email);
        executor.execUpdate(query);
    }

    public void createTable() throws SQLException {
        executor.execUpdate("create table if not exists users (id bigint auto_increment, email varchar(256), primary key (id))");
    }

    public void dropTable() throws SQLException {
        executor.execUpdate("drop table users");
    }
}
