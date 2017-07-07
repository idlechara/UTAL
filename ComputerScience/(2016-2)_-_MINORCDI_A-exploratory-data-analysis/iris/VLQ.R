#alpha_parameter = ]0..1[
#decay > 1

VLQ <- function(data_source, desired_clusters, data_dimension, alpha_parameter=0.2, decay=1.01, iterations, supervised=T, verbose=F){
  
  ## START PROCEDURE colour_by_class
  colour_by_class <- function(data, data_class){
    rbow <- rainbow(length(unique(data[[data_class]])))
    colour_idx <- 1
    for (class in unique(data[[data_class]])) {
      data$colour[data[[data_class]]==class] <- rbow[colour_idx];
      colour_idx <- colour_idx + 1
    }
    return(data)
  }
  ## END PROCEDURE colour_by_class
  
  ## START SUPERVISED TRAINING
  # This algorithm assumes that the last column is the training class.
  data_source <- read.table(data_source_path, sep=",", header=FALSE)
  
  scaled_datasource <- data_source[1:data_dimension]
  cookbook <- "global"
  cookbook <- scaled_datasource[sample(nrow(scaled_datasource),desired_clusters),]
  
  #cookbook <- data.frame(matrix(rnorm(desired_clusters*data_dimension), nrow=desired_clusters))
  scaled_datasource <- cbind(scaled_datasource, rev(data_source)[1])
  total_colums <- data_dimension+1;
  
  if(supervised){
    desired_clusters <- length(unique(scaled_datasource[,total_colums]))
    cookbook <- scaled_datasource[sample(nrow(scaled_datasource),desired_clusters), 1:data_dimension]
    cookbook <- cbind(cookbook,unique(scaled_datasource[,total_colums]))
    print("START supervised")
    print(cookbook)
    print("END supervised")
  } else {
    cookbook <- scaled_datasource[sample(nrow(scaled_datasource),desired_clusters),]
    cookbook <- cookbook[, 1:data_dimension]
    cookbook$class <- seq.int(nrow(cookbook))
  }
  alpha = alpha_parameter;
  
  for (iteration in (1:iterations)) {
    total_matches <- 0.0;  
    ##linear_decay
    #alpha_offset = (alpha_parameter_start - alpha_parameter_end)/iterations
    #alpha_parameter = alpha_parameter_start - (alpha_offset*(iteration-1))
    #log2 decay
    alpha <- alpha ^ decay;
    if(verbose){
      print("alpha")
      print(alpha)
    }
    #print("perform iteration")
    #perform learning
    cookbook_t1 = cookbook
    apply(scaled_datasource, 1, function(target) {
      ## here we take row value, select the cookbook vector and move it!
      #apply(cookbook, 1, distance)
      row <- as.numeric(target[1:data_dimension])
      minmum_distance <- Inf;
      minima_idx <- 1;
      for (cookbook_idx in (1:desired_clusters)) {
        distance <- 0;
        for (v_idx in (1:data_dimension)) {
          distance = distance + ((cookbook[cookbook_idx,][v_idx] - row[v_idx]) * (cookbook[cookbook_idx,][v_idx] - row[v_idx]))
        }
        distance = sqrt(distance);
        if(minmum_distance > distance){
          minmum_distance <- distance
          minima_idx <- cookbook_idx;
        }
      }
      
      if(supervised){
        if(cookbook[minima_idx,(data_dimension+1)] == target[(data_dimension+1)]){
          total_matches <<- total_matches + 1
        }
        if(cookbook[minima_idx,(data_dimension+1)] == target[(data_dimension+1)]){
          move_vector = (row - cookbook[minima_idx,1:data_dimension]);
          #print((alpha*move_vector))
          #print(minima_idx)
          cookbook[minima_idx,1:data_dimension] <<- cookbook[minima_idx,1:data_dimension] + (alpha*move_vector)
        }
        #else{
          #move_vector = (row - cookbook[minima_idx,1:data_dimension]);
          #cookbook[minima_idx,1:data_dimension] <<- cookbook[minima_idx,1:data_dimension] - (alpha*move_vector)
        #}
      }
      else{
        move_vector = (row - cookbook[minima_idx,1:data_dimension]);
        cookbook[minima_idx,1:data_dimension] <<- cookbook[minima_idx,1:data_dimension] + (alpha*move_vector)
      }
      
    })
    
    print("CONTENT START")
    print(cookbook_t1 - cookbook)
    print("CONTENT END")
    
    if(verbose){
      print("Precision:")
      print(as.double(as.double(total_matches)/as.double(nrow(data_source))))
      last_column_name = rev(names(cookbook))[1]
      cookbook_temp = cookbook;
      scaled_datasource_temp = scaled_datasource;
      data_source_temp = data_source;
      print(cookbook_temp)
      cookbook_temp[[last_column_name]] <- NULL
      cookbook_temp$class <- seq.int(nrow(cookbook_temp))
      names(cookbook_temp) <- names(scaled_datasource) 
      data_source_temp[, total_colums] <- sapply(data_source_temp[, total_colums], as.factor)
      cookbook_temp[, total_colums] <- sapply(cookbook_temp[, total_colums], as.factor)
      scaled_datasource_temp = rbind(scaled_datasource_temp, cookbook_temp)
      last_column_name = rev(names(scaled_datasource_temp))[1]
      pairs(scaled_datasource_temp[1:data_dimension], col=scaled_datasource_temp[[last_column_name]])  
    }
  }
  return(cookbook)
  ## END (NON)SUPERVISED TRAINING
}

iris = read.table("/Users/jvarred/Sources/Utalca/ExploratoryDataAnalysis/iris/iris.data", sep=",", header=FALSE)
data_source_path = "/Users/jvarred/Sources/Utalca/ExploratoryDataAnalysis/iris/iris.data"
data_source = read.table(data_source_path, sep=",", header=FALSE)
print(VLQ(data_source = data_source, data_dimension = 4, desired_clusters = 3,  iterations = 20, verbose = T))
