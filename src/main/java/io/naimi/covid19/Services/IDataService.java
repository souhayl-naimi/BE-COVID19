package io.naimi.covid19.Services;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public interface IDataService {
    public Document getDocument() throws IOException;
    public Elements getTables(Document document);
    public void getTableNumbers(Elements tables);
}
