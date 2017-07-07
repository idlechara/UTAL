#include <vector>

typedef struct transition
{
    char requieres;
    long destination;
} transition_t;

class State
{
public:
    std::vector<transition_t> transitions_;
    std::vector<transition_t> epsilon_transitions_;
    State(){};
    ~State(){};

    void add_transition(char requieres, long destination){
        if(requieres != '_'){
           transition_t new_transition;
           new_transition.requieres = requieres;
           new_transition.destination = destination;
           transitions_.push_back(new_transition);
        }
        else{
            transition_t new_transition;
            new_transition.requieres = requieres;
            new_transition.destination = destination;
            epsilon_transitions_.push_back(new_transition);
        }
    }

    std::vector<long> destinations_for(char requieres){
        std::vector<long> transitions_to_enable;
        if(requieres != '_'){
            for (int i = 0; i < transitions_.size(); ++i)
                if(requieres == transitions_[i].requieres)
                    transitions_to_enable.push_back(transitions_[i].destination);
        }
        else{
            for (int i = 0; i < epsilon_transitions_.size(); ++i)
                transitions_to_enable.push_back(epsilon_transitions_[i].destination);
        }
        return transitions_to_enable;
    }

    void purge_epsilon(){
        epsilon_transitions_.clear();
    }

    bool has_epsilon(){
        return (epsilon_transitions_.size() > 0);
    }


};