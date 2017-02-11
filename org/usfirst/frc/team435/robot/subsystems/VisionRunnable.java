package org.usfirst.frc.team435.robot.subsystems;

import org.usfirst.frc.team435.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionRunnable implements Runnable {

	private double default_value = 0;
	public static double left_x;
	public static double right_x;
	public static double left_width;
	public static double right_width;
	public static boolean isOnline = false;
	public static final int res_width = 320;
	public static int width_tolerance;
	public static double x_tolerance;
	private Preferences preferences = Preferences.getInstance();
	public static double gap_width;
	public static double STOP_WIDTH = 60; 

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
			if (SmartDashboard.getNumber("frames_processed",-1) == lastFrame) {
				isOnline = false;
				SmartDashboard.putBoolean("VisionIsWorking", false);
				DriverStation.reportError("stale vision tracking values", false);
			} else {
				
				isOnline = true;
				SmartDashboard.putBoolean("VisionIsWorking", true);

			}
			lastFrame = SmartDashboard.getNumber("frames_processed",-1);
			long fps = preferences.getLong("fps", 5);
			long delay = 1000 / fps;
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//No  better place, deal with it
			SmartDashboard.putNumber("fl", RobotMap.frontLeftMotor.get());
			SmartDashboard.putNumber("fr", RobotMap.frontRightMotor.get());
			SmartDashboard.putNumber("bl", RobotMap.backLeftMotor.get());
			SmartDashboard.putNumber("br", RobotMap.backRightMotor.get());
		}
	}

	public void get_values() {
		left_x = SmartDashboard.getNumber("left_x", default_value);
		right_x = SmartDashboard.getNumber("right_x", default_value);
		left_width = SmartDashboard.getNumber("left_width", default_value);
		right_width = SmartDashboard.getNumber("right_width", default_value);
		width_delta = (right_width - left_width);
		gap_width = (right_x - left_x);
		from_center = ((((gap_width) / 2) + left_x) - (res_width / 2));
		STOP_WIDTH = Preferences.getInstance().getDouble("stop_width", 100);
		SmartDashboard.putNumber("From Center", from_center);
		SmartDashboard.putNumber("Gap Width", gap_width);
		x_tolerance = Preferences.getInstance().getDouble("x_tolerance", 20);
		
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
