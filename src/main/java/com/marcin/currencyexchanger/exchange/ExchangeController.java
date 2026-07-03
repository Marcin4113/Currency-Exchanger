package com.marcin.currencyexchanger.exchange;

import com.marcin.currencyexchanger.currency.Currency;
import com.marcin.currencyexchanger.exchange.dto.ExchangeRequest;
import com.marcin.currencyexchanger.exchange.dto.ExchangeResponse;
import com.marcin.currencyexchanger.nbp.NbpService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Validated
public class ExchangeController {

    private final ExchangeService exchangeService;

    private final NbpService nbpService;

    @PostMapping("/exchange")
    public ResponseEntity<ExchangeResponse> exchangeCurrency(@Valid @RequestBody ExchangeRequest exchangeRequest) {
        ExchangeResponse exchangeResponse = exchangeService.exchangeCurrency(exchangeRequest);

        return new ResponseEntity<>(exchangeResponse, HttpStatus.OK);
    }

    @GetMapping("/currencies")
    public ResponseEntity<List<Currency>> getCurrencies() {
        return new ResponseEntity<>(nbpService.getAllCurrenciesFromCache(), HttpStatus.OK);
    }

    @GetMapping("/currency")
    public ResponseEntity<Currency> getCurrency(
            @RequestParam("code")
            @Pattern(regexp = "^[A-Z]{3}$", message = "Currency code must consist of exactly 3 uppercase letters")
            String code) {
        return new ResponseEntity<>(nbpService.getCurrencyByCode(code), HttpStatus.OK);
    }
}
