# Assignment 1 - Distributed systems

## Compilation

    gcc src/client.c -o client
    gcc src/broker.c -o broker
    gcc src/server.c -o server

## Execution
Execute each one of the following commands on different shells.

Bring up the fake NTP servers. For each entry on servercfg you should bring up one of those.
    
    ./server <listening_port> <max_queued_connections> <time_offset>

Bring up the broker. 

    ./broker <listening_port> <max_queued_connections> < servercfg

Start querying on the client. Once the client is running you should issue a query and the broker will handle the rest.

    ./client <broker_port> <broker_host>

## servercfg
In order to ease configuration, the `src/servercfg` file allows to target servers. The file format is:

    QUERY PORT HOST

In this source we provide a example serverconfig with the following datasources.

    TALCA 3004 localhost
    PARIS 3005 localhost
    LONDRES 3006 localhost

## Example
Execute each one of the following commands in a separate shell in the same given order.

```sh
#Start server listening on port 3004 with a max of 100 queued connections with a 3 hour delay.
./server 3004 100 -3 
#Start server listening on port 3005 with a max of 100 queued connections with a 3 hour delay.
./server 3005 100 -3 
#Start server listening on port 3006 with a max of 100 queued connections with a 3 hour delay.
./server 3006 100 -3 

#Start broker, listening on port 3000 with 100 max queue depth and load servercfg configuration file
./broker 3000 100 < src/servercfg

#Execute queries on src/queries using the client program
./client 3000 localhost < src/queries
```

## Disclaimer
The sourcecode is a little messy, sorry but not sorry.