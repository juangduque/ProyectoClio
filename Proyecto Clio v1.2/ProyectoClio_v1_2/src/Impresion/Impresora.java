package Impresion;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JOptionPane;
 
public class Impresora {  
 
    public void imprimir(String path) throws IOException {
 
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Fallo en gestión de archivo", JOptionPane.ERROR_MESSAGE);
        }
        if (inputStream == null) {
            return;
        }
 
        DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc document = new SimpleDoc(inputStream, docFormat, null);
 
        PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
 
        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
 
        if (defaultPrintService != null) {
            DocPrintJob printJob = defaultPrintService.createPrintJob();
            try {
                printJob.print(document, attributeSet);
 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Fallo en la impresión", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } else {
            System.err.println("No existen impresoras instaladas");
        } 
        inputStream.close();
    }
}