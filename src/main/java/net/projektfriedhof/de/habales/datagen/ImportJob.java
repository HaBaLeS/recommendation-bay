package net.projektfriedhof.de.habales.datagen;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import jodd.db.DbSession;
import jodd.db.DbThreadSession;
import jodd.db.DbTransactionMode;
import jodd.db.connection.ConnectionPoolDataSourceConnectionProvider;
import jodd.log.Logger;
import jodd.log.LoggerFactory;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import net.projektfriedhof.de.habales.datagen.dao.BayUserDAO;
import net.projektfriedhof.de.habales.datagen.dao.SqlWorkerDAO;
import net.projektfriedhof.de.habales.datagen.services.GaussRandomService;
import net.projektfriedhof.de.habales.datagen.services.NameGenerator;
import net.projektfriedhof.de.habales.datagen.services.SecureMessage;
import net.projektfriedhof.recbay.model.BayUser;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by falko on 4/11/15.
 */
@PetiteBean
public class ImportJob {

    Logger LOG = LoggerFactory.getLogger(ImportJob.class);

    @PetiteInject
    private NameGenerator gen; // = (NameGenerator) petite.getBean("nameGenerator");

    @PetiteInject
    private SecureMessage sec; //  = (SecureMessage) petite.getBean("secureMessage");

    @PetiteInject
    private MetricRegistry registry; //  = (MetricRegistry) petite.getBean(MetricRegistry.class);

    @PetiteInject
    private BayUserDAO<BayUser> bayUserDAO;

    @PetiteInject
    private SqlWorkerDAO sqlWorkerDAO;

    @PetiteInject
    private GaussRandomService gaussRandom;


    int MUCHO_RECCOMENDATIONE = 1000000;//00000; //EINE Millionen MUAHAHAAHAH;

    public void runJob() {

        //Start Console Logging
        startMetricsLogger();

        //Start s Database Sessin an other magic Stuff related to it
        //Start Transaction
        initDBConnecting();

        startGeneration();


        //close DB and session/transaction
        shutdownDB();


    }

    private void shutdownDB() {
        final DbSession session= DbThreadSession.getCurrentSession();
        session.commitTransaction();
        session.closeSession();
    }


    private void initDBConnecting() {
        PoolProperties pp = new PoolProperties();

        pp.setUrl("jdbc:postgresql://minecraft.habales.de:5432/recbay");
        pp.setUsername("recbay");
        pp.setPassword("yicMymojtyk9");
        pp.setDriverClassName("org.postgresql.Driver");

        DataSource ds = new DataSource(pp);
        ds.setCommitOnReturn(false);

        // create session and assign it to the thread
        DbSession session = new DbThreadSession(new ConnectionPoolDataSourceConnectionProvider(ds));

        session.beginTransaction(new DbTransactionMode().setReadOnly(false));
    }

    private void startMetricsLogger() {


/*
        final ConsoleReporter reporter = ConsoleReporter.forRegistry(registry)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(10, TimeUnit.SECONDS);*/
    }

    /**
     * Everythin is set up we start our Work
     */
    private void startGeneration() {

        //Delete old Stuff
        cleanTestDataBase();
        LOG.info("Done Cleaning DB");

        //Generate a brunch of user names
        List<BayUser> users = generateUsers(10000);
        LOG.info("Done creating and persisting 1000 Users");

        final List<BayUser> bayUsers = bayUserDAO.listMinimal();
        LOG.info("Done fetching all the Userz");

        //welcomePost for every USer!


        for(int i=0; i< MUCHO_RECCOMENDATIONE;i++){

            //finde User Der NAch Statistik Was Übermittelt
            int idx = gaussRandom.getNextInt(10000);
            BayUser bu = bayUsers.get(idx);

            //baur Random Reccomendation

            //adde zu Batch

            //alle 10000 wir machen einen Batch flush

        }


    }

    private List<BayUser> generateUsers(int amount) {

        List<BayUser> retList = new ArrayList<>(amount);



        Date allRegDate = new Date(0);
        String allPass = sec.sha265Hex("supergeheim");

        List<String> names = gen.generateNames(amount);

        for(String name : names){
            BayUser bayUser = new BayUser();
            bayUser.setNick(name);
            bayUser.setRegistration_time(allRegDate);
            bayUser.setEmail_address(name.replaceAll(" ","_")+"@habales.de");
            bayUser.setPasswordhash(allPass);
            retList.add(bayUser);
        }

        bayUserDAO.batchPersist(retList);

        return retList;
    }

    /**
     * Clean up  Test Database for Import Tests.
     *
     */
    private void cleanTestDataBase() {
        sqlWorkerDAO.executeSQL("truncate table bayuser cascade");

        /*
            truncate table "type" cascade;
            INSERT INTO "type" (id,name,description) VALUES (10,'rec.type.url','URL to any website URL to resources are identified by type of content');
            INSERT INTO "type" (id,name,description) VALUES (20,'rec.type.url','Text content');
            INSERT INTO "type" (id,name,description) VALUES (30,'rec.type.url','Image Uri');
            INSERT INTO "type" (id,name,description) VALUES (40,'rec.type.video','Video Uri');
            INSERT INTO "type" (id,name,description) VALUES (50,'rec.type.binary','Binary Uri');
            INSERT INTO "type" (id,name,description) VALUES (60,'rec.type.ebook','eBook Uri');
        */

    }

}
