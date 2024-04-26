package com.weng.aidata.wikidata.nodecreators;
import com.weng.aidata.helper.EntityHelper;
import com.weng.aidata.helper.InstanceOf;
import com.weng.aidata.helper.LinkHelper;
import com.weng.aidata.wikidata.WikiDataEntry;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;

public class SingleOrAlbumNodeCreator extends SimpleNameAndIdNodeCreator implements NodeCreator {

    public SingleOrAlbumNodeCreator(String label) {
        super(label);
    }

    public void createNode(WikiDataEntry entry, Session session) {
        super.createNode(entry, session);
        LinkHelper.linkNodes(entry, Properties.PERFORMER, label, InstanceOf.MUSIC_BAND.getIntName(), "PERFORMED_BY", LinkHelper.LinkDirection.RIGHT, session);
        LinkHelper.linkNodes(entry, Properties.PERFORMER, label, InstanceOf.HUMAN.getIntName(), "PERFORMED_BY", LinkHelper.LinkDirection.RIGHT, session);
        LinkHelper.linkNodes(entry, Properties.GENRE, label, InstanceOf.MUSIC_GENRE.getIntName(), "BELONGS_TO", LinkHelper.LinkDirection.RIGHT, session);

        if (LinkHelper.hasProperty(entry, Properties.PUBLICATION_DATE)) {
            String value = EntityHelper.getDate(entry, Properties.PUBLICATION_DATE);
            if (value != null) {
                addProperty(entry, "publication_date", value, session);
            }
        }

    }
}
