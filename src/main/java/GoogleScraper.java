import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

public class GoogleScraper implements HTMLScraper{

    @Override
    public String findDownloadPdfLink(String url) {
        String absHref = null;
        try {
            Elements webSitesLinks = Jsoup.connect(url).userAgent("Mozilla/5.0").
                    get().select(".g>.r>a");

            //Check if any results found
            if (webSitesLinks.isEmpty()) {
                return null;
            }

            absHref = webSitesLinks.get(0).absUrl("href");
            try {
                absHref = URLDecoder.decode(absHref.substring(absHref.indexOf('=') +
                        1, absHref.indexOf('&')), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            if (!absHref.startsWith("http")) {
                absHref = null;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return absHref;
    }


    @Override
    public String findUrlToPdf(String pdfName) throws IOException {
        String encoding = "UTF-8";
        return "https://google.com/search?q=filetype%3Apdf+"
                + URLEncoder.encode(pdfName, encoding);
    }
}
