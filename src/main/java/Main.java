import Downloader.PdfDownloader;
import Parser.PdfParser;
import Scraper.DblpScraper;
import Scraper.GoogleScraper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
//        String searchText = getSearchText();
        String searchText = "";
        searchText = searchText.isEmpty() ?
                "Multiwinner Voting: A New Challenge for Social Choice Theory" : searchText;
        //"Distance rationalization of voting rules" : searchText;

        String filename = searchText.replaceAll("[ :/*?|\"<>.]", "_");


        String url = null;
        DblpScraper dblpScraper = new DblpScraper();
        GoogleScraper googleScraper = new GoogleScraper();

        String link = dblpScraper.findUrlToPdf(searchText);
        if (link != null) {
            url = dblpScraper.findDownloadPdfLink(link);
        } else {
            link = googleScraper.findUrlToPdf(searchText);
            if (link != null) {
                url = googleScraper.findDownloadPdfLink(link);
            }
        }

        if (url != null) {
            InputStream in = PdfDownloader.getPdfStream(url);
            PdfParser.parseToTxt(in, filename + ".txt");
            in = PdfDownloader.getPdfStream(url);
            PdfDownloader.downloadPdf(in, filename + ".pdf");
        } else {
            System.out.println("PDF not found");
        }

        //hibernate demo
        Author author = new Author("Maciej", "Mizera");
        author.setUniversity("AGH");
        Thesis thesis = new Thesis("Sample Thesis");
        author.addThesis(thesis);

        dbUtil.saveAuthor(author);
        //now we should have author and thesis
        // as well as connection in link table in database
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
