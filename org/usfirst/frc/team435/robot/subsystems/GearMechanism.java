package org.usfirst.frc.team435.robot.subsystems;

import org.usfirst.frc.team435.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GearMechanism extends Subsystem {

	public GearMechanism() {
		
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public void eject() {
		RobotMap.endgame1Motor.set(RobotMap.EJECT_SPEED);
		RobotMap.endgame2Motor.set(RobotMap.EJECT_SPEED);
		SmartDashboard.putString("Gear Mechanism", "Eject");
	}
	
	public void reset() {
		RobotMap.endgame1Motor.set(-RobotMap.EJECT_SPEED);
		RobotMap.endgame2Motor.set(-RobotMap.EJECT_SPEED);
		SmartDashboard.putString("Gear Mechanism", "Reset");
	}
		
	public void stop() {
		RobotMap.endgame1Motor.set(0);
		RobotMap.endgame2Motor.set(0);
		SmartDashboard.putString("Gear Mechanism", "Stopped");
	}
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

