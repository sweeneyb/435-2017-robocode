package org.usfirst.frc.team435.robot.Automodes;
import org.usfirst.frc.team435.robot.commands.DriveForward;
import org.usfirst.frc.team435.robot.commands.DriveToTarget;
import org.usfirst.frc.team435.robot.commands.OperateGearMechanism;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.CommandGroup;
public class CenterFieldAuto extends CommandGroup {

	public  CenterFieldAuto() {
		
		double drive_time = Preferences.getInstance().getDouble("center_drive_time", 1);
		
		addSequential(new OperateGearMechanism(false, 0.4));
		addSequential(new DriveForward(.5, drive_time));
		addSequential(new DriveToTarget());
		addSequential(new DriveForward(.5, .2));
		addSequential(new OperateGearMechanism(true, 0.4));
	}
}
