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

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class Application {
    
    @RequestMapping("/")
    public String index(HttpServletRequest request, Model model) {
        List<String> headers = new ArrayList<>();
        Enumeration<String> enumHeaders = request.getHeaderNames();
        while (enumHeaders.hasMoreElements()) {
            String name = enumHeaders.nextElement();
            String value = request.getHeader(name);
            headers.add(String.format("%s: %s", name, value));
        }
        model.addAttribute("headers", headers);
        return "index";
    }
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
}
