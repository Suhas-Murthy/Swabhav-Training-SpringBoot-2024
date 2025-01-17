package com.aurionpro.bank.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aurionpro.bank.entity.Customer;
import com.aurionpro.bank.entity.KYCDocuments;
import com.aurionpro.bank.service.KYCService;

@RestController
@RequestMapping("/bankapp")
public class KYCController {
	@Autowired
	private final KYCService kycService;

	public KYCController(KYCService kycService) {
		this.kycService = kycService;
	}

	@PostMapping("/kyc/upload/{customerId}")
	public String uploadKYCDocuments(@PathVariable int customerId, @RequestParam Map<String, MultipartFile> files) {
// Loop through the files and upload
		files.forEach((key, file) -> {
			kycService.uploadDocuments(customerId, files); // key can be used as the document name
		});
		return "Documents uploaded successfully";
	}
//    public String uploadKYCDocuments(@PathVariable int customerId, @RequestParam List<MultipartFile> files) {
//        kycService.uploadDocuments(customerId, files);
//        return "Documents uploaded successfully";
//    }
	
	@GetMapping("/kyc")
	public List<KYCDocuments> getAllDocuments() {
		return kycService.getDocuments();
	}
	
//	@GetMapping("/kyc/{customerId}")
//	public List<KYCDocuments> getAllDocuments(@PathVariable int customerId) {
//		return kycService.getKycDocumentsByCustomerId(customerId);
//	}
	
	
}
