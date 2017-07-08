################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/classic/ClassicData.cpp \
../src/classic/ClassicProblem.cpp \
../src/classic/ClassicUtil.cpp 

OBJS += \
./src/classic/ClassicData.o \
./src/classic/ClassicProblem.o \
./src/classic/ClassicUtil.o 

CPP_DEPS += \
./src/classic/ClassicData.d \
./src/classic/ClassicProblem.d \
./src/classic/ClassicUtil.d 


# Each subdirectory must supply rules for building sources it contributes
src/classic/%.o: ../src/classic/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -DIL_STD -I/opt/ibm/ILOG/CPLEX_Studio1251/cplex/include -I/opt/ibm/ILOG/CPLEX_Studio1251/concert/include -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


