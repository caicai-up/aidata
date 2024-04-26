package com.weng.aidata.jsondumpreader;


import com.weng.aidata.jsondumpreader.reader.CompressedDumpReader;
import com.weng.aidata.jsondumpreader.reader.UncompressedDumpReader;
import org.apache.commons.compress.compressors.CompressorException;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class DumpReaderFactory {

    public static DumpReader getUncompressedDumpReader(String pathToFile) throws FileNotFoundException {
        return new UncompressedDumpReader(pathToFile);
    }

    public static DumpReader getCompressedDumpReader(String pathToFile) throws FileNotFoundException, CompressorException {
        return new CompressedDumpReader(pathToFile);
    }

    public static DumpReader getUncompressedDumpReader(InputStream is) {
        return new UncompressedDumpReader(is);
    }

    public static DumpReader getCompressedDumpReader(InputStream is) throws CompressorException {
        return new CompressedDumpReader(is);
    }

}
