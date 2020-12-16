package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;

public class Elevator extends SubsystemBase {
  private final CANSparkMax leader, follower;
  /** PID controller on the elevator's height. */
  private final PIDController controller;
  /** Ultrasonic sensor to get the elevator's height. */
  private final Ultrasonic distanceSensor;

  public Elevator() {
    leader = new CANSparkMax(ElevatorConstants.LEADER_ID, MotorType.kBrushless);
    controller =
        new PIDController(ElevatorConstants.kP, ElevatorConstants.kI, ElevatorConstants.kD);
    follower = new CANSparkMax(ElevatorConstants.FOLLOWER_ID, MotorType.kBrushless);
    follower.restoreFactoryDefaults();
    follower.follow(leader);

    distanceSensor = new Ultrasonic(ElevatorConstants.PNG, ElevatorConstants.ECHO);
  }

  /** Get the height of the elevator */
  public double getHeight() {
    return distanceSensor.getRangeMM();
  }

  /**
   * Sets the height goal of the elevator. The PID controller will bring the elevator to that
   * height.
   */
  public void setHeight(double height) {
    controller.setSetpoint(height);
  }

  @Override
  public void periodic() {
    leader.set(controller.calculate(getHeight()));
  }
}
