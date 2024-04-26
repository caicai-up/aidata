
package com.weng.aidata.jsondumpreader.reader;
import com.weng.aidata.jsondumpreader.DumpReaderFactory;
import org.apache.commons.compress.compressors.CompressorException;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class CompressedDumpIterator<T> extends UncompressedDumpIterator<T> {

    public CompressedDumpIterator(String pathToFile, Class<T> typeParameterClass) throws FileNotFoundException, CompressorException {
        dumpReader = DumpReaderFactory.getCompressedDumpReader(pathToFile);
        this.typeParameterClass = typeParameterClass;
        init();
    }

    public CompressedDumpIterator(InputStream is, Class<T> typeParameterClass) throws CompressorException {
        dumpReader = DumpReaderFactory.getCompressedDumpReader(is);
        this.typeParameterClass = typeParameterClass;
        init();
    }
}
