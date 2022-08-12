package be.digitalcity.giuseppe.demospringwithalexandre.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("application.jwt")
public class JwtProperties implements InitializingBean {

    @Value("${application.jwt.secret}")
    private String secret;
    @Value("${application.jwt.expiresAt}")
    private long expiresAt;
    @Value("${application.jwt.headerPrefix}")
    private String headerPrefix;
    @Value("${application.jwt.headerKey}")
    private String headerKey;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(long expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getHeaderPrefix() {
        return headerPrefix;
    }

    public void setHeaderPrefix(String headerPrefix) {
        this.headerPrefix = headerPrefix;
    }

    public String getHeaderKey() {
        return headerKey;
    }

    public void setHeaderKey(String headerKey) {
        this.headerKey = headerKey;
    }

    @Override
    public String toString() {
        return "secret = " + secret + " expiresAt = " + expiresAt + " headerPrefix = " + headerPrefix + " headerKey = " + headerKey;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(this);
    }
}
