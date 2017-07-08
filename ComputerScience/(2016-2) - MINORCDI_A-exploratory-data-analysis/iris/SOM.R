#alpha_parameter = ]0..1[
#decay > 1
#this assumes that the last column is the class
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


SOM <- function(X, dimx, dimy, Tf, ai, af, ri, rf){
  lambda <- Tf / log(ri);
  neuron_grid_size= dimx * dimy
  colMax <- function(data) sapply(data, max, na.rm = TRUE)
  colMin <- function(data) sapply(data, min, na.rm = TRUE)
  
  minmax_grid <- rbind(colMin(X[1:ncol(X)-1]), colMax(X[1:ncol(X)-1]));
  #print(minmax_grid)
  neural_grid <- apply(minmax_grid, 2, function(x){runif(neuron_grid_size, x[1],x[2])})
  neural_grid <- cbind(neural_grid, distance = rep(0, nrow(neural_grid)))
  
  for (iteration in seq(1:Tf)) {
    ##start of iteration
    neural_grid <- data.frame(neural_grid)
    apply(X[1:ncol(X)-1], 1 , function(sample_){
      #compute distances
      distance <- apply(neural_grid, 1, function(y){
        dist_ <-dist(rbind(sample_, y))
        as.numeric(dist_)
      })
      neural_grid$distance<- data.frame(distance)$distance
      #grab BMU
      min_row <- which.min(neural_grid$distance)
      x_cord <- as.integer(min_row / dimx)
      y_cord <- min_row %% dimx
      neural_grid[min_row,]
      
      # train neurons and neighbours
      neural_grid <- apply(cbind(seq_len(nrow(neural_grid)),neural_grid),1,
         function(y) {
           x = y[-1]
           x_cord_ <- as.integer(as.integer(rownames(neural_grid)[y[1]]) / dimx)
           y_cord_ <- as.integer(as.integer(rownames(neural_grid)[y[1]]) %% dimx)
           bmu_distance = ((x_cord_ - x_cord)**2) + ((y_cord_ - y_cord)**2)
           if(bmu_distance < ri**2){
             # do learning
             AoE = exp(-bmu_distance / (2 * (ri*2) ))
             x <- ai * AoE * (sample_ - x)
             return(x)
           }
           else{
             return(x);
           }
         }
      )
      
      #transform dataset and prepare for next iteration
      neural_grid <- data.frame(t(neural_grid))
    })
    # prepare for next datasampling
    ai <- max(ai * exp(-iteration / lambda), af)
    ri <- max(ri * exp(-iteration / lambda), rf)
  }
  neural_grid$distance <- NULL
  return(neural_grid);
}


iris = read.table("iris.data", sep=",", header=FALSE)
data_source_path = "iris.data"
data_source = read.table(data_source_path, sep=",", header=FALSE)
neurons <- SOM(X = data_source, dimx = 10, dimy = 10, Tf = 50, ai = 0.1, af = 0.0000001, ri = 15, rf = 1)
pairs(data_source[1:ncol(data_source)-1])
data_source <- colour_by_class(data_source, rev(names(data_source))[1]);
neurons$colour <- rep("#000000FF", nrow(neurons))
data_source$V5 <- NULL
hiss <- rbind(data_source, neurons)
pairs(hiss[1:4], col=hiss$colour)
#No se para que hice el scatterplot matrix siendo que ac?? no se entiende nada ni se quien es m??s cercano a quien
#Al menos la UMatrix en C si lo hac??a, pod??a hacer mapas de calor y no se demoraba eones en terminar :c