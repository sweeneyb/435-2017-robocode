package org.usfirst.frc.team435.robot.Automodes;
import org.usfirst.frc.team435.robot.commands.DriveForward;
import org.usfirst.frc.team435.robot.commands.DriveToTarget;
import org.usfirst.frc.team435.robot.commands.GyroTurn;
import org.usfirst.frc.team435.robot.commands.TurnLeft;

import edu.wpi.first.wpilibj.command.CommandGroup;
public class RightFieldAuto extends CommandGroup {
	public  RightFieldAuto() {
		addSequential(new DriveForward(.5, 1));
		addSequential(new GyroTurn(30, true));
		addSequential(new DriveToTarget());
		addSequential(new DriveForward(.5, .1));
		
	}
}
