package hello.login;

import hello.login.web.filter.LogFilter;
import hello.login.web.filter.LoginCheckFilter;
import hello.login.web.interceptor.LogInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**") // 인터셉터 적용할 URL 패턴(하위 전부다)
                .excludePathPatterns("/css/**", "/*.ico", "/error");    // 인터셉터에서 제외할 패턴
    }

/*
    @Bean
    public FilterRegistrationBean logFilter() {
        // WAS를 띄울 때 Filter를 같이 넣어줌
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new LogFilter());    // 등록할 필터 지정
        filterFilterRegistrationBean.setOrder(1);   // 순서
        filterFilterRegistrationBean.addUrlPatterns("/*");  // 필터 모든 URL 적용

        return filterFilterRegistrationBean;
    }
*/

    @Bean
    public FilterRegistrationBean loginCheckFilter() {
        // WAS를 띄울 때 Filter를 같이 넣어줌
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new LoginCheckFilter());    // 등록할 필터 지정
        filterFilterRegistrationBean.setOrder(2);   // 순서
        filterFilterRegistrationBean.addUrlPatterns("/*");  // 필터 모든 URL 적용

        return filterFilterRegistrationBean;
    }
}
