package com.capmtraining.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capmtraining.entities.Vendor;
import com.capmtraining.service.vendorService;

@RestController
public class VendorController {
	
	@Autowired
	vendorService vendorService;
	
	@RequestMapping("/vendor")
	// To test: http://localhost:8080/vendor
	public List<Vendor> getVendors(){
		return vendorService.readAllVendors();
	}
	
	@PostMapping("/vendor")
	// To test: Postman
	// http://localhost:8080/vendor and add payload
	/*
	 * { "companyName": "B", "firstName": "B", "lastName": "B", "website": "B",
	 * "email": "B", "status": "B", "regDate": "B" }
	 */
	public Vendor createVendor(@RequestBody Vendor myPostBody) {
		return vendorService.createVendor(myPostBody);
	}
	
	// To test: http://localhost:8080/vendor/search?company=SAP
	@RequestMapping("/vendor/search")
	public List<Vendor> searchByCompany(@RequestParam String company){
		return vendorService.searchByCompanyName(company);
	}
	
	//Request Parameter
	// To test: http://localhost:8080/vendor/lookup/477
	@RequestMapping("/vendor/lookup/{gstNo}")
	public List<Vendor> searchVendorByGST(@PathVariable("gstNo") String GSTNo){
		return vendorService.lookupVendorbyGST(GSTNo);
	}
	
	//GetEntity
	// To test: http://localhost:8080/vendor/4
	@RequestMapping("/vendor/{vendorCode}")
	public Vendor getVendorById(@PathVariable("vendorCode") String code) {
		Optional<Vendor> searchResult = vendorService.getSingleVendor(code);
		if(!searchResult.isPresent()) {
			return new Vendor((String)"", "", "", "", "", "", "", null); // will empty object
		}
		return searchResult.get();
	}
	

	// UPDATE
	// To test: http://localhost:8080/vendor
	// PUT and add updated payload
	@RequestMapping(method=RequestMethod.PUT, value="/vendor")
	public Vendor updateVendor(@RequestBody Vendor vendor) {
		return vendorService.changeVendor(vendor);
	}
	
	// To test: http://localhost:8080/vendor/4 and DELETE method
	@RequestMapping(method=RequestMethod.DELETE, value="/vendor/{id}")
	public String removeVendor(@PathVariable("id") String id) {
		return vendorService.deleteVendor(id);
	}

}
