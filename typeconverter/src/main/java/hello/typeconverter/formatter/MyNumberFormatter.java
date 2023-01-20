package hello.typeconverter.formatter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.format.Formatter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

@Slf4j
public class MyNumberFormatter implements Formatter<Number> {

    @Override
    public Number parse(String text, Locale locale) throws ParseException {
        // 문자를 숫자로 변환 : "1,000" -> 1000
        log.info("text={}, locale={}", text, locale);
        return NumberFormat.getInstance(locale).parse(text);    
        // NumberFormat : 자바에서 제공, Locale 정보를 활용해서 나라별로 다른 숫자 포맷을 만들어줌
    }

    @Override
    public String print(Number object, Locale locale) {
        // 객체를 문자로 변환 : 1000 -> "1,000"
        log.info("object={}, locale={}", object, locale);
        return NumberFormat.getInstance(locale).format(object);
    }
}
