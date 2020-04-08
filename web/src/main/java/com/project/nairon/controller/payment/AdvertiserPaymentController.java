/**
 * 
 */
package com.project.nairon.controller.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flutterwave.rave.java.payload.transferPayload;
import com.project.nairon.service.payment.TransferService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author gbemisola
 *
 */

@RequestMapping("/api/pay")
@RestController
@Tag(name = "Flutterwave Payment", description = "The Flutter Payment Service API")
public class AdvertiserPaymentController {

	private TransferService transferService;

	@Autowired
	public AdvertiserPaymentController(TransferService transferService){
		this.transferService = transferService;
	}


	@GetMapping("/home")
	@PreAuthorize("hasRole('ADVERTISER')")
	@Operation(summary = "Welcome to Flutter Service", description = "this is just a welcome message", tags = { "Flutterwave Payment" })
	public String test() {
		return "Hello advertiser welcome to  FlutterWave payment";
	}

	@PostMapping("/transfer")
	public void doTransfer(transferPayload transferPayload) {
		transferService.dotransfer(transferPayload);
	}

}

