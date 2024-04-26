package com.weng.aidata.jsondumpreader.reader;

import com.weng.aidata.jsondumpreader.DumpReader;
import com.weng.aidata.jsondumpreader.DumpReadingException;

import java.io.*;

public class UncompressedDumpReader implements DumpReader {
    private final BufferedReader br;

    public UncompressedDumpReader(String pathToFile) throws FileNotFoundException {
        this(new FileInputStream(pathToFile));
    }

    public UncompressedDumpReader(InputStream is) {
        br = new BufferedReader(new InputStreamReader(is));
    }

    public String nextJsonLine() {
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new DumpReadingException(e);
        }
    }

    public void rewind() {
        try {
            br.reset();
        } catch (IOException e) {
            throw new DumpReadingException(e);
        }
    }
}
