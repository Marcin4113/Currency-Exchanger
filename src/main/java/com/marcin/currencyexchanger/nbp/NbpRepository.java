package com.marcin.currencyexchanger.nbp;

import com.marcin.currencyexchanger.currency.Currency;
import com.marcin.currencyexchanger.nbp.exception.CurrencyAlreadyExistsException;
import com.marcin.currencyexchanger.nbp.exception.CurrencyNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Repository
public class NbpRepository {
    private List<Currency> currencies;

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = new ArrayList<>(currencies);
    }

    public Optional<Currency> getCurrencyByCode(String code){
        for (Currency currency : currencies) {
            if (currency.code().equals(code)) {
                return Optional.of(currency);
            }
        }

        return Optional.empty();
    }
}
