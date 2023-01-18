package hello.exception.resolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {


    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // Object handler : 핸들러(컨트롤러) 정보,   Exception ex : 핸들러(컨트롤러)에서 발생한 예외

        log.info("call resolver", ex);

        try {
            if (ex instanceof IllegalArgumentException) {
                log.info("IllegalArgumentException resolver to 400");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
                // 예외를 먹고 BAD_REQUEST 보냄
                return new ModelAndView();  // 새로운 ModelAndView 반환 -> 정상적으로 WAS 까지 감
            }
        } catch (IOException e) {
            log.error("resolver ex", e);
        }

        return null;

    }
}
