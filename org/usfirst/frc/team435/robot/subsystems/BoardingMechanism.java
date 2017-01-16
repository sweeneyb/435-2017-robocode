package org.usfirst.frc.team435.robot.subsystems;

import org.usfirst.frc.team435.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BoardingMechanism extends Subsystem {
    
    public void lift(int speed) {
    	RobotMap.endgame1Motor.set(speed);
    	RobotMap.endgame2Motor.set(speed);
    }

    public void initDefaultCommand() {
    }
}

