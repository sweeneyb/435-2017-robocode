package org.usfirst.frc.team435.robot.Automodes;
import org.usfirst.frc.team435.robot.commands.DriveForward;
import org.usfirst.frc.team435.robot.commands.TurnLeft;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightFieldAuto extends CommandGroup {
	public  RightFieldAuto() {
		addSequential(new DriveForward(.5, 5));
		addSequential(new TurnLeft(.5, .5));
		/* start ring light, camera and vision tracking here */
		
	}
}
