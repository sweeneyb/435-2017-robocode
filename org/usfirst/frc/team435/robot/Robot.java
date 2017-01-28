
package org.usfirst.frc.team435.robot;

import static org.usfirst.frc.team435.robot.RobotMap.robotDrive;

import org.usfirst.frc.team435.robot.Automodes.DefaultAuto;
import org.usfirst.frc.team435.robot.Automodes.LeftFieldAuto;
import org.usfirst.frc.team435.robot.Automodes.RightFieldAuto;
import org.usfirst.frc.team435.robot.subsystems.BoardingMechanism;
import org.usfirst.frc.team435.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	public static SmartDashboard dashboard;
	
	public static final double END_GAME_UP_SPEED = 1;
	public static final double END_GAME_DOWN_SPEED = .5;
	
	public static final double GEAR_MECHANISM_LEFT_SPEED = 1;
	public static final double GEAR_MECHANISM_RIGHT_SPEED = -1;
	
	private static final double DEADBAND = 0;
	public static OI oi;

	Command autonomousCommand1;
	Command defaultCommand;
	SendableChooser<CommandGroup> chooser;
	public static DriveTrain driveTrain;
	public static BoardingMechanism boardingMechanism;
	
    public Robot() {
    	super();
    }
    public void operatorControl() {
        robotDrive.setSafetyEnabled(true);
        while (isOperatorControl() && isEnabled()) {
        	
        	// Use the joystick X axis for lateral movement, Y axis for forward movement, and Z axis for rotation.
        	// This sample does not use field-oriented drive, so the gyro input is set to zero.
            robotDrive.mecanumDrive_Cartesian(oi.driveStick.getAxis(OI.STRAFE_AXIS), oi.driveStick.getAxis(OI.FORWARD_AXIS), oi.driveStick.getAxis(OI.TWIST_AXIS), 0);
            
            Timer.delay(0.005);	// wait 5ms to avoid hogging CPU cycles
        }
    }

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
		RobotMap.init();
		dashboard = new SmartDashboard();
		driveTrain = new DriveTrain();
		boardingMechanism = new BoardingMechanism();
	    RobotMap.robotDrive.setInvertedMotor(MotorType.kFrontLeft, true);	// invert the left side motors
	    RobotMap.robotDrive.setInvertedMotor(MotorType.kRearLeft, true);		// you may need to change or remove this to match your robot
	    RobotMap.robotDrive.setExpiration(0.1);
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", new DefaultAuto());
        chooser.addObject("Left Turn Auto", new LeftFieldAuto());
        chooser.addObject("Right Turn Auto", new RightFieldAuto());
        try {
			SmartDashboard.putData("Auto mode", chooser);
		} catch (Exception e) {
			DriverStation.reportError(e.getMessage(), true);
		}
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
        autonomousCommand1 = (Command) chooser.getSelected();
		
		try {
			autonomousCommand1 = (Command) chooser.getSelected();
		} catch (Exception e) {
			autonomousCommand1 = defaultCommand;
			DriverStation.reportError(e.getMessage(), true);
		}

		if (autonomousCommand1 != null) autonomousCommand1.start();
		
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand1 != null) autonomousCommand1.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
	}

	public static double calc(double value) {
		if (Math.abs(value) < DEADBAND) {
			return 0;
		} else {
			return (value - (Math.abs(value) / value * DEADBAND)) / (1 - DEADBAND);
		}
	}
}
