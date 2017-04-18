#include <QtWidgets>
#include <QtOpenGL>
#include <iostream>
#include <libs/server.h>
#include <math.h>

#include "myglwidget.h"

MyGLWidget::MyGLWidget(QWidget *parent)
    : QGLWidget(QGLFormat(QGL::SampleBuffers), parent)
{
    xRot = 0;
    yRot = 0;
    zRot = 0;
    xRot_zero = 0;
    yRot_zero = 0;
    zRot_zero = 0;
    xVertex = 0;
    yVertex = 0;
    zVertex = 0;
}

MyGLWidget::~MyGLWidget()
{
}

QSize MyGLWidget::minimumSizeHint() const
{
    return QSize(50, 50);
}

QSize MyGLWidget::sizeHint() const
{
    return QSize(400, 400);
}

static void qNormalizeAngle(int &angle)
{
    while (angle < 0)
        angle += 360 * 16;
    while (angle > 360)
        angle -= 360 * 16;
}

void MyGLWidget::setZero(){
    this->xVertex = this->xRot_zero;
    this->yVertex = this->yRot_zero;
    this->zVertex = this->zRot_zero;
}

void MyGLWidget::drawData(double x, double y, double z){
    double norm = sqrt((x*x) + (y*y) + (z*z));
    this->xRot_zero = (double)x/norm;
    this->yRot_zero = (double)y/norm;
    this->zRot_zero = -(double)z/norm;
    updateGL();
    //std::cout << "Data recieved x_:" << x<< " y_:" << y<< " z_:" << z<< std::endl;
}

void MyGLWidget::setXRotation(int angle)
{
    qNormalizeAngle(angle);
    if (angle != xRot) {
        xRot = angle;
        emit xRotationChanged(angle);
        updateGL();
        //std::cout << "x:" << xRot << " y:" << yRot << " z:" << zRot << std::endl;
    }
}

void MyGLWidget::setYRotation(int angle)
{
    qNormalizeAngle(angle);
    if (angle != yRot) {
        yRot = angle;
        emit yRotationChanged(angle);
        updateGL();
        //std::cout << "x:" << xRot << " y:" << yRot << " z:" << zRot << std::endl;
    }
}

void MyGLWidget::setZRotation(int angle)
{
    qNormalizeAngle(angle);
    if (angle != zRot) {
        zRot = angle;
        emit zRotationChanged(angle);
        updateGL();
        //std::cout << "x:" << xRot << " y:" << yRot << " z:" << zRot << std::endl;
    }
}

void MyGLWidget::initializeGL()
{
    qglClearColor(Qt::white);

    glEnable(GL_DEPTH_TEST);
    glEnable(GL_CULL_FACE);
    glShadeModel(GL_SMOOTH);
    //glEnable(GL_LIGHTING);
    glEnable(GL_LIGHT0);

    static GLfloat lightPosition[4] = { 0, 0, 10, 1.0 };
    glLightfv(GL_LIGHT0, GL_POSITION, lightPosition);
    //glColorMaterial(GL_FRONT,GL_DIFFUSE);
}


//void MyGLWidget::setRotation(double x, double y, double z){

//    updateGL();
//}

void MyGLWidget::paintGL()
{
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    glLoadIdentity();
    glTranslatef(0.0, 0.0, -10.0);

//    glRotatef(xRot_zero, 1.0, 0.0, 0.0);
//    glRotatef(yRot_zero, 0.0, 1.0, 0.0);
//    glRotatef(zRot_zero, 0.0, 0.0, 1.0);

    glRotatef(100, 1.0, 0.0, 0.0);
    glRotatef(100, 0.0, 0.0, 1.0);

    glRotatef(xRot, 1.0, 0.0, 0.0);
    glRotatef(yRot, 0.0, 1.0, 0.0);
    glRotatef(zRot, 0.0, 0.0, 1.0);

    //std::cout << "x_:" << xRot<< " y_:" << yRot << " z_:" << zRot<< std::endl;
    draw();
}

void MyGLWidget::resizeGL(int width, int height)
{
    int side = qMin(width, height);
    glViewport((width - side) / 2, (height - side) / 2, side, side);

    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
#ifdef QT_OPENGL_ES_1
    glOrthof(-2, +2, -2, +2, 1.0, 15.0);
#else
    glOrtho(-2, +2, -2, +2, 1.0, 15.0);
#endif
    glMatrixMode(GL_MODELVIEW);
}

