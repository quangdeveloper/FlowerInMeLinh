package vn.chohoa.flower.logconfig;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class RequestLoggingFilterConfig {


    @Bean
    public CommonsRequestLoggingFilter loggingFilter() {

        System.out.println(" \n CommonsRequestLoggingFilter run\n");
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(false);
        filter.setAfterMessagePrefix("REQUEST DATA :");
        return filter;
    }

    @Bean
    public FilterRegistrationBean<CustomURLFilter> filterFilterRegistrationBean() {

        System.out.println("\n FilterRegistrationBean \n");
        FilterRegistrationBean<CustomURLFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        CustomURLFilter customURLFilter = new CustomURLFilter();

        filterRegistrationBean.setFilter(customURLFilter);
        filterRegistrationBean.setOrder(2);// set precedence: độ ưu tiên (proid)

        return filterRegistrationBean;
    }
}
