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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class UserDaoTest {

    @Autowired
    UpdateUser sut;

    @Autowired
    UserDao dao;

    @Test
    public void test() {
        try {
            sut.update();
        } catch (OptimisticLockingFailureException e) {
            e.printStackTrace();
        }

        Optional<String> pwd = dao.password("test@localhost");
        pwd.ifPresent(password -> System.out.println(password));
        assertThat(pwd.isPresent(), is(false));
    }
}
