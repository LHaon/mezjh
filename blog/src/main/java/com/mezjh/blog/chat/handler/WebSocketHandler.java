package com.mezjh.blog.chat.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.apache.http.client.utils.DateUtils;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author ZJH
 * @date 2021/11/4 14:15
 */
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private static final ConcurrentHashMap<Integer, Channel> userMap = new ConcurrentHashMap<>();
    private static final AtomicInteger userNum = new AtomicInteger(1000);

    private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        AtomicReference<String> text = new AtomicReference<>(textWebSocketFrame.text());
        channels.forEach(channel -> {
            userMap.entrySet().stream().forEach(user -> {
                String dateStr = "(" + DateUtils.formatDate(new Date(), "yyyy-MM-dd hh:mm:ss") + ")";
                if (user.getValue().equals(channelHandlerContext.channel())) {
                    text.set("用户" + user.getKey() + dateStr + ":" + textWebSocketFrame.text());
                }
            });
            channel.writeAndFlush(new TextWebSocketFrame(text.get()));
        });
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        channels.add(ctx.channel());
        userMap.put(userNum.getAndIncrement(), ctx.channel());
    }
}
