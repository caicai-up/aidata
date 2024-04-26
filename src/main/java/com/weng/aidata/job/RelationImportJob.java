package com.weng.aidata.job;

import com.google.gson.Gson;
import com.weng.aidata.entity.relation.IProperty;
import com.weng.aidata.entity.relation.Item;
import com.weng.aidata.entity.relation.PProperty;
import com.weng.aidata.entity.relation.Property;
import com.weng.aidata.helper.InstanceOf;
import com.weng.aidata.helper.LinkHelper;
import com.weng.aidata.helper.ZipUtil;
import com.weng.aidata.service.IPropertyService;
import com.weng.aidata.service.ItemService;
import com.weng.aidata.service.PPropertyService;
import com.weng.aidata.service.PropertyService;
import com.weng.aidata.wikidata.*;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RelationImportJob {

    @Resource
    private ItemService itemService;
    @Resource
    private IPropertyService iPropertyService;

    @Resource
    private PropertyService propertyService;
    @Resource
    private PPropertyService pPropertyService;

    public void handlerData(Path x) throws IOException {
        int itemCount = 0;
        int propertyCount = 0;
        BufferedReader reader = new BufferedReader(new FileReader(x.toString()));
        String line = null;
        handlerMysql(x, reader, itemCount, propertyCount);
        handlerNeo4j(x, reader);
    }

    private void handlerNeo4j(Path x, BufferedReader reader) throws IOException {
        final String dbUri = "neo4j://node001:7687";
        final String dbUser = "neo4j";
        final String dbPassword = "root";

        Driver driver = GraphDatabase.driver(dbUri, AuthTokens.basic(dbUser, dbPassword));

        NodeCreatorFactory nodeCreatorFactory = new NodeCreatorFactory();
        String line;
        while ((line = reader.readLine()) != null) {
            String unzipped = ZipUtil.unzipString(line);
            WikiDataEntry wikiDataEntry = new Gson().fromJson(unzipped, WikiDataEntry.class);
            InstanceOf instanceOf = LinkHelper.getInstanceOf(wikiDataEntry);
            if (instanceOf != null) {
                Session session = driver.session();
                nodeCreatorFactory.createNode(wikiDataEntry, session, instanceOf);
                session.close();
            }
        }
        driver.close();
    }

    private void handlerMysql(Path x, BufferedReader reader, int itemCount, int propertyCount) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            String unzipped = ZipUtil.unzipString(line);
            WikiDataEntry wikiDataEntry = new Gson().fromJson(unzipped, WikiDataEntry.class);
            String id = wikiDataEntry.getId();
            Item item = new Item();
            Property property = new Property();
            if (id.contains("Q")) {
                itemCount++;
                item.setId(id);
                Label label = wikiDataEntry.getLabels().get("zh-ch");
                List<Alias> alias = wikiDataEntry.getAliases().get("zh-ch");
                Description description = wikiDataEntry.getDescriptions().get("zh-ch");
                item.setLabels(label == null ? "" : label.getValue());
                item.setAliases(alias == null ? "" : alias.get(0).getValue());
                item.setDescription(description == null ? "" : description.getValue());
                itemService.save(item);
                Map<String, List<Claim>> claims = wikiDataEntry.getClaims();
                for (Map.Entry<String, List<Claim>> entry : claims.entrySet()) {
                    String claimId = entry.getKey();
                    List<Claim> claimList = entry.getValue();
                    for (Claim claim : claimList) {
                        IProperty iProperty = new IProperty();
                        Mainsnak mainsnak = claim.getMainsnak();
                        iProperty.setPropertyId(claimId);
                        iProperty.setItemId(item.getId());
                        iProperty.setDataType(mainsnak.getDatatype());
                        Datavalue datavalue = mainsnak.getDatavalue();
                        if (mainsnak.getDatatype().equals("wikibase-item")) {
                            if (datavalue != null ) {
                                HashMap hashMap = new Gson().fromJson(datavalue.getValue().toString(), HashMap.class);
                                Object id1 = hashMap.get("id");
                                iProperty.setDataValue(id1.toString());
                            } else {
                                iProperty.setDataValue("");
                            }
                        } else {
                            iProperty.setDataValue(datavalue == null ? "" : datavalue.getValue().toString());
                        }
                        iPropertyService.save(iProperty);
                    }
                }
            } else if (id.contains("P")) {
                propertyCount++;
                property.setId(id);
                Label label = wikiDataEntry.getLabels().get("zh-ch");
                List<Alias> alias = wikiDataEntry.getAliases().get("zh-ch");
                Description description = wikiDataEntry.getDescriptions().get("zh-ch");
                property.setLabels(label == null ? "" : label.getValue());
                property.setAliases(alias == null ? "" : alias.get(0).getValue());
                property.setDescription(description == null ? "" : description.getValue());
                propertyService.save(property);
                Map<String, List<Claim>> claims = wikiDataEntry.getClaims();
                for (Map.Entry<String, List<Claim>> entry : claims.entrySet()) {
                    String claimId = entry.getKey();
                    List<Claim> claimList = entry.getValue();
                    for (Claim claim : claimList) {
                        PProperty pProperty = new PProperty();
                        Mainsnak mainsnak = claim.getMainsnak();
                        pProperty.setPropertyId(claimId);
                        pProperty.setBelongPropertyId(property.getId());
                        pProperty.setDataType(mainsnak.getDatatype());
                        Datavalue datavalue = mainsnak.getDatavalue();

                        if (mainsnak.getDatatype().equals("wikibase-item")) {
                            if (datavalue != null ) {
                                HashMap hashMap = new Gson().fromJson(datavalue.getValue().toString(), HashMap.class);
                                Object id1 = hashMap.get("id");
                                pProperty.setDataValue(id1.toString());
                            } else {
                                pProperty.setDataValue("");
                            }
                        } else {
                            pProperty.setDataValue(datavalue == null ? "" : datavalue.getValue().toString());
                        }

                        pPropertyService.save(pProperty);
                    }
                }
            }
        }
        System.out.println(x + "---处理完毕！" + "已处理Item条数：" + itemCount + "----已处理Property条数：" + propertyCount);
    }
}
