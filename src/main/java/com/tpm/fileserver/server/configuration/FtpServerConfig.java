package com.tpm.fileserver.server.configuration;

import lombok.Data;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.ssl.SslConfigurationFactory;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Data
public class FtpServerConfig {

    Integer serverPort = 2221;

    @Bean
    public FtpServer ftpServer(){
        FtpServerFactory serverFactory = new FtpServerFactory();
        ListenerFactory factory = new ListenerFactory();

        // set the port of the listener
        factory.setPort(serverPort);
//
//        // define SSL configuration
//        SslConfigurationFactory ssl = new SslConfigurationFactory();
//        ssl.setKeystoreFile(new File("src/resources/ftpserver.jks"));
//        ssl.setKeystorePassword("password");
//
//        // set the SSL configuration for the listener
//        factory.setSslConfiguration(ssl.createSslConfiguration());
//        factory.setImplicitSsl(true);

        // replace the default listener
        serverFactory.addListener("default", factory.createListener());

        PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
        userManagerFactory.setFile(new File("myusers.properties"));

        UserManager um = userManagerFactory.createUserManager();
        serverFactory.setUserManager(um);

        BaseUser user = new BaseUser();
        user.setName("Test2");
        user.setPassword("1");
        user.setHomeDirectory("X:\\Programms");
        user.setEnabled(true);
        List<Authority> authorities = new ArrayList<Authority>();
        authorities.add(new WritePermission());
        user.setAuthorities(authorities);

        BaseUser user1 = new BaseUser();
        user1.setName("anonymous");
        user1.setPassword("");
        user1.setHomeDirectory("ftproot");
//        user1.setEnabled(true);




        // start the server
        FtpServer server = serverFactory.createServer();

        try {
            um.save(user);
            um.save(user1);
            server.start();
            System.out.println("FTP Server started on port " + serverPort);
        } catch (FtpException e) {
            e.printStackTrace();
        }


        return server;
    }
}
