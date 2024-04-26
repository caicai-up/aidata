
package com.weng.aidata.wikidata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Label {

    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("value")
    @Expose
    private String value;

    /**
     * @return The language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language The language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return The value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value The value
     */
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Label{" +
                "language='" + language + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
