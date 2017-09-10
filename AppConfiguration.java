package com.auto.robot.akka;

import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfiguration {

    private static Logger logger = LoggerFactory.getLogger(AppConfiguration.class.getName());
    @Bean(destroyMethod = "shutdown")
    public ActorSystem actorSystem() {
        logger.info("初始化akkaSystem.......");
        Config config =ConfigFactory.load("akka.conf");
        ActorSystem system = ActorSystem.create("akkaSystem", config);
        /*启动 主调度 actor*/
        system.actorOf(Props.create(MainExeActor.class), "mainExeActor");
        return system;
    }
}
