#include <iostream>
#include <cstring>
#include <stack>
#include <bitset>
#include <unordered_set>
#include <queue>
#include <vector>
#include "State.cc"

typedef struct enclosure
{
    char requieres;
    long destination;
    long state;
} enclosure_t;

class Automata
{
public:
    Automata(){
    };
    Automata(std::string regex){
        //parse regex
        this->start_ptr_ = -1;
        this->current_ptr_ = 0;
        // this->start_ptr_ = 0;
        // this->current_ptr_ = 1;

        this->regex_word_ = regex;
        // this->state_nfa_.push_back(State());
        // this->or_stack_.push(0);

        //consume states to build NFA
        for (long i = 0; i < regex.size(); ++i){
            consume_state(i);
        }

        State new_state;
        state_nfa_.push_back(new_state);

        // //if there are any global or's
        // while(this->or_stack_.size()>1){
        //     state_nfa_[0].add_transition('_',this->or_stack_.top()+1);
        //     state_nfa_[this->or_stack_.top()].add_transition('_',this->state_nfa_.size()-1);
        //     this->or_stack_.pop();
        // }

        // //simplify epsylon transitions, in order to accelerate parsing
        // for(long i=0; i< state_nfa_.size(); i++){
        //     std::vector<long> epsilon_transitions = state_nfa_[i].destinations_for('_');
        //     for(long j=0; j<epsilon_transitions.size(); j++){
        //         // std::cout << "huehue\n";
        //         state_nfa_[i].transitions_.insert(state_nfa_[i].transitions_.end(), 
        //             state_nfa_[epsilon_transitions[j]].transitions_.begin(), 
        //             state_nfa_[epsilon_transitions[j]].transitions_.end());
        //     }
        //     state_nfa_[i].purge_epsilon();
        // }
        // for(long i=0; i>=state_nfa_.size(); i--){
        //     std::stack callback;
        //     callback.push(state_nfa_[i]);
        //     while(callback.size() > 0){
        //         std::vector<long> transitions = state_nfa_.destinations_for('_');
        //         for(int j = 0; j<transitions.size(); j++){
        //             callback.push(transitions[j]);
        //         }
        //     }
        // }

        //eliminar epsilon

        print_state_machine();
    };



