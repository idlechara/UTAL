'use strict';

var usuarios = {
    522514: ["Jean Dupont", "Marseille", [1989, 11, 21]],
    587125: ["Perico Los Palotes", "Valparaíso", [1990, 4, 12]],
    189471: ["Jan Kowalsky", "Krakow", [1994, 4, 22]],
    914210: ["Antonio Nobel", "Valparaíso", [1983, 7, 1]]
}

function misma_ciudad(u1, u2) {
    return usuarios[u1][1] === usuarios[u2][1];
}

function diferencia_edad(u1, u2) {
    return Math.abs(usuarios[u1][2][0] - usuarios[u2][2][0]);
}

var amistades = [
    [198471, 289142], [138555, 429900], [349123, 781118]
    // Extra friends for testing
    , [522514, 587125], [189471, 914210], [587125, 189471]
];

function obtener_amigos(u) {
    let a = amistades.map(t => {
        if (t[0] === u) return t[1];
        if (t[1] === u) return t[0];
    }).filter(t => t);

    return (a)? a : new Array();
}

function recomendar_amigo(u) {
    let a = obtener_amigos(u);
    let r = new Array();
    a.map(t => {
        obtener_amigos(t).map(p => {
            if (
                (!(a.indexOf(p) >= 0)) && //is not friend - prevent self looping
                (misma_ciudad(u,p)) && //same city
                (diferencia_edad(u,p) < 10) && //different age
                (u!==p) //assumed. Since we can target onself
            ) r.push(p);
        });
    });
    return r;
}

//Unit testing?
if (misma_ciudad(587125, 522514) === false && misma_ciudad(587125, 914210) === true)
    console.log("misma_ciudad OK");
else
    console.log("misma_ciudad FAIL");

if (misma_ciudad(522514, 587125) === false && misma_ciudad(914210, 587125) === true)
    console.log("misma_ciudad (reversed) OK");
else
    console.log("misma_ciudad (reversed) FAIL");

if (diferencia_edad(914210, 587125) === 7 && diferencia_edad(522514, 587125) === 1)
    console.log("diferencia_edad OK");
else
    console.log("diferencia_edad FAIL");

if (diferencia_edad(587125, 914210) === 7 && diferencia_edad(587125, 522514) === 1)
    console.log("diferencia_edad (reversed) OK");
else
    console.log("diferencia_edad (reversed) FAIL");

if (obtener_amigos(522514).sort().join(',')===[587125].sort().join(','))
    console.log("obtener_amigos OK");
else
    console.log("obtener_amigos FAIL");

if (recomendar_amigo(587125).sort().join(',')===[914210].sort().join(','))
    console.log("recomendar_amigo OK");
else
    console.log("recomendar_amigo FAIL");