package com.weng.aidata.jsondumpreader.reader;
import com.weng.aidata.jsondumpreader.DumpReader;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class CompressedDumpReader extends UncompressedDumpReader implements DumpReader {

    public CompressedDumpReader(String pathToFile) throws FileNotFoundException, CompressorException {
        this(new FileInputStream(pathToFile));
    }

    public CompressedDumpReader(InputStream is) throws CompressorException {
        super(new CompressorStreamFactory().createCompressorInputStream(new BufferedInputStream(is)));
    }
}
