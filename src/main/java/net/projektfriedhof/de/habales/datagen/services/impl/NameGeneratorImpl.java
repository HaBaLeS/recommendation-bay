package net.projektfriedhof.de.habales.datagen.services.impl;

import com.codahale.metrics.*;
import com.codahale.metrics.Timer;
import jodd.log.Logger;
import jodd.log.LoggerFactory;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;
import net.projektfriedhof.de.habales.datagen.services.NameGenerator;

import java.util.*;

/**
 * Generator for names. this implementation uses a fixed random seed.
 * So the names are deterministic as long as the input dictionary is the same.
 *
 *
 * Created by falko on 4/9/15.
 */
@PetiteBean(value = "nameGenerator")
public class NameGeneratorImpl implements NameGenerator {

    static final Logger LOG = LoggerFactory.getLogger("NameGeneratorImpl");

    String dictBase = "aki alf alfgeir amundi ari armod arnfinn arnlaug arnor aslak gorggon killar bardi attila bergthor bersi bjarni bjorn sniff uttar adanas bodvar bork botolf brand ekard bui binolf usgard egil einar eindridi eirik eldgrim erlend eyjolf eystein eyvind finn finnbogi fridgeir gardi geir geirmund geirstein gest gizur glum grani grim gudmund gunnar gunnbjorn gunnlaug hafgrim hakon halfdan hall halldor hallfred harald harek hastein hauk havard hedin helgi herjolf hjalti hogni hord hrafn hring hroald hrut illugi ingi ingjald ingolf isleif ivar kalf kari karlsefni ketil knut kol kolbein lambi leif ljot ljotolf lodin mord odd ofeig ogmund olaf olvir onund orm otkel otrygg ottar ozur ragnar rognvald runolf sam sighvat sigmund sigtrygg sigulf sigurd sigwulf skapti snorri solmund solvi starkad stein steinkel steinthor sturla styrkar sumarlidi svein thjodolf thjostolf thorarin thorbjorn thorbrand thord thorfinn thorgeir thorgest thorgils thorgrim thorhall thorir thorkel thormod thorstein thorvald thrain thrand tosti ulf uni vagn valgard vandrad vermund vestein vigfus yngvar ";

    final Random r = new Random(73119); //this will alway produce the same random numbers!!!

    @PetiteInject
    private MetricRegistry metrics;


    int MIN_LEN = 4;
    int MAX_LEN = 10;

    /**
     * Generate fa list of words from a dictionary.
     * the alogrithm is an implementation of Chris Pound's language machine lc (http://www.ruf.rice.edu/~pound/#scripts)
     * @param amount
     * @return
     */
    @Override
    public List<String> generateNames(int amount) {

        final Counter ngCount = metrics.counter("nameGenerations");

        Map<String,String> followers = new HashMap<>();
        Set<String> retList = new HashSet<>(amount);

        //Buld a map of every letter that is following 2 key letters
        String[] names = dictBase.split(" ");
        for(String name : names){
            char[] nameC = name.toCharArray();
            for(int i =0; i <nameC.length-2;i++){
                String key = ""+ nameC[i] + nameC[i+1];
                String vals = followers.get(key);
                if(vals == null){
                    vals = "";
                }
                followers.put(key,vals + nameC[i+2]);
            }
        }

        int keysize = followers.size();
        List<String> keyList = new ArrayList<>(followers.keySet());

        while(retList.size() < amount){
            int len = r.nextInt(MAX_LEN - MIN_LEN) + MIN_LEN;
            String name = keyList.get(r.nextInt(keysize));

            while(name.length() < MAX_LEN){
                String key = "" + name.substring(name.length() - 2, name.length());

                String chatList = followers.get(key);
                if(chatList == null){
                    name += " ";
                    name += keyList.get(r.nextInt(keysize));
                } else {
                    name += chatList.charAt(r.nextInt(chatList.length()));
                }
            }
            ngCount.inc();
            retList.add(name);
        }

        return new ArrayList(retList);
    }
}
