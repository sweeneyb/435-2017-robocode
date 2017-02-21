package org.usfirst.frc.team435.robot.subsystems;

import com.analog.adis16448.frc.ADIS16448_IMU;
import com.analog.adis16448.frc.ADIS16448_IMU.AHRSAlgorithm;
import com.analog.adis16448.frc.ADIS16448_IMU.Axis;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 * Provides a wrapper around the multiple gyro types. 
 * Will first try to instantiate the IMU (on the MXP), and return a yaw
 * then will fall back to the ADXRS450 (SPI). Lastly, if no gyro is found, returns 0 for calls to getAngle
 * This means you can hook it safely into the mechanum classes and at worse, you'll just lose field relative steering / correction.
 * Protects you from NPEs. However, if you're going to use it to do a turn, you need to check isWorking first otherwise you'll get a permanent turn.
 */
public class SafeGyro extends Subsystem implements Gyro {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private Gyro gyro;
	private ADIS16448_IMU imu;
	public SafeGyro(){
		
		try {
			imu = new ADIS16448_IMU(Axis.kZ, AHRSAlgorithm.kComplementary, false);
			imu.setTiltCompYaw(false);
			gyro = imu;
	
		}
		catch(Throwable t) {
			DriverStation.reportWarning(t.getMessage(), true);
		}
		if (gyro == null || ((ADIS16448_IMU)gyro).getTemperature()==0.0){
			try {
				gyro = new ADXRS450_Gyro();
			}
			catch(Throwable t) {
				DriverStation.reportWarning(t.getMessage(), true);
			}
	
		}
				
	
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public double getAngle(){
    	if (gyro == null)
    		return 0;
    	return gyro instanceof ADIS16448_IMU ?  imu.getRelativeYaw() : gyro.getAngle();   
    	 
    }
    public void reset(){
    	
    	if (gyro != null){
    		gyro.reset();
    	}
    }
    public boolean isWorking(){
    	return gyro!=null;
    }

	public void calibrate() {
		if (gyro!=null)
			gyro.calibrate();
		
	}

	
	public double getRate() {
		double ret_val=0.0;
		if (gyro!=null)
			ret_val=gyro.getRate();
		return ret_val;
	}

	
	public void free() {
		if (gyro!=null)
			gyro.free();
		
	}
}

