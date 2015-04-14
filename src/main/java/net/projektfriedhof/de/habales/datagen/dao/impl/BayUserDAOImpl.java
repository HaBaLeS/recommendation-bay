package net.projektfriedhof.de.habales.datagen.dao.impl;

import jodd.db.DbSession;
import jodd.db.DbThreadSession;
import jodd.petite.meta.PetiteBean;
import net.projektfriedhof.de.habales.datagen.dao.BayUserDAO;
import net.projektfriedhof.recbay.model.BayUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by falko on 4/11/15.
 */
@PetiteBean(value = "bayUserDAO")
public class BayUserDAOImpl implements BayUserDAO<BayUser> {

    final String INSERT_SQL = "INSERT INTO bayuser (nick,registration_time,email_address,passwordhash) VALUES (?,?,?,?)";

    @Override
    public void batchPersist(List<BayUser> batchList) {
        final DbSession session = DbThreadSession.getCurrentSession();

        try {

            final PreparedStatement ps = session.getConnection().prepareStatement(INSERT_SQL);
            for(BayUser bus : batchList){
                ps.setString(1,bus.getNick());
                ps.setDate(2, new Date( bus.getRegistration_time().getTime()));
                ps.setString(3,bus.getEmail_address());
                ps.setString(4,bus.getPasswordhash());
                ps.addBatch();
            }
            ps.executeBatch();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException("SQL not working :-/ ",e);
        }
    }

    @Override
    public List<BayUser> listMinimal() {

        List<BayUser> retList = new ArrayList<>();

        final DbSession session = DbThreadSession.getCurrentSession();
        try {
            final PreparedStatement stmt = session.getConnection().prepareStatement("select id,nick from bayuser");
            final ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()) {
                BayUser bu = new BayUser();
                bu.setId(resultSet.getLong(1));
                bu.setNick(resultSet.getString(2));
                retList.add(bu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return retList;
    }
}
