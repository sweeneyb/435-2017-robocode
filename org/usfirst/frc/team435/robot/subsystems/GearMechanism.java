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
		RobotMap.gearMechanismLeftMotor.set(RobotMap.EJECT_SPEED);
		RobotMap.gearMechanismRightMotor.set(RobotMap.EJECT_SPEED);
		SmartDashboard.putString("Gear Mechanism", "Eject");
	}
	
	public void reset() {
		RobotMap.gearMechanismLeftMotor.set(-RobotMap.EJECT_SPEED);
		RobotMap.gearMechanismRightMotor.set(-RobotMap.EJECT_SPEED);
		SmartDashboard.putString("Gear Mechanism", "Reset");
	}
		
	public void stop() {
		RobotMap.gearMechanismLeftMotor.set(0);
		RobotMap.gearMechanismRightMotor.set(0);
		SmartDashboard.putString("Gear Mechanism", "Stopped");
	}
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

