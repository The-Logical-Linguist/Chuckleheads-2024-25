package org.firstinspires.ftc.teamcode.HuskyLens;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import java.util.ArrayList;
import org.firstinspires.ftc.teamcode.HuskyLens.*;

@TeleOp(name="AprilTags", group="Linear Opmode")
@Disabled
public class AprilTags extends LinearOpMode {
    HuskyLens husky;
    
    // THIS CODE SUCKS AND IS NOT COMPLETE PLEASE DO NOT JUDGE IT TOO HARD

    @Override
    public void runOpMode() {
        husky = hardwareMap.get(HuskyLens.class, "husky");
        husky.cmdRequest(husky.BLOCKS);
        
        waitForStart();
        
    //     while (opModeIsActive()) {
    //         telemetry.addData("blocks", getBlocks());
    //         telemetry.update();
    //     }
    // }
    
    
    //  private ArrayList getBlocks() {
    //     ArrayList<Integer> blocks = new ArrayList<Integer>();
    //     for (int i = 0; i < husky.blocks.length; i++) {
    //         blocks.add(husky.blocks[i].id());
    //     }
    //     return blocks;
    }
}