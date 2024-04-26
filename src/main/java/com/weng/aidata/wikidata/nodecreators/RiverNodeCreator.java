package com.weng.aidata.wikidata.nodecreators;
import com.weng.aidata.helper.EntityHelper;
import com.weng.aidata.helper.InstanceOf;
import com.weng.aidata.helper.LinkHelper;
import com.weng.aidata.wikidata.WikiDataEntry;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;

public class RiverNodeCreator extends SimpleNameAndIdNodeCreator implements NodeCreator {

    public RiverNodeCreator() {
        super(InstanceOf.RIVER.getIntName());
    }

    public void createNode(WikiDataEntry entry, Session session) {
        super.createNode(entry, session);
        if (LinkHelper.hasProperty(entry, Properties.LENGTH)) {
            String value = EntityHelper.getQuantity(entry, Properties.LENGTH);
            if (value != null) {
                addProperty(entry, "length", value, session);
            }
        }
        if (LinkHelper.hasProperty(entry, Properties.DISCHARGE)) {
            String value = EntityHelper.getQuantity(entry, Properties.DISCHARGE);
            if (value != null) {
                addProperty(entry, "discharge", value, session);
            }
        }
        LinkHelper.linkNodes(entry, Properties.CONTINENT, label, InstanceOf.CONTINENT.getIntName(), "LOCATED_ON", LinkHelper.LinkDirection.RIGHT, session);
        LinkHelper.linkNodes(entry, Properties.COUNTRY, label, InstanceOf.COUNTRY.getIntName(), "LOCATED_IN", LinkHelper.LinkDirection.RIGHT, session);

    }
}
