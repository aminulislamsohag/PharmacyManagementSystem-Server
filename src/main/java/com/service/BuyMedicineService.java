package com.service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.dto.BuyMedicineReportDTO;
import com.model.BuyMedicine;
import com.repository.BuyMedicineRepository;
import com.repository.MedicineInfoProjection;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Service
public class BuyMedicineService {
	
	@Autowired
    private BuyMedicineRepository buyMedicineRepository;
	
    public BuyMedicine addBuyMedicine(BuyMedicine buymedicine) {
        return buyMedicineRepository.save(buymedicine);
    }
    public List<MedicineInfoProjection> getMedicineInfo() {
        return buyMedicineRepository.fetchMedicineInfo();
    }
    
    public BuyMedicine updateBuyMedicine(Long id, BuyMedicine updatedMedicineData) {
        Optional<BuyMedicine> existingBuyMedicineOpt = buyMedicineRepository.findById(id);

        if (existingBuyMedicineOpt.isPresent()) {
            BuyMedicine existingBuyMedicine = existingBuyMedicineOpt.get();
            existingBuyMedicine.setMedicineid(updatedMedicineData.getMedicineid());
            existingBuyMedicine.setQuantity(updatedMedicineData.getQuantity());
            existingBuyMedicine.setPrice(updatedMedicineData.getPrice());
            existingBuyMedicine.setMakedate(updatedMedicineData.getMakedate());
            existingBuyMedicine.setExpairdate(updatedMedicineData.getExpairdate());
            existingBuyMedicine.setVoucherid(updatedMedicineData.getVoucherid());
            return buyMedicineRepository.save(existingBuyMedicine);
        } else {
            return null; // Medicine with the specified ID was not found
        }
    }
    
    public void deleteBuyMedicine(Long id) {
        if (buyMedicineRepository.existsById(id)) {
        	buyMedicineRepository.deleteById(id);
        } else {
            throw new RuntimeException("Buy Medicine not found with ID: " + id);
        }
    }
    
    public List<MedicineInfoProjection> searchBuyMedicine(String query) {
        List<MedicineInfoProjection> result = new ArrayList<>();
        List<MedicineInfoProjection> allRecords = buyMedicineRepository.fetchMedicineInfo();

        try {
            // Attempt to parse the query as an integer to search by medicine id
            int medicineid = Integer.parseInt(query);
            // Filter the results by medicine id
            result = allRecords.stream()
                               .filter(record -> record.getMedicineid() != null && record.getMedicineid().equals(medicineid))
                               .toList();
        } catch (NumberFormatException e) {
            // If parsing fails, filter by Name (name search)
            result = allRecords.stream()
                               .filter(record -> record.getMedicinename() != null && record.getMedicinename().contains(query))
                               .toList();
        }

        return result;
    }
    
    
    // for buy report 
    public byte[] generateBuyReport(java.sql.Date entrydate, String fileType) throws Exception {
        List<BuyMedicine> buyMedicines = buyMedicineRepository.findAll();

        // Convert each BuyMedicine to BuyMedicineReportDTO
        List<BuyMedicineReportDTO> reportData = buyMedicines.stream()
            .map(BuyMedicineReportDTO::new)
            .collect(Collectors.toList());

        // Load Jasper template
        InputStream reportStream = new ClassPathResource("BuyMedicine.jrxml").getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        // Prepare data source and parameters
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportData);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ReportTitle", "Buy Report");
        parameters.put("entrydate", entrydate);

        // Fill the report with data
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Export to the desired format
        if ("xlsx".equalsIgnoreCase(fileType)) {
            return exportReportToExcel(jasperPrint);
        } else { // Default to PDF
            return JasperExportManager.exportReportToPdf(jasperPrint);
        }
    }

  
   /* 
    // for report date type is java.sql.Date in model class then no need DTO class
    public byte[] generateBuyReport(java.sql.Date entrydate) throws Exception {
        List<BuyMedicine> buyMedicines = buyMedicineRepository.findAll();

        // Load Jasper template
        InputStream reportStream = new ClassPathResource("BuyMedicine.jrxml").getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        // Prepare data source and parameters
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(buyMedicines);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ReportTitle", "Buy Report");
        parameters.put("entrydate", entrydate); // Use "entrydate" to match the parameter name in the Jasper file

        // Fill the report with data
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Export to PDF and return byte array
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
    */
    
 // for Voucher report  from multiple table direct connection  database
 
    private final DataSource dataSource;
    @Autowired
    public BuyMedicineService(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public byte[] generateVoucherReport(java.sql.Date entrydate, Integer voucherid, String fileType) throws Exception {
        // Load Jasper template
        InputStream reportStream = new ClassPathResource("VoucherReport.jrxml").getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        // Prepare parameters
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ReportTitle", "Voucher Report");
        parameters.put("entrydate", entrydate); // Pass the entrydate parameter to the report
        parameters.put("voucherid", voucherid); // Pass the voucherid parameter to the report

        // Establish a database connection for the report
        try (Connection connection = dataSource.getConnection()) {
            // Fill the report using the query in the Jasper file
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

            // Generate report based on fileType
            if ("xlsx".equalsIgnoreCase(fileType)) {
                return exportReportToExcel(jasperPrint);
            } else { // Default to PDF
                return JasperExportManager.exportReportToPdf(jasperPrint);
            }
        }
    }

    private byte[] exportReportToExcel(JasperPrint jasperPrint) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Configure the Excel exporter
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));

        // Excel-specific configuration
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        configuration.setOnePagePerSheet(false);
        configuration.setDetectCellType(true);
        configuration.setCollapseRowSpan(false);
        configuration.setWhitePageBackground(false); // Optional: Adjust based on requirements
        exporter.setConfiguration(configuration);

        // Export the report
        exporter.exportReport();
        return outputStream.toByteArray();
    }

    /*  // for xcel report
    private final DataSource dataSource;
    @Autowired
    public BuyMedicineService(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public byte[] generateVoucherReportExcel(java.sql.Date entrydate, Integer voucherid) throws Exception {
        // Load Jasper template
        InputStream reportStream = new ClassPathResource("VoucherReport.jrxml").getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        // Prepare parameters
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ReportTitle", "Voucher Report");
        parameters.put("entrydate", entrydate); // Pass the entrydate parameter to the report
        parameters.put("voucherid", voucherid); // Pass the voucherid parameter to the report

        // Establish a database connection for the report
        try (Connection connection = dataSource.getConnection()) {
            // Fill the report using the query in the Jasper file
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

            // Export the report to an Excel file
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            // Configure the Excel exporter
            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));

            // Optional: Set Excel-specific configurations
            SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
            configuration.setOnePagePerSheet(false);
            configuration.setDetectCellType(true);
            configuration.setCollapseRowSpan(false);
            exporter.setConfiguration(configuration);

            // Export the report
            exporter.exportReport();

            return outputStream.toByteArray();
        }
    }
*/

}
