package com.weng.aidata.helper;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;

public class NodeHelper {
    public static boolean nodeExists(String node, String id, Session session) {
//        StatementResult result = session.run("MATCH (a:" + node + ") WHERE a.id = '" + id + "' RETURN a.name AS name");

//        return result.hasNext();
        return false;
    }
}
