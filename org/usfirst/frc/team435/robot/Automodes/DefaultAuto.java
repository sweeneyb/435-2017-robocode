package org.usfirst.frc.team435.robot.Automodes;
import org.usfirst.frc.team435.robot.commands.DriveForward;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DefaultAuto extends CommandGroup {
	
	public  DefaultAuto() {
		addSequential(new DriveForward(.5, 5));
		
	}
}
