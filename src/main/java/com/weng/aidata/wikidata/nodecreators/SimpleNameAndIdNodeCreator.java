package com.weng.aidata.wikidata.nodecreators;

import com.weng.aidata.helper.EntityHelper;
import com.weng.aidata.helper.ValueHelper;
import com.weng.aidata.wikidata.WikiDataEntry;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;

public class SimpleNameAndIdNodeCreator implements NodeCreator {
    protected String label;

    public SimpleNameAndIdNodeCreator(String label) {
        this.label = label;
    }

    public void createNode(WikiDataEntry entry, Session session) {
        if (EntityHelper.getLabel(entry) == null) return;
        String name = EntityHelper.getLabel(entry).getValue();

        session.run("MERGE (c:" + label + " {name:" + ValueHelper.escape(name) + ", " +
                "id:" + ValueHelper.escape(entry.getId()) + "}) RETURN c");
    }

    public void addProperty(WikiDataEntry entry, String propName, String propValue, Session session) {
        if (EntityHelper.getLabel(entry) == null) return;
        String name = EntityHelper.getLabel(entry).getValue();

        session.run("MATCH (c:" + label + " {name:" + ValueHelper.escape(name) + ", " +
                "id:" + ValueHelper.escape(entry.getId()) + "}) SET c." + propName + " = " + ValueHelper.escape(propValue) + " RETURN c");
    }
}
