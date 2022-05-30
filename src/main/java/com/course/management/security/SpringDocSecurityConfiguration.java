package com.course.management.security;

import java.lang.reflect.Field;
import java.util.Optional;

import com.course.management.dto.LoginDTO;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springdoc.core.customizers.OpenApiCustomiser;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;
import static org.springdoc.core.Constants.SPRINGDOC_ENABLED;
import static org.springdoc.core.SpringDocUtils.getConfig;


@Configuration
@ConditionalOnProperty(name = SPRINGDOC_ENABLED, matchIfMissing = true)
public class SpringDocSecurityConfiguration {

    static {
        getConfig().addRequestWrapperToIgnore(Authentication.class)
                .addResponseTypeToIgnore(Authentication.class)
                .addAnnotationsToIgnore(AuthenticationPrincipal.class);
    }


    @Bean
    @Lazy(false)
    OpenApiCustomiser springSecurityLoginEndpointCustomiser(ApplicationContext applicationContext) {

        LoginDTO loginDto = new LoginDTO();
        FilterChainProxy filterChainProxy = applicationContext.getBean(AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME, FilterChainProxy.class);
        return openAPI -> {
            for (SecurityFilterChain filterChain : filterChainProxy.getFilterChains()) {
                Optional<UsernamePasswordAuthenticationFilter> optionalFilter =
                        filterChain.getFilters().stream()
                                .filter(UsernamePasswordAuthenticationFilter.class::isInstance)
                                .map(UsernamePasswordAuthenticationFilter.class::cast)
                                .findAny();
                if (optionalFilter.isPresent()) {
                    UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter = optionalFilter.get();
                    Operation operation = new Operation();
                    Schema<?> schema = new ObjectSchema()
                            .addProperty(usernamePasswordAuthenticationFilter.getUsernameParameter(), new StringSchema())
                            .addProperty(usernamePasswordAuthenticationFilter.getPasswordParameter(), new StringSchema());
                    RequestBody requestBody = new RequestBody().content(new Content().addMediaType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE, new MediaType().schema(schema)));
                    operation.requestBody(requestBody);
                    ApiResponses apiResponses = new ApiResponses();
                    apiResponses.addApiResponse(String.valueOf(HttpStatus.OK.value()), new ApiResponse().description(HttpStatus.OK.getReasonPhrase()));
                    apiResponses.addApiResponse(String.valueOf(HttpStatus.FORBIDDEN.value()), new ApiResponse().description(HttpStatus.FORBIDDEN.getReasonPhrase()));
                    operation.responses(apiResponses);
                    operation.addTagsItem("login");
                    PathItem pathItem = new PathItem().post(operation);
                    try {
                        Field requestMatcherField = AbstractAuthenticationProcessingFilter.class.getDeclaredField("requiresAuthenticationRequestMatcher");
                        requestMatcherField.setAccessible(true);
                        AntPathRequestMatcher requestMatcher = (AntPathRequestMatcher) requestMatcherField.get(usernamePasswordAuthenticationFilter);
                        String loginPath = requestMatcher.getPattern();
                        requestMatcherField.setAccessible(false);
                        openAPI.getPaths().addPathItem(loginPath, pathItem);
                    } catch (NoSuchFieldException | IllegalAccessException | ClassCastException ignored) {
                        // Exception escaped
                        LOGGER.trace(ignored.getMessage());
                    }
                }
            }
        };
    }
}