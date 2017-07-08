#ifdef DESAFIODOS_H_
#else
#define DESAFIODOS_H_
#include <stdlib.h>
#include <math.h>
#include "Robot.cpp"
#define PI 3.14163264

#define  FORWARD        'f'
#define  LEFT           'l'
#define  RIGHT          'r'
#define  NOTHING        'n'
#define  INTERSECTION   'i'

const char tune1[] PROGMEM = "g32>c32";
const char tune2[] PROGMEM = "b32>a32";
const char tune3[] PROGMEM = "c32>d32>e32";
const char tune4[] PROGMEM = "g32>a32>b32";

class DesafioDos{
public:
    DesafioDos(Robot &robot, int velocidad = 150, float k_p = 20.0f, float k_i = 10000.0f, float k_d = 1.5f):
        k_p(k_p),
        k_i(k_i),
        k_d(k_d),
        velocidad(velocidad),
        robot(robot)
        {};

    inline bool on(unsigned int index){
      return sensor_values[index] >= umbral + deadzone;
    }

    inline bool off(unsigned int index){
      return sensor_values[index] < umbral - deadzone;
    }


    char check_status(){
      read_line(sensor_values,IR_EMITTERS_ON);
      if( on(0) && on(4))
        return INTERSECTION;
      else if( on(0) &&  on(1) && on(2) && off(4))
        return LEFT;
      else if( off(0)  && on(2) && on(3) && on(4))
        return RIGHT;
      else if( on(1) || on(2) || on(3))
        return FORWARD;
      else
        return NOTHING;
    }

    void print_status(){
      read_line(sensor_values,IR_EMITTERS_ON);
      clear();
      lcd_goto_xy(0,1);
      print_unsigned_long(sensor_values[0]/110);
      lcd_goto_xy(1,1);
      print_unsigned_long(sensor_values[1]/110);
      lcd_goto_xy(2,1);
      print_unsigned_long(sensor_values[2]/110);
      lcd_goto_xy(3,1);
      print_unsigned_long(sensor_values[3]/110);
      lcd_goto_xy(4,1);
      print_unsigned_long(sensor_values[4]/110);
      lcd_goto_xy(0,0);
      print_character(check_status());

      return;
    }

    bool do_step(){
      play_from_program_space(tune1);
      print_status();
      // delay_ms(100);

      // lcd_goto_xy(2,0);

      // while(!(on(0) || on(4) || (off(1) && off(2) && off(3)))){
        play_from_program_space(tune2);
        advance_forward(35);
      // }


      set_motors(velocidad-25,velocidad-25);
      delay_ms(60);

      //see what happened
      read_line(sensor_values,IR_EMITTERS_ON);
      bool left_turn_avaliable = on(0);
      bool right_turn_avaliable = on(4);


      //lineup with intersection
      set_motors(velocidad-30,velocidad-30);
      delay_ms(85);

      play_from_program_space(tune2);
      //can I continue?
      read_line(sensor_values,IR_EMITTERS_ON);
      bool foward_turn_avaliable = on(1) || on(2) || on(3);

      if(left_turn_avaliable){
        lcd_goto_xy(3,0);
        print_character('L');
      }
      if(right_turn_avaliable){
        lcd_goto_xy(4,0);
        print_character('R');
      }
      if(foward_turn_avaliable){
        lcd_goto_xy(5,0);
        print_character('F');
      }


      //choose
      if(on(0) && on(1) && on(2) && on(3) && on(4)){
          play_from_program_space(tune3);
          play_from_program_space(tune4);
          delay_ms(100);
          set_motors(0,0);
          display_path();
          return false;
      }
      else if(right_turn_avaliable){
        play_from_program_space(tune3);
        turnRight();
        path[path_length] = 'R';
        path_length++;
      }
      else if(foward_turn_avaliable){
        play_from_program_space(tune3);
      }
      else if(left_turn_avaliable){
        play_from_program_space(tune3);
        turnLeft();
        path[path_length] = 'L';
        path_length++;
      }
      else{
        play_from_program_space(tune3);
        turnAround();
        path[path_length] = 'T';
        path_length++;
      }

      //drive till somthing happens
      ultimo_proporcoional = 0;
      error_integrado = 0;
      // stand_by_me();
      return true;
    }

bool do_step_temp(){
  play_from_program_space(tune1);
  read_line(sensor_values,IR_EMITTERS_ON);
  clear();
  lcd_goto_xy(0,1);
  print_unsigned_long(sensor_values[0]/110);
  lcd_goto_xy(1,1);
  print_unsigned_long(sensor_values[1]/110);
  lcd_goto_xy(2,1);
  print_unsigned_long(sensor_values[2]/110);
  lcd_goto_xy(3,1);
  print_unsigned_long(sensor_values[3]/110);
  lcd_goto_xy(4,1);
  print_unsigned_long(sensor_values[4]/110);

  // char status = check_status();
  lcd_goto_xy(0,0);
  print_character(check_status());
  // delay_ms(100);

  // lcd_goto_xy(2,0);

  // while(!(on(0) || on(4) || (off(1) && off(2) && off(3)))){
    play_from_program_space(tune2);
    advance_forward(35);
  // }


  set_motors(velocidad-25,velocidad-25);
  delay_ms(60);

  //see what happened
  read_line(sensor_values,IR_EMITTERS_ON);
  bool left_turn_avaliable = on(0);
  bool right_turn_avaliable = on(4);


  //lineup with intersection
  set_motors(velocidad-30,velocidad-30);
  delay_ms(85);

  play_from_program_space(tune2);
  //can I continue?
  read_line(sensor_values,IR_EMITTERS_ON);
  bool foward_turn_avaliable = on(1) || on(2) || on(3);

  if(left_turn_avaliable){
    lcd_goto_xy(3,0);
    print_character('L');
  }
  if(right_turn_avaliable){
    lcd_goto_xy(4,0);
    print_character('R');
  }
  if(foward_turn_avaliable){
    lcd_goto_xy(5,0);
    print_character('F');
  }


  //choose
  if(on(0) && on(1) && on(2) && on(3) && on(4)){
      play_from_program_space(tune3);
      play_from_program_space(tune4);
      delay_ms(100);
      set_motors(0,0);
      display_path();

      while(1);
      return false;
  }
  else if(left_turn_avaliable){
    play_from_program_space(tune3);
    turnLeft();
    path[path_length] = 'L';
    path_length++;
  }
  else if(foward_turn_avaliable){
    play_from_program_space(tune3);
  }
  else if(right_turn_avaliable){
    play_from_program_space(tune3);
    turnRight();
    path[path_length] = 'R';
    path_length++;
  }
  else{
    play_from_program_space(tune3);
    turnAround();
    path[path_length] = 'T';
    path_length++;
  }

  //drive till somthing happens
  ultimo_proporcoional = 0;
  error_integrado = 0;
  // stand_by_me();
  return true;
}


