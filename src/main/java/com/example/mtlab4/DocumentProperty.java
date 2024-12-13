package com.example.mtlab4;

import fr.opensagres.poi.xwpf.converter.core.FileURIResolver;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLConverter;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.ExpandedTitleContentHandler;
import org.apache.tika.sax.ToHTMLContentHandler;
import org.apache.tika.sax.ToXMLContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.tika.parser.AutoDetectParser;

public class DocumentProperty {

    private final String path;

    public DocumentProperty(String path) {
        this.path = path;
    }
    public String getHTML() throws IOException,TransformerConfigurationException, SAXException, TikaException {
        InputStream is = Files.newInputStream(Paths.get(path));
        AutoDetectParser parser = new AutoDetectParser();
        ContentHandler handler = new ToHTMLContentHandler();
        Metadata metadata = new Metadata();
        ParseContext context = new ParseContext();
        context.set(Parser.class, parser);
        parser.parse(is, handler, metadata, context);
        return handler.toString();
    }

    public String getText() throws IOException,TransformerConfigurationException, SAXException, TikaException {
        InputStream is = Files.newInputStream(Paths.get(path));
        AutoDetectParser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        ParseContext context = new ParseContext();
        context.set(Parser.class, parser);
        parser.parse(is, handler, metadata, context);
        return handler.toString();
    }
    public String getHTMLXDocReport() throws IOException {
        InputStream is = Files.newInputStream(Paths.get(path));
        XWPFDocument document = new XWPFDocument(is);
        XHTMLOptions options = XHTMLOptions.create().URIResolver(new FileURIResolver(new File("word/media")));

        OutputStream out = new ByteArrayOutputStream();


        XHTMLConverter.getInstance().convert(document, out, options);
        return out.toString();
    }
}