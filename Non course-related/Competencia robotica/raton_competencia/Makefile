CFLAGS=-g -std=gnu++11 -Wall -mcall-prologues -mmcu=atmega328p -Os
CPP=/usr/bin/avr-g++
CC=/usr/bin/avr-gcc
OBJ2HEX=/usr/bin/avr-objcopy
LDFLAGS=-Wl,-gc-sections -lpololu_atmega328p -lm
#LDFLAGS := $(LDFLAGS) -Wl,-u,vfprintf -lprintf_flt -lm
#LDFLAGS := $(LDFLAGS) -Wl,-u,vfprintf -lprintf_min

PORT=/dev/USBasp
AVRDUDE=/usr/bin/avrdude
TARGET=test
OBJECT_FILES=test.o
all: clean $(TARGET).hex program

clean:
	rm -f *.o *.hex *.obj *.hex

%.hex: %.obj
	$(OBJ2HEX) -R .eeprom -O ihex $< $@

%.o: %.cpp
	$(CPP) $(CFLAGS) -c -o $@ $<

%.obj: $(OBJECT_FILES)
	$(CC) $(CFLAGS) $(OBJECT_FILES) $(LDFLAGS) -o $@

program: $(TARGET).hex
	$(AVRDUDE) -p m328p -c usbasp -P $(PORT) -U flash:w:$(TARGET).hex

robot: clean $(TARGET).hex
