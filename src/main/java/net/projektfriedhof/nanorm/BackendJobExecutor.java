package net.projektfriedhof.nanorm;

/**
 * Created by falko on 3/14/15.
 *
 * Service which can execute BJobs. It can be with or without transaction
 * or completely without DB
 *
 */
public interface BackendJobExecutor {


    /**
     * Execute a Job with DB resource and  fully spwaned transaction
     * @param toExecute
     */
    public void executeDBJobWithTransaction(BJob toExecute);

    /**
     * Execute a Job without DB
     * @param toExecute
     */
    public void executeBJob(BJob toExecute);


}
