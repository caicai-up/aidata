package com.weng.aidata.wikidata.nodecreators;

import com.weng.aidata.helper.EntityHelper;
import com.weng.aidata.helper.InstanceOf;
import com.weng.aidata.helper.LinkHelper;
import com.weng.aidata.wikidata.WikiDataEntry;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;

public class SkyscraperNodeCreator extends SimpleNameAndIdNodeCreator implements NodeCreator {

    public SkyscraperNodeCreator() {
        super(InstanceOf.SKYSCRAPER.getIntName());
    }

    public void createNode(WikiDataEntry entry, Session session) {
        super.createNode(entry, session);
        LinkHelper.linkNodes(entry, Properties.STRUCTURAL_ENGINEER, label, InstanceOf.HUMAN.getIntName(), "ENGINEERED_BY", LinkHelper.LinkDirection.RIGHT, session);
        LinkHelper.linkNodes(entry, Properties.COUNTRY, label, InstanceOf.COUNTRY.getIntName(), "LOCATED_AT", LinkHelper.LinkDirection.RIGHT, session);
        LinkHelper.linkNodes(entry, Properties.LOCATION, label, InstanceOf.CITY.getIntName(), "LOCATED_AT", LinkHelper.LinkDirection.RIGHT, session);
        LinkHelper.linkNodes(entry, Properties.ARCHITECTURAL_STYLE, label, InstanceOf.ARCHITECTURAL_STYLE.getIntName(), "BELONGS_TO", LinkHelper.LinkDirection.RIGHT, session);
        LinkHelper.linkNodes(entry, Properties.ARCHITECT, label, InstanceOf.HUMAN.getIntName(), "PLANNED_BY", LinkHelper.LinkDirection.RIGHT, session);

        if (LinkHelper.hasProperty(entry, Properties.DATE_OF_OFFICIAL_OPENING)) {
            String value = EntityHelper.getDate(entry, Properties.DATE_OF_OFFICIAL_OPENING);
            if (value != null) {
                addProperty(entry, "date_of_official_opening", value, session);
            }
        }
        if (LinkHelper.hasProperty(entry, Properties.FLOORS_ABOVE_GROUND)) {
            String value = EntityHelper.getDate(entry, Properties.FLOORS_ABOVE_GROUND);
            if (value != null) {
                addProperty(entry, "floors_above_ground", value, session);
            }
        }
        if (LinkHelper.hasProperty(entry, Properties.NUMBER_OF_ELEVATORS)) {
            String value = EntityHelper.getDate(entry, Properties.NUMBER_OF_ELEVATORS);
            if (value != null) {
                addProperty(entry, "number_of_elevators", value, session);
            }
        }

    }
}
