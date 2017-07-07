// // The function that we were asked to implement (standalone version). Use it if you're up to the challenge.

// void SOM(double **dataset, size_t dataset_registries, size_t dataset_vector_size,
//         size_t nn_dimx,
//         size_t nn_dimy,
//         size_t iterations,
//         double initial_learning_factor,
//         double final_learning_factor,
//         size_t initial_neighbourhood_radius,
//         size_t final_neighbourhood_radius){

//     #ifdef LAST_COLUMN_AS_CLASS
//         dataset_vector_size--;
//     #endif // LAST_COLUMN_AS_CLASS
//     //Create NN grid
//     neural_map = new Grid(nn_dimx, nn_dimy, dataset_vector_size,
//                     initial_neighbourhood_radius, final_neighbourhood_radius,
//                     iterations, initial_learning_factor, final_learning_factor, 0);
//     //normalize data, asume last columm as class?
//     double *min_vector = (double * ) malloc(sizeof(double) * dataset_vector_size);
//     double *max_vector = (double * ) malloc(sizeof(double) * dataset_vector_size);

//     for(size_t i=0; i<dataset_vector_size; i++){
//         min_vector[i] = DBL_MAX;
//         max_vector[i] = DBL_MIN;
//     }

//     for(size_t i=0; i<dataset_registries; i++){    
//         for(size_t j=0; j<dataset_vector_size; j++){
//             if(dataset[i][j] < min_vector[j])
//                 min_vector[j] = dataset[i][j];
//             if(dataset[i][j] > max_vector[j])
//                 max_vector[j] = dataset[i][j];            
//         }
//     }


//     for(size_t i=0; i<dataset_registries; i++){
//         for(size_t j=0; j<dataset_vector_size; j++){
//             dataset[i][j] = normalize(dataset[i][j], min_vector[j], max_vector[j]);
//         }
//     }


//     for(size_t iter=0; iter<iterations; iter++){
//         neural_map->clear_bmu();
//         print_state(status_win, (int)iter+1, (int)iterations);
//         for(int i=0; i<dataset_registries; i++){
//             neural_map->present_sample(dataset[i], dataset_vector_size);       
//         }
//         neural_map->advance_epoch(); //after presenting data improves cluster coloring c:
//         neural_map->repaint_umatrix();
//     }

//     for(int i=0; i<dataset_registries; i++){
//         neural_map->present_bmu_sample(dataset[i], dataset_vector_size,i);       
//     }
// }

#define LAST_COLUMN_AS_CLASS
#define WORLD_WIDTH 50
#define WORLD_HEIGHT 20
#define WIDTH 60
#define HEIGHT 14 
#define INPUT_SIZE 30

//testing program
#include <iostream>
#include <fstream>
#include <SDL2/SDL.h>
#include "lib/neuron/grid.cpp"
#include <float.h>
#include <ncurses.h>
#include <sys/ioctl.h>
#include <stdio.h>
#include <unistd.h>
#include <vector>
#include <sstream>
#include <iomanip>
#include <string.h>
#include <thread>
#include <chrono>
WINDOW *status_win;
WINDOW *input_file_box;
WINDOW *help_box;
size_t pixel_size;

int startx = 0;
int starty = 0;

std::ofstream *output_log;


std::vector<char> filepath;
//example to read IRIS data
char *text [] = { 
			"File path:             ",
			"# observations:        ",
			"# of columns:          ",
			"Iterations to perform: ",
			"ANN x grid size:       ",
			"ANN y grid size:       ",
			"initial learning rate: ",
			"final learning rate:   ",
			"initial search radius: ",
			"final search radius:   ",
			"pixel size (visual):   ",
		  };

char **choices = NULL;
int n_choices = NULL;

void print_log(std::string data){
    output_log->write(data.c_str(),data.size());
    output_log->write("\n",1);
    output_log->flush();
}

void print_menu(WINDOW *menu_win, int highlight){
	int x, y, i;	
	x = 2;
	y = 2;
	box(menu_win, 0, 0);
	for(i = 0; i < n_choices; ++i)
	{	if(highlight == i + 1) /* High light the present choice */
        {	
            mvwprintw(menu_win, y, x, "%s", text[i]);
            wattron(menu_win, A_REVERSE); 
			mvwprintw(menu_win, y, x+25, "%s", choices[i]);
			wattroff(menu_win, A_REVERSE);
		}
		else{
            mvwprintw(menu_win, y, x, "%s", text[i]);
			mvwprintw(menu_win, y, x+25, "%s", choices[i]);
        }
		++y;
	}
	wrefresh(menu_win);
}

