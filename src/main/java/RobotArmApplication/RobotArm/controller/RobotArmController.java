package RobotArmApplication.RobotArm.controller;

import RobotArmApplication.RobotArm.domain.PointCommand;
import RobotArmApplication.RobotArm.service.FtpService;
import RobotArmApplication.RobotArm.service.RobotArmService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/robot")
public class RobotArmController {

    private final RobotArmService robotArmService;

    public RobotArmController(RobotArmService robotArmService) {
        this.robotArmService = robotArmService;
    }

    // Connect to the robot arm
    @GetMapping("/connect")
    public String connect() {
        return robotArmService.connect();
    }

    // Disconnect from the robot arm
    @GetMapping("/disconnect")
    public String disconnect() {
        return robotArmService.disconnect();
    }

    // Endpoint to turn the servo on
    @GetMapping("/servoOn")
    public String servoOn() {
        robotArmService.servoOn();
        return "Servo On Command Sent!";
    }

    // Endpoint to turn the servo off
    @GetMapping("/servoOff")
    public String servoOff() {
        robotArmService.servoOff();
        return "Servo Off Command Sent!";
    }

    // Endpoint of Run
    @GetMapping("/Run")
    public String Run() {
        robotArmService.Run();
        return "Run Action!";
    }

    // Endpoint of Stop
    @GetMapping("/Stop")
    public String Stop() {
        robotArmService.Stop();
        return "Stop Action!";
    }

    // Endpoint of Continue
    @GetMapping("/Continue")
    public String Continue() {
        robotArmService.Continue();
        return "Continue Action!";
    }

    // Endpoint of Pause
    @GetMapping("/Pause")
    public String Pause() {
        robotArmService.Pause();
        return "Pause Action!";
    }

    // Receive commands from the front-end
    @PostMapping("/command")
    public String processCommand(@RequestBody Map<String, String> requestBody) {
        String command = requestBody.get("command");
        if (command != null) {
            System.out.print("connected"); // log
            switch (command) {
                case "ServoOn":
                    robotArmService.servoOn();
                    return "Servo On Command Sent!";
                case "ServoOff":
                    robotArmService.servoOff();
                    return "Servo Off Command Sent!";
                case "Run":
                    robotArmService.Run();
                    return "Run Action!";
                case "Pause":
                    robotArmService.Pause();
                    return "Pause";
                case "Continue":
                    robotArmService.Continue();
                    return "Continue";
                case"Stop":
                    robotArmService.Stop();
                    return "Stop";

                // Add more cases for other commands if needed

                default:
                    return "Unknown command: " + command;
            }
        } else {
            return "Invalid request";
        }
    }

    /**
     * Endpoint to receive a list of points from the frontend and send them to the robot.
     *
     * @param pointCommand PointCommand object containing a list of byte arrays
     * @return Success or failure message
     */
    @PostMapping("/sendPointCommands")
    public String sendPointCommands(@RequestBody PointCommand pointCommand) {
        try {
            List<byte[]> points = pointCommand.getPoints();
            // Call the service method to send point commands to the robot
            return robotArmService.sendPointCommands(pointCommand);
        } catch (Exception e) {
            return "Error processing point commands request: " + e.getMessage();
        }
    }

    /**
     * Receive Lua string from the frontend, save it as a Lua file, and send it to the robot.
     *
     * @param luaRequest Map containing the Lua string
     * @return Success or failure message
     */
    @PostMapping("/luaFile")
    public String luaFile(@RequestBody Map<String, String> luaRequest) {
        String luaString = luaRequest.get("luaString");
        if (luaString != null) {
            System.out.printf("%s",luaString);
            // Define local file path to save the Lua file
            String localFilePath = "D:/MzKk/University-UIC/Y4S1/FYP/Back-End/lua/main.lua";

            // Save Lua string to a local file
            RobotArmService.saveLuaStringToLocalFile(luaString, localFilePath);

            // Upload Lua file through FTP and send to the robot
            FtpService.uploadLuaFile(localFilePath);
            return "Lua File saved successfully!";
        } else {
            return "Invalid request: lua file not provided.";
        }
    }


}


