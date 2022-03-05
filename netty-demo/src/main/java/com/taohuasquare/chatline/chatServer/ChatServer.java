package com.taohuasquare.chatline.chatServer;

/**
 * @author happy
 * @since 2022-03-05
 */
public class ChatServer {
    public static void main(String[] args) throws InterruptedException {
        new NettyServer().bind(8080);
    }
}
