package com.mezjh.blog.chat.server;

import com.mezjh.blog.chat.handler.WebSocketHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author ZJH
 * @date 2021/11/4 15:56
 */
@Slf4j
@Component
public class WebSocketServer {

    private final NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
    private final NioEventLoopGroup workerGroup = new NioEventLoopGroup();
    private Channel channel;
    private int port = 1156;

    @PostConstruct
    public void start() {
        ServerBootstrap server = new ServerBootstrap();
        server.group(bossGroup, workerGroup).option(ChannelOption.SO_BACKLOG, 1000)
                .option(ChannelOption.SO_SNDBUF, 32 * 1024).option(ChannelOption.SO_RCVBUF, 32 * 1024)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.RCVBUF_ALLOCATOR, AdaptiveRecvByteBufAllocator.DEFAULT)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.SO_KEEPALIVE, true).childHandler(new WebSocketChannelInitializer());
        try {
            ChannelFuture future = server.bind(port).sync();
            channel = future.channel();
        } catch (InterruptedException e) {
            log.error("websocket 未启动成功");
        }
    }

    private static class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {

        @Override
        public void initChannel(SocketChannel ch) throws Exception {
            ChannelPipeline pipeline = ch.pipeline();
            pipeline.addLast(new HttpServerCodec());
            pipeline.addLast(new ChunkedWriteHandler());
            pipeline.addLast(new HttpObjectAggregator(1024 * 64));
            pipeline.addLast(new IdleStateHandler(8, 10, 12));
            pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
            pipeline.addLast(new WebSocketHandler());
        }
    }
}
