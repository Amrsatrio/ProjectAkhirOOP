package com.amrsatrio.school.database;

import java.sql.*;

public final class DatabaseConnection {
    private final Connection connection;

    public DatabaseConnection(ConnectionParams params) throws SQLException {
        connection = DriverManager.getConnection(params.getUri(), params.username, params.password);
    }

    public ResultSet executeQuery(String query) throws SQLException {
        return connection.createStatement().executeQuery(query);
    }

    public int executeUpdate(String query) throws SQLException {
        return connection.createStatement().executeUpdate(query);
    }

    public PreparedStatement prepareStatement(String query) throws SQLException {
        return connection.prepareStatement(query);
    }

    public Connection getConnection() {
        return connection;
    }

    public static class ConnectionParams {
        private String username = "root";
        private String password = "";
        private String database = "";
        private String host = "localhost:3306";

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setDatabase(String database) {
            this.database = database;
        }

        public void setHost(String host) {
            this.host = host;
        }

        private String getUri() {
            return String.format("jdbc:mysql://%s/%s", host, database);
        }
    }
}
