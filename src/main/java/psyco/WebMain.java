/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package psyco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import psyco.web.ThymleafFunctionRegister;
import psyco.web.ThymleafFunctions;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"psyco"})
@EnableJpaRepositories(basePackages = "psyco.repository")
//Exception: Not a managable class
@EntityScan("psyco.entity" )
public class WebMain {


    public static void main(String[] args) throws Exception {
        ThymleafFunctionRegister.regist(ThymleafFunctions.class);
        SpringApplication.run(WebMain.class, args);
    }

}
