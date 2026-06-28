package com.marcin.currencyexchanger.nbp;

import com.marcin.currencyexchanger.currency.Currency;
import com.marcin.currencyexchanger.currency.CurrencyMapper;
import com.marcin.currencyexchanger.nbp.dto.NbpTableResponseDto;
import com.marcin.currencyexchanger.nbp.exception.CurrencyNotFoundException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NbpService {
    private static final Logger logger = LoggerFactory.getLogger(NbpService.class);

    private static final String BASE_URL = "http://api.nbp.pl/api/exchangerates/tables/A/";

    private final RestClient restClient;

    private final CurrencyMapper currencyMapper;

    private final NbpRepository nbpRepository;

    @PostConstruct
    public void initRepositoryData() {
        logger.info("Init repository data");

        List<Currency> apiCurrencies = this.getCurrenciesFromNbp();

        nbpRepository.setCurrencies(apiCurrencies);
    }

    private List<Currency> getCurrenciesFromNbp() {
        List<NbpTableResponseDto> response = restClient.get()
                .uri(BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<List<NbpTableResponseDto>>() {});

        if (response != null && !response.isEmpty()) {
            NbpTableResponseDto table = response.getFirst();

            return table.rates().stream()
                    .map(currencyMapper::toCurrency)
                    .toList();
        }

        return null;
    }

    public Currency getCurrencyByCode(String code) {
        if ("PLN".equals(code)) {
            return new Currency("PLN", "Polski złoty", BigDecimal.ONE);
        }

        return nbpRepository.getCurrencyByCode(code).orElseThrow(
                () -> new CurrencyNotFoundException(code));
    }

    public List<Currency> getAllCurrenciesFromCache() {
        return nbpRepository.getCurrencies();
    }
}
