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
package org.springframework.security.boot.acl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.acls.domain.AuditLogger;
import org.springframework.security.acls.model.AccessControlEntry;
import org.springframework.security.acls.model.AuditableAccessControlEntry;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link Sl4jAuditLogger}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@DisplayName("Sl4jAuditLogger Tests")
class Sl4jAuditLoggerTest {

    @Test
    @DisplayName("Instance can be created via constructor")
    void testInstantiation() {
        Sl4jAuditLogger instance = new Sl4jAuditLogger();
        assertThat(instance).isNotNull();
        assertThat(instance).isInstanceOf(AuditLogger.class);
    }

    @Test
    @DisplayName("logIfNeeded throws on null ACE")
    void testLogIfNeededThrowsOnNullAce() {
        Sl4jAuditLogger logger = new Sl4jAuditLogger();
        assertThatThrownBy(() -> logger.logIfNeeded(true, null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("logIfNeeded does nothing for non-auditable ACE")
    void testLogIfNeededNonAuditable() {
        Sl4jAuditLogger logger = new Sl4jAuditLogger();
        AccessControlEntry ace = mock(AccessControlEntry.class);
        // Should not throw
        logger.logIfNeeded(true, ace);
        logger.logIfNeeded(false, ace);
    }

    @Test
    @DisplayName("logIfNeeded logs when granted and auditSuccess is true")
    void testLogIfNeededGrantedAuditSuccess() {
        Sl4jAuditLogger logger = new Sl4jAuditLogger();
        AuditableAccessControlEntry ace = mock(AuditableAccessControlEntry.class);
        when(ace.isAuditSuccess()).thenReturn(true);
        when(ace.isAuditFailure()).thenReturn(false);
        // Should not throw
        logger.logIfNeeded(true, ace);
    }

    @Test
    @DisplayName("logIfNeeded logs when denied and auditFailure is true")
    void testLogIfNeededDeniedAuditFailure() {
        Sl4jAuditLogger logger = new Sl4jAuditLogger();
        AuditableAccessControlEntry ace = mock(AuditableAccessControlEntry.class);
        when(ace.isAuditSuccess()).thenReturn(false);
        when(ace.isAuditFailure()).thenReturn(true);
        // Should not throw
        logger.logIfNeeded(false, ace);
    }

    @Test
    @DisplayName("logIfNeeded does not log when granted but auditSuccess is false")
    void testLogIfNeededGrantedNoAudit() {
        Sl4jAuditLogger logger = new Sl4jAuditLogger();
        AuditableAccessControlEntry ace = mock(AuditableAccessControlEntry.class);
        when(ace.isAuditSuccess()).thenReturn(false);
        when(ace.isAuditFailure()).thenReturn(false);
        logger.logIfNeeded(true, ace);
    }

    @Test
    @DisplayName("logIfNeeded does not log when denied but auditFailure is false")
    void testLogIfNeededDeniedNoAudit() {
        Sl4jAuditLogger logger = new Sl4jAuditLogger();
        AuditableAccessControlEntry ace = mock(AuditableAccessControlEntry.class);
        when(ace.isAuditSuccess()).thenReturn(false);
        when(ace.isAuditFailure()).thenReturn(false);
        logger.logIfNeeded(false, ace);
    }
}
