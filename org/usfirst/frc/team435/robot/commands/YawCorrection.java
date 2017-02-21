package org.usfirst.frc.team435.robot.commands;

import org.usfirst.frc.team435.robot.Robot;
import org.usfirst.frc.team435.robot.subsystems.VisionRunnable;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class YawCorrection extends Command {

	public YawCorrection() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (VisionRunnable.width_delta <= -VisionRunnable.width_tolerance) {
			Robot.driveTrain.turnLeft(-0.5);
		} else if ((VisionRunnable.width_delta) >= VisionRunnable.width_tolerance) {
			Robot.driveTrain.turnLeft(0.5);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (Math.abs(VisionRunnable.width_delta) >= VisionRunnable.width_tolerance) {
			return false;
		}
		else {
			return true;
		}
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
