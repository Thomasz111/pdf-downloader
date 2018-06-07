import java.io.InputStream;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class AGHLibraryScraper {

    private HTTPRequest httpRequest;

    public AGHLibraryScraper() {
        this.httpRequest = new HTTPRequest();
    }

    public ArrayList<String> getListOfPublications(String name, String surname) throws IOException {
        String url = createUrl(name, surname);

        Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").get();
        int pageNumber = getNumberOfPages(doc);

        ArrayList<String> publications = new ArrayList<>();

        for(int i=1; i<=pageNumber; i++){
            InputStream input = httpRequest.getInputStreamFromPostRequest(url, pageUrlParameters(i));
            String pageHTML = httpRequest.getPageContentFromInputStream(input);
            input.close();

            publications.addAll(publicationListFromOnePage(pageHTML));
        }

        return publications;
    }

    private ArrayList<String> publicationListFromOnePage(String pageHTML) {
        Document doc = Jsoup.parse(pageHTML);
        ArrayList<String> publications = new ArrayList<>();
        for(Element e : doc.select(".li-publ .tp1, .tp2, .tp3")){
            publications.add(e.select("em").first().text());
        }
        return publications;
    }

    private String createUrl(String name, String surname){
        return "https://bpp.agh.edu.pl/wyszukiwanie/?fA="+name+"+"+surname;
    }

    private int getNumberOfPages(Document doc){
        Elements e = doc.select(".pagination [title=\"pozycje od-do\"] [type=\"submit\"]");
        if(e.size()==0)
            return 1;
        return e.size()/2;
    }

    private String pageUrlParameters(int pageNumber){
        return "idform=2&vt=p&lastPage="+pageNumber+"&cur_fOrd=srtAA&page%5B"+pageNumber+"%5D="+pageNumber;
    }


}
