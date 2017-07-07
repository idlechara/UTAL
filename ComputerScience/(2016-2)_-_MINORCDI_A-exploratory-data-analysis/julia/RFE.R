#Recursive feature elimination -> HOURS
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
#scaled.data <- cbind(data[,5], J)
scaled.data <- cbind(J, scaled.INST2)
scaled.data <- as.data.frame(scaled.data);
#names(scaled.data)[1]<-"species"
#scaled.data$species <- as.factor(scaled.data$species)
#levels(scaled.data$species) <- make.names(levels(factor(scaled.data$species)))

# define the control using a random forest selection function
control <- rfeControl(functions=rfFuncs, method="cv", number=10)
# run the RFE algorithm
results <- rfe(scaled.data, data[,5], sizes=c(1:2152), rfeControl=control)
# summarize the results
print(results)
# list the chosen features
predictors(results)
# plot the results
plot(results, type=c("g", "o"))
