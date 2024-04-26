package com.weng.aidata.wikidata.nodecreators;
import com.weng.aidata.helper.EntityHelper;
import com.weng.aidata.helper.InstanceOf;
import com.weng.aidata.helper.LinkHelper;
import com.weng.aidata.wikidata.WikiDataEntry;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;

public class ChemicalElementNodeCreator extends SimpleNameAndIdNodeCreator implements NodeCreator {

    public ChemicalElementNodeCreator() {
        super(InstanceOf.CHEMICAL_ELEMENT.getIntName());
    }

    public void createNode(WikiDataEntry entry, Session session) {
        super.createNode(entry, session);
        if (LinkHelper.hasProperty(entry, Properties.ELEMENT_SYMBOL)) {
            String value = EntityHelper.getString(entry, Properties.ELEMENT_SYMBOL);
            if (value != null) {
                addProperty(entry, "element_symbol", value, session);
            }
        }
        if (LinkHelper.hasProperty(entry, Properties.ATOMIC_NUMBER)) {
            String value = EntityHelper.getString(entry, Properties.ATOMIC_NUMBER);
            if (value != null) {
                addProperty(entry, "atomic_number", value, session);
            }
        }
        if (LinkHelper.hasProperty(entry, Properties.DENSITY)) {
            String value = EntityHelper.getQuantity(entry, Properties.DENSITY);
            if (value != null) {
                addProperty(entry, "density", value, session);
            }
        }
        if (LinkHelper.hasProperty(entry, Properties.MASS)) {
            String value = EntityHelper.getQuantity(entry, Properties.MASS);
            if (value != null) {
                addProperty(entry, "mass", value, session);
            }
        }
        if (LinkHelper.hasProperty(entry, Properties.TIME_OF_DISCOVERY)) {
            String value = EntityHelper.getDate(entry, Properties.TIME_OF_DISCOVERY);
            if (value != null) {
                addProperty(entry, "time_of_discovery", value, session);
            }
        }
        if (LinkHelper.hasProperty(entry, Properties.MELTING_POINT)) {
            String value = EntityHelper.getQuantity(entry, Properties.MELTING_POINT);
            if (value != null) {
                addProperty(entry, "melting_point", value, session);
            }
        }
        if (LinkHelper.hasProperty(entry, Properties.BOILING_POINT)) {
            String value = EntityHelper.getQuantity(entry, Properties.BOILING_POINT);
            if (value != null) {
                addProperty(entry, "boiling_point", value, session);
            }
        }
        LinkHelper.linkNodes(entry, Properties.DISCOVERER_OR_INVENTOR, label, InstanceOf.HUMAN.getIntName(), "DISCOVERED_BY", LinkHelper.LinkDirection.RIGHT, session);
    }
}
