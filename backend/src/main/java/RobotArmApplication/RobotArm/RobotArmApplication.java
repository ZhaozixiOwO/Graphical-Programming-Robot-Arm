package RobotArmApplication.RobotArm;

import RobotArmApplication.RobotArm.network.RobotArmClient;
import RobotArmApplication.RobotArm.service.RobotArmService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RobotArmApplication {

    public static void main(String[] args) {
        SpringApplication.run(RobotArmApplication.class, args);
    }

    // Bean for RobotArmClient
    @Bean
    public RobotArmClient robotArmClient() {
        return new RobotArmClient();
    }

    // Bean for RobotArmService
    @Bean
    public RobotArmService robotArmService(RobotArmClient robotArmClient) {
        return new RobotArmService(robotArmClient);
    }

    // ApplicationRunner to connect to the robot arm on startup
    @Bean
    public ApplicationRunner connectToRobotArmOnStartup(RobotArmService robotArmService) {
        return args -> {
            // Connect to the Robot Arm on startup
            robotArmService.connect();
        };
    }
}
