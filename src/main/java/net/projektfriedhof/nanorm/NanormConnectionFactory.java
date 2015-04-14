package net.projektfriedhof.nanorm;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;


/**
 * Created by falko on 3/12/15.
 */
public class NanormConnectionFactory {

    static DataSource ds;
    static boolean initialized = false;

    private NanormConnectionFactory(){

    }


    public static void init(){
        PoolProperties pp = new PoolProperties();
        ds = new DataSource(pp);
        ds.setCommitOnReturn(false);

        initialized = true;
    }


    public static Connection getConnection(){
        if(!initialized){
            throw new RuntimeException("Factory not initialized!");
        }
        try{
            Connection con = ds.getConnection();
            con.setAutoCommit(false);
            return con;
        } catch (Exception ex){
            throw new RuntimeException("Error creating Exception", ex);
        }
    }


}
