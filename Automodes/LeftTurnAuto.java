package Automodes;

import org.usfirst.frc.team435.robot.commands.DriveForward;
import org.usfirst.frc.team435.robot.commands.TurnLeft;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftTurnAuto extends CommandGroup { 
	public LeftTurnAuto() {
		addSequential(new DriveForward(.5, 5));
		addSequential(new TurnLeft(-.5, .5));
		/*Add vision tracking code here*/
	}

}
