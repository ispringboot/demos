package net.ijiangtao.tech.netty.demo.timeserver.bio;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * TimeServer
 *
 * @author ijiangtao
 * @create 2019-07-31 14:05
 **/
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class TimeServerHandler implements Runnable {

    private Socket socket;

    @Override
    public void run() {


        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        ) {

            String body = null;

            while (true) {
                body = in.readLine();
                log.info(body);

                if (null == body) break;

                if (body.equals("current")) {
                    String currentTime = "Server current time is:" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    out.println(currentTime);
                }

            }
        } catch (IOException e) {
            log.error("", e);
        }
    }
}
