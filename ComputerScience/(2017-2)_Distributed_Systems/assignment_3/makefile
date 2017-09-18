EXECS1=mpi_hello_world send_recv prime
MPICC?=mpicc

all: ${EXECS1}

mpi_hello_world: mpi_hello_world.c
	${MPICC} -o mpi_hello_world mpi_hello_world.c

send_recv: send_recv.c
	${MPICC} -o send_recv send_recv.c


prime: prime.c
	${MPICC} -o prime prime.c

clean:
	rm ${EXECS}
	