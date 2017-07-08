library(plyr)

game = read.table("/Volumes/Nitori/Documents/Sources/2016/Utalca/ExploratoryDataAnalysis/entropy/play.data", sep=",", header=TRUE)

ENTROPY <- function(AC, WA){
  total_data <- AC+WA
  positive <- (AC/total_data)
  negative <- (WA/total_data)
  return ( (-positive * log2(positive)) + (-negative * log2(negative)))
}

RELATIVE_ENTROPY <- function(dataset){
  dataset <- count(dataset)
  total_data <- sum(dataset$freq)
  entropy <- apply(dataset, 1, function(x){
    freq <- as.numeric(x["freq"])
    return(-(freq/total_data) * log2(freq/total_data))
  })
  dataset$entropy <- entropy
  return(sum(dataset$entropy))
}

GAIN <- function(dataset){
  general_entropy <- apply(cbind(seq_len(nrow(dataset)),dataset),2,
     function(x) {
       rel_entropy <- RELATIVE_ENTROPY(x)
       
       bool <- as.vector(dataset[,ncol(dataset)])
       cat("sample: " , length(x), "\n")
       cat("bool: " , length(bool), "\n")
       decide <- cbind(x,bool)
       freq_table <- count(decide)
       unique_classes <- unique(x)
       freq_table <- data.frame(freq_table)
       colnames(freq_table) <- c("obs", "bool", "freq")
       #for (obs in unique_classes) {
       #  cat(obs,"\n")
       #  cat("Hiss ", freq_table[freq_table$obs == obs,] , "\n")
       #}
       
       print(length(unique_classes))
       print(unique(x))
       print(freq_table)
       #print(decide[decide$bool == Yes])
     }
  )
}

print( game[game$PlayTennis == "Yes",] )

GAIN(game[-1])
RELATIVE_ENTROPY(game$Outlook)