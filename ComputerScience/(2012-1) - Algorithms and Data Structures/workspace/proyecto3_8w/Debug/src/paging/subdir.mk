################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/paging/DiskPage.cpp \
../src/paging/HeapPaging.cpp \
../src/paging/Manager.cpp \
../src/paging/Paging.cpp 

OBJS += \
./src/paging/DiskPage.o \
./src/paging/HeapPaging.o \
./src/paging/Manager.o \
./src/paging/Paging.o 

CPP_DEPS += \
./src/paging/DiskPage.d \
./src/paging/HeapPaging.d \
./src/paging/Manager.d \
./src/paging/Paging.d 


# Each subdirectory must supply rules for building sources it contributes
src/paging/%.o: ../src/paging/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


