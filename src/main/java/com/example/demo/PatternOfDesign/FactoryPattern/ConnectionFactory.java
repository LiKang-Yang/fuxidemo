package com.example.demo.PatternOfDesign.FactoryPattern;

import java.sql.Connection;

public class ConnectionFactory {
    private final ThreadLocal<Connection> connections = new ThreadLocal<Connection>();

    public Connection currentConnection(){
        Connection conn = connections.get();
        if(conn == null){
            conn = createConnection();
            connections.set(conn);
        }
        return conn;
    }

    public void setCurrentConnection(Connection conn){
        connections.set(conn);
    }

    public void closeConnection(Connection conn){
        connections.remove();
    }

    public Connection createConnection(){
        return  null;
    }

}
