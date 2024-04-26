package com.weng.aidata.wikidata.nodecreators;
import com.weng.aidata.helper.EntityHelper;
import com.weng.aidata.helper.InstanceOf;
import com.weng.aidata.helper.LinkHelper;
import com.weng.aidata.wikidata.WikiDataEntry;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;


public class MountainNodeCreator extends SimpleNameAndIdNodeCreator implements NodeCreator {

    public MountainNodeCreator() {
        super(InstanceOf.MOUNTAIN.getIntName());
    }

    public void createNode(WikiDataEntry entry, Session session) {
        super.createNode(entry, session);
        if (LinkHelper.hasProperty(entry, Properties.ELEVATION_ABOVE_SEA_LEVEL)) {
            String value = EntityHelper.getQuantity(entry, Properties.ELEVATION_ABOVE_SEA_LEVEL);
            if (value != null) {
                addProperty(entry, "elevation_above_sea_level", value, session);
            }
        }
        LinkHelper.linkNodes(entry, Properties.CONTINENT, label, InstanceOf.CONTINENT.getIntName(), "LOCATED_ON", LinkHelper.LinkDirection.RIGHT, session);
        LinkHelper.linkNodes(entry, Properties.COUNTRY, label, InstanceOf.COUNTRY.getIntName(), "LOCATED_IN", LinkHelper.LinkDirection.RIGHT, session);
    }
}
