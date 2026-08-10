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

import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link SecurityAclProperties}.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@DisplayName("SecurityAclProperties Tests")
class SecurityAclPropertiesTest {

    @Test
    @DisplayName("PREFIX constant has expected value")
    void testPREFIXConstant() {
        assertThat(SecurityAclProperties.PREFIX).isEqualTo("spring.security.acl");
    }

    @Test
    @DisplayName("Default values are correct")
    void testDefaultValues() {
        SecurityAclProperties props = new SecurityAclProperties();
        assertThat(props.isEnabled()).isFalse();
        assertThat(props.isForcePrincipalAsString()).isFalse();
        assertThat(props.isHideUserNotFoundExceptions()).isTrue();
        assertThat(props.isUseAuthenticationRequestCredentials()).isTrue();
        assertThat(props.isPooled()).isFalse();
        assertThat(props.isAnonymousReadOnly()).isFalse();
        assertThat(props.isDerefLinkFlag()).isFalse();
        assertThat(props.isSearchSubtree()).isFalse();
        assertThat(props.isAclClassIdSupported()).isFalse();
        assertThat(props.isForeignKeysInDatabase()).isTrue();
        assertThat(props.getSearchTimeLimit()).isZero();
    }

    @Test
    @DisplayName("enabled getter/setter works")
    void testEnabled() {
        SecurityAclProperties props = new SecurityAclProperties();
        props.setEnabled(true);
        assertThat(props.isEnabled()).isTrue();
    }

    @Test
    @DisplayName("forcePrincipalAsString getter/setter works")
    void testForcePrincipalAsString() {
        SecurityAclProperties props = new SecurityAclProperties();
        props.setForcePrincipalAsString(true);
        assertThat(props.isForcePrincipalAsString()).isTrue();
    }

    @Test
    @DisplayName("hideUserNotFoundExceptions getter/setter works")
    void testHideUserNotFoundExceptions() {
        SecurityAclProperties props = new SecurityAclProperties();
        props.setHideUserNotFoundExceptions(false);
        assertThat(props.isHideUserNotFoundExceptions()).isFalse();
    }

    @Test
    @DisplayName("useAuthenticationRequestCredentials getter/setter works")
    void testUseAuthenticationRequestCredentials() {
        SecurityAclProperties props = new SecurityAclProperties();
        props.setUseAuthenticationRequestCredentials(false);
        assertThat(props.isUseAuthenticationRequestCredentials()).isFalse();
    }

    @Test
    @DisplayName("ldapUrls getter/setter works")
    void testLdapUrls() {
        SecurityAclProperties props = new SecurityAclProperties();
        String[] urls = {"ldap://host:389"};
        props.setLdapUrls(urls);
        assertThat(props.getLdapUrls()).isEqualTo(urls);
    }

    @Test
    @DisplayName("urls getter/setter works")
    void testUrls() {
        SecurityAclProperties props = new SecurityAclProperties();
        String[] urls = {"ldap://host:389"};
        props.setUrls(urls);
        assertThat(props.getUrls()).isEqualTo(urls);
    }

    @Test
    @DisplayName("pooled getter/setter works")
    void testPooled() {
        SecurityAclProperties props = new SecurityAclProperties();
        props.setPooled(true);
        assertThat(props.isPooled()).isTrue();
    }

    @Test
    @DisplayName("groupSearchBase getter/setter works")
    void testGroupSearchBase() {
        SecurityAclProperties props = new SecurityAclProperties();
        props.setGroupSearchBase("ou=groups");
        assertThat(props.getGroupSearchBase()).isEqualTo("ou=groups");
    }

    @Test
    @DisplayName("anonymousReadOnly getter/setter works")
    void testAnonymousReadOnly() {
        SecurityAclProperties props = new SecurityAclProperties();
        props.setAnonymousReadOnly(true);
        assertThat(props.isAnonymousReadOnly()).isTrue();
    }

    @Test
    @DisplayName("referral getter/setter works")
    void testReferral() {
        SecurityAclProperties props = new SecurityAclProperties();
        props.setReferral("follow");
        assertThat(props.getReferral()).isEqualTo("follow");
    }

