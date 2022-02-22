package chatServer;

import chatServer.message.LoginMessage;
import chatServer.message.Message;
import chatServer.message.SingleChatMessage;
import chatServer.serverHandler.ClientInboundHandler;
import chatServer.serverHandler.MessageHandlerCodec;
import chatServer.serverHandler.MessageLengthFieldBaseFrameDecoder;
import chatServer.utils.TokenUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.HashMap;
import java.util.Scanner;

public class ChatClient {
    private int port = 7777;
    private EventLoopGroup worker;
    private Bootstrap client ;

    public ChatClient(){
        worker = new NioEventLoopGroup();
        client = new Bootstrap();
        client.channel(NioSocketChannel.class)
                .group(worker)
                .option(ChannelOption.ALLOCATOR,new PooledByteBufAllocator(false))
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new MessageLengthFieldBaseFrameDecoder(2048, Unpooled.copiedBuffer("##".getBytes())));
                        pipeline.addLast(new MessageHandlerCodec());
                        pipeline.addLast(new ClientInboundHandler());
                    }
                });
        try{
            ChannelFuture localhost = client.connect("localhost", port).sync();
            System.out.println("客户端启动成功");
            Channel channel = localhost.channel();
            Scanner scanner = new Scanner(System.in);

            HashMap<String, Object> claims = new HashMap<>();
            System.out.println("请输入用户ID：");
            String userId = scanner.nextLine();
            System.out.println("请输入密码：");
            String passwd = scanner.nextLine();
            claims.put("userId",userId);
            claims.put("passwd",passwd);
            String userToken = TokenUtil.getUserToken(claims, "1234", 1000 * 3600);
            Message loginMessage= new LoginMessage();
            loginMessage.setMagicNum(12);
            loginMessage.setVersion(1);
            loginMessage.setToken(userToken);
            loginMessage.setContent("login");
            channel.writeAndFlush(loginMessage);


            System.out.println("输入发送用户的ID");
            String id = scanner.nextLine();
            System.out.println("发送消息");
            SingleChatMessage singleChatMessage = new SingleChatMessage();
            singleChatMessage.setToken(userToken);
            singleChatMessage.setTagetUserId(id);
            singleChatMessage.setMagicNum(1);
            singleChatMessage.setVersion(1);
            while (scanner.hasNext()){
                singleChatMessage.setContent(scanner.nextLine());
                channel.writeAndFlush(singleChatMessage);
            }
            localhost.channel().closeFuture().sync();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            worker.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        ChatClient chatClient= new ChatClient();
    }
}
