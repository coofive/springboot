package com.coofive.springboot;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFamily;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

import java.io.File;
import java.net.ConnectException;

/**
 * @author : coofive
 * @version : 1.0.0
 * @date : 2/21/2019 8:41 PM
 */
public class OpenOffice {
    public static void main(String[] args) throws Exception {
        String docFile = "D:\\03_Applications\\temp_intellij\\openOffice\\test.docx";
        String pdfFile = "D:\\03_Applications\\temp_intellij\\openOffice\\test.pdf";
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
        connection.connect();
        DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
        DocumentFormat docx = new DocumentFormat("Microsoft Word 2007 XML", DocumentFamily.TEXT, "application/vnd.openxmlformats-officedocument.wordprocessingml.document", "docx");
        converter.convert(new File(docFile),docx, new File(pdfFile),null);

    }
}
