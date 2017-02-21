package org.usfirst.frc.team435.robot.commands;

import org.usfirst.frc.team435.robot.Robot;
import org.usfirst.frc.team435.robot.RobotMap;
import org.usfirst.frc.team435.robot.subsystems.VisionRunnable;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveToTarget extends Command {

	public DriveToTarget() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double strafe_speed = Preferences.getInstance().getDouble("strafe_speed", 0.35);
//		if (VisionRunnable.width_delta <= -VisionRunnable.width_tolerance) {
//			new TurnLeft(-0.5, 0.1);
//		} else if ((VisionRunnable.width_delta) >= VisionRunnable.width_tolerance) {
//			new TurnLeft(0.5, 0.1);
	//	} else
		if (Math.abs(VisionRunnable.from_center) >= VisionRunnable.x_tolerance) {
			if ((VisionRunnable.from_center) < 0) {
				Robot.driveTrain.strafeLeft(-strafe_speed);
			} else { /* > 0 */
				Robot.driveTrain.strafeLeft(strafe_speed);
			}
		}
		else {
			Robot.driveTrain.driveForward(.2);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if ((VisionRunnable.gap_width >= VisionRunnable.STOP_WIDTH)||(!Robot.pegSensor.get())||(!VisionRunnable.isOnline))
			//Remember to check orientation for sensor if auto quits
			//Also remember to take out ! when sensor is actually mounted
			return true;
		else
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