void print_param_menu(WINDOW *target_win, int highlight, std::vector<std::string> choice_vector){
	int x, y, i;	
	x = 2;
	y = 2;
	box(target_win, 0, 0);
	for(i = 0; i < choice_vector.size(); ++i)
	{	
        if(highlight == i + 1) /* High light the present choice */
        {	wattron(target_win, A_REVERSE); 
            mvwprintw(target_win, y, x, "%s", (char *)choice_vector[i].c_str());
            wattroff(target_win, A_REVERSE);
		}
		else{
            mvwprintw(target_win, y, x, "%s", (char *)choice_vector[i].c_str());
        }
		++y;
	}
	wrefresh(target_win);
}

void print_result_window(WINDOW *target_win, std::vector<std::string> result){
	int x, y, i;	
	x = 2;
	y = 2;
    wclear(target_win);
	box(target_win, 0, 0);
    if(result.size()==0){wrefresh(target_win);return;};
	for(i = 0; i < result.size(); ++i){	
        mvwprintw(target_win, y, x, "%s", (char *)result[i].c_str());
		++y;
	}
	wrefresh(target_win);
}


void print_state(WINDOW *state_win, int iterations, int goal){
	int x, y, i;	

	x = 2;
	y = 2;
	box(state_win, 0, 0);

    mvwprintw(state_win, 1, x,    "Completed:  ");
    mvwprintw(state_win, 1, x+12, "%.2f %%", (100.0*(double)iterations/goal));

    mvwprintw(state_win, 2, x,    "ITERATIONS: ");
    mvwprintw(state_win, 2, x+12, "%d", iterations);
	wrefresh(state_win);
}

std::string removeLast(std::string x)
{
	std::string y;

	for(std::string::iterator i = x.begin(); i != x.end()-1; ++i)
		y.push_back(*i);

	return y;
}

std::string trim(std::string const& str)
{
    std::string word;
    std::stringstream stream(str);
    stream >> word;

    return word;
}

double normalize(double target, double start, double end){
    target -= start;    // offsets target
    end -= start;       // offsets ending
    //at this point, we assume starting at 0.0
    return target/end;
}
Grid *neural_map;

void SOM(double **dataset, size_t dataset_registries, size_t dataset_vector_size,
        size_t nn_dimx,
        size_t nn_dimy,
        size_t iterations,
        double initial_learning_factor,
        double final_learning_factor,
        size_t initial_neighbourhood_radius,
        size_t final_neighbourhood_radius){

    #ifdef LAST_COLUMN_AS_CLASS
        dataset_vector_size--;
    #endif // LAST_COLUMN_AS_CLASS
    //Create NN grid
    neural_map = new Grid(nn_dimx, nn_dimy, dataset_vector_size,
                    initial_neighbourhood_radius, final_neighbourhood_radius,
                    iterations, initial_learning_factor, final_learning_factor, pixel_size);
    //normalize data, asume last columm as class?
    double *min_vector = (double * ) malloc(sizeof(double) * dataset_vector_size);
    double *max_vector = (double * ) malloc(sizeof(double) * dataset_vector_size);

    for(size_t i=0; i<dataset_vector_size; i++){
        min_vector[i] = DBL_MAX;
        max_vector[i] = DBL_MIN;
    }

    for(size_t i=0; i<dataset_registries; i++){    
        for(size_t j=0; j<dataset_vector_size; j++){
            if(dataset[i][j] < min_vector[j])
                min_vector[j] = dataset[i][j];
            if(dataset[i][j] > max_vector[j])
                max_vector[j] = dataset[i][j];            
        }
    }


    for(size_t i=0; i<dataset_registries; i++){
        // std::cout << "Registry: {";
        for(size_t j=0; j<dataset_vector_size; j++){
            dataset[i][j] = normalize(dataset[i][j], min_vector[j], max_vector[j]);
            // std::cout << dataset[i][j];
            // if(j<dataset_vector_size -1)
                // std::cout << ", ";
        }
        // std::cout << "}\n";
    }


    for(size_t iter=0; iter<iterations; iter++){
        neural_map->clear_bmu();
        print_state(status_win, (int)iter+1, (int)iterations);
        for(int i=0; i<dataset_registries; i++){
            neural_map->present_sample(dataset[i], dataset_vector_size);       
        }
        neural_map->advance_epoch(); //after presenting data improves cluster coloring c:
        neural_map->repaint_umatrix(true);
        //std::cout << "advancing!\n";
    }
    print_log("Finished. Now presenting samples.");
    for(int i=0; i<dataset_registries; i++){
        neural_map->present_bmu_sample(dataset[i], dataset_vector_size,i);       
    }
    print_log("Finished. Now presenting samples presented.");
    //present data!
}


