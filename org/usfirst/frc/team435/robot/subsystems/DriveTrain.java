package org.usfirst.frc.team435.robot.subsystems;

import org.usfirst.frc.team435.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
    public void driveForward(int speed) {
    	RobotMap.robotDrive.mecanumDrive_Cartesian(0, -speed, 0, 0);
    }
    public void strafeLeft(int speed) {
    	RobotMap.robotDrive.mecanumDrive_Cartesian(speed, 0, 0, 0);
    }
    public void turnLeft(int speed) {
    	RobotMap.robotDrive.mecanumDrive_Cartesian(0, 0, speed, 0);
    }
    public void initDefaultCommand() {
    }
}

