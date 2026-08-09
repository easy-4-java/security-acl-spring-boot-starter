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

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link BasePermissionExt}.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 1.0.0
 */
@DisplayName("BasePermissionExt Tests")
class BasePermissionExtTest {

    @Test
    @DisplayName("Single-arg constructor creates instance with given mask")
    void testSingleArgConstructor() {
        BasePermissionExt instance = new BasePermissionExt(1);
        assertThat(instance).isNotNull();
        assertThat(instance.getMask()).isEqualTo(1);
    }

    @Test
    @DisplayName("Two-arg constructor creates instance with given mask and code")
    void testTwoArgConstructor() {
        BasePermissionExt instance = new BasePermissionExt(2, 'w');
        assertThat(instance).isNotNull();
        assertThat(instance.getMask()).isEqualTo(2);
    }

    @Test
    @DisplayName("Mask values are correct for various permissions")
    void testMaskValues() {
        assertThat(new BasePermissionExt(0).getMask()).isEqualTo(0);
        assertThat(new BasePermissionExt(4).getMask()).isEqualTo(4);
        assertThat(new BasePermissionExt(8).getMask()).isEqualTo(8);
        assertThat(new BasePermissionExt(16).getMask()).isEqualTo(16);
    }
}