WINDOW *menu_win;
WINDOW *classes_win;
WINDOW *results_win;
std::vector<std::string> columns_to_show;
std::vector<std::string> results_to_show;

std::string path; 

int selected_column = 1;

int highlight = 1;
int choice = 0;
int c;
void call_from_thread(void)
{
    while(1){
        #ifdef LAST_COLUMN_AS_CLASS
            if(selected_column-1 < columns_to_show.size()-1){
                neural_map->repaint_heatmap(selected_column-1);
            }
            else{
                neural_map->repaint_umatrix();
            }
        #else
            if(selected_column-1 < columns_to_show.size()){
                neural_map->repaint_heatmap(selected_column-1);
            }
            else{
                neural_map->repaint_umatrix();
            }
        #endif // LAST_COLUMN_AS_CLASS

        print_param_menu(classes_win, selected_column, columns_to_show);
        refresh();
        c = wgetch(classes_win);
        if(c == KEY_UP){
            if(selected_column == 1)
                selected_column = columns_to_show.size();
            else
                --selected_column;
        }
        if(c == KEY_DOWN){
            if(selected_column == columns_to_show.size())
                selected_column = 1;
            else 
                ++selected_column;
        }
        
        if(c=='x')
        break;    
	}
   printf ("sub thread.\n");
   pthread_exit(NULL);
}

