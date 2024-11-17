package com.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.BuyMedicine;
import com.repository.MedicineInfoProjection;
import com.service.BuyMedicineService;

@RestController
@RequestMapping("/buymedicine")
public class BuyMedicineController {

	@Autowired
    private BuyMedicineService buymedicineService;
	
    @PostMapping("/add")
    public ResponseEntity<?> addMedicine(@RequestBody BuyMedicine buymedicine) {
    	BuyMedicine  saveMedicine = buymedicineService.addBuyMedicine(buymedicine);
        if (saveMedicine != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(saveMedicine);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving Buy Medicine");
        }
    }
    
    @GetMapping("/info")
    public ResponseEntity<List<MedicineInfoProjection>> getMedicineInfo() {
        List<MedicineInfoProjection> medicineInfoList = buymedicineService.getMedicineInfo();
        return ResponseEntity.ok(medicineInfoList);
    }
	
    @PutMapping("/editbuymedicine/{id}")
    public ResponseEntity<BuyMedicine> updateMedicines( @PathVariable("id") Long id, @RequestBody BuyMedicine updatedBuyMedicineData) {
    	BuyMedicine updatedBuyMedicine = buymedicineService.updateBuyMedicine(id, updatedBuyMedicineData);
        if (updatedBuyMedicine != null) {
            return ResponseEntity.ok(updatedBuyMedicine);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("deletebuymedicine/{id}")
    public ResponseEntity<String> deleteBuyMedicinedata(@PathVariable Long id) {
        try {
        	buymedicineService.deleteBuyMedicine(id);
            return new ResponseEntity<>("Medicine deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting Medicine: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
    @GetMapping("/search")
    public List<MedicineInfoProjection> searchBuyMedicine(@RequestParam("query") String query) {
        return buymedicineService.searchBuyMedicine(query);
    }
    
 // for report 
    @GetMapping("/buyReport")
    public ResponseEntity<byte[]> downloadBuyReport(@RequestParam("entrydate") String entrydate) {
        try {
            // Parse entrydate as LocalDate with the format "dd-MM-yyyy"
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate formattedEntryDate;
            try {
                formattedEntryDate = LocalDate.parse(entrydate, formatter); // Convert to LocalDate
            } catch (DateTimeParseException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // 400 if date format is incorrect
            }
            // Convert LocalDate to java.sql.Date to pass to the service
            java.sql.Date sqlDate = java.sql.Date.valueOf(formattedEntryDate);
            // Generate the report with the parsed date
            byte[] pdfBytes = buymedicineService.generateBuyReport(sqlDate); // Pass java.sql.Date to the service

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "buy_report.pdf");
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    // for report id data type is java.sql.Date in model class then no need DTO class
   /* 
    @GetMapping("/buyReport")
    public ResponseEntity<byte[]> downloadBuyReport(@RequestParam("entrydate") String entrydate) {
        try {
        	 // Parse the String to a java.util.Date using SimpleDateFormat
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date utilDate = sdf.parse(entrydate);

            // Convert java.util.Date to java.sql.Date
            Date sqlDate = new Date(utilDate.getTime());

            // Generate the report with the parsed date
            byte[] pdfBytes = buymedicineService.generateBuyReport(sqlDate); // Pass java.sql.Date to the service

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "buy_report.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
*/
    
    // REport for Voucher

    @GetMapping("/voucherReport")
    public ResponseEntity<byte[]> downloadBuyReport(@RequestParam("entrydate") String entrydate, @RequestParam("voucherid") Integer voucherid) {
        try {
            // Parse entrydate as LocalDate with the format "dd-MM-yyyy"
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate formattedEntryDate;
            try {
                formattedEntryDate = LocalDate.parse(entrydate, formatter); // Convert to LocalDate
            } catch (DateTimeParseException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // 400 if date format is incorrect
            }
            // Convert LocalDate to java.sql.Date to pass to the service
            java.sql.Date sqlDate = java.sql.Date.valueOf(formattedEntryDate);
            // Generate the report with the parsed date
            byte[] pdfBytes = buymedicineService.generateVoucherReport(sqlDate,voucherid); // Pass java.sql.Date to the service

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "buy_report.pdf");
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    
    
    
	
}
