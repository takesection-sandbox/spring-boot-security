/*
 * Copyright 2016 Pigumer Group Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.pigumer.app;

import org.seasar.doma.*;

@Entity(immutable = true)
@Table(name = "USER")
public class User {

    @Column(name = "EMAIL")
    @Id
    private final String email;

    @Column(name = "PASSWORD")
    private final String password;

    @Column(name = "VERSION")
    @Version
    private final int version;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getVersion() {
        return version;
    }

    public User(String email, String password, int version) {
        this.email = email;
        this.password = password;
        this.version = version;
    }
}
