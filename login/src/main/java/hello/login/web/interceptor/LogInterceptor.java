package hello.login.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {


    public static final String LOG_ID = "logId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        String logId = UUID.randomUUID().toString();    // 요청 로그 구분 위해 uuid 생성
        request.setAttribute(LOG_ID, logId);
        /*
            서블릿 필터의 경우 지역변수로 해결이 가능하지만, 스프링 인터셉터는 호출 시점이 완전히 분리되어 있음
            preHandle에서 지정한 값을 postHandle, afterCompletion에서 함꼐 사용하려면 어딘가 담아둬야함
            => request에 담아둠(멤버 변수로 사용하면 위험 - 싱글톤이기 때문에)
         */

        //@RequestMapping : HandlerMethod
        //정적 리소스 : ResourceHttpRequestHandler
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;// 호출할 컨트롤러 메서드의 모든 정보 포함되어 있다.
        }
        log.info("REQUEST[{}][{}][{}]", logId, requestURI, handler);
        return true;    // 정상 호출 -> 다음 인터셉터나 컨트롤러 호출
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle [{}]", modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestURI = request.getRequestURI();
        String logId = (String) request.getAttribute(LOG_ID);
        log.info("REQUEST[{}][{}][{}]", logId, requestURI, handler);
        if(ex != null) {
            log.error("afterCompletion error!!", ex);   // 오류는 바로 넣으면 됨
        }
    }
}
