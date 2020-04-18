package io.naimi.covid19.Services;

import io.naimi.covid19.DAO.StatusRepository;
import io.naimi.covid19.Entities.Status;
import net.minidev.json.JSONUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSOutput;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@Transactional
public class DataService implements IDataService {

    @Autowired
    private StatusRepository statusRepository;

    @Override

    public Document getDocument() throws IOException {
        Document document = Jsoup.connect("http://www.covidmaroc.ma/Pages/AccueilAR.aspx").get();
        return document;
    }

    @Override

    public Elements getTables(Document document) {
        Elements numbers = document.getElementsByTag("table");
        return numbers;
    }

    @Override

    public void getTableNumbers(Elements tables) {
        Element tableNumbers = tables.get(0);
        Elements trs = tableNumbers.getElementsByTag("tr");
        Elements numbers_total_tds = trs.get(1).getElementsByTag("td");
        String Last_Updated = trs.get(0).getElementsByTag("p").text();
        String Infected = numbers_total_tds.get(1).getElementsByTag("p").text();
        String Negative = numbers_total_tds.get(2).getElementsByTag("p").text();
        String Recovered = numbers_total_tds.get(0).getElementsByTag("span").get(0).text();
        String Deaths = numbers_total_tds.get(0).getElementsByTag("span").get(1).text();
        Status status = new Status(null, Last_Updated, Long.parseLong(Infected), Long.parseLong(Deaths), Long.parseLong(Recovered), Long.parseLong(Negative));
        if (!statusRepository.existsStatusByLastUpdated(Last_Updated)) {
            statusRepository.save(status);
        } else {
            System.out.println("already saved!");
        }
    }

}
