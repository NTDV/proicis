package ru.ntdv.proicis;

import com.github.valb3r.letsencrypthelper.tomcat.TomcatWellKnownLetsEncryptChallengeEndpointConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(TomcatWellKnownLetsEncryptChallengeEndpointConfig.class)
@SpringBootApplication
public
class Application {
public static
void main(String[] args) {
    SpringApplication.run(Application.class, args);
}
}
