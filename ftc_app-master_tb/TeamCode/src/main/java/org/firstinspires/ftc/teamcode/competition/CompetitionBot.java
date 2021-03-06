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

package org.firstinspires.ftc.teamcode.competition;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.camera.CameraBot;
import org.firstinspires.ftc.teamcode.mechanum.MechanumBot;
import org.firstinspires.ftc.teamcode.simple.SimpleBot;


public class CompetitionBot extends CameraBot
{
    /* Public OpMode members. */
    //   public DcMotor  newMotor    = null;
    public Servo    tail    = null;
    boolean tailIsDown = false;

    public CompetitionBot(Telemetry atelemetry) {
        super(atelemetry);
    }
    public CompetitionBot(Telemetry atelemetry, LinearOpMode aOpMode) {
        super(atelemetry, aOpMode);
    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap hwMap) {

        // Save reference to Hardware map
        super.init(hwMap);

        // Define and Initialize Motors
        // newMotor = initMotor(hwMap, "new_motor");

        // Define and initialize ALL installed servos.
        tail = initServo(hwMap, "tail", 0.5);

    }

    public void tailDown(){
        if (!tailIsDown) {
            tail.setPosition(0);
            sleep(400);
            tail.setPosition(0.5);
            tailIsDown = true;
        }
    }

    public void tailUp(){
        if (tailIsDown) {
            tail.setPosition(1);
            sleep(400);
            tail.setPosition(0.5);
            tailIsDown = false;
        }
    }

    public void armUp()
    {
        leftArm.setPower(-0.5);
        sleep(2000);
        leftArm.setPower(0);
    }

    public void armUpAfterGlyph()
    {
        leftArm.setPower(-0.07);
        sleep(2000);
        leftArm.setPower(0);
    }

    public void armDown(){
        leftArm.setPower(0.5);
        sleep(1500);
        leftArm.setPower(0);
    }

    public void clawsOpen(){
        moveClaws(-0.3);
    }
    public void clawsCenter() {
        moveBothClaws(-0.3o);
    }

    public void clawsClose(){
        moveClaws(0.3);
    }
    public final void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}

