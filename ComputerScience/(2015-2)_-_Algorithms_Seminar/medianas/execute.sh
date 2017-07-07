rm experiment.out;
g++-5 -std=gnu++11 experiment.cc -o experiment.out;
echo "Testing random distribution";
./experiment.out 2 6 random > random.log;
echo "Testing sorted distribution";
./experiment.out 2 6 sorted > sorted.log;
echo "Testing desorted distribution";
./experiment.out 2 6 desorted > desorted.log;
