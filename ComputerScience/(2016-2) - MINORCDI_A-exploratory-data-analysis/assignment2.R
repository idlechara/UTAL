ejercicio_1 <- function(arr, target){
  arr[arr=='1'] <- target;
  return(arr);
}

ejercicio_2 <- function(arr, target){
  if(length(arr[arr==5])>=1){
    return(TRUE);
  }
  else{
    return(FALSE);
  }
};

ejercicio_3 <- function(A, B, C){
  a <- as.complex(A);
  b <- as.complex(B);
  c <- as.complex(C);
  discrim <- ((b**2) - (4*a*c));
  x1 <-  ((-b) + sqrt(discrim))/(2*a);
  x2 <-  ((-b) - sqrt(discrim))/(2*a);
  return(c((x1),(x2)))
}

ejercicio_4 <- function(arr, target){
  return (length(arr[arr==5]))
}

ejercicio_5 <- function(arr){
  print(paste("Mean: ", mean(arr)));
  print(paste("Standard dev.: ", sd(arr)));
}

ejercicio_6 <- function(target){
  counter <- 0;
  for(i in 2:target){
    if(target %% i == 0){
      counter <- counter+1;
    }
  }
  return(counter);
}