package chatServer.serverHandler;

import chatServer.message.LoginMessage;
import chatServer.message.Message;
import chatServer.utils.TokenUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;

public class ClientInboundHandler extends SimpleChannelInboundHandler<Message> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message message) throws Exception {
        if (message instanceof LoginMessage){
            System.out.println(message);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("userId","hq");
        claims.put("passwd","123");
        String userToken = TokenUtil.getUserToken(claims, "1234", 1000 * 10);
        Message loginMessage= new LoginMessage();
        loginMessage.setMagicNum(12);
        loginMessage.setVersion(1);
        loginMessage.setToken(userToken);
        loginMessage.setContent("login");
        ctx.write(loginMessage);
    }
}
