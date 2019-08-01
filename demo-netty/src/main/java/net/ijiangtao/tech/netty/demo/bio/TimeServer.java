package net.ijiangtao.tech.netty.demo.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A BIO Demo : Time Server
 * <p>
 * 同步阻塞的时间服务器，每次接收一个请求，都需要创建一个新的线程。
 * <p>
 * 这种一个线程只能处理一个客户端连接的服务，无法应对高性能、高并发的应用场景。
 *
 * @author ijiangtao
 * @create 2019-07-31 13:57
 **/
@Slf4j
public class TimeServer {

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

        try (ServerSocket server = new ServerSocket(port);) {
            Socket socket = null;
            while (true) {
                //如果没有客户端接入，程序会阻塞在`accept()`方法上
                socket = server.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }
        } catch (IOException e) {
            log.error("", e);
        }

    }

}
