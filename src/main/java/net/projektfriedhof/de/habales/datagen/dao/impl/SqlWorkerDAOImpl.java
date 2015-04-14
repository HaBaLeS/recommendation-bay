package net.projektfriedhof.de.habales.datagen.dao.impl;

import jodd.db.DbSession;
import jodd.db.DbThreadSession;
import jodd.log.Logger;
import jodd.log.LoggerFactory;
import jodd.petite.meta.PetiteBean;
import net.projektfriedhof.de.habales.datagen.dao.SqlWorkerDAO;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by falko on 4/12/15.
 */
@PetiteBean(value = "sqlWorkerDAO")
public class SqlWorkerDAOImpl implements SqlWorkerDAO{

    static final Logger LOG = LoggerFactory.getLogger(SqlWorkerDAOImpl.class);


    /**
     *
     * @param work
     */
    @Override
    public void workOn(SqlWork work) {
        final DbSession session = DbThreadSession.getCurrentSession();
        final Connection con = session.getConnection();
        work.execute(con);
        try {
            if (con.isClosed()) {
                throw new RuntimeException("You closed the connection. Please do not do this!");
            }
        }catch (SQLException e){
            throw new RuntimeException("SQL Error while Checking if connection was closed");
        }
    }

    /**
     *
     * @param sqlStatement
     */
    @Override
    public void executeSQL(String sqlStatement) {
        workOn(new SqlWork(){
            @Override
            public void execute(Connection c) {
                try {
                    c.createStatement().execute(sqlStatement);
                } catch (Exception e){
                    throw new RuntimeException("Error executing SQL: " + sqlStatement ,e);
                }
            }
        });
    }
}
