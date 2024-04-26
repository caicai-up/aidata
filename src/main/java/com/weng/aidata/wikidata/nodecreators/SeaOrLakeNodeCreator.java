package com.weng.aidata.wikidata.nodecreators;
import com.weng.aidata.helper.EntityHelper;
import com.weng.aidata.helper.InstanceOf;
import com.weng.aidata.helper.LinkHelper;
import com.weng.aidata.wikidata.WikiDataEntry;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;

public class SeaOrLakeNodeCreator extends SimpleNameAndIdNodeCreator implements NodeCreator {

    public SeaOrLakeNodeCreator(InstanceOf instanceOf) {
        super(instanceOf.getIntName());
    }

    public void createNode(WikiDataEntry entry, Session session) {
        super.createNode(entry, session);
        LinkHelper.linkNodes(entry, Properties.BASIN_COUNTRY, label, InstanceOf.COUNTRY.getIntName(), "BASIN_COUNTRY", LinkHelper.LinkDirection.RIGHT, session);
        if (LinkHelper.hasProperty(entry, Properties.LENGTH)) {
            String value = EntityHelper.getQuantity(entry, Properties.LENGTH);
            if (value != null) {
                addProperty(entry, "length", value, session);
            }
        }
        if (LinkHelper.hasProperty(entry, Properties.WIDTH)) {
            String value = EntityHelper.getQuantity(entry, Properties.WIDTH);
            if (value != null) {
                addProperty(entry, "width", value, session);
            }
        }
        if (LinkHelper.hasProperty(entry, Properties.PERIMETER)) {
            String value = EntityHelper.getQuantity(entry, Properties.PERIMETER);
            if (value != null) {
                addProperty(entry, "perimeter", value, session);
            }
        }
        if (LinkHelper.hasProperty(entry, Properties.AREA)) {
            String value = EntityHelper.getQuantity(entry, Properties.AREA);
            if (value != null) {
                addProperty(entry, "area", value, session);
            }
        }
        if (LinkHelper.hasProperty(entry, Properties.VOLUME_AS_QUANTITY)) {
            String value = EntityHelper.getQuantity(entry, Properties.VOLUME_AS_QUANTITY);
            if (value != null) {
                addProperty(entry, "volume", value, session);
            }
        }

    }
}
