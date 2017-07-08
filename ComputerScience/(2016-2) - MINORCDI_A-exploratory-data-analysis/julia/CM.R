#correlation matrix, an alternative to som
library(corrplot)
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
corrplot(cor(scaled.data), order = "hclust", tl.col='black', tl.cex=.75) 