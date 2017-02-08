package org.usfirst.frc.team435.robot.Automodes;
import org.usfirst.frc.team435.robot.commands.DriveForward;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterFieldAuto extends CommandGroup {
	
	public  CenterFieldAuto() {
		addSequential(new DriveForward(.5, 5));
		
		
		
		//Remember to write new one with just passing tape
		
	}
}
