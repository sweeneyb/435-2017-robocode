package org.usfirst.frc.team435.robot.commands;

import org.usfirst.frc.team435.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForward extends Command {

	private double speed;
	private boolean stop_on_sensor = false;
    public DriveForward(double speed, double time) {
        this.speed = speed;
        setTimeout(time);
        requires(Robot.driveTrain);
    }
    public DriveForward(double speed, double time, boolean stop_on_sensor) {
        this.speed = speed;
        setTimeout(time);
        requires(Robot.driveTrain);
        this.stop_on_sensor = stop_on_sensor;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.driveForward(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (stop_on_sensor) {
        	if (Robot.floorSensor.get() || isTimedOut()) {
        		return true;
        	}
        	else {
        		return false;
        	}
        }
        else {
        	return isTimedOut();
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.driveForward(0);
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
