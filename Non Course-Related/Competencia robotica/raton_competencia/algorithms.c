#include <stdlib.h>
#include <math.h>
#define PI 3.14163264

float normal_distribution(unsigned int t, unsigned int extension, unsigned int warm_up = 200, float mu = 3.0f, float sigma = 1.0f)
{
    float x = (float)t;

    if (2 * warm_up > extension/* || compute_warmup*/)
    {
        warm_up = extension / 2;
    }

    float first_delta = warm_up;
    float second_delta = extension - (warm_up);

    if (t <= first_delta)
    {
        x = (float)t;
        x = x * (6.0f / (float)(2 * warm_up));
        return (1 / sqrt(2 * PI) * sigma) * exp(((-1) * (x - mu) * (x - mu)) / 2 * sigma * sigma) * 2.5;
    }
    else if ( t > warm_up && t  < second_delta)
    {
        x = (3.0f);
        return (1 / sqrt(2 * PI) * sigma) * exp(((-1) * (x - mu) * (x - mu)) / 2 * sigma * sigma) * 2.5;
    }
    else
    {
        x = (float)(first_delta - (t - second_delta));
        x = x * (6.0f / (float)(2 * warm_up));
        return (1 / sqrt(2 * PI) * sigma) * exp(((-1) * (x - mu) * (x - mu)) / 2 * sigma * sigma) * 2.5;
    }
}