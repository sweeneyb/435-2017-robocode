package org.usfirst.frc.team435.robot.subsystems;

import org.usfirst.frc.team435.robot.Robot;
import org.usfirst.frc.team435.robot.RobotMap;
import org.usfirst.frc.team435.robot.commands.DriveForward;
import org.usfirst.frc.team435.robot.commands.StrafeLeft;
import org.usfirst.frc.team435.robot.commands.TurnLeft;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionRunnable implements Runnable {

	private SmartDashboard dashboard = Robot.dashboard;
	private double default_value = 0;
	public static double left_x;
	public static double right_x;
	public static double left_width;
	public static double right_width;
	public static boolean isOnline = false;
	public static final int res_width = 320;
	public static int width_tolerance;
	public static int x_tolerance;
	private Preferences preferences = Preferences.getInstance();
	public static double gap_width;
	public static double STOP_WIDTH; 

	public static double lastFrame;
	public static double width_delta; // Negative when left value is smaller, positive
								// when greater right value
	public static double from_center;

	@Override
	public void run() {
		while (true) {
			get_values();
			// TODO: This isn't quite right. You might come through the loop 2x
			// before the camera puts up another frame.
			// Left for the students to address.
			if (dashboard.getNumber("frames_processed") == lastFrame) {
				isOnline = false;
				SmartDashboard.putBoolean("VisionIsWorking", false);
				DriverStation.reportError("stale vision tracking values", false);
			} else {
				lastFrame = dashboard.getNumber("frames_processed");
				isOnline = true;
				SmartDashboard.putBoolean("VisionIsWorking", true);

			}
			long fps = preferences.getLong("fps", 5);
			long delay = 1000 / fps;
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//No  better place, deal with it
			dashboard.putDouble("fl", RobotMap.frontLeftMotor.get());
			dashboard.putDouble("fr", RobotMap.frontRightMotor.get());
			dashboard.putDouble("bl", RobotMap.backLeftMotor.get());
			dashboard.putDouble("br", RobotMap.backRightMotor.get());
		}
	}

	public void get_values() {
		int frameNumber = dashboard.getInt("frames_processed");
		left_x = dashboard.getNumber("left_x", default_value);
		right_x = dashboard.getNumber("right_x", default_value);
		left_width = dashboard.getNumber("left_width", default_value);
		right_width = dashboard.getNumber("right_width", default_value);
		width_delta = (left_width - right_width);
		from_center = (((left_x - right_x) / 2) - (res_width / 2));
		gap_width = (right_x - left_x);
		STOP_WIDTH = Preferences.getInstance().getDouble("stop_width", 100);
		
		// if ((left_x == default_value) || (right_x == default_value) ||
		// (right_width == default_value)
		// || (left_width == default_value) || ((left_x == last_left_x) &&
		// (right_x == last_right_x)
		// && (right_width == last_right_width) && (left_width ==
		// last_left_width))) {
		// try {
		// Thread.sleep(25);
		// } catch (InterruptedException ex) {
		// }
		// ;
		// /* wait for 25 milliseconds */
		// i++;
		// if (i >= 10) {
		// return false;
		// }
		// } else {
		// last_left_x = left_x;
		// last_right_x = right_x;
		// last_right_width = right_width;
		// last_left_width = left_width;
		// return true;
		// }
	}

}
