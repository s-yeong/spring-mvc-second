package hello.login;

import hello.login.web.filter.LogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean logFilter() {
        // WAS를 띄울 때 Filter를 같이 넣어줌
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new LogFilter());    // 등록할 필터 지정
        filterFilterRegistrationBean.setOrder(1);   // 순서
        filterFilterRegistrationBean.addUrlPatterns("/*");  // 필터 모든 URL 적용

        return filterFilterRegistrationBean;
    }

}
