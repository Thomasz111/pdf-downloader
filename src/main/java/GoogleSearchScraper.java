import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

public class GoogleSearchScraper {

    public static String getFirstGoogleSearch(String searchText) {
        String encoding = "UTF-8";
        String url = null;
        try {
            Elements webSitesLinks = Jsoup.connect("https://google.com/search?q=filetype%3Apdf+" + URLEncoder.encode(searchText, encoding)).userAgent("Mozilla/5.0").get().select(".g>.r>a");;

            //Check if any results found
            if (webSitesLinks.isEmpty()) { return null; }

            url = webSitesLinks.get(0).absUrl("href");
            try {
                url = URLDecoder.decode(url.substring(url.indexOf('=') +
                        1, url.indexOf('&')), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            if (!url.startsWith("http")) { url = null; }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return url;
    }
}
