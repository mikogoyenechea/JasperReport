package com.jasperreport.FirstProject;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.base.JRBaseTextField;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.List;


public class App 
{
    public static void main( String[] args )
    {
        try {
			String filePath = "C:\\Users\\mikhail.goyenechea\\Documents"
					+ "\\workspace-spring-tool-suite-4-4.14.0.RELEASE\\"
					+ "FirstProject\\src\\main\\resources\\FirstReport.jrxml";
			
			//how to set value for parameter:
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("studentName", "John");
			
			//create object (data), pass into list, use JRBeanCollectionDataSource
			Student student1 = new Student(1L, "John", "Doe", "Baker Street", "Kansas City");
			
			List <Student> list = new ArrayList<Student>();
			list.add(student1);
			
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
			
			//compile jrxml file
			JasperReport report = JasperCompileManager.compileReport(filePath);
			
			//change element from Java code (results in exception)
//			JRBaseTextField textField = (JRBaseTextField) report.getTitle().getElementByKey("name");
//			
//			textField.setForecolor(Color.RED);
			
			//pass data to report
			JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);
			
			//export
			JasperExportManager.exportReportToPdfFile(print, "C:\\Users\\mikhail.goyenechea\\OneDrive - Accenture\\Documents\\Jasper Reports\\Sample Reports\\FirstReport.pdf");
			
			System.out.println("Report Created");
			
		} catch (Exception e) {
			System.out.println("Exception while creating report");
		}
    }
}
