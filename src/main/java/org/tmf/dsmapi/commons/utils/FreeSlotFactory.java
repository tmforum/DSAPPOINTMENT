/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tmf.dsmapi.commons.utils;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import org.tmf.dsmapi.appointment.model.FreeSlot;
import org.tmf.dsmapi.appointment.model.RelatedObject;
import org.tmf.dsmapi.appointment.model.RelatedPartyRef;

/**
 *
 * @author ECUS6396
 */
public class FreeSlotFactory {
    
    public static List<FreeSlot> createListeFreeSlot(){
        List<FreeSlot> freeSlots = new ArrayList<FreeSlot>();
        FreeSlot freeSlot = new FreeSlot();
        GregorianCalendar gc = new GregorianCalendar(2015,9,1,14,00);
        freeSlot.setStartDate(gc.getTime());
        gc.add(GregorianCalendar.HOUR_OF_DAY, 2);
        freeSlot.setEndDate(gc.getTime());
        List<RelatedPartyRef> relatedParties = new ArrayList<RelatedPartyRef>();
        RelatedPartyRef relatedParty = new RelatedPartyRef();
        relatedParty.setId("32");
        relatedParty.setHref("http://xxxx/organisation/32");
        relatedParties.add(relatedParty);
        freeSlot.setRelatedParty(relatedParties);
        freeSlots.add(freeSlot);
        
        freeSlot = new FreeSlot();
        gc = new GregorianCalendar(2015,9,1,16,00);
        freeSlot.setStartDate(gc.getTime());
        gc.add(GregorianCalendar.HOUR_OF_DAY, 2);
        freeSlot.setEndDate(gc.getTime());
        freeSlot.setRelatedParty(relatedParties);
        freeSlots.add(freeSlot);
        
        freeSlot = new FreeSlot();
        gc = new GregorianCalendar(2015,9,3,14,00);
        freeSlot.setStartDate(gc.getTime());
        gc.add(GregorianCalendar.HOUR_OF_DAY, 2);
        freeSlot.setEndDate(gc.getTime());
        freeSlot.setRelatedParty(relatedParties);
        freeSlots.add(freeSlot);
        
        freeSlot = new FreeSlot();
        gc = new GregorianCalendar(2015,9,3,16,00);
        freeSlot.setStartDate(gc.getTime());
        gc.add(GregorianCalendar.HOUR_OF_DAY, 2);
        freeSlot.setRelatedParty(relatedParties);
        freeSlots.add(freeSlot);
        
        return freeSlots;
    }
}
