#IG, fails due to the dimensionality curse. Few observations to associate them with a certain phenomena
#install.packages('FSelector')
library(FSelector)
options(max.print=1000)
#PCA
data = read.table("data.csv", sep=",", header=TRUE)
# log transform 
J <- scale(data[,10])
scaled.INST2 <- scale(data[,11:2161])

#analyse 1
scaled.data <- cbind(data[,5], J)
scaled.data[]
scaled.data <- cbind(scaled.data, scaled.INST2)
scaled.data <- as.data.frame(scaled.data);
names(scaled.data)[1]<-"Species"
weights <- information.gain(Species~. ,as.data.frame(scaled.data))
#print(weights)
subset <- cutoff.k(weights, 2)
f <- as.simple.formula(subset, "Species")
print(f)
print(head(weights[order(-weights$attr_importance), , drop = FALSE]))

#analyse 2
scaled.data <- cbind(data[,7], J)
scaled.data[]
scaled.data <- cbind(scaled.data, scaled.INST2)
scaled.data <- as.data.frame(scaled.data);
names(scaled.data)[1]<-"Species"
weights <- information.gain(Species~. ,as.data.frame(scaled.data))
#print(weights)
subset <- cutoff.k(weights, 2)
f <- as.simple.formula(subset, "Species")
print(f)
print(head(weights[order(-weights$attr_importance), , drop = FALSE]))


#analyse 3
scaled.data <- cbind(data[,9], J)
scaled.data[]
scaled.data <- cbind(scaled.data, scaled.INST2)
scaled.data <- as.data.frame(scaled.data);
names(scaled.data)[1]<-"Species"
weights <- information.gain(Species~. ,as.data.frame(scaled.data))
#print(weights)
subset <- cutoff.k(weights, 2)
f <- as.simple.formula(subset, "Species")
print(f)
print(head(weights[order(-weights$attr_importance), , drop = FALSE]))

