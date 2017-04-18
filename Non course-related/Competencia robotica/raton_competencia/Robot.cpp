#ifdef ROBOT_H_
#else
#define ROBOT_H_

#include <pololu/Pololu3pi.h>
#include "algorithms/algorithms.c"

const char LEFT[] PROGMEM = "c32>d32>c32>d32";
const char RIGHT[] PROGMEM = "d32>c32>d32>c32";

class Robot
{
public:
    Robot(int refractance_delay_us = 1000, unsigned int deadzone=100, unsigned int threshold=250, unsigned int timing = 35):
    left_motor(0),
    right_motor(0),
    deadzone(deadzone),
    threshold(threshold),
    timing(timing){
        this->sensor_timing[0] = 0;
        this->sensor_timing[1] = 0;
        this->sensor_timing[2] = 0;
        this->sensor_timing[3] = 0;
        this->sensor_timing[4] = 0;
        Pololu3pi().init(refractance_delay_us);
        this->robot = Pololu3pi();
        this->robot.emittersOn();
    };
    ~Robot();

    bool intersection_warning(){
        if(this->left_boundary && this->right_boundary && this->same_time)
            return true;
        return false;
    };

    void reset_warnings(){
        this->left_boundary = false;
        this->right_boundary = false;
    }

    inline bool on(unsigned int index){
        return sensor_values[index] >= threshold + deadzone;
    }

    inline bool off(unsigned int index){
        return sensor_values[index] < threshold - deadzone;
    }

     void scan(){
        //mark if any bundary condition has been met... perhaps.
        this->robot.readLineSensorsCalibrated(this->sensor_values, IR_EMITTERS_ON);
        for (int i = 0; i < 5; ++i)
        {
            if(sensor_values[i] >= threshold + deadzone){
                sensor_timing[i]++;
            }
            if(sensor_values[i] < threshold - deadzone){
                sensor_timing[i] = 0;
            }
        }
        if((sensor_timing[0] > timing)){
            left_boundary = true;
            play_from_program_space(LEFT);
        }
        if((sensor_timing[4] > timing)){
            right_boundary = true;
            play_from_program_space(RIGHT);
        }
        if((sensor_timing[0] > timing) && (sensor_timing[4] > timing)){
            same_time = true;
        }
    };

    int read_line(){
        this->scan();
        return this->robot.readLine(this->sensor_values, IR_EMITTERS_ON,0);
    };         

    /*
        IT'S SUPPOSED TO BRING VALUES BETWEEN 1000~3000
    */
    int read_narrow_line(){
        unsigned char i, on_line = 0;
        unsigned long avg;
        unsigned int sum;
        static int last_value=0;

        // this->robot.readLineSensorsCalibrated(this->sensor_values, IR_EMITTERS_ON);
        this->scan();

        avg = 0;
        sum = 0;
      
        for(i=1;i<4;i++) {
            int value = sensor_values[i];

            if(value > 200) {
                on_line = 1;
            }
            
            // only average in values that are above a noise threshold
            if(value > 50) {
                avg += (long)(value) * (i * 1000);
                sum += value;
            }
        }

        if(!on_line)
        {
            // If it last read to the left of center, return 0.
            if(last_value < (5-1)*1000/2)
                return 0;
            
            // If it last read to the right of center, return the max.
            else
                return (5-1)*1000;

        }

        last_value = avg/sum;

        return last_value;
    }

     void calibrate(){
        delay_ms(500);
        for(int counter=0;counter<80;counter++){                                    //CALIBRATION ROUTINE BEGIN
            if(counter < 20 || counter >= 60)
                set_motors(40,-40);
            else
                set_motors(-40,40);
            this->robot.calibrateLineSensors(IR_EMITTERS_ON);
            delay_ms(20);
        }
        set_motors(0,0);                                                            //CALIBRATION ROUTINE END
    }

     void wait_for_button(char *string, unsigned int button, bool debug = false){
        while(!button_is_pressed(button)){
            clear();
            print(string);
            lcd_goto_xy(0,1);
            if(debug){
                this->scan();
                print_unsigned_long(this->sensor_values[0]/100);
                lcd_goto_xy(1,1);
                print_unsigned_long(this->sensor_values[1]/100);
                lcd_goto_xy(2,1);
                print_unsigned_long(this->sensor_values[2]/100);
                lcd_goto_xy(3,1);
                print_unsigned_long(this->sensor_values[3]/100);
                lcd_goto_xy(4,1);
                print_unsigned_long(this->sensor_values[4]/100);
            }
            else{
                switch(button){
                    case BUTTON_A:
                    print("Press A");   
                    break;
                    case BUTTON_B:
                    print("Press B");   
                    break;
                    case BUTTON_C:
                    print("Press C");   
                    break;
                    default:
                    break;
                }
            }
            delay_ms(100);
        }
        delay_ms(500);
    }

    void set_motors(int left, int right){
        this->left_motor = left;
        this->right_motor = right;
        OrangutanMotors::setSpeeds(left_motor, right_motor);
    }

    void set_motors(int left_power, int right_power, int msecs){
      float factor;
      for(int i=0; i<msecs; i++){
        factor = normal_distribution(i, msecs);
        this->set_motors((int)(factor*left_power),(int)(factor*right_power));
        delay_ms(1);
      }
    }

    void stop_motors(){
        this->set_motors(0,0);
    }

    void finish(){
        while(1){}
    }
private:
    Pololu3pi robot;
    int left_motor = 0, right_motor = 0;
    unsigned int sensor_values[5];
    unsigned int sensor_timing[5];
    unsigned int deadzone, threshold, timing;
    bool left_boundary = false , right_boundary = false, same_time = false;
};

#endif