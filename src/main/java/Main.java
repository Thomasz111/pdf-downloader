import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        String searchText = getSearchText();
        searchText = searchText.isEmpty() ?
                "Multiwinner Voting: A New Challenge for Social Choice Theory" : searchText;
                //"Distance rationalization of voting rules" : searchText;

        String filename = searchText.replaceAll("[ :/*?|\"<>.]", "_");


        String url = null;
        String link = HTMLScraper.findLinkToPdfOnDblp(searchText);
        if(link != null){
            url = HTMLScraper.findPdfUrl(link);
        } else{
            url = GoogleSearchScraper.getFirstGoogleSearch(searchText);
        }

        if(url != null){
            InputStream in = PdfDownloader.getPdfStream(url);
            PdfParser.parseToTxt(in, filename + ".txt");
            in = PdfDownloader.getPdfStream(url);
            PdfDownloader.downloadPdf(in, filename + ".pdf");
        } else {
            System.out.println("PDF not found");
        }

    }

    private static String getSearchText() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
