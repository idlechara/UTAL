################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/libs/Block.cpp \
../src/libs/Cache.cpp 

OBJS += \
./src/libs/Block.o \
./src/libs/Cache.o 

CPP_DEPS += \
./src/libs/Block.d \
./src/libs/Cache.d 


# Each subdirectory must supply rules for building sources it contributes
src/libs/%.o: ../src/libs/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -std=c++11 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


