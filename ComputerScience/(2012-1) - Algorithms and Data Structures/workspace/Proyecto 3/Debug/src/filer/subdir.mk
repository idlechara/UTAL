################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/filer/DiskPage.cpp \
../src/filer/DiskPageManager.cpp 

OBJS += \
./src/filer/DiskPage.o \
./src/filer/DiskPageManager.o 

CPP_DEPS += \
./src/filer/DiskPage.d \
./src/filer/DiskPageManager.d 


# Each subdirectory must supply rules for building sources it contributes
src/filer/%.o: ../src/filer/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


