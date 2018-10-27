package com.demo.rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * RabbitMQ 链接工具类
 */

public class ConnectionUtil {

	/** 主机 */
	@Value("spring.rabbitmq.host")
	public static String host ;
	/** 用户名 */
	@Value("spring.rabbitmq.username")
	public static String username;
	/** 密码 */
	@Value("spring.rabbitmq.password")
	public static String password;
	/** 端口号 */
	@Value("spring.rabbitmq.port")
	public static Integer port;
	/** 虚拟主机，默认为 "/" */
	public static String virtualHost = "/";

	private static final ConnectionFactory connectionFactory;

	static {
		// 创建连接工厂
		connectionFactory = new ConnectionFactory();
		connectionFactory.setHost(host);
		connectionFactory.setUsername(username);
		connectionFactory.setPassword(password);
		connectionFactory.setPort(port);
		connectionFactory.setVirtualHost(virtualHost);
	}

    /**
     * 获取一个新的连接
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    public static Connection getConnection() throws IOException, TimeoutException {
        return connectionFactory.newConnection();
    }

    /**
     * 关闭连接
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    public static void close(Connection connection) throws IOException {
        connection.close();
    }

}
