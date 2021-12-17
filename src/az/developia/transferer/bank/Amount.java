package az.developia.transferer.bank;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

public class Amount {
    private BigDecimal value;
    private Currency currency;

    public Amount(BigDecimal value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount = (Amount) o;
        return Objects.equals(value, amount.value) && currency == amount.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, currency);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Amount.class.getSimpleName() + "[", "]")
                .add("value=" + value)
                .add("currency=" + currency)
                .toString();
    }

    public enum Currency {
        AZN, EUR, USD;
    }
}
