package util;

import model.Title;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.LazyDynaBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class PdfGenerator {


    public byte[] generatorPDF(Title title) throws IOException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        NumberFormat myFormatter = new DecimalFormat("###,###,###");
        String formattedNumber = myFormatter.format(Double.valueOf(title.getDni()));
        String principalText = "Deja constancia que…………"+title.getName()+"……………DNI N°…"+formattedNumber+"……,";
        List<DynaBean> dynaBeans = new ArrayList<>();
        DynaBean beane = new LazyDynaBean();
        beane.set("principalText", principalText);
        beane.set("number", ""+title.getNumber().toBigInteger().intValueExact());
        beane.set("book", ""+title.getBook().toBigInteger().intValueExact());
        beane.set("invoice", ""+title.getInvoice().toBigInteger().intValueExact());
        beane.set("cymat", toNumber(title.getCymat()));
        beane.set("cymatLetters", toLetter(title.getCymat()));
        beane.set("dateCymat", simpleDateFormat.format(title.getDateCymat()));
        beane.set("sp1", toNumber(title.getSp1()));
        beane.set("sp1Letters", toLetter(title.getSp1()));
        beane.set("dateSp1", simpleDateFormat.format(title.getDateSp1()));
        beane.set("biology", toNumber(title.getBiology()));
        beane.set("biologyLetters", toLetter(title.getBiology()));
        beane.set("dateBiology", simpleDateFormat.format(title.getDateBiology()));
        beane.set("fundaments", toNumber(title.getFundaments()));
        beane.set("fundamentsLetters", toLetter(title.getFundaments()));
        beane.set("dateFundaments", simpleDateFormat.format(title.getDateFundaments()));
        beane.set("cares", toNumber(title.getCares()));
        beane.set("caresLetters", toLetter(title.getCares()));
        beane.set("dateCares", simpleDateFormat.format(title.getDateCares()));
        beane.set("practice", toNumber(title.getPractice()));
        beane.set("practiceLetters", toLetter(title.getPractice()));
        beane.set("datePracticeF", simpleDateFormat.format(title.getDatePracticeF()));
        beane.set("average", title.getAverage().toString());
        beane.set("dateAverage", simpleDateFormat.format(title.getDateAverage()));
        dynaBeans.add(beane);
        Map<String, Object> parameters = new HashMap<>();



        byte[] b = null;

        try {
            JasperReport jasperReport;
            JasperDesign jasperDesign;
            JRBeanCollectionDataSource bean = new JRBeanCollectionDataSource(dynaBeans);

            InputStream reportResource = getClass().getClassLoader().getResourceAsStream("jasper/plantilla.jrxml");
            if (reportResource == null)
                System.out.println("No se pudo obtener el reporte");

            try {
                jasperDesign = JRXmlLoader.load(reportResource);
                jasperReport = JasperCompileManager.compileReport(jasperDesign);
                b = JasperRunManager.runReportToPdf(jasperReport, parameters, bean);
            } catch (JRException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return b;
    }


    public String toLetter(BigDecimal number){
        String fullNumberString = "";
        String fractionalString = "";
        String integerString = "";
        Integer numberInteger = number.toBigInteger().intValueExact();
        BigDecimal fractionalPart = number.remainder( BigDecimal.ONE );

        if(fractionalPart.compareTo(BigDecimal.ZERO) == 1){
            if(fractionalPart.compareTo(new BigDecimal("0.10")) == 0) fractionalString = "c/ diez";
            if(fractionalPart.compareTo(new BigDecimal("0.20")) == 0) fractionalString = "c/ veinte";
            if(fractionalPart.compareTo(new BigDecimal("0.30")) == 0) fractionalString = "c/ treinta";
            if(fractionalPart.compareTo(new BigDecimal("0.40")) == 0) fractionalString = "c/ cuarenta";
            if(fractionalPart.compareTo(new BigDecimal("0.50")) == 0) fractionalString = "c/ cincuenta";
            if(fractionalPart.compareTo(new BigDecimal("0.60")) == 0) fractionalString = "c/ sesenta";
            if(fractionalPart.compareTo(new BigDecimal("0.70")) == 0) fractionalString = "c/ setenta";
            if(fractionalPart.compareTo(new BigDecimal("0.80")) == 0) fractionalString = "c/ ochenta";
            if(fractionalPart.compareTo(new BigDecimal("0.90")) == 0) fractionalString = "c/ noventa";
        }
        if(numberInteger == 1) integerString = "Uno ";
        if(numberInteger == 2) integerString = "Dos ";
        if(numberInteger == 3) integerString = "Tres ";
        if(numberInteger == 4) integerString = "Cuatro ";
        if(numberInteger == 5) integerString = "Cinco ";
        if(numberInteger == 6) integerString = "Seis ";
        if(numberInteger == 7) integerString = "Siete ";
        if(numberInteger == 8) integerString = "Ocho ";
        if(numberInteger == 9) integerString = "Nueve ";
        if(numberInteger == 10) integerString = "Diez ";

        fullNumberString = integerString+fractionalString;
        return fullNumberString;
    }

    public String toNumber(BigDecimal number){
        Integer numberInteger = number.toBigInteger().intValueExact();
        BigDecimal fractionalPart = number.remainder( BigDecimal.ONE );

        if(fractionalPart.compareTo(BigDecimal.ZERO) == 1){
            BigDecimal newValor = number.setScale(1, RoundingMode.UNNECESSARY);
            return newValor.toString();
        }else{
            return numberInteger.toString();
        }
    }
}
