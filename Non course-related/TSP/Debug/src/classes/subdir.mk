################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/classes/ClassicData.cpp \
../src/classes/ClassicParser.cpp 

OBJS += \
./src/classes/ClassicData.o \
./src/classes/ClassicParser.o 

CPP_DEPS += \
./src/classes/ClassicData.d \
./src/classes/ClassicParser.d 


# Each subdirectory must supply rules for building sources it contributes
src/classes/%.o: ../src/classes/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -DIL_STD -I/opt/ibm/ILOG/CPLEX_Studio1251/cplex/include -I/opt/ibm/ILOG/CPLEX_Studio1251/concert/include -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


