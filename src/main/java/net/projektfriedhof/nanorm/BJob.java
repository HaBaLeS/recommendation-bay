package net.projektfriedhof.nanorm;

import java.sql.Connection;
import java.util.Date;

/**
 * Created by falko on 3/14/15.
 *
 * Class for a Backend Job. This is for Transaction handling,
 * metrics, dependency injecton and not at least separation ob Frontend to Backend
 *
 *
 *
 */
public abstract class BJob {

    /**
     * Managed Connection you can use to do stuff.
     * do not commit or close!!
     */
    private Connection connection;

    abstract BJobResult execute(Object ... args);

   /* private <T> T getService(String beanName){
        return (T)new Date();
    }*/


    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