    @Test
    @DisplayName("providerUrl getter/setter works")
    void testProviderUrl() {
        SecurityAclProperties props = new SecurityAclProperties();
        props.setProviderUrl("ldap://host:389");
        assertThat(props.getProviderUrl()).isEqualTo("ldap://host:389");
    }

    @Test
    @DisplayName("userDn getter/setter works")
    void testUserDn() {
        SecurityAclProperties props = new SecurityAclProperties();
        props.setUserDn("cn=admin");
        assertThat(props.getUserDn()).isEqualTo("cn=admin");
    }

    @Test
    @DisplayName("password getter/setter works")
    void testPassword() {
        SecurityAclProperties props = new SecurityAclProperties();
        props.setPassword("secret");
        assertThat(props.getPassword()).isEqualTo("secret");
    }

    @Test
    @DisplayName("base getter/setter works")
    void testBase() {
        SecurityAclProperties props = new SecurityAclProperties();
        props.setBase("dc=example,dc=com");
        assertThat(props.getBase()).isEqualTo("dc=example,dc=com");
    }

    @Test
    @DisplayName("baseEnvironmentProperties getter/setter works")
    void testBaseEnvironmentProperties() {
        SecurityAclProperties props = new SecurityAclProperties();
        Map<String, Object> env = Collections.singletonMap("key", "value");
        props.setBaseEnvironmentProperties(env);
        assertThat(props.getBaseEnvironmentProperties()).isEqualTo(env);
    }

    @Test
    @DisplayName("cacheEnvironmentProperties getter/setter works")
    void testCacheEnvironmentProperties() {
        SecurityAclProperties props = new SecurityAclProperties();
        props.setCacheEnvironmentProperties(false);
        assertThat(props.isCacheEnvironmentProperties()).isFalse();
    }

    @Test
    @DisplayName("searchBase getter/setter works")
    void testSearchBase() {
        SecurityAclProperties props = new SecurityAclProperties();
        props.setSearchBase("ou=users");
        assertThat(props.getSearchBase()).isEqualTo("ou=users");
    }

    @Test
    @DisplayName("searchFilter getter/setter works")
    void testSearchFilter() {
        SecurityAclProperties props = new SecurityAclProperties();
        props.setSearchFilter("(uid={0})");
        assertThat(props.getSearchFilter()).isEqualTo("(uid={0})");
    }

    @Test
    @DisplayName("derefLinkFlag getter/setter works")
    void testDerefLinkFlag() {
        SecurityAclProperties props = new SecurityAclProperties();
        props.setDerefLinkFlag(true);
        assertThat(props.isDerefLinkFlag()).isTrue();
    }

    @Test
    @DisplayName("returningAttrs getter/setter works")
    void testReturningAttrs() {
        SecurityAclProperties props = new SecurityAclProperties();
        String[] attrs = {"cn", "mail"};
        props.setReturningAttrs(attrs);
        assertThat(props.getReturningAttrs()).isEqualTo(attrs);
    }

    @Test
    @DisplayName("searchSubtree getter/setter works")
    void testSearchSubtree() {
        SecurityAclProperties props = new SecurityAclProperties();
        props.setSearchSubtree(true);
        assertThat(props.isSearchSubtree()).isTrue();
    }

    @Test
    @DisplayName("searchTimeLimit getter/setter works")
    void testSearchTimeLimit() {
        SecurityAclProperties props = new SecurityAclProperties();
        props.setSearchTimeLimit(5000);
        assertThat(props.getSearchTimeLimit()).isEqualTo(5000);
    }

    @Test
    @DisplayName("aclClassIdSupported getter/setter works")
    void testAclClassIdSupported() {
        SecurityAclProperties props = new SecurityAclProperties();
        props.setAclClassIdSupported(true);
        assertThat(props.isAclClassIdSupported()).isTrue();
    }

    @Test
    @DisplayName("foreignKeysInDatabase getter/setter works")
    void testForeignKeysInDatabase() {
        SecurityAclProperties props = new SecurityAclProperties();
        props.setForeignKeysInDatabase(false);
        assertThat(props.isForeignKeysInDatabase()).isFalse();
    }

