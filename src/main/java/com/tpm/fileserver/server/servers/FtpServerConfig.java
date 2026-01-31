package com.tpm.fileserver.server.servers;

import lombok.Data;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.ListenerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

        // replace the default listener
        serverFactory.addListener("default", factory.createListener());

        // start the server
        FtpServer server = serverFactory.createServer();

        try {
            server.start();
            System.out.println("FTP Server started on port " + serverPort);
        } catch (FtpException e) {
            e.printStackTrace();
        }


        return server;
    }
}
