package RobotArmApplication.RobotArm.network;

import RobotArmApplication.RobotArm.domain.PointCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;


/**
 * Robot Arm Client:
 * Responsible for POST and GET from the frontend as well as the Robot Arm
 * HTTP and FTP are used
 */
public class RobotArmClient {

    private static final Logger logger = LoggerFactory.getLogger(RobotArmClient.class);
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private static DataOutputStream commands;
    private static OutputStream outputStream;

    // Constructing Connection
    public void connectToRobotArm() {
        try {
            socket = new Socket("192.168.1.1", 502); // Robot IP
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Connected to Robot Arm");
            outputStream = socket.getOutputStream();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Disconnect from Robot Arm
    public void disconnect() {
        try {
            in.close();
            out.close();
            socket.close();
            System.out.println("Disconnected from Robot Arm");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // ServoOn: Turn off the robot arm
    public void servOn() {
        try {
            // ServoOn Command:
            // 00 00 00 00 00 06 02 10 00 06 00 02 04 01 01 01 01
            byte[] hexByteArray = {
                    (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                    (byte) 0x00, (byte) 0x06, (byte) 0x02, (byte) 0x10,
                    (byte) 0x00, (byte) 0x06, (byte) 0x00, (byte) 0x02,
                    (byte) 0x04, (byte) 0x01, (byte) 0x01, (byte) 0x01,
                    (byte) 0x01
            };
            // 将输出流包装为 DataOutputStream
            commands = new DataOutputStream(outputStream);
            // 使用 DataOutputStream 发送消息
            commands.write(hexByteArray);
            commands.flush();  // 刷新缓冲区确保数据被发送
            System.out.println("ServoOn Command sent: " + Arrays.toString(hexByteArray));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ServoOff: Turn off the robot arm
    public void servOff() {
        try {
            // ServoOff Command:
            // 00 00 00 00 00 06 02 10 00 06 00 02 04 00 00 00 00
            byte[] hexByteArray = {
                    (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                    (byte) 0x00, (byte) 0x06, (byte) 0x02, (byte) 0x10,
                    (byte) 0x00, (byte) 0x06, (byte) 0x00, (byte) 0x02,
                    (byte) 0x04, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                    (byte) 0x00
            };
            // 将输出流包装为 DataOutputStream
            commands = new DataOutputStream(outputStream);
            // 使用 DataOutputStream 发送消息
            commands.write(hexByteArray);
            commands.flush();  // 刷新缓冲区确保数据被发送
            System.out.println("ServoOff Command sent: " + Arrays.toString(hexByteArray));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Run() {
        try {
            // run Command:
            // 00 00 00 00 00 06 02 06 02 28 00 06
            byte[] hexByteArray = {
                    (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                    (byte) 0x00, (byte) 0x06, (byte) 0x02, (byte) 0x06,
                    (byte) 0x02, (byte) 0x28, (byte) 0x00, (byte) 0x06
            };
            // 将输出流包装为 DataOutputStream
            commands = new DataOutputStream(outputStream);
            // 使用 DataOutputStream 发送消息
            commands.write(hexByteArray);
            commands.flush();  // 刷新缓冲区确保数据被发送
            System.out.println("Run Command sent: " + Arrays.toString(hexByteArray));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Pause() {
        try {
            // Pause Command:
            // 0000 0000 0006 0206 0228 0004
            byte[] hexByteArray = {
                    (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                    (byte) 0x00, (byte) 0x06, (byte) 0x02, (byte) 0x06,
                    (byte) 0x02, (byte) 0x28, (byte) 0x00, (byte) 0x04
            };
            // 将输出流包装为 DataOutputStream
            commands = new DataOutputStream(outputStream);
            // 使用 DataOutputStream 发送消息
            commands.write(hexByteArray);
            commands.flush();  // 刷新缓冲区确保数据被发送
            System.out.println("Pause Command sent: " + Arrays.toString(hexByteArray));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Continue() {
        try {
            // Continue Command:
            // 0000 0000 0006 0206 0228 0002
            byte[] hexByteArray = {
                    (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                    (byte) 0x00, (byte) 0x06, (byte) 0x02, (byte) 0x06,
                    (byte) 0x02, (byte) 0x28, (byte) 0x00, (byte) 0x02
            };
            // 将输出流包装为 DataOutputStream
            commands = new DataOutputStream(outputStream);
            // 使用 DataOutputStream 发送消息
            commands.write(hexByteArray);
            commands.flush();  // 刷新缓冲区确保数据被发送
            System.out.println("Continue Command sent: " + Arrays.toString(hexByteArray));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Stop() {
        try {
            // Stop Command:
            // 0000 0000 0006 0206 0228 0003
            byte[] hexByteArray = {
                    (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                    (byte) 0x00, (byte) 0x06, (byte) 0x02, (byte) 0x06,
                    (byte) 0x02, (byte) 0x28, (byte) 0x00, (byte) 0x03
            };
            // 将输出流包装为 DataOutputStream
            commands = new DataOutputStream(outputStream);
            // 使用 DataOutputStream 发送消息
            commands.write(hexByteArray);
            commands.flush();  // 刷新缓冲区确保数据被发送
            System.out.println("Stop Command sent: " + Arrays.toString(hexByteArray));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Send point commands to the robot.
     *
     * @param pointCommand PointCommand object containing a list of byte arrays
     */
    public void sendPointCommands(PointCommand pointCommand) {
        try {
            List<byte[]> points = pointCommand.getPoints();

            for (byte[] point : points) {
                // Send each point to the robot
                sendByteArray(point);
            }

            logger.info("Point commands sent to the robot.");
        } catch (IOException e) {
            logger.error("Error sending point commands to the robot: {}", e.getMessage());
        }
    }

    // Helper method to send a byte array
    private void sendByteArray(byte[] byteArray) throws IOException {
        // Send the length of the byte array first
        commands.writeInt(byteArray.length);

        // Send the byte array
        outputStream.write(byteArray);

        // Flush the buffer to ensure data is sent
        outputStream.flush();
    }


}
