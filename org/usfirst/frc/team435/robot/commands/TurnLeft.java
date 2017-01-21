package org.usfirst.frc.team435.robot.commands;

import org.usfirst.frc.team435.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnLeft extends Command {

	private double speed;
	
    public TurnLeft(double speed, double time) {
        this.speed = speed;
        setTimeout(time);
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.turnLeft(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.turnLeft(0);
    }
    
    @Override
    public synchronized boolean isInterruptible() {
    	return false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}