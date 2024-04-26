package com.weng.aidata.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSplitter {

    public static void main(String[] args) {
        // 输入文件的路径
        Path inputFilePath = Paths.get("D:\\data\\data.txt");
        // 输出文件的前缀
        String outputPrefix = "split_file_";

        try {
            splitFileIntoMultipleFiles(inputFilePath, outputPrefix);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void splitFileIntoMultipleFiles(Path inputFilePath, String outputPrefix) throws IOException {
        long totalLines = Files.lines(inputFilePath).count();
        long linesPerFileExceptLast = totalLines / 10; // 每份文件的行数（除了可能最后一份）
        long remainingLines = totalLines % 10; // 剩余的行数，用于最后一份文件

        try (BufferedReader reader = Files.newBufferedReader(inputFilePath, StandardCharsets.UTF_8)) {
            String line;
            int fileIndex = 1;
            long linesWritten = 0;
            BufferedWriter writer = null;
            Path outputFile = Paths.get(outputPrefix + "_" + fileIndex + ".txt");

            while ((line = reader.readLine()) != null) {
                if (writer == null || linesWritten == linesPerFileExceptLast) {
                    // 如果writer为null或者当前文件行数达到除了最后一份外的每份文件的行数，则创建新文件
                    if (writer != null) {
                        writer.close(); // 关闭当前writer
                    }
                    fileIndex++;
                    outputFile = Paths.get(outputPrefix + "_" + fileIndex + ".txt");
                    writer = Files.newBufferedWriter(outputFile, StandardCharsets.UTF_8);
                }
                writer.write(line);
                writer.newLine(); // 写入换行符
                linesWritten++;

                // 如果是最后一份文件且还有剩余行数，特殊处理
                if (fileIndex == 10 && remainingLines > 0 && linesWritten == linesPerFileExceptLast + 1) {
                    writer.close(); // 关闭当前writer，准备创建最后一个文件
                    outputFile = Paths.get(outputPrefix + "_" + fileIndex + ".txt");
                    writer = Files.newBufferedWriter(outputFile, StandardCharsets.UTF_8);
                }
            }

            // 写入剩余的行到最后一个文件（如果有的话）
            if (writer != null && remainingLines > 0) {
                while (remainingLines > 0 && (line = reader.readLine()) != null) {
                    writer.write(line);
                    writer.newLine();
                    remainingLines--;
                }
            }

            if (writer != null) {
                writer.close(); // 关闭最后一个writer
            }
        }
    }
}