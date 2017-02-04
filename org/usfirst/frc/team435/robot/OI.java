package org.usfirst.frc.team435.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.buttons.JoystickButton;;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick smoStick;
	public Joystick driveStick;
	
	public JoystickButton halfSpeedButton;
	public JoystickButton gearMechanismOut;
	public JoystickButton gearMechanismReset;
	public JoystickButton endgameUpButton;
	public JoystickButton endgameDownButton;
	
	
	public static final int DRIVESTICK_PORT = 1;
	public static final int HALF_SPEED_BUTTON_ID = 1;
	
	public static final int SMO_STICK_PORT = 2;
	public static final int GEAR_MECHANISM_OUT_ID = 1;
	public static final int GEAR_MECHANISM_RESET_ID = 5;
	public static final int ENDGAME_UP_ID = 3;
	
	public static final AxisType FORWARD_AXIS = AxisType.kY;
	public static final AxisType STRAFE_AXIS = AxisType.kX;
	public static final AxisType TWIST_AXIS = AxisType.kTwist;
	
	
	
	public OI(){
		driveStick = new Joystick(DRIVESTICK_PORT);
		smoStick = new Joystick(SMO_STICK_PORT);
		
		halfSpeedButton = new JoystickButton(driveStick, HALF_SPEED_BUTTON_ID);
		gearMechanismOut = new JoystickButton(smoStick, GEAR_MECHANISM_OUT_ID);
		gearMechanismReset = new JoystickButton(smoStick, GEAR_MECHANISM_RESET_ID);
		endgameUpButton = new JoystickButton(smoStick, ENDGAME_UP_ID);
	
	}
	
}