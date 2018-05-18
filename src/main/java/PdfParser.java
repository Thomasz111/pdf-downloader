import java.io.*;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;



public class PdfParser {

    public static void parseToTxt(InputStream inputstream, String txtName){
        OutputStream out =null;
        try {
            out= new FileOutputStream(txtName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BodyContentHandler handler = new BodyContentHandler(out);
        Metadata metadata = new Metadata();
        ParseContext pcontext = new ParseContext();

        //parsing the document using PDF parser
        PDFParser pdfparser = new PDFParser();
        try {
            pdfparser.parse(inputstream, handler, metadata, pcontext);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TikaException e) {
            e.printStackTrace();
        }

        try {
            inputstream.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
