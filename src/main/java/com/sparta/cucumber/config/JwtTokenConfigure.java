package com.sparta.cucumber.config;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "jwt.token")
public class JwtTokenConfigure {

    private String header;
    private String issuer;
    private String clientSecret;
    private int expirySeconds;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("header", header)
                .append("issuer", issuer)
                .append("clientSecret", clientSecret)
                .append("expirySeconds", expirySeconds)
                .toString();
    }
}