package org.usfirst.frc.team435.robot.commands;

import org.usfirst.frc.team435.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GyroTurn extends Command {

	private double stop_angle;
	private boolean is_left_turn;
	private double TURN_SPEED = 0.6;
	private double desired_angle;

	public GyroTurn(double desired_angle, boolean is_left_turn) {
		this.is_left_turn = is_left_turn;
		this.desired_angle = desired_angle;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.gyro.reset();
		this.stop_angle = Robot.gyro.getAngle() - desired_angle;
		DriverStation.reportWarning("desired gyro angle" + stop_angle, false);
		DriverStation.reportWarning("Brian's weird math" + ((stop_angle + 360) % 360), false);
		TURN_SPEED = Preferences.getInstance().getDouble("turn_speed", 0.6);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double speed = is_left_turn ? TURN_SPEED : -TURN_SPEED;
		Robot.driveTrain.turnLeft(speed);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
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
	@Override
	protected void end() {
		Robot.driveTrain.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}

}
