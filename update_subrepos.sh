#!/bin/bash
mkdir -p .backup

function init_if_exists_git {
    if [ -d "$1" ]; then
        if [ -d "$1"/.git ]
        then
            # echo "git encontrado $1"
            # echo "git submodule add " \"$1\"
            REMOTE=$(git --git-dir="$1"/.git config --get remote.origin.url)
            # echo $REMOTE
            DESTINATION=./$1
            echo $DESTINATION
            cp -rfv "${DESTINATION}" ./.backup
            echo "DESTINATION" ${DESTINATION}
            rm -rfv "${DESTINATION}"
            git submodule add "${REMOTE}" "${DESTINATION}"
        fi
    fi
}

function nested_search {
    if [ "$1" -gt "0" ]
    then
        # echo "Probing directory: " $2
        for directory in "$2"/*
        do
            # echo "Found" $directory
            init_if_exists_git "${directory}"
            nested_search $[$1-1] "${directory}"
        done
    fi
}

for study_plan in *
do
    nested_search 4 "${study_plan}"
done