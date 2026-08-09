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

import javax.sql.DataSource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.security.acls.AclPermissionEvaluator;
import org.springframework.security.acls.domain.AclAuthorizationStrategy;
import org.springframework.security.acls.domain.AuditLogger;
import org.springframework.security.acls.jdbc.LookupStrategy;
import org.springframework.security.acls.model.AclCache;
import org.springframework.security.acls.model.AclService;
import org.springframework.security.acls.model.PermissionGrantingStrategy;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.boot.biz.userdetails.UserDetailsServiceAdapter;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link SecurityAclAutoConfiguration}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@DisplayName("SecurityAclAutoConfiguration Tests")
class SecurityAclAutoConfigurationTest {

    @Test
    @DisplayName("Auto-configuration class can be instantiated")
    void testInstantiation() {
        SecurityAclAutoConfiguration configuration = new SecurityAclAutoConfiguration();
        assertThat(configuration).isNotNull();
    }

    @Test
    @DisplayName("auditLogger bean is created")
    void testAuditLogger() {
        SecurityAclAutoConfiguration config = createConfiguration();
        AuditLogger logger = config.auditLogger();
        assertThat(logger).isNotNull();
    }

    @Test
    @DisplayName("permissionGrantingStrategy bean is created")
    void testPermissionGrantingStrategy() {
        SecurityAclAutoConfiguration config = createConfiguration();
        AuditLogger logger = config.auditLogger();
        PermissionGrantingStrategy strategy = config.permissionGrantingStrategy(logger);
        assertThat(strategy).isNotNull();
    }

    @Test
    @DisplayName("aclAuthorizationStrategy bean is created")
    void testAclAuthorizationStrategy() {
        SecurityAclAutoConfiguration config = createConfiguration();
        AclAuthorizationStrategy strategy = config.aclAuthorizationStrategy();
        assertThat(strategy).isNotNull();
    }

    @Test
    @DisplayName("aclCache bean is created")
    void testAclCache() {
        SecurityAclAutoConfiguration config = createConfiguration();
        AuditLogger logger = config.auditLogger();
        PermissionGrantingStrategy strategy = config.permissionGrantingStrategy(logger);
        AclAuthorizationStrategy authStrategy = config.aclAuthorizationStrategy();
        AclCache cache = config.aclCache(strategy, authStrategy);
        assertThat(cache).isNotNull();
    }

    @Test
    @DisplayName("lookupStrategy bean is created")
    void testLookupStrategy() {
        SecurityAclAutoConfiguration config = createConfiguration();
        DataSource dataSource = mock(DataSource.class);
        AuditLogger logger = config.auditLogger();
        PermissionGrantingStrategy strategy = config.permissionGrantingStrategy(logger);
        AclAuthorizationStrategy authStrategy = config.aclAuthorizationStrategy();
        AclCache cache = config.aclCache(strategy, authStrategy);
        LookupStrategy lookupStrategy = config.lookupStrategy(dataSource, cache, authStrategy, strategy);
        assertThat(lookupStrategy).isNotNull();
    }

    @Test
    @DisplayName("aclService bean is created")
    void testAclService() {
        SecurityAclAutoConfiguration config = createConfiguration();
        DataSource dataSource = mock(DataSource.class);
        AuditLogger logger = config.auditLogger();
        PermissionGrantingStrategy strategy = config.permissionGrantingStrategy(logger);
        AclAuthorizationStrategy authStrategy = config.aclAuthorizationStrategy();
        AclCache cache = config.aclCache(strategy, authStrategy);
        LookupStrategy lookupStrategy = config.lookupStrategy(dataSource, cache, authStrategy, strategy);
        AclService aclService = config.aclService(dataSource, lookupStrategy, cache);
        assertThat(aclService).isNotNull();
    }

    @Test
    @DisplayName("methodSecurityExpressionHandler bean is created")
    void testMethodSecurityExpressionHandler() {
        SecurityAclAutoConfiguration config = createConfiguration();
        DataSource dataSource = mock(DataSource.class);
        AuditLogger logger = config.auditLogger();
        PermissionGrantingStrategy strategy = config.permissionGrantingStrategy(logger);
        AclAuthorizationStrategy authStrategy = config.aclAuthorizationStrategy();
        AclCache cache = config.aclCache(strategy, authStrategy);
        LookupStrategy lookupStrategy = config.lookupStrategy(dataSource, cache, authStrategy, strategy);
        AclService aclService = config.aclService(dataSource, lookupStrategy, cache);
        AclPermissionEvaluator evaluator = config.aclPermissionEvaluator(aclService);
        MethodSecurityExpressionHandler handler = config.methodSecurityExpressionHandler(evaluator);
        assertThat(handler).isNotNull();
    }

    @Test
    @DisplayName("aclPermissionEvaluator bean is created")
    void testAclPermissionEvaluator() {
        SecurityAclAutoConfiguration config = createConfiguration();
        DataSource dataSource = mock(DataSource.class);
        AuditLogger logger = config.auditLogger();
        PermissionGrantingStrategy strategy = config.permissionGrantingStrategy(logger);
        AclAuthorizationStrategy authStrategy = config.aclAuthorizationStrategy();
        AclCache cache = config.aclCache(strategy, authStrategy);
        LookupStrategy lookupStrategy = config.lookupStrategy(dataSource, cache, authStrategy, strategy);
        AclService aclService = config.aclService(dataSource, lookupStrategy, cache);
        AclPermissionEvaluator evaluator = config.aclPermissionEvaluator(aclService);
        assertThat(evaluator).isNotNull();
    }

    @Test
    @DisplayName("userCache bean is created")
    void testUserCache() {
        SecurityAclAutoConfiguration config = createConfiguration();
        UserCache userCache = config.userCache();
        assertThat(userCache).isNotNull();
    }

    @Test
    @DisplayName("authoritiesMapper bean is created")
    void testAuthoritiesMapper() {
        SecurityAclAutoConfiguration config = createConfiguration();
        GrantedAuthoritiesMapper mapper = config.authoritiesMapper();
        assertThat(mapper).isNotNull();
    }

    @Test
    @DisplayName("daoAuthenticationProvider bean is created")
    void testDaoAuthenticationProvider() {
        SecurityAclAutoConfiguration config = createConfiguration();
        UserDetailsServiceAdapter userDetailsService = mock(UserDetailsServiceAdapter.class);
        GrantedAuthoritiesMapper mapper = config.authoritiesMapper();
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        UserCache userCache = config.userCache();
        DaoAuthenticationProvider provider = config.daoAuthenticationProvider(userDetailsService, mapper, passwordEncoder, userCache);
        assertThat(provider).isNotNull();
    }

    private SecurityAclAutoConfiguration createConfiguration() {
        SecurityAclAutoConfiguration config = new SecurityAclAutoConfiguration();
        try {
            java.lang.reflect.Field cacheManagerField = SecurityAclAutoConfiguration.class.getDeclaredField("cacheManager");
            cacheManagerField.setAccessible(true);
            cacheManagerField.set(config, new ConcurrentMapCacheManager("ACL"));

            java.lang.reflect.Field aclPropertiesField = SecurityAclAutoConfiguration.class.getDeclaredField("aclProperties");
            aclPropertiesField.setAccessible(true);
            aclPropertiesField.set(config, new SecurityAclProperties());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return config;
    }
}