    @Test
    @DisplayName("deleteEntryByObjectIdentityForeignKeySql getter/setter works")
    void testDeleteEntryByObjectIdentityForeignKeySql() {
        SecurityAclProperties props = new SecurityAclProperties();
        props.setDeleteEntryByObjectIdentityForeignKeySql("custom_sql");
        assertThat(props.getDeleteEntryByObjectIdentityForeignKeySql()).isEqualTo("custom_sql");
    }

    @Test
    @DisplayName("deleteObjectIdentityByPrimaryKeySql getter/setter works")
    void testDeleteObjectIdentityByPrimaryKeySql() {
        SecurityAclProperties props = new SecurityAclProperties();
        props.setDeleteObjectIdentityByPrimaryKeySql("custom_sql");
        assertThat(props.getDeleteObjectIdentityByPrimaryKeySql()).isEqualTo("custom_sql");
    }

    @Test
    @DisplayName("classIdentityQuerySql getter/setter works")
    void testClassIdentityQuerySql() {
        SecurityAclProperties props = new SecurityAclProperties();
        props.setClassIdentityQuerySql("custom_sql");
        assertThat(props.getClassIdentityQuerySql()).isEqualTo("custom_sql");
    }

    @Test
    @DisplayName("sidIdentityQuerySql getter/setter works")
    void testSidIdentityQuerySql() {
        SecurityAclProperties props = new SecurityAclProperties();
        props.setSidIdentityQuerySql("custom_sql");
        assertThat(props.getSidIdentityQuerySql()).isEqualTo("custom_sql");
    }

    @Test
    @DisplayName("insertClassSql getter/setter works")
    void testInsertClassSql() {
        SecurityAclProperties props = new SecurityAclProperties();
        props.setInsertClassSql("custom_sql");
        assertThat(props.getInsertClassSql()).isEqualTo("custom_sql");
    }

    @Test
    @DisplayName("insertEntrySql getter/setter works")
    void testInsertEntrySql() {
        SecurityAclProperties props = new SecurityAclProperties();
        props.setInsertEntrySql("custom_sql");
        assertThat(props.getInsertEntrySql()).isEqualTo("custom_sql");
    }

    @Test
    @DisplayName("insertObjectIdentitySql getter/setter works")
    void testInsertObjectIdentitySql() {
        SecurityAclProperties props = new SecurityAclProperties();
        props.setInsertObjectIdentitySql("custom_sql");
        assertThat(props.getInsertObjectIdentitySql()).isEqualTo("custom_sql");
    }

    @Test
    @DisplayName("insertSidSql getter/setter works")
    void testInsertSidSql() {
        SecurityAclProperties props = new SecurityAclProperties();
        props.setInsertSidSql("custom_sql");
        assertThat(props.getInsertSidSql()).isEqualTo("custom_sql");
    }

    @Test
    @DisplayName("selectClassPrimaryKeySql getter/setter works")
    void testSelectClassPrimaryKeySql() {
        SecurityAclProperties props = new SecurityAclProperties();
        props.setSelectClassPrimaryKeySql("custom_sql");
        assertThat(props.getSelectClassPrimaryKeySql()).isEqualTo("custom_sql");
    }

    @Test
    @DisplayName("selectObjectIdentityPrimaryKeySql getter/setter works")
    void testSelectObjectIdentityPrimaryKeySql() {
        SecurityAclProperties props = new SecurityAclProperties();
        props.setSelectObjectIdentityPrimaryKeySql("custom_sql");
        assertThat(props.getSelectObjectIdentityPrimaryKeySql()).isEqualTo("custom_sql");
    }

    @Test
    @DisplayName("selectSidPrimaryKeySql getter/setter works")
    void testSelectSidPrimaryKeySql() {
        SecurityAclProperties props = new SecurityAclProperties();
        props.setSelectSidPrimaryKeySql("custom_sql");
        assertThat(props.getSelectSidPrimaryKeySql()).isEqualTo("custom_sql");
    }

    @Test
    @DisplayName("updateObjectIdentitySql getter/setter works")
    void testUpdateObjectIdentitySql() {
        SecurityAclProperties props = new SecurityAclProperties();
        props.setUpdateObjectIdentitySql("custom_sql");
        assertThat(props.getUpdateObjectIdentitySql()).isEqualTo("custom_sql");
    }
}
