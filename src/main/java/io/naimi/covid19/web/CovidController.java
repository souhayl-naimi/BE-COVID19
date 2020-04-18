package io.naimi.covid19.web;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class CovidController {
    
    @GetMapping(path = "/regions")
    public List<Region> numbersRegion() throws IOException {
        List<Region> regionsNumbers = new ArrayList<>();

        Document document = Jsoup.connect("http://www.covidmaroc.ma/Pages/AccueilAR.aspx").get();
        Elements numbers = document.getElementsByTag("table");

        Element tableRegions = numbers.get(1);

        Elements numberPerRegion = tableRegions.getElementsByTag("td");
        Elements regions = tableRegions.getElementsByTag("th");

        for (int i = 0; i < 12; i++) {
            Region region = new Region();
            Long number = Long.parseLong(numberPerRegion.get(i).text());
            String name = regions.get(i + 2).text();
            region.setName(name);
            region.setNumCases(number);
            regionsNumbers.add(region);
        }

        return regionsNumbers;
    }

    @GetMapping(path="/population")
    public Pop population() throws IOException {
        Document document = Jsoup.connect("https://applications-web.hcp.ma/hpmc/frmhorlogepop2014.aspx").get();
        Pop pop = new Pop();

        Element popElement = document.getElementById("pop");
        Element tmpElement = document.getElementById("tmp");

        String population=popElement.text().replace(",","");

        String timeStamp=tmpElement.text();
        Long longPopulation = Long.parseLong(population);
        pop.setPopulation(longPopulation);
        pop.setTimestamp(timeStamp);
        return pop;
    }
}
@Data @NoArgsConstructor
class Pop{
private Long population;
private String timestamp;
}

@Data @NoArgsConstructor
class Region{
    private String name;
    private Long numCases;
}
