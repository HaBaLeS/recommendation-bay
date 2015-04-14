package net.projektfriedhof.nanorm;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by falko on 3/14/15.
 */
public class BackendJobExecutorImpl implements BackendJobExecutor {

    @Override
    public void executeDBJobWithTransaction(BJob toExecute) {
        beginBJob(toExecute);

        try{
            toExecute.execute();
            commitBJob(toExecute);
        } catch ( Exception e){
            rollbackBJob(toExecute);
            throw new RuntimeException("Error Executing BJob" ,e);
        } finally {
          closeBJob(toExecute);
        }



    }

    /**
     * Close connection and remove fom bJob
     * @param toExecute
     */
    private void closeBJob(BJob toExecute) {
        try {
            toExecute.getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException("Error closing connection", e);
        }
        toExecute.setConnection(null);
        
    }

    /**
     * Rollback the connection
     * @param toExecute
     */
    private void rollbackBJob(BJob toExecute) {
        try {
            toExecute.getConnection().rollback();
        } catch (SQLException e) {
            throw new RuntimeException("Error rolling back connection", e);
        }
    }

    /**
     * Commit the connection
     * @param toExecute
     */
    private void commitBJob(BJob toExecute) {

        try {
            toExecute.getConnection().commit();
        } catch (SQLException e) {
            throw new RuntimeException("Error commiting connection", e);
        }
    }

    /**
     * Create a connection an make available to BJob
     * @param toExecute
     */
    private void beginBJob(BJob toExecute) {
        Connection con = NanormConnectionFactory.getConnection();
        toExecute.setConnection(con);
    }

    @Override
    public void executeBJob(BJob toExecute) {
        toExecute.execute();
    }

}
