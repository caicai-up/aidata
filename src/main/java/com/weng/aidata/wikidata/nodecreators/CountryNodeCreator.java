package com.weng.aidata.wikidata.nodecreators;

import com.weng.aidata.helper.EntityHelper;
import com.weng.aidata.helper.InstanceOf;
import com.weng.aidata.helper.LinkHelper;
import com.weng.aidata.wikidata.WikiDataEntry;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;

public class CountryNodeCreator extends SimpleNameAndIdNodeCreator implements NodeCreator {

    public CountryNodeCreator() {
        super(InstanceOf.COUNTRY.getIntName());
    }

    public void createNode(WikiDataEntry entry, Session session) {
        super.createNode(entry, session);
        if (LinkHelper.hasProperty(entry, Properties.COUNTRY_CALLING_CODE)) {
            String value = EntityHelper.getString(entry, Properties.COUNTRY_CALLING_CODE);
            if (value != null) {
                addProperty(entry, "calling_code", value, session);
            }
        }
        if (LinkHelper.hasProperty(entry, Properties.LICENSE_PLATE)) {
            String value = EntityHelper.getString(entry, Properties.LICENSE_PLATE);
            if (value != null) {
                addProperty(entry, "license_plate", value, session);
            }
        }
        if (LinkHelper.hasProperty(entry, Properties.INCEPTION)) {
            String value = EntityHelper.getDate(entry, Properties.INCEPTION);
            if (value != null) {
                addProperty(entry, "inception", value, session);
            }
        }
        if (LinkHelper.hasProperty(entry, Properties.AREA)) {
            String value = EntityHelper.getQuantity(entry, Properties.AREA);
            if (value != null) {
                addProperty(entry, "area", value, session);
            }
        }
        LinkHelper.linkNodes(entry, Properties.ANTHEM, label, InstanceOf.MUSIC_SONG.getIntName(), "HAS_ANTHEM", LinkHelper.LinkDirection.RIGHT, session);
        LinkHelper.linkNodes(entry, Properties.CURRENCY, label, InstanceOf.CURRENCY.getIntName(), "HAS_CURRENCY", LinkHelper.LinkDirection.RIGHT, session);
        LinkHelper.linkNodes(entry, Properties.SHARES_BORDER_WITH, label, InstanceOf.COUNTRY.getIntName(), "SHARES_BOARDER_WITH", LinkHelper.LinkDirection.BOTH, session);
        LinkHelper.linkNodes(entry, Properties.CONTINENT, label, InstanceOf.CONTINENT.getIntName(), "LOCATED_ON", LinkHelper.LinkDirection.RIGHT, session);
        LinkHelper.linkNodes(entry, Properties.HEAD_OF_STATE, label, "Person", "IS_HEAD_OF_STATE", LinkHelper.LinkDirection.RIGHT, session);
        LinkHelper.linkNodes(entry, Properties.HEAD_OF_GOVERNMENT, label, "Country", "IS_HEAD_OF_GOVERNMENT", LinkHelper.LinkDirection.RIGHT, session);
        LinkHelper.linkNodes(entry, Properties.CAPITAL, label, InstanceOf.CITY.getIntName(), "IS_CAPITAL_OF", LinkHelper.LinkDirection.LEFT, session);
        LinkHelper.linkNodes(entry, Properties.FILMING_LOCATION, label, "City", "WAS_FILMED_AT", LinkHelper.LinkDirection.RIGHT, session);
        LinkHelper.linkNodes(entry, Properties.FILMING_LOCATION, label, "Country", "WAS_FILMED_AT", LinkHelper.LinkDirection.RIGHT, session);

    }
}
