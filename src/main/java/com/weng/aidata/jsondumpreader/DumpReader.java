package com.weng.aidata.jsondumpreader;

public interface DumpReader {
    /**
     * Returns the next JSON object (as string) or null if the end of the dump has been reached.
     *
     * @return string or null
     * @throws DumpReadingException
     */
    String nextJsonLine();
    void rewind();
}
