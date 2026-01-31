package com.tpm.fileserver.server.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class IpController {

    @GetMapping("/client-ip")
    public String getClientIpAddress(HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();
        log.info("Client ip is: " + clientIp);
        return "Client IP Address: " + clientIp;
    }
}