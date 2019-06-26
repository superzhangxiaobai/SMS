package com.xiaobai.util;

import gnu.io.*;

import java.util.Enumeration;
import java.util.HashSet;

public class CommUtils{
    /**
     * @Description:列出所有可用串口
     * @author:dengchaoqun
     * @date:2015-8-29 上午11:34:04
     */
    public static void listPorts() {
        HashSet<CommPortIdentifier> portSet = getAvailableSerialPorts();
        for (CommPortIdentifier comm : portSet) {
            System.out.println(comm.getName() + " - " + getPortTypeName(comm.getPortType()));
        }
    }

    /**
     * @Description:列出所有通信端口
     * @author:dengchaqun
     * @date:2015-8-29 下午2:06:17
     */
    @SuppressWarnings("unchecked")
    public static void listCommPorts() {
        CommPortIdentifier.getPortIdentifiers();
        /*
         * 不带参数的getPortIdentifiers方法可以获得一个枚举对象，该对象包含了
         * 系统中每个端口的CommPortIdentifier对象。注意这里的端口不仅仅是指串口，也包括并口。
         * 这个方法还可以带参数，getPortIdentifiers(CommPort)获得已经被应用程序打开的端口
         * 相对应的CommPortIdentifier对象。getPortIdentifier(String portName)
         * 获取指定端口名（比如“COM1”）的CommPortIdentifier对象。
         */
        java.util.Enumeration<CommPortIdentifier> portEnum = CommPortIdentifier.getPortIdentifiers();
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier portIdentifier = portEnum.nextElement();
            System.out.println(portIdentifier.getName() + " - " + getPortTypeName(portIdentifier.getPortType()));
        }
    }

    /**
     * @Description:获取通信端口类型名称
     * @author:Lu
     * @date:2015-8-29 上午11:35:32
     */
    public static String getPortTypeName(int portType) {
        switch (portType) {
            case CommPortIdentifier.PORT_I2C:
                return "I2C";
            case CommPortIdentifier.PORT_PARALLEL: // 并口
                return "Parallel";
            case CommPortIdentifier.PORT_RAW:
                return "Raw";
            case CommPortIdentifier.PORT_RS485: // RS485端口
                return "RS485";
            case CommPortIdentifier.PORT_SERIAL: // 串口
                return "Serial";
            default:
                return "unknown type";
        }
    }

    /**
     * @Description:获取所有可用的串口集合
     * @author:dengchaoqun
     * @date:2015-8-29 上午11:37:54
     */
    @SuppressWarnings("unchecked")
    public static HashSet<CommPortIdentifier> getAvailableSerialPorts() {
        HashSet<CommPortIdentifier> h = new HashSet<CommPortIdentifier>();
        Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();
        while (portList.hasMoreElements()) {
            CommPortIdentifier com = (CommPortIdentifier) portList.nextElement();
            switch (com.getPortType()) {
                case CommPortIdentifier.PORT_SERIAL:
                    try {
                        // open:（应用程序名【随意命名】，阻塞时等待的毫秒数）
                        /*
                         * open方法打开通讯端口，获得一个CommPort对象，它使程序独占端口。
                         * 如果端口正被其他应用程序占用，将使用CommPortOwnershipListener事件机制
                         * 传递一个PORT_OWNERSHIP_REQUESTED事件。
                         * 每个端口都关联一个InputStream和一个OutputStream,如果端口是用
                         * open方法打开的，那么任何的getInputStream都将返回相同的数据流对象，除非 有close被调用。
                         */
                        CommPort thePort = com.open(Object.class.getSimpleName(), 50);
                        thePort.close();
                        h.add(com);
                    } catch (PortInUseException e) {
                        // 不可用串口
                        System.out.println("Port, " + com.getName() + ", is in use.");
                    } catch (Exception e) {
                        System.err.println("Failed to open port " + com.getName());
                        e.printStackTrace();
                    }
            }
        }
        return h;
    }
    /**
     * 接口
     * CommDriver可负载设备（the loadable device）驱动程序接口的一部分
     *
     * CommPortOwnershipListener传递各种通讯端口的所有权事件
     *
     * ParallelPortEventListener传递并行端口事件
     *
     * SerialPortEventListener传递串行端口事件
     *
     * 类
     * CommPort通讯端口
     *
     * CommPortIdentifier通讯端口管理
     *
     * ParallelPort并行通讯端口
     *
     * ParallelPortEvent并行端口事件
     *
     * SerialPortRS-232串行通讯端口
     *
     * SerialPortEvent 串行端口事件
     *
     * 异常类
     * NoSuchPortException当驱动程序不能找到指定端口时抛出
     *
     * PortInUseException当碰到指定端口正在使用中时抛出
     *
     * UnsupportedCommOperationException驱动程序不允许指定操作时抛出
     *
     * CommPortIdentifier类
     * 这个类主要用于对通信端口进行管理和设置，是对端口进行访问控制的核心类，主要包括以下方法：
     *
     * addPortName(String,int, CommDriver) 添加端口名到端口列表里
     *
     * addPortOwnershipListener(CommPortOwnershipListener)添加端口拥有的监听器
     *
     * removePortOwnershipListener(CommPortOwnershipListener)移除端口拥有的监听器
     *
     * getCurrentOwner()获取当前占有端口的对象或应用程序
     *
     * getName()获取端口名称
     *
     * getPortIdentifier(CommPort)获取指定打开的端口的CommPortIdentifier类型对象
     *
     * getPortIdentifier(String)获取以参数命名的端口的CommPortIdentifier类型对象
     *
     * getPortIdentifiers()获取系统中的端口列表
     *
     * getPortType()获取端口的类型
     *
     * isCurrentlyOwned()判断当前端口是否被占用
     *
     * open(FileDescriptor)用文件描述的类型打开端口
     *
     * open(String,int) 打开端口，两个参数：程序名称，延迟时间(毫秒数)
     *
     * SerialPort类
     * 这个类用于描述一个RS-232串行通信端口的底层接口，它定义了串口通信所需的最小功能集。通过它，用户可以直接对串口进行读、写及设置工作。
     *
     * SerialPort类中关于串口参数的静态成员变量说明:
     *
     * DATABITS_5 数据位为5
     *
     * DATABITS_6 数据位为6
     *
     * DATABITS_7 数据位为7
     *
     * DATABITS_8 数据位为8
     *
     * PARITY_NONE 空格检验
     *
     * PARITY_ODD 奇检验
     *
     * PARITY_EVEN 偶检验
     *
     * PARITY_MARK 标记检验
     *
     * PARITY_SPACE 无检验
     *
     * STOPBITS_1 停止位为1
     *
     * STOPBITS_2 停止位为2
     *
     * STOPBITS_1_5 停止位为1.5
     *
     *
     *
     * SerialPort类中关于串口参数的方法说明:
     *
     * getBaudRate()得到波特率
     *
     * getParity()得到检验类型
     *
     * getDataBits()得到数据位数
     *
     * getStopBits()得到停止位数
     *
     * setSerialPortParams(int,int, int, int) 设置串口参数依次为(波特率,数据位,停止位,奇偶检验)
     *
     *
     *
     * SerialPort类中关于事件的静态成员变量说明：
     *
     * BI Break interrupt 通讯中断
     *
     * FE Framing error 帧错误
     *
     * CD Carrier detect 载波侦听
     *
     * OE Overrun error 溢位错误
     *
     * CTS Clear to send 清除发送
     *
     * PE Parity error 奇偶检验错误
     *
     * DSR Data set ready 数据设备准备好
     *
     * RI Ring indicator 响铃侦测
     *
     * DATA_AVAILABLE 串口中的可用数据
     *
     * OUTPUT_BUFFER_EMPTY 输出缓冲区已清空
     *
     *
     *
     * SerialPort类中关于事件的方法说明：
     *
     * isCD()是否有载波
     *
     * isCTS()是否清除以传送
     *
     * isDSR()数据是否备妥
     *
     * isDTR()是否数据端备妥
     *
     * isRI()是否响铃侦测
     *
     * isRTS()是否要求传送
     *
     * addEventListener(SerialPortEventListener)向SerialPort对象中添加串口事件监听器
     *
     * removeEventListener()移除SerialPort对象中的串口事件监听器
     *
     * notifyOnBreakInterrupt(boolean)设置中断事件true有效,false无效
     *
     * notifyOnCarrierDetect(boolean)设置载波监听事件true有效,false无效
     *
     * notifyOnCTS(boolean)设置清除发送事件true有效,false无效
     *
     * notifyOnDataAvailable(boolean)设置串口有数据的事件true有效,false无效
     *
     * notifyOnDSR(boolean)设置数据备妥事件true有效,false无效
     *
     * notifyOnFramingError(boolean)设置发生错误事件true有效,false无效
     *
     * notifyOnOutputEmpty(boolean)设置发送缓冲区为空事件true有效,false无效
     *
     * notifyOnParityError(boolean)设置发生奇偶检验错误事件true有效,false无效
     *
     * notifyOnRingIndicator(boolean)设置响铃侦测事件true有效,false无效
     *
     * getEventType()得到发生的事件类型返回值为int型
     *
     * sendBreak(int)设置中断过程的时间，参数为毫秒值
     *
     * setRTS(boolean)设置或清除RTS位
     *
     * setDTR(boolean)设置或清除DTR位
     *
     *
     *
     * SerialPort中的其他常用方法说明：
     *
     * close()关闭串口
     *
     * getOutputStream()得到OutputStream类型的输出流
     *
     * getInputStream()得到InputStream类型的输入流
     *
     */
}
