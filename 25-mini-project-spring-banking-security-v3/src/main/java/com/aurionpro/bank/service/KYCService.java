package com.aurionpro.bank.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aurionpro.bank.entity.Customer;
import com.aurionpro.bank.entity.KYCDocuments;
import com.aurionpro.bank.repository.CustomerRepository;
import com.aurionpro.bank.repository.KYCDocumentsRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class KYCService {
	@Autowired
	private final Cloudinary cloudinary;
	@Autowired
	private final KYCDocumentsRepository kycDocumentsRepository;
	@Autowired
	private final CustomerRepository customerRepository;

	public KYCService(Cloudinary cloudinary, KYCDocumentsRepository kycDocumentsRepository,
			CustomerRepository customerRepository) {
		this.cloudinary = cloudinary;
		this.kycDocumentsRepository = kycDocumentsRepository;
		this.customerRepository = customerRepository;
	}

	
    public void uploadDocuments(int customerId, Map<String, MultipartFile> files) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));

        files.forEach((name, file) -> {
            try {
                Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

                KYCDocuments kycDocument = new KYCDocuments();
                kycDocument.setName(name);  // Use the custom name (key) from the map
                kycDocument.setImageUrl(uploadResult.get("url").toString());
                kycDocument.setImageId(uploadResult.get("public_id").toString());
                kycDocument.setCustomer(customer);

                kycDocumentsRepository.save(kycDocument);

            } catch (IOException e) {
                throw new RuntimeException("Failed to upload document: " + name, e);
            }
        });
    }
    
    public List<KYCDocuments> getDocuments() {
    	return kycDocumentsRepository.findAll();
    }
    
    public List<KYCDocuments> getKycDocumentsByCustomerId(int customerId){
    	return kycDocumentsRepository.findByCustomerCustomerId(customerId);
    }
//    
    
//	public void uploadDocuments(int customerId, List<MultipartFile> files) {
//		Customer customer = customerRepository.findById(customerId)
//				.orElseThrow(() -> new RuntimeException("Customer not found"));
//
//		for (MultipartFile file : files) {
//			try {
//				Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
//				KYCDocuments kycDocument = new KYCDocuments();
//				kycDocument.setName(file.getOriginalFilename());
//				kycDocument.setImageUrl(uploadResult.get("url").toString());
//				kycDocument.setImageId(uploadResult.get("public_id").toString());
//				kycDocument.setCustomer(customer);
//
//				kycDocumentsRepository.save(kycDocument);
//
//			} catch (IOException e) {
//				throw new RuntimeException("Failed to upload document", e);
//			}
//		}
//	}
}
