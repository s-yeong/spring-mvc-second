package hello.itemservice.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;

    @Test
    void helloMessage() {
        // code : hello, args : null, locale : null
        String result = ms.getMessage("hello", null, null);
        // local 정보가 없으면 시스템의 기본 로케일 사용 -> Locale.getDefault() 호출 -> ko_KR
        // messages_ko.properties 조회 -> 조회 실패 -> messages.properties 조회

        assertThat(result).isEqualTo("안녕");
    }

    @Test
    void notFoundMessageCode() {
        assertThatThrownBy(() -> ms.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void notFoundMessageCodeDefaultMessage() {
        String result = ms.getMessage("no_code", null, "기본 메시지", null);
        assertThat(result).isEqualTo("기본 메시지");
    }

    @Test
    void argumentMessage() {
        // 메시지의 {0} 부분은 매개변수를 전달해서 치환할 수 있음
        // hello.name=안녕 {0} -> Spring 단어를 매개변수로 전달 -> 안녕 Spring
        String result = ms.getMessage("hello.name", new Object[]{"Spring"}, null);
        assertThat(result).isEqualTo("안녕 Spring");
    }

    @Test
    void defaultLang() {
        // message_ko가 없으므로 default message인 messages 사용
        assertThat(ms.getMessage("hello", null, null)).isEqualTo("안녕");
        assertThat(ms.getMessage("hello", null, Locale.KOREA)).isEqualTo("안녕");
    }

    @Test
    void enLang() {
        assertThat(ms.getMessage("hello", null, Locale.ENGLISH)).isEqualTo("hello");
    }



}
