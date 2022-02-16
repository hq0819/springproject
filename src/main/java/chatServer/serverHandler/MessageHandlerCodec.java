package chatServer.serverHandler;

import chatServer.message.LoginMessage;
import chatServer.message.Message;
import chatServer.message.MessageType;
import chatServer.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class MessageHandlerCodec extends ByteToMessageCodec<Message> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Message message, ByteBuf byteBuf) throws Exception {
        byteBuf.writeBytes(message.messageType().getCode().getBytes());
        byteBuf.writeInt(message.getVersion());
        byteBuf.writeInt(message.getMagicNum());
        byte[] bytes = message.getToken().getBytes();
        System.out.println(bytes.length);
        byteBuf.writeBytes(bytes);
        byteBuf.writeBytes(message.getContent().getBytes(StandardCharsets.UTF_8));
        byteBuf.writeBytes("##".getBytes(StandardCharsets.UTF_8));
        ctx.writeAndFlush(byteBuf);

    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        String messageType = (String) byteBuf.readCharSequence(5, Charset.defaultCharset());
        int version = byteBuf.readInt();
        int magic =  byteBuf.readInt();
        String token = (String) byteBuf.readCharSequence(128, Charset.defaultCharset());
        int i = byteBuf.readableBytes();
        Message message ;
        String content = (String) byteBuf.readCharSequence(i, Charset.defaultCharset());
        switch (MessageType.getEnumByCode(messageType)){
            case LOGINMESSAGE:
                message = new LoginMessage();
                break;
            default:
                message = null;
                break;
        }

        message.setContent(content);
        message.setToken(token);
        message.setVersion(version);
        message.setMagicNum(magic);
        list.add(message);
        Claims claims = TokenUtil.verifyToken(token, "1234");
        System.out.println(claims);

    }
}