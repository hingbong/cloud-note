package io.github.hingbong.cloudnote;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * application startup class
 *
 * @author Hingbong
 */
@SpringBootApplication
@MapperScan("io.github.hingbong.cloudnote.mapper")
public class CloudNoteApplication {

  public static void main(String... args) {
    SpringApplication.run(CloudNoteApplication.class, args);
  }
}
