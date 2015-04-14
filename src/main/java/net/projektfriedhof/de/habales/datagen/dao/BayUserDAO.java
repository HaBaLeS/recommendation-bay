package net.projektfriedhof.de.habales.datagen.dao;

import net.projektfriedhof.recbay.model.BayUser;

import java.util.List;

/**
 * Created by falko on 4/11/15.
 */
public interface BayUserDAO<M> {

    /**
     * Batch Persist all the List Entrys
     * @param retList
     */
    void batchPersist(List<BayUser> retList);

    /**
     * List them all .. only id and nick ... only partially useful
     * @return
     */
    List<M> listMinimal();
}
