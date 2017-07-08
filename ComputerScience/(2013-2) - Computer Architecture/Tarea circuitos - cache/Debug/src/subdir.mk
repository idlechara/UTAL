################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/Tarea\ circuitos\ -\ cache.cpp 

OBJS += \
./src/Tarea\ circuitos\ -\ cache.o 

CPP_DEPS += \
./src/Tarea\ circuitos\ -\ cache.d 


# Each subdirectory must supply rules for building sources it contributes
src/Tarea\ circuitos\ -\ cache.o: ../src/Tarea\ circuitos\ -\ cache.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -std=c++11 -MMD -MP -MF"src/Tarea circuitos - cache.d" -MT"src/Tarea\ circuitos\ -\ cache.d" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


