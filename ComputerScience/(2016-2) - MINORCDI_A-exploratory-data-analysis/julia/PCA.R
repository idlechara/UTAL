options(max.print=1000)
#PCA
data = read.table("data.csv", sep=",", header=TRUE)
# log transform 
J <- scale(data[,10])
scaled.INST2 <- scale(data[,11:2161])

scaled.data <- data[,1:9];
scaled.data <- cbind(scaled.data, J)
scaled.data <- cbind(scaled.data, scaled.INST2)

##analyze 1
log.purr <- scaled.data[, 10:2161]
purr.species <- data[, 5]

# apply PCA - scale. = TRUE is highly 
# advisable, but default is FALSE. 
purr.pca <- prcomp(log.purr,
                 center = TRUE,
                 scale. = TRUE)

print(purr.pca)
plot(purr.pca)
summary(purr.pca)
# Predict PCs
predict(purr.pca, newdata=tail(log.purr, 2))
