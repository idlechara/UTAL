CC=g++
CFLAGS= -lgmpxx -lgmp

all: tarea

tarea: 
	$(CC) maple_wizen.cpp $(CFLAGS) -DRECURSSIVE -o maple_wizen-recursive
	$(CC) maple_wizen.cpp $(CFLAGS) -DEXPRESSION -o maple_wizen-expression

clean:
	rm -rf maple_wizen-recursive maple_wizen-expression