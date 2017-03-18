package org.usfirst.frc.team435.robot.Automodes;
import org.usfirst.frc.team435.robot.commands.DriveForward;
import org.usfirst.frc.team435.robot.commands.DriveToTarget;
import org.usfirst.frc.team435.robot.commands.GyroTurn;
import org.usfirst.frc.team435.robot.commands.OperateGearMechanism;
import org.usfirst.frc.team435.robot.commands.TurnLeft;
import org.usfirst.frc.team435.robot.commands.YawCorrection;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.CommandGroup;
public class RightFieldAuto extends CommandGroup {
	public  RightFieldAuto() {
		
		double right_gyro_turn = Preferences.getInstance().getDouble("right_gyro_turn", 30);
		double right_drive_time = Preferences.getInstance().getDouble("right_drive_time", 1);
		
		addSequential(new OperateGearMechanism(false, 0.4));
		addSequential(new DriveForward(.5, right_drive_time, true));
		addSequential(new GyroTurn(right_gyro_turn, true));
		addSequential(new DriveToTarget());
		addSequential(new YawCorrection());
		addSequential(new DriveForward(.5, .1));
	}
}