    ~Automata(){};
    bool run(std::string word){
        #ifdef DEBUG
            std::cout << "************************************\n";
            std::cout << "\t" << word << "\n";
            std::cout << "************************************\n";
        #endif

        //initialize bitset
        // int bitset_size = this->state_nfa_.size();
        // std::bitset<bitset_size> nfa_current_transition;
        // std::bitset<bitset_size> nfa_next_transition;

        // std::cout << "Consuming " << word << std::endl;

        int bitset_size = this->state_nfa_.size();
        bool *nfa_current_transition = (bool *)malloc(sizeof(bool) * bitset_size);
        bool *nfa_next_transition = (bool *)malloc(sizeof(bool) * bitset_size);
        memset(nfa_current_transition, false, bitset_size);
        memset(nfa_next_transition, false, bitset_size);
        nfa_current_transition[0] = true;

        //for each word of the query
        char food;
        for (int word_idx = 0; word_idx < word.size(); ++word_idx){
            food = word[word_idx];

            // std::cout << "Consuming " << food << std::endl;



            #ifdef DEBUG
                //print state:
                std::cout << "recieved: " << food << "\n";
                std::cout << "current: ";
                for(int j=0; j<bitset_size; j++){
                    std::cout << nfa_current_transition[j] ? '1' : '0';
                }
            #endif


            
            /*********************************************************/
            // EPSILON TRANSITION PROCEDURE! 
            /*********************************************************/
            //get all epsilon-capable states:
            {
                std::vector<long> epsilon_capable;
                for(int state_idx=0; state_idx < bitset_size; state_idx++){
                    if(state_nfa_[state_idx].has_epsilon() && nfa_current_transition[state_idx])
                        epsilon_capable.push_back(state_idx);
                }

                for(int state_idx=0; state_idx < epsilon_capable.size(); state_idx++){
                    std::unordered_set<long> already_gone;
                    std::stack<long> not_dead_yet;
                    not_dead_yet.push(epsilon_capable[state_idx]);
                    already_gone.insert(epsilon_capable[state_idx]);
                    while(not_dead_yet.size() > 0){
                        std::vector<long> epsilon = state_nfa_[not_dead_yet.top()].destinations_for('_');
                        not_dead_yet.pop();
                        for(int epsilon_idx=0; epsilon_idx< epsilon.size(); epsilon_idx++){
                            long value = epsilon[epsilon_idx];
                            if(already_gone.count(value) == 0){
                                not_dead_yet.push(value);
                                already_gone.insert(value);
                                nfa_current_transition[value] = true;
                            }
                        }
                    }
                }
            }



            #ifdef DEBUG
                std::cout << "\nepsilon: ";
                for(int j=0; j<bitset_size; j++){
                    std::cout << nfa_current_transition[j] ? '1' : '0';
                }
            #endif
            



            //consume another state:
            for (int current_idx = 0; current_idx < bitset_size; ++current_idx){
                //if the transition is currently on, then
                // std::cout << "Checking state #" << current_idx << std::endl;
                if(nfa_current_transition[current_idx] == true){
                    //compute epsilon transitions
                    std::vector<long> transitions_to_enable;// = state_nfa_[current_idx].destinations_for('_');



                    // if(transitions_to_enable.size() > 0){
                    //     for (int transition_idx = 0; transition_idx < transitions_to_enable.size(); ++transition_idx){
                    //         // std::cout << "enabling transition #" << transition_idx << ": " 
                    //         // << transitions_to_enable[transition_idx] << std::endl;
                    //         nfa_current_transition[transitions_to_enable[transition_idx]] = true;
                    //     }
                    // }

                    //compute non epsilon transitions
                    transitions_to_enable = state_nfa_[current_idx].destinations_for(food);

                    //if there are any transitions avaliable for the current state:
                    if(transitions_to_enable.size() > 0){
                        for (int transition_idx = 0; transition_idx < transitions_to_enable.size(); ++transition_idx){
                            // std::cout << "enabling transition #" << transition_idx << ": " 
                            // << transitions_to_enable[transition_idx] << std::endl;
                            nfa_next_transition[transitions_to_enable[transition_idx]] = true;
                        }
                    }
                }
            }



            #ifdef DEBUG
                std::cout << "\nepsilon: ";
                for(int j=0; j<bitset_size; j++){
                    std::cout << nfa_current_transition[j] ? '1' : '0';
                }
            #endif

            /*********************************************************/
            // EPSILON TRANSITION PROCEDURE! AGAIN!
            /*********************************************************/
            //get all epsilon-capable states:
            {
                std::vector<long> epsilon_capable;
                for(int state_idx=0; state_idx < bitset_size; state_idx++){
                    if(state_nfa_[state_idx].has_epsilon() && nfa_next_transition[state_idx]){
                        epsilon_capable.push_back(state_idx);
                    }
                }

                for(int state_idx=0; state_idx < epsilon_capable.size(); state_idx++){
                    std::unordered_set<long> already_gone;
                    std::stack<long> not_dead_yet;
                    not_dead_yet.push(epsilon_capable[state_idx]);
                    already_gone.insert(epsilon_capable[state_idx]);
                    while(not_dead_yet.size() > 0){
                        std::vector<long> epsilon = state_nfa_[not_dead_yet.top()].destinations_for('_');
                        not_dead_yet.pop();
                        for(int epsilon_idx=0; epsilon_idx< epsilon.size(); epsilon_idx++){
                            long value = epsilon[epsilon_idx];
                            if(already_gone.count(value) == 0){
                                not_dead_yet.push(value);
                                already_gone.insert(value);
                                nfa_next_transition[value] = true;
                            }
                        }
                    }
                }
            }

            #ifdef DEBUG
                std::cout << "\nafter  : ";
                for(int j=0; j<bitset_size; j++){
                    std::cout << nfa_next_transition[j] ? '1' : '0';
                }
                std::cout << "\n---------------------------------------------------------\n";
            #endif


            //then, swap buffers
            memcpy(nfa_current_transition, nfa_next_transition, bitset_size * sizeof(bool));
            memset(nfa_next_transition, false, bitset_size);
        }


        if(nfa_current_transition[bitset_size-1] == true){
            return true;
        }
        return false;
    }

    
private:

