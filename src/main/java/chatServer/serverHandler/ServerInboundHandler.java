package chatServer.serverHandler;

import chatServer.message.LoginMessage;
import chatServer.message.Message;
import chatServer.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerInboundHandler extends SimpleChannelInboundHandler<Message> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message message) throws Exception {
        if (message instanceof LoginMessage){
           LoginMessage loginMessage = (LoginMessage) message;
            String token = loginMessage.getToken();
            Claims claims = TokenUtil.verifyToken(token, "1234");
            System.out.println(claims);
        }
    }
}
