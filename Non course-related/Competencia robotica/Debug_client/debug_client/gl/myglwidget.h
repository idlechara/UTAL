#ifndef MYGLWIDGET_H
#define MYGLWIDGET_H

#include <QtOpenGL/QGLWidget>
#include <QtOpenGL/QtOpenGL>
#include <libs/server.h>

class MyGLWidget : public QGLWidget
{
    Q_OBJECT
public:
    explicit MyGLWidget(QWidget *parent = 0);
    ~MyGLWidget();

protected:
    void initializeGL();
    void paintGL();
    void resizeGL(int width, int height);

    QSize minimumSizeHint() const;
    QSize sizeHint() const;
    void mousePressEvent(QMouseEvent *event);
    void mouseMoveEvent(QMouseEvent *event);

public slots:
    // slots for xyz-rotation slider
    void setXRotation(int angle);
    void setYRotation(int angle);
    void setZRotation(int angle);
    void setZero();
    void drawData(double x, double y, double z);

signals:
    // signaling rotation from mouse movement
    void xRotationChanged(int angle);
    void yRotationChanged(int angle);
    void zRotationChanged(int angle);
    void angle(int angle);

private:
    void draw();
    void drawGrid();
    void drawArrow( double r, double g, double b,
                    double x1, double y1, double z1,
                    double x2, double y2, double z2);
    double xRot, xRot_zero, xVertex;
    double yRot, yRot_zero, yVertex;
    double zRot, zRot_zero, zVertex;

    QPoint lastPos;
};

#endif // MYGLWIDGET_H
