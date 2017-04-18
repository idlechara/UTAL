#ifndef VIEWPORT_CPP
#define VIEWPORT_CPP

#include "viewport.h"

Viewport::Viewport(int w, int h){
    this->screen_width = w;
    this->screen_height = h;
    this->pixels = std::vector<unsigned char>( w * h * 4, 0 );
    
    if( SDL_Init( SDL_INIT_VIDEO ) < 0 )
        printf( "SDL could not initialize! SDL Error: %s\n", SDL_GetError() );
    else{
        if( !SDL_SetHint( SDL_HINT_RENDER_SCALE_QUALITY, "1" ) )
            printf( "Warning: Linear texture filtering not enabled!" );
        this->gWindow = SDL_CreateWindow( "SDL Tutorial", SDL_WINDOWPOS_UNDEFINED, SDL_WINDOWPOS_UNDEFINED, this->screen_width, this->screen_height, SDL_WINDOW_SHOWN );
        if( this->gWindow == NULL )
            printf( "Window could not be created! SDL Error: %s\n", SDL_GetError() );
        else{
            gRenderer = SDL_CreateRenderer( this->gWindow, -1, SDL_RENDERER_ACCELERATED );
            if( gRenderer == NULL )
                printf( "Renderer could not be created! SDL Error: %s\n", SDL_GetError() );
        }
    }
    this->texture = SDL_CreateTexture(
        this->gRenderer,
        SDL_PIXELFORMAT_ABGR8888,
        SDL_TEXTUREACCESS_STREAMING,
        this->screen_width, this->screen_height
    );
}



inline void Viewport::draw_pixel(int x, int y, char r, char g, char b, char alpha){
    const unsigned int offset = ( this->screen_width * 4 * y ) + x * 4;
    this->pixels[ offset + 0 ] = r;
    this->pixels[ offset + 1 ] = g;
    this->pixels[ offset + 2 ] = b;
    this->pixels[ offset + 3 ] = alpha;    // a
}

void Viewport::draw_dot(int x, int y, int size, char r, char g, char b){
    //SDL_SetRenderDrawColor( this->gRenderer, r,g,b, 0xFF );
    //int start_x = x*this->screen_width;
    //int start_y = y*this->screen_height;
    int start_x = x * size;
    int start_y = y * size;
    for(int i=start_x; i<start_x+size; i++){
        for(int j=start_y; j<start_y+size; j++){
            this->draw_pixel(i, j, r ,g ,b);
        }   
    }
    //SDL_Rect pixel = {start_x - (size/2), start_y - (size/2), size, size};
    //SDL_Rect pixel = {start_x, start_y, size, size};
    //std::cout << start_x << ", " << start_y << ", " << size << ", " << size << ", " << std::endl;
    //SDL_RenderFillRect( this->gRenderer, &pixel );
}
void Viewport::draw_cage(int x, int y, int size, char r, char g, char b){
    int start_x = x * size;
    int start_y = y * size;
    // for(int i=1; i<size; i++){
    //     this->draw_pixel(i+start_x, start_y,r,g,b, 0.1);
    //     this->draw_pixel(i+start_x, start_y+size-1,r,g,b, 0.1);
    //     this->draw_pixel(start_x, start_y+i,r,g,b, 0.1);
    //     this->draw_pixel(start_x+size-1, start_y+i,r,g,b, 0.1);
    // }
    for(int i=start_x+(size/3); i<start_x+((size/3)*2); i++){
        for(int j=start_y+(size/3); j<start_y+((size/3)*2); j++){
            this->draw_pixel(i, j, r ,g ,b);
        }   
    }
}

void Viewport::clear(){
    SDL_SetRenderDrawColor( this->gRenderer, 0x00, 0x00, 0x00, SDL_ALPHA_OPAQUE );
    SDL_RenderClear( this->gRenderer );
}

void Viewport::update(){
    SDL_UpdateTexture(
        this->texture,
        NULL,
        &pixels[0],
        this->screen_width * 4
    );

    SDL_RenderCopy( gRenderer, this->texture, NULL, NULL );
    SDL_RenderPresent( gRenderer );
}

Viewport::~Viewport(){}

#endif /* VIEWPORT_CPP */
