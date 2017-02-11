package org.usfirst.frc.team435.robot.Automodes;
import org.usfirst.frc.team435.robot.commands.DriveForward;
import org.usfirst.frc.team435.robot.commands.DriveToTarget;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterFieldAuto extends CommandGroup {
	
	public  CenterFieldAuto() {
		addSequential(new DriveForward(.5, 2));
		addSequential(new DriveToTarget());
		
		
		//Remember to write new one with just passing tape
		
	}
}
