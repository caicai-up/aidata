package com.weng.aidata.wikidata.nodecreators;
import com.weng.aidata.wikidata.WikiDataEntry;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;

public interface NodeCreator {
    void createNode(WikiDataEntry entry, Session session);
}
