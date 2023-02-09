package view;

import model.Title;
import util.ExcelReader;
import util.PdfGenerator;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Principal extends JFrame {
    private JButton excelButton;
    private JButton generateButton;
    private JPanel principalPane;
    private JLabel selectorLabel;
    private JPanel chargePanel;
    private JButton descargarExcelDeEjemploButton;
    private JTextField fieldMonth;
    private JTextField fieldYear;
    private JTextField fieldDay;
    File selectedFile = null;

    public Principal() {
        excelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

                int returnValue = jfc.showOpenDialog(null);
                // int returnValue = jfc.showSaveDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedFile = jfc.getSelectedFile();
                    selectorLabel.setText(selectedFile.getName());
                }
            }
        });
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ExcelReader excelReader = new ExcelReader();
                PdfGenerator pdfGenerator = new PdfGenerator();
                InputStream inputStream = null;
                try {
                    inputStream = new FileInputStream(selectedFile);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un archivo primero");
                    throw new RuntimeException(ex);
                }
                List<Title> titles = null;
                try {
                    titles = excelReader.generateTitles(inputStream);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "El formato del excel es incorrecto, descargue el archivo de ejemplo");
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(null, "Elija donde guardar el zip generado");
                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                jfc.setSelectedFile(new File("Titulos.zip"));
                int returnValue = jfc.showSaveDialog(null);
                String day = "";
                String month = "";
                String year = "";
                try{
                    day = fieldDay.getText();
                    month = fieldMonth.getText();
                    year = fieldYear.getText();
                }catch(Exception ex){

                }

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String zipFile = jfc.getSelectedFile().getParent() + "/Titulos.zip";
                    try {
                        FileOutputStream fos = new FileOutputStream(zipFile);
                        ZipOutputStream zos = new ZipOutputStream(fos);
                        for (Title tit : titles) {
                            zos.putNextEntry(new ZipEntry(tit.getName().replace(" ", "").concat(".pdf")));
                            zos.write(pdfGenerator.generatorPDF(tit, day, month, year));
                            zos.closeEntry();
                        }
                        zos.close();
                    } catch (IOException ioe) {
                        System.out.println("Error creating zip file: " + ioe);
                    }
                    JOptionPane.showMessageDialog(null, "Archivo guardado");
                }
            }
        });
        descargarExcelDeEjemploButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                jfc.setSelectedFile(new File("EjemploExcel.xlsx"));
                int returnValue = jfc.showSaveDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File file = jfc.getSelectedFile();
                    String fileName = jfc.getSelectedFile().getParent() + "/EjemploExcel.xlsx";
                    File outputFile = new File(fileName);
                    try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
                        try {
                            InputStream excelInput = getClass().getClassLoader().getResourceAsStream("EjemploExcel.xlsx");
                            outputStream.write(excelInput.readAllBytes());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    } catch (Exception ex) {

                    }
                    JOptionPane.showMessageDialog(null, "Archivo guardado");
                }
            }
        });
    }

    public static void main(String[] args) {
        Principal principal = new Principal();
        principal.setContentPane(principal.principalPane);
        principal.setTitle("Titulos");
        principal.setSize(800, 600);
        principal.setVisible(true);
        principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
