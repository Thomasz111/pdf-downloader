import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URLEncoder;

public class DblpScraper implements HTMLScraper {

    @Override
    public String findUrlToPdf(String pdfName) throws IOException {
        String encoding = "UTF-8";
        Document doc = Jsoup.connect("https://dblp.uni-trier.de/search?q="
                + URLEncoder.encode(pdfName, encoding)).userAgent("Mozilla/5.0").get();

        for (Element element : doc.select("li.entry")){
            String articleName = element.select(" .title").first().text();

            if(simplifyString(articleName).equals(simplifyString(pdfName))){
                Element link = element.select(" .publ .head > a").first();
                String absHref = link.attr("abs:href");
                System.out.println(absHref);
                return absHref;
            }
        }
        return null;
    }

    private static String simplifyString(String string){
        return string.replaceAll("[ :/*?|\"<>.]", "").toLowerCase();
    }
}
