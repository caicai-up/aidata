package com.weng.aidata.job;
import com.weng.aidata.helper.InstanceOf;
import com.weng.aidata.wikidata.WikiDataEntry;
import com.weng.aidata.wikidata.nodecreators.*;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;

import java.util.HashMap;
import java.util.Map;

public class NodeCreatorFactory {
    Map<InstanceOf, NodeCreator> nodeCreatorMap = new HashMap<InstanceOf, NodeCreator>();

    public NodeCreatorFactory() {
        for (InstanceOf instanceOf : InstanceOf.values()) {
            nodeCreatorMap.put(instanceOf, new SimpleNameAndIdNodeCreator(instanceOf.getIntName()));
        }
        nodeCreatorMap.put(InstanceOf.HUMAN, new PersonNodeCreator());
        nodeCreatorMap.put(InstanceOf.FICTIONAL_HUMAN, new FictionalPersonNodeCreator());
        nodeCreatorMap.put(InstanceOf.MUSIC_BAND, new BandNodeCreator());
        nodeCreatorMap.put(InstanceOf.MUSIC_SINGLE, new SingleOrAlbumNodeCreator(InstanceOf.MUSIC_SINGLE.getIntName()));
        nodeCreatorMap.put(InstanceOf.MUSIC_ALBUM, new SingleOrAlbumNodeCreator(InstanceOf.MUSIC_ALBUM.getIntName()));
        nodeCreatorMap.put(InstanceOf.FILM, new FilmNodeCreator());
        nodeCreatorMap.put(InstanceOf.PAINTING, new PaintingNodeCreator());
        nodeCreatorMap.put(InstanceOf.SKYSCRAPER, new SkyscraperNodeCreator());
        nodeCreatorMap.put(InstanceOf.THEATRE_PLAY, new TheatrePlayNodeCreator());
        nodeCreatorMap.put(InstanceOf.COUNTRY, new CountryNodeCreator());
        nodeCreatorMap.put(InstanceOf.RIVER, new RiverNodeCreator());
        nodeCreatorMap.put(InstanceOf.SEA, new SeaOrLakeNodeCreator(InstanceOf.SEA));
        nodeCreatorMap.put(InstanceOf.LAKE, new SeaOrLakeNodeCreator(InstanceOf.LAKE));
        nodeCreatorMap.put(InstanceOf.MOUNTAIN, new MountainNodeCreator());
    }

    public void createNode(WikiDataEntry entry, Session session, InstanceOf instanceOf) {
        NodeCreator nodeCreator = nodeCreatorMap.get(instanceOf);
        if (nodeCreator != null && !entry.getLabels().isEmpty()) {
            nodeCreator.createNode(entry, session);
        } else {
            System.out.println("no creator found for " + instanceOf);
        }
    }
}
