package org.usfirst.frc.team435.robot.Automodes;
import org.usfirst.frc.team435.robot.commands.DriveForward;
import org.usfirst.frc.team435.robot.commands.DriveToTarget;
import org.usfirst.frc.team435.robot.commands.OperateGearMechanism;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterFieldAuto extends CommandGroup {
	
	public  CenterFieldAuto() {
		addSequential(new OperateGearMechanism(true, 0.2));
		addSequential(new DriveForward(.5, .5));
		addSequential(new DriveToTarget());
		addSequential(new DriveForward(.5, .1));
		//Remember to write new one with just passing tape
	}
}
