import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public interface HTMLScraper {

    default String findDownloadPdfLink(String url) throws IOException {
        Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").get();

        for (Element link : doc.select("a[href]")) {
            String absHref = link.attr("abs:href");
            String urlText = link.text();
            if(urlText.toLowerCase().contains("pdf") || absHref.toLowerCase().contains("pdf")){
                System.out.println(urlText + " " + absHref);
                return absHref;
            }
        }
        return null;
    }

    String findUrlToPdf(String pdfName) throws IOException;
}
