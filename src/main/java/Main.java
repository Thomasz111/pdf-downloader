import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
//        String searchText = getSearchText();
//        searchText = searchText.isEmpty() ?
//                "Multiwinner Voting: A New Challenge for Social Choice Theory" : searchText;
//
//        String filename = searchText.replaceAll("[ :/*?|\"<>.]", "_");
//
//        String url = GoogleSearchScraper.getFirstGoogleSearch(searchText);
//
//        InputStream in = PdfDownloader.getPdfStream(url);
//        PdfParser.parseToTxt(in, filename + ".txt");
//        in = PdfDownloader.getPdfStream(url);
//        PdfDownloader.downloadPdf(in, filename + ".pdf");

        String searchText = "Distance rationalization of voting rules";
        String filename = searchText.replaceAll("[ :/*?|\"<>.]", "_");
        String link = HTMLScraper.findLinkToPdfOnDblp(searchText);
        String url = HTMLScraper.findPdfUrl(link);

        InputStream in = PdfDownloader.getPdfStream(url);
        PdfDownloader.downloadPdf(in, filename + ".pdf");
        in = PdfDownloader.getPdfStream(url);
        PdfParser.parseToTxt(in, filename + ".txt");
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
