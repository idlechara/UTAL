#ifndef OPERATIONS_H
#define OPERATIONS_H

#define MB_PUT      0x00000001
#define MB_GET      0x00000002
#define MQ_PUT      0x00000004
#define MQ_GET      0x00000008
#define MQ_DELETE   0x00000010


// There is nothing specified that we should recieve the pid of
// target and destination, but well, here we are.
typedef struct {
    char message[1024];     // Actual message. It could be anything
    short container;
    short operation;
    size_t destination;
    size_t id;
    size_t size;
} message_t;

int mb_put(int mb_id, char *msg, int msg_size, int dest, server_config_t *config);
int mb_get(int mb_id, char *msg, int *msg_size, int dest, server_config_t *config);
int mq_put(int mq_id, char *msg, int msg_size, server_config_t *config);
int mq_get(int mq_id, char *msg, int *msg_size, server_config_t *config);
int mq_delete(int mq_id, int msg_id, server_config_t *config);
void rot13 (char *s);

#endif