    void do_step_inverse(){
      play_from_program_space(tune1);
        play_from_program_space(tune3);
          play_from_program_space(tune2);
            play_from_program_space(tune4);
      if(path_length<0){
        while(do_step()){};
        return;
      }
      play_from_program_space(tune1);
      read_line(sensor_values,IR_EMITTERS_ON);
      clear();

      // char status = check_status();
      lcd_goto_xy(0,0);
      print_character(check_status());
      // delay_ms(100);

      // lcd_goto_xy(2,0);

      // while(!(on(0) || on(4) || (off(1) && off(2) && off(3)))){
        play_from_program_space(tune2);
        advance_forward(35);
      // }


      set_motors(velocidad-25,velocidad-25);
      delay_ms(60);

      //see what happened
      read_line(sensor_values,IR_EMITTERS_ON);
      bool left_turn_avaliable = on(0);
      bool right_turn_avaliable = on(4);


      //lineup with intersection
      set_motors(velocidad-30,velocidad-30);
      delay_ms(85);

      play_from_program_space(tune2);
      //can I continue?
      read_line(sensor_values,IR_EMITTERS_ON);
      bool foward_turn_avaliable = on(1) || on(2) || on(3);

      if(left_turn_avaliable){
        lcd_goto_xy(3,0);
        print_character('L');
      }
      if(right_turn_avaliable){
        lcd_goto_xy(4,0);
        print_character('R');
      }
      if(foward_turn_avaliable){
        lcd_goto_xy(5,0);
        print_character('F');
      }


      //choose
      if(on(0) && on(1) && on(2) && on(3) && on(4)){
          play_from_program_space(tune3);
          play_from_program_space(tune4);
          delay_ms(100);
          set_motors(0,0);
          // stand_by_me();
          display_path();
          while(1);
      }
      else if(right_turn_avaliable && path[path_length-1] == 'L'){
        play_from_program_space(tune3);
        turnRight();
        path_length--;
      }
      else if(left_turn_avaliable && path[path_length-1] == 'R'){
        play_from_program_space(tune3);
        turnLeft();
        path_length--;
      }
      else if(foward_turn_avaliable && !(left_turn_avaliable || right_turn_avaliable) ){
        return;
      }
      else if(path[path_length-1] == 'T'){
        play_from_program_space(tune3);
        turnAround();
        path_length--;
      }

      //drive till somthing happens
      ultimo_proporcoional = 0;
      error_integrado = 0;

      lcd_goto_xy(7,0);
      print_character(path[path_length]);
      lcd_goto_xy(0,1);
      print_unsigned_long(sensor_values[0]/110);
      lcd_goto_xy(1,1);
      print_unsigned_long(sensor_values[1]/110);
      lcd_goto_xy(2,1);
      print_unsigned_long(sensor_values[2]/110);
      lcd_goto_xy(3,1);
      print_unsigned_long(sensor_values[3]/110);
      lcd_goto_xy(4,1);
      print_unsigned_long(sensor_values[4]/110);
      // stand_by_me();
      return;
    }

