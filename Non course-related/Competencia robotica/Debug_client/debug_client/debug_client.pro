#-------------------------------------------------
#
# Project created by QtCreator 2014-03-29T17:34:12
#
#-------------------------------------------------

QT       += core gui opengl

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = debug_client
TEMPLATE = app

INCLUDEPATH += ../../../BeagleBone/LSM303DLHC/src/lib/

SOURCES += main.cpp\
        mainwindow.cpp \
    libs/server.cpp\
    libs/serverthread.cpp \   
    gl/myglwidget.cpp \
    network/client.cpp \
    ../../../BeagleBone/LSM303DLHC/src/lib/LSM303DLHC.cpp \
    network/debugdata.cpp

HEADERS  += mainwindow.h \
    libs/server.h\
    libs/serverthread.h \
    gl/myglwidget.h \
    network/client.h \
    ../../../BeagleBone/LSM303DLHC/src/lib/LSM303DLHC.h \
    network/debugdata.h
 
FORMS    += mainwindow.ui
