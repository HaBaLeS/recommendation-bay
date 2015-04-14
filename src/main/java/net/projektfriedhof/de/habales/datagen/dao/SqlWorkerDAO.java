package net.projektfriedhof.de.habales.datagen.dao;

import jodd.petite.meta.PetiteBean;

import java.sql.Connection;

/**
 * Created by falko on 4/12/15.
 */
@PetiteBean(value = "sqlWorkerDAO")
public interface SqlWorkerDAO {

    /**
     * Simple Wrapper class for SQL Work
     */
    public abstract class SqlWork{

        public abstract void execute(Connection c);

    }

    /**
     * Execute a Unit of SQL Work within a Transaction.
     *
     * As you did not open the Connection dont close it!
     *
     * @param work
     */
    public void workOn(SqlWork work);


    /**
     * For simple parameterless Sql statements. You do not get any feedback about execution
     * You better use this for Testing purpose only.
     * @param sqlStatement
     */
    public void executeSQL(String sqlStatement);

}
