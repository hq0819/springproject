package chatServer.serverHandler;

import chatServer.message.Message;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class MessageHandlerCodec extends ByteToMessageCodec<Message> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Message message, ByteBuf byteBuf) throws Exception {
        ByteOutputStream bo = new ByteOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bo);
        os.writeObject(message);
        os.flush();
        byte[] bytes = bo.getBytes();
        byteBuf.writeBytes(bytes);
        byteBuf.writeBytes("##".getBytes(StandardCharsets.UTF_8));
        bo.close();
        os.close();
        ctx.writeAndFlush(byteBuf);

    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        byte[] b = new byte[byteBuf.readableBytes()];
        byteBuf.duplicate().readBytes(b);
        ByteInputStream bi = new ByteInputStream(b, 0,byteBuf.readableBytes());
        ObjectInputStream is = new ObjectInputStream(bi);
        Message message = (Message) is.readObject();
        list.add(message);
        is.close();
        bi.close();

    }
}
