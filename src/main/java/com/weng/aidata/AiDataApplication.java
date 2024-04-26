package com.weng.aidata;

import com.weng.aidata.job.RelationImportJob;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootApplication
public class AiDataApplication implements CommandLineRunner {

    @Resource
    private RelationImportJob relationImportJob;

    public static void main(String[] args) {
        SpringApplication.run(AiDataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        File[] files = new File("D:\\test\\data").listFiles();
        for (File file : files) {
            mysqlImporter(file.toPath());
        }
        System.out.println("线程全部分配完毕");
    }

    private void mysqlImporter(Path x) {
        new Thread(() -> {
            try {
                System.out.println(x + "-----正在处理");
                relationImportJob.handlerData(x);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
