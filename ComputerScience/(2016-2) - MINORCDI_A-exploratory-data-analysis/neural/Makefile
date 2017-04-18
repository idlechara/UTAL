#OBJS specifies which files to compile as part of the project
OBJS = SOM.cpp

#CC specifies which compiler we're using
CC = g++-6 -std=c++0x -g

#COMPILER_FLAGS specifies the additional compilation options we're using
# -w suppresses all warnings
COMPILER_FLAGS = -w

#Includes
INCLUDE = -I/usr/local/include/SDL2 -D_THREAD_SAFE -I/usr/local/opt/ncurses/include

#LINKER_FLAGS specifies the libraries we're linking against
LINKER_FLAGS = $(INCLUDE) -L/usr/local/lib -L/usr/local/opt/ncurses/lib -lSDL2  -lm -lncurses -lpthread 

#OBJ_NAME specifies the name of our exectuable
OBJ_NAME = SOM

#This is the target that compiles our executable
all : $(OBJS)
		$(CC) $(OBJS) $(COMPILER_FLAGS) $(LINKER_FLAGS) -o $(OBJ_NAME)