    void print_state_machine(){
        for (int i = 0; i < state_nfa_.size(); ++i){
            std::cout << "Transition: #" << i << "\n";
            for (int j = 0; j < state_nfa_[i].transitions_.size(); ++j){
                std::cout << state_nfa_[i].transitions_[j].requieres << " -> " << state_nfa_[i].transitions_[j].destination << "\n";
            }for (int j = 0; j < state_nfa_[i].epsilon_transitions_.size(); ++j){
                std::cout << state_nfa_[i].epsilon_transitions_[j].requieres << " -> " << state_nfa_[i].epsilon_transitions_[j].destination << "\n";
            }
        }
    }

    void consume_state(long &index){
        //add concatenation
        State new_state;

        //assuming non or transition, only *()^
        if( regex_word_[index] == '(' ){
            or_flag_enabled_ = true;
            parenthesis_open_state = current_ptr_;
            // this->or_stack_.push(start_ptr_);
            return;
        }
        if( regex_word_[index] == ')' ){
            //compute or's
            for(int i=0; i < p_or_count_; i++){
                state_nfa_[this->or_stack_.top()].add_transition('_',current_ptr_);
                std::cout << "Undo: " << this->or_stack_.top() << "\n";
                this->or_stack_.pop();
            }
            parenthesis_flag_enabled_ = true;
            or_flag_enabled_ = false;
            return;
        }


        //compute ors
        if(regex_word_[index] == '|'){
            parenthesis_flag_enabled_ = false;
            if(or_flag_enabled_){
                pending_transition_ = true;
                state_nfa_[parenthesis_open_state].add_transition(regex_word_[index+1], current_ptr_+1); //divergence

                this->or_stack_.push(current_ptr_); //to connect later to pseudo-final state
                index+=1;
                p_or_count_ ++;
                start_ptr_++;
                current_ptr_++;
                state_nfa_.push_back(new_state);
            }
            else{
                this->or_stack_.push(current_ptr_);
                index++;
                p_or_count_ ++;
                start_ptr_++;
                current_ptr_++;
                state_nfa_.push_back(new_state);
            }
            return;
        }

        if(regex_word_[index] == '+'){
            if(parenthesis_flag_enabled_){
                state_nfa_[start_ptr_].add_transition(last_char_, parenthesis_open_state+1);
                parenthesis_flag_enabled_ = false;
                // std::cout << "kekekek\n" ;
            }else{
                state_nfa_[start_ptr_].add_transition(regex_word_[index-1], start_ptr_);
            }
            return;
        }

        if(regex_word_[index] == '*'){

            if(parenthesis_flag_enabled_){
                state_nfa_[start_ptr_].add_transition(last_char_, parenthesis_open_state+1);
                state_nfa_[parenthesis_open_state+1].add_transition('_', current_ptr_);     //jump
                parenthesis_flag_enabled_ = false;
            }
            else{
                state_nfa_[start_ptr_].add_transition('_', current_ptr_);     //jump
                state_nfa_[start_ptr_].add_transition(regex_word_[index-1], start_ptr_);
            }
            return;
        }


        parenthesis_flag_enabled_ = false;
        last_char_ = regex_word_[index];
        new_state.add_transition(regex_word_[index], current_ptr_ +1 );
        state_nfa_.push_back(new_state);
        current_ptr_ ++;
        start_ptr_ ++;
    }

