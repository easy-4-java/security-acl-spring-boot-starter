/*
 * Copyright (c) 2018, hiwepy (https://github.com/hiwepy).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.springframework.security.boot;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.boot.biz.authentication.AuthenticatingFailureCounter;
import org.springframework.security.boot.biz.authentication.PostRequestAuthenticationEntryPoint;
import org.springframework.security.boot.biz.authentication.PostRequestAuthenticationFailureHandler;
import org.springframework.security.boot.biz.authentication.PostRequestAuthenticationSuccessHandler;
import org.springframework.security.boot.biz.authentication.captcha.CaptchaResolver;
import org.springframework.security.boot.biz.userdetails.UserDetailsServiceAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link SecurityAclFilterConfiguration}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@DisplayName("SecurityAclFilterConfiguration Tests")
class SecurityAclFilterConfigurationTest {

    @Test
    @DisplayName("Instance can be created via constructor")
    void testInstantiation() {
        SecurityAclFilterConfiguration instance = new SecurityAclFilterConfiguration();
        assertThat(instance).isNotNull();
    }

    @Test
    @DisplayName("AclSecurityFilterChainConfiguration can be created via constructor")
    void testAclSecurityFilterChainConfiguration() {
        SecurityBizProperties bizProperties = new SecurityBizProperties();
        SecurityAclProperties aclProperties = new SecurityAclProperties();

        ObjectProvider<ObjectMapper> objectMapperProvider = mockProvider(null);
        ObjectProvider<UserDetailsServiceAdapter> userDetailsServiceProvider = mockProvider(null);
        ObjectProvider<PasswordEncoder> passwordEncoderProvider = mockProvider(null);
        ObjectProvider<SessionRegistry> sessionRegistryProvider = mockProvider(null);
        ObjectProvider<RememberMeServices> rememberMeServicesProvider = mockProvider(null);
        ObjectProvider<PostRequestAuthenticationEntryPoint> entryPointProvider = mockProvider(null);
        ObjectProvider<DaoAuthenticationProvider> authProvider = mockProvider(null);
        ObjectProvider<PostRequestAuthenticationSuccessHandler> successHandlerProvider = mockProvider(null);
        ObjectProvider<PostRequestAuthenticationFailureHandler> failureHandlerProvider = mockProvider(null);
        ObjectProvider<CaptchaResolver> captchaResolverProvider = mockProvider(null);
        ObjectProvider<AuthenticatingFailureCounter> failureCounterProvider = mockProvider(null);
        ObjectProvider<CsrfTokenRepository> csrfProvider = mockProvider(null);
        ObjectProvider<InvalidSessionStrategy> invalidSessionProvider = mockProvider(null);
        ObjectProvider<RequestCache> requestCacheProvider = mockProvider(null);
        ObjectProvider<SecurityContextLogoutHandler> logoutHandlerProvider = mockProvider(null);
        ObjectProvider<SessionAuthenticationStrategy> sessionAuthStrategyProvider = mockProvider(null);
        ObjectProvider<SessionInformationExpiredStrategy> expiredSessionProvider = mockProvider(null);

        SecurityAclFilterConfiguration.AclSecurityFilterChainConfiguration config =
                new SecurityAclFilterConfiguration.AclSecurityFilterChainConfiguration(
                        objectMapperProvider,
                        userDetailsServiceProvider,
                        passwordEncoderProvider,
                        sessionRegistryProvider,
                        rememberMeServicesProvider,
                        aclProperties,
                        entryPointProvider,
                        authProvider,
                        successHandlerProvider,
                        failureHandlerProvider,
                        captchaResolverProvider,
                        failureCounterProvider,
                        csrfProvider,
                        invalidSessionProvider,
                        requestCacheProvider,
                        logoutHandlerProvider,
                        sessionAuthStrategyProvider,
                        expiredSessionProvider
                );

        assertThat(config).isNotNull();
    }

    @SuppressWarnings("unchecked")
    private <T> ObjectProvider<T> mockProvider(T value) {
        ObjectProvider<T> provider = mock(ObjectProvider.class);
        when(provider.getIfAvailable()).thenReturn(value);
        return provider;
    }
}
