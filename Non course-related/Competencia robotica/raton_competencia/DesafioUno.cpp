#ifdef DESAFIOUNO_H_
#else
#define DESAFIOUNO_H_
#include <stdlib.h>
#include "Robot.cpp"
class DesafioUno{
public:
    DesafioUno(Robot &robot, int velocidad = 150, float k_p = 20.0f, float k_i = 10000.0f, float k_d = 1.5f) :
        k_p(k_p),
        k_i(k_i),
        k_d(k_d),
        velocidad(velocidad),
        robot(robot)
        {};

    ~DesafioUno(){
    };
    void run(){
        unsigned int posicion;
        int resultado_pid;
        int laps = 0;
        while(laps < 2){
            if(robot.intersection_warning()){
                laps++;
                robot.reset_warnings();
            }
            posicion = robot.read_line();
            /************************************/
            // Valor máximo para el error: 2000 //
            /************************************/
            int proporcion_error = ((int)posicion) - 2000;                      //PROPORCIONAL    -> ERROR INSTANTANEO
            int derivativo_error = proporcion_error - ultimo_proporcoional;     //DIFERENCIAL     -> ERROR ESPERADO
            error_integrado += proporcion_error;                                       //INTEGRAL        -> ERROR ACUMULADO
            // Incremento instantáneo      = +-4000
            // Incremento a controlador    = 10 máximo, multiplicador = 0.2
            //
            // Incremento diferenciado     = +-4000
            // Incremento a controlador    = multiplicador = 1

            // Inremento por integral = ilimitado, restringido solamente por
            // velocidad de lectura. Dado que está seteada para 1000, que es el
            // valor por defecto es  400 microsegundos, y el incremento de este
            // puede ser de a unidades de 2000 en promedio, la idea es que en
            // aproximadamente 4000 microsegundos de error, incremente en 1 punto
            // el diferencial. El punto máximo de error por diferenciación
            // se alcanza a los 100 milisegundos a una velocidad media.
            // Incremento a controlador    = multiplicador = 0.00002
            ultimo_proporcoional = proporcion_error;                            //guarda el último valor para ser usado en el siguiente cálculo

            resultado_pid = (int)((((float)proporcion_error)*k_p) + (((float)error_integrado)*k_i) +(((float)derivativo_error)*k_d));


            if(resultado_pid > velocidad)
                resultado_pid = velocidad;
            if(resultado_pid < -velocidad)
                resultado_pid = -velocidad;

            if(resultado_pid < 0)
                robot.set_motors(velocidad+resultado_pid, velocidad);
            else
                robot.set_motors(velocidad, velocidad-resultado_pid);
        }
        print("Finished.");
    }
private:
    float k_p, k_i, k_d;
    int velocidad;
    int ultimo_proporcoional = 0;
    int error_integrado = 0;
    int state = 0;
    unsigned int sensor_values[5];    
    Robot robot;

};
#endif
