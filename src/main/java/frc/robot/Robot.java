package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Constants.ArmConstants;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Elevator;

public class Robot extends TimedRobot {
  private final Arm arm = new Arm();
  private final Elevator elevator = new Elevator();

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  /** Calculates the arm's height from the floor. */
  public double getAbsoluteArmHeight() {
    var elevatorHeight = elevator.getHeight();
    var theta = 90 - arm.getAngle();
    return elevatorHeight - (Math.cos(Units.degreesToRadians(theta)) * ArmConstants.ARM_LENGTH);
  }
}
