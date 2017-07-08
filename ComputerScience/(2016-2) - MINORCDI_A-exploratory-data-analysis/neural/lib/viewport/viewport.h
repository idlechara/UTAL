#ifndef VIEWPORT_H
#define VIEWPORT_H

#include <SDL2/SDL.h>
#include <SDL2/SDL_ttf.h>
#include <iostream>
#include <vector>
#include <string>

class Viewport{
protected:
public:
    Viewport(int w=640, int h=480);
    void draw_dot(int x, int y, int size = 1, char r=0x00, char g=0x00, char b=0x00);
    void draw_cage(int x, int y, int size = 1, char r=0x00, char g=0x00, char b=0x00);
    void clear();
    void update();
    void draw_pixel(int x, int y, char r, char g, char b, char alpha = SDL_ALPHA_OPAQUE);
    ~Viewport();
private:
    int screen_width;
    int screen_height;
    TTF_Font* font;
    std::vector< unsigned char > pixels;
    SDL_Window* gWindow = NULL;
    SDL_Renderer* gRenderer = NULL;
    SDL_Texture *texture;
};
#endif /* VIEWPORT_H */
