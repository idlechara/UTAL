#include <pololu/Pololu3pi.h>
#include "DesafioDos.cpp"
#include "DesafioUno.cpp"
#include "Robot.cpp"
const char start[] PROGMEM = "c32>d32>e32>f32>g32>a32>b32";
int main()
{
    play_from_program_space(start);
    // unsigned int sensor_values[5];

    Robot raton(1000);

	// Pololu3pi().init(1000);
	// emitters_on();

	// while(!button_is_pressed(BUTTON_B)){
	// 	clear();
	// 	print("Chall.2");
	// 	lcd_goto_xy(0,1);
	// 	print("Press B");
	// 	delay_ms(100);
	// }

    raton.wait_for_button("Desf. 1",BUTTON_B);

    raton.calibrate();
	// delay_ms(500);
	// for(int counter=0;counter<80;counter++){									//CALIBRATION ROUTINE BEGIN
	// 	if(counter < 20 || counter >= 60)
	// 		set_motors(40,-40);
	// 	else
	// 		set_motors(-40,40);
	// 	calibrate_line_sensors(IR_EMITTERS_ON);
	// 	delay_ms(20);
	// }
	// set_motors(0,0);															//CALIBRATION ROUTINE END

    raton.wait_for_button("Ready!", BUTTON_B, true);

	// while(!button_is_pressed(BUTTON_B)){
	// 	clear();
	// 	print("Ready!");
	// 	read_line(sensor_values,IR_EMITTERS_ON);
	// 	lcd_goto_xy(0,1);
	// 	print_unsigned_long(sensor_values[0]/100);
	// 	lcd_goto_xy(1,1);
	// 	print_unsigned_long(sensor_values[1]/100);
	// 	lcd_goto_xy(2,1);
	// 	print_unsigned_long(sensor_values[2]/100);
	// 	lcd_goto_xy(3,1);
	// 	print_unsigned_long(sensor_values[3]/100);
	// 	lcd_goto_xy(4,1);
	// 	print_unsigned_long(sensor_values[4]/100);
	// 	delay_ms(100);
	// }
	// delay_ms(500);
	


	DesafioUno desafio1(raton, 50);
	DesafioDos desafio2(raton, 50, 0.2f, 0.00050, 1.5f);


	// raton.set_motors(250,250);
	// delay_ms(5000);

	// desafio2.turn(100);
	desafio1.run();
	// desafio2.run();
	// desafio2.center();
	// HAFL TURN
	// desafio2.smooth_set_motors(200,-200, 162);
	// desafio2.smooth_set_motors(200,-200, 328);

	raton.stop_motors();
	raton.finish();	
	// desafio1.run();
	return 0;
}
