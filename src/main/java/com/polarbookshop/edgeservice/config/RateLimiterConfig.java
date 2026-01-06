package com.polarbookshop.edgeservice.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.security.Principal;

@Configuration
public class RateLimiterConfig {

    @Bean
    public KeyResolver keyResolver() {
        return exchange -> exchange.getPrincipal() // 从当前请求（exchange）中获取当前的认证用户（principal）
                .map(Principal::getName) // 从principal中抽取用户名
                .defaultIfEmpty("anonymous"); // 如果请求未经认证，使用anonymous作为限流的默认键
    }
}
