package org.usfirst.frc.team435.robot.commands;

import org.usfirst.frc.team435.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class OperateGearMechanism extends TimedCommand {
	private boolean isEject;
    public OperateGearMechanism(boolean isEject, double timeOut) {
    	super(timeOut);
    	this.isEject = isEject;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (isEject) {
    		RobotMap.gearMechanism.eject();
    	}
    	else {
    		RobotMap.gearMechanism.reset();
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.gearMechanism.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
