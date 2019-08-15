package net.ijiangtao.tech.netty.demo.timeserver.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * client
 *
 * @author ijiangtao
 * @create 2019-07-31 14:41
 **/
@Slf4j
public class TimeClient {

    public static void main(String[] args) {

        int port = 8280;
        if (null != args && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                log.error("", e);
            }
        }

        //--------------------------------------

        try (Socket socket = new Socket("127.0.0.1", port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        ) {
            out.println("current");
            log.info("client 2 server success");

            String read = in.readLine();
            log.info("now is [{}]", read);
        } catch (IOException e) {
            log.error("", e);
        }

    }

}
