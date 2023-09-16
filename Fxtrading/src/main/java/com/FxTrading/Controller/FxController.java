package com.FxTrading.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.FxTrading.Entity.ForeignExchange;
import com.FxTrading.Exception.CcyAlreadyExistsException;
import com.FxTrading.Exception.CcyNotValidException;
import com.FxTrading.Exception.ExchangeDataNotExistsException;
import com.FxTrading.Service.FxService;

@RestController
public class FxController {
	@Autowired
	private FxService serviceImpl;

	@GetMapping("/add-ccy/{ccy}")
	public ResponseEntity<String> addCcy(@PathVariable String ccy) {
		int status = serviceImpl.addCcy(ccy);
		if (status == 1) {
			return new ResponseEntity<String>("CCY Added !!", HttpStatus.CREATED);
		} else if (status == 2) {
			throw new CcyAlreadyExistsException("Ccy" + ccy + " Already Added In Properties File");
		} else if (status == 3) {
			throw new CcyNotValidException("Invalid CCY ");
		}else {
			return new ResponseEntity<String>("Something Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@PostMapping("/exchange-currency")
	public ResponseEntity<String> exchangeCurrency(@RequestBody ForeignExchange currency) {
		

		int status = serviceImpl.exchangeCurrency(currency);
		if (status == 1) {
			return new ResponseEntity<String>("Currency Exchanged !!", HttpStatus.CREATED);
		} else if (status == 2) {
			throw new CcyNotValidException("Ccy Not Exists in ccy-data.properties");
		} else {
			return new ResponseEntity<String>("Something Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/get-all-exchanges")
	public ResponseEntity<Map<String, String>> getAllExchanges() {

		Map<String, String> map = serviceImpl.getAllExchanges();
		if (map != null) {
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.FOUND);
		} else {
			throw new ExchangeDataNotExistsException("No Records Found");
		}

	}
	

}
