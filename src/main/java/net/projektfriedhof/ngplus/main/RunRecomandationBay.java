package net.projektfriedhof.ngplus.main;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * @author Stas
 * @date 3/27/13
 */
public class RunRecomandationBay {
    private static final Logger log = LoggerFactory.getLogger(RunRecomandationBay.class);

    public static void main(String args[]) throws Exception {
        // add SLF4JBridgeHandler to j.u.l's root logger, should be done once during
        // the initialization phase of your application
       // SLF4JBridgeHandler.install();

    	/*#
    	 *Commandlione settings: 
    	 *
    	 *	-port
    	 *	-dbdir
    	 *	-logdir
    	 *	-da war noch was 
    	 *	-
    	 */
    	
    	// create Options object
    	Options options = new Options();
    	
    	// add t option
    	options.addOption("port", true, "Port to listen to. Default: 8080");
    	options.addOption("logDir", true, "Directory to log to. Default is " + System.getProperty("user.dir"));
    	options.addOption("db","database", true, "HSQLDB");
    	
    	
    	CommandLineParser parser = new PosixParser();
    	CommandLine cmd = parser.parse( options, args);
    	
    	// automatically generate the help statement
    	HelpFormatter formatter = new HelpFormatter();
    	formatter.printHelp( "java - jar ngplus-jar-with-dependencies.jar", options );
    	
    	
    	Option option = options.getOption("database");
    	
    	startSQLServer(option.getValue());
    	
        try {
        	EmbeddedServer server = new EmbeddedServer(8080);
            server.start();
            log.info("Server started");
            server.join();
            
            
        } catch (Exception e) {
            log.error("Failed to start server.", e);
            e.printStackTrace();
        }
    }

	private static void startSQLServer(String pathToDB) throws Exception{
		if(StringUtils.isEmpty(pathToDB)){
			pathToDB = "/tmp/ngplusDB";
		}
		
		HsqlProperties p = new HsqlProperties();
		 p.setProperty("server.database.0","file:" + pathToDB);
		 p.setProperty("server.dbname.0","ngplus_db");
		 // set up the rest of properties

		 // alternative to the above is
		 Server server = new Server();
		 server.setProperties(p);
		 server.setLogWriter(null); // can use custom writer
		 server.setErrWriter(null); // can use custom writer
		 server.start();
		
	}
}