package com.tpm.fileserver.server.servers;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.ListenerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FtpServerConfig {

    @Bean
    public FtpServer ftpServer(){
        FtpServerFactory serverFactory = new FtpServerFactory();
        ListenerFactory factory = new ListenerFactory();

        // set the port of the listener
        factory.setPort(2221);

        // replace the default listener
        serverFactory.addListener("default", factory.createListener());

        // start the server
        FtpServer server = serverFactory.createServer();

        try {
            server.start();
            System.out.println("FTP Server started on port 21");
        } catch (FtpException e) {
            e.printStackTrace();
        }


        return server;
    }
}
