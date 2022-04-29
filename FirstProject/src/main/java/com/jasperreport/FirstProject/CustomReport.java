package com.jasperreport.FirstProject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class CustomReport {

	public static void main(String[] args) {
		try {
			String filePath = "C:\\Users\\mikhail.goyenechea\\Documents\\"
					+ "workspace-spring-tool-suite-4-4.14.0.RELEASE\\"
					+ "FirstProject\\src\\main\\resources\\Student.jrxml";
			
			Subject subject1 = new Subject("Java", 63);
			Subject subject2 = new Subject("Groovy", 95);
			Subject subject3 = new Subject("SQL", 59);
			List<Subject> list = new ArrayList<Subject>();
			list.add(subject1);
			list.add(subject2);
			list.add(subject3);
			
			//takes list data
			JRBeanCollectionDataSource dataSource = 
					new JRBeanCollectionDataSource(list);
			/*
				Created 2 fields in Main Report for subjectName and 
				marksObtained to make a chart
			*/
			
			//takes list data
			JRBeanCollectionDataSource chartDataSource = 
					new JRBeanCollectionDataSource(list);
			
			//Remember to create new parameters in Jasper Reports
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("studentName", "John");
			parameters.put("tableData", dataSource); //takes dataSource data
			parameters.put("subReport", getSubReport());
			parameters.put("subDataSource", getSubDataSource());
			parameters.put("subParameter", getSubParametersMap());
			
			JasperReport report = JasperCompileManager.compileReport(filePath);
			
			JasperPrint print = JasperFillManager.fillReport(report, parameters, chartDataSource);
			
			JasperExportManager.exportReportToPdfFile(print, "C:\\Users\\mikhail.goyenechea\\OneDrive - Accenture\\Documents\\Jasper Reports\\Sample Reports\\CustomReport.pdf");
			
			/*
			Export to HTML
			JasperExportManager.exportReportToHtmlFile(print, <directory>);
			Export to Excel	
			JasperExportManager.exportReportToXml(print, <directory>)
			*/
			
			System.out.println("Report Created...");
			
		} catch (Exception e) {
			System.out.println("Exception while creating report");
		}

	}
	
	//Create method to return the subreport as a JasperReport object
	public static JasperReport getSubReport() {
		String filePath = "C:\\Users\\mikhail.goyenechea\\Documents"
				+ "\\workspace-spring-tool-suite-4-4.14.0.RELEASE\\"
				+ "FirstProject\\src\\main\\resources\\FirstReport.jrxml";
		JasperReport report;
		try {
			report = JasperCompileManager.compileReport(filePath);
			return report;
		} catch (JRException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	//Create method for subdata source
	public static JRBeanCollectionDataSource getSubDataSource() {
		//create object (data), pass into list, use JRBeanCollectionDataSource
		Student student1 = new Student(1L, "John", "Doe", "Baker Street", "Kansas City");
		
		List <Student> list = new ArrayList<Student>();
		list.add(student1);
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
		
		return dataSource;
		
	}
	
	public static Map<String, Object> getSubParametersMap() {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("studentName", "John");
		
		return parameters;
	}

}
