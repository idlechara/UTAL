################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
../src/Tarea\ TDA\ Lista.c 

OBJS += \
./src/Tarea\ TDA\ Lista.o 

C_DEPS += \
./src/Tarea\ TDA\ Lista.d 


# Each subdirectory must supply rules for building sources it contributes
src/Tarea\ TDA\ Lista.o: ../src/Tarea\ TDA\ Lista.c
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C Compiler'
	gcc -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"src/Tarea TDA Lista.d" -MT"src/Tarea\ TDA\ Lista.d" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


