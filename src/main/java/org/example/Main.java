package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class Main {
    public static void main(String[] args) throws IOException {
        String topic = "";
        String topicUrl = "";
        String url = "https://vnexpress.net";
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Elements menuBar = doc.select("nav>ul>li");
        Elements topicLinks = menuBar.select("a[href]");
        for (Element link : topicLinks) {
            String folderName = link.attr("title");
            topic = link.attr("title");
            File folder = new File(folderName);
            folder.mkdir();
            topicUrl = link.attr("abs:href");
            System.out.println("TOPIC: "+topicUrl);
            System.out.println("\n");
            Document topicDoc = Jsoup.connect(topicUrl).get();
            Elements newsHeadlines = topicDoc.select("h3.title-news > a");
            for (Element headline : newsHeadlines) {
                url = headline.attr("href");
                doc = Jsoup.connect(url).get();
                String title = doc.select(".title-detail").html();
                String description = doc.select("p.description").text();
                String date = doc.select("span.date").text();
                String author = doc.select("p.author").text();
                String content = doc.select(".Normal").html();
                SaveFile.saveFile(topic, title, content);
            }
        }
    }
}