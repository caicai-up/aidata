package com.weng.aidata.wikidata;

import com.google.gson.annotations.Expose;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

@Generated("org.jsonschema2pojo")
public class Snaks {

    @Expose
    private List<Snak> snak = new ArrayList<Snak>();

    /**
     * 
     * @return
     *     The p143
     */
    public List<Snak> getSnak() {
        return snak;
    }

    /**
     * 
     * @param snak
     *     The P143
     */
    public void setSnak(List<Snak> snak) {
        this.snak = snak;
    }

}
