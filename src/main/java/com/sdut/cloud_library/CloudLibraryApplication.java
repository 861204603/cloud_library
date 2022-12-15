package com.sdut.cloud_library;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@Slf4j
@Transactional
@EnableTransactionManagement
public class CloudLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudLibraryApplication.class, args);
        log.info("启动成功·");
    }

}
