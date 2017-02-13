package org.usfirst.frc.team435.robot;


import org.usfirst.frc.team435.robot.subsystems.BoardingMechanism;
import org.usfirst.frc.team435.robot.subsystems.GearMechanism;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;


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
	
	public static SpeedController gearMechanismLeftMotor;
	public static SpeedController gearMechanismRightMotor;
	public static GearMechanism gearMechanism;
	
	public static RobotDrive robotDrive;
	public static SpeedController endgame1Motor;
	public static SpeedController endgame2Motor;
	public static BoardingMechanism boardingMechanism;
	
	public static final int ENDGAME1_MOTOR = 0;
	public static final int ENDGAME2_MOTOR = 1;
	
	public static final int GEAR_MECHANISM_LEFT_MOTOR = 2;
	public static final int GEAR_MECHANISM_RIGHT_MOTOR = 3;
	public static final double EJECT_SPEED = 1; 
	
	public static final int FRONT_LEFT_MOTOR = 4;       
	public static final int FRONT_RIGHT_MOTOR = 2;
	public static final int BACK_LEFT_MOTOR = 3;
	public static final int BACK_RIGHT_MOTOR = 1;
	
	public static void init() {
		endgame1Motor = new Spark(ENDGAME1_MOTOR);
		endgame2Motor = new Spark(ENDGAME2_MOTOR);
		gearMechanismLeftMotor = new Talon(GEAR_MECHANISM_LEFT_MOTOR);
		gearMechanismRightMotor = new Talon(GEAR_MECHANISM_RIGHT_MOTOR);
		frontLeftMotor = new CANTalon(FRONT_LEFT_MOTOR);
		frontRightMotor = new CANTalon(FRONT_RIGHT_MOTOR);
		backLeftMotor = new CANTalon(BACK_LEFT_MOTOR);
		backRightMotor = new CANTalon(BACK_RIGHT_MOTOR);
		robotDrive = new RobotDrive(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor);
		boardingMechanism = new BoardingMechanism();
		gearMechanism = new GearMechanism();
	}
}
