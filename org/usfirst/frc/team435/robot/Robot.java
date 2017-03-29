
package org.usfirst.frc.team435.robot;

import static org.usfirst.frc.team435.robot.RobotMap.robotDrive;

import java.io.File;
import java.io.IOException;

import org.usfirst.frc.team435.robot.Automodes.CenterFieldAuto;
import org.usfirst.frc.team435.robot.Automodes.DefaultAuto;
import org.usfirst.frc.team435.robot.Automodes.LeftFieldAuto;
import org.usfirst.frc.team435.robot.Automodes.RightFieldAuto;
import org.usfirst.frc.team435.robot.subsystems.BoardingMechanism;
import org.usfirst.frc.team435.robot.subsystems.DriveTrain;
import org.usfirst.frc.team435.robot.subsystems.GearMechanism;
import org.usfirst.frc.team435.robot.subsystems.SafeGyro;
import org.usfirst.frc.team435.robot.subsystems.VisionRunnable;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
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

	private static final double DEADBAND = .1;
	public OI oi;
	Command autonomousCommand1;
	Command defaultCommand;
	SendableChooser<CommandGroup> chooser;
	ProcessBuilder pb;
	Preferences preferences;
	public static DriveTrain driveTrain;
	public static DigitalInput floorSensor;
	public static DigitalInput pegSensor;
	public static SafeGyro gyro;
	

	public Robot() {
		super();
	}

	public void operatorControl() {
		robotDrive.setSafetyEnabled(true);
		if (isOperatorControl() && isEnabled()) {

			// Use the joystick X axis for lateral movement, Y axis for forward
			// movement, and Z axis for rotation.
			// This sample does not use field-oriented drive, so the gyro input
			// is set to zero.
			robotDrive.mecanumDrive_Cartesian(calc(oi.driveStick.getAxis(OI.STRAFE_AXIS)),
					calc(oi.driveStick.getAxis(OI.FORWARD_AXIS)), -calc(oi.driveStick.getAxis(OI.TWIST_AXIS)), 0);
			if (oi.smoStick.getRawButton(OI.ENDGAME_UP_ID)) {
			
				RobotMap.boardingMechanism.lift(1.0);
			} 
			else {
				RobotMap.boardingMechanism.lift(0.0);
			}
			if (oi.gearMechanismEject.get()) {
			
				RobotMap.gearMechanism.eject();
			} 
			else if (oi.gearMechanismReset.get()) {
				RobotMap.gearMechanism.reset();
			}
			else {
				RobotMap.gearMechanism.stop();
			}

		}
	}

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		RobotMap.init();
		dashboard = new SmartDashboard();
		driveTrain = new DriveTrain();
		RobotMap.robotDrive.setInvertedMotor(MotorType.kFrontLeft, true);
		RobotMap.robotDrive.setInvertedMotor(MotorType.kRearLeft, true);
		chooser = new SendableChooser();
		chooser.addDefault("Default Auto", new DefaultAuto());
		chooser.addObject("Left Field Auto", new LeftFieldAuto());
		chooser.addObject("Right Field Auto", new RightFieldAuto());
		chooser.addObject("Center Field Auto", new CenterFieldAuto());
		floorSensor = new DigitalInput(0);
		pegSensor = new DigitalInput(1);
		try {
			gyro = new SafeGyro();
			gyro.calibrate();
			gyro.reset();
		}
		catch(Throwable t) {
			DriverStation.reportError(t.getMessage(), true);
		}
		

		try {
			SmartDashboard.putData("Auto mode", chooser);
		} catch (Exception e) {
			DriverStation.reportError(e.getMessage(), true);
		}
		preferences = Preferences.getInstance();

		RobotMap.robotDrive.setSafetyEnabled(false);

		// May need another stream for the DS to watch (with normal brightness)
		//Invoke vision code
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		try {
			camera.setResolution(preferences.getInt("res_width", 640), preferences.getInt("res_height", 480));
			camera.setFPS(preferences.getInt("fps", 5));
			camera.setBrightness(0);
			camera.setExposureManual(1);
			camera.setWhiteBalanceManual(4000);
		} catch (Throwable t) {
			DriverStation.reportError(t.getMessage(), true);
			t.printStackTrace();
		}
		startVision();
		new Thread(new VisionRunnable()).start();

	}

	public static void startVision() {
		ProcessBuilder pb = new ProcessBuilder("bash", "-c",
				"kill $(ps -elf | grep vision | grep -v grep | awk '{print $1}'); rm -f /home/lvuser/vision.log* ; /home/lvuser/vision http://localhost:1181/stream.mjpg");
		try {
			pb.redirectErrorStream(true);
			pb.redirectOutput(new File("/home/lvuser/vision.log"));
			pb.start();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			DriverStation.reportError(e.getMessage(), true);

		}
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		
		RobotMap.robotDrive.setSafetyEnabled(false);
		
		autonomousCommand1 = chooser.getSelected();

		try {
			autonomousCommand1 = chooser.getSelected();
		} catch (Exception e) {
			autonomousCommand1 = defaultCommand;
			DriverStation.reportError(e.getMessage(), true);
		}

		if (autonomousCommand1 != null)
			autonomousCommand1.start();

	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand1 != null)
			autonomousCommand1.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		operatorControl();

	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}

	public static double calc(double value) {
		if (Math.abs(value) < DEADBAND) {
			return 0;
		} else {
			double expValue = (value - (Math.abs(value) / value * DEADBAND)) / (1 - DEADBAND);
			return expValue * Math.abs(expValue);
		}
	}
}
