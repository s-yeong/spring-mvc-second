package hello.itemservice.validation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.FieldError;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.ObjectError;

import static org.assertj.core.api.Assertions.*;

public class MessageCodeResolverTest {

    // MessageCodesResolver : 검증 오류 코드로 메시지 코드들을 생성
    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

    @Test
    void messageCodeResolverObject() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item");

//        new ObjectError("item", new String[]{"required", "required.item"}, null, null);
        assertThat(messageCodes).containsExactly("required.item", "required");
    }

    @Test
    void messageCodeResolverField() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item", "itemName", String.class);

        // bindingResult.rejectValue("itemName", "required");
//        new FieldError("item", "itemName", null, false, messageCodes, null, null);
        assertThat(messageCodes).containsExactly(
                "required.item.itemName",
                "required.itemName",
                "required.java.lang.String",
                "required"
        );

    }

}
