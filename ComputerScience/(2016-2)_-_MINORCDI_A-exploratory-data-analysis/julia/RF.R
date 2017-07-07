#random forest, works well.
library(randomForest)
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
scaled.data$Species <- as.factor(scaled.data$Species)
varNames <- names(scaled.data)
varNames <- varNames[!varNames %in% c("Species")]
varNames1 <- paste(varNames, collapse = "+")
rf.form <- as.formula(paste("Species", varNames1, sep = " ~ "))
cross.sell.rf <- randomForest(rf.form,scaled.data,ntree=500,importance=T)
plot(cross.sell.rf)
varImpPlot(cross.sell.rf,sort = T,main="Variable Importance",n.var=10)
weights <- information.gain(Species~. ,as.data.frame(scaled.data))
#print(weights)
subset <- cutoff.k(weights, 2)
f <- as.simple.formula(subset, "Species")
print(f)
head(weights[order(-weights$attr_importance), , drop = FALSE])

#analyse 2
scaled.data <- cbind(data[,7], J)
scaled.data[]
scaled.data <- cbind(scaled.data, scaled.INST2)
scaled.data <- as.data.frame(scaled.data);
names(scaled.data)[1]<-"Species"
scaled.data$Species <- as.factor(scaled.data$Species)
varNames <- names(scaled.data)
varNames <- varNames[!varNames %in% c("Species")]
varNames1 <- paste(varNames, collapse = "+")
rf.form <- as.formula(paste("Species", varNames1, sep = " ~ "))
cross.sell.rf <- randomForest(rf.form,scaled.data,ntree=500,importance=T)
plot(cross.sell.rf)
varImpPlot(cross.sell.rf,sort = T,main="Variable Importance",n.var=10)
weights <- information.gain(Species~. ,as.data.frame(scaled.data))
#print(weights)
subset <- cutoff.k(weights, 2)
f <- as.simple.formula(subset, "Species")
print(f)
head(weights[order(-weights$attr_importance), , drop = FALSE])

#analyse 3
scaled.data <- cbind(data[,9], J)
scaled.data[]
scaled.data <- cbind(scaled.data, scaled.INST2)
scaled.data <- as.data.frame(scaled.data);
names(scaled.data)[1]<-"Species"
scaled.data$Species <- as.factor(scaled.data$Species)
varNames <- names(scaled.data)
varNames <- varNames[!varNames %in% c("Species")]
varNames1 <- paste(varNames, collapse = "+")
rf.form <- as.formula(paste("Species", varNames1, sep = " ~ "))
cross.sell.rf <- randomForest(rf.form,scaled.data,ntree=500,importance=T)
plot(cross.sell.rf)
varImpPlot(cross.sell.rf,sort = T,main="Variable Importance",n.var=10)
weights <- information.gain(Species~. ,as.data.frame(scaled.data))
#print(weights)
subset <- cutoff.k(weights, 2)
f <- as.simple.formula(subset, "Species")
print(f)
head(weights[order(-weights$attr_importance), , drop = FALSE])