void MyGLWidget::mousePressEvent(QMouseEvent *event)
{
    lastPos = event->pos();
}

void MyGLWidget::mouseMoveEvent(QMouseEvent *event)
{
    int dx = event->x() - lastPos.x();
    int dy = event->y() - lastPos.y();

    if (event->buttons() & Qt::LeftButton) {
        setXRotation(xRot + dy);
        setYRotation(yRot + dx);
    } else if (event->buttons() & Qt::RightButton) {
        setXRotation(xRot + dy);
        setZRotation(zRot + dx);
    }

    lastPos = event->pos();
}

void MyGLWidget::drawGrid(){
    for(int i=-5; i<6; i++){
        glBegin(GL_LINES);
            glColor3f(0.7,0.7,0.7);
            glVertex3f(i,-99,0);
            glVertex3f(i,99,0);
        glEnd();
        glBegin(GL_LINES);
            glColor3f(0.7,0.7,0.7);
            glVertex3f(-99,i,0);
            glVertex3f(99,i,0);
        glEnd();
    }
}

void MyGLWidget::drawArrow(double r, double g, double b,
                           double x1, double y1, double z1,
                           double x2, double y2, double z2){
    glBegin(GL_LINES);
        glColor3f(r,g,b);
        glVertex3f(x1,y1,z1);
        glVertex3f(x2,y2,z2);
    glEnd();
}

void MyGLWidget::draw()
{

    drawGrid();

    drawArrow(1,0,0,
              0,0,0,
              2,0,0);

    drawArrow(0,1,0,
              0,0,0,
              0,2,0);

    drawArrow(0,0,1,
              0,0,0,
              0,0,2);

    drawArrow(0,0,0,
              0,0,0,
              xRot_zero,yRot_zero,zRot_zero);


    drawArrow(0,0,0,
              0,0,0,
              xVertex,yVertex,zVertex);

    //calculate angle
    double angle_ = (180.0 / 3.1415926)*acos(xRot_zero*xVertex + yRot_zero*yVertex + zRot_zero*zVertex);
    emit angle((int)angle_);

//    glBegin(GL_LINES);
//        glColor3f(0,0,0);
//        glVertex3f(0,0,0);
//        glVertex3f(xRot_zero,yRot_zero,zRot_zero);
//    glEnd();
//    glBegin(GL_LINES);
//        glColor3f(0.7,0.7,0.7);
//        glVertex3f(xRot_zero,0,0);
//        glVertex3f(xRot_zero,yRot_zero,0);
//    glEnd();
//    glBegin(GL_LINES);
//        glColor3f(0.7,0.7,0.7);
//        glVertex3f(0,yRot_zero,0);
//        glVertex3f(xRot_zero,yRot_zero,0);
//    glEnd();
//    glBegin(GL_LINES);
//        glColor3f(0.7,0.7,0.7);
//        glVertex3f(xRot_zero,yRot_zero,0);
//        glVertex3f(xRot_zero,yRot_zero,zRot_zero);
//    glEnd();

//    glBegin(GL_QUADS);
//    glColor3f(0,0,1);
//        glNormal3f(0,0,-1);
//        glVertex3f(-1,-1,0);
//        glVertex3f(-1,1,0);
//        glVertex3f(1,1,0);
//        glVertex3f(1,-1,0);

//    glEnd();
//    glBegin(GL_TRIANGLES);
//        glNormal3f(0,-1,0.707);
//        glVertex3f(-1,-1,0);
//        glVertex3f(1,-1,0);
//        glVertex3f(0,0,1.2);
//    glEnd();
//    glBegin(GL_TRIANGLES);
//        glNormal3f(1,0, 0.707);
//        glVertex3f(1,-1,0);
//        glVertex3f(1,1,0);
//        glVertex3f(0,0,1.2);
//    glEnd();
//    glBegin(GL_TRIANGLES);
//        glNormal3f(0,1,0.707);
//        glVertex3f(1,1,0);
//        glVertex3f(-1,1,0);
//        glVertex3f(0,0,1.2);
//    glEnd();
//    glBegin(GL_TRIANGLES);
//        glNormal3f(-1,0,0.707);
//        glVertex3f(-1,1,0);
//        glVertex3f(-1,-1,0);
//        glVertex3f(0,0,1.2);
//    glEnd();
}
