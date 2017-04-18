#Highly correlated factors, delivers all the classes with high correlation.
library(mlbench)
library(caret)
options(max.print=1000)
#PCA
data = read.table("data.csv", sep=",", header=TRUE)
# log transform 
J <- scale(data[,10])
scaled.INST2 <- scale(data[,11:2161])


#analyse 1
scaled.data <- cbind(J, scaled.INST2)
scaled.data <- as.data.frame(scaled.data);
names(scaled.data)[1]<-"First"

correlationMatrix <- cor(scaled.data)
highlyCorrelated <- findCorrelation(correlationMatrix, cutoff=.95)
print(highlyCorrelated)