int main(void){
    output_log = new std::ofstream("log.txt");
    print_log("Starting SOM");
    
    struct winsize w;
    ioctl(STDOUT_FILENO, TIOCGWINSZ, &w);

    choices = (char **) malloc( 11 * sizeof(char *));
    for(size_t i=0; i<11; i++){
        choices[i] = (char *) malloc(sizeof(char) * 30);
    }
    n_choices = 11;
    strcpy(choices[0], "./smoke.data");
    strcpy(choices[1], "64");
    strcpy(choices[2], "2155");
    strcpy(choices[3], "100");
    strcpy(choices[4], "8");
    strcpy(choices[5], "8");
    strcpy(choices[6], "0.1");
    strcpy(choices[7], "0.00000001");
    strcpy(choices[8], "8");
    strcpy(choices[9], "1");
    strcpy(choices[10], "70");

    path = choices[0];
	initscr();
	clear();
	noecho();
	cbreak();	/* Line buffering disabled. pass on everything */
	startx = (80 - WIDTH);
	starty = (24 - HEIGHT);
		
	menu_win = newwin(HEIGHT, WIDTH, 3, 3);
    status_win = newwin(4, WIDTH, HEIGHT+3, 3);

	keypad(menu_win, TRUE);
	mvprintw(0, 0, "EDA Assignment 3 - Self Organizing Maps - Erik Regla");
	refresh();
	print_menu(menu_win, highlight);
	while(1){
        std::stringstream ss_buf;
        c = wgetch(menu_win);
        std::string out;
		switch(c){	
            case KEY_UP:
				if(highlight == 1)
					highlight = n_choices;
				else
					--highlight;
				break;
			case KEY_DOWN:
				if(highlight == n_choices)
					highlight = 1;
				else 
					++highlight;
				break;
			case 10:
                //dump
				choice = highlight;
				break;
            case 127:
                {
                    size_t choice_len = strlen(choices[highlight-1]);
                    choices[highlight-1][choice_len-1] = 0;
                    refresh();
                }
                break;
			default:
                size_t choice_len = strlen(choices[highlight-1]);
                if(choice_len == 30) continue;
                char *temp = (char *) malloc(sizeof(char) * 1);
                temp[0] = c;
                strcat(choices[highlight-1], temp);
                free(temp);
				refresh();
				break;
		}
        wclear(menu_win);
		print_menu(menu_win, highlight);
        refresh();
		if(choice != 0)	/* User did a choice come out of the infinite loop */
			break;
            
	}	
	
	

    
    
    size_t iterations   = std::stoi(choices[3]);
    size_t observations = std::stoi(choices[1]);
    size_t columns      = std::stoi(choices[2]);
    size_t ann_x        = std::stoi(choices[4]);
    size_t ann_y        = std::stoi(choices[5]);
    double initi_learn  = std::stod(choices[6]);
    double final_learn  = std::stod(choices[7]);
    size_t initi_radiu  = std::stoi(choices[8]);
    size_t final_radiu  = std::stoi(choices[9]);
    pixel_size  = std::stoi(choices[10]);

    FILE * fp;
    path = trim(path);
    fp = std::fopen (path.c_str(), "r");
    double **data = (double ** ) malloc(sizeof(double*) * observations);
    std::vector<std::string> collection;
    for(size_t i=0; i<observations; i++){
        data[i] = (double * ) malloc(sizeof(double) * columns);
        double temp = 0.0;
        for(size_t col=0; col<columns; col++){
            fscanf(fp, "%lf", &temp);    
            data[i][col] = temp;
            if(col>=columns-3){
                print_log("Retriving: " + std::to_string(temp));
            }
        }
        print_log("");
        char dummy_string[30];
        fscanf(fp, "%[^\n]", &dummy_string);
        collection.push_back(std::to_string(i) + ": " + dummy_string);
    }
    print_log("File reading succeeded!");
    
    SOM(data,
        observations,
        columns,
        ann_x,ann_y,
        iterations,
        initi_learn,
        final_learn,
        initi_radiu,
        final_radiu);


    print_log("Som finished.");

    werase(menu_win);
    werase(status_win);
    refresh();

    mvprintw(0, 0, "EDA Assignment 3 - Self Organizing Maps (Heatmap + UMatrix)- Erik Regla");
    mvprintw(1, 10, "Hover the mouse over the squares to view data detail");
    mvprintw(2, 10, "Execution terminated - Press 'x' to close.");
    
    refresh();

    results_win = newwin(18, 68, 3, 12+3);
    classes_win = newwin(18, 12, 3, 3);
    keypad(classes_win, TRUE);
    refresh();
    print_log("Starting rendering!");
    results_to_show.reserve(columns+1);
    columns_to_show.reserve(columns+1);
    for(int i=0; i<columns; i++){
        #ifdef LAST_COLUMN_AS_CLASS
        if(i==columns-1){
            print_log("LAST COLUMN AS CLASS");
            columns_to_show.push_back("U MATRIX");
            // results_to_show.push_back(collection[i]);
            continue;
        }
        #endif // LAST_COLUMN_AS_CLASS
        print_log("Adding column" + std::to_string(i));
        columns_to_show.push_back("Column " + std::to_string(i));
    }
    for(int i=0; i<observations; i++){
        results_to_show.push_back(collection[i]);
    }



    print_log("Calling results thread!");
    std::thread t1(call_from_thread);
    	
    SDL_Event event;
    int x_pos, y_pos, counter=0;
    wclear(results_win);
    print_result_window(results_win, results_to_show);

    while(1){
        while(SDL_PollEvent(&event)){
            if(event.type == SDL_MOUSEMOTION){
                x_pos = event.motion.x / pixel_size;
                y_pos = event.motion.y / pixel_size;
                SDL_Delay(20);
                results_to_show.clear();
                if(neural_map->__bmu_map.find(x_pos*ann_x + y_pos) != neural_map->__bmu_map.end()){
                    for(size_t i=0; i<neural_map->__bmu_map[x_pos*ann_x + y_pos].size(); i++){
                        results_to_show.push_back(collection[
                            neural_map->__bmu_map[x_pos * ann_x + y_pos].at((int)i)
                        ]);
                    }
                }
                // results_to_show.push_back(std::to_string(x_pos));
                // results_to_show.push_back(std::to_string(y_pos));
                wclear(results_win);
                print_result_window(results_win, results_to_show);
                // neural_map->__viewport->draw_dot(x_pos,y_pos,pixel_size,0,0,0);
                // neural_map->__viewport->update();
                refresh();
            }
        };
        
    }
    clrtoeol();
    refresh();
    endwin();

    output_log->close();
    return 0;
}