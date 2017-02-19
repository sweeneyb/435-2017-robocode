package org.usfirst.frc.team435.robot.commands;

import org.usfirst.frc.team435.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GyroTurn extends Command {

    private double stop_angle;
	private boolean is_left_turn;
	private double TURN_SPEED = 0.33;
	private double desired_angle;
	public GyroTurn(double desired_angle, boolean is_left_turn) {
       this.is_left_turn = is_left_turn;
       this.desired_angle = desired_angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        this.stop_angle = Robot.gyro.getAngle() - desired_angle;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double speed = is_left_turn ? TURN_SPEED: -TURN_SPEED;
    	Robot.driveTrain.turnLeft(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (is_left_turn && Robot.gyro.getAngle() <= stop_angle) {
    		return true;
    	}
    	if (!is_left_turn && Robot.gyro.getAngle() >= stop_angle) {
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
