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
        DblpScraper dblpScraper = new DblpScraper();
        GoogleScraper googleScraper = new GoogleScraper();

        String link = dblpScraper.FindUrlToPdf(searchText);
        if(link != null){
            url = dblpScraper.FindDownloadPdfLink(link);
        } else{
            link = googleScraper.FindUrlToPdf(searchText);
            if(link != null){
                url = googleScraper.FindDownloadPdfLink(searchText);
            }
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
