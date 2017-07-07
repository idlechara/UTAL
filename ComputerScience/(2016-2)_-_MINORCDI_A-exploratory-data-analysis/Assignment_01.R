#https://github.com/eregla/MINORCDI_A-exploratory-data-analysis/blob/master/Assignment_01.R
library(chron);
library(ggplot2);
library(corrplot)
library(gridExtra);
library(scales);
library(maptools);
#retrieving data from tarball
temp <- tempfile()
download.file("https://archive.ics.uci.edu/ml/machine-learning-databases/00357/occupancy_data.zip",temp);
training_data <- read.table(unz(temp, "datatraining.txt"), sep=",", header=TRUE);
line_data <- read.table(unz(temp, "datatest.txt"), sep=",", header=TRUE);
line_data2 <- read.table(unz(temp, "datatest2.txt"), sep=",", header=TRUE);
unlink(temp);

convert_nsm <- function(x){
  temp  <- as.POSIXct(x, format="%Y-%m-%d %H:%M:%S", tz="GMT");
  return( (hours(temp)  *60*60) +
            (minutes(temp)*60) +
            (seconds(temp)));
};
convert_date <- function(x){
  return(as.POSIXct(x, format="%Y-%m-%d %H:%M:%S", tz="GMT"));
};
convert_week <- function(x){
  temp  <- as.POSIXct(x, format="%Y-%m-%d %H:%M:%S", tz="GMT");
  return(!is.weekend(temp));
};


line_data["date"] <- lapply(line_data["date"], convert_date);
line_data <- line_data[line_data$date>"2015-02-03 00:06:00" & line_data$date < "2015-02-04 00:06:00",]

training_data["NSM"] <- lapply(training_data["date"], convert_nsm);
training_data["WeekStatus"] <- lapply(training_data["date"], convert_week);

training_data$Colour="green";
training_data$Colour[training_data$Occupancy==1]="blue";
# Basic Scatterplot Matrix

pdf("./Figura3.pdf")
pairs(~Temperature+Humidity+Light+CO2+HumidityRatio+NSM+WeekStatus,data=training_data, main="Figure 4 replica", col=training_data$Colour);
dev.off()

names(line_data)[names(line_data)=="date"] <- "Time"

pdf("./Figura4.pdf")
plot1 <- ggplot(line_data, aes(x = Time, y = Temperature, group=1))   + geom_line(colour="red")   + theme(legend.position="none", axis.text.x = element_text(angle = 90, hjust = 1)) + scale_x_datetime(breaks = date_breaks("2 hours"), labels=date_format("%H:%M"));
plot2 <- ggplot(line_data, aes(x = Time, y = Humidity, group=1))      + geom_line(colour="blue")  + theme(legend.position="none", axis.text.x = element_text(angle = 90, hjust = 1)) + scale_x_datetime(breaks = date_breaks("2 hours"), labels=date_format("%H:%M"));
plot3 <- ggplot(line_data, aes(x = Time, y = HumidityRatio, group=1)) + geom_line(colour="cyan")  + theme(legend.position="none", axis.text.x = element_text(angle = 90, hjust = 1)) + scale_x_datetime(breaks = date_breaks("2 hours"), labels=date_format("%H:%M"));
plot4 <- ggplot(line_data, aes(x = Time, y = CO2, group=1))           + geom_line(colour="green") + theme(legend.position="none", axis.text.x = element_text(angle = 90, hjust = 1)) + scale_x_datetime(breaks = date_breaks("2 hours"), labels=date_format("%H:%M"));
plot5 <- ggplot(line_data, aes(x = Time, y = Light, group=1))         + geom_line(colour="brown") + theme(legend.position="none", axis.text.x = element_text(angle = 90, hjust = 1)) + scale_x_datetime(breaks = date_breaks("2 hours"), labels=date_format("%H:%M"));
plot6 <- ggplot(line_data, aes(x = Time, y = Occupancy, group=1))     + geom_line(colour="black") + theme(legend.position="none", axis.text.x = element_text(angle = 90, hjust = 1)) + scale_x_datetime(breaks = date_breaks("2 hours"), labels=date_format("%H:%M"));
grid.arrange(plot1, plot2,  plot3,  plot4,  plot5,  plot6, ncol=1);
dev.off()
