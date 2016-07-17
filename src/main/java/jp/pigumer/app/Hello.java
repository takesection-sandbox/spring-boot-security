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

import akka.actor.ActorRef;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;
import java.util.UUID;

@Controller
public class Hello {

    @Autowired
    ObjectFactory<RequestData> requestDataFactory;

    @Autowired
    Akka akka;

    @RequestMapping("/hello")
    public ModelAndView hello(@AuthenticationPrincipal User user,
                              @ModelAttribute Form form) {
        requestDataFactory.getObject().message = UUID.randomUUID().toString();

        ModelAndView mv = new ModelAndView();
        mv.addObject("user", user.getUsername());
        mv.addObject("form", form);
        mv.setViewName("hello");
        return mv;
    }

    @RequestMapping("/tell")
    @ResponseBody
    public void tell() {
        ActorRef ref = akka.system.actorFor("user/sample");
        ref.tell(Objects.toString(UUID.randomUUID()), ActorRef.noSender());
    }



}
