package org.usfirst.frc.team435.robot.Automodes;
import org.usfirst.frc.team435.robot.commands.DriveForward;
import org.usfirst.frc.team435.robot.commands.GyroTurn;
import org.usfirst.frc.team435.robot.commands.OperateGearMechanism;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.CommandGroup;
public class LeftFieldAuto extends CommandGroup { 
	
	public LeftFieldAuto() {
		
		double left_gyro_turn = Preferences.getInstance().getDouble("left_gyro_turn", 30);
		double left_drive_time = Preferences.getInstance().getDouble("left_drive_time", 1.5);
		double left_drive_time_after_turn = Preferences.getInstance().getDouble("left_drive_time_after_turn", 0.5);
		
		addSequential(new OperateGearMechanism(false, 0.4));
		addSequential(new DriveForward(.5, left_drive_time));
		addSequential(new GyroTurn(left_gyro_turn, false));

		addSequential(new DriveForward(.5, .2));
	}

}
