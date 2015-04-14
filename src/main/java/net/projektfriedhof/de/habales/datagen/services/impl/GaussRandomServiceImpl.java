package net.projektfriedhof.de.habales.datagen.services.impl;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Histogram;
import jodd.petite.meta.PetiteBean;
import net.projektfriedhof.de.habales.datagen.services.GaussRandomService;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

/**
 * Created by falko on 4/13/15.
 */
@PetiteBean(value = "gaussRandom")
public class GaussRandomServiceImpl implements GaussRandomService {

    private final static SecureRandom secRan = new SecureRandom();



/*
    public static void main(String[] arg){

        final Histogram hist = mr.histogram("muahaah");

        final ConsoleReporter reporter = ConsoleReporter.forRegistry(mr)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(1, TimeUnit.SECONDS);



        for(int i= 0; i<5000000;i++){

            //var z = buildGuassRandom_BoxMueller()
            double z = buildGuassRandom_Polar();

            //hard limit to -4 to 4 so we can move our
            //dataset around to get the testdata we want.
            while(z > 4 || z < -4){
                z = buildGuassRandom_Polar();
                //z = buildGuassRandom_BoxMueller()
            }
            z = z+4; //make it positiv vom 0 to 8

            //z = z * 1250 //make results form 0 to 10000

            hist.update((int)(z*1250));

            //System.out.println(z);
        }

        try {
           // Thread.sleep(5000);
        }catch (Exception e){

        }


    }
*/
    private double buildGuassRandom_Polar() {
        double u = 0d;
        double v = 0d;
        double q = 0d;
        do{
            u = 2*Math.abs(secRan.nextDouble()) -1;
            v = 2*Math.abs(secRan.nextDouble()) -1;

            q = u*u +v*v;

        } while( q == 0d || q>1d);
        double p = Math.sqrt(-2* Math.log(q) / q);
        return u * p;
    }

    @Override
    public int getNextInt(int max) {

        double z = buildGuassRandom_Polar();

        //hard limit to -4 to 4 so we can move our
        //dataset around to get the testdata we want.
        while(z > 3 || z < -3){
            z = buildGuassRandom_Polar();
        }
        z = z+3; //make it positive vom 0 to 8
        z = z * (double)(max/8d);

        return (int)z;
    }
}
