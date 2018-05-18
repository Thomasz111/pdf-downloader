import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        String searchText = "Multiwinner Voting: A New Challenge for Social Choice Theory";
        String filename = searchText.replaceAll("[ :/*?|\"<>.]", "_");

        String url = GoogleSearchScraper.getFirstGoogleSearch(searchText);

        InputStream in = PdfDownloader.getPdfStream(url);
        PdfDownloader.downloadPdf(in, filename+".pdf");

        in = PdfDownloader.getPdfStream(url);
        PdfParser.parseToTxt(in,filename+".txt");

    }
}
