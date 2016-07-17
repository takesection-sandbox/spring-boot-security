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

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableAutoConfiguration
@Controller
public class Application {

    static class Customizer implements EmbeddedServletContainerCustomizer {
        @Override
        public void customize(ConfigurableEmbeddedServletContainer container) {
            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
        }
    }

    @Value("${jp.pigumer.app.number}")
    private int number;

    @Autowired
    ObjectFactory<SessionData> sessionDataFactory;

    @RequestMapping("/")
    public String index() {
        SessionData data = sessionDataFactory.getObject();
        data.message = String.valueOf(number);
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @Bean
    Customizer customizer() {
        return new Customizer();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
}
