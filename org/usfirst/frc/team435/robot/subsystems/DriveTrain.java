package org.usfirst.frc.team435.robot.subsystems;

import org.usfirst.frc.team435.robot.RobotMap;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
    public void driveForward(double speed) {
    	RobotMap.robotDrive.mecanumDrive_Cartesian(0, -speed, 0, 0);
    	SmartDashboard.putString("Drive Status", "Drive Forward;" + speed);
    }
    public void strafeLeft(double speed) {
    	RobotMap.robotDrive.mecanumDrive_Cartesian(speed, 0, 0, 0);
    	SmartDashboard.putString("Drive Status", "Strafe;" + speed);
    }
    public void turnLeft(double speed) {
    	RobotMap.robotDrive.mecanumDrive_Cartesian(0, 0, speed, 0);
    	SmartDashboard.putString("Drive Status", "Turn;" + speed);
    }
    public void initDefaultCommand() {
    }
    public void stop() {
    	RobotMap.robotDrive.mecanumDrive_Cartesian(0, 0, 0, 0);
    	SmartDashboard.putString("Drive Status", "Stopped;");
    }
}

