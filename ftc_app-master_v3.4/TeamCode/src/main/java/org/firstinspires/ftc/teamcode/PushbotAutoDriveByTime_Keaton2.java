/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;

/**
 * This file illustrates the concept of driving a path based on time.
 * It uses the common Pushbot hardware class to define the drive on the robot.
 * The code is structured as a LinearOpMode
 *
 * The code assumes that you do NOT have encoders on the wheels,
 *   otherwise you would use: PushbotAutoDriveByEncoder;
 *
 *   The desired path in this example is:
 *   - Drive forward for 3 seconds
 *   - Spin right for 1.3 seconds
 *   - Drive Backwards for 1 Second
 *   - Stop and close the claw.
 *
 *  The code is written in a simple form with no optimizations.
 *  However, there are several ways that this type of sequence could be streamlined,
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="Keaton2: Auto Drive By Time", group="Pushbot")
//@Disabled
public class PushbotAutoDriveByTime_Keaton2 extends LinearOpMode {

    /* Declare OpMode members. */
    HardwarePushbot robot   = new HardwarePushbot();   // Use a Pushbot's hardware
    private ElapsedTime     runtime = new ElapsedTime();


    static final double     FORWARD_SPEED = 1.0;
    static final double     TURN_SPEED    = 0.5;

    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step through each leg of the path, ensuring that the Auto mode has not been stopped along the way

        moveArm(2.0, 0.1);
        runForward(2.0, 0.5);
        moveArm(2.0, -0.1);


        // Step 4:  Stop and close the claw.
        robot.leftDrive.setPower(0);
        robot.rightDrive.setPower(0);
        robot.leftClaw.setPosition(0.75);
        robot.rightClaw.setPosition(0.75);
        robot.rightClaw.setPosition(-0.75);

        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);
    }

    public void moveArm(double seconds, double power)
    {
        robot.leftArm.setPower(power);
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() < seconds)) {
            telemetry.addData("Path", "Time: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.leftArm.setPower(0.0);
    }
    public void runForward(double seconds, double power)
    {
        robot.leftDrive.setPower(power);
        robot.rightDrive.setPower(power);
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() < seconds)) {
            telemetry.addData("Path", "Time: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.leftDrive.setPower(0.0);
        robot.rightDrive.setPower(0.0);
    }

    public void runBackward(double seconds, double power)
    {
        power = -power;
        runForward(seconds, power);
    }

    public void spinRight(double seconds, double power)
    {
        robot.leftDrive.setPower(TURN_SPEED);
        robot.rightDrive.setPower(-TURN_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.3)) {
            telemetry.addData("Path", "Right: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.leftDrive.setPower(0.0);
        robot.rightDrive.setPower(0.0);
    }

    public void spinLeft(double seconds, double power)
    {
        robot.leftDrive.setPower(-TURN_SPEED);
        robot.rightDrive.setPower(TURN_SPEED);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.3)) {
            telemetry.addData("Path", "Left: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.leftDrive.setPower(0.0);
        robot.rightDrive.setPower(0.0);
    }
}
