package com.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.model.BuyMedicine;
import com.repository.BuyMedicineRepository;
import com.repository.MedicineInfoProjection;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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
    
    
    // for report 
    
    public byte[] generateBuyReport(java.sql.Date entrydate) throws Exception {
        List<BuyMedicine> buyMedicines = buyMedicineRepository.findAll();

        // Load Jasper template
        InputStream reportStream = new ClassPathResource("BuyMedicine.jrxml").getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        // Prepare data source and parameters
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(buyMedicines);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ReportTitle", "Buy Report");
        parameters.put("entrydate", entrydate); // Pass java.sql.Date here to Jasper

        // Fill the report with data
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Export to PDF and return byte array
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }


    

}