    // void consume_state(long &index){
    //     std::cout << "Consuming: idx=" << index << "char=" << regex_word_[index] << "\n";
    //     if( (regex_word_[index] >= 'a' && regex_word_[index] <= 'z') ||
    //         (regex_word_[index] >= 'A' && regex_word_[index] <= 'Z')){
    //         State new_state;
            
    //         //add a new transition to the stack
    //         enclosure_t transition;
    //         transition.requieres = regex_word_[index];
    //         transition.destination = current_ptr_;
    //         transition.state = start_ptr_;
    //         e_stack_.push(transition);
    //         std::cout << "Transition pushed.\n";

    //         if(state_nfa_.size() >= start_ptr_){
    //             std::cout << "Adding blank transition.\n";
    //             //add a blank transition, because each character is a transition itself
    //             state_nfa_.push_back(new_state);
    //             //move ptrs
    //             current_ptr_++;
    //             start_ptr_++;
    //         }
    //         else{
    //             std::cout << "Correcting blank transition.\n";
    //             //then, there is a existing transition, so...
    //             state_nfa_[start_ptr_].add_transition(regex_word_[index],current_ptr_);
    //             current_ptr_++;
    //             start_ptr_ = current_ptr_ - 1;
    //         }

    //         return;
    //     }

    //     if(regex_word_[index] == '('){
    //         p_stack_.push(start_ptr_);
    //     }

    //     if(regex_word_[index] == ')'){
    //         //flush all or operators
    //         while(or_count_-- >= 0){
    //             std::cout << "or_stack_!" << or_stack_.size() << "\n";
    //             enclosure_t transition = or_stack_.top();
    //             std::cout << "add or!" << transition.state << "\n";
    //             state_nfa_[transition.state].add_transition(transition.requieres, start_ptr_);
    //             or_stack_.pop();
    //         }
    //         or_count_ = 0;
    //         p_stack_.pop();
    //     }

    //     if(regex_word_[index] == '|'){
    //         enclosure_t transition = e_stack_.top();
    //         e_stack_.pop();
    //         transition.destination = -1;    //final of level flag
    //         or_stack_.push(transition);
    //         start_ptr_ = p_stack_.top();
    //         or_count_++;
    //     }


    //     // if(regex_word_[index] == '+'){
    //     //     //two cases: there can be a )+ or a +. the latter is only to repeat the last char.
    //     //     if(regex_word_[index-1] == ')'){

    //     //     }
    //     //     else{
    //     //         enclosure_t transition;
    //     //         transition.requieres = e_stack_.top().requieres;
    //     //         transition.destination = e_stack_.top().state;
    //     //         transition.state = e_stack_.top().state;
    //     //         e_stack_.push(transition);
    //     //     }
    //     // }

    //     // if(regex_word_[index] == '*'){
    //     //     //two cases: there can be a )* or a *. the latter is only to repeat the last char.
    //     //     if(regex_word_[index-1] == ')'){

    //     //     }
    //     //     else{
    //     //         enclosure_t transition;
    //     //         transition.requieres = e_stack_.top().requieres;
    //     //         transition.destination = e_stack_.top().destination;
    //     //         transition.state = start_ptr_-1;
    //     //         e_stack_.push(transition);
    //     //     }
    //     // }

    // }

    bool or_flag_enabled_ = false;
    bool parenthesis_flag_enabled_ = false;
    long operator_ptr = -1;
    bool pending_transition_ = false;
    long pending_char_ = -1;
    long pending_state_ = -1;

    long w_ptr_ = 0;
    long p_or_count_ = 0;
    long or_count_ = 0;
    char last_char_ = 0;
    long parenthesis_close_state = -1;
    long parenthesis_open_state = -1;
    std::string regex_word_;
    std::queue<char> food;
    std::stack<long> p_stack_;
    std::stack<enclosure_t> e_stack_;
    std::stack<long> or_stack_;
    // std::stack<long> or_stack_;
    long start_ptr_;
    long current_ptr_;
    std::vector<State> state_nfa_;
};