 
// package org.firstinspires.ftc.teamcode;

// import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
// import com.qualcomm.robotcore.util.Range;
// import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

// import org.firstinspires.ftc.teamcode.centerstage.RobotHardware;
// import org.firstinspires.ftc.teamcode.lib.MecanumChassis;
// import org.firstinspires.ftc.teamcode.lib.AutonomousControl.Side;



// import com.qualcomm.hardware.dfrobot.HuskyLens;
// import java.util.Map;
// import com.qualcomm.robotcore.hardware.Servo;
// import com.qualcomm.robotcore.util.Range;
// import com.qualcomm.robotcore.hardware.DcMotor;
// import com.qualcomm.robotcore.hardware.DcMotorEx;
// import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
// import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
// import com.qualcomm.robotcore.eventloop.opmode.Disabled;
// import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
// import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
// import com.qualcomm.robotcore.hardware.DistanceSensor;

// import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
// import org.firstinspires.ftc.robotcore.internal.system.Deadline;

// import java.util.concurrent.TimeUnit;

// @Autonomous(name="AutoBlueLeft", group="Autonomous")
// public class AutoBlueLeft extends LinearOpMode {
//     private RobotHardware rh = null;
//     private MecanumChassis mc = null;
//         private HuskyLens huskyLens;
//             private final int READ_PERIOD = 1;
//     private final double MOTOR_POWER_SCALE = 0.02;
//     private Deadline rateLimit = new Deadline(READ_PERIOD, TimeUnit.SECONDS);
//     private int inputColor = 0;  //true is 1, red is 2
//     private double dist = 0;
//     private int errorSide = 0;

//     // Adjust this to control motor movement speed
   

    
//     @Override
//     public void runOpMode() {
//         rh = new RobotHardware(hardwareMap);
//         mc = rh.getMecanumChassis();



       

//         // Reverse motors if necessary to align with your robot's configuration


           
           

//         /*
//          * Immediately expire so that the first time through we'll do the read.
//          */
//         rateLimit.expire();

//         /*
//          * Basic check to see if the device is alive and communicating.  This is not
//          * technically necessary here as the HuskyLens class does this in its
//          * doInitialization() method which is called when the device is pulled out of
//          * the hardware map.  However, sometimes it's unclear why a device reports as
//          * failing on initialization.  In the case of this device, it's because the
//          * call to knock() failed.
//          */
//         if (!huskyLens.knock()) {
//             telemetry.addData(">>", "Problem communicating with " + huskyLens.getDeviceName());
//         } else {
//             telemetry.addData(">>", "Press start to continue");
//         }

//         /*
//          * The device uses the concept of an algorithm to determine what types of
//          * objects it will look for and/or what mode it is in.  The algorithm may be
//          * selected using the scroll wheel on the device, or via software as shown in
//          * the call to selectAlgorithm().
//          *
//          * The SDK itself does not assume that the user wants a particular algorithm on
//          * startup, and hence does not set an algorithm.
//          *
//          * Users, should, in general, explicitly choose the algorithm they want to use
//          * within the OpMode by calling selectAlgorithm() and passing it one of the values
//          * found in the enumeration HuskyLens.Algorithm.
//          */
//         huskyLens.selectAlgorithm(HuskyLens.Algorithm.COLOR_RECOGNITION);

//         telemetry.update();
//         waitForStart();

//         /*
//          * Looking for AprilTags per the call to selectAlgorithm() above.  A handy grid
//          * for testing may be found at https://wiki.dfrobot.com/HUSKYLENS_V1.0_SKU_SEN0305_SEN0336#target_20.
//          *
//          * Note again that the device only recognizes the 36h11 family of tags out of the box.
//          */
        
//         left();
        
//         waitForStart();

//         while (opModeIsActive()) {
//             mc.runQueue();
//         }
//     }
    
//     private void left() {
//         if (huskyColorSense == 1)
//         {
//             mc.queueMoveTo(-3, 5, 0.8);
//         }
//     }
        
