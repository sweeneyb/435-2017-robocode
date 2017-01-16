package org.usfirst.frc.team435.robot;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.Victor;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
    public static SpeedController frontLeftMotor;
	public static SpeedController frontRightMotor;
	public static SpeedController backLeftMotor;
	public static SpeedController backRightMotor;

	public static RobotDrive robotDrive;
	public static SpeedController endgame1Motor;
	public static SpeedController endgame2Motor; 
	
	public static final int ENDGAME1_MOTOR = 0;
	public static final int ENDGAME2_MOTOR = 1;
	
	public static final int FRONT_LEFT_MOTOR = 0;       
	public static final int FRONT_RIGHT_MOTOR = 1;
	public static final int BACK_LEFT_MOTOR = 2;
	public static final int BACK_RIGHT_MOTOR = 3;
	
	public static void init() {
		endgame1Motor = new Victor(ENDGAME1_MOTOR);
		endgame2Motor = new Jaguar(ENDGAME2_MOTOR);
		frontLeftMotor = new Talon(FRONT_LEFT_MOTOR);
		frontRightMotor = new Spark(FRONT_RIGHT_MOTOR);
		backLeftMotor = new TalonSRX(BACK_LEFT_MOTOR);
		backRightMotor = new Spark(BACK_RIGHT_MOTOR);
		robotDrive = new RobotDrive(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor);
	}
}
