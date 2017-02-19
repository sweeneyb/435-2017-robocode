package org.usfirst.frc.team435.robot.Automodes;
import org.usfirst.frc.team435.robot.commands.DriveForward;
import org.usfirst.frc.team435.robot.commands.DriveToTarget;
import org.usfirst.frc.team435.robot.commands.TurnLeft;

import edu.wpi.first.wpilibj.command.CommandGroup;
public class LeftFieldAuto extends CommandGroup { 
	public LeftFieldAuto() {
		addSequential(new DriveForward(.5, 5));
		addSequential(new TurnLeft(-.5, .5));
		addSequential(new DriveToTarget());
		addSequential(new DriveForward(.5, .1));
		
	}

}
