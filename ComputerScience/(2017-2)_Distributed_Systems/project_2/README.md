# Distributed Systems - Project 2

## How to compile?
```
gcc main.c -o master
gcc slave.c -o slave
```

## How to execute?
Execute each one of the following commands in a separate terminal
```sh
./master 5   #Indicates that you'll bring 4 processes
./slave 0 1  #Indicates that this process will be the General 0, responding 1 (WAR!)
./slave 1 0  #Indicates that this process will be the General 1, responding 0 (Peace)
./slave 2 -1 #Indicates that this process will be the General 2, responding -1 (bizantine sucka)
./slave 3 0  #Indicates that this process will be the General 3, responding 0 (Peace)
./slave 4 0  #Indicates that this process will be the General 4, responding 0 (Peace)
```

Then, master process will ask you to press RETURN in order to trigger the process. Then once 
it's finished, press it again to release resources allocated.

## Why a slave and a master?
Because I felt doing it. Also, master acts as a mediator for triggering the process,
think of it as a human controlled semaphore. 