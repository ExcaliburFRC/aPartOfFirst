package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;

public class Arm extends SubsystemBase {
  private CANSparkMax motor;
  private AnalogPotentiometer potentiometer;
  private PIDController controller;

  public Arm() {
    motor = new CANSparkMax(ArmConstants.MOTOR_ID, MotorType.kBrushless);
    potentiometer = new AnalogPotentiometer(1, 90, 0);
    controller = new PIDController(ArmConstants.kP, ArmConstants.kI, ArmConstants.kD);
  }

  /** Set the target angle */
  public void setAngle(double angle) {
    controller.setSetpoint(angle);
  }

  /** Get the angle of the potentiometer */
  public double getAngle() {
    return potentiometer.get();
  }

  /** Constant PID to bring the arm and keep it at a certain angle. */
  @Override
  public void periodic() {
    motor.set(controller.calculate(getAngle()));
  }
}
