package com.weng.aidata.helper;

import com.weng.aidata.wikidata.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EntityHelper {
    private static final String[] languagesToImport = {"zh-ch"};

    public static Label getLabel(WikiDataEntry entry) {
        for (String language : languagesToImport) {
            if (entry.getLabels().containsKey(language)) return entry.getLabels().get(language);
        }
        return null;
    }

    public static Description getDescription(WikiDataEntry entry) {
        for (String language : languagesToImport) {
            if (entry.getDescriptions().containsKey(language)) return entry.getDescriptions().get(language);
        }
        return null;
    }

    public static List<Alias> getAlias(WikiDataEntry entry) {
        for (String language : languagesToImport) {
            if (entry.getAliases().containsKey(language)) return entry.getAliases().get(language);
        }
        return null;
    }

    public static SiteLink getSiteLink(WikiDataEntry entry) {
        if (entry.getSitelinks().containsKey("zhwiki")) {
            return entry.getSitelinks().get("zhwiki");
        }
        return null;
    }


    public static String getDate(WikiDataEntry entry, String property) {
        List<Claim> claimList = entry.getClaims().get(property);
        String result = null;
        try {
            if (claimList != null && !claimList.isEmpty()) {
                Claim claim = claimList.get(0);
                if (claim.getMainsnak().getDatatype().equals("time")) {
                    if (claim.getMainsnak().getDatavalue() == null) return null;
                    Map<String, String> valueMap = (Map) claim.getMainsnak().getDatavalue().getValue();
                    result = ValueHelper.formatDate(valueMap.get("time"));
                }
            }
        } catch (NullPointerException npe) {
            npe.printStackTrace();
            System.out.println("npe on : " + claimList.get(0).getMainsnak());
            System.out.println("entity : " + entry);
        }
        return result;
    }

    private static final String Q712226 = "km²";

    public static String getQuantity(WikiDataEntry entry, String property) {
        List<Claim> claimList = entry.getClaims().get(property);
        String result = null;
        try {
            if (claimList != null && !claimList.isEmpty()) {
                Claim claim = claimList.get(0);
                if (claim.getMainsnak().getDatatype().equals("quantity")) {
                    if (claim.getMainsnak().getDatavalue() == null) return null;
                    Map<String, String> valueMap = (Map) claim.getMainsnak().getDatavalue().getValue();
                    result = valueMap.get("amount");
                    result += UnitConverter.convert(valueMap.get("unit"));
                }
            }
        } catch (NullPointerException npe) {
            npe.printStackTrace();
            System.out.println("npe on : " + claimList.get(0).getMainsnak());
            System.out.println("entity : " + entry);
        }
        return result;
    }

    public static Set<String> getLinkedIds(WikiDataEntry entry, String property) {
        Set<String> result = new HashSet<String>();
        try {
            for (Claim claim : entry.getClaims().get(property)) {
                if ("wikibase-item".equals(claim.getMainsnak().getDatatype())) {
                    Object o = claim.getMainsnak().getDatavalue().getValue();
                    if (o instanceof Map) {
                        Map<String, Object> valueMap = (Map) o;
                        if (valueMap.containsKey("entity-type")) {
                            if (((String) valueMap.get("entity-type")).equals("item")) {
                                String id = ((Double) valueMap.get("numeric-id")).toString();
                                if (id != null) {
                                    if (id.contains(".")) {
                                        result.add("Q" + id.split("\\.")[0]);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (NullPointerException npe) {

        }
        return result;
    }

    public static String getString(WikiDataEntry entry, String property) {
        try {
            for (Claim claim : entry.getClaims().get(property)) {
                if ("string".equals(claim.getMainsnak().getDatatype())) {
                    return (String) claim.getMainsnak().getDatavalue().getValue();
                }
            }
        } catch (NullPointerException npe) {

        }
        return null;
    }
}
