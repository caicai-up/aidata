package com.weng.aidata.wikidata.nodecreators;
import com.weng.aidata.helper.EntityHelper;
import com.weng.aidata.helper.InstanceOf;
import com.weng.aidata.helper.LinkHelper;
import com.weng.aidata.helper.ValueHelper;
import com.weng.aidata.wikidata.WikiDataEntry;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;


public class FictionalPersonNodeCreator implements NodeCreator {

    private final static String label = InstanceOf.FICTIONAL_HUMAN.getIntName();

    public void createNode(WikiDataEntry entry, Session session) {
        String name = EntityHelper.getLabel(entry).getValue();
        if (name == null) return; // do not add non english names
        session.run("MERGE (p:" + label + " {name:" + ValueHelper.escape(name) + ", " +
                "id:" + ValueHelper.escape(entry.getId()) + "}) RETURN p");

        LinkHelper.linkNodes(entry, Properties.GENDER, label, "Gender", "HAS_GENDER", LinkHelper.LinkDirection.RIGHT, session);
        LinkHelper.linkNodes(entry, Properties.FATHER, label, label, "IS_CHILD", LinkHelper.LinkDirection.RIGHT, session);
        LinkHelper.linkNodes(entry, Properties.MOTHER, label, label, "IS_CHILD", LinkHelper.LinkDirection.RIGHT, session);
        LinkHelper.linkNodes(entry, Properties.CHILD, label, label, "HAS_CHILD", LinkHelper.LinkDirection.RIGHT, session);
        LinkHelper.linkNodes(entry, Properties.SISTER, label, label, "HAS_SISTER", LinkHelper.LinkDirection.RIGHT, session);
        LinkHelper.linkNodes(entry, Properties.BROTHER, label, label, "HAS_BROTHER", LinkHelper.LinkDirection.RIGHT, session);
        LinkHelper.linkNodes(entry, Properties.SPOUSE, label, label, "IS_SPOUSE", LinkHelper.LinkDirection.RIGHT, session);
        LinkHelper.linkNodes(entry, Properties.PROFESSION, label, "Profession", "WORKS_AS", LinkHelper.LinkDirection.RIGHT, session);
        LinkHelper.linkNodes(entry, Properties.PLACE_OF_BIRTH, label, "City", "BORN_IN", LinkHelper.LinkDirection.RIGHT, session);
        LinkHelper.linkNodes(entry, Properties.PLACE_OF_BIRTH, label, "Country", "BORN_IN", LinkHelper.LinkDirection.RIGHT, session);
        LinkHelper.linkNodes(entry, Properties.COUNTRY_OF_CITIZENSHIP, label, "Country", "CITIZEN_OF", LinkHelper.LinkDirection.RIGHT, session);
        LinkHelper.linkNodes(entry, Properties.NATIVE_LANGUAGE, label, "Language", "NATIVE_LANGUAGE", LinkHelper.LinkDirection.RIGHT, session);
    }
}
