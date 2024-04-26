package com.weng.aidata.wikidata.nodecreators;

import com.weng.aidata.helper.EntityHelper;
import com.weng.aidata.helper.InstanceOf;
import com.weng.aidata.helper.LinkHelper;
import com.weng.aidata.wikidata.WikiDataEntry;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;

public class TheatrePlayNodeCreator extends SimpleNameAndIdNodeCreator {
    public TheatrePlayNodeCreator() {
        super(InstanceOf.THEATRE_PLAY.getIntName());
    }

    public void createNode(WikiDataEntry entry, Session session) {
        super.createNode(entry, session);
        LinkHelper.linkNodes(entry, Properties.CHARACTERS, label, InstanceOf.FICTIONAL_HUMAN.getIntName(), "HAS_CHARACTER", LinkHelper.LinkDirection.RIGHT, session);
        LinkHelper.linkNodes(entry, Properties.AUTHOR, label, InstanceOf.HUMAN.getIntName(), "AUTHOR_OF", LinkHelper.LinkDirection.LEFT, session);
        LinkHelper.linkNodes(entry, Properties.GENRE, label, InstanceOf.THEATRICAL_GENRE.getIntName(), "BELONGS_TO", LinkHelper.LinkDirection.RIGHT, session);
        LinkHelper.linkNodes(entry, Properties.ORIGINAL_LANGUAGE_OF_WORK, label, InstanceOf.LANGUAGE.getIntName(), "ORIGINAL_CREATED_IN", LinkHelper.LinkDirection.RIGHT, session);

        if (LinkHelper.hasProperty(entry, Properties.PUBLICATION_DATE)) {
            String value = EntityHelper.getDate(entry, Properties.PUBLICATION_DATE);
            if (value != null) {
                addProperty(entry, "publication_date", value, session);
            }
        }

    }

}
