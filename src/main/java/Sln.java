import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Sln {


    static final String fileName = "src/main/resources/Blank_A4.jrxml";
    static final String outFile = "EmployeeReports.pdf";

    public static void main(String[] args) throws JRException, FileNotFoundException {

        List<Employee> employeeList = new ArrayList<Employee>();
        Map<String, Object> parameter = new HashMap<String, Object>();

        employeeList.add(new Employee(1, "Jack Ryan", 100.0));
        employeeList.add(new Employee(2, "Cathy Mueller", 130.0));
        employeeList.add(new Employee(3, "Matice", 90.0));

        parameter.put("employeeDataSource", new JRBeanCollectionDataSource(employeeList));
        parameter.put("title", "Employee Report");

        JasperReport jasperDesign = JasperCompileManager.compileReport(fileName);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperDesign, parameter, new JREmptyDataSource());

        File file = new File(outFile);
        OutputStream outputSteam = new FileOutputStream(file);
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputSteam);

        System.out.println("Report Generated!");


    }

}