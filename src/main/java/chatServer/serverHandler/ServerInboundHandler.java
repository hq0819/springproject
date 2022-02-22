package chatServer.serverHandler;

import chatServer.message.CallBackMessage;
import chatServer.message.LoginMessage;
import chatServer.message.Message;
import chatServer.message.SingleChatMessage;
import chatServer.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.ConcurrentHashMap;

public class ServerInboundHandler extends SimpleChannelInboundHandler<Message> {
    public static ConcurrentHashMap<String, Channel> channelGroup = new ConcurrentHashMap();
    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message message) throws Exception {
        String token = message.getToken();
        Claims claims = TokenUtil.verifyToken(token, "1234");
        if (message instanceof LoginMessage){
            CallBackMessage callBackMessage = new CallBackMessage();
            callBackMessage.setContent("登录成功");
            callBackMessage.setToken(token);
            ctx.writeAndFlush(callBackMessage);
            Channel channel = ctx.channel();
            channelGroup.put((String)claims.get("userId"),channel);
            group.add(channel);
        }else if (message instanceof SingleChatMessage){
            SingleChatMessage mes = (SingleChatMessage) message;
            Channel channel = channelGroup.get(mes.getTagetUserId());
            if (channel== null){
                System.out.println("数据持久");
            }
            channel.writeAndFlush(message);
        }


    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    }
}
