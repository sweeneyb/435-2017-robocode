package org.usfirst.frc.team435.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team435.robot.Robot;
import org.usfirst.frc.team435.robot.commands.StrafeLeft;
import org.usfirst.frc.team435.robot.commands.TurnLeft;
import org.usfirst.frc.team435.robot.commands.DriveForward;
import edu.wpi.first.wpilibj.networktables.*;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
/**
 *
 */
public class Vision extends CommandGroup {
    private NetworkTable left_x_table;
    private NetworkTable right_x_table;
    private NetworkTable left_width_table;
    private NetworkTable right_width_table;
    private double default_value;
    private double left_x;
    private double right_x;
    private double left_width;
    private double right_width;
    private double last_left_x;
    private double last_right_x;
    private double last_left_width;
    private double last_right_width;
    private int i;
    private final int res_width = 640;
    public int tolerance;
    public Vision() {
    	visionInit();
    	visionRun();
    }
    public void visionRun() {
    	while(true) {
    		if (!(get_values())) {
    			DriverStation.reportError("stale vision tracking values", false);
    			SmartDashboard.putBoolean("VisionIsWorking", false);
    		}
    		else {
    			SmartDashboard.putBoolean("VisionIsWorking", true);
  
    		    if (right_width - left_width <= -tolerance) { 
    			    new TurnLeft(-0.5, 0.1);
    		    }
    		    else if (right_width - left_width >= tolerance) {
    		    	new TurnLeft(0.5, 0.1);
    		    }
    		    else if (Math.abs((left_x + right_x) - res_width) >= tolerance) {
    		    	if (((left_x + right_x) - res_width) < 0) {
    		    		new StrafeLeft(0.5, 0.1);
    		    	}
    		    	else { /* > 0 */
    		    		new StrafeLeft(-0.5, 0.1);
    		    	}
    		    }

    		    else {
    		    	new DriveForward(0.5, 0.1);
    		    }
    		}
    	}
    }
    public void visionInit() {
        Robot.startVision();
        left_x_table = NetworkTable.getTable("left_x");
        right_x_table = NetworkTable.getTable("right_x");
        right_width_table = NetworkTable.getTable("right_width");
        left_width_table = NetworkTable.getTable("left_width");
        default_value = 218949832749289343323.21243234324;
        last_left_x = default_value;
        last_right_x = default_value;
        last_left_width = default_value;
        last_right_width = default_value;
        tolerance = 10;
    }
    public Boolean get_values() {
    	i = 0;
        while (true) {
        	left_x = left_x_table.getNumber("left_x", default_value);
        	right_x = right_x_table.getNumber("right_x", default_value);
        	left_width = left_width_table.getNumber("left_width", default_value);
        	right_width = right_width_table.getNumber("right_width", default_value);
        	if ((left_x == default_value) || (right_x == default_value) || (right_width == default_value) || (left_width == default_value) || (left_x == last_left_x) || (right_x == last_right_x) || (right_width == last_right_width) || (left_width == last_left_width)) {
        		try{Thread.sleep(25);}catch(InterruptedException ex){};
        		/* wait for 25 milliseconds */
        		i ++;
        		if (i >= 10) {
        			return false;
        		}
        	}
        	else {
        		last_left_x = left_x;
        		last_right_x = right_x;
        		last_right_width = right_width;
        		last_left_width = left_width;
        		return true;
        	}
        }
    }
}
