package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name="thef", group="Linear Opmode")
public class Test extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    
    private DcMotorEx backLeft = null;
    private DcMotorEx backRight = null;
    private DcMotorEx frontLeft = null;
    private DcMotorEx frontRight = null;
    
    BNO055IMU imu = null;
    
    private double defaultMoveSpeed = 1;
    private double defaultTurnSpeed = 0.75;
    private double headingCorrectionSpeed = 0.6;
    
    private double currentHeading;
    private double targetHeading;
    
    private double headingBuffer = 0.25;
    
    @Override
    public void runOpMode() {
        backLeft  = hardwareMap.get(DcMotorEx.class, "backLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");
        frontLeft  = hardwareMap.get(DcMotorEx.class, "frontLeft");
        frontRight  = hardwareMap.get(DcMotorEx.class, "frontRight");
        
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        
        // Retrieve the IMU from the hardware map
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        // Technically this is the default, however specifying it is clearer
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        // Without this, data retrieving from the IMU throws an exception
        imu.initialize(parameters);
        
        targetHeading = imu.getAngularOrientation().firstAngle;

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            currentHeading = imu.getAngularOrientation().firstAngle;
            double moveSpeed = defaultMoveSpeed;
            double turnSpeed = defaultTurnSpeed;
            
            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;
            
            if (gamepad1.right_trigger > 0.5) {
                moveSpeed /= 2;
                turnSpeed /= 2;
            }

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x), 1);
            double frontLeftPower = (y + x + rx) / denominator * moveSpeed;
            double backLeftPower = (y - x + rx) / denominator * moveSpeed;
            double frontRightPower = (y - x - rx) / denominator * moveSpeed;
            double backRightPower = (y + x - rx) / denominator * moveSpeed;

            frontLeft.setPower(frontLeftPower);
            backLeft.setPower(backLeftPower);
            frontRight.setPower(frontRightPower);
            backRight.setPower(backRightPower);
            
            telemetry.addData("Current heading", currentHeading);
            telemetry.addData("Target heading", targetHeading);
            telemetry.update();
        }
    }
        
}