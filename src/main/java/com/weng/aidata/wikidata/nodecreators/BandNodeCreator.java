package com.weng.aidata.wikidata.nodecreators;
import com.weng.aidata.helper.EntityHelper;
import com.weng.aidata.helper.InstanceOf;
import com.weng.aidata.helper.LinkHelper;
import com.weng.aidata.wikidata.WikiDataEntry;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;

public class BandNodeCreator extends SimpleNameAndIdNodeCreator implements NodeCreator {

    public BandNodeCreator() {
        super(InstanceOf.MUSIC_BAND.getIntName());
    }

    public void createNode(WikiDataEntry entry, Session session) {
        super.createNode(entry,session);
        LinkHelper.linkNodes(entry, Properties.HAS_PART, label, InstanceOf.HUMAN.getIntName(), "HAS_MEMBER", LinkHelper.LinkDirection.RIGHT, session);
        LinkHelper.linkNodes(entry, Properties.HAS_PART, label, InstanceOf.HUMAN.getIntName(), "IS_MEMBER", LinkHelper.LinkDirection.LEFT, session);
        LinkHelper.linkNodes(entry, Properties.GENRE, label, InstanceOf.MUSIC_GENRE.getIntName(), "BELONGS_TO", LinkHelper.LinkDirection.RIGHT, session);
        LinkHelper.linkNodes(entry, Properties.COUNTRY_OF_ORIGIN, label, InstanceOf.COUNTRY.getIntName(), "ORIGINATES_IN", LinkHelper.LinkDirection.RIGHT, session);
        if (LinkHelper.hasProperty(entry, Properties.INCEPTION)) {
            String value = EntityHelper.getDate(entry, Properties.INCEPTION);
            if (value != null) {
                addProperty(entry, "founded", value, session);
            }
        }

    }
}
