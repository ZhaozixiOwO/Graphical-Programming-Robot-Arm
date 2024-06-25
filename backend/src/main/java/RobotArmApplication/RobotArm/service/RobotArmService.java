package RobotArmApplication.RobotArm.service;

import RobotArmApplication.RobotArm.domain.PointCommand;
import RobotArmApplication.RobotArm.network.RobotArmClient;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

@Service
public class RobotArmService {

    private final RobotArmClient robotArmClient;

    public RobotArmService(RobotArmClient robotArmClient) {
        this.robotArmClient = robotArmClient;

    }
    // Method to connect to the robot arm
    public String connect() {
        robotArmClient.connectToRobotArm();
        return "Connected to Robot Arm";
    }

    // Method to disconnect from the robot arm
    public String disconnect() {
        robotArmClient.disconnect();
        return "Disconnected from Robot Arm";
    }

    // Method to send the servo on command
    public void servoOn() {
        robotArmClient.servOn();
    }

    // Method to send the servo off command
    public void servoOff() {
        robotArmClient.servOff();
    }

    // Method of run action
    public void Run(){robotArmClient.Run();}

    // Method of Stop action
    public void Stop(){robotArmClient.Stop();}

    // Method of Continue action
    public void Continue(){robotArmClient.Continue();}

    // Method of Pause action
    public void Pause(){robotArmClient.Pause();}
    /**
     * Send point commands to the robot.
     *
     * @param pointCommand PointCommand object containing a list of byte arrays
     * @return Success or failure message
     */
    public String sendPointCommands(PointCommand pointCommand) {
        try {
            // Call the client method to send point commands to the robot
            robotArmClient.sendPointCommands(pointCommand);
            return "Point commands sent to the robot successfully.";
        } catch (Exception e) {
            return "Error sending point commands to the robot: " + e.getMessage();
        }
    }

    /**
     * Saves a Lua string to a local file.
     *
     * @param luaString     Lua string received from the frontend
     * @param localFilePath Local file path to save the Lua file
     */
    public static void saveLuaStringToLocalFile(String luaString, String localFilePath) {
        try {
            File localFile = new File(localFilePath);
            try (OutputStream luaFile = Files.newOutputStream(localFile.toPath())) {
                luaFile.write(luaString.getBytes());
                System.out.println("\nLua string saved to a local file successfully.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving Lua string to a local file: " + e.getMessage());
        }
    }

}
