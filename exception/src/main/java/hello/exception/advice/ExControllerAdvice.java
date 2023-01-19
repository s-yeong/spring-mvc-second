package hello.exception.advice;

import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice("hello.exception.api")   // @ResponseBody + @ControllerAdvice
public class ExControllerAdvice {

    /**
     * 대상으로 지정한 여러 컨트롤러에 @ExceptionHandler, @InitBinder 기능 부여해주는 역할
     * 대상 지정하지 않으면 모든 컨트롤러에 적용
     */

    @ResponseStatus(HttpStatus.BAD_REQUEST) // 200 OK -> 400 Bad Request
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandle(IllegalArgumentException e) {
        log.error("[exceptionHandle] ex", e);
        return new ErrorResult("BAD", e.getMessage());
    }

    @ExceptionHandler   // 생략가능 - 메서드 파라미터의 예외가 지정됨 (UserException)
    public ResponseEntity<ErrorResult> userExHandle(UserException e) {
        log.error("[exceptionHandle] ex", e);
        ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandle(Exception e) {
        // 개발할 때 거의 최상위 예외
        // => RuntimeException 여기서 처리됨 (하위 자식 클래스를 모두 처리할 수 있기 때문)
        log.error("[exceptionHandle] ex", e);
        return new ErrorResult("EX", "내부 오류");
    }
}
