package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.hardware.bosch.BNO055IMU;

import com.qualcomm.robotcore.hardware.DigitalChannel;

import com.qualcomm.robotcore.hardware.Servo;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import com.qualcomm.robotcore.util.ElapsedTime;

import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Driving", group="Linear Opmode")
public class Neel extends LinearOpMode{
    //Declare OpMode members.

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotorEx fl = null;

    private DcMotorEx fr = null;

    private DcMotorEx bl = null;

    private DcMotorEx br = null;
 
    
    private BNO055IMU imu = null;
    
    double maxSpeed = .1;
    

    @Override
    public void runOpMode() {
        
    waitForStart();
    if (isStopRequested()) return;

    while (opModeIsActive()) {
        // imu parameters
                // Actually set up the IMU.
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        // parameters.mode             = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit        = BNO055IMU.AngleUnit.DEGREES;
        // parameters.accelUnit        = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        //parameters.loggingEnabled   = false;
        imu.initialize(parameters);

        telemetry.addData("Status", "Initialized");

        telemetry.update();


        // Initialize the hardware variables. Note that the strings used here as parameters

        // to 'get' must correspond to the names assigned during the robot configuration

        // step (using the FTC Robot Controller app on the phone).

        fl  = hardwareMap.get(DcMotorEx.class, "frontLeft");

        fr = hardwareMap.get(DcMotorEx.class, "frontRight");

        bl  = hardwareMap.get(DcMotorEx.class, "backLeft");

        br = hardwareMap.get(DcMotorEx.class, "backRight");

       

        // Most robots need the motor on one side to be reversed to drive forward

        // Reverse the motor that runs backwards when connected directly to the battery

        fl.setDirection(DcMotor.Direction.REVERSE);

        fr.setDirection(DcMotor.Direction.FORWARD);

        bl.setDirection(DcMotor.Direction.REVERSE);

        br.setDirection(DcMotor.Direction.FORWARD);
    
            double y = gamepad1.left_stick_x; // Remember, this is reversed!
            double x = -gamepad1.left_stick_y; // Counteract imperfect strafing
            double botHeading = -imu.getAngularOrientation().firstAngle;
            
            double rotX = x * Math.cos(botHeading) - y * Math.sin(botHeading) * .5;
            double rotY = x * Math.sin(botHeading) + y * Math.cos(botHeading) * .5;
            //double rx = ((gamepad1.right_stick_y) * Math.sin(botHeading) + (gamepad1.right_stick_x) *Math.cos(botHeading));
            double rx=1;
            //rx = (newRightStickx * Math.cos(botHeading) - newRightSticky * Math.sin(botHeading));
            //double ry = (newRightSticky * Math.sin(botHeading) - newRightStickx * Math.cos(botHeading));
            telemetry.addData("angular", ((0) * Math.sin(botHeading) + (1) *Math.cos(botHeading)));
        //     if (Math.abs(rx) >= .2) {
        //   if (rx < 0) rx = -.7;
        //     if (rx > 0) rx = .7; }
            
            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio, but only when
            // at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + (rx), 1);
            double frontLeftPower = (rotY + rotX - rx) / denominator;
            double backLeftPower = (rotY - rotX - rx) / denominator;
            double frontRightPower = (rotY - rotX + rx) / denominator;
            double backRightPower = (rotY + rotX  + rx) / denominator;

            fl.setPower(frontLeftPower * maxSpeed);
            bl.setPower(backLeftPower * maxSpeed);
            fr.setPower(frontRightPower * maxSpeed);
            br.setPower(backRightPower * maxSpeed);

    // todo: write your code here
} } } 