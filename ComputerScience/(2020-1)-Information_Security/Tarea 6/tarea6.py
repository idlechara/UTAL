
import sys
message, key, order = [line.strip() for line in sys.stdin.readlines()]

key = key.upper()
message = message.upper()

alphabet = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"

skey_lkt = [i % len(key) for i in range(0,len(message),1)]
cyphered = [(alphabet[(alphabet.index(key[idx%len(key)])+alphabet.index(chara))%len(alphabet)]) for idx, chara in enumerate(message, start=0)]
print(cyphered)

braile_lexicon = " A1B'K2L@CIF/MSP\"E3H9O6R^DJG>NTQ,*5<-U8V.%[$+X!&;:4\\0Z7(_?W]#Y)="
braile_alphabet = "⠀⠁⠂⠃⠄⠅⠆⠇⠈⠉⠊⠋⠌⠍⠎⠏⠐⠑⠒⠓⠔⠕⠖⠗⠘⠙⠚⠛⠜⠝⠞⠟⠠⠡⠢⠣⠤⠥⠦⠧⠨⠩⠪⠫⠬⠭⠮⠯⠰⠱⠲⠳⠴⠵⠶⠷⠸⠹⠺⠻⠼⠽⠾⠿"

braile_cmessage = [braile_alphabet[braile_lexicon.index(i)] if i in braile_lexicon else " " for i in cyphered]
braile_skey = [braile_alphabet[braile_lexicon.index(i)] if i in braile_lexicon else " " for i in key]

print(braile_cmessage)
print(braile_skey)

c_cyphered = [i if i in braile_lexicon else " " for i in cyphered]

bidx = [
    ["1" if (braile_lexicon.index(i) & 0b000001) else "0" for i in c_cyphered],
    ["1" if (braile_lexicon.index(i) & 0b001000) else "0" for i in c_cyphered],
    ["1" if (braile_lexicon.index(i) & 0b000010) else "0" for i in c_cyphered],
    ["1" if (braile_lexicon.index(i) & 0b010000) else "0" for i in c_cyphered],
    ["1" if (braile_lexicon.index(i) & 0b000100) else "0" for i in c_cyphered],
    ["1" if (braile_lexicon.index(i) & 0b100000) else "0" for i in c_cyphered]
]


print(" ".join(["".join(bidx[int(i)-1]) for i in order]))

skey_lkt = [i % len(key) for i in range(0,len(message),1)]
decyphered = [(alphabet[(alphabet.index(chara) - alphabet.index(key[idx%len(key)]))%len(alphabet)]) for idx, chara in enumerate(cyphered, start=0)]
print(decyphered)

