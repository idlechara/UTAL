################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/Proyecto\ 3\ -\ Remake.cpp 

OBJS += \
./src/Proyecto\ 3\ -\ Remake.o 

CPP_DEPS += \
./src/Proyecto\ 3\ -\ Remake.d 


# Each subdirectory must supply rules for building sources it contributes
src/Proyecto\ 3\ -\ Remake.o: ../src/Proyecto\ 3\ -\ Remake.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"src/Proyecto 3 - Remake.d" -MT"src/Proyecto\ 3\ -\ Remake.d" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


