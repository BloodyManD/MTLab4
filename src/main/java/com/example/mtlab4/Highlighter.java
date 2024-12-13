package com.example.mtlab4;

import com.denisfesenko.converter.HtmlToOpenXMLConverter;
import fr.opensagres.poi.xwpf.converter.core.FileURIResolver;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLConverter;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Highlighter {
    public static void main(String[] args) throws IOException {
        highlight("C:/Users/ngavr/IdeaProjects/MTLab4/src/main/WordDocs/test.docx", "C:/Users/ngavr/IdeaProjects/MTLab4/src/main/WordDocs/test2.docx", "ам");
    }
    public static void highlight(String fileIn, String fileOut, String word) throws IOException {
        String text = docxToHTML(fileIn);
        text = text.replaceAll(word, "<span style=\"background-color: #ffff00;\">"+word+"</span>");
        _HTMLToDocx(text, fileOut);
    }
    public static String docxToHTML(String fileIn) throws IOException {
        InputStream is = Files.newInputStream(Paths.get(fileIn));
        XWPFDocument document = new XWPFDocument(is);
        XHTMLOptions options = XHTMLOptions.create().URIResolver(new FileURIResolver(new File("word/media")));

        OutputStream out = new ByteArrayOutputStream();


        XHTMLConverter.getInstance().convert(document, out, options);
        return out.toString();
    }

    public static void _HTMLToDocx(String _HTMLString, String fileOut){
        try{
            HtmlToOpenXMLConverter converter = new HtmlToOpenXMLConverter();
            WordprocessingMLPackage wordDocument = converter.convert(_HTMLString);

            File outputFile = new File(fileOut);
            wordDocument.save(outputFile);
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        } catch (Docx4JException e) {
            throw new RuntimeException(e);
        }
    }
}
