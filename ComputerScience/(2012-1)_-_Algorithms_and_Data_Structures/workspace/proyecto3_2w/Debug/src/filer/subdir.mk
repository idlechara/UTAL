################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/filer/FileArray.cpp \
../src/filer/FileReader.cpp \
../src/filer/FileWritter.cpp 

OBJS += \
./src/filer/FileArray.o \
./src/filer/FileReader.o \
./src/filer/FileWritter.o 

CPP_DEPS += \
./src/filer/FileArray.d \
./src/filer/FileReader.d \
./src/filer/FileWritter.d 


# Each subdirectory must supply rules for building sources it contributes
src/filer/%.o: ../src/filer/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


