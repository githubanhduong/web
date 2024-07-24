package com.likelion.web;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CVPdf {
    @Test
    public static void main(String[] args) throws IOException {
        // File file = new File("D:\\javong\\pdf1.pdf");
        // PDDocument doc = new PDDocument.load(file);

        // // Retrieve the page
        // PDPage page = doc.getPage(0);

        // // Creating Object of PDImageXObject for selecting
        // // Image and provide the path of file in argument
        // PDImageXObject pdfimg = PDImageXImage.createFromFile(
        //         "D:\\Images\\chloro.jpg", doc);

        // // Creating the PDPageContentStream Object
        // // for Inserting Image
        // PDPageContentStream image = new PDPageContentStream(doc, page);

        // // set the Image inside the Page
        // image.drawImage(pdfImage, 55, 370);
        // System.out.println("Image Inserted");

        // // Closing the page of PDF by closing
        // // PDPageContentStream Object
        // // && Saving the Document
        // image.close();
        // doc.save("D:\\javong\\pdf1.pdf");

        // // Closing the Document
        // doc.close();
    }
}
