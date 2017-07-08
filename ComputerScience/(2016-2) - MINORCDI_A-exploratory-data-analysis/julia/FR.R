#Feature Ranking
library(mlbench)
library(caret)
set.seed(7)
options(max.print=1000)
#PCA
data = read.table("data.csv", sep=",", header=TRUE)
# log transform 
J <- scale(data[,10])
scaled.INST2 <- scale(data[,11:2161])

#analyse 1
scaled.data <- cbind(data[,5], J)
scaled.data <- cbind(scaled.data, scaled.INST2)
scaled.data <- as.data.frame(scaled.data);
names(scaled.data)[1]<-"species"
scaled.data$species <- as.factor(scaled.data$species)
levels(scaled.data$species) <- make.names(levels(factor(scaled.data$species)))

control <- trainControl(method="repeatedcv",number=10, repeats=3, allowParallel = TRUE)
model <- train(species~., data=scaled.data, method="lvq", trControl=control)
importance <- varImp(model, scale=FALSE)
print(importance)
plot(importance)

#analyse 2
scaled.data <- cbind(data[,7], J)
scaled.data[]
scaled.data <- cbind(scaled.data, scaled.INST2)
scaled.data <- as.data.frame(scaled.data);
names(scaled.data)[1]<-"Species"

control <- trainControl(method="repeatedcv", number=10, repeats=3)
model <- train(Species~., data=scaled.data, method="lvq", preProcess="scale", trControl=control)
importance <- varImp(model, scale=FALSE)
print(importance)
plot(importance)

#analyse 3
scaled.data <- cbind(data[,9], J)
scaled.data[]
scaled.data <- cbind(scaled.data, scaled.INST2)
scaled.data <- as.data.frame(scaled.data);
names(scaled.data)[1]<-"Species"

control <- trainControl(method="repeatedcv", number=10, repeats=3)
model <- train(Species~., data=scaled.data, method="lvq", preProcess="scale", trControl=control)
importance <- varImp(model, scale=FALSE)
print(importance)
plot(importance)