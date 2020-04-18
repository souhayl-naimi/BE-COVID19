package io.naimi.covid19;

import io.naimi.covid19.Services.IDataService;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.util.Date;

@SpringBootApplication
@EnableScheduling
public class Covid19Application implements CommandLineRunner {

    @Autowired
    private IDataService iDataService;

    @Scheduled(cron = "0 * * * * *")
    public void schedule() throws IOException {
        Document document = iDataService.getDocument();
        Elements tables = iDataService.getTables(document);
        iDataService.getTableNumbers(tables);
        System.out.println("Executed At : "+new Date());
    }
    public static void main(String[] args) {
        SpringApplication.run(Covid19Application.class, args);
    }

    @Override

    public void run(String... args) throws Exception {

    }
}
