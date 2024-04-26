package com.weng.aidata.jsondumpreader.reader;

import com.google.gson.Gson;
import com.weng.aidata.jsondumpreader.DumpReader;
import com.weng.aidata.jsondumpreader.DumpReaderFactory;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;

public class UncompressedDumpIterator<T> implements Iterator<T> {

    DumpReader dumpReader;
    private Gson gson = new Gson();
    private T nextObject = null;
    Class<T> typeParameterClass;

    protected UncompressedDumpIterator() {}

    public UncompressedDumpIterator(String pathToFile, Class<T> typeParameterClass) throws FileNotFoundException {
        dumpReader = DumpReaderFactory.getUncompressedDumpReader(pathToFile);
        this.typeParameterClass = typeParameterClass;
        init();
    }

    public UncompressedDumpIterator(InputStream is, Class<T> typeParameterClass) {
        dumpReader = DumpReaderFactory.getUncompressedDumpReader(is);
        this.typeParameterClass = typeParameterClass;
        init();
    }

    void init() {
        dumpReader.nextJsonLine(); // skip first line
        readEntity();
    }

    private void readEntity() {
        String jsonString = dumpReader.nextJsonLine();
        if (jsonString.endsWith(",")) {
            jsonString = jsonString.substring(0, jsonString.length() - 1);
        }
        if (jsonString == null || jsonString.isEmpty() || jsonString.equals("]")) {
            nextObject = null;
        } else {
            nextObject = gson.fromJson(jsonString, typeParameterClass);
        }
    }

    public boolean hasNext() {
        return nextObject != null;
    }

    public T next() {
        T result = nextObject;
        readEntity();
        return result;
    }

    public void remove() {
        throw new UnsupportedOperationException("remove");
    }
}
