package pers.ken.javase.networkProgramming;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 测试服务器
 */
public class Server {
    public static void main(String[] args) throws IOException {
        //传输字节
//        new ByteServer().startByteServer();
        //传输文件
        new FileServer().starFileServer();
    }
}
class ByteServer{
    void startByteServer() throws IOException {
        //new调用，传入值为服务器端口值
        //作用：创建一个服务器套接字ServerSocket,接受连接
        ServerSocket serverSocket = new ServerSocket(8888);
        //对象调用，无传入值，返回值为Socket
        //作用：阻塞线程，直到流套接字建立连接后，创建一个新的Socket套接字
        Socket accept = serverSocket.accept();
        //对象调用，无传入值，返回值为InputStream
        //作用：创建字节输入流
        InputStream in = accept.getInputStream();
        byte[] bytes = new byte[1024];
        //注意：建立套接字连接后，可以使用抽象类InputStream的read方法
        System.out.println(new String(bytes,0,in.read(bytes)));
        //对象调用，无传入值，返回值为OutputStream
        //作用：创建字节输出流
        OutputStream out = accept.getOutputStream();
        //注意：建立套接字连接后，可以使用抽象类OutputStream的write方法
        out.write("收到".getBytes());
        //关闭服务器套接字
        serverSocket.close();
        //关闭套接字
        accept.close();
    }
}
class FileServer{
    void starFileServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        Socket accept = serverSocket.accept();
        //接收图片
        InputStream in = accept.getInputStream();
        byte[] bytes = new byte[1024];
        int r;
        FileOutputStream fos = new FileOutputStream("C:/Temp/server/001.jpg");
        while((r = in.read(bytes)) != -1){
            fos.write(bytes,0,r);
        }
        //传输反馈
        OutputStream out = accept.getOutputStream();
        out.write("服务器已经完整接收文件".getBytes());
        serverSocket.close();
        accept.close();
        fos.close();
    }
}

