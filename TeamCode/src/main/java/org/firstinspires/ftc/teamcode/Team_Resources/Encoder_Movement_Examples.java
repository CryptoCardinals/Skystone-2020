

package org.firstinspires.ftc.teamcode.Team_Resources;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name = "Basic: Linear OpMode", group = "Linear Opmode")
@Disabled
public class Encoder_Movement_Examples extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor drop = null;
    private Servo drop_pin = null;
    private DistanceSensor Range_Boy = null;
    private DigitalChannel Touchy_Boy = null;


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        drop = hardwareMap.get(DcMotor.class, "drop");
        drop_pin = hardwareMap.get(Servo.class, "drop_pin");
        Range_Boy = hardwareMap.get(DistanceSensor.class, "Range_Boy");
        Touchy_Boy = hardwareMap.get(DigitalChannel.class, "Touchy_Boy");
        Touchy_Boy.setMode(DigitalChannel.Mode.INPUT);


        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        waitForStart();
        runtime.reset();

        Movement(.5, 1000);
        Movement2(.5, 1000);


    }


    public void Movement(double power, double distance) {

        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        while (Math.abs(leftDrive.getCurrentPosition() - distance) >= 5) {


            leftDrive.setPower(power);
            rightDrive.setPower(power);
        }

        leftDrive.setPower(0);
        rightDrive.setPower(0);


    }

    public void Movement2(double power, int distance) {
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftDrive.setTargetPosition(distance);
        rightDrive.setTargetPosition(distance);

        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftDrive.setPower(power);
        rightDrive.setPower(power);

        while (leftDrive.isBusy() || rightDrive.isBusy()) {

        }
        leftDrive.setPower(0);
        rightDrive.setPower(0);

        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


}

