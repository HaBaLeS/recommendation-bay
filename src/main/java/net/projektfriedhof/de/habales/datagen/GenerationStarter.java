package net.projektfriedhof.de.habales.datagen;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import jodd.db.DbSession;
import jodd.db.DbThreadSession;
import jodd.db.connection.ConnectionPoolDataSourceConnectionProvider;
import jodd.log.Logger;
import jodd.log.LoggerFactory;
import jodd.log.impl.SimpleLoggerFactory;
import jodd.petite.PetiteContainer;
import jodd.petite.scope.SingletonScope;
import net.projektfriedhof.de.habales.datagen.dao.impl.BayUserDAOImpl;
import net.projektfriedhof.de.habales.datagen.dao.impl.SqlWorkerDAOImpl;
import net.projektfriedhof.de.habales.datagen.services.NameGenerator;
import net.projektfriedhof.de.habales.datagen.services.SecureMessage;
import net.projektfriedhof.de.habales.datagen.services.impl.GaussRandomServiceImpl;
import net.projektfriedhof.de.habales.datagen.services.impl.NameGeneratorImpl;
import net.projektfriedhof.de.habales.datagen.services.impl.SecureMessageImpl;
import net.projektfriedhof.recbay.model.BayUser;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by falko on 4/9/15.
 */
public class GenerationStarter {

    private static Logger LOG;
    private PetiteContainer petite;

    /**
     * Main Class delegate to Non static run Method
     * @param args
     */
    public static void main(String args[] ){
        LoggerFactory.setLoggerFactory(new SimpleLoggerFactory(Logger.Level.INFO));
        LOG = LoggerFactory.getLogger(GenerationStarter.class);
        new GenerationStarter().run();



    }

    /**
     * High level Exceution
     */
    private void run() {
        LOG.info("Start!");

        //Set up IOC Container register all Beans here
        petiteSetup();

        petite.getBean(ImportJob.class).runJob();



        try {
            Thread.sleep(5000L);
        } catch (Exception e){

        }

        LOG.info("END");
    }


    private void petiteSetup() {

        petite = new PetiteContainer();
        petite.registerPetiteBean(ImportJob.class,null, null, null,false);
        petite.registerPetiteBean(NameGeneratorImpl.class,null, null, null,false);
        petite.registerPetiteBean(GaussRandomServiceImpl.class,null, SingletonScope.class,null,false);
        petite.registerPetiteBean(SecureMessageImpl.class,null, null, null,false);

        petite.registerPetiteBean(BayUserDAOImpl.class,null, null, null,false);
        petite.registerPetiteBean(SqlWorkerDAOImpl.class,null, null, null,false);


        petite.registerPetiteBean(MetricRegistry.class,null, SingletonScope.class,null,false);

    }


}
    