//     private void middle() {
//         if (huskyColorSense == 2)
//         {
//         mc.queueMoveTo(0, 6, 0.4);
//         }
//     }
    
//     private void right() {
//         if (huskyColorSense == 3)
//         {
//             mc.queueMoveTo(3,5,0.8);
//         }
        
        
        
//     }
    
//     private void chooseDirec(){
        
//     }
    


//     private int huskyColorSense(){
        
//                 while(opModeIsActive()) {
//             if (!rateLimit.hasExpired()) {
//                 continue;
//             }
           
//             if(gamepad1.x)
//             {
//                 inputColor = 1;
//             }
//             else if(gamepad1.y)
//             {
//                 inputColor = 2;
//             }
           
//          telemetry.addData("input color: ", inputColor);
//             rateLimit.reset();

//             /*
//              * All algorithms, except for LINE_TRACKING, return a list of Blocks where a
//              * Block represents the outline of a recognized object along with its ID number.
//              * ID numbers allow you to identify what the device saw.  See the HuskyLens documentation
//              * referenced in the header comment above for more information on IDs and how to
//              * assign them to objects.
//              *
//              * Returns an empty array if no objects are seen.
//              */
//             HuskyLens.Block[] blocks = huskyLens.blocks();
//             for (int i = 0; i < blocks.length; i++) {
//                 telemetry.addData("Block", blocks[i].toString());
//             }

//             if (blocks.length > 0) {
//                 int blockX = blocks[0].x;
//                 int blockY = blocks[0].y;
               
               
//                 int readColor = blocks[0].id;
//                 int screenCenterX = 320/2; // Screen's X-axis center
//                 int screenCenterY = 240/2;
//                 dist = (10.58888888883 * 30) / (2 * blocks[0].width * Math.tan(0.925/2)) * 5.5;
//                 double dh = Math.sqrt((blockX - screenCenterX)^2 + (blockY - screenCenterY)^2);
//                 telemetry.addData("Block tostring", blocks[0].toString());
//                 telemetry.addData("Block count", blocks.length);
//                 telemetry.addData("Distance ", dist);
//                 telemetry.addData("Block Identification #",readColor);
//                 telemetry.addData("dist of width", dh);
//                 // Assuming the screen resolution is 320x240 pixels

//                 // Get the position of the first block
               

//                 // Calculate the error (difference between block position and screen center)
//                 double errorX = (blockX - screenCenterX) * .001;
               
//                 double sign = -Math.signum(blockX - screenCenterX) * .1;
//                 if(Math.abs(blockX - screenCenterX) < 50) sign = 0;
//                 errorX = Range.clip(errorX, -.2, .2) *-1;
//                 // Apply motor power adjustments here
               
//                 double errorScreenCenter = Math.abs(blockX - screenCenterX);
               
//                 if(50 < errorScreenCenter && errorScreenCenter < 100){
//                     telemetry.addData("center of screen error: ", errorScreenCenter);
//                 }
//                 else if(errorScreenCenter > 50){
//                     errorSide = 1;
//                 }
//                 else if(blockX < screenCenterX){
//                     errorSide = 3;
//                 }
//                 else if(errorScreenCenter < 50)
//                 {
//                     errorSide = 2;
//                 }
               
               
//               /*if(readColor == inputColor) //only correct motors when id matches target id
//                 {
                   
//                     frontLeft.setPower(errorX + sign);
//                     frontRight.setPower(-errorX - sign);
//                     backLeft.setPower(-errorX - sign);
//                     backRight.setPower(errorX + sign);
                   
//                 }*/
               
//                 // Output telemetry for debugging
//                 telemetry.addData("Block X", blockX);
//                 //telemetry.addData("Block Y", blockY);
//                 telemetry.addData("Error X", errorX);
//                 telemetry.addData("error on: "+errorSide, errorScreenCenter);
             


//             telemetry.update();
//             }
//                 }
//             return errorSide;
        
                
    
    
// }
// }