    int advance_forward(unsigned int time){
        unsigned int posicion;
        int resultado_pid;

        while(1){
          posicion = read_line(sensor_values,IR_EMITTERS_ON);

          if(on(0) || on(4) || (off(1) && off(2) && off(3)))
            return 0;

          int proporcion_error = ((int)posicion) - 2000;
          int derivativo_error = proporcion_error - ultimo_proporcoional;
          error_integrado += proporcion_error;
          ultimo_proporcoional = proporcion_error;
          resultado_pid = (int)((((float)proporcion_error)*k_p) + (((float)error_integrado)*k_i) +(((float)derivativo_error)*k_d));
          if(resultado_pid > velocidad)
              resultado_pid = velocidad;
          if(resultado_pid < -velocidad)
              resultado_pid = -velocidad;

          if(resultado_pid < 0){
              set_motors(velocidad+resultado_pid, velocidad);
              // print_unsigned_long(velocidad+resultado_pid);
              // lcd_goto_xy(4,1);
              // print_unsigned_long(velocidad);
          }
          else{
              set_motors(velocidad, velocidad-resultado_pid);
              // print_unsigned_long(velocidad);
              // lcd_goto_xy(4,1);
              // print_unsigned_long(velocidad+resultado_pid);
          }
        }
    }


    void smooth_set_motors(int left_power, int right_power, int msecs){
      float factor;
      for(int i=0; i<msecs; i++){
        factor = normal_density(i, msecs);
        set_motors((int)(factor*left_power),(int)(factor*right_power));
        delay_ms(1);
      }
    }

    float normal_density(unsigned int t, unsigned int extension, /*bool compute_warmup = true,*/ unsigned int warm_up = 200, float mu = 3.0f, float sigma = 1.0f){
      float x = (float)t;

      if (2 * warm_up > extension/* || compute_warmup*/){
        warm_up = extension/2;
      }

      float first_delta = warm_up;
      float second_delta = extension - (warm_up);

      if(t <= first_delta){
        x = (float)t;
        x = x * (6.0f/(float)(2 * warm_up));
        return (1/sqrt(2*PI)*sigma) * exp(((-1)*(x-mu)*(x-mu))/2*sigma*sigma) * 2.5;
      }
      else if( t > warm_up && t  < second_delta){
        x = (3.0f);
        return (1/sqrt(2*PI)*sigma) * exp(((-1)*(x-mu)*(x-mu))/2*sigma*sigma) * 2.5;
      }
      else{
        x = (float)(first_delta - (t - second_delta));
        x = x * (6.0f/(float)(2 * warm_up));
        return (1/sqrt(2*PI)*sigma) * exp(((-1)*(x-mu)*(x-mu))/2*sigma*sigma) * 2.5;
      }
    }


    void turnLeft(){
      smooth_set_motors(-200,200, 162);
      center();
    }

    void turnRight(){
      smooth_set_motors(200,-200, 162);
      center();
    }

    void turnAround(){
      smooth_set_motors(200,-200, 328);
      center();
    }

    void display_path()
    {
      path[path_length] = 0;

      clear();
      print(path);

      if(path_length > 8)
      {
        lcd_goto_xy(0,1);
        print(path+8);
      }
    }

    void run(){
        //run maze
        set_motors(velocidad,velocidad);
        delay_ms(400);
        while(do_step()){
        }

        //simplify

        //return to origin

        turnAround();
        while(1){
          // do_step_inverse();
          do_step_temp();
        }
    }

    void stand_by_me(){
      set_motors(0,0);
      play_from_program_space(tune4);
      while(!button_is_pressed(BUTTON_B)){
      }
      while(!button_is_pressed(BUTTON_B)){
      }
      delay_ms(200);
    }

    void center(){
      unsigned int line_position = read_line(sensor_values,IR_EMITTERS_ON);

      while(line_position > 2000 + deadzone || line_position < 2000 - deadzone){
        if(line_position < 2000)
          set_motors(-40,40);
        else
          set_motors(40,-40);
        line_position = read_line(sensor_values,IR_EMITTERS_ON);
      }
    }
private:
    char path[100];
    int  path_length = 0;
    float k_p, k_i, k_d;
    unsigned int umbral = 250;
    unsigned int deadzone = 100;
    char previous_status = FORWARD;
    int velocidad;
    int ultimo_proporcoional = 0;
    int error_integrado = 0;
    int state = 0;
    unsigned int sensor_values[5];
    Robot robot;
};
#endif
