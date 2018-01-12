
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.springnetty.core.channel.HttpChannelInitializer;
import com.springnetty.web.config.SystemConfig;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
  * @ClassName: HttpServer
  * @Description: HttpServer
  * @author xupengtao
  * @date 2018年1月12日 下午9:59:11
  *
 */
public class HttpServer {
    
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);
    
    private static void startHttpServer() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).handler(new LoggingHandler(LogLevel.INFO)).childHandler(new HttpChannelInitializer());

            LOGGER.info("Netty server has started on port : " + SystemConfig.getInstance().getHttpPort());

            Channel ch = b.bind(SystemConfig.getInstance().getHttpPort()).sync().channel();
            ch.closeFuture().sync();
        } catch (Exception e) {
            LOGGER.error("Netty server start fail.", e);
        }
        finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        startHttpServer();
    }
}
